package com.reservja.model.persistence;

import javax.inject.Named;

import com.reservja.model.entity.Reservas;
import com.reservja.model.repository.IReservasDAO;

@Named
public class ReservasDaoImpl extends DaoGeneric<Reservas, Integer> implements IReservasDAO {

}
