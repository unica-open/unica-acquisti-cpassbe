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

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;

/**
 * Data Access Object implementor for the entity CpassTOrdSubimpegnoOrdine
 */
@ApplicationScoped
public class CpassTOrdSubimpegnoOrdineDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdSubimpegnoOrdine> implements CpassTOrdSubimpegnoOrdineDao {

	@Override
	public BigDecimal calcolaSubimpegnoOrdinato(UUID subimpegnoId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(x.subimpegnoImporto) ");
		jpql.append(" FROM CpassTOrdSubimpegnoOrdine x ");
		jpql.append(" WHERE cpassTSubimpegno.subimpegnoId = :subimpegnoId ");
		jpql.append(" AND impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");

		int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);

		Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("subimpegnoId", subimpegnoId);
		q.setParameter("impegnoAnnoEsercizio", annoCorrente);

		Object result = (Object) q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public List<CpassTOrdSubimpegnoOrdine> getSubimpegni(UUID impegnoOrdineId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdSubimpegnoOrdine tso ");
		jpql.append(" WHERE tso.cpassTOrdImpegnoOrdine.impegnoOrdineId = :impegnoOrdineId ");

		Map<String, Object> params = new HashMap<>();
		params.put("impegnoOrdineId", impegnoOrdineId);

		TypedQuery<CpassTOrdSubimpegnoOrdine> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void delete(UUID idSubimpegnoOrdine) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdSubimpegnoOrdine ");
		sb.append(" WHERE subimpegnoOrdineId = :idSubimpegnoOrdine");

		Map<String, Object> params = new HashMap<>();
		params.put("idSubimpegnoOrdine", idSubimpegnoOrdine);

		Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

}
