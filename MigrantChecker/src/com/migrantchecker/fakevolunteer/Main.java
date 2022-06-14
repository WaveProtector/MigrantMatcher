package com.migrantchecker.fakevolunteer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.migrantchecker.MigrantChecker;
import com.migrantchecker.controllers.RegistaAjudaAlojHandler;
import com.migrantchecker.controllers.RegistaAjudaItemHandler;
import com.migrantchecker.dominio.AjudaAloj;
import com.migrantchecker.dominio.AjudaItem;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.Voluntario;

public class Main {
	/*Código que simula a interação de um voluntário */
	public static void main(String[] args) {
		Voluntario v = new Voluntario("919191919");
		String tipoAjuda[] = {"Aloj", "Item"};
		Random rd = new Random();
		List<Regiao> disponiveis = new ArrayList<>();
		String codigoSMS;
		
		String ajudaEscolhida = tipoAjuda[rd.nextInt(2)];
		if(ajudaEscolhida.equals(tipoAjuda[0])) {
			AjudaAloj a = new AjudaAloj(tipoAjuda[0], v);
			RegistaAjudaAlojHandler handler = new MigrantChecker().getRegistaAjudaAlojHandler(v.numTel);
			disponiveis = handler.indicarNumPessoas(rd.nextInt(10));
			codigoSMS = handler.associaAjudaRegiao(disponiveis.get(rd.nextInt(disponiveis.size())), a);
			handler.indicarCodigo(codigoSMS);
		} else {
			AjudaItem a = new AjudaItem(tipoAjuda[1], v);
			RegistaAjudaItemHandler handler = new MigrantChecker().getRegistaAjudaItemHandler(v.numTel);
			String descItem = "descricao";
			disponiveis = handler.indicaDescItem(descItem);
			codigoSMS = handler.associaAjudaRegiao(disponiveis.get(rd.nextInt(disponiveis.size())), a);
			handler.indicarCodigo(codigoSMS);
		}
	}
}
