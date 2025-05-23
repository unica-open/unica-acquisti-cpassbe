/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
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
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

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
		final Date now = new Date();
		final Optional<T> ris = super.findOne(key).filter(el -> el.getDataCancellazione() == null || (el.getDataCancellazione().compareTo(now) > 0 ) );
		return ris;

	}

	/**
	 * Restituisce l'oggetto per id
	 */
	@Override
	public Optional<T> findById(K key) {
		return super.findOne(key);
	}

	@Override
	public void deleteLogically(K key) {
		final Date now = new Date();
		//String utente = "admin";
		final String utenteCf = getUtenteCf();
		final StringBuilder sb = new StringBuilder()
				.append("FROM ")
				.append(clazz.getName())
				.append(" WHERE ")
				.append(idField.getName())
				.append(" = :id");
		final Map<String, Object> params = new HashMap<>();
		params.put("id", key);
		final TypedQuery<T> typedQuery = composeTypedQuery(sb, params);
		typedQuery.getResultList()
		.stream()
		.forEach(entity -> {
			entity.setUtenteCancellazione(utenteCf);
			entity.setUtenteModifica(utenteCf);
			entity.setDataCancellazione(now);
			entity.setDataModifica(now);
		});
	}

	@Override
	public T insert(T entity) {
		final Date now = new Date();
		final String utenteCf = getUtenteCf();
		entity.setOptlock(UUID.randomUUID());
		if(entity.getDataCreazione() == null) {
			entity.setDataCreazione(now);
		}
		entity.setDataModifica(now);
		if(entity.getUtenteCreazione()==null) {
			entity.setUtenteCreazione(utenteCf);
		}
		if(entity.getUtenteModifica()==null) {
			entity.setUtenteModifica(utenteCf);
		}
		return super.insert(entity);
	}

	@Override
	public T update(T entity) {
		return update(entity, Boolean.TRUE);
	}

	@Override
	public T update(T entity, boolean checkOptlock) {
		final Date now = new Date();
		final String utenteCf = getUtenteCf();
		//T current = findById(entity.getId()).orElseThrow(() -> new RuntimeException("entity non trovata "+ entity.getId())); // entity non trovato
		final T current = findOne(entity.getId()).orElseThrow(() -> new RuntimeException("entity non trovata - id: " + entity.getId())); // entity non trovato
		// Check optlock
		if (checkOptlock) {
			if(!current.getOptlock().equals(entity.getOptlock())) {
				throw new BusinessException(CoreError.INVALID_OPTLOOK.getError("optlock", current.getOptlock()));
			}
			if(entity.getOptlock()==null) {
				throw new BusinessException("optLock non passato in richiesta dal chiamante");
			}
		}
		entity.setOptlock(UUID.randomUUID());
		if(entity.getUtenteCreazione()==null) {
			entity.setUtenteCreazione(current.getUtenteCreazione());
		}
		if(entity.getDataCreazione()==null) {
			entity.setDataCreazione(current.getDataCreazione());
		}
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
