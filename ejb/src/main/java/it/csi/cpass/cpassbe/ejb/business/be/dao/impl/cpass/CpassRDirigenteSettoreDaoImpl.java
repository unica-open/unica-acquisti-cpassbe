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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRDirigenteSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRDirigenteSettore;

@ApplicationScoped
public class CpassRDirigenteSettoreDaoImpl extends BaseEntityDaoImpl<Integer, CpassRDirigenteSettore> implements CpassRDirigenteSettoreDao {

	@Override
	public List<CpassRDirigenteSettore> findByRDirigenteSettore(UUID utenteId, UUID settoreId,Date dataValiditaFine) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT rus ");
		jpql.append(" FROM CpassRDirigenteSettore rus ");
		jpql.append(" WHERE 1 = 1 ");

		if (utenteId != null) {
			jpql.append(" AND rus.cpassTUtente.utenteId = :utenteId ");
			params.put("utenteId", utenteId);
		}

		if (settoreId != null) {
			jpql.append(" AND rus.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settoreId);
		}

		if(dataValiditaFine!=null) {
			//jpql.append(" AND rus.dataValiditaFine   IS NULL ");
			jpql.append(" AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and date_trunc('day',rus.dataValiditaFine)   > :dataValiditaFine ))");
			params.put("dataValiditaFine", dataValiditaFine);
		}

		final TypedQuery<CpassRDirigenteSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassRDirigenteSettore> findByRAltriDirigentiSuStessoSettore(UUID utenteId, UUID settoreId,Date dataValiditaFine) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT rus ");
		jpql.append(" FROM CpassRDirigenteSettore rus ");
		jpql.append(" WHERE 1 = 1 ");

		if (utenteId != null) {
			jpql.append(" AND rus.cpassTUtente.utenteId != :utenteId ");
			params.put("utenteId", utenteId);
		}

		if (settoreId != null) {
			jpql.append(" AND rus.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settoreId);
		}

		if(dataValiditaFine!=null) {
			jpql.append(" AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   > :dataValiditaFine ))");
			//jpql.append(" AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and date_trunc('day',rus.dataValiditaFine)   >= :dataValiditaFine ))");
			//jpql.append(" AND rus.dataValiditaFine   IS NULL ");

			params.put("dataValiditaFine", dataValiditaFine);
		}

		final TypedQuery<CpassRDirigenteSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteLogicallyByUtenteId(UUID utenteId,UUID settoreId,Date dataValiditaFine) {
		final Date now = new Date();
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE CpassRDirigenteSettore urd set urd.dataValiditaFine = :now ");
		sb.append(" WHERE dataValiditaFine is null ");

		if (utenteId != null) {
			sb.append(" AND urd.cpassTUtente.utenteId = :utenteId");
			params.put("utenteId", utenteId);
		}

		if (settoreId != null) {
			sb.append(" AND urd.cpassTSettore.settoreId = :settoreId ");
			params.put("settoreId", settoreId);
		}
		if(dataValiditaFine!=null) {
			sb.append(" AND (urd.dataValiditaFine   IS NULL OR (urd.dataValiditaFine   IS NOT NULL and urd.dataValiditaFine   > :dataValiditaFine ))");
			params.put("dataValiditaFine", dataValiditaFine);
		}

		params.put("now", now);
		params.put("utenteId", utenteId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();

	}


}
