package br.unitins.livros.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.application.Session;
import br.unitins.livros.application.Util;
import br.unitins.livros.dao.LivroDAO;
import br.unitins.livros.model.ItemVenda;
import br.unitins.livros.model.Livro;

@Named
@ViewScoped
public class VendaController implements Serializable {

	private static final long serialVersionUID = -637345764345173797L;
	private Integer tipoFiltro;
	private String filtro;
	private List<Livro> listaLivro;

	public void pesquisar() {
		LivroDAO dao = new LivroDAO();
		setListaLivro(dao.getByNome(filtro));
	}
	
	public void comprar(Livro livro) {
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("carrinho");
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();
		
		ItemVenda item = new ItemVenda();
		item.setLivro(livro);
		item.setValor(livro.getPreco());
		item.setQuantidade(1);
		
		// verificando se contem o livro no carrinho para alterar a quantidade
		if (carrinho.contains(item)) {
			int index = carrinho.indexOf(item);
			int quantidade = carrinho.get(index).getQuantidade();
			carrinho.get(index).setQuantidade(quantidade + 1);
			carrinho.get(index).setValor(livro.getPreco());
		} else {
			carrinho.add(item);
		}
		
		// cria/ atualiza o objeto na sessao
		Session.getInstance().set("carrinho", carrinho);
		
		Util.addMessageInfo("Livro adicionado na sessão");
		
	}

	public Integer getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
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
