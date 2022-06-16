package com.migrantchecker.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa um registo de um migrante.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public abstract class Registo {
	
	/**
	 * Representa o tipo de registo escolhido.
	 */
	public String tipoEscolhido;
	
	/**
	 * Representa o migrante a efetuar o registo.
	 */
	private Migrante m;
	
	/**
	 * Representa a lista de ajudas escolhidas pelo migrante.
	 */
	public List<Ajuda> laEscolhidas = new ArrayList<>();

	/**
	 * Este construtor recebe o tipo de registo escolhido, o migrante que faz o registo e constrói um registo
	 * associando estes parâmetros.
	 * 
	 * @param tipoEscolhido, o tipo de registo escolhido.
	 * @param m, o migrante a efetuar o registo.
	 */
	protected Registo(String tipoEscolhido, Migrante m) {
		this.tipoEscolhido = tipoEscolhido;
		this.m = m;
	}
	
	/**
	 * Este método recebe uma lista de ajudas escolhidas pelo migrante e regista-as/associa-as.
	 * 
	 * @param laEscolhidas, a lista de ajudas escolhidas pelo migrante.
	 */
	public void registaAjudasEscolhidas(List<Ajuda> laEscolhidas) {
		this.laEscolhidas = laEscolhidas;
	}
	
	/**
	 * Este método devolve o número de telemóvel do migrante.
	 * 
	 * @return o número de telemóvel do migrante.
	 */
	public String getNumTel() {
		return m.getNumTel();
	}
}
