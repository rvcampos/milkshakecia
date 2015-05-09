package br.comvizzatech.teste.controller;

import javax.enterprise.context.Conversation;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.comvizzatech.teste.model.mesa.Mesa;
import br.comvizzatech.teste.service.MesaService;

//@ManagedBean(name = "mesaController")
//@RequestScoped
@Model
public class MesaController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private MesaService mesaService;


	public boolean abreFechaMesa(Mesa mesa) throws Exception {
		try {
			if(!mesaService.canDoAction(mesa))
			{
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Falha", "Inconsistência de dados para a mesa");
				facesContext.addMessage(null, m);
				return false;
			}
			if(mesa.getStatus())
			{
				mesaService.abreMesa(mesa);
				facesContext.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Sucesso",
						"Mesa aberta com sucesso"));
			}
			else
			{
				fechaMesa(mesa);
			}
			return true;
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Registration Unsuccessful");
			facesContext.addMessage(null, m);
			return false;
		}
	}
	
	public void fechaMesa(Mesa mesa) throws Exception {
		try {
			mesaService.fechaMesa(mesa);
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Sucesso",
					"Mesa fechada com sucesso"));
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Registration Unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Falha ao realizar ação em mesa";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

}
