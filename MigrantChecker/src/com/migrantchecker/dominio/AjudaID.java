package com.migrantchecker.dominio;

/**
 * Classe adicional que representa os "IDs"/designações únicas das ajudas (feito com padrão singleton).
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class AjudaID {
	
	/**
	 * Representa o "ID global" das ajudas.
	 */
	private static AjudaID INSTANCE = new AjudaID();
	
	/**
	 * Representa o "ID"/designacao das ajudas.
	 */
	private String ID;
	
	/**
	 * Representa qual o próximo "ID" a ser feito.
	 */
	private int nextID;
	
	/**
	 * Este método devolve uma instância do "ID global" das ajudas.
	 * 
	 * @return uma instância do "ID global" das ajudas.
	 */
	public static AjudaID getINSTANCE() {
		return INSTANCE;
	}
	
	/**
	 * Este construtor constrói o "ID global" das ajudas.
	 */
	private AjudaID() {
		this.ID = Integer.toString(this.nextID);
	}
	
	/**
	 * Este método devolve o "ID" para ser associado a uma ajuda e só deve ser chamado para tal.
	 * 
	 * @return o "ID" para ser associado a uma ajuda.
	 */
	public String getAjudaID() {
		this.nextID++;
		this.ID = Integer.toString(this.nextID);
		return this.ID;
	}
}
