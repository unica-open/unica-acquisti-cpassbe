/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.exception.BusinessException;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;

/**
 * Base Data Access Object (DAO) implementor
 * @param <K> the key type
 * @param <T> the entity type
 */
public abstract class BaseAuditedEntityDaoImpl<K, T extends BaseAuditedEntity<K>> extends BaseEntityDaoImpl<K, T> implements BaseAuditedEntityDao<K, T> {
	
	/**
	 * Restituisce l'oggetto se valido
	 */
	@Override
	public Optional<T> findOne(K key) {
		return super.findOne(key)
				.filter(el -> el.getDataCancellazione() == null);
	}
	
	/**
	 * Restituisce l'oggetto per id
	 */
	public Optional<T> findById(K key) {
		return super.findOne(key);
	}

	@Override
	public void deleteLogically(K key) {
		Date now = new Date();
		String utente = "admin";
		StringBuilder sb = new StringBuilder()
				.append("FROM ")
				.append(clazz.getName())
				.append(" WHERE ")
				.append(idField.getName())
				.append(" = :id");
		Map<String, Object> params = new HashMap<>();
		params.put("id", key);
		TypedQuery<T> typedQuery = composeTypedQuery(sb, params);
		typedQuery.getResultList()
			.stream()
			.forEach(entity -> {
				entity.setUtenteCancellazione(utente);
				entity.setUtenteModifica(utente);
				entity.setDataCancellazione(now);
				entity.setDataModifica(now);
			});
	}

	@Override
	public T insert(T entity) {
		Date now = new Date();
		String utenteCf = getUtenteCf();
		entity.setOptlock(UUID.randomUUID());
		entity.setDataCreazione(now);
		entity.setDataModifica(now);
		entity.setUtenteCreazione(utenteCf);
		entity.setUtenteModifica(utenteCf);
		return super.insert(entity);
	}

	@Override
	public T update(T entity) {
		Date now = new Date();
		String utenteCf = getUtenteCf();
		T current = findOne(entity.getId()).orElseThrow(() -> new RuntimeException("Richiesta vecchia riprovare")); // entity non trovato
		// T current = findOne(entity.getId()).orElseThrow(() -> new RuntimeException("Richiesta vecchia riprovare - id: " + entity.getId())); // entity non trovato
		// Check optlock
		if(!current.getOptlock().equals(entity.getOptlock())) {
			throw new BusinessException("Dati vecchi");
		}
		if(entity.getOptlock()==null) {
			throw new BusinessException("optLock non passato in richiesta dal chiamante");
		}
		entity.setOptlock(UUID.randomUUID());
		entity.setUtenteCreazione(current.getUtenteCreazione());
		entity.setDataCreazione(current.getDataCreazione());		
		entity.setUtenteModifica(utenteCf);
		entity.setDataModifica(now);
		return super.update(entity);
	}
	
	private String getUtenteCf() {
		// per aggiornamento da jms message
		String utenteCf = CpassEnum.UTENTE_SISTEMA_CF.getCostante();
		if (CpassThreadLocalContainer.UTENTE_CONNESSO.get() != null) {
			utenteCf = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getCodiceFiscale();
		}
		return utenteCf;
	}
}
