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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassROrdRdaOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassROrdRdaOrdine;

/**
 * Data Access Object implementor for the entity CpassROrdRdaOrdine
 */
@ApplicationScoped
public class CpassROrdRdaOrdineDaoImpl extends BaseEntityDaoImpl<Integer, CpassROrdRdaOrdine> implements CpassROrdRdaOrdineDao {

	@Override
	public List<CpassROrdRdaOrdine> findByTestataOrdineId(UUID testataOrdineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByTestataOrdineTestataRda(UUID testataOrdineId, UUID testataRdaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ").append(clazz.getName());
		sb.append(" WHERE cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId");
		params.put("testataOrdineId", testataOrdineId);
		if(testataRdaId!= null) {
			sb.append(" AND cpassTOrdTestataRda.testataRdaId = :testataRdaId");
			params.put("testataRdaId", testataRdaId);
		}

		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

}
