package com.migrantchecker.dominio;

/**
 * Classe que representa um voluntário no sistema Migrant Matcher.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class Voluntario {
	
	/**
	 * Representa o número de telemóvel do voluntário.
	 */
	private String numTel;
	
	/**
	 * Este construtor recebe um número de telemóvel de um voluntário e constrói um voluntário associando
	 * esse parâmetro ao mesmo.
	 * 
	 * @param numTel, número de telemóvel do voluntário.
	 */
	public Voluntario(String numTel) {
		this.numTel = numTel;
	}
	
	/**
	 * Este método devolve o número de telemóvel do voluntário.
	 * 
	 * @return o número de telemóvel do voluntário.
	 */
	public String getNumTel() {
		return this.numTel;
	}
}
