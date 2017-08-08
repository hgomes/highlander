package br.com.highlander.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.highlander.json.JSONArray;
import br.com.highlander.json.JSONObject;

public class Venda implements Resource, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date data;
	private int loja;
	private int pdv;
	private String status;

	private List<ItemVenda> itens = new ArrayList<ItemVenda>();

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

	public void addItemVenda(ItemVenda itemVenda) {
		this.itens.add(itemVenda);
	}

	public List<ItemVenda> getItens() {
		return this.itens;
	}



	@Override
	public String toJson() {

		JSONObject json = new JSONObject();

		try {

			json.put("id", String.valueOf(this.getId()));
			json.put("data", String.valueOf(this.getData()));
			json.put("loja", String.valueOf(this.getLoja()));
			json.put("pdv", String.valueOf(this.getPdv()));
			json.put("status", String.valueOf(this.getStatus()));

			JSONArray jsonArray = new JSONArray();
			json.put("itens", jsonArray);

			for (ItemVenda iv:this.getItens()) {
				jsonArray.put(iv.toJson());
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return json.toString();
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + "]";
	}




}
