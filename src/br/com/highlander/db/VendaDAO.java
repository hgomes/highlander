package br.com.highlander.db;

import br.com.highlander.entities.Venda;

public interface VendaDAO {

	public Venda selectById(int id);

	/**
	 * Seleciono o primeiro registro disponivel para consulta
	 * @return
	 */
	public Venda selectPrimeiroRegistroDisponivel();

	public boolean trocaStatus(Venda venda);

	public boolean inserir(Venda venda);

}
