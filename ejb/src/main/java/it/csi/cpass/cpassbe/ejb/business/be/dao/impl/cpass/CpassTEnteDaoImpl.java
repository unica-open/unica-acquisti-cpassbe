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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTEnteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTEnte
 */
@ApplicationScoped
public class CpassTEnteDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTEnte> implements CpassTEnteDao {
	@Override
	public Optional<CpassTEnte> findByCodiceFiscale(String codiceFiscale) {

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTEnte tente ")
				.append(" WHERE 1 = 1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "tente.enteCodiceFiscale", "codiceFiscale", codiceFiscale);

		final TypedQuery<CpassTEnte> query = composeTypedQuery(jpql, params);

		return query.getResultList().stream().findFirst();
	}

	@Override
	public Optional<CpassTEnte> getEnteByCodice(String enteCodice) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTEnte ute ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ute.enteCodice", "enteCodice", enteCodice);
		final TypedQuery<CpassTEnte> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

}
