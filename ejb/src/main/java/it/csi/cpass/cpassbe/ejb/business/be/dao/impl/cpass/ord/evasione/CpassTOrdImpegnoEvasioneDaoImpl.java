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
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdImpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdImpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

/**
 * Data Access Object implementor for the entity CpassTOrdImpegnoEvasione
 */
@ApplicationScoped
public class CpassTOrdImpegnoEvasioneDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdImpegnoEvasione> implements CpassTOrdImpegnoEvasioneDao {

	@Override
	public List<CpassTOrdImpegnoEvasione> getImpegniEvasione(UUID rigaEvasioneId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoEvasione tio ");
		jpql.append(" WHERE tio.cpassTOrdRigaEvasione.rigaEvasioneId = :rigaEvasioneId ");

		final Map<String, Object> params = new HashMap<>();
		params.put("rigaEvasioneId", rigaEvasioneId);

		final TypedQuery<CpassTOrdImpegnoEvasione> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void deleteByRigaEvasione(UUID rigaEvasioneId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdImpegnoEvasione imp");
		sb.append(" WHERE imp.cpassTOrdRigaEvasione.rigaEvasioneId = :rigaEvasioneId");
		final Map<String, Object> params = new HashMap<>();
		params.put("rigaEvasioneId", rigaEvasioneId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public List<CpassTOrdImpegnoEvasione> findByIdRigaEvasione(UUID idRigaEvasione) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdImpegnoEvasione impegno ");
		jpql.append(" WHERE impegno.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "impegno.cpassTOrdRigaEvasione.rigaEvasioneId", "idRiga", idRigaEvasione);
		final TypedQuery<CpassTOrdImpegnoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdImpegnoEvasione> findByIdImpegnoOrdine(UUID impegnoOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdImpegnoEvasione ie ");
		jpql.append(" AND ie.dataCancellazione IS NULL ");

		JpaQueryHelper.andFieldEquals(jpql, params, "ie.cpassTOrdImpegnoOrdine.impegnoOrdineId", "impegnoOrdineId", impegnoOrdineId);
		final TypedQuery<CpassTOrdImpegnoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public BigDecimal calcolaTotaleEvaso(UUID impegnoOrdineId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE(ie.importoRipartito,0) + COALESCE(ie.importoSospeso,0)) ");
		jpql.append(" FROM CpassTOrdImpegnoEvasione ie ");
		jpql.append(" WHERE ie.cpassTOrdImpegnoOrdine.impegnoOrdineId = :impegnoOrdineId ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("impegnoOrdineId", impegnoOrdineId);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero,UUID testataEvasioneId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE(ie.importoRipartito,0) + COALESCE(ie.importoSospeso,0)) ");
		jpql.append(" FROM CpassTOrdImpegnoEvasione ie ");
		jpql.append(" WHERE ie.impegnoAnno = :impegnoAnno ");
		jpql.append(" AND ie.impegnoNumero = :impegnoNumero ");

		jpql.append(" AND ie.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.testataEvasioneId = :testataEvasioneId ");


		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("impegnoAnno", impegnoAnno);
		q.setParameter("impegnoNumero", impegnoNumero);
		q.setParameter("testataEvasioneId", testataEvasioneId);


		//q.setParameter("impegnoAnnoEsercizio", impegnoAnnoEsercizio);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}
	@Override
	public List<CpassTOrdImpegnoEvasione> findByIdsRigaEvasioneEImpegno(List<RigaEvasione> rigaEvasiones, Integer impegnoAnno, Integer impegnoNumero) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoEvasione ie ")
		.append(" WHERE ie.cpassTOrdRigaEvasione.rigaEvasioneId in (:rigaEvasioneIds)");
		params.put("rigaEvasioneIds", rigaEvasiones.stream().map(RigaEvasione::getId).collect(Collectors.toList()));

		JpaQueryHelper.andFieldEquals(jpql, params, "ie.impegnoAnno", "impegnoAnno", impegnoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "ie.impegnoNumero", "impegnoNumero", impegnoNumero);
		jpql.append(" ORDER BY ie.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.evasioneAnno"
				+ ", ie.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.evasioneNumero"
				+ ", ie.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.progressivo"
				+ ", ie.cpassTOrdRigaEvasione.progressivo");

		final TypedQuery<CpassTOrdImpegnoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public BigDecimal calcolaImpegnoEvaso(Integer annoEsercizio, Integer annoImpegno, Integer numeroImpegno,Integer annoEsercizioSuOrdine,UUID enteId,Integer statoInContId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE(ctoio.importoRipartito,0) + COALESCE(ctoio.importoSospeso,0)) ");
		jpql.append(" FROM CpassTOrdImpegnoEvasione ctoio ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" AND ctoio.impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");
		jpql.append(" AND ctoio.impegnoAnno          = :impegnoAnno ");
		jpql.append(" AND ctoio.impegnoNumero        = :impegnoNumero ");
		//jpql.append(" AND ctoio.ente.enteId          = :enteId ");
		final Map<String, Object> params = new HashMap<>();
		if(annoEsercizioSuOrdine!=null) {
			jpql.append(" AND ctoio.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.evasioneAnno = :annoEsercizioSuOrdine ");
			params.put("annoEsercizioSuOrdine", annoEsercizioSuOrdine);
		}

		if(statoInContId!=null) {
			jpql.append(" AND ctoio.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.cpassDStato.statoId = :statoInContId ");
			params.put("statoInContId", statoInContId);
		}

		params.put("impegnoAnnoEsercizio", annoEsercizio);
		params.put("impegnoAnno", annoImpegno);
		params.put("impegnoNumero", numeroImpegno);
		//params.put("enteId", enteId);

		final TypedQuery<BigDecimal> query = composeTypedQuery(jpql, params,BigDecimal.class);
		if (query.getResultList()!=null && query.getResultList().size() > 0) {
			return query.getSingleResult() == null ? BigDecimal.ZERO : query.getSingleResult();
		} else {
			return BigDecimal.ZERO;
		}
	}

}
