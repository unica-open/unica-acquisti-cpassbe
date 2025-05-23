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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoAssociatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoAssociato;

/**
 * Data Access Object implementor for the entity CpassTOrdImpegnoAssociato
 */
@ApplicationScoped
public class CpassTOrdSubimpegnoAssociatoDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdSubimpegnoAssociato> implements CpassTOrdSubimpegnoAssociatoDao {

	@Override
	public List<CpassTOrdSubimpegnoAssociato> getSubimpegniAssociati(UUID impegnoAssociatoId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdSubimpegnoAssociato ti ");
		jpql.append(" WHERE ti.cpassTOrdImpegnoAssociato.impegnoAssociatoId = :impegnoAssociatoId ");

		final Map<String, Object> params = new HashMap<>();
		params.put("impegnoAssociatoId", impegnoAssociatoId);

		final TypedQuery<CpassTOrdSubimpegnoAssociato> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void deleteByTestataOrdine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdSubimpegnoAssociato ");
		sb.append(" WHERE cpassTOrdImpegnoAssociato.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId");

		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);

		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

}
