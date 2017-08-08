package br.com.highlander.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.highlander.engine.VendaEngine;
import br.com.highlander.entities.Venda;

@RestController
public class VendaController extends Controller {


	@RequestMapping(value = "/vendas/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void getVenda(@PathVariable("id") String strId, HttpServletResponse response) {

		int id = Integer.parseInt(strId);

		Venda venda = VendaEngine.getInstance().getById(id);

		generatorJson(response, venda);
	}


	@RequestMapping(value = "/vendas/create", method = RequestMethod.POST)
	public ResponseEntity<String> postVenda(Venda venda) {

		System.out.println("put venda..." + venda);

		VendaEngine.getInstance().inserir(venda);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
}
