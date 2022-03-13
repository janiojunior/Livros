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
public class LivroController implements Serializable {
	
	private static final long serialVersionUID = 1258970559285652391L;
	private Livro livro;
	private List<Livro> listaLivro;
	private int cont = 1;
	
	private boolean validarCampos() {
		// verificar se estah vazio ou nullo
		if (getLivro().getNome().isBlank()) {
			Util.addMessageError("O nome do livro deve ser informado.");
			return false;
		}
		if (getLivro().getNome().trim().length() < 2) {
			Util.addMessageError("O nome do livro deve ter pelo menos 2 caracteres.");
			return false;
		}
		return true;
	}
	
	public void incluir() {
		if (!validarCampos())
			return;
		
		getLivro().setId(cont++);
		getListaLivro().add(getLivro());
		livro = null;
		
		Util.addMessageInfo("Inclusão realizada com sucesso.");
	}
	
	public void alterar() {
		if (!validarCampos())
			return;
		
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
		Util.addMessageInfo("Alteração realizada com sucesso.");
	}
	
	public void excluir() {
		excluir(getLivro());
		limpar();
	}
	
	public void excluir(Livro livro) {
		listaLivro.remove(livro);
		Util.addMessageInfo("Exclusão realizada com sucesso.");
	}
	
	public void editar(Livro livro) {
		setLivro(livro.getClone());
	}
	
	public void limpar() {
		livro = null;
	}
	
	public Genero[] getListaGenero() {
		return Genero.values();
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

	public List<Livro> getListaLivro() {
		if (listaLivro == null)
			listaLivro = new ArrayList<Livro>();
		return listaLivro;
	}

	public void setListaLivro(List<Livro> listaLivro) {
		this.listaLivro = listaLivro;
	}
	
}
