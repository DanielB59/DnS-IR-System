package com.shenkar.ir.model;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import com.shenkar.ir.entities.IEntity;

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
	
	public void entityInsert(IEntity... entities) throws HibernateException {	//	TODO	replace with custom exception!
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
}
