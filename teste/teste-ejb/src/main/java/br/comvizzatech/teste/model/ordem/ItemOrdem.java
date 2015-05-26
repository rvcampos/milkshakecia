package br.comvizzatech.teste.model.ordem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.comvizzatech.teste.model.historico.ItemOrdemHistorico;
import br.comvizzatech.teste.model.historico.OrdemHistorico;

@Entity
@Table(name = "item_ordem",schema="milkshakecia")
public class ItemOrdem {

	@Id
	@Column(name="id_item_ordem")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idItemOrdem;

	@ManyToOne
	@JoinColumn(name = "id_ordem", referencedColumnName = "ID_ORDER")
	private Ordem ordem;

	@Column(name = "cancelado")
	private Boolean cancelado;

	@Column(name = "id_combo")
	private Integer idCombo;

	@OneToMany(mappedBy = "itemOrdem", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ItemOrdemDet> itemOrdemDet;

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

	public void addItemOrdemDet(ItemOrdemDet det) {
		if (this.getItemOrdemDet() == null) {
			this.setItemOrdemDet(new ArrayList<ItemOrdemDet>());
		}
		det.setItemOrdem(this);
		getItemOrdemDet().add(det);
	}

	public List<ItemOrdemDet> getItemOrdemDet() {
		return itemOrdemDet;
	}

	public void setItemOrdemDet(List<ItemOrdemDet> itemOrdemDet) {
		this.itemOrdemDet = itemOrdemDet;
	}
	
	public String printProdutoInfo()
	{
		StringBuilder bldr = new StringBuilder();
		
		return bldr.toString();
	}

	public ItemOrdemHistorico criaHistorico(Timestamp date) {
		ItemOrdemHistorico hist = new ItemOrdemHistorico();
		hist.setCancelado(cancelado);
		hist.setDtCriacao(date);
		hist.setIdCombo(getIdCombo());
		hist.setIdItemOrdem(idItemOrdem);
		if(getItemOrdemDet() != null)
		{
			for(ItemOrdemDet det:getItemOrdemDet())
			{
				hist.addItemOrdemDetHistorico(det.criaHistorico(date));
			}
		}
		return hist;
	}
}
