package com.migrantchecker.dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Registo {
	
	public String tipoEscolhido;
	private Migrante m;
	public List<Ajuda> laEscolhidas = new ArrayList<>();

	protected Registo(String tipoEscolhido, Migrante m) {
		this.tipoEscolhido = tipoEscolhido;
		this.m = m;
	}
	
	public void registaAjudasEscolhidas(List<Ajuda> laEscolhidas) {
		this.laEscolhidas = laEscolhidas;
	}
	
	public String getNumTel() {
		return m.getNumTel();
	}
}
