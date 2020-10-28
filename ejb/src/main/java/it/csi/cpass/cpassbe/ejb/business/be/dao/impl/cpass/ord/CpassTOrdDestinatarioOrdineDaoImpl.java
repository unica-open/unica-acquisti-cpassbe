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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTOrdDestinatarioOrdineDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdDestinatarioOrdine> implements CpassTOrdDestinatarioOrdineDao {
	
	@Override
	public List<CpassTOrdDestinatarioOrdine> findByOrdine(UUID id) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTOrdDestinatarioOrdine dest ")
			.append(" WHERE dest.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "dest.cpassTOrdTestataOrdine.testataOrdineId", "idtest", id);
		TypedQuery<CpassTOrdDestinatarioOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
