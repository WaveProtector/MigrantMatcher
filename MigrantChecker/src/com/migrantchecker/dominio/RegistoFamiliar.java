package com.migrantchecker.dominio;

/**
 * Classe implementa o registo, caso o tipo de registo seja um registo familiar.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class RegistoFamiliar extends Registo {

	/**
	 * Representa a família do migrante/cabeça de casal.
	 */
	private String[] familia;
	
	/**
	 * Este construtor recebe o tipo de registo escolhido, o migrante que faz o registo e constrói um registo
	 * familiar associando estes parâmetros.
	 * 
	 * @param tipoEscolhido, o tipo de registo escolhido.
	 * @param m, o migrante a efetuar o registo.
	 */
	public RegistoFamiliar(String tipoEscolhido, Migrante m) {
		super(tipoEscolhido, m);
	}
	
	/**
	 * Este método recebe o número de familiares do migrante e associa esse número ao tamanho do array que
	 * representa os familiares.
	 * 
	 * @param numPessoas, o número de familiares do migrante.
	 */
	public void setNumPessoasFamilia(int numPessoas) {
		this.familia = new String[numPessoas];
	}
	
	/**
	 * Este método recebe um nome de um familiar do migrante e coloca-o no array que representa os familiares.
	 * 
	 * @param nome, o nome de um familiar do migrante.
	 */
	public void addFamiliar(String nome) {
		boolean registado = false;
		for(int i = 0; i < familia.length && !registado; i++) {
			if(familia[i] == null) {
				familia[i] = nome;
				registado = true;
			}
		}
	}
	
	/**
	 * Este método devolve o número de familiares que o migrante indicou no registo familiar.
	 * 
	 * @return o número de familiares do migrante.
	 */
	public int numFamiliares() {
		return familia.length;
	}
}
