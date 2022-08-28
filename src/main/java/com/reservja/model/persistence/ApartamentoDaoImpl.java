package com.reservja.model.persistence;

import javax.inject.Named;

import com.reservja.model.entity.Apartamento;
import com.reservja.model.repository.IApartamentoDAO;

@Named
public class ApartamentoDaoImpl extends DaoGeneric<Apartamento, Integer> implements IApartamentoDAO {
	
}
