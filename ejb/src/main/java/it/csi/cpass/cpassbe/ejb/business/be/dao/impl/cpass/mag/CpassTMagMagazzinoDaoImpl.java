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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.mag;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.mag.CpassTMagMagazzinoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.mag.CpassTMagMagazzino;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassTMagMagazzinoDaoImpl
 */
@ApplicationScoped
public class CpassTMagMagazzinoDaoImpl extends BaseEntityDaoImpl<Integer, CpassTMagMagazzino> implements CpassTMagMagazzinoDao {

	@Override
	public List<CpassTMagMagazzino> getMagazziniByEnteId(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTMagMagazzino mag ")
				.append("WHERE mag.cpassTEnte.enteId = :enteId ");
		params.put("enteId", enteId);

		final TypedQuery<CpassTMagMagazzino> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Page<CpassTMagMagazzino> findPaginated(String codice, String descrizione, UUID enteId, int page, int size,String sortField, String sortDirection) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTMagMagazzino mag ")
				.append("WHERE mag.cpassTEnte.enteId = :enteId ");
		params.put("enteId", enteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "mag.magazzinoCodice", "codice", codice);
		JpaQueryHelper.andFieldLike(jpql, params, "mag.magazzinoDescrizione", "descrizione", descrizione);



		/*
		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
			.append(", tRms.testataRmsId ASC ");
		}

		if (sortField == null) {
			jpql.append(" ORDER BY tRms.rmsAnno DESC, tRms.rmsNumero DESC ");
		}
		 */
		return getPagedResult(jpql, params, page, size);
	}


}
