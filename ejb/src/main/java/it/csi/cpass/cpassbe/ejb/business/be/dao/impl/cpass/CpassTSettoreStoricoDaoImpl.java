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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreStoricoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettoreStorico;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTComunicazione
 */
@ApplicationScoped
public class CpassTSettoreStoricoDaoImpl extends BaseEntityDaoImpl<Integer, CpassTSettoreStorico> implements CpassTSettoreStoricoDao {

	@Override
	public List<CpassTSettoreStorico> findBySettoriPrecedentiBySettoreAttuale(UUID settoreId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT ss FROM CpassTSettoreStorico ss ")
				.append(" WHERE 1 = 1  ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ss.settoreAttuale.settoreId", "settoreId", settoreId);
		final TypedQuery<CpassTSettoreStorico> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	// Nothing to add

	@Override
	public Optional<CpassTSettoreStorico> isSettoreRiorganizzato(UUID idSettoreAttuale, UUID idSettorePrecedente) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT ss FROM CpassTSettoreStorico ss ")
				.append(" WHERE 1 = 1  ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ss.settoreAttuale.settoreId", "idSettoreAttuale", idSettoreAttuale);
		JpaQueryHelper.andFieldEquals(jpql, params, "ss.settoreStorico.settoreId", "idSettorePrecedente", idSettorePrecedente);
		final TypedQuery<CpassTSettoreStorico> query = composeTypedQuery(jpql, params);

		//TypedQuery<CpassVSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}
}
