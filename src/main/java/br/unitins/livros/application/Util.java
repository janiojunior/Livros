package br.unitins.livros.application;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {
	
	public static void addMessage(String message) {
		FacesMessage fm = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

}
