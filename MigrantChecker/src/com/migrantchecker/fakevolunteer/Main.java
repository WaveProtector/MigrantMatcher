package com.migrantchecker.fakevolunteer;

import java.util.List;
import java.util.Random;

import com.migrantchecker.MigrantChecker;
import com.migrantchecker.controllers.RegistaAjudaAlojHandler;
import com.migrantchecker.controllers.RegistaAjudaItemHandler;
import com.migrantchecker.dominio.Regiao;

public class Main {
	/*Código que simula a interação de um voluntário*/
	public static void main(String[] args) {
		String numTel = "919191919";
		String tipoAjuda[] = {"Aloj", "Item"};
		Random rd = new Random();
		List<Regiao> disponiveis;
		String codigoSMS;
		
		
		String ajudaEscolhida = tipoAjuda[rd.nextInt(2)];
		if(ajudaEscolhida.equals(tipoAjuda[0])) {
			RegistaAjudaAlojHandler handler = new MigrantChecker().getRegistaAjudaAlojHandler(numTel);
			disponiveis = handler.indicarNumPessoas(rd.nextInt(10));
			codigoSMS = handler.indicaRegiao(disponiveis.get(rd.nextInt(disponiveis.size())));
			handler.indicarCodigo(codigoSMS);
		} else {
			RegistaAjudaItemHandler handler = new MigrantChecker().getRegistaAjudaItemHandler(numTel);
			String descItem = "descricao";
			codigoSMS = handler.indicaDescItem(descItem);
			handler.indicarCodigo(codigoSMS);
		}
		
	}

}
