package br.comvizzatech.teste.model.produtos;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "\"SABOR\"")
@Cacheable(true)
public class Sabor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1195691580436984333L;
	@Id
	@Column(name = "id_sabor", unique = true, nullable = false)
	private Integer idSabor;
	private String nome;

	public Integer getIdSabor() {
		return idSabor;
	}

	public void setIdSabor(Integer idSabor) {
		this.idSabor = idSabor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Sabor [idSabor=" + idSabor + ", nome=" + nome + "]";
	}

}
