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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;

/**
 * Data Access Object implementor for the entity CpassTOrdImpegnoOrdine
 */
@ApplicationScoped
public class CpassTOrdImpegnoOrdineDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdImpegnoOrdine> implements CpassTOrdImpegnoOrdineDao {

	@Override
	public List<CpassTOrdImpegnoOrdine> getImpegniCollegati(UUID testataOrdineId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoOrdine tio ");
		jpql.append(" WHERE tio.cpassTOrdRigaOrdine.cpassTOrdDestinatario.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");

		Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);

		TypedQuery<CpassTOrdImpegnoOrdine> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public BigDecimal calcolaOrdinato(UUID impegnoId, UUID testataOrdineId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(ctoio.importo) ");
		jpql.append(" FROM CpassTOrdImpegnoOrdine ctoio ");
		jpql.append(" WHERE cpassTImpegno.impegnoId = :impegnoId ");
		jpql.append(" AND impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");
//		jpql.append(" AND impegnoAnno = :impegnoAnno ");
//		jpql.append(" AND impegnoNumero = :impegnoNumero ");

		int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);

		// escludo gli impegni dell'ordine specificato
		if (testataOrdineId != null) {
			jpql.append(" AND not exists ( ");
			jpql.append("   select ctoro ");
			jpql.append("   from CpassTOrdRigaOrdine ctoro, CpassTOrdDestinatarioOrdine ctod ");
			jpql.append("   where ctoro.cpassTOrdDestinatario.destinatarioId = ctod.destinatarioId  ");
			jpql.append("   and ctod.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");
			jpql.append("   and ctoio.cpassTOrdRigaOrdine.rigaOrdineId = ctoro.rigaOrdineId  ");
			jpql.append(" ) ");
		}

		Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("impegnoId", impegnoId);
		q.setParameter("impegnoAnnoEsercizio", annoCorrente);
//		q.setParameter("impegnoAnno", impegno.getAnno());
//		q.setParameter("impegnoNumero", impegno.getNumero());

		if (testataOrdineId != null) {
			q.setParameter("testataOrdineId", testataOrdineId);
		}

		Object result = (Object) q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public List<CpassTOrdImpegnoOrdine> getImpegni(UUID rigaOrdineId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoOrdine tio ");
		jpql.append(" WHERE tio.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ");

		Map<String, Object> params = new HashMap<>();
		params.put("rigaOrdineId", rigaOrdineId);

		TypedQuery<CpassTOrdImpegnoOrdine> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}
	
	@Override
	public List<CpassTOrdImpegnoOrdine> getImpegniNonPresentiEvasione(UUID rigaOrdineId, UUID rigaEvasioneId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoOrdine tio ");
		jpql.append(" WHERE tio.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ");

		if (rigaEvasioneId != null) {
			jpql.append(" AND NOT EXISTS ( ");
			jpql.append("   SELECT ie FROM CpassTOrdImpegnoEvasione ie ");
			jpql.append("   WHERE ie.cpassTOrdImpegnoOrdine.impegnoOrdineId = tio.impegnoOrdineId ");
			jpql.append("   AND ie.cpassTOrdRigaEvasione.rigaEvasioneId = :rigaEvasioneId ");
			jpql.append(" ) ");
		}

		Map<String, Object> params = new HashMap<>();
		params.put("rigaOrdineId", rigaOrdineId);
		if (rigaEvasioneId != null) {
			params.put("rigaEvasioneId", rigaEvasioneId);
		}

		TypedQuery<CpassTOrdImpegnoOrdine> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void deleteByRiga(UUID rigaOrdineId) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdImpegnoOrdine ");
		sb.append(" WHERE cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId");

		Map<String, Object> params = new HashMap<>();
		params.put("rigaOrdineId", rigaOrdineId);

		Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

}
