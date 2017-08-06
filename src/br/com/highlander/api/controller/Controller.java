package br.com.highlander.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.highlander.entities.Resource;

public abstract class Controller {

	public void generatorJson(HttpServletResponse response, Resource resource) {

		try {

			response.getOutputStream().println(resource.toJson());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
