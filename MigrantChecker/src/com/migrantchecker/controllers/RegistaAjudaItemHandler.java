package com.migrantchecker.controllers;

import java.util.List;
import java.util.Random;

import com.migrantchecker.dominio.AjudaItem;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.catRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

public class RegistaAjudaItemHandler extends AbstractRegistaAjudaHandler {

	private AjudaItem a;
	
	public List<Regiao> indicaDescItem(String descItem) {
		this.a = new AjudaItem("Ajuda de item", this.v);
		a.setDescItem(descItem);
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
