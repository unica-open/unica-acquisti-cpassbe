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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDStatoElOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassDStatoElOrdineDaoImpl extends BaseEntityDaoImpl<Integer, CpassDStatoElOrdine> implements CpassDStatoElOrdineDao {

	@Override
	public List<CpassDStatoElOrdine> getStatoElOrdineByTipo(String statoTipo) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassDStatoElOrdine ds ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoTipo", "statoTipo", statoTipo);
		TypedQuery<CpassDStatoElOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public CpassDStatoElOrdine findByCodiceTipo(String codice, String statoTipo) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassDStatoElOrdine ds ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoCodice", "statoCodice", codice);
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoTipo", "statoTipo", statoTipo);
		TypedQuery<CpassDStatoElOrdine> query = composeTypedQuery(jpql, params);
		
		List<CpassDStatoElOrdine> result = query.getResultList();
		return result != null && result.size() > 0 ? result.get(0) : null;
	}

}
