package com.migrantchecker.dominio;

/**
 * Classe que representa um migrante no sistema Migrant Matcher.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class Migrante {

	/**
	 * Representa o nome do migrante
	 */
	private String nome;
	
	/**
	 * Representa o número de telemóvel do migrante
	 */
	private String numTel;
	
	/**
	 * Este construtor recebe um nome e número de telemóvel de um migrante e constrói um migrante associando
	 * esses parâmetros.
	 * 
	 * @param nome, o nome do migrante.
	 * @param numTel, o número de telemóvel do migrante.
	 */
	public Migrante(String nome, String numTel) {
		this.nome = nome;
		this.numTel = numTel;
	}
	
	/**
	 * Este método devolve o número de telemóvel do migrante.
	 * 
	 * @return o número de telemóvel do migrante.
	 */
	public String getNumTel() {
		return this.numTel;
	}
}
