package br.comvizzatech.teste.model.produtos;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.comvizzatech.teste.model.Categoria;

@Entity
@XmlRootElement
@Table(name = "\"PRODUTO\"")
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6482017018057900807L;
	@Id
	@Column(name = "id_produto", unique = true, nullable = false)
	private Integer idProduto;
	@NotNull
	private String nome;
	@Column(name = "preco_padrao", precision = 3, scale = 2)
	private BigDecimal precoPadrao;
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false, insertable = false, updatable = false)
	private Categoria categoria;
	@OneToMany(fetch = EAGER)
	@OrderBy("sabor")
	@JoinColumn(name = "id_Produto", nullable = false, insertable = false, updatable = false, referencedColumnName = "id_produto")
	private List<ProdutoSabor> sabores;
	@OneToMany(fetch = EAGER)
	@OrderBy("tamanho")
	@JoinColumn(name = "id_Produto", nullable = false, insertable = false, updatable = false, referencedColumnName = "id_produto")
	private List<ProdutoTamanho> tamanhos;

	@Column(name = "qtd_adicional_incluso")
	private Short qtdAdicionalIncluso;

	@Column(name = "\"preco_adicional\"")
	private BigDecimal precoAdicional;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPrecoPadrao() {
		return precoPadrao;
	}

	public void setPrecoPadrao(BigDecimal precoPadrao) {
		this.precoPadrao = precoPadrao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<ProdutoSabor> getSabores() {
		return sabores;
	}

	public void setSabores(List<ProdutoSabor> sabores) {
		this.sabores = sabores;
	}

	public List<ProdutoTamanho> getTamanhos() {
		return tamanhos;
	}

	public void setTamanhos(List<ProdutoTamanho> tamanhos) {
		this.tamanhos = tamanhos;
	}

	public Short getQtdAdicionalIncluso() {
		return qtdAdicionalIncluso;
	}

	public void setQtdAdicionalIncluso(Short qtdAdicionalIncluso) {
		this.qtdAdicionalIncluso = qtdAdicionalIncluso;
	}

	public BigDecimal getPrecoAdicional() {
		return precoAdicional;
	}

	public void setPrecoAdicional(BigDecimal precoAdicional) {
		this.precoAdicional = precoAdicional;
	}
	
	public ProdutoTamanho getTamanhoInfoByTamanho(Tamanho tamanho)
	{
		if(tamanho != null && this.getTamanhos() != null)
		{
			for(ProdutoTamanho produtoTamanho : this.getTamanhos())
			{
				if(produtoTamanho.getTamanho().getIdTamanho().equals(tamanho.getIdTamanho()))
				{
					return produtoTamanho;
				}
			}
		}
		return null;
	}
	
	public ProdutoSabor getSaborInfoBySabor(Sabor sabor)
	{
		if(sabor != null && this.getSabores() != null)
		{
			for(ProdutoSabor produtoSabor : this.getSabores())
			{
				if(produtoSabor.getSabor().getIdSabor().equals(sabor.getIdSabor()))
				{
					return produtoSabor;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nome=" + nome
				+ ", precoPadrao=" + precoPadrao + ", categoria=" + categoria
				+ ", sabores=" + (sabores != null ? sabores.size() : 0)
				+ ", tamanhos=" + (tamanhos != null ? tamanhos.size() : 0)
				+ ", qtdAdicionalIncluso=" + qtdAdicionalIncluso
				+ ", precoAdicional=" + precoAdicional + "]";
	}
}
