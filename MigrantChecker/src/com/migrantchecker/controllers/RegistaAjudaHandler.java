package com.migrantchecker.controllers;

import java.util.List;
import java.util.Random;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.Voluntario;
import com.migrantchecker.dominio.catRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;

public class RegistaAjudaHandler {
	
	private Voluntario v;
	private Regiao r;
	private Ajuda a;
	private String code;
	
	public void iniciarAplicacao(String numTel) {
		this.v = new Voluntario(numTel);
	}

	public void indicarTipoAjuda(String ajudaEscolhida) {
		this.a = new Ajuda(ajudaEscolhida);
		
	}

	public List<Regiao> indicarNumPessoas(int numPessoas) {
		a.setNumPessoas(numPessoas);
		return catRegioes.getListaRegioes();
	}

	public String indicaRegiao(Regiao regiao) { //faz sentido devolver uma string?
		//r = catRegioes.getRegiao(regiao); esta opção não faz sentido 
		r = regiao;
		PidgeonSMSSender sender = new PidgeonSMSSender();
		Random rd = new Random();
		int i = 0;
		while(i < 5) {
			code.concat(Integer.toString(rd.nextInt(9) + 1));
			i++;
		}
		sender.send(v.numTel, "Este e o seu codigo unico de confirmacao de ajuda: " + code);
		return null;
	}

	public String indicaDescItem(String descItem) { //faz sentido devolver uma string?
		a.setDescItem(descItem);
		PidgeonSMSSender sender = new PidgeonSMSSender();
		Random rd = new Random();
		int i = 0;
		while(i < 5) {
			code.concat(Integer.toString(rd.nextInt(9) + 1));
			i++;
		}
		sender.send(v.numTel, "Este e o seu codigo unico de confirmacao de ajuda: " + code);
		return null;
	}

	public void indicarCodigo(String codigoSMS) {
		PidgeonSMSSender sender = new PidgeonSMSSender();
		if (codigoSMS.equals(code)) {
			sender.send(v.numTel, "Codigo correto. Ajuda registada com sucesso.");
		} else {
			sender.send(v.numTel, "Codigo incorreto."); //Fazer alguma coisa com código incorreto?
		}
		
	}
}
