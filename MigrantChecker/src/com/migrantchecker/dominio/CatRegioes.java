package com.migrantchecker.dominio;

import java.util.Arrays;
import java.util.List;

import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;

/**
 * Classe que representa o catálogo de regiões, possuindo uma lista de regiões já predefinidas e que
 * devem ser utilizadas pelo sistema Migrant Matcher.
 *  
 * @author Ricardo Mateus, fc56366
 *
 */
public class CatRegioes {
	
	/**
	 * Representa o catálogo de regiões.
	 */
	private static CatRegioes INSTANCE = new CatRegioes();
	/**
	 * Representa a lista de regiões predefinidas no catálogo de regiões.
	 */
	private List<Regiao> lr;
	
	/**
	 * Este construtor constrói o catálogo de regiões com a lista de regiões predefinidas.
	 */
	private CatRegioes() {
		Regiao norte = new Regiao("Norte");
		Regiao centro = new Regiao("Centro");
		Regiao alentejo = new Regiao("Alentejo");
		Regiao algarve = new Regiao("Algarve");
		Regiao acores = new Regiao("Acores");
		Regiao madeira = new Regiao("Madeira");
		this.lr = Arrays.asList(new Regiao[] {norte, centro, alentejo, algarve, acores, madeira});
	}
	
	/**
	 * Este método devolve uma instância do catálogo de regiões.
	 * 
	 * @return uma instância do catálogo de regiões.
	 */
	public static CatRegioes getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Este método devolve a lista de regiões predefinidas no catálogo de regiões.
	 * 
	 * @return a lista de regiões predefinidas no catálogo de regiões.
	 */
	public List<Regiao> getListaRegioes() {
		return lr;
	}

	/**
	 * Este método recebe uma região e devolve essa região da lista de regiões, caso esta exista na lista
	 * de regiões.
	 * 
	 * @param r, a região recebida.
	 * @return o equivalente dessa região da lista de regiões.
	 * @throws RegiaoNotInCatRegioesException caso a região não esteja na lista de regiões do catálogo 
	 * de regiões.
	 */
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

	/**
	 * Este método recebe uma região com ajudas associadas ao mesmo e substitui essa região pela região
	 * equivalente na lista de regiões do catálogo de regiões.
	 * 
	 * @param r, a região com ajudas associadas.
	 */
	public void confirmAjudaToRegiao(Regiao r) {
		boolean foundReg = false;
		for(int i = 0; i < lr.size() & !foundReg; i++) {
			if(lr.get(i).getDesignacao().equals(r.getDesignacao())) {
				lr.set(i, r);
				foundReg = true;
			}
		}
	}
	
	/**
	 * Este método recebe uma ajuda e remove essa ajuda da região associada à mesma na lista de regiões
	 * do catálogo de regiões.
	 * 
	 * @param a, a ajuda a ser removida da lista de regiões.
	 */
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
