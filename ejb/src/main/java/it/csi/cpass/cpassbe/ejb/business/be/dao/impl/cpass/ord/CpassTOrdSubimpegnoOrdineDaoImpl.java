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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;

/**
 * Data Access Object implementor for the entity CpassTOrdSubimpegnoOrdine
 */
@ApplicationScoped
public class CpassTOrdSubimpegnoOrdineDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdSubimpegnoOrdine> implements CpassTOrdSubimpegnoOrdineDao {

	@Override
	public BigDecimal calcolaSubimpegnoOrdinato(UUID subimpegnoId,Integer impegnoAnnoEsercizio) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(x.subimpegnoImporto) ");
		jpql.append(" FROM CpassTOrdSubimpegnoOrdine x ");
		jpql.append(" WHERE cpassTSubimpegno.subimpegnoId = :subimpegnoId ");
		jpql.append(" AND impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("subimpegnoId", subimpegnoId);

		if(impegnoAnnoEsercizio!=null) {
			q.setParameter("impegnoAnnoEsercizio", impegnoAnnoEsercizio);
		}else {
			q.setParameter("impegnoAnnoEsercizio", Calendar.getInstance().get(Calendar.YEAR));
		}
		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public List<CpassTOrdSubimpegnoOrdine> getSubimpegni(UUID impegnoOrdineId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdSubimpegnoOrdine tso ");
		jpql.append(" WHERE tso.cpassTOrdImpegnoOrdine.impegnoOrdineId = :impegnoOrdineId ");

		final Map<String, Object> params = new HashMap<>();
		params.put("impegnoOrdineId", impegnoOrdineId);

		final TypedQuery<CpassTOrdSubimpegnoOrdine> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@Override
	public void delete(UUID idSubimpegnoOrdine) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdSubimpegnoOrdine ");
		sb.append(" WHERE subimpegnoOrdineId = :idSubimpegnoOrdine");

		final Map<String, Object> params = new HashMap<>();
		params.put("idSubimpegnoOrdine", idSubimpegnoOrdine);

		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public BigDecimal calcolaOrdinatoSubImpegno(Integer annoEsercizio, Integer annoImpegno, Integer numeroImpegno,Integer annoSubImpegno, Integer numeroSubImpegno,Integer annoEsercizioSuOrdine,UUID enteId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(ctoio.subimpegnoImporto) ");
		jpql.append(" FROM CpassTOrdSubimpegnoOrdine ctoio ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" AND ctoio.impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");
		jpql.append(" AND ctoio.impegnoAnno          = :impegnoAnno ");
		jpql.append(" AND ctoio.impegnoNumero        = :impegnoNumero ");
		jpql.append(" AND ctoio.subimpegnoAnno       = :annoSubImpegno ");
		jpql.append(" AND ctoio.subimpegnoNumero     = :numeroSubImpegno ");

		//jpql.append(" AND ctoio.ente.enteId          = :enteId ");
		final Map<String, Object> params = new HashMap<>();
		if(annoEsercizioSuOrdine!=null) {
			jpql.append(" AND ctoio.cpassTOrdImpegnoOrdine.cpassTOrdRigaOrdine.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno = :annoEsercizioSuOrdine ");
			params.put("annoEsercizioSuOrdine", annoEsercizioSuOrdine);
		}

		params.put("impegnoAnnoEsercizio", annoEsercizio);
		params.put("impegnoAnno", annoImpegno);
		params.put("impegnoNumero", numeroImpegno);
		params.put("annoSubImpegno", annoSubImpegno);
		params.put("numeroSubImpegno", numeroSubImpegno);
		//params.put("enteId", enteId);

		final TypedQuery<BigDecimal> query = composeTypedQuery(jpql, params,BigDecimal.class);
		if (query.getResultList()!=null && query.getResultList().size() > 0) {
			return query.getSingleResult() == null ? BigDecimal.ZERO : query.getSingleResult();
		} else {
			return BigDecimal.ZERO;
		}

	}

	@Override
	public void deleteFromRiga(UUID idRiga) {
		final StringBuilder sb = new StringBuilder();
		sb.append("delete from cpass_t_ord_subimpegno_ordine where cpass_t_ord_subimpegno_ordine.impegno_ordine_id in (select impegno_ordine_id from cpass_t_ord_impegno_ordine where cpass_t_ord_impegno_ordine.riga_ordine_id = :idRiga  )");
		final Map<String, Object> params = new HashMap<>();
		params.put("idRiga", idRiga);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public void deleteFromTestataordine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("delete from cpass_t_ord_subimpegno_ordine where cpass_t_ord_subimpegno_ordine.subimpegno_ordine_id in (select distinct subimpegno_ordine_id from cpass_v_ordine where cpass_v_ordine.testata_ordine_id = :testataOrdineId  )");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public void updateFromTestataordine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("update cpass_t_ord_subimpegno_ordine set subimpegno_importo = 0 where subimpegno_ordine_id in (select distinct subimpegno_ordine_id from cpass_v_ordine where testata_ordine_id = :testataOrdineId)");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}




}
