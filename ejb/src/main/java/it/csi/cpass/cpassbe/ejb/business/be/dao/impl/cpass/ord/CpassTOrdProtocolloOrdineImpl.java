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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdProtocolloOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdProtocolloOrdine;

/**
 * Data Access Object implementor for the entity CpassDOrdTipoProcedura
 */
@ApplicationScoped
public class CpassTOrdProtocolloOrdineImpl extends BaseAuditedEntityDaoImpl<Integer, CpassTOrdProtocolloOrdine> implements CpassTOrdProtocolloOrdineDao {

	@Override
	public List<CpassTOrdProtocolloOrdine> findProtocolloByOrderId(UUID testataOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT p FROM CpassTOrdProtocolloOrdine p ");
		jpql.append(" WHERE p.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");
		//params.put("now", new Date());
		params.put("testataOrdineId", testataOrdineId);
		final TypedQuery<CpassTOrdProtocolloOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteProtocolloByTestataordineId(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdProtocolloOrdine po ");
		sb.append(" WHERE po.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();

	}

	@Override
	public Optional<CpassTOrdProtocolloOrdine> getProtocolloOrdineByAnnoNumero(Integer annoProtocolloOrig, String numeroProtocolloOrig,UUID testataOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT p FROM CpassTOrdProtocolloOrdine p ");
		jpql.append(" WHERE p.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");
		jpql.append(" AND p.annoProtocolloOrig = :annoProtocolloOrig ");
		jpql.append(" AND p.numeroProtocolloOrig = :numeroProtocolloOrig ");
		params.put("testataOrdineId", testataOrdineId);
		params.put("annoProtocolloOrig", annoProtocolloOrig);
		params.put("numeroProtocolloOrig", numeroProtocolloOrig);
		final TypedQuery<CpassTOrdProtocolloOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<CpassTOrdProtocolloOrdine> getProtocolloOrdineByIndiceclassificazione(String indiceclassificazione,UUID testataOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT p FROM CpassTOrdProtocolloOrdine p ");
		jpql.append(" WHERE p.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");
		jpql.append(" AND p.indiceclassificazione = :indiceclassificazione ");
		params.put("testataOrdineId", testataOrdineId);
		params.put("indiceclassificazione", indiceclassificazione);
		final TypedQuery<CpassTOrdProtocolloOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<CpassTOrdProtocolloOrdine> getProtocolloOrdineByStrutturaAggregativa(String voceTitolario,String numeroFascicolo, UUID testataOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT p FROM CpassTOrdProtocolloOrdine p ");
		jpql.append(" WHERE p.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");
		jpql.append(" AND p.voceTitolario = :voceTitolario ");
		jpql.append(" AND p.numeroFascicolo = :numeroFascicolo ");
		params.put("testataOrdineId", testataOrdineId);
		final TypedQuery<CpassTOrdProtocolloOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultStream().findFirst();
	}

}
