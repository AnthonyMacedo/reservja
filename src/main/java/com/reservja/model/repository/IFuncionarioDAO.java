package com.reservja.model.repository;

import com.reservja.model.entity.Funcionario;

public interface IFuncionarioDAO extends IDAO<Funcionario, Integer> {
	
	Funcionario consultarUsuario(String usuario, String senha);
	
}
