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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche.CpassDCpvDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassDCpvDaoImpl extends BaseEntityDaoImpl<Integer, CpassDCpv> implements CpassDCpvDao {

	@Override
	public CpassDCpv findByCodice(String codice) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassDCpv cpv ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldLike  (jpql, params, "cpv.cpvCodice", "codice", codice);
		//JpaQueryHelper.andFieldEquals  (jpql, params, "cpv.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassDCpv> query = composeTypedQuery(jpql, params);
		return query.getSingleResult();
	}

	@Override
	public List<CpassDCpv>  getCpvsByInterventoId(UUID interventoId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT rus.cpassDCpv FROM CpassRInterventoCpv rus ")
				.append(" WHERE rus.cpassTPbaIntervento.interventoId = :interventoId")
				.append("    AND (rus.dataValiditaInizio IS NULL OR (rus.dataValiditaInizio IS NOT NULL and rus.dataValiditaInizio <= :now ))")
				.append("    AND (rus.dataValiditaFine   IS NULL OR (rus.dataValiditaFine   IS NOT NULL and rus.dataValiditaFine   >= :now ))")
				.append("    AND (rus.cpassTUtente.dataCancellazione  IS NULL OR (rus.cpassTUtente.dataCancellazione  IS NOT NULL and rus.cpassTUtente.dataCancellazione   > :now ))");

		params.put("interventoId", interventoId);
		params.put("now", now);

		final TypedQuery<CpassDCpv> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Page<CpassDCpv> findCpvOdsPaginated(
			String codiceOds
			,String descrizioneOds
			,Integer cpvId
			,String genericoStr
			,UUID enteId
			,int page
			,int size
			,String sortField
			,String sortDirection
			) {

		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();

		final StringBuilder jpql = new StringBuilder()
				.append("select DISTINCT cpv FROM CpassDCpv cpv , CpassDOggettiSpesa os ")
				.append(" WHERE 1 = 1 ")
				.append(" AND (os.dataCancellazione  IS NULL OR  os.dataCancellazione   > :now ) ")
				.append("   AND (os.dataValiditaInizio IS NULL OR  os.dataValiditaInizio <= :now ) ")
				.append("   AND (os.dataValiditaFine   IS NULL OR  os.dataValiditaFine   >= :now ) ")
				.append("   AND os.cpassDCpv = cpv ")
				.append("   AND os.cpassTEnte.enteId = :enteId ");
		params.put("now", now);
		params.put("enteId", enteId);

		JpaQueryHelper.andFieldEquals(jpql, params, "os.oggettiSpesaCodice", "codice", codiceOds);
		JpaQueryHelper.andFieldLike(jpql, params, "os.oggettiSpesaDescrizione", "descrizione", descrizioneOds);
		JpaQueryHelper.andFieldEquals(jpql, params, "os.cpassDCpv.cpvId", "cpvId", cpvId);

		if(!org.apache.commons.lang3.StringUtils.isEmpty(genericoStr)) {
			JpaQueryHelper.andFieldEquals(jpql, params, "os.generico", "generico", Boolean.valueOf(genericoStr));
		}

		return getPagedResult(jpql, params, page, size);

	}



}
