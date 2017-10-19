package com.algaworks.ocs.service;

import com.algaworks.ocs.api.Autorizavel;
import com.algaworks.ocs.api.Ligacao;
import com.algaworks.ocs.model.Cliente;
import com.algaworks.ocs.repository.Clientes;

public class ClienteService implements Autorizavel {

	private Clientes clientes;
	
	public ClienteService(Clientes clientes) {
		this.clientes = clientes;
	}
	
	@Override
	public Ligacao autorizar(String numero) {
		Cliente cliente = clientes.porNumero(numero);
		
		if(cliente.getSaldo() > 0)
			return criarLigacaoAutorizada(cliente);

		return Ligacao.criarLigacaoNaoAutorizada();
	}
	
	private Ligacao criarLigacaoAutorizada(Cliente cliente) {
		double tempoPermitido = (cliente.getSaldo() / cliente.getTarifa().getValor()) * 60;
		return new Ligacao(true, tempoPermitido);
	}
	
}