package br.unitins.livros.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.livros.application.Util;
import br.unitins.livros.dao.UsuarioDAO;
import br.unitins.livros.model.Usuario;

@Named
@ApplicationScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1258970559285652391L;
	private Usuario usuario;
	private List<Usuario> listaUsuario;


	public void incluir() {
		UsuarioDAO dao = new UsuarioDAO();
		if (!dao.insert(getUsuario())) {
			Util.addMessageInfo("Erro ao tentar incluir o usuário.");
			return;
		}
		limpar();
		setListaUsuario(null);
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
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.getAll();
			if (listaUsuario == null) 
				listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

}
