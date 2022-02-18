package com.duda.cursojava.aula74.labs;

public class ThreadSemafaro implements Runnable {

	//atributo
	private CorSemafaro cor;
	private boolean parar;
	private boolean corMudou;

	//metodo
	public ThreadSemafaro(){
		this.cor = CorSemafaro.VERMELHO;

		//por padrao o valor incial do atributo booleano e falso
		//this.parar = false; //opcional
		//this.corMudou = false;

		//iniciando a Thread
		new Thread(this).start();
	}

	//aqui mudamos a cor do semafaro
	@Override
	public void run() {

		//enquanto nao parar...
		while(!parar){

			try {
				/*switch (this.cor) {
					case VERMELHO:
						Thread.sleep(2000);
						break;
					case AMARELO:
						Thread.sleep(300);
						break;
					case VERDE:
						Thread.sleep(1000);
						break;
					default:
						break;
					}*/
				Thread.sleep(this.cor.getTempoEspera());

				//chamando o metodo 
				this.mudarCor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//metodo para mudar a cor
	private synchronized void mudarCor(){
		switch (this.cor) {
		case VERMELHO:
			this.cor = CorSemafaro.VERDE;
			break;
		case AMARELO:
			this.cor = CorSemafaro.VERMELHO;
			break;
		case VERDE:
			this.cor = CorSemafaro.AMARELO;
			break;
		default:
			break;
		}
		this.corMudou = true;
		notify();
	}

	//metodo para avisar que mudou de cor e assim mudar novamente
	public synchronized void esperaCorMudar(){
		while(!this.corMudou){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.corMudou = false;
	}

	public synchronized void desligarSemafaro(){
		this.parar = true;
	}

	public CorSemafaro getCor() {
		return cor;
	}
}
