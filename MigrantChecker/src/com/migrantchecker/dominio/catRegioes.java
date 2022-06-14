package com.migrantchecker.dominio;

import java.util.Arrays;
import java.util.List;

public class catRegioes {
	
	private static catRegioes INSTANCE = new catRegioes();
	private List<Regiao> lr;
	
	protected catRegioes() {
		Regiao norte = new Regiao("Norte");
		Regiao centro = new Regiao("Centro");
		Regiao alentejo = new Regiao("Alentejo");
		Regiao algarve = new Regiao("Algarve");
		Regiao acores = new Regiao("Acores");
		Regiao madeira = new Regiao("Madeira");
		this.lr = Arrays.asList(new Regiao[] {norte, centro, alentejo, algarve, acores, madeira});
	}
	
	public static catRegioes getInstance() {
		return INSTANCE;
	}
	
	public List<Regiao> getListaRegioes() {
		return lr;
	}

	public Regiao getRegiao(Regiao r) {
		Regiao reg = null;
		boolean foundReg = false;
		for (int i = 0; i < lr.size() && !foundReg; i++) {
			if(lr.get(i).getDesignacao().equals(r.getDesignacao())) {
				reg = lr.get(i);
				foundReg = true;
			}
		}
		return reg;
	}

	public void confirmAjudaToRegiao(Regiao r) {
		boolean foundReg = false;
		for(int i = 0; i < lr.size() & !foundReg; i++) {
			if(lr.get(i).getDesignacao().equals(r.getDesignacao())) {
				lr.set(i, r);
				foundReg = true;
			}
		}
	}
}
