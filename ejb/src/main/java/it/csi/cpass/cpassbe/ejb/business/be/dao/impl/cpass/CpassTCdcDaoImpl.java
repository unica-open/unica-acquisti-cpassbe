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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTCdcDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTCdc;

/**
 * Data Access Object implementor for the entity CpassTCdc
 */
@ApplicationScoped
public class CpassTCdcDaoImpl extends BaseEntityDaoImpl<Integer, CpassTCdc> implements CpassTCdcDao {

	@Override
	public List<CpassTCdc> getCdcBySettore(List<UUID> settoreId) {

		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT DISTINCT rus.cpassTCdc FROM CpassRSettoreCdc rus ")
				.append(" WHERE 1 = 1 ")
				.append("AND (rus.dataCancellazione IS NULL OR  rus.dataCancellazione > :now) ")
				.append("AND rus.cpassTSettore.settoreId IN (:settoreId)");
		params.put("settoreId", settoreId);
		params.put("now", now);

		final TypedQuery<CpassTCdc> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassTCdc> getCdcByCodice(String codice, UUID settoreId) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();

		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTCdc cdc ")
				.append(" WHERE cdc.cdcCodice = :codice ")
				.append(" AND cdc.dataValiditaInizio <= :now ")
				.append(" AND (cdc.dataValiditaFine IS NULL  OR  cdc.dataValiditaFine > :now) ")
				.append(" AND EXISTS ( FROM CpassRSettoreCdc rus where  rus.cpassTSettore.settoreId = :settoreId AND rus.cpassTCdc = cdc )");

		params.put("codice", codice);
		params.put("settoreId", settoreId);
		params.put("now", now);

		final TypedQuery<CpassTCdc> query = composeTypedQuery(jpql, params);
		query.setMaxResults(1);
		return query.getResultStream().findFirst();
	}

	@Override
	public List<CpassTCdc>  getCdcValidiByEnte(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTCdc cdc ")
				.append(" WHERE cdc.cpassTEnte.enteId = :enteId ")
				.append(" AND cdc.dataValiditaInizio <= :now ")
				.append(" AND (cdc.dataValiditaFine IS NULL  OR  cdc.dataValiditaFine > :now) ");

		params.put("enteId", enteId);
		params.put("now", now);

		final TypedQuery<CpassTCdc> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
