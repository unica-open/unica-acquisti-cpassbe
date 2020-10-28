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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassDCpvDaoImpl extends BaseEntityDaoImpl<Integer, CpassDCpv> implements CpassDCpvDao {

	@Override
	public CpassDCpv findByCodice(String codice) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassDCpv cpv ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldLike  (jpql, params, "cpv.cpvCodice", "codice", codice);
		TypedQuery<CpassDCpv> query = composeTypedQuery(jpql, params);
		return query.getSingleResult();
	}

	@Override
	public List<CpassDCpv>  getCpvsByInterventoId(UUID interventoId) {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		
		StringBuilder jpql = new StringBuilder()
			.append("SELECT rus.cpassDCpv FROM CpassRInterventoCpv rus ")			
			.append(" WHERE rus.cpassTPbaIntervento.interventoId = :interventoId");	
			/*
			.append("    AND (rus.dataValiditaInizio IS NULL OR (rus.dataValiditaInizio IS NOT NULL and rus.dataValiditaInizio <= :now ))")
			.append("    AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :now ))")
			.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR (rus.cpassTUtente.dataCancellazione  IS NOT NULL and rus.cpassTUtente.dataCancellazione   > :now ))");
			*/
		params.put("interventoId", interventoId);
		//params.put("now", now);
		
		TypedQuery<CpassDCpv> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
