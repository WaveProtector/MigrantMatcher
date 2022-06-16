package com.migrantchecker.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma região no sistema Migrant Matcher.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class Regiao {
	
	/**
	 * Representa a designação da região.
	 */
	private String designacao;
	
	/**
	 * Representa as ajudas disponíveis na região.
	 */
	private List<Ajuda> la;
	
	/**
	 * Este construtor recebe uma designação para uma região e constrói essa região associando 
	 * essa designação.
	 * 
	 * @param designacao, designação da região.
	 */
	public Regiao(String designacao) {
		this.designacao = designacao;
		this.la = new ArrayList<>();
	}
	
	/**
	 * Este método devolve a designação associada à região.
	 * 
	 * @return a designação associada à região.
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * Este método devolve uma lista com as designações das ajudas disponíveis na região associada.
	 * 
	 * @return uma lista com as designações das ajudas disponíveis na região associada.
	 */
	public List<String> getListaAjudasDes() {
		List<String> ajudas = new ArrayList<>();
		for(int i = 0; i < this.la.size(); i++) {
			ajudas.add(this.la.get(i).getDesignacao());
		}
		return ajudas;
	}
	
	/**
	 * Este método devolve a lista das ajudas disponíveis na região associada.
	 * 
	 * @return uma lista das ajudas disponíveis na região associada.
	 */
	public List<Ajuda> getListaAjudas() {
		return this.la;
	}
	
	/**
	 * Este método adiciona uma ajuda à lista de ajudas da região.
	 * 
	 * @param a, a ajuda a adicionar.
	 */
	public void addAjuda(Ajuda a) {
		la.add(a);
	}
	
	/**
	 * Este método recebe a designação de uma ajuda e remove a ajuda com essa designação.
	 * 
	 * @param designacao, a designação da ajuda a remover.
	 */
	public void removeAjuda(String designacao) {
		boolean foundAjuda = false;
		for(int i = 0; i < la.size() && !foundAjuda; i++) {
			if(la.get(i).designacao.equals(designacao)) {
				la.remove(i);
				foundAjuda = true;
			}
		}
	}
	
	/**
	 * Este método recebe a designação de uma ajuda e retorna se a ajuda com essa designação existe na 
	 * região ou não.
	 * 
	 * @param designacao, a designação da ajuda a averiguar.
	 * @return se a ajuda com a designação dada existe na região ou não.
	 */
	public boolean temAjuda(String designacao) {
		boolean temAjuda = false;
		for(int i = 0; i < la.size() && !temAjuda; i++) {
			if(la.get(i).designacao.equals(designacao)) {
				temAjuda = true;
			}
		}
		return temAjuda;
	}
}
