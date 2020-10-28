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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDestinatarioEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDestinatarioEvasione;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTOrdDestinatarioEvasioneDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdDestinatarioEvasione> implements CpassTOrdDestinatarioEvasioneDao {
	
	@Override
	public List<CpassTOrdDestinatarioEvasione> findByTestataEvasione(UUID testataEvasioneId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append(" FROM CpassTOrdDestinatarioEvasione dest ")
			.append(" WHERE dest.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "dest.cpassTOrdTestataEvasione.testataEvasioneId", "testataEvasioneId", testataEvasioneId);
		TypedQuery<CpassTOrdDestinatarioEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdDestinatarioEvasione> findByIdDestinatarioOrdine(UUID idDestinatarioOrdine) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTOrdDestinatarioEvasione dest ")
				.append(" WHERE dest.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "dest.cpassTOrdDestinatarioOrdine.destinatarioId", "destinatarioId", idDestinatarioOrdine);
		TypedQuery<CpassTOrdDestinatarioEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
}
