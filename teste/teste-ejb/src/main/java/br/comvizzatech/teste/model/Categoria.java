package br.comvizzatech.teste.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.comvizzatech.teste.model.produtos.Produto;

@Entity
@XmlRootElement
@Table(name = "\"CATEGORIA\"", schema = "milkshakecia")
public class Categoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2490453700400262775L;

	/**
	 * 
	 */

	@Id
	@Column(name = "id_categoria")
	private Integer idCategoria;

	@Column
	private String nome;

	@Column(name = "mostra_tela")
	private Boolean isMostraTela;

	@OneToMany(mappedBy = "categoria")
	private List<Produto> produtos;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getIsMostraTela() {
		return isMostraTela;
	}

	public void setIsMostraTela(Boolean isMostraTela) {
		this.isMostraTela = isMostraTela;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nome=" + nome
				+ ", isMostraTela=" + isMostraTela + "]";
	}

}
