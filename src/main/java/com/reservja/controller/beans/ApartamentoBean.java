package com.reservja.controller.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reservja.model.entidades.Apartamento;
import com.reservja.model.enums.StatusApartamento;
import com.reservja.model.persistencia.ApartamentoDAOJPA;
import com.reservja.model.persistencia.dao.ApartamentoDAO;

@ViewScoped
@ManagedBean(name = "apartamentoBean")
public class ApartamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Apartamento apartamento;
	private List<Apartamento> listaApartamentos;

	ApartamentoDAO dao = new ApartamentoDAOJPA();
	
	private List<StatusApartamento> enumStatus;

	public ApartamentoBean() {
		apartamento = new Apartamento();
		enumStatus = Arrays.asList(StatusApartamento.values());
	}	

	public String salvar() {
		dao.save(apartamento);
		apartamento = new Apartamento();
		return "/paginas/apartamento.xhtml?faces-redirect-true";
	}

	public List<StatusApartamento> getStatusApartamento() {
	    return enumStatus;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public List<Apartamento> getListaApartamentos() {
		return listaApartamentos;
	}

	public void setListaApartamentos(List<Apartamento> listaApartamentos) {
		this.listaApartamentos = listaApartamentos;
	}

	public ApartamentoDAO getDao() {
		return dao;
	}

	public void setDao(ApartamentoDAO dao) {
		this.dao = dao;
	}

}
