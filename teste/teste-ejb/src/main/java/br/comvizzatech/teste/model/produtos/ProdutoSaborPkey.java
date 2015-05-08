package br.comvizzatech.teste.model.produtos;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProdutoSaborPkey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6624542603670067897L;
	private Integer produto;
	private Integer sabor;

	public Integer getProduto() {
		return produto;
	}

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

	public Integer getSabor() {
		return sabor;
	}

	public void setSabor(Integer sabor) {
		this.sabor = sabor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((sabor == null) ? 0 : sabor.hashCode());
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
		ProdutoSaborPkey other = (ProdutoSaborPkey) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (sabor == null) {
			if (other.sabor != null)
				return false;
		} else if (!sabor.equals(other.sabor))
			return false;
		return true;
	}

}
