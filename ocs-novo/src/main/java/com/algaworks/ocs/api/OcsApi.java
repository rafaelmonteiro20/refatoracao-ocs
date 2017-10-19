package com.algaworks.ocs.api;

import com.algaworks.ocs.cdr.CDRGenerator;
import com.algaworks.ocs.log.ApiLogger;
import com.algaworks.ocs.model.Cliente;
import com.algaworks.ocs.repository.Clientes;
import com.algaworks.ocs.service.ClienteService;

public class OcsApi {

	private ApiLogger logger;
	private Clientes clientes;
	private CDRGenerator cdrGenerator;
	private Autorizavel autorizavel;

	public OcsApi(CDRGenerator cdrGenerator, Clientes clientes) {
		ClienteService service = new ClienteService(clientes);
		this.autorizavel = new ApiLogger(service);
		this.cdrGenerator = cdrGenerator;
	}

	public Ligacao autorizar(String numero) {
		return autorizavel.autorizar(numero);
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
