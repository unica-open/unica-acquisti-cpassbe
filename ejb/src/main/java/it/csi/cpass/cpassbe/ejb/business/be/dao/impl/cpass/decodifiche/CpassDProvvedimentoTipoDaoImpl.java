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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDProvvedimentoTipoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDProvvedimentoTipo;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTComunicazione
 */
@ApplicationScoped
public class CpassDProvvedimentoTipoDaoImpl extends BaseEntityDaoImpl<Integer, CpassDProvvedimentoTipo> implements CpassDProvvedimentoTipoDao {

	@Override
	public Optional<CpassDProvvedimentoTipo> findByCodice(String codice,UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDProvvedimentoTipo pt ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "pt.provvedimentoTipoCodice", "codice", codice);
		JpaQueryHelper.andFieldEquals(jpql, params, "pt.cpassTEnte.enteId", "enteId", enteId);

		final TypedQuery<CpassDProvvedimentoTipo> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<CpassDProvvedimentoTipo> findAllByEnte(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDProvvedimentoTipo pt ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "pt.cpassTEnte.enteId", "enteId", enteId);

		final TypedQuery<CpassDProvvedimentoTipo> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
