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
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteRupSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStoricoInterventoRup;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTPbaIntervento
 */
@ApplicationScoped
public class CpassTUtenteDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTUtente> implements CpassTUtenteDao {

	@Override
	public Optional<CpassTUtente> findUtenteByCf(String utenteCodiceFiscale) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTUtente ute ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "ute.utenteCodiceFiscale", "utenteCodiceFiscale", utenteCodiceFiscale);
		TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<CpassTUtente> getUtenteBySettoreRuolo(UUID settoreId, String ruoloCodice) {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		
		StringBuilder jpql = new StringBuilder()
			.append("SELECT rus.cpassTUtente FROM CpassRUtenteSettore rus ")
			.append(" WHERE 1 = 1 ")
			.append("    AND (rus.dataValiditaInizio IS NULL OR (rus.dataValiditaInizio IS NOT NULL and rus.dataValiditaInizio <= :now ))")
			.append("    AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :now ))");

		
		JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTSettore.settoreId", "settoreId", settoreId);
		
		jpql.append(" AND EXISTS ( ");
			jpql.append(" FROM rus.cpassRRuoloUtenteSettores rrus where rrus.cpassDRuolo.ruoloCodice = :ruoloCodice ");	
			jpql.append("    AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))");
			jpql.append("    AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))");
		jpql.append(" ) ");
		
		params.put("now", now);
		params.put("ruoloCodice", ruoloCodice);	
		
		//JpaQueryHelper.andFieldNull(jpql, "rus.cpassTUtente.dataCancellazione");
		jpql.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR (rus.cpassTUtente.dataCancellazione  IS NOT NULL and rus.cpassTUtente.dataCancellazione   > :now ))");
		
		JpaQueryHelper.andFieldNull(jpql, "rus.cpassTSettore.dataCancellazione");

		TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTUtente> getRupsBySettoreId(UUID settoreId) {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		
		StringBuilder jpql = new StringBuilder()
			.append("SELECT rus.cpassTUtente FROM CpassRUtenteRupSettore rus ")
			.append(" WHERE rus.cpassTSettore.settoreId = :settoreId")	
			.append("    AND (rus.dataValiditaInizio IS NULL OR (rus.dataValiditaInizio IS NOT NULL and rus.dataValiditaInizio <= :now ))")
			.append("    AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :now ))")
			.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR (rus.cpassTUtente.dataCancellazione  IS NOT NULL and rus.cpassTUtente.dataCancellazione   > :now ))");
			params.put("settoreId", settoreId);
			params.put("now", now);
		TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassTUtente> getUtenteCorrenteDelegatoByUtenteRupId(UUID utenteRupId) {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		
		StringBuilder jpql = new StringBuilder()
			.append("SELECT rus.cpassTUtenteRupDelegato FROM CpassTUtenteRupDeleghe rus ")
			.append(" WHERE rus.cpassTUtenteRup.utenteId = :utenteRupId")	
			.append("    AND (rus.dataValiditaInizio IS NULL OR (rus.dataValiditaInizio IS NOT NULL and rus.dataValiditaInizio <= :now ))")
			.append("    AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :now ))");
			params.put("utenteRupId", utenteRupId);
			params.put("now", now);
		TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Optional<CpassTUtente> getUtenteRupDelegante(UUID utenteRupDelegatoId) {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		
		StringBuilder jpql = new StringBuilder()
			.append("SELECT rus.cpassTUtenteRup FROM CpassTUtenteRupDeleghe rus ")
			.append(" WHERE rus.cpassTUtenteRupDelegato.utenteId = :utenteRupDelegatoId")	
			.append("    AND (rus.dataValiditaInizio IS NULL OR (rus.dataValiditaInizio IS NOT NULL and rus.dataValiditaInizio <= :now ))")
			.append("    AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :now ))");
			params.put("utenteRupDelegatoId", utenteRupDelegatoId);
			params.put("now", now);
		TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}
	
} 
