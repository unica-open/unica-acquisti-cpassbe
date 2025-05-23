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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.decodifiche;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDTipoSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDTipoSettore;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassDTipoSettoreDaoImpl extends BaseEntityDaoImpl<Integer, CpassDTipoSettore> implements CpassDTipoSettoreDao {


	@Override
	public List<CpassDTipoSettore> findByEnteId(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDTipoSettore ts ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ts.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassDTipoSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassDTipoSettore> getTipoSettoreByCodeAndEnteId(String tipoSettoreCodice, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDTipoSettore ts ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ts.cpassTEnte.enteId", "enteId", enteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "ts.tipoSettoreCodice", "tipoSettoreCodice", tipoSettoreCodice);

		final TypedQuery<CpassDTipoSettore> query = composeTypedQuery(jpql, params);
		final Optional<CpassDTipoSettore> ris = query.getResultList().stream().findFirst();
		return ris;
	}

}
