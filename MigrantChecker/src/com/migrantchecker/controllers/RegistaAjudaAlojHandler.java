package com.migrantchecker.controllers;

import java.util.List;

import com.migrantchecker.dominio.AjudaAloj;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.catRegioes;

public class RegistaAjudaAlojHandler extends AbstractRegistaAjudaHandler {

	private AjudaAloj a;
	
	public RegistaAjudaAlojHandler(String numTel) {
		super(numTel);
	}
	
	public List<Regiao> indicarNumPessoas(int numPessoas) {
		a.setNumPessoas(numPessoas);
		return catRegioes.getListaRegioes();
	}

}
