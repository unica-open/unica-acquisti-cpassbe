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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUfficioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;

/**
 * Data Access Object implementor for the entity CpassTUfficio
 */
@ApplicationScoped
public class CpassTUfficioDaoImpl extends BaseAuditedEntityDaoImpl<Integer, CpassTUfficio> implements CpassTUfficioDao {

	@Override
	public List<CpassTUfficio> getUfficiBySettore(List<UUID> settoreId) {

		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT DISTINCT rus.cpassTUfficio FROM CpassRUfficioSettore rus ")
				.append(" WHERE 1 = 1 ")
				.append("AND (rus.cpassTUfficio.dataCancellazione IS NULL OR (rus.cpassTUfficio.dataCancellazione IS NOT NULL and rus.cpassTUfficio.dataCancellazione > :now)) ")
				.append("AND rus.dataValiditaInizio <= :now ")
				.append("AND (rus.dataValiditaFine IS NULL OR rus.dataValiditaFine > :now) ")
				.append("AND rus.cpassTSettore.settoreId IN (:settoreId)");
		params.put("settoreId", settoreId);
		params.put("now", now);

		final TypedQuery<CpassTUfficio> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassTUfficio> getUfficioByCodice(String codice, UUID settoreId) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();

		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTUfficio uff ")
				.append(" WHERE uff.ufficioCodice = :codice ")
				.append(" AND (uff.dataCancellazione IS NULL OR (uff.dataCancellazione IS NOT NULL and uff.dataCancellazione > :now)) ")
				.append(" AND EXISTS ( FROM CpassRUfficioSettore rus where  rus.cpassTSettore.settoreId = :settoreId AND rus.cpassTUfficio = uff )");

		params.put("codice", codice);
		params.put("settoreId", settoreId);
		params.put("now", now);

		final TypedQuery<CpassTUfficio> query = composeTypedQuery(jpql, params);
		query.setMaxResults(1);
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<CpassTUfficio> getUfficioByCod(String codice) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();

		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTUfficio uff ")
				.append(" WHERE uff.ufficioCodice = :codice ")
				.append(" AND (uff.dataCancellazione IS NULL OR (uff.dataCancellazione IS NOT NULL and uff.dataCancellazione > :now)) ");

		params.put("codice", codice);
		params.put("now", now);

		final TypedQuery<CpassTUfficio> query = composeTypedQuery(jpql, params);
		query.setMaxResults(1);
		return query.getResultStream().findFirst();
	}

	@Override
	public List<CpassTUfficio>  getUfficiValidiByEnte(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTUfficio uff ")
				.append(" WHERE uff.cpassTEnte.enteId = :enteId ")
				.append(" AND (uff.dataCancellazione IS NULL OR (uff.dataCancellazione IS NOT NULL and uff.dataCancellazione > :now)) ");
		params.put("enteId", enteId);
		params.put("now", now);

		final TypedQuery<CpassTUfficio> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
