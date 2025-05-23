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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDocumentoTrasportoRigaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasportoRiga;

@ApplicationScoped
public class CpassTOrdDocumentoTrasportoRigaDaoImpl extends BaseEntityDaoImpl<Integer, CpassTOrdDocumentoTrasportoRiga> implements CpassTOrdDocumentoTrasportoRigaDao {

	@Override
	public Optional<CpassTOrdDocumentoTrasportoRiga> findByRigaEvasioneId(UUID rigaEvasioneId) {
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdDocumentoTrasportoRiga dtr ")
				.append(" WHERE dtr.cpassTOrdRigaEvasione.rigaEvasioneId = :rigaEvasioneId ");

		final Map<String, Object> params = new HashMap<>();
		params.put("rigaEvasioneId", rigaEvasioneId);

		final TypedQuery<CpassTOrdDocumentoTrasportoRiga> query = composeTypedQuery(jpql, params);
		return query.getResultStream().findFirst();
	}
}
