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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFruitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTFruitore;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTFruitore
 */
@ApplicationScoped
public class CpassTFruitoreDaoImpl extends BaseEntityDaoImpl<Integer, CpassTFruitore> implements CpassTFruitoreDao {

	@Override
	public Optional<CpassTFruitore> findByChiaveLogica(String fruitoreCodice, String fruitoreEnteCodiceFiscale) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTFruitore tf ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tf.fruitoreCodice", "fruitoreCodice", fruitoreCodice);
		JpaQueryHelper.andFieldEquals(jpql, params, "tf.fruitoreEnteCodiceFiscale", "fruitoreEnteCodiceFiscale", fruitoreEnteCodiceFiscale);
		final TypedQuery<CpassTFruitore> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}
}
