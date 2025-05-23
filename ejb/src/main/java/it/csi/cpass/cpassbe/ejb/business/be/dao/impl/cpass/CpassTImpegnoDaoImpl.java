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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTImpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;

/**
 * Data Access Object implementor for the entity CpassTImpegnoDao
 */
@ApplicationScoped
public class CpassTImpegnoDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTImpegno> implements CpassTImpegnoDao {

	@Override
	public List<CpassTImpegno> getImpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTImpegno ti ");
		jpql.append(" WHERE ti.impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");
		jpql.append(" AND ti.impegnoAnno = :impegnoAnno ");
		jpql.append(" AND ti.impegnoNumero = :impegnoNumero ");

		final Map<String, Object> params = new HashMap<>();
		params.put("impegnoAnnoEsercizio", annoEsercizio);
		params.put("impegnoAnno", anno);
		params.put("impegnoNumero", numero);

		if (enteId != null) {
			jpql.append(" AND ti.cpassTEnte.enteId = :enteId ");
			params.put("enteId", enteId);
		}
		jpql.append(" order by  ti.impegnoAnnoEsercizio,ti.impegnoAnno, ti.impegnoNumero, ti.cpassTEnte.enteId  ");


		final TypedQuery<CpassTImpegno> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
