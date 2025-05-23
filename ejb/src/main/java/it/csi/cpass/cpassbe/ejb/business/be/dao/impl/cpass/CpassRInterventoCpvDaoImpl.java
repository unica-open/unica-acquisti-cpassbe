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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRInterventoCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRInterventoCpv;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTPbaIntervento
 */
@ApplicationScoped
public class CpassRInterventoCpvDaoImpl extends BaseEntityDaoImpl<Integer, CpassRInterventoCpv> implements CpassRInterventoCpvDao {


	@Override
	public List<CpassRInterventoCpv> getCpassRInterventoCpvByIntervento(UUID interventoId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassRInterventoCpv ric ")
				.append(" WHERE 1 = 1 " );
		JpaQueryHelper.andFieldEquals(jpql, params, "ric.cpassTPbaIntervento.interventoId", "interventoId", interventoId);

		final TypedQuery<CpassRInterventoCpv> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteByIdIntervento(UUID interventoId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassRInterventoCpv imp");
		sb.append(" WHERE imp.cpassTPbaIntervento.interventoId = :interventoId");
		final Map<String, Object> params = new HashMap<>();
		params.put("interventoId", interventoId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();

	}

}
