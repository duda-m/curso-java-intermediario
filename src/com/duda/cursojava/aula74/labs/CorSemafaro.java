package com.duda.cursojava.aula74.labs;

public enum CorSemafaro {

	//valores que o enum pode ter
	VERDE(1000), AMARELO(300), VERMELHO(2000);

	private int tempoEspera;

	CorSemafaro(int tempoEspera){
		this.tempoEspera = tempoEspera;
	}

	public int getTempoEspera() {
		return tempoEspera;
	}

}
