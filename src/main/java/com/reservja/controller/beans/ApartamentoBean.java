package com.reservja.controller.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	private List<StatusApartamento> enumStatus = Arrays.asList(StatusApartamento.values());

	public ApartamentoBean() {
		apartamento = new Apartamento();
	}	

	public String salvar() {
		dao.save(apartamento);
		apartamento = new Apartamento();
		mostrarMsg("Cadastrado com sucesso.");
		return "/paginas/apartamento.xhtml?faces-redirect=true";
	}
	
	public String limpar() {
		apartamento = new Apartamento();
		return "/paginas/apartamento.xhtml?faces-redirect=true";
	}
	
	public void mostrarMsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);
	}

	public List<StatusApartamento> getStatusApartamento() {
	    return enumStatus;
	}
	
	public StatusApartamento[] getDescricao() {
		return StatusApartamento.values();
	}

	public List<StatusApartamento> getEnumStatus() {
		return enumStatus;
	}

	public void setEnumStatus(List<StatusApartamento> enumStatus) {
		this.enumStatus = enumStatus;
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
