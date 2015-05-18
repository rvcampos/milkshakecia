package br.comvizzatech.teste.model.historico;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the item_ordem_det_historico database table.
 * 
 */
@Entity
@Table(name="item_ordem_det_historico")
@NamedQuery(name="ItemOrdemDetHistorico.findAll", query="SELECT i FROM ItemOrdemDetHistorico i")
public class ItemOrdemDetHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	private String detalhe;

	@Column(name="dt_criacao")
	private Timestamp dtCriacao;

	@Id
	@Column(name="id_item_ordem_det")
	private Long idItemOrdemDet;

	@Column(name="id_produto")
	private Integer idProduto;

	@Column(name="id_sabor")
	private Integer idSabor;

	@Column(name="id_tamanho")
	private Integer idTamanho;

	private Integer quantidade;

	//bi-directional many-to-one association to ItemOrdemHistorico
	@ManyToOne
	@JoinColumn(name="id_item_ordem", referencedColumnName="id_item_ordem")
	private ItemOrdemHistorico itemOrdemHistorico;

	public ItemOrdemDetHistorico() {
	}

	public String getDetalhe() {
		return this.detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public Timestamp getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Timestamp dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Long getIdItemOrdemDet() {
		return this.idItemOrdemDet;
	}

	public void setIdItemOrdemDet(Long idItemOrdemDet) {
		this.idItemOrdemDet = idItemOrdemDet;
	}

	public Integer getIdProduto() {
		return this.idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getIdSabor() {
		return this.idSabor;
	}

	public void setIdSabor(Integer idSabor) {
		this.idSabor = idSabor;
	}

	public Integer getIdTamanho() {
		return this.idTamanho;
	}

	public void setIdTamanho(Integer idTamanho) {
		this.idTamanho = idTamanho;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ItemOrdemHistorico getItemOrdemHistorico() {
		return this.itemOrdemHistorico;
	}

	public void setItemOrdemHistorico(ItemOrdemHistorico itemOrdemHistorico) {
		this.itemOrdemHistorico = itemOrdemHistorico;
	}

}