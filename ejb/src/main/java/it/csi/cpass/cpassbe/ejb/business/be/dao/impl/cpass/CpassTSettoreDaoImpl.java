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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
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
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rus.cpassTSettore FROM CpassRUtenteSettore rus ")
				.append(" WHERE 1 = 1 ")
				.append("    AND (rus.dataValiditaInizio IS NULL OR  rus.dataValiditaInizio <= :now ) ")
				.append("    AND (rus.dataValiditaFine   IS NULL OR  rus.dataValiditaFine   >= :now ) ");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTUtente.utenteId", "utenteId", utenteId);
		jpql.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR (rus.cpassTUtente.dataCancellazione  IS NOT NULL and rus.cpassTUtente.dataCancellazione   > :now ))");
		final TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Page<CpassTSettore> findPaginated(String codice, String descrizione, Boolean utenteSettoreDefault, Utente rup, Integer tipoSettoreId, UUID enteId,String validi,String all, int page, int size, String sortField, String sortDirection) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTSettore sett ")
				.append(" WHERE 1 = 1 ");
		if(!all.equalsIgnoreCase("ALL")) {
			jpql.append(" AND (sett.dataCancellazione  IS NULL OR  sett.dataCancellazione  >= :now ) ");
			if(!validi.equalsIgnoreCase("true")) {
				jpql.append(" AND (sett.dataValiditaInizio IS NULL OR  sett.dataValiditaInizio <= :now ) ");
				jpql.append(" AND (sett.dataValiditaFine   IS NULL OR  sett.dataValiditaFine   >= :now ) ");
			}
			params.put("now", now);
		}

		jpql.append(" AND sett.cpassDTipoSettore.flagUtilizzabile = true  ");
		JpaQueryHelper.andFieldLike  (jpql, params, "sett.settoreCodice", "codice", codice);
		JpaQueryHelper.andFieldLike  (jpql, params, "sett.settoreDescrizione", "descrizione", descrizione);
		JpaQueryHelper.andFieldEquals(jpql, params, "sett.tipoSettoreId", "tipoSettoreId", tipoSettoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "sett.cpassTEnte.enteId", "enteId", enteId);
		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY sett.settoreId ").append(sortDirection).append(", sett.settoreId ASC");
		}
		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public Optional<CpassTSettore> findByCodice(String codice,UUID enteId,Boolean valido,String operation) {
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTSettore sett  WHERE 1 = 1 ");
		final Date now = new Date();
		final Map<String, Object> params = new HashMap<>();
		if(valido) {
			jpql.append(" AND (sett.dataCancellazione  IS NULL OR  sett.dataCancellazione  >= :now ) ");
			jpql.append(" AND (sett.dataValiditaInizio IS NULL OR  sett.dataValiditaInizio <= :now ) ");
			jpql.append(" AND (sett.dataValiditaFine   IS NULL OR  sett.dataValiditaFine   >= :now ) ");
			params.put("now", now);
		}
		if(operation.equals("like")) {
			JpaQueryHelper.andFieldLike(jpql, params, "sett.settoreCodice", "codice", codice);
		}else {
			JpaQueryHelper.andFieldEquals(jpql, params, "sett.settoreCodice", "codice", codice);
		}
		JpaQueryHelper.andFieldEquals(jpql, params, "sett.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		final Optional<CpassTSettore> ris = query.getResultList().stream().findFirst();
		return ris;
	}

	@Override
	public List<CpassTSettore> getSettoriByRupId(UUID rupId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" SELECT rus.cpassTSettore FROM CpassRUtenteRupSettore rus ")
				.append(" WHERE rus.cpassTUtente.utenteId = :rupId ")
				.append("    AND (rus.dataValiditaInizio IS NULL OR rus.dataValiditaInizio <= :now ) ")
				.append("    AND (rus.dataValiditaFine   IS NULL OR rus.dataValiditaFine   >= :now ) ")
				.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR  rus.cpassTUtente.dataCancellazione   > :now ) ");
		params.put("rupId", rupId);
		params.put("now", now);
		final TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTSettore> getSettoriBySettorePadre(UUID settoreId,Boolean valido) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTSettore sett  WHERE 1 = 1 ");
		if(valido) {
			jpql.append(" AND (sett.dataCancellazione  IS NULL OR  sett.dataCancellazione  >= :now ) ");
			jpql.append(" AND (sett.dataValiditaInizio IS NULL OR  sett.dataValiditaInizio <= :now ) ");
			jpql.append(" AND (sett.dataValiditaFine   IS NULL OR  sett.dataValiditaFine   >= :now ) ");
			params.put("now", now);
		}

		JpaQueryHelper.andFieldEquals(jpql, params, "sett.cpassTSettorePadre.settoreId", "settoreId", settoreId);
		final TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTSettore> getSettoriByEnteId(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTSettore sett  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "sett.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTSettore> getSettoriByCdc(String cdcCodice, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder().append("SELECT rus.cpassTSettore FROM CpassRSettoreCdc rus WHERE 1 = 1 AND (rus.dataCancellazione   IS NULL OR  rus.dataCancellazione   > :now ) ");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTCdc.cdcCodice", "cdcCodice", cdcCodice);
		JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
