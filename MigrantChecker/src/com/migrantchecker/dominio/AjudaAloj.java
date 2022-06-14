package com.migrantchecker.dominio;

public class AjudaAloj extends Ajuda {
	
	private int numPessoas;
	
	public AjudaAloj(String designacao, Voluntario v) {
		super(designacao, v);
	}
	
	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
	}
}
