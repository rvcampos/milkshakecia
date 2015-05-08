/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.comvizzatech.teste.data.rep;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.comvizzatech.teste.model.mesa.Mesa;

@ApplicationScoped
public class MesaRepository {

    @Inject
    private EntityManager em;

    public Mesa findById(Integer id) {
        return em.find(Mesa.class, id);
    }

    public Mesa findByStatus(String status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Mesa> criteria = cb.createQuery(Mesa.class);
        Root<Mesa> member = criteria.from(Mesa.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(member).where(cb.equal(member.get("status"), status));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Mesa> findAllOrderedById() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Mesa> criteria = cb.createQuery(Mesa.class);
        Root<Mesa> member = criteria.from(Mesa.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(member).orderBy(cb.asc(member.get("idMesa")));
        List<Mesa> resultList = em.createQuery(criteria).getResultList();
		return resultList;
    }
}
