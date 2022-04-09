package br.unitins.livros.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.livros.application.Util;
import br.unitins.livros.model.Usuario;

@Named
@ApplicationScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1258970559285652391L;
	private Usuario usuario;
	private List<Usuario> listaUsuario;

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado. Faça o download.");
			e.printStackTrace();
			return null;
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/livrosdb", "topicos1", "123456");
		} catch (SQLException e) {
			System.out.println("Problema ao conectar no banco de dados. Verifique as informacoes de conexao.");
			e.printStackTrace();
			return null;
		}

		return conn;
	}

	public void incluir() {
		Util.addMessageInfo("Inclusão realizada com sucesso.");
	}

	public void alterar() {
		limpar();
		Util.addMessageInfo("Alteração realizada com sucesso.");
	}

	public void excluir() {
		excluir(getUsuario());
		limpar();
	}

	public void excluir(Usuario usuario) {
		listaUsuario.remove(usuario);
		Util.addMessageInfo("Exclusão realizada com sucesso.");
	}

	public void editar(Usuario usuario) {
		setUsuario(usuario.getClone());
	}

	public void limpar() {
		usuario = null;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();

			Connection conn = UsuarioController.getConnection();
			if (conn == null) {
				Util.addMessageError("Problemas ao acessar o banco de dados. Entre em contato com o suporte.");
				return listaUsuario;
			}

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  u.id, ");
			sql.append("  u.nome, ");
			sql.append("  u.login, ");
			sql.append("  u.senha ");
			sql.append("FROM ");
			sql.append("  usuario u ");
			sql.append("ORDER BY ");
			sql.append("  u.nome DESC, u.login DESC ");

			ResultSet rs = null;
			try {
				rs = conn.createStatement().executeQuery(sql.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				while (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setLogin(rs.getString("login"));
					usuario.setSenha(rs.getString("senha"));

					listaUsuario.add(usuario);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

}
