package br.comvizzatech.teste.model.historico;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the ordem_historico database table.
 * 
 */
@Entity
@Table(name = "ordem_historico")
@NamedQuery(name = "OrdemHistorico.findAll", query = "SELECT o FROM OrdemHistorico o")
public class OrdemHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_order")
	private Long idOrdem;

	@Column(name = "data_ordem")
	private Timestamp dataOrdem;

	@Column(name = "documento_nota")
	private String documentoNota;

	@Column(name = "dt_criacao")
	private Timestamp dtCriacao;

	@Column(name = "ind_viagem")
	private Boolean indViagem;

	private Integer status;

	@Column(name = "tipo_pagamento")
	private Integer tipoPagamento;

	@Column(name = "id_mesa")
	private Integer idMesa;

	// bi-directional many-to-one association to ItemOrdemHistorico
	@OneToMany(mappedBy = "ordemHistorico")
	private List<ItemOrdemHistorico> itemOrdemHistoricos;

	public OrdemHistorico() {
	}

	public Timestamp getDataOrdem() {
		return this.dataOrdem;
	}

	public void setDataOrdem(Timestamp dataOrdem) {
		this.dataOrdem = dataOrdem;
	}

	public String getDocumentoNota() {
		return this.documentoNota;
	}

	public void setDocumentoNota(String documentoNota) {
		this.documentoNota = documentoNota;
	}

	public Timestamp getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Timestamp dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Boolean getIndViagem() {
		return this.indViagem;
	}

	public void setIndViagem(Boolean indViagem) {
		this.indViagem = indViagem;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTipoPagamento() {
		return this.tipoPagamento;
	}

	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<ItemOrdemHistorico> getItemOrdemHistoricos() {
		return this.itemOrdemHistoricos;
	}

	public void setItemOrdemHistoricos(
			List<ItemOrdemHistorico> itemOrdemHistoricos) {
		this.itemOrdemHistoricos = itemOrdemHistoricos;
	}

	public ItemOrdemHistorico addItemOrdemHistorico(
			ItemOrdemHistorico itemOrdemHistorico) {
		getItemOrdemHistoricos().add(itemOrdemHistorico);
		itemOrdemHistorico.setOrdemHistorico(this);

		return itemOrdemHistorico;
	}

	public ItemOrdemHistorico removeItemOrdemHistorico(
			ItemOrdemHistorico itemOrdemHistorico) {
		getItemOrdemHistoricos().remove(itemOrdemHistorico);
		itemOrdemHistorico.setOrdemHistorico(null);

		return itemOrdemHistorico;
	}

	public Long getIdOrdem() {
		return idOrdem;
	}

	public void setIdOrdem(Long idOrdem) {
		this.idOrdem = idOrdem;
	}

	public Integer getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

}