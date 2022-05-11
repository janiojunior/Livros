package br.unitins.livros.controller;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.application.Util;
import br.unitins.livros.dao.LivroDAO;
import br.unitins.livros.model.Genero;
import br.unitins.livros.model.Livro;

@Named
@ViewScoped
public class Livro2Controller implements Serializable {

	private static final long serialVersionUID = 8269752858637589975L;
	private Livro livro;
	
	public Livro2Controller() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("livroFlash");
		setLivro((Livro)flash.get("livroFlash"));
	}
	
	public void voltar() {
		Util.redirect("livro1.xhtml");
	}

	public void incluir() {
		LivroDAO dao = new LivroDAO();
		if (!dao.insert(getLivro())) {
			Util.addMessageInfo("Erro ao tentar incluir o livro.");
			return;
		}
		limpar();
		Util.addMessageInfo("Inclusão realizada com sucesso.");
	}

	public void alterar() {
		LivroDAO dao = new LivroDAO();
		if (!dao.update(getLivro())) {
			Util.addMessageInfo("Erro ao tentar alterar o livro.");
			return;
		}
		limpar();
		Util.addMessageInfo("Alteração realizada com sucesso.");
	}

	public void excluir() {
		LivroDAO dao = new LivroDAO();
		if (!dao.delete(getLivro().getId())) {
			Util.addMessageInfo("Erro ao tentar excluir o livro.");
			return;
		}
		Util.addMessageInfo("Exclusão realizada com sucesso.");
		limpar();
	}

	public void limpar() {
		livro = null;
	}
	
	public Livro getLivro() {
		if (livro == null) {
			livro = new Livro();
			livro.setGenero(new Genero());
		}
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
