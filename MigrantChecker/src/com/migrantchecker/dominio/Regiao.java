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

	public List<String> getListaAjudasDes() {
		List<String> ajudas = new ArrayList<>();
		for(int i = 0; i < this.la.size(); i++) {
			ajudas.add(this.la.get(i).getDesignacao());
		}
		return ajudas;
	}
	
	public List<Ajuda> getListaAjudas() {
		return this.la;
	}
	
	public void addAjuda(Ajuda a) {
		la.add(a);
	}
}
