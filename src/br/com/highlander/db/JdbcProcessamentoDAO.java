package br.com.highlander.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import br.com.highlander.entities.ItemVenda;
import br.com.highlander.entities.Processamento;
import br.com.highlander.entities.Venda;

public class JdbcProcessamentoDAO implements ProcessamentoDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean inserir(Processamento processamento) {

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = this.dataSource.getConnection();
			pstm = conn.prepareStatement(INSERIR);
			pstm.setInt(1, processamento.getLoja());
			pstm.setInt(2, processamento.getPdv());
			pstm.setString(3, processamento.getNomeArquivo());
			pstm.setString(4, processamento.getStatus());

			return (pstm.executeUpdate()>0);


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
	public Processamento selectPrimeiroRegistroPendente() {

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		Processamento p = new Processamento();
		Venda v 		= new Venda();

		try {

			conn = this.dataSource.getConnection();
			pstm = conn.prepareStatement(SELECT_PROCESSAMENTO);
			rs   = pstm.executeQuery();

			int idVenda = -1;

			while(rs.next()) {

				if (p.getId()==0) {

					p.setId(rs.getInt(1));
					p.setData(rs.getDate(2));
					p.setLoja(rs.getInt(3));
					p.setPdv(rs.getInt(4));
					p.setNomeArquivo(rs.getString(5));
					p.setStatus(rs.getString(6));
				}

				if (v.getId()!=idVenda) {

					v.setId(rs.getInt(7));
					v.setData(rs.getDate(8));
					v.setId(rs.getInt(9));
					v.setPdv(rs.getInt(10));
					v.setStatus(rs.getString(11));

					idVenda = v.getId();
				}

				v.addItemVenda(new ItemVenda(rs.getInt(12), rs.getInt(13), rs.getString(14), rs.getDouble(15), rs.getDouble(16)));
				p.addVenda(v);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs!=null)rs.close();

				if (pstm!=null)pstm.close();

				if (conn!=null)conn.close();

			} catch (SQLException e2) {

				e2.printStackTrace();
			}
		}

		return p;
	}

	public boolean trocarStatus(Processamento processamento) {

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = this.dataSource.getConnection();
			pstm = conn.prepareStatement(UPDATE_STATUS);
			pstm.setString(1, processamento.getStatus());
			pstm.setString(2, processamento.getNomeArquivo());
			pstm.setInt(3, processamento.getId());

			return (pstm.executeUpdate()>0);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {
				if (pstm!=null)pstm.close();

				if (conn!=null)conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}


	public final static String SELECT_PROCESSAMENTO = "SELECT p.id_processamento, p.data, p.loja, p.pdv, p.nome_arquivo, p.status, "
			+ "v.id_venda, v.data, v.loja, v.pdv, v.status, iv.id_venda, iv.id_item_venda, "
			+ "iv.produto, iv.preco_unitario, iv.desconto "
			+ "FROM tb_processamento p "
			+ "INNER JOIN tb_venda v ON p.pdv = v.pdv "
			+ "INNER JOIN tb_item_venda iv ON v.id_venda = iv.id_venda "
			+ "WHERE p.status like 'PENDENTE' "
			+ "AND p.id_processamento = (SELECT min(id_processamento) FROM tb_processamento WHERE status like 'PENDENTE') "
			+ "ORDER BY v.id_venda ;";

	public final static String INSERIR = "INSERT INTO tb_processamento(data, loja, pdv, nome_arquivo, status) VALUES(now(), ?, ?, ?, ?); ";

	public final static String UPDATE_STATUS = "UPDATE tb_processamento SET status = ?, nome_arquivo = ? WHERE id_processamento = ? ";

}
