package com.migrantchecker.dominio;

public class Regiao {
	
	private static String designacao; 
	
	public Regiao(String designacao) {
		Regiao.designacao = designacao; //talvez dê erro aqui?
	}
	
	public String getDesignacao() {
		return designacao;
	}

}
