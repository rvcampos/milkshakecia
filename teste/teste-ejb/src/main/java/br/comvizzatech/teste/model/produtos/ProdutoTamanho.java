package br.comvizzatech.teste.model.produtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "\"PRODUTO_TAMANHO\"")
@XmlRootElement
@IdClass(ProdutoTamanhoPkey.class)
@Cacheable(true)
public class ProdutoTamanho implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4236344842237540196L;
	@Id
	@OneToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	@Id
	@OneToOne
	@JoinColumn(name = "id_tamanho")
	private Tamanho tamanho;
	@Column(precision=2)
	private BigDecimal preco;
	private Integer medida;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getMedida() {
		return medida;
	}

	public void setMedida(Integer medida) {
		this.medida = medida;
	}
	
	public boolean printPreco()
	{
		return getPreco() != null && BigDecimal.ONE.compareTo(BigDecimal.ZERO) == 1;
	}
	
	public String getPrecoStr()
	{
		return printPreco() ? "R$ " + getPreco().toString() : ""; 
	}

}
