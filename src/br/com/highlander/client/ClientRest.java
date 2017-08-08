package br.com.highlander.client;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import br.com.highlander.config.ConfigManager;
import br.com.highlander.entities.Venda;

public class ClientRest {

	private RestTemplate restTemplate = null;

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 5000;
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(timeout);
	    return clientHttpRequestFactory;
	}

	public void inserirUmaVenda() {

		this.restTemplate = new RestTemplate(getClientHttpRequestFactory());

		//this.restTemplate = new RestTemplate();

		Venda venda = new Venda();
		venda.setLoja(1);
		venda.setPdv(2);
		venda.setStatus("NÃO PROCESSADO");

		String url = ConfigManager.getInstance().getUrlPost() + "vendas/create";

		System.out.println("url " + url);

		this.restTemplate.postForObject(url, venda, Venda.class);

		//this.restTemplate.postForLocation(url, venda, Collections.emptyMap());


		//Venda response = this.restTemplate.getForObject(url, Venda.class);

		//System.out.println("Venda criada " + response.getId());


	}

	public static void main(String[] args) {

		ClientRest client = new ClientRest();
		client.inserirUmaVenda();
	}

}
