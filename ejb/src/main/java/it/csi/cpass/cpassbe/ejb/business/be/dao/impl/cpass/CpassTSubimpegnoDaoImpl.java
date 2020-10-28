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
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSubimpegnoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;

/**
 * Data Access Object implementor for the entity CpassTSubimpegno
 */
@ApplicationScoped
public class CpassTSubimpegnoDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTSubimpegno> implements CpassTSubimpegnoDao {

	@Override
	public CpassTSubimpegno getSubimpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId, Integer subimpegnoAnno, Integer subimpegnoNumero) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTSubimpegno ti ");
		jpql.append(" WHERE ti.impegnoAnnoEsercizio = :impegnoAnnoEsercizio ");
		jpql.append(" AND ti.impegnoAnno = :impegnoAnno ");
		jpql.append(" AND ti.impegnoNumero = :impegnoNumero ");
		jpql.append(" AND ti.subimpegnoNumero = :subimpegnoNumero ");
		jpql.append(" AND ti.subimpegnoAnno = :subimpegnoAnno ");

		if (enteId != null) {
			jpql.append(" AND ti.cpassTEnte.enteId = :enteId ");
		}

		Map<String, Object> params = new HashMap<>();
		params.put("impegnoAnnoEsercizio", annoEsercizio);
		params.put("impegnoAnno", anno);
		params.put("impegnoNumero", numero);
		params.put("subimpegnoAnno", subimpegnoAnno);
		params.put("subimpegnoNumero", subimpegnoNumero);

		if (enteId != null) {
			params.put("enteId", enteId);
		}

		TypedQuery<CpassTSubimpegno> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList().get(0);
		} else {
			return null;
		}
	}

}
