package com.migrantchecker.tests;

import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.migrantchecker.MigrantChecker;
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

class Tests {
	
	@Test
	void testMultiplasAjudasMultiplosRegistos() throws NumPessoasAlojIgualMenorZeroException, 
	RegiaoNotInCatRegioesException, FamiliaMaiorQueAlojException, AjudaNaoExisteException, NumFamiliaresIgualMenorZeroException {
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
		
		RegistaAjudaAlojHandler handlerV1 = new MigrantChecker().getRegistaAjudaAlojHandler();
		handlerV1.iniciarAplicacao(numTelV1);
		disponiveis1 = handlerV1.indicarNumPessoas(5);
		codigoSMS1 = handlerV1.associaAjudaRegiao(disponiveis1.get(5));
		handlerV1.indicarCodigo(codigoSMS1);
		
		RegistaAjudaItemHandler handlerV2 = new MigrantChecker().getRegistaAjudaItemHandler();
		handlerV2.iniciarAplicacao(numTelV2);
		String descItem = "Frigorífico 3000 em bom estado";
		disponiveis2 = handlerV2.indicaDescItem(descItem);
		codigoSMS2 = handlerV2.associaAjudaRegiao(disponiveis2.get(5));
		handlerV2.indicarCodigo(codigoSMS2);
		
		RegistaAjudaAlojHandler handlerV3 = new MigrantChecker().getRegistaAjudaAlojHandler();
		handlerV3.iniciarAplicacao(numTelV3);
		disponiveis3 = handlerV3.indicarNumPessoas(3);
		codigoSMS3 = handlerV3.associaAjudaRegiao(disponiveis3.get(0));
		handlerV3.indicarCodigo(codigoSMS3);
		
		ProcuraAjudaIndividualHandler handlerM1 = new MigrantChecker().getProcuraAjudaIndividualHandler();
		handlerM1.indicarInfo(nomeM1, numTelM1);
		regioes1 = handlerM1.pedirListaRegioes();
		Regiao regiaoEscolhida1 = regioes1.get(0);
		ajudasDaRegiao1 = handlerM1.indicarRegiao(regiaoEscolhida1);
		if(!ajudasDaRegiao1.isEmpty()) {
			String ajudaEscolhida1 = ajudasDaRegiao1.get(0);
			handlerM1.escolherAjuda(ajudaEscolhida1);
			handlerM1.confirmarAjuda();
		}
		
		ProcuraAjudaFamiliarHandler handlerM2 = new MigrantChecker().getProcuraAjudaFamiliarHandler();
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
				String ajudaEscolhida2 = ajudasDaRegiao2.get(0);
				handlerM2.escolherAjuda(ajudaEscolhida2);
				handlerM2.confirmarAjuda();
				i2++;
			}
		}
	}

	@Test
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
		
		RegistaAjudaAlojHandler handlerV = new MigrantChecker().getRegistaAjudaAlojHandler();
		handlerV.iniciarAplicacao(numTelV);
		disponiveis = handlerV.indicarNumPessoas(2);
		codigoSMS = handlerV.associaAjudaRegiao(disponiveis.get(4));
		handlerV.indicarCodigo(codigoSMS);
		
		ProcuraAjudaFamiliarHandler handlerM = new MigrantChecker().getProcuraAjudaFamiliarHandler();
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
	void testNumPessoasAlojIgualMenorZeroZero() {
		RegistaAjudaAlojHandler handlerV = new MigrantChecker().getRegistaAjudaAlojHandler();
		assertThrows(NumPessoasAlojIgualMenorZeroException.class, () -> handlerV.indicarNumPessoas(-1));
	}
	
	@Test
	void testNumFamiliaresIgualMenorZero() throws NumFamiliaresIgualMenorZeroException {
		ProcuraAjudaFamiliarHandler handlerM = new MigrantChecker().getProcuraAjudaFamiliarHandler();
		assertThrows(NumFamiliaresIgualMenorZeroException.class, () -> handlerM.indicarNumPessoasFamiliar(0));
	}
	
	@Test
	void userAdicionaNovaRegiao() throws NumPessoasAlojIgualMenorZeroException, 
	RegiaoNotInCatRegioesException {
		RegistaAjudaAlojHandler handlerV = new MigrantChecker().getRegistaAjudaAlojHandler();
		Regiao maliciosa = new Regiao("maliciosa");
		assertThrows(RegiaoNotInCatRegioesException.class, () -> handlerV.associaAjudaRegiao(maliciosa));
	}
	
	@Test
	void userIndicaRegiaoInexistente() {
		ProcuraAjudaFamiliarHandler handlerM = new MigrantChecker().getProcuraAjudaFamiliarHandler();
		Regiao atlantida = new Regiao("atlantida");
		assertThrows(RegiaoNotInCatRegioesException.class, () -> handlerM.indicarRegiao(atlantida));
	}
	
	@Test
	void userEscolheAjudaInexistente() {
		ProcuraAjudaIndividualHandler handlerM = new MigrantChecker().getProcuraAjudaIndividualHandler();
		assertThrows(AjudaNaoExisteException.class, () -> handlerM.escolherAjuda("maliciosa"));
	}
	
	@Test
	void userConfirmaAjudasSemEscolher() {
		ProcuraAjudaFamiliarHandler handlerM = new MigrantChecker().getProcuraAjudaFamiliarHandler();
		assertThrows(java.lang.NullPointerException.class, () -> handlerM.confirmarAjuda());
	}
}
