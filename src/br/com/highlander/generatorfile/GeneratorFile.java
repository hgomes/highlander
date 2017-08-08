package br.com.highlander.generatorfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.highlander.config.ConfigManager;
import br.com.highlander.entities.ItemVenda;
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

	public String gerar() {

		try {

			String strFile   = makeNamefile();
			File file         = new File(ConfigManager.getInstance().getPastaProcessado() + strFile);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			gerandoLinha(bw);

			if (file.exists()) {
				System.out.println("Arquivo gerado...");
			} else {
				System.out.println("Problemas para gerar o arquivo txt");
			}

			return strFile;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void gerandoLinha(BufferedWriter bw) throws IOException {

		List<Venda> vendas = this.processamento.getVendas();
		char[] linha       = null;

		for (Venda venda :vendas) {

			List<ItemVenda> itensVenda = venda.getItens();

			linha = new char[52];

			for (ItemVenda itemVenda : itensVenda) {

				gerandoColuna(linha, "0" + venda.getId(), 0, 10);
				gerandoColuna(linha, "0" + simpleFileFormat.format(venda.getData()), 11, 18);
				gerandoColuna(linha, "0" + venda.getLoja(), 19, 22);
				gerandoColuna(linha, "0" + venda.getPdv(), 23, 25);
				gerandoColuna(linha, "0" + itemVenda.getProduto(), 26, 36);
				gerandoColuna(linha, "0" + itemVenda.getPrecoUnitario(), 37, 41);
				gerandoColuna(linha, "0" + itemVenda.getDesconto(), 42, 46);
				gerandoColuna(linha, "0" + (itemVenda.getPrecoUnitario() - itemVenda.getDesconto()), 47, 51);

			}

			bw.write(linha, 0, 51);
			bw.newLine();
		}

		bw.flush();
	}

	public void gerandoColuna(char[] linha, String txt, int off, int len) throws IOException {

		char[] temp = txt.toCharArray();
		int i 		= 0;

		for (;off<=len; off++) {

			if (temp.length>i) {
				linha[off] = temp[i];
			} else {
				linha[off] = ' ';
			}

			i++;
		}
	}

	public String makeNamefile() {
		return "highlander_" + simpleDateFormat.format(new Date()) + ".txt";
	}

}
