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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassDOrdTipoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassDOrdTipoEvasione;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassDOrdTipoEvasioneDaoImpl extends BaseEntityDaoImpl<Integer, CpassDOrdTipoEvasione> implements CpassDOrdTipoEvasioneDao {

	@Override
	public CpassDOrdTipoEvasione findByCodice(String codice) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassDOrdTipoEvasione te ");
		jpql.append(" WHERE (te.dataCancellazione  IS NULL OR (te.dataCancellazione IS NOT NULL and te.dataCancellazione > :now ))");
		params.put("now", now);

		JpaQueryHelper.andFieldEquals(jpql, params, "te.tipoEvasioneCodice", "codice", codice);

		final TypedQuery<CpassDOrdTipoEvasione> query = composeTypedQuery(jpql, params);
		final List<CpassDOrdTipoEvasione> results = query.getResultList();
		return results != null && results.size() > 0 ? results.get(0) : null;
	}

	@Override
	public List<CpassDOrdTipoEvasione> findValid() {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDOrdTipoEvasione te ")
				.append("WHERE te.dataCancellazione IS NULL ")
				.append("OR (te.dataCancellazione IS NOT NULL and te.dataCancellazione > :now) ")
				.append("ORDER BY te.tipoEvasioneDescrizione");
		params.put("now", now);

		final TypedQuery<CpassDOrdTipoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
