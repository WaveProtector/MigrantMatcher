package com.migrantchecker.dominio;

import java.util.ArrayList;
import java.util.List;

public class Migrante {

	public String nome;
	public String numTel;
	public List<Ajuda> laEscolhidas = new ArrayList<>();
	public Migrante(String nome, String numTel) {
		this.nome = nome;
		this.numTel = numTel;
	}

	public void registaAjudasEscolhidas(List<Ajuda> laEscolhidas) {
		this.laEscolhidas = laEscolhidas;
	}

}
