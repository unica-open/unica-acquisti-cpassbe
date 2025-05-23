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

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdTipoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoOrdine;

/**
 * Data Access Object implementor for the entity CpassDOrdTipoOrdine
 */
@ApplicationScoped
public class CpassDOrdTipoOrdineDaoImpl extends BaseAuditedEntityDaoImpl<Integer, CpassDOrdTipoOrdine> implements CpassDOrdTipoOrdineDao {

	@Override
	public List<CpassDOrdTipoOrdine> findValid() {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDOrdTipoOrdine to ")
				.append("WHERE to.dataCancellazione IS NULL ")
				.append("OR (to.dataCancellazione IS NOT NULL and to.dataCancellazione > :now) ")
				.append("ORDER BY to.tipologiaDocumentoDescrizione");
		params.put("now", now);

		final TypedQuery<CpassDOrdTipoOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassDOrdTipoOrdine> getListaValidTipoOrdineExcludeCode(String code) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassDOrdTipoOrdine to ")
				.append(" WHERE (to.dataCancellazione IS NULL OR  to.dataCancellazione > :now) ")
				.append(" AND to.tipologiaDocumentoCodice != :code")
				.append(" ORDER BY to.tipologiaDocumentoDescrizione");
		params.put("code", code);
		params.put("now", now);

		final TypedQuery<CpassDOrdTipoOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassDOrdTipoOrdine> findValidByCodice(String codice) {
		final Map<String, Object> params = new HashMap<>();
		final java.util.Date now = new java.util.Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassDOrdTipoOrdine to ")
				.append(" WHERE (to.dataCancellazione IS NULL ")
				.append(" OR (to.dataCancellazione IS NOT NULL and to.dataCancellazione > :now)) ")
				.append(" AND to.tipologiaDocumentoCodice = :codice ");

		params.put("now", now);
		params.put("codice", codice);
		final TypedQuery<CpassDOrdTipoOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultStream().findFirst();
	}
}
