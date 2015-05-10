package br.comvizzatech.teste.model.mesa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the "MESA_HISTORICO" database table.
 * 
 */
@Entity
@Table(name="MESA_HISTORICO")
@NamedQuery(name="MesaHistorico.findAll", query="SELECT m FROM MesaHistorico m")
public class MesaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MesaHistoricoPK id;

	@Column(name="DATA_ABERTURA")
	private Timestamp dataAbertura;

	@Column(name="DESCONTO")
	private BigDecimal desconto;

	@Column(name="dt_fechamento")
	private Timestamp dtFechamento;

	public MesaHistorico() {
	}

	public MesaHistoricoPK getId() {
		return this.id;
	}

	public void setId(MesaHistoricoPK id) {
		this.id = id;
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

	public Timestamp getDtFechamento() {
		return this.dtFechamento;
	}

	public void setDtFechamento(Timestamp dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

}