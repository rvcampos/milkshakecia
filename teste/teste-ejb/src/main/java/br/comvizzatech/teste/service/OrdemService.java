package br.comvizzatech.teste.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.logging.log4j.Logger;

import br.comvizzatech.teste.model.ordem.Ordem;

@Stateless
public class OrdemService {

	@Inject
	private Logger logger;

	@Inject
	private EntityManager em;

	public boolean criaOrdem(Ordem ord) {
		try {
			if (ord.getIdOrdem() != null) {
				em.merge(ord);
			} else {
				em.persist(ord);
			}
		} catch (Exception e) {
			logger.error("",e);
			return false;
		}
		return true;
	}

}