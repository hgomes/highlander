package br.com.highlander.monitor;

import br.com.highlander.engine.ProcessamentoEngine;
import br.com.highlander.entities.Processamento;
import br.com.highlander.generatorfile.GeneratorFile;

public class JobGerarArquivo implements Runnable {

	@Override
	public void run() {

		try {

			while(true) {

				Processamento processamento = ProcessamentoEngine.getInstance().getProcessamentoPendente();

				if (processamento.getId()>0) {

					GeneratorFile generatorFile = new GeneratorFile(processamento);

					String nomeArquivo = generatorFile.gerar();

					if (nomeArquivo!=null) {

						processamento.setNomeArquivo(nomeArquivo);
						processamento.setStatus(OK);

						boolean ok = ProcessamentoEngine.getInstance().trocarStatus(processamento);

						System.out.println("Atualizando status na tabela processamento " + ok);
					}

				} else {
					System.out.println("Nao existe registro na tabela processamento para analisar");
				}

				Thread.sleep(1000 * 60);
			}

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	/*
	public void teste() {

		System.out.println("JobGerarArquivo " + new Date());

		Processamento processamento = ProcessamentoEngine.getInstance().getProcessamentoPendente();

		if (processamento.getId()>0) {

			GeneratorFile generatorFile = new GeneratorFile(processamento);

			String nomeArquivo = generatorFile.gerar();

			if (nomeArquivo!=null) {

				processamento.setNomeArquivo(nomeArquivo);
				processamento.setStatus(OK);

				boolean ok = ProcessamentoEngine.getInstance().trocarStatus(processamento);

				System.out.println("Atualizando status " + ok);
			}

		} else {
			System.out.println("Nao existe registro na tabela processamento para analisar");
		}

	}

	public static void main(String[] args) {

		JobGerarArquivo job = new JobGerarArquivo();
		job.teste();
	}*/

	private static final String OK = "OK";
}
