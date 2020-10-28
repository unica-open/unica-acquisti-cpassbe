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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTListinoFornitoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassTListinoFornitore
 */
@ApplicationScoped
public class CpassTListinoFornitoreDaoImpl extends BaseAuditedEntityDaoImpl<Integer, CpassTListinoFornitore> implements CpassTListinoFornitoreDao {

	


	@Override
	public Page<CpassTListinoFornitore> findPaginated(
														String codiceOds,
														String descrizione,
														UUID fornitoreId,
														Integer oggettiSpesaId,
														int page,
														int size, 
														String sortField,
														String sortDirection
														){
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTListinoFornitore int ")
			.append(" WHERE int.dataCancellazione IS NULL ");
		
		JpaQueryHelper.andFieldEquals(jpql, params, "int.listinoFornitoreCodiceOds", "codiceOds", codiceOds);
		JpaQueryHelper.andFieldLike(jpql, params, "int.listinoFornitoreDescrizione", "descrizione", descrizione);

		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTFornitore.fornitoreId", "fornitoreId", fornitoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDOggettiSpesa.oggettiSpesaId", "oggettiSpesaId", oggettiSpesaId);


		//jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
		log.info("findPaginated", jpql.toString());
		return getPagedResult(jpql, params, page, size);	}

}
