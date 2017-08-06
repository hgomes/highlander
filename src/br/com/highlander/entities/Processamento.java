package br.com.highlander.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Processamento {

	private int id;
	private Date data;
	private int loja;
	private int pdv;
	private String status;
	private String nomeArquivo;

	private List<Venda> vendas = new ArrayList<Venda>();

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getLoja() {
		return loja;
	}
	public void setLoja(int loja) {
		this.loja = loja;
	}
	public int getPdv() {
		return pdv;
	}
	public void setPdv(int pdv) {
		this.pdv = pdv;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public void addVenda(Venda venda) {
		this.vendas.add(venda);
	}
	public List<Venda> getVendas() {
		return this.vendas;
	}
	@Override
	public String toString() {
		return "Processamento [id=" + id + "]";
	}
}
