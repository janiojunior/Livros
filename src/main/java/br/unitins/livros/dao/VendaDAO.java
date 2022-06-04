package br.unitins.livros.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.livros.model.Autor;
import br.unitins.livros.model.ItemVenda;
import br.unitins.livros.model.Usuario;
import br.unitins.livros.model.Venda;

public class VendaDAO implements DAO<Venda> {

	@Override
	public boolean insert(Venda obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}
		
		// transacao de forma manual(agora eh obrigado a executar um commit)
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO venda ( ");
		sql.append("  data_venda, ");
		sql.append("  id_usuario ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?  ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			// so salva a data .. sem a hora.
			stat.setDate(1, Date.valueOf(obj.getDataVenda().toLocalDate()));
			stat.setInt(2, obj.getUsuario().getId());
			stat.execute();
			
			// obtendo o id gerado pelo banco
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt("id"));
			}
			
			// obj eh nossa venda
			salvarItensVenda(obj, conn);
			
			// salvando no banco (definitivo)
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	private void salvarItensVenda(Venda obj, Connection conn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO item_venda ( ");
		sql.append("  valor, ");
		sql.append("  quantidade, ");
		sql.append("  id_livro, ");
		sql.append("  id_venda ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?  ");
		sql.append(") ");

		PreparedStatement stat = null;
		for (ItemVenda itemVenda : obj.getListaItemVenda()) {
			stat = conn.prepareStatement(sql.toString());
			stat.setDouble(1, itemVenda.getValor());
			stat.setInt(2, itemVenda.getQuantidade());
			stat.setInt(3, itemVenda.getLivro().getId());
			stat.setInt(4, obj.getId());
			stat.execute();
		}
	}

	@Override
	public boolean update(Venda obj) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}

	@Override
	public List<Venda> getAll() {
//		Connection conn = DAO.getConnection();
//		if (conn == null) {
//			return null;
//		}
//
//		List<Venda> lista = new ArrayList<Venda>();
//
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT ");
//		sql.append("  l.id, ");
//		sql.append("  l.nome, ");
//		sql.append("  l.data_lancamento, ");
//		sql.append("  l.preco, ");
//		sql.append("  l.estoque, ");
//		sql.append("  l.editora, ");
//		sql.append("  l.id_autor, ");
//		sql.append("  a.nome AS nome_autor, ");
//		sql.append("  a.data_nascimento ");
//		sql.append("FROM ");
//		sql.append("  venda l LEFT JOIN autor a ON a.id = l.id_autor ");
//		sql.append("ORDER BY ");
//		sql.append("  l.nome ");
//		
//		ResultSet rs = null;
//
//		try {
//			rs = conn.createStatement().executeQuery(sql.toString());
//			while (rs.next()) {
//				Venda venda = new Venda();
//				venda.setId(rs.getInt("id"));
//				venda.setNome(rs.getString("nome"));
//				venda.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
//				venda.setPreco(rs.getDouble("preco"));
//				venda.setEstoque(rs.getInt("estoque"));
//				venda.setEditora(rs.getString("editora"));
//				venda.setAutor(new Autor());
//				if (rs.getObject("id_autor") != null) {
//					venda.getAutor().setId(rs.getInt("id_autor"));
//					venda.getAutor().setNome(rs.getString("nome_autor"));
//					venda.getAutor().setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
//				}
//
//				lista.add(venda);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			lista = null;
//		}
//
//		try {
//			rs.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return lista;
		return null;
	}

	public Venda getById(int id) {
//		Connection conn = DAO.getConnection();
//		if (conn == null) {
//			return null;
//		}
//
//		Venda venda = null;
//
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT ");
//		sql.append("  l.id, ");
//		sql.append("  l.nome, ");
//		sql.append("  l.data_lancamento, ");
//		sql.append("  l.preco, ");
//		sql.append("  l.estoque, ");
//		sql.append("  l.editora, ");
//		sql.append("  l.id_autor, ");
//		sql.append("  a.nome AS nome_autor, ");
//		sql.append("  a.data_nascimento ");
//		sql.append("FROM ");
//		sql.append("  venda l LEFT JOIN autor a ON a.id = l.id_autor ");
//		sql.append("WHERE ");
//		sql.append("  l.id = ? ");
//
//		ResultSet rs = null;
//		PreparedStatement stat = null;
//		try {
//			stat = conn.prepareStatement(sql.toString());
//			stat.setInt(1, id);
//
//			rs = stat.executeQuery();
//			if (rs.next()) {
//				venda = new Venda();
//				venda.setId(rs.getInt("id"));
//				venda.setNome(rs.getString("nome"));
//				venda.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
//				venda.setPreco(rs.getDouble("preco"));
//				venda.setEstoque(rs.getInt("estoque"));
//				venda.setEditora(rs.getString("editora"));
//				venda.setAutor(new Autor());
//				if (rs.getObject("id_autor") != null) {
//					venda.getAutor().setId(rs.getInt("id_autor"));
//					venda.getAutor().setNome(rs.getString("nome_autor"));
//					venda.getAutor().setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			rs.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return venda;
		return null;
	}

	public List<Venda> getByUsuario(Usuario usuario) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Venda> lista = new ArrayList<Venda>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.data_venda ");
		sql.append("FROM ");
		sql.append("  venda v ");
		sql.append("WHERE ");
		sql.append(" v.id_usuario = ? ");
		sql.append("ORDER BY ");
		sql.append("  v.data_venda DESC ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, usuario.getId());

			rs = stat.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setDataVenda(LocalDateTime.of(rs.getDate("data_venda").toLocalDate(), LocalTime.MIN));
				venda.setUsuario(usuario);
				
				lista.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = null;
		}

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
