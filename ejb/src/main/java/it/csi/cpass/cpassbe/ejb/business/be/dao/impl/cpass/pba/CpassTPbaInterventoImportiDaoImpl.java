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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.pba;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoImportiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoImporti;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassTPbaInterventoImporti
 */
@ApplicationScoped
public class CpassTPbaInterventoImportiDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTPbaInterventoImporti> implements CpassTPbaInterventoImportiDao {

	@Override
	public Page<CpassTPbaInterventoImporti> findPaginated(BigDecimal interventoImportiImportoAnnoPrimo,
			BigDecimal interventoImportiImportoAnnoSecondo, BigDecimal interventoImportiImportoAnnoTerzo, BigDecimal interventoImportiImportoAnniSuccessivi,
			UUID interventoId, Integer risorsaId, int page, int size) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTPbaInterventoImporti tii ")
				.append(" WHERE 1 = 1 ");


		JpaQueryHelper.andFieldEquals(jpql, params, "tii.interventoImportiImportoAnnoPrimo", "interventoImportiImportoAnnoPrimo", interventoImportiImportoAnnoPrimo);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.interventoImportiImportoAnnoSecondo", "interventoImportiImportoAnnoSecondo", interventoImportiImportoAnnoSecondo);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.interventoImportiImportoAnnoTerzo", "interventoImportiImportoAnnoTerzo", interventoImportiImportoAnnoTerzo);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.interventoImportiImportoAnniSuccessivi", "interventoImportiImportoAnniSuccessivi", interventoImportiImportoAnniSuccessivi);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.cpassTPbaIntervento.interventoId", "interventoId", interventoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.cpassDPbaRisorsa.risorsaId", "risorsaId", risorsaId);

		jpql.append(" ORDER BY tii.interventoImportiId ");

		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public BigDecimal getImportoTotByInterventoId(UUID interventoId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE(ie.interventoImportiImportoAnnoPrimo,0) + COALESCE(ie.interventoImportiImportoAnnoSecondo,0) + COALESCE(ie.interventoImportiImportoAnnoTerzo,0) + COALESCE(ie.interventoImportiImportoAnniSuccessivi,0) )        ");
		jpql.append(" FROM CpassTPbaInterventoImporti ie ");
		jpql.append(" WHERE ie.cpassTPbaIntervento.interventoId = :interventoId ");

		//log.info("sql --> ", jpql.toString());
		//log.info("interventoId --> ", interventoId);
		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("interventoId", interventoId);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}


	@Override
	public void deleteByIdIntervento(UUID interventoId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTPbaInterventoImporti imp");
		sb.append(" WHERE imp.cpassTPbaIntervento.interventoId = :interventoId");
		final Map<String, Object> params = new HashMap<>();
		params.put("interventoId", interventoId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();

	}

	@Override
	public List<CpassTPbaInterventoImporti> getInterventiImportiByInterventoId(UUID interventoId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTPbaInterventoImporti ie ");
		jpql.append(" WHERE ie.cpassTPbaIntervento.interventoId = :interventoId ");
		params.put("interventoId", interventoId);
		final TypedQuery<CpassTPbaInterventoImporti> query = composeTypedQuery(jpql, params);
		final List<CpassTPbaInterventoImporti> ris = query.getResultList();
		return ris;
	}

}
