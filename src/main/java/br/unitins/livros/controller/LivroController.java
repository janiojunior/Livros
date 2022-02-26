package br.unitins.livros.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.application.Util;
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
		
		Util.addMessage("Inclusão realizada com sucesso.");
	}
	
	public void alterar() {
		int index = listaLivro.indexOf(getLivro());
		listaLivro.set(index, getLivro());
		
		
//		for (int index = 0; index < listaLivro.size(); index++) {
//			// encontrando o indice
//			if(listaLivro.get(index).getId().equals(getLivro().getId())) {
//				// substituindo o objeto 
//				listaLivro.set(index, getLivro());
//				break;
//			}
//		}
		// limpando o objeto livro (para limpar o formulario)
		limpar();
		Util.addMessage("Alteração realizada com sucesso.");
	}
	
	public void excluir() {
		excluir(getLivro());
		limpar();
	}
	
	public void excluir(Livro livro) {
		listaLivro.remove(livro);
		Util.addMessage("Exclusão realizada com sucesso.");
	}
	
	public void editar(Livro livro) {
		setLivro(livro.getClone());
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
