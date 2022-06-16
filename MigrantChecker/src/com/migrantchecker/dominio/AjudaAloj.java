package com.migrantchecker.dominio;

public class AjudaAloj extends Ajuda {
	
	private int numPessoas;
	
	public AjudaAloj(Voluntario v) {
		super(v);
	}
	
	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
	}
	
	public int getNumPessoas() {
		return this.numPessoas;
	}
}
