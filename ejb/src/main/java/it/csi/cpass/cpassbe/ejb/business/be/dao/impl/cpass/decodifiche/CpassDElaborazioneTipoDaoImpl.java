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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.decodifiche;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDElaborazioneTipoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDElaborazioneTipo;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDElaborazioneTipoDaoImpl
 */
@ApplicationScoped
public class CpassDElaborazioneTipoDaoImpl extends BaseEntityDaoImpl<Integer, CpassDElaborazioneTipo> implements CpassDElaborazioneTipoDao {

	@Override
	public Optional<CpassDElaborazioneTipo> findByElaborazioneTipoCodice(String elaborazioneTipoCodice) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassDElaborazioneTipo et ");
		jpql.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "et.elaborazioneTipoCodice", "elaborazioneTipoCodice", elaborazioneTipoCodice);
		final TypedQuery<CpassDElaborazioneTipo> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

}
