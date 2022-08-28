package com.reservja.model.persistence;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.reservja.model.repository.IDAO;

@Named
public class DaoGeneric<T, I extends Serializable> implements IDAO<T, I> {

	@Inject
	private EntityManager em;
		
	public DaoGeneric() {
	}

	@Override
	public void save(T entity) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(entity);
		et.commit();
	}

	@Override
	public void remove(Class<T> classe, I pk) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		T entity = getById(classe, pk);
		em.remove(entity);
		et.commit();
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
		Query q = em.createQuery("select x from " + classe.getSimpleName() + " x");
		return q.getResultList();
	}

}
