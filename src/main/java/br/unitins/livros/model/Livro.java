package br.unitins.livros.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Livro implements Cloneable {
	private Integer id;

	@NotNull(message = "O nome não pode ser nulo.")
	private String nome;

	@NotNull(message = "A data não pode ser nula.")
	private LocalDate dataLancamento;
	
	@NotNull(message = "O preço não pode ser nulo.")
	private Double preco;
	
	@NotNull(message = "O estoque não pode ser nulo.")
	private Integer estoque;

	@NotNull(message = "A editora não pode ser nula.")
	private String editora;

	@NotNull(message = "O autor não pode ser nulo.")
	private Autor autor;

	public Livro getClone() {
		try {
			return (Livro) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	

}
