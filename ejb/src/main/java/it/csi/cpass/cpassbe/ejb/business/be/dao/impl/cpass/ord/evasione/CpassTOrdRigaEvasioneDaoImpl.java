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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdRigaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTOrdRigaEvasioneDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdRigaEvasione> implements CpassTOrdRigaEvasioneDao {

	@Override
	public List<CpassTOrdRigaEvasione> findByIdDestinatarioEvasione(UUID idDestinatarioEvasione) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTOrdRigaEvasione riga ")
			.append(" WHERE riga.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatarioEvasione.destinatarioEvasioneId", "idDest", idDestinatarioEvasione);
		TypedQuery<CpassTOrdRigaEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdRigaEvasione> findByIdRigaOrdine(UUID idRigaOrdine) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTOrdRigaEvasione riga ")
				.append(" WHERE riga.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdRigaOrdine.rigaOrdineId", "idRigaOrdine", idRigaOrdine);
		TypedQuery<CpassTOrdRigaEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteByDestinatarioEvasione(UUID idDestinatario) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdImpegnoEvasione ");
		sb.append(" WHERE cpassTOrdDestinatario.destinatarioId = :idDestinatario");

		Map<String, Object> params = new HashMap<>();
		params.put("idDestinatario", idDestinatario);

		Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public List<CpassTOrdRigaEvasione> findByTestataEvasione(UUID testataEvasioneId, UUID rigaEvasioneId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdRigaEvasione riga ");
		jpql.append(" WHERE riga.dataCancellazione IS NULL ");
		jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataEvasione.testataEvasioneId = :testataEvasioneId ");

		params.put("testataEvasioneId", testataEvasioneId);

		if (rigaEvasioneId != null) {
			// esclude la riga specificata
			jpql.append(" AND riga.rigaEvasioneId <> :rigaEvasioneId ");
			params.put("rigaEvasioneId", rigaEvasioneId);
		}

		TypedQuery<CpassTOrdRigaEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public BigDecimal calcolaTotale(UUID rigaOrdineId) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(re.importoTotale) ");
		jpql.append(" FROM CpassTOrdRigaEvasione re ");
		jpql.append(" WHERE re.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ");
		jpql.append(" AND re.cpassDStatoElOrdine.statoCodice <> :statoCodice ");

		Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("rigaOrdineId", rigaOrdineId);
		q.setParameter("statoCodice", ConstantsCPassStatoElOrdine.StatoEnum.RIGA_EVASIONE_ANNULLATA.getCostante()); // DISABBINATA -> Annullata

		Object result = (Object) q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

}
