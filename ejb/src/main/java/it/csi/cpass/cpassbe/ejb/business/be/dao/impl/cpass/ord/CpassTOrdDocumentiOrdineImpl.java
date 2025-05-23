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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDocumentiOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDocumentiOrdine;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDOrdTipoProcedura
 */
@ApplicationScoped
public class CpassTOrdDocumentiOrdineImpl extends BaseAuditedEntityDaoImpl<Integer,  CpassTOrdDocumentiOrdine> implements CpassTOrdDocumentiOrdineDao {

	@Override
	public List<CpassTOrdDocumentiOrdine> getDocumentiByOrdineTestataId(UUID testataOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdDocumentiOrdine dest ")
				.append(" WHERE dest.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "dest.cpassTOrdTestataOrdine.testataOrdineId", "testataOrdineId", testataOrdineId);
		final TypedQuery<CpassTOrdDocumentiOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteByTestataordineId(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdDocumentiOrdine do ");
		sb.append(" WHERE do.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public void deleteById(Integer documentoOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdDocumentiOrdine do ");
		sb.append(" WHERE do.documentiOrdineId = :documentoOrdineId");
		final Map<String, Object> params = new HashMap<>();
		params.put("documentoOrdineId", documentoOrdineId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}
}
