package com.migrantchecker.dominio;

public abstract class Ajuda {
	
	protected String designacao;
	protected Voluntario v;
	
	public Ajuda(Voluntario v) {
		this.designacao = AjudaID.getINSTANCE().getAjudaID();
		this.v = v;
	}
	
	public String getNumTel() {
		return v.getNumTel();
	}
	
	public String getDesignacao() {
		return this.designacao;
	}
}
