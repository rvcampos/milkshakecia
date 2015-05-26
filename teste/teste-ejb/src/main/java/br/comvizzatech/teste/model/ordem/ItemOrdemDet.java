package br.comvizzatech.teste.model.ordem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.comvizzatech.teste.model.produtos.Produto;
import br.comvizzatech.teste.model.produtos.ProdutoProdutoAdicional;
import br.comvizzatech.teste.model.produtos.ProdutoSabor;
import br.comvizzatech.teste.model.produtos.ProdutoTamanho;

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
	@JoinColumn(name="id_item_ordem",referencedColumnName="id_item_ordem")
	private ItemOrdem itemOrdem;

	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	private Produto produto;

	@Column(name = "ID_SABOR")
	private Integer idSabor;

	@Column(name = "ID_TAMANHO")
	private Integer idTamanho;

	@Column(name = "QUANTIDADE")
	private Integer quantidade;

	@OneToMany(mappedBy = "itemOrdemDet",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
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
	
	public String printProdutoDetalhes()
	{
		StringBuilder desc = new StringBuilder(produto.getNome());
		if (getIdTamanho() != null) {
			ProdutoTamanho prdTamanho = produto
					.getTamanhoInfoByTamanhoId(getIdTamanho());
			desc.append(" ").append(prdTamanho.getTamanho().getNome());
		}

		if (getIdSabor() != null) {
			ProdutoSabor prdSabor = produto.getSaborInfoBySaborId(getIdSabor());
			desc.append(" ").append(prdSabor.getSabor().getNome());
		}
		
		if (getItemDetAdic() != null && !getItemDetAdic().isEmpty()) {
			desc.append(" c/ ");
			boolean first = true;
			for(ItemOrdemDetAdic i : getItemDetAdic())
			{
				ProdutoProdutoAdicional prdSabor = produto.getProdAdInfoByProdAdicionalId(Integer.valueOf(i.getIdProdutoAdic()));
				if(!first)
				{
					desc.append(",");
				}
				desc.append("\n").append(prdSabor.getProdutoAdicional().getNome());
				first = false;
			}
		}
		return desc.toString();
	}
	
	public BigDecimal calculaPrecoProdutoUnit()
	{
		return calculaPrecoProdutoUnit(true);
	}
	
	public BigDecimal calculaPrecoProdutoUnit(boolean consideraAdicionais)
	{
		BigDecimal dec = BigDecimal.ZERO;
		if (produto.getPrecoPadrao() != null) {
			dec = produto.getPrecoPadrao();
		}

		if (getIdTamanho() != null) {
			ProdutoTamanho prdTamanho = produto
					.getTamanhoInfoByTamanhoId(getIdTamanho());
			if (prdTamanho.getPreco() != null) {
				dec = prdTamanho.getPreco();
			}
		}

		if (getIdSabor() != null) {
			ProdutoSabor prdSabor = produto.getSaborInfoBySaborId(getIdSabor());
			if (prdSabor.getPrecoAdicional() != null) {
				dec = dec.add(prdSabor.getPrecoAdicional());
			}
		}
		if(consideraAdicionais && getItemDetAdic() != null && !getItemDetAdic().isEmpty())
		{
			dec = dec.add(getPrecoAdicionais());
		}
		return dec;
	}

	public BigDecimal getPrecoAdicionais() {
		BigDecimal dec = BigDecimal.ZERO;
		double qtdAdicionaisCobrados = getQtdAdicionaisCobrados();
		if(qtdAdicionaisCobrados > 0.0d)
		{
			dec = dec.add(produto.getPrecoAdicional().multiply(BigDecimal.valueOf(qtdAdicionaisCobrados)));
		}
		return dec;
	}
	
	public double getQtdAdicionaisCobrados() {
		Short qtdAdIncl = produto.getQtdAdicionalIncluso();
		double qtd = 0;
		if(qtdAdIncl < getItemDetAdic().size())
		{
			qtd = getItemDetAdic().size() - qtdAdIncl;
		}
		return qtd;
	}
	
	public BigDecimal calculaPrecoProduto()
	{
		BigDecimal dec = BigDecimal.ZERO;
		if (produto.getPrecoPadrao() != null) {
			dec = produto.getPrecoPadrao();
		}

		if (getIdTamanho() != null) {
			ProdutoTamanho prdTamanho = produto
					.getTamanhoInfoByTamanhoId(getIdTamanho());
			if (prdTamanho.getPreco() != null) {
				dec = prdTamanho.getPreco();
			}
		}

		if (getIdSabor() != null) {
			ProdutoSabor prdSabor = produto.getSaborInfoBySaborId(getIdSabor());
			if (prdSabor.getPrecoAdicional() != null) {
				dec = dec.add(prdSabor.getPrecoAdicional());
			}
		}
		if(getItemDetAdic() != null && !getItemDetAdic().isEmpty())
		{
			dec = dec.add(getPrecoAdicionais());
		}
		dec = dec.multiply(BigDecimal.valueOf(quantidade));
		
		return dec;
	}

	public String getNomeComTamanho() {
		String nome = produto.getNome();
		if (produto.getCategoria().getNome().toLowerCase()
				.equals("milk shakes")) {
			nome = "Milkshake " + nome;
		}
		if(getIdTamanho() != null)
		{
			ProdutoTamanho prdTamanho = produto
					.getTamanhoInfoByTamanhoId(getIdTamanho());
			nome += " " + prdTamanho.getTamanho().getNome();
		}
		return nome;
	}

}