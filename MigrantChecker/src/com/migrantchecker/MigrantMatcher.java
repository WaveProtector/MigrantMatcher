package com.migrantchecker;

import com.migrantchecker.controllers.ProcuraAjudaFamiliarHandler;
import com.migrantchecker.controllers.ProcuraAjudaIndividualHandler;
import com.migrantchecker.controllers.RegistaAjudaAlojHandler;
import com.migrantchecker.controllers.RegistaAjudaItemHandler;

/**
 * Classe que representa o sistema Migrant Matcher.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class MigrantMatcher {
	
	/**
	 * Este método retorna o handler para o caso em que um voluntário regista uma ajuda de alojamento.
	 * 
	 * @return o handler para o caso em que um voluntário regista uma ajuda de alojamento.
	 */
	public RegistaAjudaAlojHandler getRegistaAjudaAlojHandler() {
		return new RegistaAjudaAlojHandler();
	}
	
	/**
	 * Este método retorna o handler para o caso em que um voluntário regista uma ajuda de item.
	 * 
	 * @return o handler para o caso em que um voluntário regista uma ajuda de item.
	 */
	public RegistaAjudaItemHandler getRegistaAjudaItemHandler() {
		return new RegistaAjudaItemHandler();
	}
	
	/**
	 * Este método retorna o handler para o caso em que um migrante faz um registo individual.
	 * 
	 * @return o handler para o caso em que um migrante faz um registo individual.
	 */
	public ProcuraAjudaIndividualHandler getProcuraAjudaIndividualHandler() {
		return new ProcuraAjudaIndividualHandler();
	}
	
	/**
	 * Este método retorna o handler para o caso em que um migrante faz um registo familiar.
	 * 
	 * @return o handler para o caso em que um migrante faz um registo familiar.
	 */
	public ProcuraAjudaFamiliarHandler getProcuraAjudaFamiliarHandler() {
		return new ProcuraAjudaFamiliarHandler();
	}
}
