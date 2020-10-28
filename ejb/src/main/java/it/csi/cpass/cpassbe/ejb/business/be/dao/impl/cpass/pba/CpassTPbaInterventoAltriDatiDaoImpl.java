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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoAltriDatiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoAltriDati;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTPbaInterventoAltriDati
 */
@ApplicationScoped
public class CpassTPbaInterventoAltriDatiDaoImpl extends BaseEntityDaoImpl<UUID, CpassTPbaInterventoAltriDati> implements CpassTPbaInterventoAltriDatiDao {

	@Override
	public List<CpassTPbaInterventoAltriDati> getByInterventoId(UUID interventoId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTPbaInterventoAltriDati iad ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "iad.cpassTPbaIntervento.interventoId", "interventoId", interventoId);

		TypedQuery<CpassTPbaInterventoAltriDati> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
