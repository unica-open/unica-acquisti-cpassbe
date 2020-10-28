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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTMetadatiFunzioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTMetadatiFunzione;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassTMetadatiFunzioneDaoImpl extends BaseEntityDaoImpl<Integer, CpassTMetadatiFunzione> implements CpassTMetadatiFunzioneDao {

	@Override
	public List<CpassTMetadatiFunzione> getMetadatiFunzioneByModuloFunzione(String modulo, String funzione) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTMetadatiFunzione ds ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.modulo", "modulo", modulo);
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.funzione", "funzione", funzione);
		jpql.append(" order by ds.ordinamentoLayout ");
		
		TypedQuery<CpassTMetadatiFunzione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public CpassTMetadatiFunzione getMetadatiFunzioneByModuloFunzioneChiave(String modulo, String funzione,
			String chiaveColonna) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTMetadatiFunzione ds ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.modulo", "modulo", modulo);
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.funzione", "funzione", funzione);
		JpaQueryHelper.andFieldEquals(jpql, params, "ds.chiaveColonna", "chiaveColonna", chiaveColonna);
		TypedQuery<CpassTMetadatiFunzione> query = composeTypedQuery(jpql, params);
		return query.getSingleResult();
	}

	

	

}
