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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDOggettiSpesaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassDOggettiSpesa
 */
@ApplicationScoped
public class CpassDOggettiSpesaDaoImpl     extends BaseAuditedEntityDaoImpl<Integer, CpassDOggettiSpesa> implements CpassDOggettiSpesaDao {

	@Override
	public Page<CpassDOggettiSpesa> findPaginated(
			Boolean inventariabile
			,String codice
			,String descrizione
			,Integer aliquoteIvaid
			,Integer cpvId
			,String cpvCodice
			,Integer unitaMisuraId
			,Boolean generico
			,Boolean odsValidi
			,UUID enteId
			,int page
			,int size
			,String sortField
			,String sortDirection
			) {

		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassDOggettiSpesa os  WHERE 1=1 ");


		if(odsValidi== null || odsValidi) {
			jpql.append("   AND (os.dataCancellazione  IS NULL OR (os.dataCancellazione  IS NOT NULL and os.dataCancellazione   > :now )) ")
			.append("   AND (os.dataValiditaInizio IS NULL OR (os.dataValiditaInizio IS NOT NULL and os.dataValiditaInizio <= :now )) ")
			.append("   AND (os.dataValiditaFine   IS NULL OR (os.dataValiditaFine   IS NOT NULL and os.dataValiditaFine   > :now )) ")
			.append("   AND (os.dataCancellazione  IS NULL) ");
		}else {
			jpql.append("   AND ((os.dataValiditaFine   IS NOT NULL and os.dataValiditaFine   <= :now ) OR (os.dataCancellazione  IS NOT NULL and os.dataCancellazione   <= :now )) ");
		}
		params.put("now", now);

		JpaQueryHelper.andFieldEquals(jpql, params, "os.inventariabile", "inventariabile", inventariabile);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.oggettiSpesaCodice", "codice", codice);
		JpaQueryHelper.andFieldLike(jpql, params, "os.oggettiSpesaDescrizione", "descrizione", descrizione);
		JpaQueryHelper.andFieldLike(jpql, params, "os.cpassDCpv.cpvCodice", "cpvCodice", cpvCodice);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassDAliquoteIva.aliquoteIvaId", "aliquoteIvaid", aliquoteIvaid);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassDCpv.cpvId", "cpvId", cpvId);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassDUnitaMisura.unitaMisuraId", "unitaMisuraId", unitaMisuraId);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.generico", "generico", generico);

		if (sortField != null ) {
			jpql.append(" ORDER BY ").append(sortField);
			if(sortDirection != null) {
				jpql.append(" ").append(sortDirection);
			}
		}

		if (sortField == null) {
			jpql.append(" ORDER BY os.oggettiSpesaCodice ");
		}
		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public CpassDOggettiSpesa findByCodice(String codice, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDOggettiSpesa os ")
				.append(" WHERE os.cpassTEnte.enteId = :enteId")
				.append("   AND (os.dataCancellazione  IS NULL OR (os.dataCancellazione  IS NOT NULL and os.dataCancellazione   > :now ))")
				.append("   AND (os.dataValiditaInizio IS NULL OR (os.dataValiditaInizio IS NOT NULL and os.dataValiditaInizio <= :now ))")
				.append("   AND (os.dataValiditaFine   IS NULL OR (os.dataValiditaFine   IS NOT NULL and os.dataValiditaFine   >= :now ))");
		params.put("enteId", enteId);
		params.put("now", now);

		JpaQueryHelper.andFieldEquals(jpql, params, "os.oggettiSpesaCodice", "codice", codice);
		final TypedQuery<CpassDOggettiSpesa> query = composeTypedQuery(jpql, params);
		final List<CpassDOggettiSpesa> results = query.getResultList();
		return results != null && results.size() > 0 ? results.get(0) : null;
	}

	@Override
	public List<CpassDOggettiSpesa> findOdsByCpvId(Integer cpvId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDOggettiSpesa os ")
				.append(" WHERE (os.dataCancellazione  IS NULL OR (os.dataCancellazione  IS NOT NULL and os.dataCancellazione   > :now ))")
				.append("   AND (os.dataValiditaInizio IS NULL OR (os.dataValiditaInizio IS NOT NULL and os.dataValiditaInizio <= :now ))")
				.append("   AND (os.dataValiditaFine   IS NULL OR (os.dataValiditaFine   IS NOT NULL and os.dataValiditaFine   >= :now ))");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassDCpv.cpvId", "cpvId", cpvId);
		jpql.append("   ORDER BY os.oggettiSpesaDescrizione ");
		final TypedQuery<CpassDOggettiSpesa> query = composeTypedQuery(jpql, params);

		final List<CpassDOggettiSpesa> results = query.getResultList();

		return results;
	}

	@Override
	public List<CpassDOggettiSpesa> getOdsByEnteId(UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDOggettiSpesa os ")
				.append(" WHERE (os.dataCancellazione  IS NULL OR (os.dataCancellazione  IS NOT NULL and os.dataCancellazione   > :now ))")
				.append("   AND (os.dataValiditaInizio IS NULL OR (os.dataValiditaInizio IS NOT NULL and os.dataValiditaInizio <= :now ))")
				.append("   AND (os.dataValiditaFine   IS NULL OR (os.dataValiditaFine   IS NOT NULL and os.dataValiditaFine   >= :now ))");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassDOggettiSpesa> query = composeTypedQuery(jpql, params);

		final List<CpassDOggettiSpesa> results = query.getResultList();

		return results;
	}
}

