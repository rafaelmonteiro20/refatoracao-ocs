package com.algaworks.ocs.cdr;

import com.algaworks.ocs.api.Finalizavel;

public interface CDRGenerator extends Finalizavel {

	void gerar(String numero, double tempo, double valorLigacao);

}