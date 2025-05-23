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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDAooActaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDAooActa;

/**
 * Data Access Object implementor for the entity CpassDOrdTipoProcedura
 */
@ApplicationScoped
public class CpassDAooActaImpl extends BaseEntityDaoImpl<Integer, CpassDAooActa> implements CpassDAooActaDao {

	@Override
	public Optional<CpassDAooActa> getAooByIdSettore(UUID settoreId,UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rsaa.cpassDAooActa  ")
				.append("FROM CpassRSettoreAooActa rsaa ")
				.append("WHERE (rsaa.dataFineValidita IS NULL ")
				.append("OR (rsaa.dataFineValidita IS NOT NULL and rsaa.dataFineValidita > :now)) ")
				.append(" AND rsaa.cpassDAooActa.enteId = :enteId ")
				.append(" AND rsaa.cpassTSettore.settoreId = :settoreId ");

		params.put("now", now);
		params.put("settoreId", settoreId);
		params.put("enteId", enteId);
		final TypedQuery<CpassDAooActa> query = composeTypedQuery(jpql, params);
		return query.getResultStream().findFirst();
	}

	@Override
	public List<CpassDAooActa> findByActaOrigId(Integer aooActaOrigId,UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassDAooActa rsaa ")
				.append(" WHERE 1 = 1 AND (rsaa.dataFineValidita IS NULL OR rsaa.dataFineValidita > :now) ")
				.append(" AND rsaa.enteId = :enteId ")
				.append(" AND rsaa.aooActaOrigId = :aooActaOrigId ");
		params.put("now", now);
		params.put("enteId", enteId);
		params.put("aooActaOrigId", aooActaOrigId);
		final TypedQuery<CpassDAooActa> query = composeTypedQuery(jpql, params);
		final List<CpassDAooActa> ris = query.getResultList();
		return ris;
	}

}
