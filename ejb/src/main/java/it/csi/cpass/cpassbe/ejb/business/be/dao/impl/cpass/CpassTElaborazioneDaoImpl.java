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
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTComunicazione
 */
@ApplicationScoped
public class CpassTElaborazioneDaoImpl extends BaseEntityDaoImpl<Integer, CpassTElaborazione> implements CpassTElaborazioneDao {

	@Override
	public List<CpassTElaborazione> findByEntityId(String entityId) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTElaborazione el ")
			.append(" WHERE 1 = 1 ");
		
		JpaQueryHelper.andFieldEquals(jpql, params, "el.entitaId", "entitaId", entityId);

		TypedQuery<CpassTElaborazione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
	@Override
	public List<CpassTElaborazione> getElaborazione(String entitaId, String elaborazioneStato, String elaborazioneTipoCodice) {
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTElaborazione el ")
				.append(" WHERE 1 = 1 ");	
		
		JpaQueryHelper.andFieldLike(jpql, params, "el.entitaId", "entitaId", entitaId);
		JpaQueryHelper.andFieldLike(jpql, params, "el.elaborazioneStato", "elaborazioneStato", elaborazioneStato);
		JpaQueryHelper.andFieldLike(jpql, params, "el.cpassDElaborazioneTipo.elaborazioneTipoCodice", "elaborazioneTipoCodice", elaborazioneTipoCodice);
		
		jpql.append(" ORDER BY el.elaborazioneData DESC ");
		
		TypedQuery<CpassTElaborazione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
}
