package com.migrantchecker.controllers;

import java.util.Random;

import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.Voluntario;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;

public abstract class AbstractRegistaAjudaHandler {
	
	protected Voluntario v;
	private Regiao r;
	protected String code;
	
	public AbstractRegistaAjudaHandler(String numTel) {
		this.v = new Voluntario(numTel);
	}

//	public void indicarTipoAjuda(String ajudaEscolhida) {
//		if(ajudaEscolhida.equalsIgnoreCase("alojamento")) {
//			this.a = new AjudaAloj("Oferta de alojamento");
//		} else if(ajudaEscolhida.equalsIgnoreCase("item")) {
//			this.a = new AjudaItem("Oferta de item");
//		}
//	}

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

	public void indicarCodigo(String codigoSMS) {
		PidgeonSMSSender sender = new PidgeonSMSSender();
		if (codigoSMS.equals(code)) {
			sender.send(v.numTel, "Codigo correto. Ajuda registada com sucesso.");
		} else {
			sender.send(v.numTel, "Codigo incorreto."); //Fazer alguma coisa com código incorreto?
		}
		
	}
}
