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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassVCpvOdsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpvOds;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassVCpvOdsDaoImpl extends BaseEntityDaoImpl<Long, CpassVCpvOds> implements CpassVCpvOdsDao {
	@Override
	public List<CpassVCpvOds> getTreeCpvs() {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("FROM CpassVCpvOds vasb WHERE 1 = 1 ");
		TypedQuery<CpassVCpvOds> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
