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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDPermessoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDPermesso;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDPermesso
 */
@ApplicationScoped
public class CpassDPermessoDaoImpl extends BaseEntityDaoImpl<Integer, CpassDPermesso> implements CpassDPermessoDao {

	@Override
	public List<CpassDPermesso> getByUtenteIdAndSettoreIdAndModuloId(UUID utenteId, UUID settoreId, Integer moduloId) {
		Date now = new Date();
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("SELECT rrp.cpassDPermesso FROM CpassRRuoloPermesso rrp ")
			.append("WHERE 1 = 1 ")
			.append("AND EXISTS (")
			.append(" FROM CpassRRuoloUtenteSettore rrus, CpassRRuoloModulo rrm ")
			.append(" WHERE rrm.cpassDRuolo = rrp.cpassDRuolo ")
			.append(" AND rrus.cpassDRuolo = rrp.cpassDRuolo ")

			.append("    AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ) ) ")
			.append("    AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ) ) ");
		params.put("now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente.utenteId", "utenteId", utenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTSettore.settoreId", "settoreId", settoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrm.cpassDModulo.moduloId", "moduloId", moduloId);
		
		//JpaQueryHelper.andFieldNull(jpql, "rrus.cpassRUtenteSettore.cpassTUtente.dataCancellazione");
		jpql.append("    AND (rrus.cpassRUtenteSettore.cpassTUtente.dataCancellazione  IS NULL OR (rrus.cpassRUtenteSettore.cpassTUtente.dataCancellazione  IS NOT NULL and rrus.cpassRUtenteSettore.cpassTUtente.dataCancellazione   > :now ) ) ");

		JpaQueryHelper.andFieldNull(jpql, "rrus.cpassRUtenteSettore.cpassTSettore.dataCancellazione");
		jpql.append(")");
		
		TypedQuery<CpassDPermesso> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
	@Override
	public List<CpassDPermesso> getByUtenteIdAndSettoreId(UUID utenteId, UUID settoreId) {
		Date now = new Date();
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("SELECT rrp.cpassDPermesso FROM CpassRRuoloPermesso rrp ")
			.append("WHERE 1 = 1 ")
			.append("AND EXISTS (")
			.append(" FROM CpassRRuoloUtenteSettore rrus, CpassRRuoloModulo rrm ")
			.append(" WHERE rrm.cpassDRuolo = rrp.cpassDRuolo ")
			.append(" AND rrus.cpassDRuolo = rrp.cpassDRuolo ");
		
		params.put("now", now);

		JpaQueryHelper.andCheckDateValidita(jpql, params, "rrus", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente.utenteId", "utenteId", utenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTSettore.settoreId", "settoreId", settoreId);
		JpaQueryHelper.andCheckDataCancellazione(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente", now);
		JpaQueryHelper.andFieldNull(jpql, "rrus.cpassRUtenteSettore.cpassTSettore.dataCancellazione");

		jpql.append(")");
		
		TypedQuery<CpassDPermesso> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
