package com.algaworks.ocs.log;

public interface LoggerOCS {

	void autorizandoLigacao(String numero);
	
	void ligacaoAutorizada(String numero, double tempoPermitido);

	void ligacaoNegada(String numero);
	
	void finalizandoLigacao(String numero, double tempo);
	
	void ligacaoFinalizada(String numero, double valorLigacao);
	
}
