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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassRNotificaUtenteDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassRNotificaUtente;

@ApplicationScoped
public class CpassRNotificaDaoImpl extends BaseEntityDaoImpl<Integer, CpassRNotificaUtente> implements CpassRNotificaUtenteDao {

	@Override
	public List<CpassRNotificaUtente> findByUtenteEStatoLette(UUID utenteId, Boolean flgLetto) {

		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassRNotificaUtente crnu ");
		jpql.append(" WHERE 1 = 1");
		jpql.append(" AND crnu.cpassTNotifica.flgGenerico = FALSE");
		jpql.append(" AND crnu.cpassTUtente.utenteId = :utenteId ");
		jpql.append(" AND crnu.cpassTNotifica.dataInizio <= :now AND (crnu.cpassTNotifica.dataFine is NULL or (crnu.cpassTNotifica.dataFine is not NULL and crnu.cpassTNotifica.dataFine > :now )) ");
		params.put("now", new Date());
		params.put("utenteId", utenteId);

		if(flgLetto!=null) {
			jpql.append(" AND crnu.flgLetto = :flgLetto ");
			params.put("flgLetto", flgLetto);
		}

		final TypedQuery<CpassRNotificaUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public CpassRNotificaUtente findByUtenteEIdNotifica(UUID utenteId, Integer idNotifica) {

		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassRNotificaUtente crnu ");
		jpql.append(" WHERE 1 = 1");
		jpql.append(" AND crnu.cpassTUtente.utenteId = :utenteId ");
		jpql.append(" AND crnu.cpassTNotifica.notificaId = :notificaId ");
		params.put("utenteId", utenteId);
		params.put("notificaId", idNotifica);
		final TypedQuery<CpassRNotificaUtente> query = composeTypedQuery(jpql, params);
		final List<CpassRNotificaUtente> results = query.getResultList();
		return results != null && results.size() > 0 ? results.get(0) : null;
	}

	@Override
	public List<CpassRNotificaUtente> findAvvisiByUtenteLetti(UUID utenteId, Boolean flgLetto) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		//jpql.append(" FROM CpassRNotificaUtente crnu ");
		jpql.append("FROM CpassRNotificaUtente crnu ");
		jpql.append(" WHERE 1 = 1");
		jpql.append(" AND crnu.cpassTNotifica.flgGenerico = TRUE");
		jpql.append(" AND crnu.cpassTUtente.utenteId = :utenteId ");
		jpql.append(" AND crnu.cpassTNotifica.dataInizio <= :now AND (crnu.cpassTNotifica.dataFine is NULL or (crnu.cpassTNotifica.dataFine is not NULL and crnu.cpassTNotifica.dataFine > :now )) ");
		params.put("now", new Date());
		params.put("utenteId", utenteId);
		if(flgLetto!=null) {
			jpql.append(" AND crnu.flgLetto = :flgLetto ");
			params.put("flgLetto", flgLetto);
		}
		final TypedQuery<CpassRNotificaUtente> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
