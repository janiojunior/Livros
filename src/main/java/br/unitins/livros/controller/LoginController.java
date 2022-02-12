package br.unitins.livros.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController {
	
	private String usuario = "janio";
	private String senha = "123";
	
	public String entrar() {
		if (usuario.equals("janio") && senha.equals("123"))
			return "principal.xhtml?faces-redirect=true";
		
		System.out.println(usuario);
		System.out.println(senha);
		return null;
	}
	
	public void limpar() {
		usuario = null;
		senha = null;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
