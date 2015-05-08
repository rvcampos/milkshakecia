package br.comvizzatech.teste.model.mesa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * The primary key class for the "MESA_HISTORICO" database table.
 * 
 */
@Embeddable
public class MesaHistoricoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"ID_MESA\"")
	private Integer idMesa;

	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long index;

	public MesaHistoricoPK() {
	}
	public Integer getIdMesa() {
		return this.idMesa;
	}
	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}
	public Long getIndex() {
		return this.index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MesaHistoricoPK)) {
			return false;
		}
		MesaHistoricoPK castOther = (MesaHistoricoPK)other;
		return 
			this.idMesa.equals(castOther.idMesa)
			&& this.index.equals(castOther.index);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idMesa.hashCode();
		hash = hash * prime + this.index.hashCode();
		
		return hash;
	}
}