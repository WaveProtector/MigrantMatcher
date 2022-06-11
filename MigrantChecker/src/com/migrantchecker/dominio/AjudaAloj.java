package com.migrantchecker.dominio;

public class AjudaAloj extends Ajuda {
	
	private int numPessoas;
	
	public AjudaAloj(String designacao) {
		super(designacao);
	}
	
	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
		
	}
}
