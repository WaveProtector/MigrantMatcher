package com.migrantchecker.controllers;

import java.util.List;
import java.util.Random;

import com.migrantchecker.dominio.AjudaItem;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;
import com.migrantchecker.dominio.CatRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

/**
 * Classe que implementa o Regista Ajuda Handler, para o caso do voluntário oferecer um item.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class RegistaAjudaItemHandler extends AbstractRegistaAjudaHandler {

	/**
	 * Representa a ajuda do item oferecido pelo voluntário.
	 */
	private AjudaItem a;
	
	/**
	 * Este método recebe a descrição do item, associa-a à ajuda oferecida pelo voluntário e devolve
	 * uma lista de regiões predefinidas no catálogo de regiões(catRegioes).
	 * 
	 * @param descItem, a descrição do item a ser oferecido pelo voluntário.
	 * @return uma lista de regiões predefinidas no catálogo de regiões(catRegioes).
	 */
	public List<Regiao> indicaDescItem(String descItem) {
		this.a = new AjudaItem(this.v);
		a.setDescItem(descItem);
		return CatRegioes.getInstance().getListaRegioes();
	}

	@Override
	public String associaAjudaRegiao(Regiao r) throws RegiaoNotInCatRegioesException {
		if(!CatRegioes.getInstance().getListaRegioes().contains(r)) {
			throw new RegiaoNotInCatRegioesException();
		} else {
			this.r = r;
			this.r.addAjuda(this.a);
			String numTel = v.getNumTel();
			PidgeonSMSSender sender1 = new PidgeonSMSSender();
			TelegramSMSSender sender2 = new TelegramSMSSender();
			sender2.setNumber(numTel);
			Random rd = new Random();
			int i = 0;
			while(i < 5) {
				code = code.concat(Integer.toString(rd.nextInt(9) + 1));
				i++;
			}
			sender1.send(numTel, "Este e o seu codigo unico de confirmacao de ajuda: " + code);
			sender2.setText("Este e o seu codigo unico de confirmacao de ajuda: " + code);
			sender2.send();
		}
		return code;
	}
}
