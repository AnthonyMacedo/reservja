package com.reservja.model.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.reservja.controller.jpautil.JPAUtil;
import com.reservja.model.persistencia.dao.DAO;

public class DAOJPA<T, I extends Serializable> implements DAO<T, I> {

	private EntityManager em;
	

	public DAOJPA() {
	}

	@Override
	public void save(T entity) {
		em = JPAUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(entity);
		et.commit();
		em.close();
	}

	@Override
	public void remove(Class<T> classe, I pk) {
		em = JPAUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		T entity = getById(classe, pk);
		em.remove(entity);
		et.commit();
		em.close();
	}

	@Override
	public T getById(Class<T> classe, I pk) {
		try {
			return this.em.find(classe, pk);
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Class<T> classe) {
		em = JPAUtil.getEntityManager();
		Query q = em.createQuery("select x from " + classe.getSimpleName() + " x");
		return q.getResultList();
	}

}
