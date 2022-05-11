package br.unitins.livros.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.livros.model.Genero;
import br.unitins.livros.model.Livro;

public class LivroDAO implements DAO<Livro> {

	@Override
	public boolean insert(Livro obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO livro ( ");
		sql.append("  nome, ");
		sql.append("  data_lancamento, ");
		sql.append("  editora, ");
		sql.append("  autor, ");
		sql.append("  id_genero ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?  ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setDate(2, Date.valueOf(obj.getDataLancamento()));
			stat.setString(3, obj.getEditora());
			stat.setString(4, obj.getAutor());
			if ((obj.getGenero() == null || obj.getGenero().getId() == null))
				stat.setObject(5, null);
			else
				stat.setInt(5, obj.getGenero().getId());

			stat.execute();

		} catch (SQLException e) {
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

	@Override
	public boolean update(Livro obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE livro SET  ");
		sql.append("  nome = ?, ");
		sql.append("  data_lancamento = ?, ");
		sql.append("  editora = ?, ");
		sql.append("  autor = ?, ");
		sql.append("  id_genero = ? ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setDate(2, Date.valueOf(obj.getDataLancamento()));
			stat.setString(3, obj.getEditora());
			stat.setString(4, obj.getAutor());
			if ((obj.getGenero() == null || obj.getGenero().getId() == null))
				stat.setObject(5, null);
			else
				stat.setInt(5, obj.getGenero().getId());
			
			stat.setInt(6, obj.getId());

			stat.execute();

		} catch (SQLException e) {
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

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Livro> getAll() {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Livro> lista = new ArrayList<Livro>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  l.id, ");
		sql.append("  l.nome, ");
		sql.append("  l.data_lancamento, ");
		sql.append("  l.editora, ");
		sql.append("  l.autor, ");
		sql.append("  l.id_genero ");
		sql.append("FROM ");
		sql.append("  livro l ");
		sql.append("ORDER BY ");
		sql.append("  l.nome ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setNome(rs.getString("nome"));
				livro.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
				livro.setEditora(rs.getString("editora"));
				livro.setAutor(rs.getString("autor"));
				
				livro.setGenero(new Genero());
				livro.getGenero().setId(rs.getInt("id_genero"));
					
				lista.add(livro);
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
	
	public Livro getById(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}
		
		Livro livro = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  l.id, ");
		sql.append("  l.nome, ");
		sql.append("  l.data_lancamento, ");
		sql.append("  l.editora, ");
		sql.append("  l.autor, ");
		sql.append("  l.id_genero ");
		sql.append("FROM ");
		sql.append("  livro l ");
		sql.append("WHERE ");
		sql.append("  l.id = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setNome(rs.getString("nome"));
				livro.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
				livro.setEditora(rs.getString("editora"));
				livro.setAutor(rs.getString("autor"));
				
				livro.setGenero(new Genero());
				livro.getGenero().setId(rs.getInt("id_genero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		return livro;
	}

	public List<Livro> getByNome(String nome) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Livro> lista = new ArrayList<Livro>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  l.id, ");
		sql.append("  l.nome, ");
		sql.append("  l.data_lancamento, ");
		sql.append("  l.editora, ");
		sql.append("  l.autor, ");
		sql.append("  l.id_genero ");
		sql.append("FROM ");
		sql.append("  livro l ");
		sql.append("WHERE ");
		sql.append(" l.nome iLIKE ? ");
		sql.append("ORDER BY ");
		sql.append("  l.nome ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + nome + "%");
			
			rs = stat.executeQuery();
			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setNome(rs.getString("nome"));
				livro.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
				livro.setEditora(rs.getString("editora"));
				livro.setAutor(rs.getString("autor"));
				
				livro.setGenero(new Genero());
				livro.getGenero().setId(rs.getInt("id_genero"));
					
				lista.add(livro);
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
