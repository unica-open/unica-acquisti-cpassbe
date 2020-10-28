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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassDOrdTipoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTComunicazione;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoOrdine;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDOrdTipoOrdine
 */
@ApplicationScoped
public class CpassDOrdTipoOrdineDaoImpl extends BaseEntityDaoImpl<Integer, CpassDOrdTipoOrdine> implements CpassDOrdTipoOrdineDao {

	@Override
	public List<CpassDOrdTipoOrdine> findValid() {
		Map<String, Object> params = new HashMap<>();
		java.util.Date now = new java.util.Date();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassDOrdTipoOrdine to ")
			.append("WHERE to.dataCancellazione IS NULL ")
			.append("OR (to.dataCancellazione IS NOT NULL and to.dataCancellazione > :now) ")
			.append("ORDER BY to.tipologiaDocumentoDescrizione");
		params.put("now", now);
		
		TypedQuery<CpassDOrdTipoOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}	
