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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdStatoNsoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDOrdStatoNsoDaoImpl
 */
@ApplicationScoped
public class CpassDOrdStatoNsoDaoImpl extends BaseEntityDaoImpl<Integer, CpassDOrdStatoNso> implements CpassDOrdStatoNsoDao {

	@Override
	public List<CpassDOrdStatoNso> getStatoNsosByTipo(String statoNsoTipo) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDOrdStatoNso ds ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoNsoTipo", "statoNsoTipo", statoNsoTipo);
		final TypedQuery<CpassDOrdStatoNso> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassDOrdStatoNso> findByCodiceTipo(String statoNsoCodice, String statoNsoTipo) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDOrdStatoNso ds ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoNsoCodice", "statoNsoCodice", statoNsoCodice);
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoNsoTipo", "statoNsoTipo", statoNsoTipo);
		final TypedQuery<CpassDOrdStatoNso> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

}
