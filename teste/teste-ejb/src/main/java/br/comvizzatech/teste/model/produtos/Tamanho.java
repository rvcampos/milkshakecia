package br.comvizzatech.teste.model.produtos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "\"TAMANHO\"", schema = "milkshakecia")
public class Tamanho implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1891258170559046995L;
	@Id
	@Column(name = "id_tamanho", unique = true, nullable = false)
	private Integer idTamanho;
	@Column
	private String nome;
	@Column
	private String sigla;

	public Integer getIdTamanho() {
		return idTamanho;
	}

	public void setIdTamanho(Integer idTamanho) {
		this.idTamanho = idTamanho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return "Tamanho [idTamanho=" + idTamanho + ", nome=" + nome
				+ ", sigla=" + sigla + "]";
	}
}
