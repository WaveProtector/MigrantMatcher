package com.migrantchecker.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class catRegioes {
	
	private static List<Regiao> lr = new ArrayList<>();
	
	public static List<Regiao> getListaRegioes() {
		Regiao norte = new Regiao("Norte");
		Regiao centro = new Regiao("Centro");
		Regiao alentejo = new Regiao("Alentejo");
		Regiao algarve = new Regiao("Algarve");
		Regiao acores = new Regiao("Acores");
		Regiao madeira = new Regiao("Madeira");
		lr = Arrays.asList(new Regiao[] {norte, centro, alentejo, algarve, acores, madeira});
		return lr;
	}

	public static Regiao getRegiao(Regiao regiao) {
		Regiao r = null;
		boolean foundReg = false;
		for (int i = 0; i < lr.size() && !foundReg; i++) {
			if(lr.get(i).getDesignacao().equals(regiao.getDesignacao())) {
				r = lr.get(i);
				foundReg = true;
			}
		}
		return r;
	}

}
