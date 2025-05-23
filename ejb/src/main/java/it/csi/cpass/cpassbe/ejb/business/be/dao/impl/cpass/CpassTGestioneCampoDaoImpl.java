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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTGestioneCampoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTGestioneCampo;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTFruitore
 */
@ApplicationScoped
public class CpassTGestioneCampoDaoImpl extends BaseEntityDaoImpl<Integer, CpassTGestioneCampo> implements CpassTGestioneCampoDao {

	@Override
	public List<CpassTGestioneCampo> findByEnte(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTGestioneCampo gc ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "gc.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTGestioneCampo> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	/*
	@Override
	public Optional<CpassTFruitore> findByChiaveLogica(String fruitoreCodice, String fruitoreEnteCodiceFiscale) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTFruitore tf ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tf.fruitoreCodice", "fruitoreCodice", fruitoreCodice);
		JpaQueryHelper.andFieldEquals(jpql, params, "tf.fruitoreEnteCodiceFiscale", "fruitoreEnteCodiceFiscale", fruitoreEnteCodiceFiscale);
		TypedQuery<CpassTFruitore> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}
	 */
}
