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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRUfficioSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUfficioSettore;

@ApplicationScoped
public class CpassRUfficioSettoreDaoImpl extends BaseEntityDaoImpl<Integer, CpassRUfficioSettore> implements CpassRUfficioSettoreDao {

	@Override
	public CpassRUfficioSettore findByUffIdSettId(UUID settoreId, Integer ufficioId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassRUfficioSettore crnu ");
		jpql.append(" WHERE 1 = 1");

		jpql.append(" AND crnu.cpassTUfficio.ufficioId = :ufficioId ");
		jpql.append(" AND crnu.cpassTSettore.settoreId = :settoreId ");


		params.put("ufficioId", ufficioId);
		params.put("settoreId", settoreId);

		final TypedQuery<CpassRUfficioSettore> query = composeTypedQuery(jpql, params);
		final List<CpassRUfficioSettore> results = query.getResultList();
		return results != null && results.size() > 0 ? results.get(0) : null;

	}



}
