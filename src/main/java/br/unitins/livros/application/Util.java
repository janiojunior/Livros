package br.unitins.livros.application;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Util {

	public static void addMessageInfo(String message) {
		addMessage(message, FacesMessage.SEVERITY_INFO);
	}

	public static void addMessageWarn(String message) {
		addMessage(message, FacesMessage.SEVERITY_WARN);
	}

	public static void addMessageError(String message) {
		addMessage(message, FacesMessage.SEVERITY_ERROR);
	}

	private static void addMessage(String message, Severity severity) {
		FacesMessage fm = new FacesMessage(severity, message, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	public static void redirect(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
