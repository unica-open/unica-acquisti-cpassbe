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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.decodifiche;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDRuoloDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDRuolo;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTPbaIntervento
 */
@ApplicationScoped
public class CpassDRuoloDaoImpl extends BaseEntityDaoImpl<Integer, CpassDRuolo> implements CpassDRuoloDao {


	@Override
	public List<CpassDRuolo> getRuoliByUtenteSettore(UUID settoreId, UUID utenteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" SELECT rrus.cpassDRuolo FROM CpassRRuoloUtenteSettore rrus ")
				.append(" WHERE 1 = 1 ")
				.append("   AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ) ) ")
				.append("   AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ) ) ");

		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTSettore.settoreId", "settoreId", settoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente.utenteId", "utenteId", utenteId);

		final TypedQuery<CpassDRuolo> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassDRuolo> getRuoliByEnte(UUID enteId,String selezionabileDaProcedura) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" SELECT DISTINCT rrm.cpassDRuolo FROM CpassRRuoloModulo rrm ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "rrm.cpassTEnte.enteId", "enteId", enteId);
		if(selezionabileDaProcedura!= null) {
			if(selezionabileDaProcedura.equalsIgnoreCase("TRUE")) {
				jpql.append("   AND rrm.cpassDRuolo.selezionabileDaProcedura = 'SI' ");
			}else if(selezionabileDaProcedura.equalsIgnoreCase("FALSE")) {
				jpql.append("   AND rrm.cpassDRuolo.selezionabileDaProcedura = 'NO' ");
			}
		}

		final TypedQuery<CpassDRuolo> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
