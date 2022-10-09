package com.reservja.model.repository;

import com.reservja.model.entity.Apartamento;

public interface IApartamentoDAO extends IDAO<Apartamento, Integer> {

	public boolean validarQuartoCadastrado(Integer numQuarto);
	
	public Apartamento verificaSeTemId(Integer numQuarto);
	
}
