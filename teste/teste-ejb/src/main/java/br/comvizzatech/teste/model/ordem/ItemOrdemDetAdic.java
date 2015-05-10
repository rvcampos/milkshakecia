package br.comvizzatech.teste.model.ordem;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the "ITEM_ORDEM_DET_ADIC" database table.
 * 
 */
@Entity
@Table(name = "\"ITEM_ORDEM_DET_ADIC\"")
@NamedQuery(name = "ItemOrdemDetAdic.findAll", query = "SELECT i FROM ItemOrdemDetAdic i")
public class ItemOrdemDetAdic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ID_ITEM_ORDEM_DET_ADIC\"")
	private Long idItemOrdemDetAdic;

	@ManyToOne
	@JoinColumn(name = "\"ID_ITEM_ORDEM_DET\"", referencedColumnName = "\"ID_ITEM_ORDEM_DET\"")
	private ItemOrdemDet itemOrdemDet;

	@Column(name = "\"ID_PRODUTO_ADIC\"")
	private Integer idProdutoAdic;

	public ItemOrdemDetAdic() {
	}

	public Long getIdItemOrdemDetAdic() {
		return this.idItemOrdemDetAdic;
	}

	public void setIdItemOrdemDetAdic(Long idItemOrdemDetAdic) {
		this.idItemOrdemDetAdic = idItemOrdemDetAdic;
	}

	public Integer getIdProdutoAdic() {
		return this.idProdutoAdic;
	}

	public void setIdProdutoAdic(Integer idProdutoAdic) {
		this.idProdutoAdic = idProdutoAdic;
	}

	public ItemOrdemDet getItemOrdemDet() {
		return itemOrdemDet;
	}

	public void setItemOrdemDet(ItemOrdemDet itemOrdemDet) {
		this.itemOrdemDet = itemOrdemDet;
	}

}