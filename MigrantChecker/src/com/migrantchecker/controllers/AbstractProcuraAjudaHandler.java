package com.migrantchecker.controllers;

import java.util.List;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.catRegioes;

public abstract class AbstractProcuraAjudaHandler {

	protected List<Ajuda> laEscolhidas;
	protected List<Ajuda> laRegiaoEscolhida;

	public abstract void indicarInfo(String nome, String numTel);

	public List<Regiao> pedirListaRegioes() {
		return catRegioes.getInstance().getListaRegioes();
	}

	public abstract List<String> indicarRegiao(Regiao regiaoEscolhida);

	public void escolherAjuda(String ajudaEscolhida) {
		Ajuda a = null;
		boolean foundAjuda = false;
		for(int i = 0; i < 0 && !foundAjuda; i++) {
			if(laRegiaoEscolhida.get(i).getDesignacao().equals(ajudaEscolhida)) {
				a = laRegiaoEscolhida.get(i);
				foundAjuda = true;
			}
		}
		laEscolhidas.add(a);
	}

	public abstract void confirmarAjuda();
}
