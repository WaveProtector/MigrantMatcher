package com.migrantchecker.controllers;

import java.util.Random;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.Voluntario;
import com.migrantchecker.dominio.catRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

public abstract class AbstractRegistaAjudaHandler {
	
	protected Voluntario v;
	protected String code;
	protected Regiao r;
	
	public AbstractRegistaAjudaHandler(String numTel) {
		this.v = new Voluntario(numTel);
	}

	public String associaAjudaRegiao(Regiao r, Ajuda a) {
		this.r = r;
		this.r.addAjuda(a);
		PidgeonSMSSender sender1 = new PidgeonSMSSender();
		TelegramSMSSender sender2 = new TelegramSMSSender();
		sender2.setNumber(v.numTel);
		Random rd = new Random();
		int i = 0;
		while(i < 5) {
			code.concat(Integer.toString(rd.nextInt(9) + 1));
			i++;
		}
		sender1.send(v.numTel, "Este e o seu codigo unico de confirmacao de ajuda: " + code);
		sender2.setText("Este e o seu codigo unico de confirmacao de ajuda: " + code);
		sender2.send();
		return code;
	}

	public void indicarCodigo(String codigoSMS) {
		PidgeonSMSSender sender1 = new PidgeonSMSSender();
		TelegramSMSSender sender2 = new TelegramSMSSender();
		sender2.setNumber(v.numTel);
		if (codigoSMS.equals(code)) {
			catRegioes.getInstance().confirmAjudaToRegiao(r);
			sender1.send(v.numTel, "Codigo correto. Ajuda registada com sucesso.");
			sender2.setText("Codigo correto. Ajuda registada com sucesso.");
			sender2.send();
		} else {
			sender1.send(v.numTel, "Codigo incorreto. Ajuda não registada.");
			sender2.setText("Codigo incorreto. Ajuda não registada.");
			sender2.send();
		}
	}
}
