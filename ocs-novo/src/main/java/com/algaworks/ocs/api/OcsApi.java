package com.algaworks.ocs.api;

import com.algaworks.ocs.cdr.CDRGenerator;
import com.algaworks.ocs.log.ApiLogger;
import com.algaworks.ocs.log.LoggerOCS;
import com.algaworks.ocs.model.Cliente;
import com.algaworks.ocs.repository.Clientes;

public class OcsApi {

	private LoggerOCS logger;
	private Clientes clientes;
	private CDRGenerator cdrGenerator;

	public OcsApi(CDRGenerator cdrGenerator) {
		this.logger = new ApiLogger();
		this.clientes = new Clientes();
		
		this.cdrGenerator = cdrGenerator;
	}

	public Ligacao autorizar(String numero) {
		logger.autorizandoLigacao(numero);
		
		Cliente cliente = clientes.porNumero(numero);
		double saldo = cliente.getSaldo();
		double tarifa = cliente.getTarifa().getValor();
		
		boolean autorizada;
		double tempoPermitido = -1;
		
		if (saldo > 0) {
			autorizada = true;
			tempoPermitido = (saldo / tarifa) * 60;
			logger.ligacaoAutorizada(numero, tempoPermitido);
		} else {
			autorizada = false;
			logger.ligacaoNegada(numero);
		}

		return new Ligacao(autorizada, tempoPermitido);
	}

	public void finalizar(String numero, double tempo) {
		logger.finalizandoLigacao(numero, tempo);
		
		Cliente cliente = clientes.porNumero(numero);
		double tarifa = cliente.getTarifa().getValor();
		double saldo = cliente.getSaldo();
		double valorLigacao = (tempo / 60) * tarifa;
		saldo -= valorLigacao;
		cliente.setSaldo(saldo);
		
		clientes.salvar(cliente);
		logger.ligacaoFinalizada(numero, valorLigacao);
		
		cdrGenerator.gerar(numero, tempo, valorLigacao);
	}
	
}
