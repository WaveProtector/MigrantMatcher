package com.migrantchecker.dominio;

/**
 * Classe que implementa a ajuda, para o caso da ajuda fornecida ser a ajuda de item.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class AjudaItem extends Ajuda {
	
	/**
	 * Representa a descricao do item.
	 */
	private String descItem;
	
	/**
	 * Este construtor constrói uma ajuda de item ao receber um voluntário.
	 * 
	 * @param v, o voluntário que oferece a ajuda de item.
	 */
	public AjudaItem(Voluntario v) {
		super(v);
	}
	
	/**
	 * Este método recebe a descrição do item oferecida pelo voluntário e associa-a à ajuda de item.
	 * 
	 * @param descItem, a descrição do item oferecida pelo voluntário.
	 */
	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}
}
