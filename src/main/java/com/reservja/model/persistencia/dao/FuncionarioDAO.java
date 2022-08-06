package com.reservja.model.persistencia.dao;

import com.reservja.model.entidades.Funcionario;

public interface FuncionarioDAO extends DAO<Funcionario, Integer> {
	
	Funcionario consultarUsuario(String usuario, String senha);
	
}
