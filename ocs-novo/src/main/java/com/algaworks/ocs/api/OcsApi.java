package com.algaworks.ocs.api;

import com.algaworks.ocs.model.Cliente;
import com.algaworks.ocs.repository.Clientes;

public class OcsApi {

	private Clientes clientes;
	private Autorizavel autorizavel;
	private Finalizavel finalizavel;

	public OcsApi(Clientes clientes, Autorizavel autorizavel, Finalizavel finalizavel) {
		this.clientes = clientes;
		this.autorizavel = autorizavel;
		this.finalizavel = finalizavel;
	}

	public Ligacao autorizar(String numero) {
		return autorizavel.autorizar(numero);
	}

	public void finalizar(String numero, double tempo) {
		Cliente cliente = clientes.porNumero(numero);
		finalizavel.finalizar(cliente, tempo);
	}
	
}
