package br.comvizzatech.teste.model.produtos;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProdutoProdutoAdicionalPkey implements Serializable {

	private static final long serialVersionUID = -5550278986401940604L;
	private Integer produto;
	private Integer produtoAdicional;

	public Integer getProduto() {
		return produto;
	}

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

	public Integer getProdutoAdicional() {
		return produtoAdicional;
	}

	public void setProdutoAdicional(Integer produtoAdicional) {
		this.produtoAdicional = produtoAdicional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((produtoAdicional == null) ? 0 : produtoAdicional.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoProdutoAdicionalPkey other = (ProdutoProdutoAdicionalPkey) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (produtoAdicional == null) {
			if (other.produtoAdicional != null)
				return false;
		} else if (!produtoAdicional.equals(other.produtoAdicional))
			return false;
		return true;
	}

}
