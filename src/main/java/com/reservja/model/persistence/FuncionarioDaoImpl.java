package com.reservja.model.persistence;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.reservja.model.entity.Funcionario;
import com.reservja.model.repository.IFuncionarioDAO;

@Named
public class FuncionarioDaoImpl extends DaoGeneric<Funcionario, Integer> implements IFuncionarioDAO {

	@Inject
	private EntityManager em;

	public FuncionarioDaoImpl() {
	}

	@Override
	public Funcionario consultarUsuario(String usuario, String senha) {

		Funcionario funcionario = null;

		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		try {

			funcionario = (Funcionario) em.createQuery(
					"select f from Funcionario f where f.usuario = '" + usuario + "' and f.senha = '" + senha + "'")
					.getSingleResult();

		} catch (javax.persistence.NoResultException e) {
			System.out.println(e + " / Usuário ou senha incorretos.");
		}

		entityTransaction.commit();

		return funcionario;
	}

}
