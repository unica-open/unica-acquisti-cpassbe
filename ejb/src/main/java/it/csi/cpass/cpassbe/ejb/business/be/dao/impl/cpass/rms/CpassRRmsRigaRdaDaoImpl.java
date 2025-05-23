/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.rms;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassRRmsRigaRdaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassRRmsRigaRda;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassRRmsRigaRdaDaoImpl extends BaseEntityDaoImpl<Integer, CpassRRmsRigaRda>  implements CpassRRmsRigaRdaDao {

	@Override
	public void deleteByRdaId(UUID rigaRdaId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassRRmsRigaRda rr");
		sb.append(" WHERE rr.cpassTOrdRigaRda.rigaRdaId = :rigaRdaId");
		final Map<String, Object> params = new HashMap<>();
		params.put("rigaRdaId", rigaRdaId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public List<CpassRRmsRigaRda> getRRmsRdaRmsByRigheRdaRmsId(UUID rigaRdaId,UUID rigaRmsId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("  FROM CpassRRmsRigaRda r WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "r.cpassTOrdRigaRda.rigaRdaId", "rigaRdaId", rigaRdaId);
		JpaQueryHelper.andFieldEquals(jpql, params, "r.cpassTRmsRigaRms.rigaRmsId", "rigaRmsId", rigaRmsId);
		final TypedQuery<CpassRRmsRigaRda> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
