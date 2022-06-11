package com.migrantchecker.controllers;

import java.util.Random;

import com.migrantchecker.dominio.AjudaItem;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;

public class RegistaAjudaItemHandler extends AbstractRegistaAjudaHandler {

	private AjudaItem a;
	
	public RegistaAjudaItemHandler(String numTel) {
		super(numTel);
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
}
