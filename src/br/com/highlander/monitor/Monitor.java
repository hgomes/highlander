package br.com.highlander.monitor;

import java.util.Scanner;

public class Monitor {

	private Thread jobConsulta, jobGerarArquivo;

	public void startThreads() {

		try {

			System.out.println("Starting thread");

			jobConsulta      = new Thread(new JobConsultaTbVenda()); //primeiro thread
			jobConsulta.start();

			JobProcessamento.getInstance(); //segundo thread

			jobGerarArquivo = new Thread(new JobGerarArquivo()); //terceiro thread
			jobGerarArquivo.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopThread() {

		System.out.println("Interropendo os threads");

		this.jobConsulta.interrupt();
		this.jobGerarArquivo.interrupt();

	}

	public static void main(String[] args) {

		Monitor monitor = new Monitor();

		Scanner sc = new Scanner(System.in);

		System.out.println("Digite um comando para manipular os threds (start/stop)");

		while(true) {

			String cmd = sc.next();

			if (cmd.equalsIgnoreCase("start")) {
				monitor.startThreads();
			} else if (cmd.equalsIgnoreCase("stop")) {
				monitor.stopThread();
				break;
			}
		}

	}

}
