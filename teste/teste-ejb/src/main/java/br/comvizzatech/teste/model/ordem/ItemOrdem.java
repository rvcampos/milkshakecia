package br.comvizzatech.teste.model.ordem;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"ITEM_ORDEM\"")
public class ItemOrdem {

	@Id
	@Column(name = "\"ID_ITEM_ORDEM\"")
	private Long idItemOrdem;

	@ManyToOne
	@JoinColumn(name = "\"ID_ORDEM\"", referencedColumnName = "\"ID_ORDER\"")
	private Ordem ordem;

	@Column(name = "\"CANCELADO\"")
	private Boolean cancelado;

	@Column(name = "\"ID_COMBO\"")
	private Integer idCombo;
	
	@OneToMany(mappedBy="itemOrdem",cascade=CascadeType.ALL)
	private List<ItemOrdemDetAdic> itemOrdemDetAdic;

	public Long getIdItemOrdem() {
		return idItemOrdem;
	}

	public void setIdItemOrdem(Long idItemOrdem) {
		this.idItemOrdem = idItemOrdem;
	}

	public Ordem getOrdem() {
		return ordem;
	}

	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}

	public Boolean getCancelado() {
		return cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Integer getIdCombo() {
		return idCombo;
	}

	public void setIdCombo(Integer idCombo) {
		this.idCombo = idCombo;
	}
}
