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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTParametro
 */
@ApplicationScoped
public class CpassTFornitoreDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTFornitore> implements CpassTFornitoreDao {

	@Override
	public CpassTFornitore getFornitoreByCodice(String codice, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTFornitore tf ").append("WHERE tf.codice = :codice ");
		params.put("codice", codice);
		JpaQueryHelper.andFieldEquals(jpql, params, "tf.cpassTEnte.enteId", "enteId", enteId);

		final TypedQuery<CpassTFornitore> query = composeTypedQuery(jpql, params);
		if (query.getResultList().size() > 0) {
			return query.getResultList().get(0);
		} else {
			return null;
		}
	}


	//	@Override
	//	public Page<CpassTFornitore> findPaginated(String codice, String codiceFiscale, String partitaIva, String ragioneSociale, int page, int size, String sortField, String sortDirection) {
	//		Map<String, Object> params = new HashMap<>();
	//
	//		StringBuilder jpql = new StringBuilder()
	//				.append(" FROM CpassTFornitore tf ")
	//				.append(" WHERE 1 = 1 ");
	//
	//		JpaQueryHelper.andFieldLike(jpql, params, "tf.codice", "codice", codice);
	//		JpaQueryHelper.andFieldLike(jpql, params, "tf.codiceFiscale", "codiceFiscale", codiceFiscale);
	//		JpaQueryHelper.andFieldLike(jpql, params, "tf.partitaIva", "partitaIva", codice);
	//		JpaQueryHelper.andFieldLike(jpql, params, "tf.ragioneSociale", "ragioneSociale", ragioneSociale);
	//
	//		if (sortField != null && sortDirection != null) {
	//			jpql.append(" ORDER BY ").append(" ").append(sortDirection);
	//		}
	//
	//		TypedQuery<CpassTFornitore> query = composeTypedQuery(jpql, params);
	//		return getPagedResult(jpql, params, page, size);
	//	}

	@Override
	public List<CpassTFornitore> getFornitore(String codice, String codiceFiscale, String partitaIva, String ragioneSociale, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTFornitore tf ")
				.append(" WHERE 1 = 1 ");

		JpaQueryHelper.andFieldLike(jpql, params, "tf.codice", "codice", codice);
		JpaQueryHelper.andFieldLike(jpql, params, "tf.codiceFiscale", "codiceFiscale", codiceFiscale);
		JpaQueryHelper.andFieldLike(jpql, params, "tf.partitaIva", "partitaIva", partitaIva);
		JpaQueryHelper.andFieldLike(jpql, params, "tf.ragioneSociale", "ragioneSociale", ragioneSociale);
		JpaQueryHelper.andFieldEquals(jpql, params, "tf.cpassTEnte.enteId", "enteId", enteId);

		final TypedQuery<CpassTFornitore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}


	/*@Override
	public void deleteLogically(UUID key) {
		// TODO Auto-generated method stub

	}


	@Override
	public Optional<CpassTFornitore> findById(UUID key) {
		// TODO Auto-generated method stub
		return null;
	}
	 */


}
