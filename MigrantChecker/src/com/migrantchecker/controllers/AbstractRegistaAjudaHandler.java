package com.migrantchecker.controllers;

import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.dominio.Voluntario;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;
import com.migrantchecker.dominio.CatRegioes;
import com.pidgeonsmssender.sdk.PidgeonSMSSender;
import com.telegramsms.TelegramSMSSender;

/**
 * Classe abstrata que representa um handler para voluntários registarem
 * as suas ajudas no sistema Migrant Matcher.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
public abstract class AbstractRegistaAjudaHandler {
	
	/**
	 * Representa o voluntário a utilizar o sistema.
	 */
	protected Voluntario v;
	/**
	 * Representa o código de confirmação que é enviado ao voluntário.
	 */
	protected String code = "";
	/**
	 * Representa a região em que o voluntário oferece a ajuda.
	 */
	protected Regiao r;
	
	/**
	 * Este método recebe um número de telefone e associa-o ao voluntário.
	 * 
	 * @param numTel o número de telefone do voluntário.
	 */
	public void iniciarAplicacao(String numTel) {
		this.v = new Voluntario(numTel);
	}

	/**
	 * Este método recebe a região escolhida pelo voluntário onde este vai efetuar a ajuda e devolve o código
	 * de confirmação de registo assim como também o envia por sms.
	 * 
	 * @param r, região escolhida pelo voluntário.
	 * @return código de confirmação do registo.
	 * @throws RegiaoNotInCatRegioesException caso a região indicada não esteja no catálogo de regiões.
	 */
	public abstract String associaAjudaRegiao(Regiao r) throws RegiaoNotInCatRegioesException;

	/**
	 * Este método recebe um código sms enviado pelo voluntário para afirmar se é o mesmo código enviado de
	 * confirmação: 
	 *  	- Se for o mesmo código, a ajuda é associada à região no catálogo de regiões e são enviados
	 *		  sms a afirmar que o código inserido foi o correto.
	 *		- Caso contrário, apenas é enviado um sms a afirmar que o código inserido é incorreto.
	 * 
	 * @param codigoSMS, o código sms enviado pelo voluntário.
	 */
	public void indicarCodigo(String codigoSMS) {
		PidgeonSMSSender sender1 = new PidgeonSMSSender();
		TelegramSMSSender sender2 = new TelegramSMSSender();
		String numTel = v.getNumTel();
		sender2.setNumber(numTel);
		if (codigoSMS.equals(code)) {
			CatRegioes.getInstance().confirmAjudaToRegiao(r);
			sender1.send(numTel, "Codigo correto. Ajuda registada com sucesso.");
			sender2.setText("Codigo correto. Ajuda registada com sucesso.");
			sender2.send();
		} else {
			sender1.send(numTel, "Codigo incorreto. Ajuda não registada.");
			sender2.setText("Codigo incorreto. Ajuda não registada.");
			sender2.send();
		}
	}
}
