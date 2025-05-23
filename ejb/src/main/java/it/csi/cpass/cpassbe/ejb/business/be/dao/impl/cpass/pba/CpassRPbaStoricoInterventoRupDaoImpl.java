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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.pba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassRPbaStoricoInterventoRupDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStoricoInterventoRup;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTPbaInterventoImporti
 */
@ApplicationScoped
public class CpassRPbaStoricoInterventoRupDaoImpl extends BaseEntityDaoImpl<Integer, CpassRPbaStoricoInterventoRup> implements CpassRPbaStoricoInterventoRupDao {

	@Override
	public List<CpassRPbaStoricoInterventoRup> getStoricoRupsByInterventoId(UUID interventoId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassRPbaStoricoInterventoRup ir ")
				.append(" WHERE 1 = 1 ");


		JpaQueryHelper.andFieldEquals(jpql, params, "ir.cpassTPbaIntervento.interventoId", "interventoId", interventoId);

		jpql.append(" ORDER BY ir.dataStoricizzazione DESC ");

		final TypedQuery<CpassRPbaStoricoInterventoRup> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteByIdIntervento(UUID interventoId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassRPbaStoricoInterventoRup imp");
		sb.append(" WHERE imp.cpassTPbaIntervento.interventoId = :interventoId");
		final Map<String, Object> params = new HashMap<>();
		params.put("interventoId", interventoId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();

	}
}
