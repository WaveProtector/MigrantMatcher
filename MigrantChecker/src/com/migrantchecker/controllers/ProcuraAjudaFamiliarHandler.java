package com.migrantchecker.controllers;

import java.util.List;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Migrante;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.RegistoFamiliar;
import com.migrantchecker.dominio.catRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

public class ProcuraAjudaFamiliarHandler extends AbstractProcuraAjudaHandler {

	private RegistoFamiliar r;
	
	@Override
	public void indicarInfo(String nome, String numTel) {
		Migrante m = new Migrante(nome, numTel);
		this.r = new RegistoFamiliar("Registo familiar", m);
	}
	
	public void indicarNumPessoasFamiliar(int familiares) {
		r.setNumPessoasFamilia(familiares);
	}
	
	public void indicarFamiliar(String nome) {
		r.addFamiliar(nome);
	}

	@Override
	public List<String> indicarRegiao(Regiao regiaoEscolhida) {
		this.laRegiaoEscolhida = catRegioes.getInstance().getRegiao(regiaoEscolhida).getListaAjudas();
		List<String> la = catRegioes.getInstance().getRegiao(regiaoEscolhida).getListaAjudasDes();
		if(la.isEmpty()) {
			String numTel = r.getNumTel();
			PidgeonSMSSender sender1 = new PidgeonSMSSender();
			TelegramSMSSender sender2 = new TelegramSMSSender();
			sender2.setNumber(numTel);
			sender2.setText("Nao existe nenhuma ajuda na regiao indicada. Pretende ser notificado"
					+ " quando existir ajuda nesta regiao?");
			sender1.send(numTel, "Nao existe nenhuma ajuda na regiao indicada. Pretende ser notificado"
					+ " quando existir ajuda nesta regiao?");
			sender2.send();
		}
		return la;
	}

	@Override
	public void confirmarAjuda() {
		r.registaAjudasEscolhidas(laEscolhidas);
		PidgeonSMSSender sender1 = new PidgeonSMSSender();
		TelegramSMSSender sender2 = new TelegramSMSSender();
		String message = "A sua ajuda foi escolhida!";
		sender2.setText(message);
		for(Ajuda a : laEscolhidas) {
			sender2.setNumber(a.getNumTel());
			sender1.send(a.getNumTel(), message);
			sender2.send();
		}
	}
}
