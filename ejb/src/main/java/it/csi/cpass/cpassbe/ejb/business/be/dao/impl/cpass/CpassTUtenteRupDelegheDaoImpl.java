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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteRupDelegheDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtenteRupDeleghe;

@ApplicationScoped
public class CpassTUtenteRupDelegheDaoImpl extends BaseEntityDaoImpl<Integer, CpassTUtenteRupDeleghe> implements CpassTUtenteRupDelegheDao {

	@Override
	public void deleteLogicallyByUtenteId(UUID utenteId) {
		final Date now = new Date();
		final StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE CpassTUtenteRupDeleghe urd set urd.dataValiditaFine = :now ");
		sb.append(" WHERE urd.cpassTUtenteRup.utenteId = :utenteId");
		final Map<String, Object> params = new HashMap<>();
		params.put("now", now);
		params.put("utenteId", utenteId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();

	}


}
