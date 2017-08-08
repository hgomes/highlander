package br.com.highlander.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigManager {

	private static ConfigManager instance;
	private PropertiesConfiguration config;


	public ConfigManager() {

		config = new PropertiesConfiguration();
		try {

			config.read(new FileReader(new File("highlander.properties")));

		} catch (ConfigurationException | IOException e) {

			e.printStackTrace();
		}
	}

	public static synchronized ConfigManager getInstance() {

		if (instance==null) {

			instance = new ConfigManager();
		}

		return instance;
	}

	public String getPastaProcessado() {
		return this.config.getString(PASTA_PROCESSADO);
	}

	public String getUrlPost() {
		return this.config.getString(URL_POST);
	}

	private static final String PASTA_PROCESSADO = "pasta.processado";
	private static final String URL_POST         = "url.post";

}
