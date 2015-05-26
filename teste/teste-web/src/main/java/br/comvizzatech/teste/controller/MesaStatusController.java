package br.comvizzatech.teste.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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

	public void emiteCupom() {
		ECFHelper.emiteCupom(mesa, produtos,pgto,0d);
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
}
