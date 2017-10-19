package com.algaworks.ocs.cdr;

import com.algaworks.ocs.model.Cliente;

public class CDRGeneratorNoSQL implements CDRGenerator {

	@Override
	public void gerar(String numero, double tempo, double valorLigacao) {
		// Salvar em um banco NoSQL
	}

	@Override
	public void finalizar(Cliente cliente, double tempo) {
		// Finalizando ligação
	}

}
