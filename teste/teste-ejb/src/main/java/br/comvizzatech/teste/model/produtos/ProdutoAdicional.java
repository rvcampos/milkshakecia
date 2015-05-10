package br.comvizzatech.teste.model.produtos;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "PRODUTO_ADICIONAL" database table.
 * 
 */
@Entity
@Table(name="PRODUTO_ADICIONAL")
@NamedQuery(name="ProdutoAdicional.findAll", query="SELECT p FROM ProdutoAdicional p")
@Cacheable(true)
public class ProdutoAdicional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_PRODUTO_ADICIONAL")
	private Integer idProdutoAdicional;

	@Column(name="NOME")
	private String nome;

	public ProdutoAdicional() {
	}

	public Integer getIdProdutoAdicional() {
		return this.idProdutoAdicional;
	}

	public void setIdProdutoAdicional(Integer idProdutoAdicional) {
		this.idProdutoAdicional = idProdutoAdicional;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}