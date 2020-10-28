/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRUtenteSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteSettore;

@ApplicationScoped
public class CpassRUtenteSettoreDaoImpl extends BaseEntityDaoImpl<Integer, CpassRUtenteSettore> implements CpassRUtenteSettoreDao {

	@Override
	public List<CpassRUtenteSettore> findByUtenteSettore(UUID utenteId, UUID settoreId) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT rus ");
		jpql.append(" FROM CpassRUtenteSettore rus ");
		jpql.append(" WHERE rus.cpassTUtente.utenteId = :utenteId ");

		params.put("utenteId", utenteId);

		if (settoreId != null) {
			jpql.append(" AND rus.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settoreId);
		}

		TypedQuery<CpassRUtenteSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
