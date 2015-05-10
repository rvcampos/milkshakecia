package br.comvizzatech.teste.model.mesa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the "MESA" database table.
 * 
 */
@Entity
@Table(name="MESA")
@NamedQuery(name="Mesa.findAll", query="SELECT m FROM Mesa m")
public class Mesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_MESA")
	private Integer idMesa;

	@Column(name="DATA_ABERTURA")
	private Timestamp dataAbertura;

	@Column(name="DESCONTO")
	private BigDecimal desconto;

	@Column(name="STATUS")
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