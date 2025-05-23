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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTComunicazioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTComunicazione;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTComunicazione
 */
@ApplicationScoped
public class CpassTComunicazioneDaoImpl extends BaseEntityDaoImpl<Integer, CpassTComunicazione> implements CpassTComunicazioneDao {

	@Override
	public List<CpassTComunicazione> findActive(Date checkDate) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTComunicazione tc ")
				.append("WHERE tc.comunicazioneDataInizio <= :comunicazioneDataInizio ")
				.append("AND (tc.comunicazioneDataFine IS NULL OR tc.comunicazioneDataFine >= :comunicazioneDataFine) ");
		JpaQueryHelper.andFieldNull(jpql, "tc.dataCancellazione");
		params.put("comunicazioneDataInizio", checkDate);
		params.put("comunicazioneDataFine", checkDate);

		final TypedQuery<CpassTComunicazione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
