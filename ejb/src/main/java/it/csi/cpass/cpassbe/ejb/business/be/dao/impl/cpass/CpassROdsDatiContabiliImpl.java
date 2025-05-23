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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassROdsDatiContabiliDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassROdsDatiContabili;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassROdsDatiContabiliImpl
 */
@ApplicationScoped
public class CpassROdsDatiContabiliImpl extends BaseAuditedEntityDaoImpl<Integer, CpassROdsDatiContabili> implements CpassROdsDatiContabiliDao {

	@Override
	public List<CpassROdsDatiContabili> getCpassROdsDatiContabiliByOds(Integer oggettiSpesaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassROdsDatiContabili t ")
				.append(" WHERE 1 = 1 " );
		JpaQueryHelper.andFieldEquals(jpql, params, "t.cpassDOggettiSpesa.oggettiSpesaId", "oggettiSpesaId", oggettiSpesaId);

		final TypedQuery<CpassROdsDatiContabili> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
