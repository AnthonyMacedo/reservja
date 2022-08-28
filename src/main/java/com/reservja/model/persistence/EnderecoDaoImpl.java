package com.reservja.model.persistence;

import javax.inject.Named;

import com.reservja.model.entity.Endereco;
import com.reservja.model.repository.IEnderecoDAO;

@Named
public class EnderecoDaoImpl extends DaoGeneric<Endereco, Integer> implements IEnderecoDAO{


}
