package com.migrantchecker.dominio;

public class AjudaItem extends Ajuda {
	
	private String descItem;
	
	public AjudaItem(String designacao, Voluntario v) {
		super(designacao, v);
	}
	
	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}
}
