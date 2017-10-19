package com.algaworks.ocs.service;

import com.algaworks.ocs.api.Autorizavel;
import com.algaworks.ocs.api.Finalizavel;
import com.algaworks.ocs.api.Ligacao;
import com.algaworks.ocs.model.Cliente;
import com.algaworks.ocs.repository.Clientes;

public class ClienteService implements Autorizavel, Finalizavel {

	private Clientes clientes;
	
	private Finalizavel finalizavel;
	
	public ClienteService(Clientes clientes, Finalizavel finalizavel) {
		this.clientes = clientes;
		this.finalizavel = finalizavel;
	}
	
	@Override
	public Ligacao autorizar(String numero) {
		Cliente cliente = clientes.porNumero(numero);
		
		if(cliente.getSaldo() > 0)
			return criarLigacaoAutorizada(cliente);

		return Ligacao.criarLigacaoNaoAutorizada();
	}
	
	@Override
	public void finalizar(Cliente cliente, double tempo) {
		atualizarSaldo(cliente, tempo);
		clientes.salvar(cliente);
		finalizavel.finalizar(cliente, tempo);
	}

	private void atualizarSaldo(Cliente cliente, double tempo) {
		double valorLigacao = cliente.getTarifa().calcularValorLigacao(tempo);
		cliente.atualizarSaldo(valorLigacao);
	}
	
	private Ligacao criarLigacaoAutorizada(Cliente cliente) {
		double tempoPermitido = (cliente.getSaldo() / cliente.getTarifa().getValor()) * 60;
		return new Ligacao(true, tempoPermitido);
	}
	
}
