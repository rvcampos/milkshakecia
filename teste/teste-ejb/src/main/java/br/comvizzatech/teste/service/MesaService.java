package br.comvizzatech.teste.service;

import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.comvizzatech.teste.model.mesa.Mesa;
import br.comvizzatech.teste.model.mesa.MesaHistorico;
import br.comvizzatech.teste.model.mesa.MesaHistoricoPK;

@Stateless
public class MesaService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Mesa> mesaEventSrc;

	public void abreMesa(Mesa mesa) throws Exception {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		log.info("Abrindo mesa " + mesa.getIdMesa() + "/"
				+ timestamp.toString());
		mesa.setStatus(false);
		mesa.setDataAbertura(timestamp);
		em.merge(mesa);
		mesaEventSrc.fire(mesa);
	}

	private void createHistory(Mesa mesa, Timestamp dataFechamento) {
		MesaHistorico hist = new MesaHistorico();
		MesaHistoricoPK pk = new MesaHistoricoPK();
		pk.setIdMesa(mesa.getIdMesa());
		pk.setIndex(getIndexForMesaId(mesa));
		hist.setId(pk);
		hist.setDataAbertura(mesa.getDataAbertura());
		hist.setDesconto(mesa.getDesconto());
		hist.setDtFechamento(dataFechamento);
		// TODO adicionar pedidos da mesa
		em.persist(hist);
	}

	private long getIndexForMesaId(Mesa mesa) {
		long ret = 0L;

		Object singleResult = em
				.createQuery(
						"select max(e.id.index) from MesaHistorico e where e.id.idMesa = :idMesa")
				.setParameter("idMesa", mesa.getIdMesa()).getSingleResult();

		if (singleResult != null) {
			return (Long) singleResult + 1L;
		}

		return ret;
	}

	public void fechaMesa(Mesa mesa) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			log.info("Fechando mesa " + mesa.getIdMesa() + "/"
					+ timestamp.toString());
			createHistory(mesa, timestamp);
			mesa.setStatus(true);
			mesa.setDataAbertura(timestamp);
			em.merge(mesa);
		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "fechaMesa", e);
		}
		mesaEventSrc.fire(mesa);
	}
	public boolean canDoAction(Mesa mesa) {
		Mesa mesaDB = em.find(Mesa.class, mesa.getIdMesa());
		if(!mesaDB.getStatus().equals(mesa.getStatus()))
		{
			return false;
		}
		return true;
	}

}
