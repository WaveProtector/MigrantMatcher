package com.migrantchecker.dominio;

import java.util.ArrayList;
import java.util.List;

public class Regiao {
	
	private String designacao;
	private List<Ajuda> la;
	
	public Regiao(String designacao) {
		this.designacao = designacao;
		this.la = new ArrayList<>();
	}
	
	public String getDesignacao() {
		return designacao;
	}

	public List<Ajuda> getListaAjudas() {
		return la;
	}
	
	public void addAjuda(Ajuda a) {
		la.add(a);
	}
}
