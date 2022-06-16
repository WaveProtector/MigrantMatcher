package com.migrantchecker.controllers;

import java.util.List;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Migrante;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.RegistoIndividual;
import com.migrantchecker.exceptions.AjudaNaoExisteException;
import com.migrantchecker.exceptions.FamiliaMaiorQueAlojException;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;
import com.migrantchecker.dominio.CatRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

/**
 * Classe que implementa o Procura Ajuda Handler, para o caso do migrante fazer um registo individual.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class ProcuraAjudaIndividualHandler extends AbstractProcuraAjudaHandler {

	/**
	 * Representa o registo individual associado ao migrante.
	 */
	private RegistoIndividual r;

	@Override
	public void indicarInfo(String nome, String numTel) {
		Migrante m = new Migrante(nome, numTel);
		this.r = new RegistoIndividual("Registo Individual", m);
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
		}
		laEscolhidas.add(a);
	}
	
	@Override
	public void confirmarAjuda() {
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
