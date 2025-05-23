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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.rms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassTRegoleSmistamentoRmsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRegoleSmistamentoRms;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTRegoleSmistamentoRmsDaoImpl extends BaseEntityDaoImpl<Integer, CpassTRegoleSmistamentoRms> implements CpassTRegoleSmistamentoRmsDao {

	@Override
	public List<CpassTRegoleSmistamentoRms> getRegoleSmistamentoRms(CpassTRegoleSmistamentoRms cpassTRegoleSmistamentoRms,UUID enteId) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTRegoleSmistamentoRms tRms ")
				.append(" WHERE tRms.cpassTEnte.enteId = :enteId ");
		params.put("enteId", enteId);

		JpaQueryHelper.andFieldEquals(jpql, params, "tRms.oggettiSpesaCodice", "oggettiSpesaCodice", cpassTRegoleSmistamentoRms.getOggettiSpesaCodice());

		if(cpassTRegoleSmistamentoRms.getOggettiSpesaCodice().equals("ALL")) {
			if(cpassTRegoleSmistamentoRms.getLivelloCpv()!=null) {
				JpaQueryHelper.andFieldEquals(jpql, params, "tRms.regolaCpv", "regolaCpv", cpassTRegoleSmistamentoRms.getCpvCodice());
				JpaQueryHelper.andFieldEquals(jpql, params, "tRms.livelloCpv", "livelloCpv",  cpassTRegoleSmistamentoRms.getLivelloCpv().toString());
			}else {
				jpql.append(" AND tRms.livelloCpv IS NULL AND tRms.cpvCodice = :cpvCodice ");
				params.put("cpvCodice", cpassTRegoleSmistamentoRms.getCpvCodice());
			}
		}

		JpaQueryHelper.andFieldEquals(jpql, params, "tRms.tuttaLaStruttura", "tuttaLaStruttura", cpassTRegoleSmistamentoRms.getTuttaLaStruttura());

		final TypedQuery<CpassTRegoleSmistamentoRms> query = composeTypedQuery(jpql, params);

		return query.getResultList();
	}

	@Override
	public void cancellaRegoleSmistamentoRms(UUID enteId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTRegoleSmistamentoRms tRms  WHERE tRms.cpassTEnte.enteId = :enteId ");
		final Map<String, Object> params = new HashMap<>();
		params.put("enteId", enteId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

}
