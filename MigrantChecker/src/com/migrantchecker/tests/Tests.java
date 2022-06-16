package com.migrantchecker.tests;

import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.migrantchecker.MigrantMatcher;
import com.migrantchecker.controllers.ProcuraAjudaFamiliarHandler;
import com.migrantchecker.controllers.ProcuraAjudaIndividualHandler;
import com.migrantchecker.controllers.RegistaAjudaAlojHandler;
import com.migrantchecker.controllers.RegistaAjudaItemHandler;
import com.migrantchecker.dominio.Regiao;
import com.migrantchecker.exceptions.AjudaNaoExisteException;
import com.migrantchecker.exceptions.FamiliaMaiorQueAlojException;
import com.migrantchecker.exceptions.NumFamiliaresIgualMenorZeroException;
import com.migrantchecker.exceptions.NumPessoasAlojIgualMenorZeroException;
import com.migrantchecker.exceptions.RegiaoNotInCatRegioesException;

/**
 * Classe que corre alguns testes para o sistema do Migrant Matcher.
 * 
 * @author Ricardo Mateus, fc56366
 *
 */
class Tests {
	
	@Test
	/**
	 * Testa um "happy path" em que o voluntário V1 e o voluntário V2 registam as suas ajudas na mesma
	 * região e o voluntário V3 regista a sua ajuda noutra região. O migrante M1 vai escolher a ajuda do V3
	 * e o migrante M2 vai escolher as restantes ajudas.
	 */
	void testMultiplasAjudasMultiplosRegistos() throws NumPessoasAlojIgualMenorZeroException, 
	RegiaoNotInCatRegioesException, FamiliaMaiorQueAlojException, AjudaNaoExisteException, 
	NumFamiliaresIgualMenorZeroException {
		String numTelV1 = "910000000";
		List<Regiao> disponiveis1 = new ArrayList<>();
		String codigoSMS1;
		
		String nomeM1 = "Alfonse";
		String numTelM1 = "190000000";
		List<Regiao> regioes1 = new ArrayList<>();
		List<String> ajudasDaRegiao1 = new ArrayList<>();
		
		String numTelV2 = "913333333";
		List<Regiao> disponiveis2 = new ArrayList<>();
		String codigoSMS2;
		
		String nomeM2 = "Corrin";
		String numTelM2 = "196666666";
		String[] familiares2 = {"Rhajat", "Effie"};
		List<Regiao> regioes2 = new ArrayList<>();
		List<String> ajudasDaRegiao2 = new ArrayList<>();
		
		String numTelV3 = "916666666";
		List<Regiao> disponiveis3 = new ArrayList<>();
		String codigoSMS3;
		
		RegistaAjudaAlojHandler handlerV1 = new MigrantMatcher().getRegistaAjudaAlojHandler();
		handlerV1.iniciarAplicacao(numTelV1);
		disponiveis1 = handlerV1.indicarNumPessoas(5);
		codigoSMS1 = handlerV1.associaAjudaRegiao(disponiveis1.get(5));
		handlerV1.indicarCodigo(codigoSMS1);
		
		RegistaAjudaItemHandler handlerV2 = new MigrantMatcher().getRegistaAjudaItemHandler();
		handlerV2.iniciarAplicacao(numTelV2);
		String descItem = "Frigorífico 3000 em bom estado";
		disponiveis2 = handlerV2.indicaDescItem(descItem);
		codigoSMS2 = handlerV2.associaAjudaRegiao(disponiveis2.get(5));
		handlerV2.indicarCodigo(codigoSMS2);
		
		RegistaAjudaAlojHandler handlerV3 = new MigrantMatcher().getRegistaAjudaAlojHandler();
		handlerV3.iniciarAplicacao(numTelV3);
		disponiveis3 = handlerV3.indicarNumPessoas(3);
		codigoSMS3 = handlerV3.associaAjudaRegiao(disponiveis3.get(0));
		handlerV3.indicarCodigo(codigoSMS3);
		
		ProcuraAjudaIndividualHandler handlerM1 = new MigrantMatcher().getProcuraAjudaIndividualHandler();
		handlerM1.indicarInfo(nomeM1, numTelM1);
		regioes1 = handlerM1.pedirListaRegioes();
		Regiao regiaoEscolhida1 = regioes1.get(0);
		ajudasDaRegiao1 = handlerM1.indicarRegiao(regiaoEscolhida1);
		if(!ajudasDaRegiao1.isEmpty()) {
			String ajudaEscolhida1 = ajudasDaRegiao1.get(0);
			handlerM1.escolherAjuda(ajudaEscolhida1);
			handlerM1.confirmarAjuda();
		}
		
		ProcuraAjudaFamiliarHandler handlerM2 = new MigrantMatcher().getProcuraAjudaFamiliarHandler();
		handlerM2.indicarInfo(nomeM2, numTelM2);
		handlerM2.indicarNumPessoasFamiliar(familiares2.length);
		handlerM2.indicarFamiliar(familiares2[0]);
		handlerM2.indicarFamiliar(familiares2[1]);
		regioes2 = handlerM2.pedirListaRegioes();
		Regiao regiaoEscolhida2 = regioes2.get(5);
		ajudasDaRegiao2 = handlerM2.indicarRegiao(regiaoEscolhida2);
		if(!ajudasDaRegiao2.isEmpty()) {
			int numAjudasEscolhidas2 = 2;
			int i2 = 0;
			while(i2 < numAjudasEscolhidas2 && i2 < ajudasDaRegiao2.size()) {
				String ajudaEscolhida2 = ajudasDaRegiao2.get(i2);
				handlerM2.escolherAjuda(ajudaEscolhida2);
				i2++;
			}
			handlerM2.confirmarAjuda();
		}
	}

	@Test
	/**
	 * Testa se a exception FamiliaMaiorQueAlojException é lançada quando o tamanho de uma família de um 
	 * registo familiar é maior que a capacidade do alojamento.
	 */
	void testFamiliaresMaiorQueCapacidadeAloj() throws NumPessoasAlojIgualMenorZeroException, 
	RegiaoNotInCatRegioesException, NumFamiliaresIgualMenorZeroException {
		String numTelV = "918888888";
		List<Regiao> disponiveis = new ArrayList<>();
		String codigoSMS;
		
		String nomeM = "Mr. Resetti";
		String numTelM = "198888888";
		String[] familiares = {"Resetti Jr.1", "Resetti Jr.2"};
		List<Regiao> regioes = new ArrayList<>();
		List<String> ajudasDaRegiao = new ArrayList<>();
		
		RegistaAjudaAlojHandler handlerV = new MigrantMatcher().getRegistaAjudaAlojHandler();
		handlerV.iniciarAplicacao(numTelV);
		disponiveis = handlerV.indicarNumPessoas(2);
		codigoSMS = handlerV.associaAjudaRegiao(disponiveis.get(4));
		handlerV.indicarCodigo(codigoSMS);
		
		ProcuraAjudaFamiliarHandler handlerM = new MigrantMatcher().getProcuraAjudaFamiliarHandler();
		handlerM.indicarInfo(nomeM, numTelM);
		handlerM.indicarNumPessoasFamiliar(familiares.length);
		handlerM.indicarFamiliar(familiares[0]);
		handlerM.indicarFamiliar(familiares[1]);
		regioes = handlerM.pedirListaRegioes();
		Regiao regiaoEscolhida = regioes.get(4);
		ajudasDaRegiao = handlerM.indicarRegiao(regiaoEscolhida);
		if(!ajudasDaRegiao.isEmpty()) {
			String ajudaEscolhida = ajudasDaRegiao.get(0);
			assertThrows(FamiliaMaiorQueAlojException.class, () -> handlerM.escolherAjuda(ajudaEscolhida));
		}
	}
	
	@Test
	/**
	 * Testa se a exception NumPessoasAlojIgualMenorZeroException é lançada quando o tamanho da capacidade 
	 * de um alojamento é inferior ou igual a zero.
	 */
	void testNumPessoasAlojIgualMenorZeroZero() {
		RegistaAjudaAlojHandler handlerV = new MigrantMatcher().getRegistaAjudaAlojHandler();
		assertThrows(NumPessoasAlojIgualMenorZeroException.class, () -> handlerV.indicarNumPessoas(-1));
	}
	
	@Test
	/**
	 * Testa se a exception NumFamiliaresIgualMenorZeroException é lançada quando o número de familiares
	 * indicado é inferior ou igual a zero.
	 */
	void testNumFamiliaresIgualMenorZero() throws NumFamiliaresIgualMenorZeroException {
		ProcuraAjudaFamiliarHandler handlerM = new MigrantMatcher().getProcuraAjudaFamiliarHandler();
		assertThrows(NumFamiliaresIgualMenorZeroException.class, () -> handlerM.indicarNumPessoasFamiliar(0));
	}
	
	@Test
	/**
	 * Testa se a exception RegiaoNotInCatRegioesException é lançada quando o user(voluntário) tenta
	 * associar uma região não existente à ajuda.
	 */
	void userAdicionaNovaRegiao() throws NumPessoasAlojIgualMenorZeroException, 
	RegiaoNotInCatRegioesException {
		RegistaAjudaAlojHandler handlerV = new MigrantMatcher().getRegistaAjudaAlojHandler();
		Regiao maliciosa = new Regiao("maliciosa");
		assertThrows(RegiaoNotInCatRegioesException.class, () -> handlerV.associaAjudaRegiao(maliciosa));
	}
	
	@Test
	/**
	 * Testa se a exception RegiaoNotInCatRegioesException é lançada quando o user(migrante) tenta
	 * indicar uma região não existente.
	 */
	void userIndicaRegiaoInexistente() {
		ProcuraAjudaFamiliarHandler handlerM = new MigrantMatcher().getProcuraAjudaFamiliarHandler();
		Regiao atlantida = new Regiao("atlantida");
		assertThrows(RegiaoNotInCatRegioesException.class, () -> handlerM.indicarRegiao(atlantida));
	}
	
	@Test
	/**
	 * Testa se a exception AjudaNaoExisteException é lançada quando o user(migrante) tenta escolher uma
	 * ajuda não existente.
	 */
	void userEscolheAjudaInexistente() {
		ProcuraAjudaIndividualHandler handlerM = new MigrantMatcher().getProcuraAjudaIndividualHandler();
		assertThrows(AjudaNaoExisteException.class, () -> handlerM.escolherAjuda("maliciosa"));
	}
	
	@Test
	/**
	 * Testa se a exception java.lang.NullPointerException é lançada quando o user(migrante) tenta confirmar
	 * as ajudas escolhidas sem ter escolhido nenhuma ajuda.
	 */
	void userConfirmaAjudasSemEscolher() {
		ProcuraAjudaFamiliarHandler handlerM = new MigrantMatcher().getProcuraAjudaFamiliarHandler();
		assertThrows(java.lang.NullPointerException.class, () -> handlerM.confirmarAjuda());
	}
}
