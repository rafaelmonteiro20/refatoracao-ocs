package com.algaworks.ocs.log;

import org.apache.log4j.Logger;

public class ApiLogger implements LoggerOCS {

	private static final Logger LOGGER = Logger.getLogger(ApiLogger.class);
	
	@Override
	public void autorizandoLigacao(String numero) {
		LOGGER.info(String.format("Autorizando ligação para o número: %s", numero));
	}

	@Override
	public void ligacaoAutorizada(String numero, double tempoPermitido) {
		LOGGER.info(String.format("Tempo permitido, em segundos, para número %s é %.2f", numero, tempoPermitido));
	}
	
	@Override
	public void ligacaoNegada(String numero) {
		LOGGER.info(String.format("Número %s não tem saldo suficiente para completar ligação.", numero));
	}
	
	@Override
	public void finalizandoLigacao(String numero, double tempo) {
		LOGGER.info(String.format("Finalizando ligação para número %s que falou por %.2f segundos", numero, tempo));
	}
	
	@Override
	public void ligacaoFinalizada(String numero, double valorLigacao) {
		LOGGER.info(String.format("Chamada finalizada e débito efetuado no valor de R$%.2f para número %s", valorLigacao, numero));
	}
	
}
