package br.com.highlander.entities;

import br.com.highlander.json.JSONException;
import br.com.highlander.json.JSONObject;

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

	public int getIdVenda() {
		return idVenda;
	}

	public int getIdItemVenda() {
		return idItemVenda;
	}

	public String getProduto() {
		return produto;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public double getDesconto() {
		return desconto;
	}

	@Override
	public String toJson() {

		JSONObject json = new JSONObject();
		try {

			json.put("produto", this.getProduto());
			json.put("preco_unitario", this.getPrecoUnitario());
			json.put("desconto", this.getDesconto());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
}
