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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTProvvedimentoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProvvedimento;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTComunicazione
 */
@ApplicationScoped
public class CpassTProvvedimentoDaoImpl extends BaseEntityDaoImpl<Integer, CpassTProvvedimento> implements CpassTProvvedimentoDao {

	@Override
	public List<CpassTProvvedimento> getProvvedimentoByAnnoNumeroTipo(Integer anno, String numero, Integer tipoId, List<UUID> settoriId,UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTProvvedimento pr ")
				.append(" WHERE 1 = 1 ")
				.append("    AND (pr.dataValiditaInizio IS NULL OR (pr.dataValiditaInizio IS NOT NULL and pr.dataValiditaInizio <= :now ))")
				.append("    AND (pr.dataValiditaFine   IS NULL OR (pr.dataValiditaFine   IS NOT NULL and pr.dataValiditaFine   >= :now ))")	;
		//.append("    AND pr.cpassTSettore.settoreId IN (:settoriId) ");
		JpaQueryHelper.andFieldEquals(jpql, params, "pr.provvedimentoAnno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "pr.provvedimentoNumero", "numero", numero);
		JpaQueryHelper.andFieldEquals(jpql, params, "pr.cpassDProvvedimentoTipo.provvedimentoTipoId", "tipoId", tipoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "pr.cpassTEnte.enteId", "enteId", enteId);
		params.put("now", now);
		//params.put("settoriId", settoriId);
		final TypedQuery<CpassTProvvedimento> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	// Nothing to add


}
