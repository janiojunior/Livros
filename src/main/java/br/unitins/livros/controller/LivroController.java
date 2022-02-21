package br.unitins.livros.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.model.Livro;

@Named
@ViewScoped
public class LivroController implements Serializable {
	
	private static final long serialVersionUID = 1258970559285652391L;
	private Livro livro;
	private List<Livro> listaLivro;

	
	public void incluir() {
		getListaLivro().add(getLivro());
		livro = null;
	}
	
	public Livro getLivro() {
		if (livro == null)
			livro = new Livro();
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getListaLivro() {
		if (listaLivro == null)
			listaLivro = new ArrayList<Livro>();
		return listaLivro;
	}

	public void setListaLivro(List<Livro> listaLivro) {
		this.listaLivro = listaLivro;
	}
	
}
