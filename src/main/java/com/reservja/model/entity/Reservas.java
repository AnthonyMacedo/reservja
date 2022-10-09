package com.reservja.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservas implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESERVA")
	private Integer idReserva;
	
	@OneToOne
	@JoinColumn(name = "CLIENTE")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "APARTAMENTO")
	private Apartamento apartamento;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "DATA_INICIAL")
	private Date dataInicial;
	
	@Temporal(TemporalType.DATE)
	@Column (name = "DATA_FINAL")
	private Date dataFinal;
	
	public Reservas() {
	}

	public Reservas(Integer idReserva, Cliente cliente, Apartamento apartamento, Date dataInicial, Date dataFinal) {
		super();
		this.idReserva = idReserva;
		this.cliente = cliente;
		this.apartamento = apartamento;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idReserva == null) ? 0 : idReserva.hashCode());
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
		Reservas other = (Reservas) obj;
		if (idReserva == null) {
			if (other.idReserva != null)
				return false;
		} else if (!idReserva.equals(other.idReserva))
			return false;
		return true;
	}
	
	
}
