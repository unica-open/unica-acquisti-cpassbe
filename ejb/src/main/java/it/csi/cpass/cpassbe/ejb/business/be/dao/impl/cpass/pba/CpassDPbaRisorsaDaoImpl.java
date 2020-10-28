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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.pba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaRisorsaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaRisorsa;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDPbaRisorsa
 */
@ApplicationScoped
public class CpassDPbaRisorsaDaoImpl extends BaseEntityDaoImpl<Integer, CpassDPbaRisorsa> implements CpassDPbaRisorsaDao {

	@Override
	public List<CpassDPbaRisorsa> getRisorseByTipo(String tipo) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassDPbaRisorsa risorsa  ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "risorsa.risorsaTipo", "tipo", tipo);

		jpql.append(" ORDER BY risorsa.risorsaTipo, risorsa.risorsaOrdinamento, risorsa.risorsaId ");
		
		TypedQuery<CpassDPbaRisorsa> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
