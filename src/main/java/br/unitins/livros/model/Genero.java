package br.unitins.livros.model;

public enum Genero {
	
	ACAO(1, "Ação"), 
	ROMANCE(2, "Romance"), 
	TERROR(3, "Terror"), 
	COMEDIA(4, "Comédia"), 
	ADULTO(5, "Adulto");
	
	private int id;
	private String label;
	
	Genero(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	

}
