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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.rda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.rda.CpassTOrdRigaRdaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdRigaRda;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTOrdRigaRdaDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdRigaRda> implements CpassTOrdRigaRdaDao {

	@Override
	public List<CpassTOrdRigaRda> getRigaRdaByRmsIdAndTestataRda(UUID rigaRmsId, UUID testataRdaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("select r.cpassTOrdRigaRda FROM CpassRRmsRigaRda r  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "r.cpassTRmsRigaRms.rigaRmsId", "rigaRmsId", rigaRmsId);
		JpaQueryHelper.andFieldEquals(jpql, params, "r.cpassTOrdRigaRda.cpassTOrdTestataRda.testataRdaId", "testataRdaId", testataRdaId);
		final TypedQuery<CpassTOrdRigaRda> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}



}
