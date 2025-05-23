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
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTParametroDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTParametro;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTParametro
 */
@ApplicationScoped
public class CpassTParametroDaoImpl extends BaseEntityDaoImpl<Integer, CpassTParametro> implements CpassTParametroDao {

	@Override
	public CpassTParametro getParametro(String chiave, String riferimento, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTParametro tp ");
		jpql.append(" WHERE tp.abilitata = :abilitata ");
		jpql.append(" AND tp.chiave = :chiave ");
		params.put("abilitata", Boolean.TRUE);
		params.put("chiave", chiave);

		if(enteId!=null) {
			jpql.append(" AND ( tp.cpassTEnte.enteId = :enteId OR tp.cpassTEnte.enteId IS NULL ) ");
			params.put("enteId", enteId);
		}
		if (riferimento != null) {
			jpql.append(" AND tp.riferimento = :riferimento ");
			params.put("riferimento", riferimento);
		}

		final TypedQuery<CpassTParametro> query = composeTypedQuery(jpql, params);
		final List<CpassTParametro> result = query.getResultList();
		final CpassTParametro ris = result != null && result.size() > 0 ? result.get(0) : null;
		return ris;
	}

	@Override
	public List<CpassTParametro> getParametriByRiferimentoAndAmbiente(String riferimento, String ambiente, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTParametro tp ");
		jpql.append(" WHERE tp.abilitata = :abilitata ");
		jpql.append(" AND ( tp.cpassTEnte.enteId = :enteId OR tp.cpassTEnte.enteId IS NULL ) ");

		params.put("abilitata", Boolean.TRUE);
		params.put("enteId", enteId);

		if (riferimento != null) {
			jpql.append(" AND tp.riferimento = :riferimento ");
			params.put("riferimento", riferimento);
		}

		if (ambiente != null) {
			jpql.append(" AND tp.ambiente = :ambiente ");
			params.put("ambiente", ambiente);
		}

		final TypedQuery<CpassTParametro> query = composeTypedQuery(jpql, params);
		final List<CpassTParametro> list = query.getResultList();
		return list;
	}

	@Override
	public List<CpassTParametro> getParametriByChiaveRiferimentoAndAmbiente(String chiave, String riferimento, String ambiente, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTParametro tp ");
		jpql.append(" WHERE tp.abilitata = :abilitata ");
		jpql.append(" AND ( tp.cpassTEnte.enteId = :enteId OR tp.cpassTEnte.enteId IS NULL ) ");

		params.put("abilitata", Boolean.TRUE);
		params.put("enteId", enteId);

		JpaQueryHelper.andFieldEquals(jpql, params, "tp.abilitata", "abilitata", Boolean.TRUE);
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.chiave", "chiave", chiave);
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.riferimento", "riferimento", riferimento);
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.ambiente", "ambiente", ambiente);

		final TypedQuery<CpassTParametro> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public CpassTParametro getParametroByChiave(String chiave) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTParametro tp ");
		jpql.append(" WHERE tp.abilitata = :abilitata ");
		jpql.append(" AND tp.chiave = :chiave ");

		params.put("abilitata", Boolean.TRUE);
		params.put("chiave", chiave);


		final TypedQuery<CpassTParametro> query = composeTypedQuery(jpql, params);
		final List<CpassTParametro> result = query.getResultList();
		return result != null && result.size() > 0 ? result.get(0) : null;
	}

}
