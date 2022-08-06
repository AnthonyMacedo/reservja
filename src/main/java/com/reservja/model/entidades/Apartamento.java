package com.reservja.model.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.reservja.model.enums.StatusApartamento;

@Entity
@Table(name = "APARTAMENTO")
public class Apartamento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID_APARTAMENTO")
	private Integer idApartamento;

	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "CLIENTE")
	private Cliente cliente;
	
	@Column(name = "QTD_OCUPANTES")
	private Integer qtdOcupantes;
	
	@Column(name = "STATUS_APARTAMENTO")
	private StatusApartamento statusApartamento;
	
	public Apartamento() {
	}
		
	public Apartamento(Integer idApartamento, Cliente cliente, Integer qtdOcupantes,
			StatusApartamento statusApartamento) {
		super();
		this.idApartamento = idApartamento;
		this.cliente = cliente;
		this.qtdOcupantes = qtdOcupantes;
		this.statusApartamento = statusApartamento;
	}

	public Integer getIdApartamento() {
		return idApartamento;
	}

	public void setIdApartamento(Integer idApartamento) {
		this.idApartamento = idApartamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getQtdOcupantes() {
		return qtdOcupantes;
	}

	public void setQtdOcupantes(Integer qtdOcupantes) {
		this.qtdOcupantes = qtdOcupantes;
	}

	public StatusApartamento getStatusApartamento() {
		return statusApartamento;
	}

	public void setStatusApartamento(StatusApartamento statusApartamento) {
		this.statusApartamento = statusApartamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idApartamento == null) ? 0 : idApartamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apartamento other = (Apartamento) obj;
		if (idApartamento == null) {
			if (other.idApartamento != null)
				return false;
		} else if (!idApartamento.equals(other.idApartamento))
			return false;
		return true;
	}
	
	
	
	
}
