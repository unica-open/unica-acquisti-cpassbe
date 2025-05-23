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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassRSettoreAooActaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRSettoreAooActa;


@ApplicationScoped
public class CpassRSettoreAooActaImpl extends BaseEntityDaoImpl<Integer, CpassRSettoreAooActa> implements CpassRSettoreAooActaDao {

	@Override
	public List<CpassRSettoreAooActa> findByAooActaIdSettId(UUID settId, Integer aooActaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassRSettoreAooActa crnu ");
		jpql.append(" WHERE 1 = 1");

		jpql.append(" AND crnu.dataFineValidita IS NULL ");

		if(aooActaId!=null) {
			jpql.append(" AND crnu.CpassDAooActa.aooActaId = :aooActaId ");
			params.put("aooActaId", aooActaId);
		}

		if(settId!=null) {
			jpql.append(" AND crnu.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settId);
		}

		final TypedQuery<CpassRSettoreAooActa> query = composeTypedQuery(jpql, params);
		final List<CpassRSettoreAooActa> results = query.getResultList();
		return results;
	}



}
