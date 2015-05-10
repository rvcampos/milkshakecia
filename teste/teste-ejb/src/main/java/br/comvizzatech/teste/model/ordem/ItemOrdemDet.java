package br.comvizzatech.teste.model.ordem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.comvizzatech.teste.model.produtos.Produto;

/**
 * The persistent class for the "ITEM_ORDEM_DET" database table.
 * 
 */
@Entity
@Table(name = "ITEM_ORDEM_DET")
public class ItemOrdemDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_ITEM_ORDEM_DET")
	private Long idItemOrdemDet;

	@Column(name = "DETALHE")
	private String detalhe;

	@ManyToOne
	@JoinColumn(name="ID_ITEM_ORDEM",referencedColumnName="ID_ITEM_ORDEM")
	private ItemOrdem itemOrdem;

	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO", referencedColumnName = "id_produto")
	private Produto produto;

	@Column(name = "ID_SABOR")
	private Integer idSabor;

	@Column(name = "ID_TAMANHO")
	private Integer idTamanho;

	@Column(name = "QUANTIDADE")
	private Integer quantidade;

	@OneToMany(mappedBy = "itemOrdemDet",cascade=CascadeType.ALL)
	private List<ItemOrdemDetAdic> itemDetAdic;

	public ItemOrdemDet() {
	}

	public Long getIdItemOrdemDet() {
		return this.idItemOrdemDet;
	}

	public void setIdItemOrdemDet(Long idItemOrdemDet) {
		this.idItemOrdemDet = idItemOrdemDet;
	}

	public String getDetalhe() {
		return this.detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
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

	public ItemOrdem getItemOrdem() {
		return itemOrdem;
	}

	public void setItemOrdem(ItemOrdem itemOrdem) {
		this.itemOrdem = itemOrdem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<ItemOrdemDetAdic> getItemDetAdic() {
		return itemDetAdic;
	}

	public void setItemDetAdic(List<ItemOrdemDetAdic> itemDetAdic) {
		this.itemDetAdic = itemDetAdic;
	}
	
	public void addItemDetAdic(ItemOrdemDetAdic itemOrdemDetAdic)
	{
		if(this.itemDetAdic == null)
		{
			this.itemDetAdic = new ArrayList<ItemOrdemDetAdic>();
		}
		itemOrdemDetAdic.setItemOrdemDet(this);
		this.itemDetAdic.add(itemOrdemDetAdic);
	}

}