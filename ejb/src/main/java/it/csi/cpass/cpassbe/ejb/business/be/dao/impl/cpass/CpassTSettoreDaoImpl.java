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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Data Access Object implementor for the entity CpassTPbaIntervento
 */
@ApplicationScoped
public class CpassTSettoreDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTSettore> implements CpassTSettoreDao {
	
	@Override
	public Optional<CpassTSettore> findById(UUID key) {
		return super.findById(key);
	}

	@Override
	public List<CpassTSettore> getSettoriByUtenteId(UUID utenteId) {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		StringBuilder jpql = new StringBuilder()
			.append("SELECT rus.cpassTSettore FROM CpassRUtenteSettore rus ")
			.append(" WHERE 1 = 1 ")			
			.append("    AND (rus.dataValiditaInizio IS NULL OR (rus.dataValiditaInizio IS NOT NULL and rus.dataValiditaInizio <= :now ))")
			.append("    AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :now ))");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTUtente.utenteId", "utenteId", utenteId);
		jpql.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR (rus.cpassTUtente.dataCancellazione  IS NOT NULL and rus.cpassTUtente.dataCancellazione   > :now ))");

		TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Page<CpassTSettore> findPaginated(String cap, String codice, String descrizione, String indirizzo, String localita, String provincia, String telefono, Boolean utenteSettoreDefault, Utente rup, Long tipoSettoreId, UUID enteId, int page, int size, String sortField, String sortDirection) {
		Map<String, Object> params = new HashMap<>();
		
		Date now = new Date();
		
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTSettore sect ")
			.append(" WHERE ( sect.dataCancellazione IS NULL OR (sect.dataCancellazione IS NOT NULL AND sect.dataCancellazione >= :now )) ");
		
		params.put("now", now);

		JpaQueryHelper.andFieldEquals(jpql, params, "sect.settoreCap", "cap", cap);
		JpaQueryHelper.andFieldLike  (jpql, params, "sect.settoreCodice", "codice", codice);
		JpaQueryHelper.andFieldLike  (jpql, params, "sect.settoreDescrizione", "descrizione", descrizione);
		JpaQueryHelper.andFieldEquals(jpql, params, "sect.settoreIndirizzo", "indirizzo", indirizzo);
		JpaQueryHelper.andFieldEquals(jpql, params, "sect.settoreLocalita", "localita", localita);
		JpaQueryHelper.andFieldEquals(jpql, params, "sect.settoreProvincia", "provincia", provincia);
		JpaQueryHelper.andFieldEquals(jpql, params, "sect.settoreTelefono", "telefono", telefono);
		JpaQueryHelper.andFieldEquals(jpql, params, "sect.tipoSettoreId", "tipoSettoreId", tipoSettoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "sect.cpassTEnte.enteId", "enteId", enteId);

		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append("sect.settoreId").append(" ").append(sortDirection)
				.append(", sect.settoreId ASC");
		}

		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public CpassTSettore findByCodice(String codice) {
		StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTSettore sect ")
				.append(" WHERE ( sect.dataCancellazione IS NULL OR (sect.dataCancellazione IS NOT NULL AND sect.dataCancellazione >= :now )) ");

		Date now = new Date();
		Map<String, Object> params = new HashMap<>();
		params.put("now", now);

		JpaQueryHelper.andFieldLike(jpql, params, "sect.settoreCodice", "codice", codice);
		TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getSingleResult();
	}

	@Override
	public List<CpassTSettore> getSettoriByRupId(UUID rupId) {
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		StringBuilder jpql = new StringBuilder()
				.append("SELECT rus.cpassTSettore FROM CpassRUtenteRupSettore rus ")
				.append(" WHERE rus.cpassTUtente.utenteId = :rupId")	
				.append("    AND (rus.dataValiditaInizio IS NULL OR (rus.dataValiditaInizio IS NOT NULL and rus.dataValiditaInizio <= :now ))")
				.append("    AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :now ))")
				.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR (rus.cpassTUtente.dataCancellazione  IS NOT NULL and rus.cpassTUtente.dataCancellazione   > :now ))");
		params.put("rupId", rupId);
		params.put("now", now);

		TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
		
		

	}

	@Override
	public List<CpassTSettore> getSettoriBySettorePadre(UUID settoreId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTSettore sett ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "sett.cpassTSettorePadre.settoreId", "settoreId", settoreId);
		TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
}
