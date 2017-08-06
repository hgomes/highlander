package br.com.highlander.monitor;

import java.util.Date;

import br.com.highlander.engine.ProcessamentoEngine;
import br.com.highlander.entities.Processamento;

public class JobGerarArquivo implements Runnable {

	@Override
	public void run() {

		try {

			while(true) {

				System.out.println("JobGerarArquivo " + new Date());

				Processamento processamento = ProcessamentoEngine.getInstance().getProcessamentoPendente();


				Thread.sleep(1000 * 60);

			}

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

}
