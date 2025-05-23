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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassVUltimoProgrammaBiennioDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVUltimoProgrammaBiennio;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTPbaProgrammaDaoImpl
 */
@ApplicationScoped
public class CpassVUltimoProgrammaBiennioDaoImpl extends BaseEntityDaoImpl<UUID, CpassVUltimoProgrammaBiennio> implements CpassVUltimoProgrammaBiennioDao {



	@Override
	public List<CpassVUltimoProgrammaBiennio> getProgrammaUltimoDelBiennioById(UUID programmaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassVUltimoProgrammaBiennio tp ")
				.append("WHERE 1=1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.programmaId", "programmaId", programmaId);
		final TypedQuery<CpassVUltimoProgrammaBiennio> query = composeTypedQuery(jpql, params);
		// return query.getResultList().stream().findFirst();
		return query.getResultList();
	}

}
