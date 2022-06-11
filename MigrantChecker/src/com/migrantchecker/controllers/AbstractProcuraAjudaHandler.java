package com.migrantchecker.controllers;

import java.util.List;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Migrante;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.Registo;
import com.migrantchecker.dominio.catRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;

public abstract class AbstractProcuraAjudaHandler {

	private Migrante m;
	private List<Ajuda> laEscolhidas;

	public void indicarInfo(String nome, String numTel) {
		this.m = new Migrante(nome, numTel);
	}

	public List<Regiao> pedirListaRegioes() {
		return catRegioes.getListaRegioes();
	}

	public List<Ajuda> indicarRegiao(Regiao regiaoEscolhida) {
		List<Ajuda> la = catRegioes.getRegiao(regiaoEscolhida).getListaAjudas();
		if(la.isEmpty()) {
			PidgeonSMSSender sender = new PidgeonSMSSender();
			sender.send(m.numTel, "Nao existe nenhuma ajuda na regiao indicada. Pretende ser notificado"
					+ " quando existir ajuda nesta regiao?");
		}
		return la;
	}

	public void escolherAjuda(Ajuda ajudaEscolhida) {
		laEscolhidas.add(ajudaEscolhida);
	}

	public void confirmarAjuda() {
		m.registaAjudasEscolhidas(laEscolhidas);
		//TODO enviar sms aos voluntários que ofereceram as respetivas ajudas
		//Talvez para fazer isso temos de acessar um catálogo de voluntários
		
	}

}
