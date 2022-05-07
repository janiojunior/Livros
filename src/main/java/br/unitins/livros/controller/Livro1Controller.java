package br.unitins.livros.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.livros.application.Util;
import br.unitins.livros.model.Autor;
import br.unitins.livros.model.Genero;
import br.unitins.livros.model.Livro;

@Named
@ApplicationScoped
public class Livro1Controller implements Serializable {

	private static final long serialVersionUID = 1258970559285652391L;

	private String filtro;
	private List<Livro> listaLivro;

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
