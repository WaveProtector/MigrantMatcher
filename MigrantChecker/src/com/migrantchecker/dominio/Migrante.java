package com.migrantchecker.dominio;

public class Migrante {

	private String nome;
	private String numTel;
	
	public Migrante(String nome, String numTel) {
		this.nome = nome;
		this.numTel = numTel;
	}
	
	public String getNumTel() {
		return this.numTel;
	}
}
