/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.mepa;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaXmlDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaXml;

@ApplicationScoped
public class CpassTScaricoMepaXmlDaoImpl  extends BaseEntityDaoImpl<Integer,CpassTScaricoMepaXml> implements CpassTScaricoMepaXmlDao {


	@Override
	public void rimuoviRecordByIdTestata(Integer testataId) {
		final StringBuilder jpql = new StringBuilder()
				.append("DELETE FROM cpassTScaricoMepaXml smxml ")
				.append(" WHERE smxml.scaricoMepaTestataId = :testataId ");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataId", testataId);

		final TypedQuery<CpassTScaricoMepaXml> query = composeTypedQuery(jpql, params);
		query.getSingleResult();
		//System.out.println("cucu");
	}
}
