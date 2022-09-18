package com.reservja.controller.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.reservja.model.entity.Apartamento;
import com.reservja.model.enums.StatusApartamento;
import com.reservja.model.repository.IApartamentoDAO;

@ViewScoped
@Named(value = "apartamentoBean")
public class ApartamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;	

	private Apartamento apartamento;
	
	private List<Apartamento> listaApartamentos;
	
	@Inject //transient para nao deixar o atributo serializar onde gera o erro ao usar outros escopos @view @session
	transient private IApartamentoDAO iApartamentoDao;
	
	private List<StatusApartamento> enumStatus = Arrays.asList(StatusApartamento.values());

	public ApartamentoBean() {		
		apartamento = new Apartamento();
	}	

	public String salvar() {
		iApartamentoDao.save(apartamento);
		msg("Apartamento cadastrado.");
		apartamento = new Apartamento();
		
		return "/paginas/apartamento.xhtml?faces-redirect=true";
	}
	
	public String limpar() {
		apartamento = new Apartamento();
		return "/paginas/apartamento.xhtml?faces-redirect=true";
	}
	
	public void msg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
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
			this.listaApartamentos = iApartamentoDao.getAll(Apartamento.class);
		}
		return listaApartamentos;
	}

	public void setListaApartamentos(List<Apartamento> listaApartamentos) {
		this.listaApartamentos = listaApartamentos;
	}

	public IApartamentoDAO getiApartamentoDao() {
		return iApartamentoDao;
	}

	public void setiApartamentoDao(IApartamentoDAO iApartamentoDao) {
		this.iApartamentoDao = iApartamentoDao;
	}

	

}
