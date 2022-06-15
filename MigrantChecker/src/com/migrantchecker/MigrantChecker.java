package com.migrantchecker;

import com.migrantchecker.controllers.ProcuraAjudaFamiliarHandler;
import com.migrantchecker.controllers.ProcuraAjudaIndividualHandler;
import com.migrantchecker.controllers.RegistaAjudaAlojHandler;
import com.migrantchecker.controllers.RegistaAjudaItemHandler;

public class MigrantChecker {
	
	public RegistaAjudaAlojHandler getRegistaAjudaAlojHandler() {
		return new RegistaAjudaAlojHandler();
	}
	
	public RegistaAjudaItemHandler getRegistaAjudaItemHandler() {
		return new RegistaAjudaItemHandler();
	}
	
	public ProcuraAjudaIndividualHandler getProcuraAjudaIndividualHandler() {
		return new ProcuraAjudaIndividualHandler();
	}
	
	public ProcuraAjudaFamiliarHandler getProcuraAjudaFamiliarHandler() {
		return new ProcuraAjudaFamiliarHandler();
	}
}
