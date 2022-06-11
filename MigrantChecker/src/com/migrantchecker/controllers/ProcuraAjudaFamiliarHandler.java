package com.migrantchecker.controllers;

import com.migrantchecker.dominio.RegistoFamiliar;

public class ProcuraAjudaFamiliarHandler extends AbstractProcuraAjudaHandler {

	private RegistoFamiliar r;
	private String[] familia;
	
	public ProcuraAjudaFamiliarHandler(String tipoEscolhido) {
		r = new RegistoFamiliar(tipoEscolhido);
	}
	
	public void indicarNumPessoasFamiliar(int familiares) {
		this.familia = new String[familiares];
	}
	
	public void indicarFamiliar(String nome) {
		boolean registado = false;
		for(int i = 0; i < familia.length && !registado; i++) {
			if(familia[i] == null) {
				familia[i] = nome;
				registado = true;
			}
		}
	}
}
