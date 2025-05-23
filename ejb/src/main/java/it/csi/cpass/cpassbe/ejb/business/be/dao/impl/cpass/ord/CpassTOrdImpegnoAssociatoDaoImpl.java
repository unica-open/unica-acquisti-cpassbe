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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoAssociato;

/**
 * Data Access Object implementor for the entity CpassTOrdImpegnoAssociato
 */
@ApplicationScoped
public class CpassTOrdImpegnoAssociatoDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdImpegnoAssociato> implements CpassTOrdImpegnoAssociatoDao {

	@Override
	public List<CpassTOrdImpegnoAssociato> getImpegniAssociati(UUID testataOrdineId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoAssociato ti ");
		jpql.append(" WHERE ti.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");

		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);

		final TypedQuery<CpassTOrdImpegnoAssociato> query = composeTypedQuery(jpql, params);
		final List<CpassTOrdImpegnoAssociato> results = query.getResultList();
		if (results.size() > 0) {
			return results;
		} else {
			return null;
		}
	}

	@Override
	public void deleteByTestataOrdine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdImpegnoAssociato");
		sb.append(" WHERE cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId");

		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);

		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

}
