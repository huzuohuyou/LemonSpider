package com.lemon.commons.util.ds;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.lemon.exception.LemonException;
import com.lemon.commons.log.Log;

/*
 * 获取数据库连接的公用类
 */
@SuppressWarnings("deprecation")
public final class DatabaseManager {
	private final static Log log = Log.getLogger(DatabaseManager.class);

	private static SessionFactory factory = null;
	private static ServiceRegistry serviceRegistry = null;

	private static void initSessionFactory() throws LemonException {
		// synchronized (DatabaseManager.class) {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			factory = configuration.buildSessionFactory(serviceRegistry);
			log.info("[DatabaseManager] Init hibernate SessionFactory successfully.");
		} catch (Exception e) {
			log.error("[DatabaseManager] Fail to init hibernate SessionFactory.", e);
			throw new LemonException("[DatabaseManager] Fail to init hibernate SessionFactory.", e, "1001");
		}
		// }
	}

	public static Session getSession() throws LemonException {
		synchronized (DatabaseManager.class) {
			if (factory == null) {
				initSessionFactory();
			}
		}
		Session session = null;
		try {
			session = factory.openSession();
		} catch (Exception e) {
			log.error("[DatabaseManager] Fail to get session from hibernate SessionFactory.", e);
			throw new LemonException("[DatabaseManager] Fail to get session from hibernate SessionFactory.", e, "1002");
		}

		if (session == null) {
			log.error("[DatabaseManager] Fail to get session from hibernate SessionFactory.");
			throw new LemonException("[DatabaseManager] Fail to get session from hibernate SessionFactory.", "1003");
		}

		return session;
	}

}