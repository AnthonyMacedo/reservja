package com.reservja.controller.beans;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;

import com.reservja.model.entity.Cliente;

@Named(value = "reservasBean")
public class ReservasBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	public ReservasBean() {
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	

}
