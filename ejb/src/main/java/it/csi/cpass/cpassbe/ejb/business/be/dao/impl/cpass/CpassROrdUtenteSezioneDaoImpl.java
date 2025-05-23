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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassROrdUtenteSezioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdUtenteSezione;

@ApplicationScoped
public class CpassROrdUtenteSezioneDaoImpl extends BaseAuditedEntityDaoImpl<Integer, CpassROrdUtenteSezione> implements CpassROrdUtenteSezioneDao {


	@Override
	public void deleteLogicallyByUtenteId(UUID utenteId,UUID settoreId) {
		final Date now = new Date();
		final StringBuilder sb = new StringBuilder();
		final Map<String, Object> params = new HashMap<>();

		sb.append(" UPDATE CpassROrdUtenteSezione urd set urd.dataCancellazione = :now ");
		sb.append(" WHERE urd.cpassTUtente.utenteId = :utenteId");
		params.put("now", now);
		params.put("utenteId", utenteId);

		if(settoreId!=null) {
			sb.append(" and  urd.cpassTSettore.settoreId  = :settoreId");
			params.put("settoreId", settoreId);
		}

		final Query query = composeQuery(sb, params);
		query.executeUpdate();

	}

	@Override
	public List<CpassROrdUtenteSezione> getUtenteSezioneByUtenteId(UUID utenteId,UUID settoreId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassROrdUtenteSezione crnu ");
		jpql.append(" WHERE crnu.cpassTUtente.utenteId = :utenteId ");
		jpql.append(" AND (crnu.dataCancellazione is  NULL OR (crnu.dataCancellazione is NOT NULL AND crnu.dataCancellazione > :now))");
		params.put("utenteId", utenteId);
		params.put("now", new Date());
		if(settoreId!=null) {
			jpql.append(" and  crnu.cpassTOrdSezione.cpassTSettore.settoreId  = :settoreId");
			params.put("settoreId", settoreId);
		}


		final TypedQuery<CpassROrdUtenteSezione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}


}
