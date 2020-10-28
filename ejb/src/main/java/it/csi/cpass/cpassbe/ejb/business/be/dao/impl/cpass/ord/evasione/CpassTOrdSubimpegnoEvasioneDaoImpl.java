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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdSubimpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdImpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdSubimpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTOrdSubimpegnoEvasione
 */
@ApplicationScoped
public class CpassTOrdSubimpegnoEvasioneDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdSubimpegnoEvasione> implements CpassTOrdSubimpegnoEvasioneDao {

	@Override
	public List<CpassTOrdSubimpegnoEvasione> getSubimpegni(UUID impegnoEvasioneId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdSubimpegnoEvasione tso ");
		jpql.append(" WHERE tso.cpassTOrdImpegnoEvasione.impegnoEvasioneId = :impegnoEvasioneId ");

		Map<String, Object> params = new HashMap<>();
		params.put("impegnoEvasioneId", impegnoEvasioneId);

		TypedQuery<CpassTOrdSubimpegnoEvasione> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void delete(UUID idSubimpegnoEvasione) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdSubimpegnoEvasione ");
		sb.append(" WHERE subimpegnoEvasioneId = :idSubimpegnoEvasione");

		Map<String, Object> params = new HashMap<>();
		params.put("idSubimpegnoEvasione", idSubimpegnoEvasione);

		Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public BigDecimal calcolaTotaleEvaso(UUID subimpegnoOrdineId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(sie.importoRipartito + sie.importoSospeso) ");
		jpql.append(" FROM CpassTOrdSubimpegnoEvasione sie ");
		jpql.append(" WHERE sie.cpassTOrdSubimpegnoOrdine.subimpegnoOrdineId = :subimpegnoOrdineId ");

		Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("subimpegnoOrdineId", subimpegnoOrdineId);

		Object result = (Object) q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero, Integer subimpegnoAnno, Integer subimpegnoNumero) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(sie.importoRipartito + sie.importoSospeso) ");
		jpql.append(" FROM CpassTOrdSubimpegnoEvasione sie ");
		jpql.append(" WHERE sie.impegnoAnno = :impegnoAnno ");
		jpql.append(" AND sie.impegnoNumero = :impegnoNumero ");
		jpql.append(" AND sie.subimpegnoAnno = :subimpegnoAnno ");
		jpql.append(" AND sie.subimpegnoNumero = :subimpegnoNumero ");

		Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("impegnoAnno", impegnoAnno);
		q.setParameter("impegnoNumero", impegnoNumero);
		q.setParameter("subimpegnoAnno", subimpegnoAnno);
		q.setParameter("subimpegnoNumero", subimpegnoNumero);

		Object result = (Object) q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}
	
	@Override
	public List<CpassTOrdSubimpegnoEvasione> findByIdSubimpegnoOrdine(UUID subimpegnoOrdineId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdSubimpegnoEvasione sie ");
		jpql.append(" AND sie.dataCancellazione IS NULL ");

		JpaQueryHelper.andFieldEquals(jpql, params, "ie.cpassTOrdSubimpegnoOrdine.subimpegnoOrdineId", "subimpegnoOrdineId", subimpegnoOrdineId);
		TypedQuery<CpassTOrdSubimpegnoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
