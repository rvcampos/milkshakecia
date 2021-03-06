package br.comvizzatech.teste.model.ordem;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.comvizzatech.teste.model.historico.OrdemHistorico;

/**
 * The persistent class for the "ORDEM" database table.
 * 
 */
@Entity
@Table(name = "ordem")
public class Ordem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDER")
	private Long idOrdem;

	@Column(name = "DATA_ORDEM")
	private Timestamp dataOrdem;

	@Column(name = "DOCUMENTO_NOTA")
	private String documentoNota;

	@Column(name = "IND_VIAGEM")
	private Boolean indViagem;

	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "TIPO_PAGAMENTO")
	private Integer tipoPagamento;

	// bi-directional many-to-one association to ItemOrdem
	@OneToMany(mappedBy = "ordem", cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
	private List<ItemOrdem> itemOrdems;

	@Column(name="id_mesa")
	private Integer id_mesa;

	public Ordem() {
	}

	public Long getIdOrdem() {
		return this.idOrdem;
	}

	public void setIdOrdem(Long idOrdem) {
		this.idOrdem = idOrdem;
	}

	public Timestamp getDataOrdem() {
		return this.dataOrdem;
	}

	public void setDataOrdem(Timestamp dataOrdem) {
		this.dataOrdem = dataOrdem;
	}

	public String getDocumentoNota() {
		return this.documentoNota;
	}

	public void setDocumentoNota(String documentoNota) {
		this.documentoNota = documentoNota;
	}

	public Boolean getIndViagem() {
		return this.indViagem;
	}

	public void setIndViagem(Boolean indViagem) {
		this.indViagem = indViagem;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTipoPagamento() {
		return this.tipoPagamento;
	}

	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<ItemOrdem> getItemOrdems() {
		return this.itemOrdems;
	}

	public void setItemOrdems(List<ItemOrdem> itemOrdems) {
		this.itemOrdems = itemOrdems;
	}

	public ItemOrdem addItemOrdem(ItemOrdem itemOrdem) {
		if (getItemOrdems() == null) {
			setItemOrdems(new ArrayList<ItemOrdem>());
		}
		getItemOrdems().add(itemOrdem);
		itemOrdem.setOrdem(this);

		return itemOrdem;
	}

	public ItemOrdem removeItemOrdem(ItemOrdem itemOrdem) {
		getItemOrdems().remove(itemOrdem);
		itemOrdem.setOrdem(null);

		return itemOrdem;
	}

	public OrdemHistorico criaHistorico(Timestamp date) {
		OrdemHistorico ordh = new OrdemHistorico();
		ordh.setDtCriacao(date);
		ordh.setTipoPagamento(tipoPagamento);
		ordh.setDataOrdem(dataOrdem);
		ordh.setIndViagem(indViagem);
		ordh.setIdMesa(id_mesa);
		ordh.setIdOrdem(idOrdem);
		if(getItemOrdems() != null)
		{
			for(ItemOrdem ord : getItemOrdems())
			{
				ordh.addItemOrdemHistorico(ord.criaHistorico(date));
			}
		}
		return ordh;
	}

	public List<ItemOrdemDet> getAllItemOrdemDet() {
		List<ItemOrdemDet> detalhes = new ArrayList<ItemOrdemDet>();
		for (ItemOrdem itm : itemOrdems) {
			if (itm.getCancelado() == null || !itm.getCancelado()) {
				detalhes.addAll(itm.getItemOrdemDet());
			}
		}
		return detalhes;
	}

	public Integer getId_mesa() {
		return id_mesa;
	}

	public void setId_mesa(Integer id_mesa) {
		this.id_mesa = id_mesa;
	}

}