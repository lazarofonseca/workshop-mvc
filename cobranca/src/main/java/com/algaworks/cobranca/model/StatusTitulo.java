package com.algaworks.cobranca.model;

public enum StatusTitulo {
	
	PENDENTE("Pendente"),
	RECEBIDO("Recebido"),
	CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusTitulo(String decricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
