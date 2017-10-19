package com.algaworks.ocs.api;

public class Ligacao {

	private boolean autorizada;
	private double tempo;

	public Ligacao(boolean autorizada, double tempo) {
		if(tempo <= 0)
			throw new IllegalArgumentException("Tempo não permitido.");
		
		this.autorizada = autorizada;
		this.tempo = tempo;
	}

	public static Ligacao criarLigacaoNaoAutorizada() {
		return new Ligacao(false, 0.0);
	}

	public boolean isAutorizada() {
		return autorizada;
	}

	public void setAutorizada(boolean autorizada) {
		this.autorizada = autorizada;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

}
