package br.comvizzatech.teste.model.produtos;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PRODUTO_PRODUTOS_ADICIONAIS")
@XmlRootElement
@IdClass(ProdutoProdutoAdicionalPkey.class)
@Cacheable(true)
public class ProdutoProdutoAdicional {

	@Id
	@OneToOne
	@JoinColumn(name = "ID_PRODUTO", insertable=false,updatable=false)
	private Produto produto;

	@Id
	@OneToOne
	@JoinColumn(name = "ID_PRODUTO_ADICIONAL", insertable=false,updatable=false)
	private ProdutoAdicional produtoAdicional;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoAdicional getProdutoAdicional() {
		return produtoAdicional;
	}

	public void setProdutoAdicional(ProdutoAdicional produtoAdicional) {
		this.produtoAdicional = produtoAdicional;
	}

}
