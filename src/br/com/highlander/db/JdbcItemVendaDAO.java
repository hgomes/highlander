package br.com.highlander.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import br.com.highlander.entities.ItemVenda;

public class JdbcItemVendaDAO implements ItemVendaDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ItemVenda selectById(int id) {

		ItemVenda itemVenda = new ItemVenda();

		Connection conn = null;

		try {

			conn 				   = this.dataSource.getConnection();
			//PreparedStatement pstm = conn.prepareStatement(sql);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

		return itemVenda;
	}

	//id_item_venda, id_venda, produto, preco_unitario, desconto


	private static final String SELECT_BY_ID = "SELECT id_item_venda, id_venda, produto, preco_unitario, desconto  FROM tb_item_venda WHERE id_venda = ? ";

}
