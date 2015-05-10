package br.comvizzatech.teste.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.comvizzatech.teste.model.ordem.Ordem;

@Stateless
public class OrdemService {

	@Inject
	private Logger log;

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
			return false;
		}
		finally
		{
			
		}
		return true;
	}

}