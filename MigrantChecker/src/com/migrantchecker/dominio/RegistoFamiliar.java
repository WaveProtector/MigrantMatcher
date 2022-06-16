package com.migrantchecker.dominio;

public class RegistoFamiliar extends Registo {

	private String[] familia;
	
	public RegistoFamiliar(String tipoEscolhido, Migrante m) {
		super(tipoEscolhido, m);
	}
	
	public void setNumPessoasFamilia(int numPessoas) {
		this.familia = new String[numPessoas];
	}
	
	public void addFamiliar(String nome) {
		boolean registado = false;
		for(int i = 0; i < familia.length && !registado; i++) {
			if(familia[i] == null) {
				familia[i] = nome;
				registado = true;
			}
		}
	}
	
	public int numFamiliares() {
		return familia.length;
	}
}
