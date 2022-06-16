package com.migrantchecker.dominio;

/**
 * Classe abstrata que representa uma ajuda a ser oferecida por um voluntário.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public abstract class Ajuda {
	
	/**
	 * Representa a designacao/"ID" da ajuda, sendo esta designacao única para cada ajuda criada.
	 */
	protected String designacao;
	
	/**
	 * Representa o voluntário que oferece a ajuda.
	 */
	protected Voluntario v;
	
	/**
	 * Este construtor cria uma ajuda ao receber um voluntário, associá-lo à mesma e ainda dá-lhe uma 
	 * designacao única.
	 * 
	 * @param v, o voluntário que vai oferecer a ajuda.
	 */
	public Ajuda(Voluntario v) {
		this.designacao = AjudaID.getINSTANCE().getAjudaID();
		this.v = v;
	}
	
	/**
	 * Este método devolve o número de telemóvel do voluntário que está associado à ajuda.
	 * 
	 * @return o número de telemóvel do voluntário associado à ajuda.
	 */
	public String getNumTel() {
		return v.getNumTel();
	}
	
	/**
	 * Este método devolve a designacao da ajuda.
	 * 
	 * @return a designacao da ajuda.
	 */
	public String getDesignacao() {
		return this.designacao;
	}
}
