package br.com.highlander.entities;

public class ItemVenda implements Resource {

	private int idVenda;
	private int idItemVenda;
	private String produto;
	private double precoUnitario;
	private double desconto;

	public ItemVenda() {

	}

	public ItemVenda(int idVenda, int idItemVenda, String produto, double precoUnitario, double desconto) {
		super();
		this.idVenda = idVenda;
		this.idItemVenda = idItemVenda;
		this.produto = produto;
		this.precoUnitario = precoUnitario;
		this.desconto = desconto;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}
}
