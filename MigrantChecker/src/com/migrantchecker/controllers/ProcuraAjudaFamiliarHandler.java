package com.migrantchecker.controllers;

import java.util.List;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.AjudaAloj;
import com.migrantchecker.dominio.Migrante;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.RegistoFamiliar;
import com.migrantchecker.exceptions.AjudaNaoExisteException;
import com.migrantchecker.exceptions.FamiliaMaiorQueAlojException;
import com.migrantchecker.exceptions.NumFamiliaresIgualMenorZeroException;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;
import com.migrantchecker.dominio.CatRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

public class ProcuraAjudaFamiliarHandler extends AbstractProcuraAjudaHandler {

	private RegistoFamiliar r;
	
	@Override
	public void indicarInfo(String nome, String numTel) {
		Migrante m = new Migrante(nome, numTel);
		this.r = new RegistoFamiliar("Registo familiar", m);
	}
	
	public void indicarNumPessoasFamiliar(int familiares) throws NumFamiliaresIgualMenorZeroException {
		if(familiares <= 0) {
			throw new NumFamiliaresIgualMenorZeroException();
		} else {
			this.r.setNumPessoasFamilia(familiares);
		}
	}
	
	public void indicarFamiliar(String nome) {
		this.r.addFamiliar(nome);
	}

	@Override
	public List<String> indicarRegiao(Regiao regiaoEscolhida) throws RegiaoNotInCatRegioesException {
		this.laRegiaoEscolhida = CatRegioes.getInstance().getRegiao(regiaoEscolhida).getListaAjudas();
		List<String> la = CatRegioes.getInstance().getRegiao(regiaoEscolhida).getListaAjudasDes();
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
	public void escolherAjuda(String ajudaEscolhida) throws FamiliaMaiorQueAlojException, 
	AjudaNaoExisteException {
		Ajuda a = null;
		boolean foundAjuda = false;
		for(int i = 0; i < laRegiaoEscolhida.size() && !foundAjuda; i++) {
			if(laRegiaoEscolhida.get(i).getDesignacao().equals(ajudaEscolhida)) {
				a = laRegiaoEscolhida.get(i);
				foundAjuda = true;
			}
		}
		if(!foundAjuda) {
			throw new AjudaNaoExisteException();
		} else if(a instanceof AjudaAloj) {
				AjudaAloj temp = (AjudaAloj) a;
				if(temp.getNumPessoas() > 0 && temp.getNumPessoas() < r.numFamiliares() + 1) {
					throw new FamiliaMaiorQueAlojException();
				} else
					laEscolhidas.add(a);
		} else {
			laEscolhidas.add(a);
		}
		
	}

	@Override
	public void confirmarAjuda() {
		r.registaAjudasEscolhidas(laEscolhidas);
		r.registaAjudasEscolhidas(laEscolhidas);
		for(int i = 0; i < laEscolhidas.size(); i++) {
			CatRegioes.getInstance().removeAjudaRegiao(laEscolhidas.get(i));
		}
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
