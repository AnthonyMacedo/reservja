package com.reservja.controller.jpautil;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {

	private static EntityManagerFactory emf = null;

	static {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("reservja");
		}
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static Object getPrimaryKey(Object entity) {
		return emf.getPersistenceUnitUtil().getIdentifier(entity);
	}
}
