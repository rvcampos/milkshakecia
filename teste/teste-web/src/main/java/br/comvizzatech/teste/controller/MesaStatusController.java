package br.comvizzatech.teste.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.comvizzatech.teste.model.mesa.Mesa;
import br.comvizzatech.teste.model.ordem.Ordem;
import br.comvizzatech.teste.service.MesaService;

@ManagedBean(name = "mesaStatusView")
// @Named("pedidosView")
@ViewScoped
public class MesaStatusController {

	@Inject
	private FacesContext facesContext;

	private Integer idMesa;
	private Mesa mesa;

	@Inject
	private MesaService service;

	@PostConstruct
	public void init() {
		if (facesContext.getExternalContext().getRequestParameterMap()
				.get("idMesa") != null) {
			this.idMesa = Integer.parseInt(facesContext.getExternalContext()
					.getRequestParameterMap().get("idMesa"));
			mesa = service.getMesaById(idMesa);
		}
	}
	
	public void buildPedidosView()
	{
		for(Ordem ordem : mesa.getPedidosMesa())
		{
			
		}
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
}
