package com.reservja.controller.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.reservja.model.entity.Apartamento;
import com.reservja.model.enums.StatusApartamento;
import com.reservja.model.persistence.ApartamentoDaoImpl;

@RequestScoped
@Named(value = "apartamentoBean")
public class ApartamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;	

	private Apartamento apartamento;
	
	private List<Apartamento> listaApartamentos;
	
	@Inject
	ApartamentoDaoImpl apartamentoDaoImpl;
	
	private List<StatusApartamento> enumStatus = Arrays.asList(StatusApartamento.values());

	public ApartamentoBean() {		
		apartamento = new Apartamento();
	}	

	public String salvar() {
		apartamentoDaoImpl.save(apartamento);
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
		if (this.listaApartamentos == null) {
			this.listaApartamentos = apartamentoDaoImpl.getAll(Apartamento.class);
		}
		return listaApartamentos;
	}

	public void setListaApartamentos(List<Apartamento> listaApartamentos) {
		this.listaApartamentos = listaApartamentos;
	}

	public ApartamentoDaoImpl getApartamentoDaoImpl() {
		return apartamentoDaoImpl;
	}

	public void setApartamentoDaoImpl(ApartamentoDaoImpl apartamentoDaoImpl) {
		this.apartamentoDaoImpl = apartamentoDaoImpl;
	}

}
