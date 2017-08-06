package br.com.highlander.monitor;

public class Monitor {

	public static void main(String[] args) {

		new Thread(new JobConsultaTbVenda()).start();

		try {

			JobProcessamento.getInstance();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Starting thread");


	}

}
