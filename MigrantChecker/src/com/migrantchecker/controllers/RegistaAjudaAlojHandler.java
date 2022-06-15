package com.migrantchecker.controllers;

import java.util.List;
import java.util.Random;

import com.migrantchecker.dominio.AjudaAloj;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.catRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

public class RegistaAjudaAlojHandler extends AbstractRegistaAjudaHandler {

	private AjudaAloj a;
	
	public List<Regiao> indicarNumPessoas(int numPessoas) {
		this.a = new AjudaAloj(this.v);
		a.setNumPessoas(numPessoas);
		return catRegioes.getInstance().getListaRegioes();
	}

	@Override
	public String associaAjudaRegiao(Regiao r) {
		this.r = r;
		this.r.addAjuda(this.a);
		String numTel = v.getNumTel();
		PidgeonSMSSender sender1 = new PidgeonSMSSender();
		TelegramSMSSender sender2 = new TelegramSMSSender();
		sender2.setNumber(numTel);
		Random rd = new Random();
		int i = 0;
		while(i < 5) {
			code.concat(Integer.toString(rd.nextInt(9) + 1));
			i++;
		}
		sender1.send(numTel, "Este e o seu codigo unico de confirmacao de ajuda: " + code);
		sender2.setText("Este e o seu codigo unico de confirmacao de ajuda: " + code);
		sender2.send();
		return code;
	}
}
