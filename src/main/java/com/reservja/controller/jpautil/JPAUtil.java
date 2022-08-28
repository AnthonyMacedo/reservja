package com.reservja.controller.jpautil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {

	private EntityManagerFactory emf = null;

	public JPAUtil() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("reservja");
		}
	}
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public Object getPrimaryKey(Object entity) {
		return emf.getPersistenceUnitUtil().getIdentifier(entity);
	}
}
