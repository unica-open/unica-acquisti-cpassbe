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
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaTestataDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaTestata;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

@ApplicationScoped
public class CpassTScaricoMepaTestataDaoImpl extends BaseEntityDaoImpl<Integer,CpassTScaricoMepaTestata> implements CpassTScaricoMepaTestataDao{


	@Override
	public Page<CpassTScaricoMepaTestata> getOrdiniMepaDaCaricare(Integer statoId,
			List<String> codiciUfficio,
			int page,
			int size,
			String sortField,
			String sortDirection) {
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTScaricoMepaTestata tev ")
				.append(" WHERE tev.cpassDStato.statoId = :statoId ")
				.append(" AND tev.buyerCustomerPartyId IN :codiciUfficio ");

		final Map<String,Object> params = new HashMap<>();
		params.put("statoId", statoId);
		params.put("codiciUfficio", codiciUfficio);

		if (sortField != null && sortDirection != null) {
			//jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection).append(", tev.scaricoMepaTestataId ASC ");
		}

		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public List<CpassTScaricoMepaTestata> getOrdiniMepaDaCaricareSenzaCodUfficio(Integer statoId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTScaricoMepaTestata tev ")
				.append(" WHERE tev.cpassDStato.statoId = :statoId ")
				.append(" AND tev.buyerCustomerPartyId IS NULL OR TRIM(tev.buyerCustomerPartyId) = ''");
		params.put("statoId", statoId);
		final TypedQuery<CpassTScaricoMepaTestata> query = composeTypedQuery(jpql, params);
		final List<CpassTScaricoMepaTestata> result = query.getResultList();
		return result;
	}

	@Override
	public CpassTScaricoMepaTestata findScaricoMepaTestataByOrderId(String orderId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTScaricoMepaTestata tev ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tev.orderId", "orderId", orderId);
		final TypedQuery<CpassTScaricoMepaTestata> query = composeTypedQuery(jpql, params);
		final List<CpassTScaricoMepaTestata> result = query.getResultList();
		return result != null && result.size() > 0 ? result.get(0) : null;
	}

}
