package com.reservja.model.persistence;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.reservja.model.entity.Apartamento;
import com.reservja.model.repository.IApartamentoDAO;

@Named
public class ApartamentoDaoImpl extends DaoGeneric<Apartamento, Integer> implements IApartamentoDAO {

	@Inject
	private EntityManager em;

	public ApartamentoDaoImpl() {
	}

	@SuppressWarnings("unused")
	@Override
	public boolean validarQuartoCadastrado(Integer numQuarto) {

		Apartamento quartoCadastrado = null;

		try {

			quartoCadastrado = (Apartamento) em
					.createQuery("select a from Apartamento a where a.numeroQuarto = '" + numQuarto + "'")
					.getSingleResult();

			return true;

		} catch (javax.persistence.NoResultException e) {
			System.out.println("QUARTO NAO LOCALIZADO.");
		}
		return false;
	}
	
	@Override
	public Apartamento verificaSeTemId(Integer numQuarto) {

		Apartamento quartoCadastrado = null;

		try {

			quartoCadastrado = (Apartamento) em
					.createQuery("select a from Apartamento a where a.numeroQuarto = '" + numQuarto + "'")
					.getSingleResult();

			return quartoCadastrado;

		} catch (javax.persistence.NoResultException e) {
			System.out.println("ID NAO LOCALIZADO.");
		}
		return null;

	}

}
