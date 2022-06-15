package com.migrantchecker.controllers;

import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.Voluntario;
import com.migrantchecker.dominio.catRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

public abstract class AbstractRegistaAjudaHandler {
	
	protected Voluntario v;
	protected String code;
	protected Regiao r;
	
	public void iniciarAplicacao(String numTel) {
		this.v = new Voluntario(numTel);
	}

	public abstract String associaAjudaRegiao(Regiao r);

	public void indicarCodigo(String codigoSMS) {
		PidgeonSMSSender sender1 = new PidgeonSMSSender();
		TelegramSMSSender sender2 = new TelegramSMSSender();
		String numTel = v.getNumTel();
		sender2.setNumber(numTel);
		if (codigoSMS.equals(code)) {
			catRegioes.getInstance().confirmAjudaToRegiao(r);
			sender1.send(numTel, "Codigo correto. Ajuda registada com sucesso.");
			sender2.setText("Codigo correto. Ajuda registada com sucesso.");
			sender2.send();
		} else {
			sender1.send(numTel, "Codigo incorreto. Ajuda não registada.");
			sender2.setText("Codigo incorreto. Ajuda não registada.");
			sender2.send();
		}
	}
}
