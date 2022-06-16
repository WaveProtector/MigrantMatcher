package com.migrantchecker.controllers;

import java.util.ArrayList;
import java.util.List;

import com.migrantchecker.dominio.Ajuda;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.exceptions.AjudaNaoExisteException;
import com.migrantchecker.exceptions.FamiliaMaiorQueAlojException;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;
import com.migrantchecker.dominio.CatRegioes;

public abstract class AbstractProcuraAjudaHandler {

	protected List<Ajuda> laEscolhidas = new ArrayList<>();
	protected List<Ajuda> laRegiaoEscolhida = new ArrayList<>();

	public abstract void indicarInfo(String nome, String numTel);

	public List<Regiao> pedirListaRegioes() {
		return CatRegioes.getInstance().getListaRegioes();
	}

	public abstract List<String> indicarRegiao(Regiao regiaoEscolhida) throws RegiaoNotInCatRegioesException;

	public abstract void escolherAjuda(String ajudaEscolhida) throws FamiliaMaiorQueAlojException, 
	AjudaNaoExisteException;

	public abstract void confirmarAjuda();
}
