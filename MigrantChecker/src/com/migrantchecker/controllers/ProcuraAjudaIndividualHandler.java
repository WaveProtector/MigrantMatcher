package com.migrantchecker.controllers;

import com.migrantchecker.dominio.RegistoIndividual;

public class ProcuraAjudaIndividualHandler extends AbstractProcuraAjudaHandler {

	private RegistoIndividual r;
	
	public ProcuraAjudaIndividualHandler(String tipoEscolhido) {
		r = new RegistoIndividual(tipoEscolhido);
	}
	
}
