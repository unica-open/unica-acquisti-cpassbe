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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRUtenteRupSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteRupSettore;

@ApplicationScoped
public class CpassRUtenteRupSettoreDaoImpl extends BaseEntityDaoImpl<Integer, CpassRUtenteRupSettore> implements CpassRUtenteRupSettoreDao {



	@Override
	public void deleteLogicallyByUtenteId(UUID utenteId,UUID settoreId) {
		final Date now = new Date();
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE CpassRUtenteRupSettore urd set urd.dataValiditaFine = :now ");
		sb.append(" WHERE dataValiditaFine is null ");
		params.put("now", now);

		if (utenteId != null) {
			sb.append(" AND urd.cpassTUtente.utenteId = :utenteId");
			params.put("utenteId", utenteId);
		}

		if (settoreId != null) {
			sb.append(" AND urd.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settoreId);
		}

		final Query query = composeQuery(sb, params);
		query.executeUpdate();

	}

	@Override
	public List<CpassRUtenteRupSettore> getRupBySettoreUtenteAttivi(UUID settoreId, UUID utenteId,Date dataValiditaFine) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT rus ");
		jpql.append(" FROM CpassRUtenteRupSettore rus ");
		jpql.append(" WHERE rus.cpassTUtente.utenteId = :utenteId ");
		params.put("utenteId", utenteId);
		if (settoreId != null) {
			jpql.append(" AND rus.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settoreId);
		}
		if(dataValiditaFine!=null) {
			jpql.append(" AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :dataValiditaFine ))");
			params.put("dataValiditaFine", dataValiditaFine);
		}
		// jpql.append("AND NOT EXISTS (SELECT 1 FROM CpassRUtenteRupSettore rus2 WHERE rus2.cpassTUtente.utenteId = :utenteId AND rus2.cpassTSettore.settoreId = :settoreId AND rus2.dataValiditaFine IS NULL)");


		final TypedQuery<CpassRUtenteRupSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassRUtenteRupSettore> getRupBySettoreUtenteChiusiInData(UUID settoreId, UUID utenteId,Date dataValiditaFine) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT rus ");
		jpql.append(" FROM CpassRUtenteRupSettore rus ");
		jpql.append(" WHERE rus.cpassTUtente.utenteId = :utenteId ");
		params.put("utenteId", utenteId);
		if (settoreId != null) {
			jpql.append(" AND rus.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settoreId);
		}
		jpql.append(" AND rus.dataValiditaFine = :dataValiditaFine ");
		params.put("dataValiditaFine", dataValiditaFine);

		final TypedQuery<CpassRUtenteRupSettore> query = composeTypedQuery(jpql, params);
		List<CpassRUtenteRupSettore> ris = query.getResultList();
		return ris;
		
	}
}
