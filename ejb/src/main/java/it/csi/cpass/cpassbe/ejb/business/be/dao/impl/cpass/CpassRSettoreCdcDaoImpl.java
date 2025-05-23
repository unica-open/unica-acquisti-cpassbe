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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRSettoreCdcDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRSettoreCdc;

@ApplicationScoped
public class CpassRSettoreCdcDaoImpl extends BaseAuditedEntityDaoImpl<Integer, CpassRSettoreCdc> implements CpassRSettoreCdcDao {


	@Override
	public List<CpassRSettoreCdc> findByCdcIdSettId(UUID settoreId, Integer cdcId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassRSettoreCdc crnu ");
		jpql.append(" WHERE 1 = 1");

		jpql.append(" AND crnu.dataCancellazione IS NULL ");

		if(cdcId!=null) {
			jpql.append(" AND crnu.cpassTCdc.cdcId = :cdcId ");
			params.put("cdcId", cdcId);
		}

		if(settoreId != null) {
			jpql.append(" AND crnu.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settoreId);
		}

		final TypedQuery<CpassRSettoreCdc> query = composeTypedQuery(jpql, params);
		final List<CpassRSettoreCdc> results = query.getResultList();
		return results;

	}

}
