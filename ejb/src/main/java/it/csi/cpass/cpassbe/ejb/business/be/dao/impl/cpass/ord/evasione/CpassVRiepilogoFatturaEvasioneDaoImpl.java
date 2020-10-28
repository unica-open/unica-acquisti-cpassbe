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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassVRiepilogoFatturaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVRiepilogoFatturaEvasione;

/**
 * Data Access Object implementor for the entity CpassVRiepilogoFatturaEvasione
 */
@ApplicationScoped
public class CpassVRiepilogoFatturaEvasioneDaoImpl extends BaseEntityDaoImpl<Long, CpassVRiepilogoFatturaEvasione> implements CpassVRiepilogoFatturaEvasioneDao {

	
	@Override
	public List<CpassVRiepilogoFatturaEvasione> getRiepilogoFatturaByIdEvasione(UUID testataEvasioneId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("FROM CpassVRiepilogoFatturaEvasione vo WHERE vo.testataEvasioneId =:testataEvasioneId ");
		params.put("testataEvasioneId", testataEvasioneId);
		TypedQuery<CpassVRiepilogoFatturaEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	
}
