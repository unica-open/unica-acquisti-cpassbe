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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.decodifiche;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDStatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassDStatoDaoImpl extends BaseEntityDaoImpl<Integer, CpassDStato> implements CpassDStatoDao {


	@Override
	public Optional<CpassDStato> findByCodiceTipo(String statoCodice, String statoTipo) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDStato ds ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoCodice", "statoCodice", statoCodice);
		if(statoTipo!= null) {
			JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoTipo", "statoTipo", statoTipo);
		}
		final TypedQuery<CpassDStato> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<CpassDStato> getStatosByTipo(String statoTipo) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDStato ds ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.statoTipo", "statoTipo", statoTipo);
		final TypedQuery<CpassDStato> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
