package br.unitins.livros.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.unitins.livros.application.Session;
import br.unitins.livros.application.Util;
import br.unitins.livros.dao.UsuarioDAO;
import br.unitins.livros.model.Usuario;

@Named
@RequestScoped
public class LoginController {
	
	private Usuario usuario;
	
	public void entrar() {
		String hash = Util.hash(getUsuario());
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.verificarLogin(getUsuario().getLogin(), hash);
		if (usuario == null) {
			Util.addMessageError("Login ou Senha inválido.");
			return;
		}
		// colocando o objeto na session
		Session.getInstance().set("usuarioLogado", usuario);
		
// 		outra forma de colocar o objeto na session
//		FacesContext.getCurrentInstance().getExternalContext().
//		getSessionMap().put("usuarioLogado", usuario);
		
		Util.redirect("template.xhtml");
	}
	
	public void limpar() {
		usuario = null;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

	
}
