package br.com.highlander.db;

import br.com.highlander.entities.Processamento;

public interface ProcessamentoDAO {

	public boolean inserir(Processamento processamento);

	public Processamento selectPrimeiroRegistroPendente();

}
