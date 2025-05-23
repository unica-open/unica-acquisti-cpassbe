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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRRuoloUtenteSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRRuoloUtenteSettore;

@ApplicationScoped
public class CpassRRuoloUtenteSettoreDaoImpl extends BaseEntityDaoImpl<Integer, CpassRRuoloUtenteSettore> implements CpassRRuoloUtenteSettoreDao {

	@Override
	public List<CpassRRuoloUtenteSettore> findByRuoloUtenteSettore(Integer utenteSettoreId, Integer ruoloId) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT rus ");
		jpql.append(" FROM CpassRRuoloUtenteSettore rus ");

		jpql.append(" WHERE 1 = 1 ");

		if (utenteSettoreId != null) {
			jpql.append(" AND rus.cpassRUtenteSettore.utenteSettoreId = :utenteSettoreId ");
			params.put("utenteSettoreId", utenteSettoreId);
		}

		if (ruoloId != null) {
			jpql.append(" AND rus.cpassDRuolo.ruoloId = :ruoloId ");
			params.put("ruoloId", ruoloId);
		}

		final TypedQuery<CpassRRuoloUtenteSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	/*
	public void deleteLogicallyByUtenteId(UUID utenteId) {
		Date now = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE CpassRRuoloUtenteSettore urd set urd.dataValiditaFine = :now ");
		sb.append(" WHERE urd.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId");
		Map<String, Object> params = new HashMap<>();
		params.put("now", now);
		params.put("utenteId", utenteId);
		Query query = composeQuery(sb, params);
		query.executeUpdate();

	}
	 */
	public void deleteLogicallyByUtenteId(UUID utenteId,UUID settoreId) {
		final Date now = new Date();
		final StringBuilder sb = new StringBuilder();
		final Map<String, Object> params = new HashMap<>();

		sb.append(" Update Cpass_R_Ruolo_Utente_Settore set data_Validita_Fine = :now ");
		sb.append(" where data_Validita_Fine is null and utente_settore_id in (");
		sb.append("								select utente_settore_id ");
		sb.append("								from cpass_R_Utente_Settore ");
		sb.append("								where ");
		sb.append("								utente_id = :utenteId ");
		if(settoreId!=null) {
			sb.append("								AND	settore_id = :settoreId ");
			params.put("settoreId", settoreId);
		}
		sb.append(")");

		params.put("now", now);
		params.put("utenteId", utenteId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();

	}

}
