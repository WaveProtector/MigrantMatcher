package com.migrantchecker;

import com.migrantchecker.controllers.RegistaAjudaHandler;

public class MigrantChecker {
	
	public RegistaAjudaHandler getRegistaAjudaHandler() {
		return new RegistaAjudaHandler();
	}
	
	public ProcuraAjudaHandler getProcuraAjudaHandler() {
		return new ProcuraAjudaHandler();
	}

}
