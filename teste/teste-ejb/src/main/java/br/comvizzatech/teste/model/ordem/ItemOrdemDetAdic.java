package br.comvizzatech.teste.model.ordem;

import java.io.Serializable;
import javax.persistence.*;

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
	@JoinColumn(name = "\"ID_ITEM_ORDEM\"", referencedColumnName = "\"ID_ITEM_ORDEM\"")
	private ItemOrdem itemOrdem;

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

	public ItemOrdem getItemOrdem() {
		return itemOrdem;
	}

	public void setItemOrdem(ItemOrdem itemOrdem) {
		this.itemOrdem = itemOrdem;
	}

}