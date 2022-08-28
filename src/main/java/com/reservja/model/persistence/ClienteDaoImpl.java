package com.reservja.model.persistence;

import javax.inject.Named;

import com.reservja.model.entity.Cliente;
import com.reservja.model.repository.IClienteDAO;

@Named
public class ClienteDaoImpl extends DaoGeneric<Cliente, Integer> implements IClienteDAO{

}
