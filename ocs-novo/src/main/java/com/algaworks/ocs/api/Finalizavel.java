package com.algaworks.ocs.api;

import com.algaworks.ocs.model.Cliente;

public interface Finalizavel {

	void finalizar(Cliente cliente, double tempo);
	
}
