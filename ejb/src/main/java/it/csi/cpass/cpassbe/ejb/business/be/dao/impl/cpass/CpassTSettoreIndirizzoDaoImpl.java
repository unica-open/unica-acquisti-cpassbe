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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreIndirizzoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTSettoreIndirizzo;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Data Access Object implementor for the entity CpassTSettoreIndirizzo
 */
@ApplicationScoped
public class CpassTSettoreIndirizzoDaoImpl extends BaseAuditedEntityDaoImpl<Integer, CpassTSettoreIndirizzo> implements CpassTSettoreIndirizzoDao {


	@Override
	public List<CpassTSettoreIndirizzo> findBySettore(Settore settore) {

		final Date now = new Date();

		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTSettoreIndirizzo si ")
				.append(" WHERE (si.dataCancellazione  IS NULL OR (si.dataCancellazione IS NOT NULL and si.dataCancellazione > :now ))   ");
		JpaQueryHelper.andFieldEquals(jpql, params, "si.cpassTSettore.settoreCodice", "settoreCodice", settore.getCodice());
		JpaQueryHelper.andFieldEquals(jpql, params, "si.cpassTSettore.settoreId", "settoreId", settore.getId());
		params.put("now", now);

		final TypedQuery<CpassTSettoreIndirizzo> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
