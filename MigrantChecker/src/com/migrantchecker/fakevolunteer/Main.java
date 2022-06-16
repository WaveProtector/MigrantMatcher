package com.migrantchecker.fakevolunteer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.migrantchecker.MigrantChecker;
import com.migrantchecker.controllers.RegistaAjudaAlojHandler;
import com.migrantchecker.controllers.RegistaAjudaItemHandler;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.exceptions.NumPessoasAlojIgualMenorZeroException;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;

public class Main {
	/*Código que simula a interação de um voluntário */
	public static void main(String[] args) throws NumPessoasAlojIgualMenorZeroException, RegiaoNotInCatRegioesException {
		String numTel = "919191919";
		String tipoAjuda[] = {"Aloj", "Item"};
		Random rd = new Random();
		List<Regiao> disponiveis = new ArrayList<>();
		String codigoSMS;
		
		String ajudaEscolhida = tipoAjuda[rd.nextInt(2)];
		if(ajudaEscolhida.equals(tipoAjuda[0])) {
			RegistaAjudaAlojHandler handler = new MigrantChecker().getRegistaAjudaAlojHandler();
			handler.iniciarAplicacao(numTel);
			disponiveis = handler.indicarNumPessoas(rd.nextInt(10));
			codigoSMS = handler.associaAjudaRegiao(disponiveis.get(rd.nextInt(disponiveis.size())));
			handler.indicarCodigo(codigoSMS);
		} else {
			RegistaAjudaItemHandler handler = new MigrantChecker().getRegistaAjudaItemHandler();
			handler.iniciarAplicacao(numTel);
			String descItem = "descricao";
			disponiveis = handler.indicaDescItem(descItem);
			codigoSMS = handler.associaAjudaRegiao(disponiveis.get(rd.nextInt(disponiveis.size())));
			handler.indicarCodigo(codigoSMS);
		}
	}
}
