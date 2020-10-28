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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassDPbaAcquistoVariatoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaAcquistoVariato;

/**
 * Data Access Object implementor for the entity CpassDPbaAcquistoVariato
 */
@ApplicationScoped
public class CpassDPbaAcquistoVariatoDaoImpl extends BaseEntityDaoImpl<Integer, CpassDPbaAcquistoVariato> implements CpassDPbaAcquistoVariatoDao {

	@Override
	public List<CpassDPbaAcquistoVariato> findAllOrdinato() {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassDPbaAcquistoVariato av  ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" ORDER BY av.acquistoVariatoCodice");
		
		TypedQuery<CpassDPbaAcquistoVariato> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	// Nothing to add
}
