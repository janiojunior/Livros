package br.unitins.livros.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.model.Venda;

@Named
@ViewScoped
public class DetalheVendaController implements Serializable {
	
	private static final long serialVersionUID = 7304575574104806720L;
	private Venda venda;

	public DetalheVendaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		// mantem o objeto no flashscoped enquanto nao renovar a requisicao
		flash.keep("vendaFlash");
		setVenda( (Venda)flash.get("vendaFlash") );
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
}
