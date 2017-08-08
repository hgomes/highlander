package br.com.highlander.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import br.com.highlander.entities.ItemVenda;
import br.com.highlander.entities.Venda;

public class JdbcVendaDAO implements VendaDAO {

	private DataSource dataSource;

	public JdbcVendaDAO() {
		super();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Venda selectById(int id) {

		Venda venda    = null;
		Connection conn = null;

		try {

			conn 				   = this.dataSource.getConnection();
			PreparedStatement pstm = conn.prepareStatement(SELECT_BY_ID);
			pstm.setInt(1, id);

			ResultSet rs           = pstm.executeQuery();

			while(rs.next()) {

				venda = getResult(rs);
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return venda;
	}

	@Override
	public Venda selectPrimeiroRegistroDisponivel() {

		Venda venda = new Venda();

		Connection conn = null;
		Statement st    = null;
		ResultSet rs    = null;

		try {

			conn = this.dataSource.getConnection();
			st   = conn.createStatement();
			rs   = st.executeQuery(SELECT_PRIMEIRA_VENDA_DISPONIVEL);

			while(rs.next()) {

				venda = getResult(rs);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {
				if (conn!=null)conn.close();

				if (st!=null)st.close();

				if (rs!=null)rs.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return venda;
	}

	private Venda getResult(ResultSet rs) throws SQLException {

		Venda venda = new Venda();

		if (venda.getId()==0) { //gera o objeto venda.

			venda.setId(rs.getInt(1));
			venda.setData(rs.getDate(2));
			venda.setLoja(rs.getInt(3));
			venda.setPdv(rs.getInt(4));
			venda.setStatus(rs.getString(5));
		}

		venda.addItemVenda(new ItemVenda(rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDouble(9), rs.getDouble(10)));

		return venda;
	}


	public boolean trocaStatus(Venda venda) {

		PreparedStatement pstm = null;
		Connection conn        = null;

		try {

			conn = this.dataSource.getConnection();
			pstm = conn.prepareStatement(UPDATE_STATUS);
			pstm.setString(1, venda.getStatus());
			pstm.setInt(2, venda.getId());

			if (pstm.executeUpdate()>0) return true;;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				if (pstm!=null)pstm.close();

				if (conn!=null)conn.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean inserir(Venda venda) {

		PreparedStatement pstm = null;
		Connection conn        = null;
		ResultSet rs           = null;
		boolean ok 			   = false;

		try {

			conn = this.dataSource.getConnection();
			pstm = conn.prepareStatement(INSERIR);
			pstm.setInt(1, venda.getLoja());
			pstm.setInt(2, venda.getPdv());
			pstm.setString(3, venda.getStatus());

			ok = (pstm.executeUpdate()>0);

			rs = pstm.getGeneratedKeys();

			if (rs.next()) {
				venda.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {

				if (pstm!=null)pstm.close();

				if (conn!=null)conn.close();

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return ok;
	}



	private static final String UPDATE_STATUS = "UPDATE tb_venda SET status = ? WHERE id_venda = ? ";

	private static final String SELECT_BY_ID = "SELECT v.id_venda, v.data, v.loja, v.pdv, v.status, "
			+ "iv.id_venda, iv.id_item_venda, iv.produto, iv.preco_unitario, iv.desconto "
			+ "FROM tb_venda v "
			+ "INNER JOIN tb_item_venda iv ON v.id_venda = iv.id_venda "
			+ "WHERE v.id_venda = ? ";

	private static final String SELECT_PRIMEIRA_VENDA_DISPONIVEL = "SELECT v.id_venda, v.data, v.loja, v.pdv, v.status, "
			+ "iv.id_venda, iv.id_item_venda, iv.produto, iv.preco_unitario, iv.desconto "
			+ "FROM tb_venda v "
			+ "INNER JOIN tb_item_venda iv ON v.id_venda = iv.id_venda "
			+ "WHERE v.status like 'NÃO PROCESSADO' "
			+ "	AND v.id_venda = (SELECT MIN(id_venda) FROM tb_venda WHERE status like 'NÃO PROCESSADO'); ";

	private static final String INSERIR = "INSERT INTO tb_venda(data, loja, pdv, status) VALUES(now(), ?, ?, ?)";



}
