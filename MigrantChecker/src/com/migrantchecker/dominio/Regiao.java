package com.migrantchecker.dominio;

import java.util.List;

public class Regiao {
	
	private static String designacao;
	private List<Ajuda> la;
	
	public Regiao(String designacao) {
		Regiao.designacao = designacao; //talvez dê erro aqui?
	}
	
	public String getDesignacao() {
		return designacao;
	}

	public List<Ajuda> getListaAjudas() {
		return la;
	}

}
