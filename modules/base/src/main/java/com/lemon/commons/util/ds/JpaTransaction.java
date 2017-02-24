package com.lemon.commons.util.ds;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTransaction {
	private static JpaTransaction instance = null;

	public static JpaTransaction sharedInstance() {
		if (instance == null) {
			instance = new JpaTransaction();
		}
		return instance;
	}

	private EntityManagerFactory factory;
	private EntityManager manager = null;
	private JpaTransaction() {
		factory = Persistence.createEntityManagerFactory("transactionManager");
	}

	public EntityManager beginTransaction() {
		if (manager != null) {
			manager.clear();
			manager.close();
		}
		manager = factory.createEntityManager();
		manager.getTransaction().begin();
		return manager;
	}

	public void commit() {
		if (manager != null) {
			manager.getTransaction().commit();
			manager = null;
		}
	}
}
