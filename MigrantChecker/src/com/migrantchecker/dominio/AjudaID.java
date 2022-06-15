package com.migrantchecker.dominio;

public class AjudaID {
	
	private static AjudaID INSTANCE = new AjudaID();
	private String ID;
	private int nextID;
	
	public static AjudaID getINSTANCE() {
		return INSTANCE;
	}
	
	private AjudaID() {
		this.ID = Integer.toString(this.nextID);
	}
	
	public String getAjudaID() {
		this.nextID++;
		this.ID = Integer.toString(this.nextID);
		return this.ID;
	}
}
