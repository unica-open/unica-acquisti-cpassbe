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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.view.CpassVSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVSettore;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassVSettoreDaoImpl
 */
@ApplicationScoped
public class CpassVSettoreDaoImpl extends BaseEntityDaoImpl<UUID, CpassVSettore> implements CpassVSettoreDao {
	@Override
	public List<CpassVSettore> getSettoreTreeByEnte(UUID enteId, String validi){
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassVSettore vs WHERE 1 = 1 ");
		if(validi == null || !validi.equalsIgnoreCase("all")) {
			final Date now  = new Date();
			jpql.append(" AND (vs.dataValiditaInizio IS NULL OR  vs.dataValiditaInizio <= :now ) ");
			jpql.append(" AND (vs.dataValiditaFine   IS NULL OR  vs.dataValiditaFine   >= :now ) ");
			params.put("now", now);
		}
		JpaQueryHelper.andFieldEquals(jpql, params, "vs.enteId", "enteId", enteId);
		final TypedQuery<CpassVSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassVSettore> getMySectorFather(UUID settoreId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("select vsFather FROM CpassVSettore vs, CpassVSettore vsFather WHERE vs.settoreIdPadre = vsFather.settoreId ");

		JpaQueryHelper.andFieldEquals(jpql, params, "vs.settoreId", "settoreId", settoreId);
		final TypedQuery<CpassVSettore> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<CpassVSettore> getSettoriSonsBySettoreAndEnte(UUID settoreId, UUID enteId) {
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder sql = new StringBuilder().append("WITH RECURSIVE alberosettore AS (");
		sql.append(" SELECT ");
		sql.append("     CAST( s.settore_id AS VARCHAR) ");
		sql.append(" FROM cpass_t_settore s, ");
		sql.append("      cpass_d_tipo_settore ts ");
		sql.append(" WHERE ");
		sql.append("    s.tipo_settore_id = ts.tipo_settore_id");
		sql.append(" AND s.settore_id = :settoreId ");
		sql.append(" AND s.ente_id = :enteId ");
		sql.append(" UNION ALL");
		sql.append(" SELECT ");
		sql.append(" 	CAST( s_figlio.settore_id AS VARCHAR) ");
		sql.append(" FROM cpass_t_settore s_figlio ");
		sql.append("      JOIN alberosettore mtree ON mtree.settore_id = CAST( s_figlio.settore_padre_id AS VARCHAR) ");
		sql.append("     JOIN cpass_d_tipo_settore ts ON s_figlio.tipo_settore_id = ts.tipo_settore_id");
		sql.append(" where ");
		sql.append(" s_figlio.ente_id = :enteId  ");
		sql.append(" )");
		sql.append(" SELECT ");
		sql.append("    alberosettore.settore_id");
		sql.append("    FROM alberosettore");

		param.put("settoreId", settoreId);
		param.put("enteId", enteId);
		log.info("sql ", sql.toString());
		log.info("settoreId ", settoreId);
		log.info("enteId ", enteId);

		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> list = query.getResultList();
		final List<CpassVSettore> listaRis = new ArrayList<>();
		//for(Object[] obj : list) {  // se aggiungo una seconda o piu' colonne
		for(final Object obj : list) {
			final CpassVSettore oe = new CpassVSettore();
			//oe.setLivello((Integer)obj[0]);
			//oe.setSettoreIdPadre((UUID)obj[1]);
			oe.setSettoreId(UUID.fromString((String)obj));
			//oe.setSettoreCodice((String)obj[1]);
			//oe.setSettoreDescrizione((String)obj[3]);
			//oe.setEnteId((UUID)obj[4]);
			//oe.setTipoSettoreid((Integer)obj[5]);
			listaRis.add(oe);
		}
		return listaRis;

	}
}

//public List<CpassVOrdineEvasione> getListEvasioneByOrdineId(UUID idTestataOrdine) {
