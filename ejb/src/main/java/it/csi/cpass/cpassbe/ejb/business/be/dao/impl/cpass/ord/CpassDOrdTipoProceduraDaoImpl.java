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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdTipoProceduraDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoProcedura;

/**
 * Data Access Object implementor for the entity CpassDOrdTipoProcedura
 */
@ApplicationScoped
public class CpassDOrdTipoProceduraDaoImpl extends BaseAuditedEntityDaoImpl<Integer, CpassDOrdTipoProcedura> implements CpassDOrdTipoProceduraDao {

	@Override
	public List<CpassDOrdTipoProcedura> findValid(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassDOrdTipoProcedura tp ")
				.append(" WHERE tp.dataCancellazione IS NULL ")
				.append(" OR (tp.dataCancellazione IS NOT NULL and tp.dataCancellazione > :now )")
				.append(" AND tp.cpassTEnte.enteId = :enteId ")

				.append(" ORDER BY tp.tipoProceduraDescrizione");
		params.put("now", now);
		params.put("enteId", enteId);

		final TypedQuery<CpassDOrdTipoProcedura> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
