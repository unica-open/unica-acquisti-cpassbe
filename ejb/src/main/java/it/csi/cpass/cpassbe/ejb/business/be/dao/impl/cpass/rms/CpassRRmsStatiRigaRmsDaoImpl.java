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
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassRRmsStatiRigaRmsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassRRmsStatiRigaRms;

@ApplicationScoped
public class CpassRRmsStatiRigaRmsDaoImpl extends BaseEntityDaoImpl<Integer, CpassRRmsStatiRigaRms>  implements CpassRRmsStatiRigaRmsDao {
	@Override
	public void deleteByRmsId(UUID rigaRmsId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassRRmsStatiRigaRms rr");
		sb.append(" WHERE rr.cpassTRmsRigaRms.rigaRmsId = :rigaRmsId");
		final Map<String, Object> params = new HashMap<>();
		params.put("rigaRmsId", rigaRmsId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

}
