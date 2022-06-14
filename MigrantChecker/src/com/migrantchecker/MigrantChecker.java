package com.migrantchecker;

import com.migrantchecker.controllers.ProcuraAjudaFamiliarHandler;
import com.migrantchecker.controllers.ProcuraAjudaIndividualHandler;
import com.migrantchecker.controllers.RegistaAjudaAlojHandler;
import com.migrantchecker.controllers.RegistaAjudaItemHandler;

public class MigrantChecker {
	
	public RegistaAjudaAlojHandler getRegistaAjudaAlojHandler(String numTel) {
		return new RegistaAjudaAlojHandler(numTel);
	}
	
	public RegistaAjudaItemHandler getRegistaAjudaItemHandler(String numTel) {
		return new RegistaAjudaItemHandler(numTel);
	}
	
	public ProcuraAjudaIndividualHandler getProcuraAjudaIndividualHandler(String tipoRegisto) {
		return new ProcuraAjudaIndividualHandler(tipoRegisto);
	}
	
	public ProcuraAjudaFamiliarHandler getProcuraAjudaFamiliarHandler(String tipoRegisto) {
		return new ProcuraAjudaFamiliarHandler(tipoRegisto);
	}
}
