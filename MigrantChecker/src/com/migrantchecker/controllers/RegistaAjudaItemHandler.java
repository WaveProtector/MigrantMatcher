package com.migrantchecker.controllers;

import java.util.List;

import com.migrantchecker.dominio.AjudaItem;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.catRegioes;

public class RegistaAjudaItemHandler extends AbstractRegistaAjudaHandler {

	private AjudaItem a;
	
	public RegistaAjudaItemHandler(String numTel) {
		super(numTel);
	}
	
	public List<Regiao> indicaDescItem(String descItem) {
		a.setDescItem(descItem);
		return catRegioes.getInstance().getListaRegioes();
	}
	
}
