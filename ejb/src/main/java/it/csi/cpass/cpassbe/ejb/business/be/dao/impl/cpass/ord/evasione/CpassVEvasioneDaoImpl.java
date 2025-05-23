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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassVEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVEvasione;

/**
 * Data Access Object implementor for the entity CpassTImpegnoDao
 */
@ApplicationScoped
public class CpassVEvasioneDaoImpl extends BaseEntityDaoImpl<Long, CpassVEvasione> implements CpassVEvasioneDao {

	@Override
	public BigDecimal getRipartitoSuEvasioneById(UUID testataEvasioneId, Integer statoId, Boolean subimportoRipartito) {
		final StringBuilder jpql = new StringBuilder();
		if(subimportoRipartito) {
			jpql.append(" SELECT SUM(COALESCE (ie.subImportoRipartito,0) + COALESCE (ie.subImportoSospeso,0)) ");
		}else {
			jpql.append(" SELECT SUM(COALESCE (ie.importoRipartito,0) + COALESCE (ie.importoSospeso,0)) ");
		}
		jpql.append(" FROM CpassVEvasione ie ");
		jpql.append(" WHERE ie.testataEvasioneId = :testataEvasioneId ");
		if(statoId!= null) {
			jpql.append(" AND ie.statoId = :statoId ");
		}
		final Query q = entityManager.createQuery(jpql.toString());
		if(statoId!= null) {
			q.setParameter("statoId", statoId);
		}
		q.setParameter("testataEvasioneId", testataEvasioneId);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}



	@Override
	public List<CpassVEvasione> getListaSubImpegniByEvasioneId(UUID testataEvasioneId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassVEvasione vo WHERE vo.testataEvasioneId =:testataEvasioneId ");
		jpql.append(" ORDER BY ");
		jpql.append("  vo.evasioneAnno");
		jpql.append(" ,vo.evasioneNumero");

		jpql.append(" ,vo.impegnoAnno");
		jpql.append(" ,vo.impegnoNumero");
		jpql.append(" ,vo.subimpegnoAnno");
		jpql.append(" ,vo.subimpegnoNumero");


		params.put("testataEvasioneId", testataEvasioneId);
		final TypedQuery<CpassVEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
