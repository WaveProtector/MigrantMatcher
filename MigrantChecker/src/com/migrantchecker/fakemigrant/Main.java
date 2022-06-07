package com.migrantchecker.fakemigrant;

import java.util.List;
import java.util.Random;

import com.migrantchecker.MigrantChecker;
import com.migrantchecker.ProcuraAjudaHandler;
import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Regiao;

public class Main {
	/*Código que simula a interação de um migrante*/
	public static void main(String[] args) {
		ProcuraAjudaHandler handler = new MigrantChecker().getProcuraAjudaHandler();
		
		String tipoRegisto[] = {"Individual", "Familiar"};
		Random rd = new Random();
		String tipoEscolhido = tipoRegisto[rd.nextInt(2)];
		String nome = "Alfonse";
		String numTel = "191919191";
		List<Regiao> regioes;
		List<Ajuda> ajudasDaRegiao;
				
		handler.iniciarRegisto(tipoEscolhido);
		if(tipoEscolhido.equals(tipoRegisto[0])) {
			handler.indicarInfo(nome, numTel);
		} else {
			int familiares = rd.nextInt(10) + 1;
			handler.indicarNumPessoasFamiliar(familiares);
			handler.indicarCabecaCasal(nome, numTel);
			int i = 0;
			while(i < familiares) {
				handler.indicarFamiliar(nome);
				i++;
			}
		}
		regioes = handler.pedirListaRegioes();
		Regiao regiaoEscolhida = regioes.get(rd.nextInt(regioes.size()) + 1);
		ajudasDaRegiao = handler.indicarRegiao(regiaoEscolhida);
		int numAjudasEscolhidas = rd.nextInt(5) + 1;
		int i = 0;
		while(i < numAjudasEscolhidas) {
			Ajuda ajudaEscolhida = ajudasDaRegiao.get(rd.nextInt(ajudasDaRegiao.size() + 1));
			handler.escolherAjuda(ajudaEscolhida);
		}
		handler.confirmarAjuda();
		
		
		

	}

}
