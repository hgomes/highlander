package br.com.highlander.engine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.highlander.db.ProcessamentoDAO;
import br.com.highlander.entities.Processamento;
import br.com.highlander.entities.Venda;

public class ProcessamentoEngine {

	private static ProcessamentoEngine instance;
	private ProcessamentoDAO processamentoDao;

	private ProcessamentoEngine() {

		ApplicationContext context =
	    		new ClassPathXmlApplicationContext(new String[] { "spring-beans.xml" });

		this.processamentoDao = (ProcessamentoDAO) context.getBean("processamentoDao");

	}

	public static synchronized ProcessamentoEngine getInstance() {

		if (instance==null)instance = new ProcessamentoEngine();

		return instance;
	}

	public Processamento factoy(Venda venda) {

		Processamento processamento = new Processamento();
		processamento.setLoja(venda.getLoja());
		processamento.setPdv(venda.getPdv());

		return processamento;
	}

	public boolean inserir(Processamento processamento) {

		boolean ok = this.processamentoDao.inserir(processamento);

		return ok;
	}

	public Processamento getProcessamentoPendente() {

		Processamento processamento = this.processamentoDao.selectPrimeiroRegistroPendente();

		return processamento;

	}
}
