package br.com.highlander.generatorfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.highlander.entities.Processamento;
import br.com.highlander.entities.Venda;

public class GeneratorFile {

	private Processamento processamento;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy-HH:mm:ss");
	private SimpleDateFormat simpleFileFormat = new SimpleDateFormat("ddMMyyyy");

	public GeneratorFile(Processamento processamento) {
		super();
		this.processamento = processamento;
	}

	public void gerar() {

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(makeNamefile())));
			gerandoLinha(bw);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void gerandoLinha(BufferedWriter bw) throws IOException {

		List<Venda> vendas = this.processamento.getVendas();

		for (Venda venda :vendas) {

			/* PAREI AQUI
 			bw.write(String.valueOf(venda.getId()).toCharArray(), 0, 10);
			bw.write(String.valueOf(venda.getData()).toCharArray(), 11, 18);
			bw.write(String.valueOf(venda.getLoja()).toCharArray(), 19, 22);
			bw.write(String.valueOf(venda.getPdv()).toCharArray(), 23, 25);
			bw.write(venda.get).toCharArray(), 0, 10);
			bw.write(String.valueOf(venda.getId()).toCharArray(), 0, 10);
			bw.write(String.valueOf(venda.getId()).toCharArray(), 0, 10);
			bw.write(String.valueOf(venda.getId()).toCharArray(), 0, 10);
			*/

			bw.newLine();
		}
	}

	public String makeNamefile() {
		return "highlander_" + simpleDateFormat.toString();
	}

}
