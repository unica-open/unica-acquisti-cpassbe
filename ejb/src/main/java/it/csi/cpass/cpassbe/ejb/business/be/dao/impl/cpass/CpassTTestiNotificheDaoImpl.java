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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTTestiNotificheDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTTestiNotifiche;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTTestiNotificheDaoImpl extends BaseEntityDaoImpl<Integer, CpassTTestiNotifiche> implements CpassTTestiNotificheDao {

	@Override
	public Optional<CpassTTestiNotifiche> findByCodice(String codice) {

		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTTestiNotifiche ctn ")
		.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ctn.codice", "codice", codice);
		final TypedQuery<CpassTTestiNotifiche> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();

	}

	@Override
	public String getCodice() {
		final Map<String, Object> params = new HashMap<>();
		final String jpql = "SELECT MAX(ctn.codice) FROM CpassTTestiNotifiche ctn WHERE ctn.codice LIKE :prefix";
		params.put("prefix", "A%");
		final TypedQuery<String> query = composeTypedQuery(jpql, params, String.class);
		final String ultimoCodice = query.getSingleResult();
		String nuovoCodice;
		if (ultimoCodice == null) {
			nuovoCodice = "A0001";
		} else {
			final int progressivo = Integer.parseInt(ultimoCodice.substring(1));
			nuovoCodice = String.format("A%04d", progressivo + 1);
		}
		return nuovoCodice;
	}


}
