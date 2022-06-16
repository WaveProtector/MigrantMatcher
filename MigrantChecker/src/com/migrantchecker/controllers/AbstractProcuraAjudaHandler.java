package com.migrantchecker.controllers;

import java.util.ArrayList;
import java.util.List;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.exceptions.AjudaNaoExisteException;
import com.migrantchecker.exceptions.FamiliaMaiorQueAlojException;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;
import com.migrantchecker.dominio.CatRegioes;

/**
 * Classe abstrata que representa um handler para migrantes procurarem
 * ajudas no sistema Migrant Matcher.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public abstract class AbstractProcuraAjudaHandler {

	/**
	 * Representa as ajudas escolhias pelo migrante.
	 */
	protected List<Ajuda> laEscolhidas = new ArrayList<>();
	
	/**
	 * Representa a lista de ajudas da regiao escolhida pelo migrante. 
	 */
	protected List<Ajuda> laRegiaoEscolhida = new ArrayList<>();

	/**
	 * Este método recebe o nome e número de telemóvel do migrante a ser registado e associa-o.
	 * 
	 * @param nome, nome do migrante.
	 * @param numTel, número de telemóvel do migrante.
	 */
	public abstract void indicarInfo(String nome, String numTel);

	/**
	 * Este método devolve a lista de regiões predifinidas no catálogo de regiões(catRegioes).
	 * 
	 * @return a lista de regiões lista de regiões predifinidas no catRegioes.
	 */
	public List<Regiao> pedirListaRegioes() {
		return CatRegioes.getInstance().getListaRegioes();
	}

	/**
	 * Este método recebe a região escolhida pelo migrante para receber ajudas e devolve umas lista com as 
	 * designações das ajudas dessa região. Caso não haja ajudas na região escolhida, o migrante recebe um
	 * sms a avisá-lo da ausência de ajudas e pergunta-lhe se pretende ser notificado quando houver ajudas
	 * na região escolhida.
	 * 
	 * @param regiaoEscolhida, a região escolhida pelo migrante.
	 * @return lista de designações das ajudas da região escolhida.
	 * @throws RegiaoNotInCatRegioesException caso a região indicada não esteja no catálogo de regiões.
	 */
	public abstract List<String> indicarRegiao(Regiao regiaoEscolhida) throws RegiaoNotInCatRegioesException;

	/**
	 * Este método recebe a designacao da ajuda escolhida pelo migrante e regista-a numa lista de ajudas 
	 * associadas ao mesmo.
	 * 
	 * @param ajudaEscolhida, a ajuda escolhida pelo migrante.
	 * @throws FamiliaMaiorQueAlojException caso o número de familiares do migrante seja maior que a 
	 * capacidade do alojamento, se requisitado e se o migrante tiver um registo familiar.
	 * @throws AjudaNaoExisteException caso a ajuda escolhida não existir na região.
	 */
	public abstract void escolherAjuda(String ajudaEscolhida) throws FamiliaMaiorQueAlojException, 
	AjudaNaoExisteException;

	/**
	 * Este método associa a lista de ajudas do migrante ao seu registo, confirmando assim o mesmo e enviando
	 * mensagens sms aos voluntários a informar que as suas respetivas ajudas foram escolhidas.
	 */
	public abstract void confirmarAjuda();
}
