package br.comvizzatech.teste.view;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

@ManagedBean(name="dFView")
public class DFView {

	public void viewPedidoModal() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 720);
		options.put("contentWidth", 1280);

		RequestContext.getCurrentInstance().openDialog("pedido", options,
				null);
	}
	
	public void viewFechaConta() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 320);

		RequestContext.getCurrentInstance().openDialog("viewFechaConta", options,
				null);
	}

}
