package com.migrantchecker.dominio;

/**
 * Classe que implementa o registo, caso o tipo de registo seja um registo individual.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class RegistoIndividual extends Registo {

	/**
	 * Este construtor recebe o tipo de registo escolhido, o migrante que faz o registo e constrói um registo
	 * individual associando estes parâmetros.
	 * 
	 * @param tipoEscolhido, o tipo de registo escolhido.
	 * @param m, o migrante a efetuar o registo.
	 */
	public RegistoIndividual(String tipoEscolhido, Migrante m) {
		super(tipoEscolhido, m);
	}

}
