package com.migrantchecker.dominio;

import java.util.Arrays;
import java.util.List;

import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;

public class CatRegioes {
	
	private static CatRegioes INSTANCE = new CatRegioes();
	private List<Regiao> lr;
	
	protected CatRegioes() {
		Regiao norte = new Regiao("Norte");
		Regiao centro = new Regiao("Centro");
		Regiao alentejo = new Regiao("Alentejo");
		Regiao algarve = new Regiao("Algarve");
		Regiao acores = new Regiao("Acores");
		Regiao madeira = new Regiao("Madeira");
		this.lr = Arrays.asList(new Regiao[] {norte, centro, alentejo, algarve, acores, madeira});
	}
	
	public static CatRegioes getInstance() {
		return INSTANCE;
	}
	
	public List<Regiao> getListaRegioes() {
		return lr;
	}

	public Regiao getRegiao(Regiao r) throws RegiaoNotInCatRegioesException {
		Regiao reg = null;
		boolean foundReg = false;
		for (int i = 0; i < lr.size() && !foundReg; i++) {
			if(lr.get(i).getDesignacao().equals(r.getDesignacao())) {
				reg = lr.get(i);
				foundReg = true;
			}
		}
		if(!foundReg)
			throw new RegiaoNotInCatRegioesException();
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
	
	public void removeAjudaRegiao(Ajuda a) {
		boolean foundReg = false;
		for(int i = 0; i < lr.size() & !foundReg; i++) {
			if(lr.get(i).temAjuda(a.designacao)) {
				Regiao temp = lr.get(i);
				temp.removeAjuda(a.designacao);
				lr.set(i, temp);
				foundReg = true;
			}
		}
	}
}
