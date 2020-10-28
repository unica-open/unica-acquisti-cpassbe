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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.enterprise.context.ApplicationScoped;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDUnitaMisuraDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassDUnitaMisuraDaoImpl extends BaseEntityDaoImpl<Integer, CpassDUnitaMisura> implements CpassDUnitaMisuraDao {

	@Override
	public List<CpassDUnitaMisura> findValid() {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassDUnitaMisura tp ")
			.append("WHERE tp.dataCancellazione IS NULL ")
			.append("OR (tp.dataCancellazione IS NOT NULL and tp.dataCancellazione > :now )")
			.append("ORDER BY tp.unitaMisuraDescrizione");
		params.put("now", now);
		
		TypedQuery<CpassDUnitaMisura> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
}
