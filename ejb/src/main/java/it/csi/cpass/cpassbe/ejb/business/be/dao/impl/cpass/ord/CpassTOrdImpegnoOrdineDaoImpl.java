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
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoOrdine tio ");
		jpql.append(" WHERE tio.cpassTOrdRigaOrdine.cpassTOrdDestinatario.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");

		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);

		final TypedQuery<CpassTOrdImpegnoOrdine> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public BigDecimal calcolaOrdinato(UUID impegnoId, Integer impegnoAnnoEsercizio,UUID testataOrdineId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(ctoio.importo) ");
		jpql.append(" FROM CpassTOrdImpegnoOrdine ctoio ");
		jpql.append(" WHERE cpassTImpegno.impegnoId = :impegnoId ");
		jpql.append(" AND impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");


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

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("impegnoId", impegnoId);

		if(impegnoAnnoEsercizio==null) {
			q.setParameter("impegnoAnnoEsercizio", Calendar.getInstance().get(Calendar.YEAR));
		}else {
			q.setParameter("impegnoAnnoEsercizio", impegnoAnnoEsercizio);
		}

		if (testataOrdineId != null) {
			q.setParameter("testataOrdineId", testataOrdineId);
		}

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public List<CpassTOrdImpegnoOrdine> getImpegni(UUID rigaOrdineId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoOrdine tio ");
		jpql.append(" WHERE tio.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ");

		final Map<String, Object> params = new HashMap<>();
		params.put("rigaOrdineId", rigaOrdineId);

		final TypedQuery<CpassTOrdImpegnoOrdine> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public List<CpassTOrdImpegnoOrdine> getImpegniNonPresentiEvasione(UUID rigaOrdineId, UUID rigaEvasioneId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdImpegnoOrdine tio ");
		jpql.append(" WHERE tio.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ");

		if (rigaEvasioneId != null) {
			jpql.append(" AND NOT EXISTS ( ");
			jpql.append("   SELECT ie FROM CpassTOrdImpegnoEvasione ie ");
			jpql.append("   WHERE ie.cpassTOrdImpegnoOrdine.impegnoOrdineId = tio.impegnoOrdineId ");
			jpql.append("   AND ie.cpassTOrdRigaEvasione.rigaEvasioneId = :rigaEvasioneId ");
			jpql.append(" ) ");
		}

		final Map<String, Object> params = new HashMap<>();
		params.put("rigaOrdineId", rigaOrdineId);
		if (rigaEvasioneId != null) {
			params.put("rigaEvasioneId", rigaEvasioneId);
		}

		final TypedQuery<CpassTOrdImpegnoOrdine> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void deleteByRiga(UUID rigaOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdImpegnoOrdine ");
		sb.append(" WHERE cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId");
		final Map<String, Object> params = new HashMap<>();
		params.put("rigaOrdineId", rigaOrdineId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}


	@Override
	public void deleteFromTestataordine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("delete from cpass_t_ord_impegno_ordine where cpass_t_ord_impegno_ordine.impegno_ordine_id in (select distinct impegno_ordine_id from cpass_v_ordine where cpass_v_ordine.testata_ordine_id = :testataOrdineId  )");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}







	@Override
	public BigDecimal calcolaOrdinatoImpegno(Integer annoEsercizio, Integer annoImpegno, Integer numeroImpegno,Integer annoEsercizioSuOrdine,UUID enteId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(ctoio.importo) ");
		jpql.append(" FROM CpassTOrdImpegnoOrdine ctoio ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" AND ctoio.impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");
		jpql.append(" AND ctoio.impegnoAnno          = :impegnoAnno ");
		jpql.append(" AND ctoio.impegnoNumero        = :impegnoNumero ");
		//jpql.append(" AND ctoio.ente.enteId          = :enteId ");
		final Map<String, Object> params = new HashMap<>();
		if(annoEsercizioSuOrdine!=null) {
			jpql.append(" AND ctoio.cpassTOrdRigaOrdine.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno = :annoEsercizioSuOrdine ");
			params.put("annoEsercizioSuOrdine", annoEsercizioSuOrdine);
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

	@Override
	public void updateFromTestataordine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("update cpass_t_ord_impegno_ordine set importo = 0 where impegno_ordine_id in (select distinct impegno_ordine_id from cpass_v_ordine where testata_ordine_id = :testataOrdineId)");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}



}
