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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDocumentoTrasportoXMLDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasportoXml;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTOrdDocumentoTrasportoXMLDaoImpl extends BaseEntityDaoImpl<Integer, CpassTOrdDocumentoTrasportoXml> implements CpassTOrdDocumentoTrasportoXMLDao {

	@Override
	public List<CpassTOrdDocumentoTrasportoXml> findDDTFileByTestataEnteDaStoricizzare(UUID enteId, Date dataStorico) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTOrdDocumentoTrasportoXml dt  WHERE TO_DATE(dt.dataConsegna ,'yyyy-MM-dd') <= :dataStorico ");
		jpql.append(" AND dt.dataSpostamento IS NULL ");
		JpaQueryHelper.andFieldNull(jpql,  "dt.dataSpostamento");
		final TypedQuery<CpassTOrdDocumentoTrasportoXml> query = composeTypedQuery(jpql, params);
		final List<CpassTOrdDocumentoTrasportoXml> result = query.getResultList();
		return result ;
	}

}
