package com.reservja.model.enums;

public enum StatusApartamento {
	
	Disponivel("Disponível"),
	Ocupado("Ocupado"),
	Reservado("Reservado");
	
	private String descricao;
	
	StatusApartamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	
}
