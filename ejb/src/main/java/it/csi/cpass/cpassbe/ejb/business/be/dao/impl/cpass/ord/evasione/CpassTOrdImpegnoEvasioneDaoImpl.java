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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdImpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdImpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTOrdImpegnoEvasione
 */
@ApplicationScoped
public class CpassTOrdImpegnoEvasioneDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdImpegnoEvasione> implements CpassTOrdImpegnoEvasioneDao {

	@Override
	public List<CpassTOrdImpegnoEvasione> getImpegniEvasione(UUID rigaEvasioneId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoEvasione tio ");
		jpql.append(" WHERE tio.cpassTOrdRigaEvasione.rigaEvasioneId = :rigaEvasioneId ");

		Map<String, Object> params = new HashMap<>();
		params.put("rigaEvasioneId", rigaEvasioneId);

		TypedQuery<CpassTOrdImpegnoEvasione> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void deleteByRigaEvasione(UUID rigaEvasioneId) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdImpegnoEvasione ");
		sb.append(" WHERE cpassTOrdRigaEvasione.rigaEvasioneId = :rigaEvasioneId");
		Map<String, Object> params = new HashMap<>();
		params.put("rigaEvasioneId", rigaEvasioneId);
		Query query = composeQuery(sb, params);
		query.executeUpdate();
	}
	
	@Override
	public List<CpassTOrdImpegnoEvasione> findByIdRigaEvasione(UUID idRigaEvasione) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdImpegnoEvasione impegno ");
		jpql.append(" WHERE impegno.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "impegno.cpassTOrdRigaEvasione.rigaEvasioneId", "idRiga", idRigaEvasione);
		TypedQuery<CpassTOrdImpegnoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
	@Override
	public List<CpassTOrdImpegnoEvasione> findByIdImpegnoOrdine(UUID impegnoOrdineId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdImpegnoEvasione ie ");
		jpql.append(" AND ie.dataCancellazione IS NULL ");

		JpaQueryHelper.andFieldEquals(jpql, params, "ie.cpassTOrdImpegnoOrdine.impegnoOrdineId", "impegnoOrdineId", impegnoOrdineId);
		TypedQuery<CpassTOrdImpegnoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
	@Override
	public BigDecimal calcolaTotaleEvaso(UUID impegnoOrdineId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(ie.importoRipartito + ie.importoSospeso) ");
		jpql.append(" FROM CpassTOrdImpegnoEvasione ie ");
		jpql.append(" WHERE ie.cpassTOrdImpegnoOrdine.impegnoOrdineId = :impegnoOrdineId ");

		Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("impegnoOrdineId", impegnoOrdineId);

		Object result = (Object) q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}
	
	@Override
	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(ie.importoRipartito + ie.importoSospeso) ");
		jpql.append(" FROM CpassTOrdImpegnoEvasione ie ");
		jpql.append(" WHERE ie.impegnoAnno = :impegnoAnno ");
		jpql.append(" AND ie.impegnoNumero = :impegnoNumero ");
		
		Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("impegnoAnno", impegnoAnno);
		q.setParameter("impegnoNumero", impegnoNumero);
		
		Object result = (Object) q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}
	
}
