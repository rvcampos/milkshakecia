package br.comvizzatech.teste.model.historico;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the item_ordem_historico database table.
 * 
 */
@Entity
@Table(name = "item_ordem_historico")
@NamedQuery(name = "ItemOrdemHistorico.findAll", query = "SELECT i FROM ItemOrdemHistorico i")
public class ItemOrdemHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_item_ordem")
	private Long idItemOrdem;

	private Boolean cancelado;

	@Column(name = "dt_criacao")
	private Timestamp dtCriacao;

	@Column(name = "id_combo")
	private Integer idCombo;

	// bi-directional many-to-one association to OrdemHistorico
	@ManyToOne
	@JoinColumn(name = "id_ordem", referencedColumnName = "id_order")
	private OrdemHistorico ordemHistorico;

	// bi-directional many-to-one association to ItemOrdemDetHistorico
	@OneToMany(mappedBy = "itemOrdemHistorico")
	private List<ItemOrdemDetHistorico> itemOrdemDetHistoricos;

	public ItemOrdemHistorico() {
	}

	public Boolean getCancelado() {
		return this.cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Timestamp getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Timestamp dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdCombo() {
		return this.idCombo;
	}

	public void setIdCombo(Integer idCombo) {
		this.idCombo = idCombo;
	}

	public OrdemHistorico getOrdemHistorico() {
		return this.ordemHistorico;
	}

	public void setOrdemHistorico(OrdemHistorico ordemHistorico) {
		this.ordemHistorico = ordemHistorico;
	}

	public List<ItemOrdemDetHistorico> getItemOrdemDetHistoricos() {
		return this.itemOrdemDetHistoricos;
	}

	public void setItemOrdemDetHistoricos(
			List<ItemOrdemDetHistorico> itemOrdemDetHistoricos) {
		this.itemOrdemDetHistoricos = itemOrdemDetHistoricos;
	}

	public ItemOrdemDetHistorico addItemOrdemDetHistorico(
			ItemOrdemDetHistorico itemOrdemDetHistorico) {
		if(getItemOrdemDetHistoricos() == null)
		{
			setItemOrdemDetHistoricos(new ArrayList<ItemOrdemDetHistorico>());
		}
		getItemOrdemDetHistoricos().add(itemOrdemDetHistorico);
		itemOrdemDetHistorico.setItemOrdemHistorico(this);

		return itemOrdemDetHistorico;
	}

	public ItemOrdemDetHistorico removeItemOrdemDetHistorico(
			ItemOrdemDetHistorico itemOrdemDetHistorico) {
		getItemOrdemDetHistoricos().remove(itemOrdemDetHistorico);
		itemOrdemDetHistorico.setItemOrdemHistorico(null);

		return itemOrdemDetHistorico;
	}

	public Long getIdItemOrdem() {
		return idItemOrdem;
	}

	public void setIdItemOrdem(Long idItemOrdem) {
		this.idItemOrdem = idItemOrdem;
	}

}