package com.migrantchecker.fakemigrant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.migrantchecker.MigrantMatcher;
import com.migrantchecker.controllers.ProcuraAjudaFamiliarHandler;
import com.migrantchecker.controllers.ProcuraAjudaIndividualHandler;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.exceptions.AjudaNaoExisteException;
import com.migrantchecker.exceptions.FamiliaMaiorQueAlojException;
import com.migrantchecker.exceptions.NumFamiliaresIgualMenorZeroException;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;

/**
 * Classe que simula a interação de um migrante no sistema Migrant Matcher.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public class Main {
	
	public static void main(String[] args) throws FamiliaMaiorQueAlojException, AjudaNaoExisteException, 
	NumFamiliaresIgualMenorZeroException, RegiaoNotInCatRegioesException {
		String tipoRegisto[] = {"Individual", "Familiar"};
		Random rd = new Random();
		String tipoEscolhido = tipoRegisto[rd.nextInt(2)];
		String nome = "Alfonse";
		String numTel = "191919191";
		List<Regiao> regioes = new ArrayList<>();
		List<String> ajudasDaRegiao = new ArrayList<>();
		
		if(tipoEscolhido.equals(tipoRegisto[0])) {
			ProcuraAjudaIndividualHandler handler = new MigrantMatcher().getProcuraAjudaIndividualHandler();
			handler.indicarInfo(nome, numTel);
			regioes = handler.pedirListaRegioes();
			Regiao regiaoEscolhida = regioes.get(rd.nextInt(regioes.size()));
			ajudasDaRegiao = handler.indicarRegiao(regiaoEscolhida);
			if(!ajudasDaRegiao.isEmpty()) {
				int numAjudasEscolhidas = rd.nextInt(5) + 1;
				int i = 0;
				while(i < numAjudasEscolhidas && i < ajudasDaRegiao.size()) {
					String ajudaEscolhida = ajudasDaRegiao.get(rd.nextInt(ajudasDaRegiao.size()));
					handler.escolherAjuda(ajudaEscolhida);
					i++;
				}
				handler.confirmarAjuda();
			}
		} else {
			ProcuraAjudaFamiliarHandler handler = new MigrantMatcher().getProcuraAjudaFamiliarHandler();
			int familiares = rd.nextInt(10) + 1;
			handler.indicarInfo(nome, numTel);
			handler.indicarNumPessoasFamiliar(familiares);
			int i = 0;
			while(i < familiares) {
				handler.indicarFamiliar(nome);
				i++;
			}
			regioes = handler.pedirListaRegioes();
			Regiao regiaoEscolhida = regioes.get(rd.nextInt(regioes.size()));
			ajudasDaRegiao = handler.indicarRegiao(regiaoEscolhida);
			if(!ajudasDaRegiao.isEmpty()) {
				int numAjudasEscolhidas = rd.nextInt(5) + 1;
				i = 0;
				while(i < numAjudasEscolhidas) {
					String ajudaEscolhida = ajudasDaRegiao.get(rd.nextInt(ajudasDaRegiao.size()));
					handler.escolherAjuda(ajudaEscolhida);
					i++;
				}
				handler.confirmarAjuda();
			}
		}
	}
}
