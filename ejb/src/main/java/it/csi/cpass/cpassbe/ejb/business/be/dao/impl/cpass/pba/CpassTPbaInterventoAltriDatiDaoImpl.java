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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoAltriDatiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoAltriDati;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTPbaInterventoAltriDati
 */
@ApplicationScoped
public class CpassTPbaInterventoAltriDatiDaoImpl extends BaseEntityDaoImpl<UUID, CpassTPbaInterventoAltriDati> implements CpassTPbaInterventoAltriDatiDao {

	@Override
	public List<CpassTPbaInterventoAltriDati> getByInterventoId(UUID interventoId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTPbaInterventoAltriDati iad ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "iad.cpassTPbaIntervento.interventoId", "interventoId", interventoId);

		final TypedQuery<CpassTPbaInterventoAltriDati> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteByIdIntervento(UUID interventoId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTPbaInterventoAltriDati imp");
		sb.append(" WHERE imp.cpassTPbaIntervento.interventoId = :interventoId");
		final Map<String, Object> params = new HashMap<>();
		params.put("interventoId", interventoId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public BigDecimal getImportoIvaTotByInterventoId(UUID interventoId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE(ie.ivaPrimoAnno,0) + COALESCE(ie.ivaSecondoAnno,0) + COALESCE(ie.ivaTerzoAnno,0) + COALESCE(ie.ivaAnniSuccessivi,0) )        ");
		jpql.append(" FROM CpassTPbaInterventoAltriDati ie ");
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



}
