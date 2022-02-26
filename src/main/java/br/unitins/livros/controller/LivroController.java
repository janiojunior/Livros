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
	private int cont = 1;

	
	public void incluir() {
		getLivro().setId(cont++);
		getListaLivro().add(getLivro());
		livro = null;
	}
	
	public void alterar() {
		
		for (int index = 0; index < listaLivro.size(); index++) {
			// encontrando o indice
			if(listaLivro.get(index).getId().equals(getLivro().getId())) {
				// substituindo o objeto 
				listaLivro.set(index, getLivro());
				break;
			}
		}
		// limpando o objeto livro (para limpar o formulario)
		limpar();
	}
	
	public void editar(Livro livro) {
		Livro novo = new Livro();
		novo.setId(livro.getId());
		novo.setNome(livro.getNome());
		novo.setAnoLancamento(livro.getAnoLancamento());
		novo.setAutor(livro.getAutor());
		novo.setEditora(livro.getEditora());
		novo.setGenero(livro.getGenero());
		setLivro(novo);
	}
	
	public void limpar() {
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
