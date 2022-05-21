package br.unitins.livros.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.application.Util;
import br.unitins.livros.dao.AutorDAO;
import br.unitins.livros.dao.LivroDAO;
import br.unitins.livros.model.Autor;
import br.unitins.livros.model.Livro;

@Named
@ViewScoped
public class Livro2Controller implements Serializable {

	private static final long serialVersionUID = 8269752858637589975L;
	private Livro livro;
	private List<Autor> listaAutor;
	
	public Livro2Controller() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("livroFlash");
		setLivro((Livro)flash.get("livroFlash"));
	}
	
	public List<Autor> getListaAutor() {
		if (listaAutor == null) {
			AutorDAO dao = new AutorDAO();
			listaAutor = dao.getAll();
			if (listaAutor == null)
				listaAutor = new ArrayList<Autor>();
		}
		return listaAutor;
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
			livro.setAutor(new Autor());
		}
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
