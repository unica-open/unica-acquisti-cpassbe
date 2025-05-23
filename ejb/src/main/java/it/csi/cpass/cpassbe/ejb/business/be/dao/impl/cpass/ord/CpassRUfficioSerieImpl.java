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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassRUfficioSerieDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRUfficioSerie;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDOrdTipoProcedura
 */
@ApplicationScoped
public class CpassRUfficioSerieImpl extends BaseEntityDaoImpl<Integer, CpassRUfficioSerie> implements CpassRUfficioSerieDao {

	@Override
	public List<CpassRUfficioSerie> getUfficioSerieByUfficioId(Integer uffId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassRUfficioSerie tord  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTUfficio.ufficioId", "uffId", uffId);
		//JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassRUfficioSerie> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}



}
