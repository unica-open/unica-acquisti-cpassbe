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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdSubimpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdSubimpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

/**
 * Data Access Object implementor for the entity CpassTOrdSubimpegnoEvasione
 */
@ApplicationScoped
public class CpassTOrdSubimpegnoEvasioneDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdSubimpegnoEvasione> implements CpassTOrdSubimpegnoEvasioneDao {

	@Override
	public List<CpassTOrdSubimpegnoEvasione> getSubimpegni(UUID impegnoEvasioneId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdSubimpegnoEvasione tso ");
		jpql.append(" WHERE tso.cpassTOrdImpegnoEvasione.impegnoEvasioneId = :impegnoEvasioneId ");

		final Map<String, Object> params = new HashMap<>();
		params.put("impegnoEvasioneId", impegnoEvasioneId);

		final TypedQuery<CpassTOrdSubimpegnoEvasione> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void delete(UUID idSubimpegnoEvasione) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdSubimpegnoEvasione ");
		sb.append(" WHERE subimpegnoEvasioneId = :idSubimpegnoEvasione");

		final Map<String, Object> params = new HashMap<>();
		params.put("idSubimpegnoEvasione", idSubimpegnoEvasione);

		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public BigDecimal calcolaTotaleEvaso(UUID subimpegnoOrdineId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(sie.importoRipartito + sie.importoSospeso) ");
		jpql.append(" FROM CpassTOrdSubimpegnoEvasione sie ");
		jpql.append(" WHERE sie.cpassTOrdSubimpegnoOrdine.subimpegnoOrdineId = :subimpegnoOrdineId ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("subimpegnoOrdineId", subimpegnoOrdineId);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero, Integer subimpegnoAnno, Integer subimpegnoNumero,UUID testataEvasioneId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(sie.importoRipartito + sie.importoSospeso) ");
		jpql.append(" FROM CpassTOrdSubimpegnoEvasione sie ");
		jpql.append(" WHERE sie.impegnoAnno = :impegnoAnno ");
		jpql.append(" AND sie.impegnoNumero = :impegnoNumero ");
		jpql.append(" AND sie.subimpegnoAnno = :subimpegnoAnno ");
		jpql.append(" AND sie.subimpegnoNumero = :subimpegnoNumero ");
		jpql.append(" AND sie.cpassTOrdImpegnoEvasione.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.testataEvasioneId = :testataEvasioneId ");



		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("impegnoAnno", impegnoAnno);
		q.setParameter("impegnoNumero", impegnoNumero);
		q.setParameter("subimpegnoAnno", subimpegnoAnno);
		q.setParameter("subimpegnoNumero", subimpegnoNumero);
		q.setParameter("testataEvasioneId", testataEvasioneId);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public List<CpassTOrdSubimpegnoEvasione> findByIdSubimpegnoOrdine(UUID subimpegnoOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdSubimpegnoEvasione sie ");
		jpql.append(" AND sie.dataCancellazione IS NULL ");

		JpaQueryHelper.andFieldEquals(jpql, params, "ie.cpassTOrdSubimpegnoOrdine.subimpegnoOrdineId", "subimpegnoOrdineId", subimpegnoOrdineId);
		final TypedQuery<CpassTOrdSubimpegnoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdSubimpegnoEvasione> findByIdsRigaEvasioneESubimpegno(List<RigaEvasione> rigaEvasiones, Integer impegnoAnno, Integer impegnoNumero,Integer subimpegnoAnno, Integer subimpegnoNumero) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT se FROM CpassTOrdSubimpegnoEvasione se, CpassTOrdImpegnoEvasione ie ")
		.append(" WHERE se.cpassTOrdImpegnoEvasione = ie")
		.append(" AND ie.cpassTOrdRigaEvasione.rigaEvasioneId in (:rigaEvasioneIds)");
		params.put("rigaEvasioneIds", rigaEvasiones.stream().map(RigaEvasione::getId).collect(Collectors.toList()));

		JpaQueryHelper.andFieldEquals(jpql, params, "ie.impegnoAnno", "impegnoAnno", impegnoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "ie.impegnoNumero", "impegnoNumero", impegnoNumero);
		JpaQueryHelper.andFieldEquals(jpql, params, "se.impegnoAnno", "impegnoAnno", impegnoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "se.impegnoNumero", "impegnoNumero", impegnoNumero);
		JpaQueryHelper.andFieldEquals(jpql, params, "se.subimpegnoAnno", "subimpegnoAnno", subimpegnoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "se.subimpegnoNumero", "subimpegnoNumero", subimpegnoNumero);
		jpql.append(" ORDER BY ie.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.evasioneAnno"
				+ ", ie.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.evasioneNumero"
				+ ", ie.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.progressivo"
				+ ", ie.cpassTOrdRigaEvasione.progressivo");


		final TypedQuery<CpassTOrdSubimpegnoEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();

	}

	@Override
	public BigDecimal calcolaSubimpegnoEvaso(Integer annoEsercizio, Integer annoImpegno, Integer numeroImpegno,Integer annoSubImpegno, Integer numeroSubImpegno,Integer annoEsercizioSuOrdine,UUID enteId,Integer statoId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(ctoio.importoRipartito + ctoio.importoSospeso) ");
		jpql.append(" FROM CpassTOrdSubimpegnoEvasione ctoio ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" AND ctoio.impegnoAnnoEsercizio >= :impegnoAnnoEsercizio ");
		jpql.append(" AND ctoio.impegnoAnno          = :impegnoAnno ");
		jpql.append(" AND ctoio.impegnoNumero        = :impegnoNumero ");
		jpql.append(" AND ctoio.subimpegnoAnno       = :annoSubImpegno ");
		jpql.append(" AND ctoio.subimpegnoNumero     = :numeroSubImpegno ");

		//jpql.append(" AND ctoio.ente.enteId          = :enteId ");
		final Map<String, Object> params = new HashMap<>();
		if(annoEsercizioSuOrdine!=null) {
			jpql.append(" AND ctoio.cpassTOrdImpegnoEvasione.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.evasioneAnno = :annoEsercizioSuOrdine ");
			params.put("annoEsercizioSuOrdine", annoEsercizioSuOrdine);
		}

		if(statoId!=null) {
			jpql.append(" AND ctoio.cpassTOrdImpegnoEvasione.cpassTOrdRigaEvasione.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.cpassDStato.statoId = :statoId ");
			params.put("statoId", statoId);
		}

		params.put("impegnoAnnoEsercizio", annoEsercizio);
		params.put("impegnoAnno", annoImpegno);
		params.put("impegnoNumero", numeroImpegno);
		params.put("annoSubImpegno", annoSubImpegno);
		params.put("numeroSubImpegno", numeroSubImpegno);

		final TypedQuery<BigDecimal> query = composeTypedQuery(jpql, params,BigDecimal.class);
		if (query.getResultList()!=null && query.getResultList().size() > 0) {
			return query.getSingleResult() == null ? BigDecimal.ZERO : query.getSingleResult();
		} else {
			return BigDecimal.ZERO;
		}

	}
}
