package com.migrantchecker.fakevolunteer;

import java.util.List;
import java.util.Random;

import com.migrantchecker.MigrantChecker;
import com.migrantchecker.controllers.RegistaAjudaHandler;
import com.migrantchecker.dominio.Regiao;

public class Main {
	/*Código que simula a interação de um voluntário*/
	public static void main(String[] args) {
		RegistaAjudaHandler handler = new MigrantChecker().getRegistaAjudaHandler();
		
		String numTel = "919191919";
		String tipoAjuda[] = {"Aloj", "Item"};
		Random rd = new Random();
		List<Regiao> disponiveis;
		String codigoSMS;
		
		handler.iniciarAplicacao(numTel); //talvez um atributo que tem de ser dado à classe de domínio de volunteer
		String ajudaEscolhida = tipoAjuda[rd.nextInt(2)];
		handler.indicarTipoAjuda(ajudaEscolhida); //talvez um enum? mas string deve funcionar
		if(ajudaEscolhida.equals(tipoAjuda[0])) {
			disponiveis = handler.indicarNumPessoas(rd.nextInt(10));
			codigoSMS = handler.indicaRegiao(disponiveis.get(rd.nextInt(disponiveis.size())));
		} else {
			String descItem = "descricao";
			codigoSMS = handler.indicaDescItem(descItem);
		}
		
		handler.indicarCodigo(codigoSMS);
	}

}
