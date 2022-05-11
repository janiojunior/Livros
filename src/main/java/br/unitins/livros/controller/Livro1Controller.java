package br.unitins.livros.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.application.Util;
import br.unitins.livros.dao.LivroDAO;
import br.unitins.livros.model.Livro;

@Named
@ViewScoped
public class Livro1Controller implements Serializable {

	private static final long serialVersionUID = 1258970559285652391L;

	private String filtro;
	private List<Livro> listaLivro;
	
	public void editar(int id) {
		LivroDAO dao = new LivroDAO();
		Livro livro = dao.getById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("livroFlash", livro);
		
		Util.redirect("livro2.xhtml");
	}
	
	public void novo() {
		Util.redirect("livro2.xhtml");
	}
	
	public void pesquisar() {
		LivroDAO dao = new LivroDAO();
		setListaLivro(dao.getByNome(getFiltro()));
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Livro> getListaLivro() {
		return listaLivro;
	}

	public void setListaLivro(List<Livro> listaLivro) {
		this.listaLivro = listaLivro;
	}

}
