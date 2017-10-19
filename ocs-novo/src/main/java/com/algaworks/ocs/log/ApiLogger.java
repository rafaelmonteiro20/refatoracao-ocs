package com.algaworks.ocs.log;

import org.apache.log4j.Logger;

import com.algaworks.ocs.api.Autorizavel;
import com.algaworks.ocs.api.Ligacao;

public class ApiLogger implements Autorizavel {

	private static final Logger LOGGER = Logger.getLogger(ApiLogger.class);
	
	private Autorizavel autorizavel;
	
	public ApiLogger(Autorizavel autorizavel) {
		this.autorizavel = autorizavel;
	}
	
	@Override
	public Ligacao autorizar(String numero) {
		
		LOGGER.info(String.format("Autorizando ligação para o número: %s", numero));
		
		Ligacao ligacao = autorizavel.autorizar(numero);
		
		if(ligacao.isAutorizada()) {
			LOGGER.info(String.format("Tempo permitido, em segundos, para número %s é %.2f", numero, ligacao.getTempo()));
		} else {
			LOGGER.info(String.format("Número %s não tem saldo suficiente para completar ligação.", numero));
		}
		
		return ligacao;
	}
	
	public void finalizandoLigacao(String numero, double tempo) {
		LOGGER.info(String.format("Finalizando ligação para número %s que falou por %.2f segundos", numero, tempo));
	}
	
	public void ligacaoFinalizada(String numero, double valorLigacao) {
		LOGGER.info(String.format("Chamada finalizada e débito efetuado no valor de R$%.2f para número %s", valorLigacao, numero));
	}
	
}
