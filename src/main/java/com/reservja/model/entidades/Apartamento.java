package com.reservja.model.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name = "NUM_QUARTO")
	private Integer numeroQuarto;	
	
	@Column(name = "QTD_OCUPANTES")
	private Integer qtdOcupantes;
	
	@Column(name = "STATUS_APARTAMENTO", columnDefinition = "ENUM('DISPONIVEL', 'OCUPADO', 'RESERVADO')")
	@Enumerated(EnumType.STRING)
	private StatusApartamento statusApartamento;
	
	public Apartamento() {
	}

	public Apartamento(Integer idApartamento, Integer numeroQuarto, Integer qtdOcupantes,
			StatusApartamento statusApartamento) {
		super();
		this.idApartamento = idApartamento;
		this.numeroQuarto = numeroQuarto;
		this.qtdOcupantes = qtdOcupantes;
		this.statusApartamento = statusApartamento;
	}

	public Integer getIdApartamento() {
		return idApartamento;
	}

	public void setIdApartamento(Integer idApartamento) {
		this.idApartamento = idApartamento;
	}

	public Integer getNumeroQuarto() {
		return numeroQuarto;
	}

	public void setNumeroQuarto(Integer numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
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
