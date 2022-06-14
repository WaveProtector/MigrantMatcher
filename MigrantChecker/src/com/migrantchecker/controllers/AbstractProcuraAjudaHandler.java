package com.migrantchecker.controllers;

import java.util.List;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Migrante;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.catRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

public abstract class AbstractProcuraAjudaHandler {

	private Migrante m;
	private List<Ajuda> laEscolhidas;

	public void indicarInfo(String nome, String numTel) {
		this.m = new Migrante(nome, numTel);
	}

	public List<Regiao> pedirListaRegioes() {
		return catRegioes.getInstance().getListaRegioes();
	}

	public List<Ajuda> indicarRegiao(Regiao regiaoEscolhida) {
		List<Ajuda> la = catRegioes.getInstance().getRegiao(regiaoEscolhida).getListaAjudas();
		if(la.isEmpty()) {
			PidgeonSMSSender sender1 = new PidgeonSMSSender();
			TelegramSMSSender sender2 = new TelegramSMSSender();
			sender2.setNumber(m.numTel);
			sender2.setText("Nao existe nenhuma ajuda na regiao indicada. Pretende ser notificado"
					+ " quando existir ajuda nesta regiao?");
			sender1.send(m.numTel, "Nao existe nenhuma ajuda na regiao indicada. Pretende ser notificado"
					+ " quando existir ajuda nesta regiao?");
			sender2.send();
		}
		return la;
	}

	public void escolherAjuda(Ajuda ajudaEscolhida) {
		laEscolhidas.add(ajudaEscolhida);
	}

	public void confirmarAjuda() {
		m.registaAjudasEscolhidas(laEscolhidas);
		PidgeonSMSSender sender1 = new PidgeonSMSSender();
		TelegramSMSSender sender2 = new TelegramSMSSender();
		String message = "A sua ajuda foi escolhida!";
		sender2.setText(message);
		for(Ajuda a : laEscolhidas) {
			sender2.setNumber(a.v.numTel);
			sender1.send(a.v.numTel, message);
			sender2.send();
		}
	}
}
