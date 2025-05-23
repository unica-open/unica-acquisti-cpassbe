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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTAggiornamenoStrutturaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTAggiornamentoStruttura;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTComunicazione
 */
@ApplicationScoped
public class CpassTAggiornamentoStrutturaDaoImpl extends BaseEntityDaoImpl<Integer, CpassTAggiornamentoStruttura> implements CpassTAggiornamenoStrutturaDao{

	@Override
	public List<CpassTAggiornamentoStruttura> findByEnte(String enteCode) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTAggiornamentoStruttura el ")
				.append(" WHERE esito is null or trim(esito) = '' ");
		JpaQueryHelper.andFieldEquals(jpql, params, "el.enteCode", "enteCode", enteCode);
		final TypedQuery<CpassTAggiornamentoStruttura> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
