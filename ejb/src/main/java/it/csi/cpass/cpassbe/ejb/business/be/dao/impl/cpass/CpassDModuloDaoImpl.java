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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDModuloDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDModulo;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDModulo
 */
@ApplicationScoped
public class CpassDModuloDaoImpl extends BaseEntityDaoImpl<Integer, CpassDModulo> implements CpassDModuloDao {

	@Override
	public List<CpassDModulo> getByUtenteIdAndSettoreId(UUID utenteId, UUID settoreId) {
		Date now = new Date();
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("SELECT dm FROM CpassDModulo dm ")
			.append("WHERE 1 = 1 ")
			.append("AND ( ")
			.append("  EXISTS (")
			.append("    FROM CpassRRuoloUtenteSettore rrus, CpassRRuoloModulo rrm ")
			.append("    WHERE rrm.cpassDModulo = dm ")
			.append("    AND rrus.cpassDRuolo = rrm.cpassDRuolo ");
		JpaQueryHelper.andCheckDateValidita(jpql, params, "rrus", "now", now);
			
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente.utenteId", "utenteId", utenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTSettore.settoreId", "settoreId", settoreId);	
		JpaQueryHelper.andFieldNull(jpql, "rrus.cpassRUtenteSettore.cpassTUtente.dataCancellazione");
		JpaQueryHelper.andFieldNull(jpql, "rrus.cpassRUtenteSettore.cpassTSettore.dataCancellazione");	
		jpql.append("    ) ");			
		// Bug 167 - Regole visibilità - se l'utente ha ruolo ADMIN vede tutti moduli
		jpql.append("  OR EXISTS (");
		jpql.append("    FROM CpassRRuoloUtenteSettore rrus");
		jpql.append("    WHERE rrus.cpassDRuolo.ruoloCodice = '" + CpassRuoloEnum.ADMIN.getCodice() + "' ");
		JpaQueryHelper.andCheckDateValidita(jpql, params, "rrus", "now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente.utenteId", "utenteId", utenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTSettore.settoreId", "settoreId", settoreId);
		JpaQueryHelper.andCheckDataCancellazione(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente", "now", now);
		JpaQueryHelper.andFieldNull(jpql, "rrus.cpassRUtenteSettore.cpassTSettore.dataCancellazione");			
		jpql.append("    ) ");		
		jpql.append(" ) ");
		params.put("now", now);
		TypedQuery<CpassDModulo> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
