package br.unitins.livros.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.application.Session;
import br.unitins.livros.model.ItemVenda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable{
	private List<ItemVenda> listaItemVenda;
	
	public CarrinhoController() {
		listaItemVenda = (List<ItemVenda>) Session.getInstance().get("carrinho");
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
	
}
