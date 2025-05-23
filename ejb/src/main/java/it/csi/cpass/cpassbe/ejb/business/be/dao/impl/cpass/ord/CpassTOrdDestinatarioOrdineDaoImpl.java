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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdDestinatarioOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTOrdDestinatarioOrdineDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdDestinatarioOrdine> implements CpassTOrdDestinatarioOrdineDao {

	@Override
	public List<CpassTOrdDestinatarioOrdine> findByOrdine(UUID id) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdDestinatarioOrdine dest ")
				.append(" WHERE dest.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "dest.cpassTOrdTestataOrdine.testataOrdineId", "idtest", id);
		final TypedQuery<CpassTOrdDestinatarioOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdDestinatarioOrdine> getDestinatarioByTestataAndProgressivo(UUID idTestataOrdine,	Integer progressivo) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdDestinatarioOrdine dest ")
				.append(" WHERE dest.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "dest.cpassTOrdTestataOrdine.testataOrdineId", "idtest", idTestataOrdine);
		JpaQueryHelper.andFieldEquals(jpql, params, "dest.progressivo", "progressivo", progressivo);

		final TypedQuery<CpassTOrdDestinatarioOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteFromTestataordine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("delete from cpass_t_ord_destinatario_ordine where cpass_t_ord_destinatario_ordine.destinatario_id in (select distinct destinatario_id from cpass_v_ordine where cpass_v_ordine.testata_ordine_id = :testataOrdineId  )");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public void updateFromTestataordine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("update cpass_t_ord_riga_ordine set stato_id = 54 where riga_ordine_id in (select distinct riga_ordine_id from cpass_v_ordine where testata_ordine_id = :testataOrdineId)");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}

}
