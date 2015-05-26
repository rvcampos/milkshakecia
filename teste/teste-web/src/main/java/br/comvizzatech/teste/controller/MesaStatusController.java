package br.comvizzatech.teste.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.comvizzatech.teste.model.FormaPagamento;
import br.comvizzatech.teste.model.mesa.Mesa;
import br.comvizzatech.teste.model.ordem.ItemOrdemDet;
import br.comvizzatech.teste.model.ordem.Ordem;
import br.comvizzatech.teste.service.MesaService;
import br.comvizzatech.teste.service.OrdemService;

@ManagedBean(name = "mesaStatusView")
// @Named("pedidosView")
@ViewScoped
public class MesaStatusController {

	@Inject
	private FacesContext facesContext;
	@Inject
	private OrdemService ordemService;
	@Inject
	private MesaService mesaService;

	private Integer idMesa;
	private Mesa mesa;
	private Integer pgto;
	private Double valorTotal = null;

	private List<ItemOrdemDet> produtos;

	@Inject
	private MesaService service;

	private static final List<FormaPagamento> pagtos = new ArrayList<FormaPagamento>();

	@PostConstruct
	public void init() {
		if (facesContext.getExternalContext().getRequestParameterMap()
				.get("idMesa") != null) {
			this.idMesa = Integer.parseInt(facesContext.getExternalContext()
					.getRequestParameterMap().get("idMesa"));
			mesa = service.getMesaById(idMesa);
		}
		if (pagtos.isEmpty()) {
			pagtos.add(new FormaPagamento(0, "Dinheiro"));
			pagtos.add(new FormaPagamento(1, "Débito"));
			pagtos.add(new FormaPagamento(2, "Crédito"));
			pagtos.add(new FormaPagamento(3, "Voucher"));
		}
	}

	public List<ItemOrdemDet> buildPedidosView() {
		if (produtos == null || produtos.isEmpty()) {
			produtos = new ArrayList<ItemOrdemDet>();
			List<Ordem> ordensByMesa = ordemService.getOrdensByMesa(idMesa);
			if (ordensByMesa != null) {
				for (Ordem ordem : ordensByMesa) {
					produtos.addAll(ordem.getAllItemOrdemDet());
				}
			}
		}
		return produtos;
	}

	public boolean emiteCupom() {
		if(produtos == null || produtos.isEmpty())
		{
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Mesa sem pedidos", "Falha ao fechar mesa");
			return true;
		}
		return ECFHelper.emiteCupom(mesa, produtos,pgto,valorTotal);
	}
	
	public boolean fechaMesa()
	{
		try {
			if(emiteCupom())
			{
				mesaService.fechaMesa(mesa);
				setProdutos(null);
				RequestContext.getCurrentInstance().closeDialog("viewFechaConta");
				return true;
			}
			else
			{
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Falha na emissão de cupom", "Falha ao fechar mesa");
				facesContext.addMessage(null, m);
			}
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Falha ao fechar mesa");
			facesContext.addMessage(null, m);
		}
		return false;
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

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<FormaPagamento> getPagtos() {
		return MesaStatusController.pagtos;
	}

	public Integer getPgto() {
		return pgto;
	}

	public void setPgto(Integer pgto) {
		this.pgto = pgto;
	}

	public List<ItemOrdemDet> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ItemOrdemDet> produtos) {
		this.produtos = produtos;
	}
	
	public BigDecimal totalPedidos()
	{
		BigDecimal total = BigDecimal.ZERO;
		if(produtos != null &&!produtos.isEmpty())
		{
			for (ItemOrdemDet produto : produtos) {
				total = total.add(produto.calculaPrecoProduto());
			}
		}
		
		return total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
}
