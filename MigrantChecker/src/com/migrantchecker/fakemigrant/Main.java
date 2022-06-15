package com.migrantchecker.fakemigrant;

import java.util.List;
import java.util.Random;

import com.migrantchecker.MigrantChecker;
import com.migrantchecker.controllers.ProcuraAjudaFamiliarHandler;
import com.migrantchecker.controllers.ProcuraAjudaIndividualHandler;
import com.migrantchecker.dominio.Regiao;

public class Main {
	/*Código que simula a interação de um migrante*/
	public static void main(String[] args) {
		String tipoRegisto[] = {"Individual", "Familiar"};
		Random rd = new Random();
		String tipoEscolhido = tipoRegisto[rd.nextInt(2)];
		String nome = "Alfonse";
		String numTel = "191919191";
		List<Regiao> regioes;
		List<String> ajudasDaRegiao;
		
		if(tipoEscolhido.equals(tipoRegisto[0])) {
			ProcuraAjudaIndividualHandler handler = new MigrantChecker().getProcuraAjudaIndividualHandler();
			handler.indicarInfo(nome, numTel);
			regioes = handler.pedirListaRegioes();
			Regiao regiaoEscolhida = regioes.get(rd.nextInt(regioes.size()) + 1);
			ajudasDaRegiao = handler.indicarRegiao(regiaoEscolhida);
			int numAjudasEscolhidas = rd.nextInt(5) + 1;
			int i = 0;
			while(i < numAjudasEscolhidas) {
				String ajudaEscolhida = ajudasDaRegiao.get(rd.nextInt(ajudasDaRegiao.size() + 1));
				handler.escolherAjuda(ajudaEscolhida);
				i++;
			}
			handler.confirmarAjuda();
		} else {
			ProcuraAjudaFamiliarHandler handler = new MigrantChecker().getProcuraAjudaFamiliarHandler();
			int familiares = rd.nextInt(10) + 1;
			handler.indicarNumPessoasFamiliar(familiares);
			handler.indicarInfo(nome, numTel);
			int i = 0;
			while(i < familiares) {
				handler.indicarFamiliar(nome);
				i++;
			}
			regioes = handler.pedirListaRegioes();
			Regiao regiaoEscolhida = regioes.get(rd.nextInt(regioes.size()) + 1);
			ajudasDaRegiao = handler.indicarRegiao(regiaoEscolhida);
			int numAjudasEscolhidas = rd.nextInt(5) + 1;
			i = 0;
			while(i < numAjudasEscolhidas) {
				String ajudaEscolhida = ajudasDaRegiao.get(rd.nextInt(ajudasDaRegiao.size() + 1));
				handler.escolherAjuda(ajudaEscolhida);
				i++;
			}
			handler.confirmarAjuda();
		}
	}
}
