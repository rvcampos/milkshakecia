package br.comvizzatech.teste.model.mesa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.comvizzatech.teste.model.ordem.ItemOrdemDet;
import br.comvizzatech.teste.model.ordem.Ordem;

/**
 * The persistent class for the "MESA" database table.
 * 
 */
@Entity
@Table(name = "mesa")
@NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m")
public class Mesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_MESA")
	private Integer idMesa;

	@Column(name = "DATA_ABERTURA")
	private Timestamp dataAbertura;

	@Column(name = "DESCONTO")
	private BigDecimal desconto;

	@Column(name = "STATUS")
	private Boolean status;

	public Mesa() {
	}

	public Integer getIdMesa() {
		return this.idMesa;
	}

	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

	public Timestamp getDataAbertura() {
		return this.dataAbertura;
	}

	public void setDataAbertura(Timestamp dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public BigDecimal getDesconto() {
		return this.desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}