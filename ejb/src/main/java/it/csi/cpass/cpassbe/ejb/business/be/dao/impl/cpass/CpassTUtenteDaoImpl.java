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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * Data Access Object implementor for the entity CpassTPbaIntervento
 */
@ApplicationScoped
public class CpassTUtenteDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTUtente> implements CpassTUtenteDao {

	@Override
	public Optional<CpassTUtente> findUtenteByCf(String utenteCodiceFiscale,Boolean valido) {
		final Date now = new Date();
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTUtente ute ")
				.append(" WHERE 1 = 1   ");
		if(valido) {
			jpql.append("    AND (ute.dataCancellazione  IS NULL OR  :now < ute.dataCancellazione )");
			params.put("now", now);
		}
		JpaQueryHelper.andFieldEquals(jpql, params, "ute.utenteCodiceFiscale", "utenteCodiceFiscale", utenteCodiceFiscale);
		final TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		final List<CpassTUtente> ris = query.getResultList();
		return ris.stream().findFirst();
	}

	@Override
	public List<CpassTUtente> getUtenteBySettoreRuolo(UUID settoreId, String ruoloCodice) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rus.cpassTUtente FROM CpassRUtenteSettore rus ")
				.append(" WHERE 1 = 1 ")
				.append("    AND (rus.dataValiditaInizio IS NULL OR  rus.dataValiditaInizio <= :now )")
				.append("    AND (rus.dataValiditaFine   IS NULL OR  :now < rus.dataValiditaFine    )");


		JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTSettore.settoreId", "settoreId", settoreId);

		jpql.append(" AND EXISTS ( ");
		jpql.append(" FROM rus.cpassRRuoloUtenteSettores rrus where rrus.cpassDRuolo.ruoloCodice = :ruoloCodice ");
		jpql.append("    AND (rrus.dataValiditaInizio IS NULL OR rrus.dataValiditaInizio <= :now )");
		jpql.append("    AND (rrus.dataValiditaFine   IS NULL OR :now < rrus.dataValiditaFine  )");
		jpql.append(" ) ");

		params.put("now", now);
		params.put("ruoloCodice", ruoloCodice);
		//JpaQueryHelper.andFieldNull(jpql, "rus.cpassTUtente.dataCancellazione");
		jpql.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR  :now < rus.cpassTUtente.dataCancellazione    )");
		JpaQueryHelper.andFieldNull(jpql, "rus.cpassTSettore.dataCancellazione");
		final TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTUtente> getRupsBySettoreId(UUID settoreId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rus.cpassTUtente FROM CpassRUtenteRupSettore rus ")
				.append(" WHERE rus.cpassTSettore.settoreId = :settoreId")
				.append("    AND (rus.dataValiditaInizio IS NULL OR  rus.dataValiditaInizio <= :now )")
				.append("    AND (rus.dataValiditaFine   IS NULL OR  :now < rus.dataValiditaFine    )")
				.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR  :now < rus.cpassTUtente.dataCancellazione )");
		params.put("settoreId", settoreId);
		params.put("now", now);
		final TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassTUtente> getUtenteCorrenteDelegatoByUtenteRupId(UUID utenteRupId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rus.cpassTUtenteRupDelegato FROM CpassTUtenteRupDeleghe rus ")
				.append(" WHERE rus.cpassTUtenteRup.utenteId = :utenteRupId")
				.append("    AND (rus.dataValiditaInizio IS NULL OR  rus.dataValiditaInizio <= :now )")
				.append("    AND (rus.dataValiditaFine   IS NULL OR  :now < rus.dataValiditaFine    )");
		params.put("utenteRupId", utenteRupId);
		params.put("now", now);
		final TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Optional<CpassTUtente> getUtenteRupDelegante(UUID utenteRupDelegatoId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rus.cpassTUtenteRup FROM CpassTUtenteRupDeleghe rus ")
				.append(" WHERE rus.cpassTUtenteRupDelegato.utenteId = :utenteRupDelegatoId")
				.append(" AND (rus.dataCancellazione IS NULL OR  :now < rus.dataCancellazione  )")
				.append(" AND (rus.dataValiditaFine  IS NULL OR  :now < rus.dataValiditaFine   )");

		params.put("utenteRupDelegatoId", utenteRupDelegatoId);
		params.put("now", now);
		final TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Page<CpassTUtente> findPaginated(Boolean dirigente, Utente utente,Ruolo ruolo, Settore settore,UUID enteId, Boolean checkDataValiditaFine, int page,int size, String sortField , String sortDirection) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder().append(" SELECT DISTINCT ute FROM CpassTUtente ute");

		if(ruolo.getId() != null || (settore != null && settore.getId() != null)) {
			jpql.append(" ,CpassRUtenteSettore rus ");
		}

		jpql.append(" WHERE (ute.dataCancellazione IS NULL OR  :now < ute.dataCancellazione  ) ");

		if((ruolo.getId() != null || (settore != null && settore.getId() != null)) && checkDataValiditaFine) {
			jpql.append(" AND (rus.dataValiditaFine  IS NULL OR  :now < rus.dataValiditaFine )");
		}

		if(ruolo.getId()!= null || (settore!=null && settore.getId()!=null)) {
			jpql.append(" AND    ute = rus.cpassTUtente ");
		}



		params.put("now", now);

		if(ruolo.getId()!= null) {
			jpql.append(" AND  EXISTS ( ");
			jpql.append(" select 1 FROM CpassRRuoloUtenteSettore rrus  WHERE rrus.cpassRUtenteSettore = rus  AND (rrus.dataValiditaFine IS NULL OR  :now < rrus.dataValiditaFine ) ");
			params.put("now", now);
			JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassDRuolo.ruoloId", "ruoloId", ruolo.getId());
			jpql.append(") ");

		}

		if(dirigente) {
			jpql.append(" AND  EXISTS ( ");
			jpql.append(" select 1 FROM CpassRDirigenteSettore rds  WHERE rds.cpassTUtente = ute AND (rds.dataValiditaFine IS NULL OR :now < rds.dataValiditaFine ) ");
			if (settore != null) {
				JpaQueryHelper.andFieldEquals(jpql, params, "rds.cpassTSettore.settoreId", "settoreId", settore.getId());
			}
			jpql.append(") ");
			params.put("now", now);
		}
		JpaQueryHelper.andFieldEquals(jpql, params, "ute.utenteCodiceFiscale", "utenteCodiceFiscale", utente.getCodiceFiscale());
		JpaQueryHelper.andFieldLike  (jpql, params, "ute.utenteCognome", "utenteCognome", utente.getCognome());
		JpaQueryHelper.andFieldLike  (jpql, params, "ute.utenteNome", "utenteNome", utente.getNome());

		if(settore!=null && settore.getId()!=null) {
			JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTSettore.settoreId", "settoreId", settore.getId());
		}

		if (sortField != null ) {
			jpql.append(" ORDER BY ").append(sortField);
			if(sortDirection != null) {
				jpql.append(" ").append(sortDirection);
			}
		}

		if (sortField == null) {
			jpql.append(" ORDER BY ute.utenteCognome, ute.utenteNome ");
		}
		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public Optional<CpassTUtente> getDirigenteSettoreByUtenteIdSettoreId(UUID settoreId, UUID utenteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rds.cpassTUtente FROM CpassRDirigenteSettore rds ")
				.append(" WHERE (rds.dataValiditaFine IS NULL OR :now < rds.dataValiditaFine  ) ");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rds.cpassTSettore.settoreId", "settoreId", settoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rds.cpassTUtente.utenteId", "utenteId", utenteId);
		final TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Optional<CpassTUtente> getUtenteRupSettoreByUtenteIdSettoreId(UUID settoreId, UUID utenteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rurs.cpassTUtente FROM CpassRUtenteRupSettore rurs ")
				.append(" WHERE (rurs.dataValiditaFine IS NULL OR   :now < rurs.dataValiditaFine  ) ");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rurs.cpassTSettore.settoreId", "settoreId", settoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rurs.cpassTUtente.utenteId", "utenteId", utenteId);
		final TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}


	@Override
	public List<CpassTUtente> findPaginatedNoPage(Boolean dirigente, Utente utente, Ruolo ruolo, Settore settore,UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder().append(" SELECT DISTINCT ute FROM CpassTUtente ute,CpassRUtenteSettore rus WHERE ");
		jpql.append("     ute = rus.cpassTUtente ");
		jpql.append(" AND (ute.dataCancellazione IS NULL OR  :now < ute.dataCancellazione  )");
		jpql.append(" AND (rus.dataValiditaFine  IS NULL OR  :now < rus.dataValiditaFine   )");
		params.put("now", now);



		if(ruolo.getId()!= null) {
			jpql.append(" AND  EXISTS ( ");
			jpql.append(" select 1 FROM CpassRRuoloUtenteSettore rrus  WHERE rrus.cpassRUtenteSettore = rus  AND (rrus.dataValiditaFine IS NULL OR  :now < rrus.dataValiditaFine ) ");
			params.put("now", now);
			JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassDRuolo.ruoloId", "ruoloId", ruolo.getId());
			jpql.append(") ");
		}

		if(dirigente) {
			jpql.append(" AND  EXISTS ( ");
			jpql.append(" select 1 FROM CpassRDirigenteSettore rds  WHERE rds.cpassTUtente = ute AND (rds.dataValiditaFine IS NULL OR :now < rds.dataValiditaFine )) ");
			JpaQueryHelper.andFieldEquals(jpql, params, "rds.cpassTSettore.settoreId", "settoreId", settore.getId());
			jpql.append(") ");
			params.put("now", now);
		}
		JpaQueryHelper.andFieldEquals(jpql, params, "ute.utenteCodiceFiscale", "utenteCodiceFiscale", utente.getCodiceFiscale());
		JpaQueryHelper.andFieldLike  (jpql, params, "ute.utenteCognome", "utenteCognome", utente.getCognome());
		JpaQueryHelper.andFieldLike  (jpql, params, "ute.utenteNome", "utenteNome", utente.getNome());
		JpaQueryHelper.andFieldEquals(jpql, params, "rus.cpassTSettore.settoreId", "settoreId", settore.getId());

		final TypedQuery<CpassTUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
