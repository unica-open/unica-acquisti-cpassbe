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
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneParametroDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazioneParametro;

/**
 * Data Access Object implementor for the entity CpassTElaborazioneParametro
 */
@ApplicationScoped
public class CpassTElaborazioneParametroDaoImpl extends BaseEntityDaoImpl<Integer, CpassTElaborazioneParametro> implements CpassTElaborazioneParametroDao {
	// Nothing to add

	@Override
	public CpassTElaborazioneParametro getParametro(Integer elaborazioneId, String chiave) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTElaborazioneParametro tp ");
		jpql.append(" WHERE tp.cpassTElaborazione.elaborazioneId = :elaborazioneId ");
		jpql.append(" AND tp.elaborazioneParametroChiave = :chiave ");

		params.put("elaborazioneId", elaborazioneId);
		params.put("chiave", chiave);

		final TypedQuery<CpassTElaborazioneParametro> query = composeTypedQuery(jpql, params);
		return query.getResultList().get(0);
	}

}
