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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.pba;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

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
			BigDecimal interventoImportiImportoAnnoSecondo, BigDecimal interventoImportiImportoAnniSuccessivi,
			UUID interventoId, Integer risorsaId, int page, int size) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTPbaInterventoImporti tii ")
			.append(" WHERE 1 = 1 ");


		JpaQueryHelper.andFieldEquals(jpql, params, "tii.interventoImportiImportoAnnoPrimo", "interventoImportiImportoAnnoPrimo", interventoImportiImportoAnnoPrimo);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.interventoImportiImportoAnnoSecondo", "interventoImportiImportoAnnoSecondo", interventoImportiImportoAnnoSecondo);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.interventoImportiImportoAnniSuccessivi", "interventoImportiImportoAnniSuccessivi", interventoImportiImportoAnniSuccessivi);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.cpassTPbaIntervento.interventoId", "interventoId", interventoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tii.cpassDPbaRisorsa.risorsaId", "risorsaId", risorsaId);

		jpql.append(" ORDER BY tii.interventoImportiId ");

		return getPagedResult(jpql, params, page, size);
	}

}
