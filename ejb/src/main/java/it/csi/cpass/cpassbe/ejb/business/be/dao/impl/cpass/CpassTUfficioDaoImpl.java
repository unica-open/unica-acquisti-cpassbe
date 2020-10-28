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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUfficioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTUfficio
 */
@ApplicationScoped
public class CpassTUfficioDaoImpl extends BaseAuditedEntityDaoImpl<Integer, CpassTUfficio> implements CpassTUfficioDao {

	@Override
	public List<CpassTUfficio> getUfficiBySettore(UUID settoreId) {
		
		Map<String, Object> params = new HashMap<>();
		java.util.Date now = new java.util.Date();

		StringBuilder jpql = new StringBuilder()
			.append("SELECT rus.cpassTUfficio FROM CpassRUfficioSettore rus ")
			.append(" WHERE 1 = 1 ")
			.append("AND (rus.cpassTUfficio.dataCancellazione IS NULL ")
			.append("OR (rus.cpassTUfficio.dataCancellazione IS NOT NULL and rus.cpassTUfficio.dataCancellazione > :now)) ")
			.append("AND rus.dataValiditaInizio <= :now ")
			.append("AND (rus.dataValiditaFine IS NULL ")
			.append("OR (rus.dataValiditaFine IS NOT NULL and rus.dataValiditaFine > :now)) ");
		JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTSettore.settoreId", "settoreId", settoreId);
		params.put("now", now);
	
		TypedQuery<CpassTUfficio> query = composeTypedQuery(jpql, params);
		return query.getResultList();		
		
		
//		Date now = new Date();
//		StringBuilder jpql = new StringBuilder()
//			.append(" FROM CpassDOrdTipoProcedura tp ")
//			.append(" WHERE tp.dataCancellazione IS NULL ")
//			.append(" OR (tp.dataCancellazione IS NOT NULL and tp.dataCancellazione > :now )")
//			.append(" ORDER BY tp.tipoProceduraDescrizione");
//		params.put("now", now);
		
	}
}	
