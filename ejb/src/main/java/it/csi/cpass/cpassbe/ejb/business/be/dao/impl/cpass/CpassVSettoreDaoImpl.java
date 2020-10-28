/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassVSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVSettore;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassVSettoreDaoImpl
 */
@ApplicationScoped
public class CpassVSettoreDaoImpl extends BaseEntityDaoImpl<UUID, CpassVSettore> implements CpassVSettoreDao {
	@Override
	public List<CpassVSettore> getSettoreTreeByEnte(UUID enteId){
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("FROM CpassVSettore vs WHERE 1 = 1 ");
		
		JpaQueryHelper.andFieldEquals(jpql, params, "vs.enteId", "enteId", enteId);
		TypedQuery<CpassVSettore> query = composeTypedQuery(jpql, params);
		
		return query.getResultList();
	}

	@Override
	public Optional<CpassVSettore> getMySectorFather(UUID settoreId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("select vsFather FROM CpassVSettore vs, CpassVSettore vsFather WHERE vs.settoreIdPadre = vsFather.settoreId ");
		
		JpaQueryHelper.andFieldEquals(jpql, params, "vs.settoreId", "settoreId", settoreId);
		TypedQuery<CpassVSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}
}
