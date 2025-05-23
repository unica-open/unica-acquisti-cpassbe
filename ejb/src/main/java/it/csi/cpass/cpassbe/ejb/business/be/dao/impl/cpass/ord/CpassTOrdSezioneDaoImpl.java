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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSezioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassTOrdSezione
 */
@ApplicationScoped
public class CpassTOrdSezioneDaoImpl extends BaseEntityDaoImpl<Integer, CpassTOrdSezione> implements CpassTOrdSezioneDao {

	@Override
	public List<CpassTOrdSezione> getSezioniByEnte(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdSezione sez ")
				.append("WHERE sez.cpassTEnte.enteId = :enteId ")
				.append("AND (sez.dataCancellazione IS NULL OR (sez.dataCancellazione IS NOT NULL and sez.dataCancellazione > :now )) ");
		params.put("enteId", enteId);
		params.put("now", now);

		final TypedQuery<CpassTOrdSezione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Page<CpassTOrdSezione> findPaginated(String codice, String descrizione, UUID enteId, int page, int size,String sortField, String sortDirection) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassTOrdSezione sez WHERE sez.cpassTEnte.enteId = :enteId ");
		params.put("enteId", enteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "sez.sezioneCodice"     , "codice",      codice);
		JpaQueryHelper.andFieldLike  (jpql, params, "sez.sezioneDescrizione", "descrizione", descrizione);
		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public List<CpassTOrdSezione> getSezioniByUtente(UUID settoreId,UUID utenteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT r.cpassTOrdSezione FROM CpassROrdUtenteSezione r ")
				.append("WHERE (r.dataCancellazione IS NULL OR (r.dataCancellazione IS NOT NULL and r.dataCancellazione > :now )) ")
				.append("AND r.cpassTUtente.utenteId = :utenteId ")
				.append("AND r.cpassTOrdSezione.cpassTSettore.settoreId = :settoreId ")
				;
		params.put("now", now);
		params.put("utenteId", utenteId);
		params.put("settoreId", settoreId);
		final TypedQuery<CpassTOrdSezione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdSezione> getSezioniBySettoreId(UUID settoreId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdSezione sez ")
				.append("WHERE sez.cpassTSettore.settoreId = :settoreId ")
				.append("AND (sez.dataCancellazione IS NULL OR (sez.dataCancellazione IS NOT NULL and sez.dataCancellazione > :now )) ");


		params.put("settoreId", settoreId);
		params.put("now", now);

		final TypedQuery<CpassTOrdSezione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
