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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioInvioNsoXmlDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioInvioNsoXml;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTOrdDestinatarioInvioNsoXml
 */
@ApplicationScoped
public class CpassTOrdDestinatarioInvioNsoXmlDaoImpl extends BaseEntityDaoImpl<Integer, CpassTOrdDestinatarioInvioNsoXml> implements CpassTOrdDestinatarioInvioNsoXmlDao {

	@Override
	public List<CpassTOrdDestinatarioInvioNsoXml> findDestinatarioInvioNsoByEnteDaStoricizzare(UUID enteId,Date daysNumb) {

		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTOrdDestinatarioInvioNsoXml din WHERE TO_DATE(din.dataConsegna,'yyyy-MM-dd') <= :daysNumb ");
		JpaQueryHelper.andFieldNull(jpql,  "dt.dataSpostamento");
		params.put("daysNumb", daysNumb);
		final TypedQuery<CpassTOrdDestinatarioInvioNsoXml> query = composeTypedQuery(jpql, params);
		final List<CpassTOrdDestinatarioInvioNsoXml> result = query.getResultList();
		return result ;
	}

}
