package br.com.highlander.monitor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import br.com.highlander.engine.ProcessamentoEngine;
import br.com.highlander.entities.Processamento;

public class JobProcessamento extends Consumer<Processamento>  {

     private static int      MAX_QUEUE_SIZE             = 1;
     private static int      MAX_ITEMS_CONSUMED_PER_RUN = 1;
     private static long     SLEEP_TIME                 = 1000 * 60;

     private static JobProcessamento instance;

     public static synchronized JobProcessamento getInstance() throws Exception {

    	 if (instance==null)instance = new JobProcessamento();

    	 return instance;
     }

	 private JobProcessamento()  throws Exception {
	      super(new LinkedBlockingQueue<Processamento>(MAX_QUEUE_SIZE),
	            MAX_ITEMS_CONSUMED_PER_RUN,
	            SLEEP_TIME);
	 }

	@Override
	protected void consume(List<Processamento> l) {

		for (Processamento processamento: l) {

			System.out.println("Gerando processamento " + processamento + " " + new Date());

			processamento.setStatus(PENDENTE);

			ProcessamentoEngine.getInstance().inserir(processamento);
		}
	}

	private final static String PENDENTE = "PENDENTE";

}


