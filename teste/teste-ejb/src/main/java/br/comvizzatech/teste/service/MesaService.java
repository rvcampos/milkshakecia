package br.comvizzatech.teste.service;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.logging.log4j.Logger;

import br.comvizzatech.teste.model.historico.MesaHistorico;
import br.comvizzatech.teste.model.historico.MesaHistoricoPK;
import br.comvizzatech.teste.model.mesa.Mesa;
import br.comvizzatech.teste.model.ordem.Ordem;

@Stateless
public class MesaService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Mesa> mesaEventSrc;
	
	public Mesa getMesaById(Integer idMesa)
	{
		return em.find(Mesa.class, idMesa);
	}

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
		List<Ordem> ordens = findOrdemByMesaId(mesa.getIdMesa());
		if(ordens != null && !ordens.isEmpty())
		{
			
		}
		// TODO adicionar pedidos da mesa
		em.persist(hist);
	}
	
	private List<Ordem> findOrdemByMesaId(Integer mesaId)
	{
		return em
				.createQuery(
						"select o from Ordem o where o.idMesa = :idMesa")
						.setParameter("idMesa", mesaId).getResultList();
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
			log.error("fechaMesa", e);
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
