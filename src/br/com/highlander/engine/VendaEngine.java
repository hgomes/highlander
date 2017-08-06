package br.com.highlander.engine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.highlander.db.VendaDAO;
import br.com.highlander.entities.Venda;

public class VendaEngine {

	private static VendaEngine instance;
	private VendaDAO vendaDao;

	private VendaEngine() {

		ApplicationContext context =
	    		new ClassPathXmlApplicationContext(new String[] { "spring-beans.xml" });

		this.vendaDao = (VendaDAO) context.getBean("vendaDao");
	}

	public Venda getById(int id) {

		if (id==0)return null;

		Venda venda = this.vendaDao.selectById(id);

		return venda;
	}

	public Venda getPrimeiroRegistroDisponivel() {

		Venda venda = this.vendaDao.selectPrimeiroRegistroDisponivel();

		if (venda!=null && venda.getId()>0) {
			venda.setStatus(OK);
			trocaStatus(venda);
		}

		return venda;
	}

	public boolean trocaStatus(Venda venda) {

		boolean ok = this.vendaDao.trocaStatus(venda);

		return ok;
	}



	public static synchronized VendaEngine getInstance() {

		if (instance==null)instance = new VendaEngine();

		return instance;
	}

	public static final String OK = "OK";



}
