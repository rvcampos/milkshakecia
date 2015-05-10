package br.comvizzatech.teste.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "dFView")
public class DFView {

	public void viewPedidoModal(Integer idMesa) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 720);
		options.put("contentWidth", 1280);
		options.put("includeViewParams", true);

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		if (idMesa != null) {
			List<String> values = new ArrayList<String>();
			values.add(idMesa.toString());
			params.put("idMesa", values);
		}

		RequestContext.getCurrentInstance().openDialog("pedido", options,
				params);
	}

	public void viewPedidoModal() {
		viewPedidoModal(null);
	}

	public void viewFechaConta() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 320);

		RequestContext.getCurrentInstance().openDialog("viewFechaConta",
				options, null);
	}

}
