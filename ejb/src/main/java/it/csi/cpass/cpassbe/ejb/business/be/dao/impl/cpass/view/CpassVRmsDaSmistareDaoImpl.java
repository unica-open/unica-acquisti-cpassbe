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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.view.CpassVRmsDaSmistareDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVRmsDaSmistare;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassVRmsDaSmistareDaoImpl
 */
@ApplicationScoped
public class CpassVRmsDaSmistareDaoImpl extends BaseEntityDaoImpl<Long, CpassVRmsDaSmistare> implements CpassVRmsDaSmistareDao {

	@Override
	public List<CpassVRmsDaSmistare> getRmsDaSmistare(CpassVRmsDaSmistare cpassVRmsDaSmistare) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassVRmsDaSmistare vs where 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "vs.enteId", "enteId", cpassVRmsDaSmistare.getEnteId());

		final TypedQuery<CpassVRmsDaSmistare> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	/*
	@Override
	public Optional<CpassVSettore> getMySectorFather(UUID settoreId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("select vsFather FROM CpassVSettore vs, CpassVSettore vsFather WHERE vs.settoreIdPadre = vsFather.settoreId ");

		JpaQueryHelper.andFieldEquals(jpql, params, "vs.settoreId", "settoreId", settoreId);
		TypedQuery<CpassVSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}
	 */
}
