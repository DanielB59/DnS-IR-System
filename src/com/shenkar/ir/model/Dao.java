package com.shenkar.ir.model;

import java.util.*;

import javax.persistence.PersistenceException;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.shenkar.ir.entities.*;

public class Dao implements IDao {
	
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
}
