package com.migrantchecker.controllers;

import java.util.List;
import java.util.Random;

import com.migrantchecker.dominio.AjudaAloj;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.exceptions.NumPessoasAlojIgualMenorZeroException;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;
import com.migrantchecker.dominio.CatRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

public class RegistaAjudaAlojHandler extends AbstractRegistaAjudaHandler {

	private AjudaAloj a;
	
	public List<Regiao> indicarNumPessoas(int numPessoas) throws NumPessoasAlojIgualMenorZeroException {
		this.a = new AjudaAloj(this.v);
		if(numPessoas <= 0) {
			throw new NumPessoasAlojIgualMenorZeroException();
		} else {
			a.setNumPessoas(numPessoas);
		}
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
