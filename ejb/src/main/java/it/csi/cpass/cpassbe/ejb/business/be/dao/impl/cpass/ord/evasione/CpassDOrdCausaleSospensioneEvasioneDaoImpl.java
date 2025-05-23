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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassDOrdCausaleSospensioneEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassDOrdCausaleSospensioneEvasione;

@ApplicationScoped
public class CpassDOrdCausaleSospensioneEvasioneDaoImpl extends BaseEntityDaoImpl<Integer, CpassDOrdCausaleSospensioneEvasione> implements CpassDOrdCausaleSospensioneEvasioneDao {

	@Override
	public List<CpassDOrdCausaleSospensioneEvasione> findAllValide() {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassDOrdCausaleSospensioneEvasione cse ");
		jpql.append(" WHERE (cse.dataCancellazione  IS NULL OR (cse.dataCancellazione IS NOT NULL and cse.dataCancellazione > :now ))");
		params.put("now", now);

		final TypedQuery<CpassDOrdCausaleSospensioneEvasione> query = composeTypedQuery(jpql, params);
		final List<CpassDOrdCausaleSospensioneEvasione> results = query.getResultList();
		return results;
	}




}
