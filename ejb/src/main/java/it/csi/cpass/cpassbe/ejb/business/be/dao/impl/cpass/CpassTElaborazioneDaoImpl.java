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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;
import it.csi.cpass.cpassbe.ejb.util.DateUtility;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassTComunicazione
 */
@ApplicationScoped
public class CpassTElaborazioneDaoImpl extends BaseEntityDaoImpl<Integer, CpassTElaborazione> implements CpassTElaborazioneDao {

	@Override
	public List<CpassTElaborazione> findByEntityId(String entityId) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTElaborazione el ")
				.append(" WHERE 1 = 1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "el.entitaId", "entitaId", entityId);
		jpql.append(" ORDER BY el.elaborazioneData DESC ");
		final TypedQuery<CpassTElaborazione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTElaborazione> getElaborazione(String entitaId, String elaborazioneStato, String elaborazioneTipoCodice, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append(" FROM CpassTElaborazione el  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldLike(jpql, params, "el.entitaId", "entitaId", entitaId);
		JpaQueryHelper.andFieldEquals(jpql, params, "el.cpassTEnte.enteId", "enteId", enteId);
		JpaQueryHelper.andFieldLike(jpql, params, "el.elaborazioneStato", "elaborazioneStato", elaborazioneStato);
		JpaQueryHelper.andFieldLike(jpql, params, "el.cpassDElaborazioneTipo.elaborazioneTipoCodice", "elaborazioneTipoCodice", elaborazioneTipoCodice);
		jpql.append(" ORDER BY el.elaborazioneData DESC ");
		final TypedQuery<CpassTElaborazione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTElaborazione> getElaborazioneDocumentoTrasportoScartato(String despatchAdviceId) {
		final Map<String,Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTElaborazione el ")
				.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "el.entitaId", "despatchAdviceId", despatchAdviceId);

		final TypedQuery<CpassTElaborazione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTElaborazione> getElaborazioneByEnteAndType(UUID idEnte, List<Integer> listaElaborazioneTipoId,Date dataPassata,Integer numElaborazioneDiGiornata) {
		final Map<String,Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassTElaborazione el  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "el.cpassTEnte.enteId", "idEnte", idEnte);

		if(numElaborazioneDiGiornata!= null && numElaborazioneDiGiornata>0) {
			jpql.append(" AND el.numElaborazioneDiGiornata  = :numElaborazioneDiGiornata ");
			params.put("numElaborazioneDiGiornata", numElaborazioneDiGiornata);
		}

		jpql.append(" AND el.cpassDElaborazioneTipo.elaborazioneTipoId IN (:listaElaborazioneTipoId) ");
		//jpql.append(" AND TO_DATE(el.elaborazioneData ,'yyyy-MM-dd') >= :dataPassata ");
		jpql.append(" AND el.elaborazioneData  >= :dataPassata ");


		params.put("listaElaborazioneTipoId", listaElaborazioneTipoId);
		params.put("dataPassata", DateUtility.getDateWithoutTime (dataPassata));
		jpql.append(" ORDER BY el.elaborazioneData DESC ");

		final TypedQuery<CpassTElaborazione> query = composeTypedQuery(jpql, params);
		return query.getResultList();

	}


}



