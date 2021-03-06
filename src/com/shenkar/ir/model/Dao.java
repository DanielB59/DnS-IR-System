package com.shenkar.ir.model;

import java.util.*;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import com.shenkar.ir.entities.*;

public class Dao {
	
	private static Dao instance = null;
	
	private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	private Metadata metadata = null;
	private SessionFactory factory = null;
	
	private Dao() throws ClassNotFoundException {
		try {
			metadata = new MetadataSources(registry).buildMetadata();
			factory = metadata.buildSessionFactory();
//			builder = factory.getCriteriaBuilder();
			factory.createEntityManager();
//			entityManager = factory.createEntityManager();
//			metaModel = entityManager.getMetamodel();
		} catch (HibernateException e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
			throw e;
		}
	}
	
	public static Dao getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new Dao();
		}
		return instance;
	}
	
	public void insertTerms(Collection<Term> terms) throws HibernateException {	//	TODO	replace with custom exception!
		Session session = null;
		
		try {
			session = factory.openSession();
			
			for (IEntity term : terms)
				try {
					session.beginTransaction();
					session.save(term);
					session.getTransaction().commit();
				}
				catch (PersistenceException e1) {
					session.getTransaction().rollback();
				}
		}
		catch (HibernateException e2) {
			e2.printStackTrace();
			session.getTransaction().rollback();
			throw e2;
		}
		finally {
			session.close();
		}
	}
	
	public void insertEntity(IEntity... entities) throws HibernateException {	//	TODO	replace with custom exception!
		Session session = null;
		
		try {
			session = factory.openSession();
			
			session.beginTransaction();
			
			for (IEntity entity : entities)
				session.save(entity);
			
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw e;
		}
		finally {
			session.close();
		}
	}
	
	public void insertLinks(Index indexTable) throws HibernateException {
		try {
			insertEntity(indexTable.document);
			insertTerms(indexTable.index.keySet());
			
			for (Link link : indexTable.toLinks()) {
				insertEntity(link);
			}
		}
		catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Batch> getAllBatches() {
		Session session = this.factory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Batch> query = builder.createQuery( Batch.class );
		Root<Batch> root = query.from( Batch.class );
		query.select(root);
		List<Batch> result = session.createQuery(query).getResultList();
		for (Batch batch : result) {
			session.beginTransaction();
			session.remove(batch);
			session.getTransaction().commit();
		}
		session.close();
		return result;
	}
	
	public List<Term> getAllTerms() {
		Session session = this.factory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Term> query = builder.createQuery( Term.class );
		Root<Term> root = query.from( Term.class );
		query.select(root);
		List<Term> result = session.createQuery(query).getResultList();
		for (Term term : result) {
			session.beginTransaction();
			session.remove(term);
			session.getTransaction().commit();
		}
		session.close();
		return result;
	}
	
	public List<Link> search(List<String> terms) {
		Session session = this.factory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Link> query = builder.createQuery( Link.class );
		Root<Link> root = query.from( Link.class );
		query.select(root);
		List<Link> temp = session.createQuery(query).getResultList();
		session.close();
		List<Link> result = new ArrayList<Link>();
		for (Link link : temp) {
			if (terms.contains(link.getTerm().getTerm()) && link.getDocument().getActive()) {
				System.out.println(link);
				result.add(link);
			}
		}
		return result;
	}
	
	public Document getDocument(Integer id) {
		Session session = this.factory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Document> query = builder.createQuery( Document.class );
		Root<Document> root = query.from( Document.class );
		query.select(root).where(builder.equal(root.get("id"), id));
		List<Document> result = session.createQuery(query).getResultList();
		session.close();
		if (result.size() == 1)
			return result.get(0);
		else
			return null;
	}
	
	public void disableDocuments(Document... docs) {
		Session session = null;
		
		try {
			session = factory.openSession();
			
			session.beginTransaction();
			
			for (Document doc : docs) {
				doc.setActive(false);
				session.update(doc);
			}
			
			session.getTransaction().commit();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw e;
		}
		finally {
			session.close();
		}
	}
}
