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

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDAliquoteIvaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDAliquoteIva;
import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassDAliquoteIvaDaoImpl extends BaseEntityDaoImpl<Integer, CpassDAliquoteIva> implements CpassDAliquoteIvaDao {
	@Override
	public List<CpassDAliquoteIva> findValid() {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassDAliquoteIva tp ")
			.append("WHERE tp.dataCancellazione IS NULL ")
			.append("OR (tp.dataCancellazione IS NOT NULL and tp.dataCancellazione > :now )")
			.append("ORDER BY tp.percentuale");
		params.put("now", now);
		
		TypedQuery<CpassDAliquoteIva> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
