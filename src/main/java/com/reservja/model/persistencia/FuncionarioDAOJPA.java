package com.reservja.model.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.reservja.controller.jpautil.JPAUtil;
import com.reservja.model.entidades.Funcionario;
import com.reservja.model.persistencia.dao.FuncionarioDAO;

public class FuncionarioDAOJPA extends DAOJPA<Funcionario, Integer> implements FuncionarioDAO {

	private EntityManager em;
	
	public FuncionarioDAOJPA() {
	}

	@Override
	public Funcionario consultarUsuario(String usuario, String senha) {
		
		Funcionario funcionario = null;
		
		em = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		funcionario = (Funcionario) em.createQuery("select f from Funcionario f where f.usuario = '" + usuario + "' and f.senha = '" + senha + "'").getSingleResult();

		entityTransaction.commit();

		return funcionario;
	}

}
