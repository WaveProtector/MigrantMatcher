package com.migrantchecker.dominio;

public abstract class Ajuda {
	
	public String designacao;
	public Voluntario v;
	
	public Ajuda(String designacao, Voluntario v) {
		this.designacao = designacao;
		this.v = v;
	}
}
