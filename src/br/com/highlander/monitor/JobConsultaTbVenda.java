package br.com.highlander.monitor;

import java.util.Date;

import br.com.highlander.engine.ProcessamentoEngine;
import br.com.highlander.engine.VendaEngine;
import br.com.highlander.entities.Processamento;
import br.com.highlander.entities.Venda;

public class JobConsultaTbVenda implements Runnable {

	@Override
	public void run() {

		try {

			while(true) {

				System.out.println("JobConsultaTbVenda " + new Date());

				Venda venda = VendaEngine.getInstance().getPrimeiroRegistroDisponivel();

				boolean retirarDaFila = VendaEngine.getInstance().trocaStatus(venda);

				if (retirarDaFila)System.out.println("removendo da fila");
				else System.out.println("falhou a remocao e a troca do status");

				System.out.println("Processando a fila de venda" + venda);

				Processamento processamento = ProcessamentoEngine.getInstance().factoy(venda);

				JobProcessamento.getInstance().add(processamento);

				Thread.sleep(1000 * 60);

			}

		} catch (InterruptedException e) {

			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}