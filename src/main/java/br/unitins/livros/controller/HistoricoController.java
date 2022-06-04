package br.unitins.livros.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.livros.application.Session;
import br.unitins.livros.dao.VendaDAO;
import br.unitins.livros.model.Usuario;
import br.unitins.livros.model.Venda;

@Named
@ViewScoped
public class HistoricoController implements Serializable{

	private static final long serialVersionUID = 8868841038332206274L;
	private List<Venda> listaVenda;
	
	public HistoricoController() {
	}

	public List<Venda> getListaVenda() {
		Usuario usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		if (usuarioLogado == null) {
			listaVenda = new ArrayList<Venda>();
		} else {
			if (listaVenda == null) {
				VendaDAO dao = new VendaDAO();
				listaVenda = dao.getByUsuario(usuarioLogado);
				if (listaVenda == null)
					listaVenda = new ArrayList<Venda>();
			}
		}
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
	
	
	
}
