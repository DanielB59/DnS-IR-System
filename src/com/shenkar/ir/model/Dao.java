package com.shenkar.ir.model;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

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
	
}
