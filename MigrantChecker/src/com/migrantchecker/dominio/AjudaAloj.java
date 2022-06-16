package com.migrantchecker.dominio;

/**
 * Classe que implementa a ajuda, para o caso da ajuda fornecida ser a ajuda de alojamento.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class AjudaAloj extends Ajuda {
	
	/**
	 * Representa o número de pessoas que o alojamento pode ter(capacidade do alojamento).
	 */
	private int numPessoas;
	
	/**
	 * Este construtor constrói uma ajuda de alojamento ao receber um voluntário.
	 * 
	 * @param v, o voluntário que vai oferece a ajuda de alojamento.
	 */
	public AjudaAloj(Voluntario v) {
		super(v);
	}
	
	/**
	 * Este método recebe um número de pessoas que representam quantas pessoas conseguem viver no alojamento
	 * e associa-o à ajuda de alojamento.
	 * 
	 * @param numPessoas, número de pessoas que o alojamento pode ter.
	 */
	public void setNumPessoas(int numPessoas) {
		this.numPessoas = numPessoas;
	}
	
	/**
	 * Este método devolve o número de pessoas que o alojamento pode ter.
	 * 
	 * @return o número de pessoas que o alojamento pode ter.
	 */
	public int getNumPessoas() {
		return this.numPessoas;
	}
}
