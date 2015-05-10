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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@IdClass(ProdutoSaborPkey.class)
@XmlRootElement
@Table(name = "PRODUTO_SABOR", schema = "milkshakecia")
@Cacheable(true)
public class ProdutoSabor implements Serializable{

	private static final long serialVersionUID = -643739859553578881L;
	@Id
	@OneToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	@Id
	@OneToOne
	@JoinColumn(name = "id_sabor")
	private Sabor sabor;

	@Column(name = "ativo")
	private Boolean isAtivo;
	
	@Column(name="preco_adicional")
	private BigDecimal precoAdicional;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	@Override
	public String toString() {
		return "ProdutoSabor [produto=" + produto.getNome() + ", sabor=" + sabor
				+ ", isAtivo=" + isAtivo + "]";
	}

	public BigDecimal getPrecoAdicional() {
		return precoAdicional;
	}

	public void setPrecoAdicional(BigDecimal precoAdicional) {
		this.precoAdicional = precoAdicional;
	}

}
