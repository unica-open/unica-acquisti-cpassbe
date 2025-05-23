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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVRmsRdaOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVRmsRdaOrdine;

/**
 * Data Access Object implementor for the entity CpassTImpegnoDao
 */
@ApplicationScoped
public class CpassVRmsRdaOrdineDaoImpl extends BaseEntityDaoImpl<Integer, CpassVRmsRdaOrdine> implements CpassVRmsRdaOrdineDao {

	@Override
	public BigDecimal getQuantitaNonEvasaSuOrdine(UUID rigaRmsId) {
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("select sum(COALESCE (quantita_riga_rda,0)) ");
		jpql.append(" from cpass_v_rms_rda_ordine");
		jpql.append(" where");
		jpql.append(" testata_ordine_id is null");
		jpql.append(" and riga_rms_id = :rigaRmsId ");

		param.put("rigaRmsId", rigaRmsId);

		final Query query = composeNativeQuery(jpql.toString(), param);
		log.info("getQuantitaEvasaSuOrdine ", "qta --> " + jpql.toString());
		log.info("getQuantitaEvasaSuOrdine ", "rigaRmsId --> " + rigaRmsId.toString());


		final List<Object> list = query.getResultList();
		BigDecimal ris = BigDecimal.ZERO;
		for(final Object obj : list) {
			if(obj!=null) {
				ris = (BigDecimal)obj;
			}
		}
		log.info("getQuantitaEvasaSuOrdine ", "qta --> " + ris.toString());
		return ris;//findOrdineByIds(listaId);
	}

	@Override
	public BigDecimal getQuantitaEvasaSuAltriOrdini(UUID rigaRmsId,UUID testataOrdineId) {
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("select sum(COALESCE (quantita_riga_rda,0)) ");
		jpql.append(" from cpass_v_rms_rda_ordine");
		jpql.append(" where");
		jpql.append(" testata_ordine_id IS NOT NULL ");
		jpql.append(" AND testata_ordine_id != :testataOrdineId");
		jpql.append(" AND riga_rms_id = :rigaRmsId ");

		param.put("testataOrdineId", testataOrdineId);
		param.put("rigaRmsId", rigaRmsId);

		final Query query = composeNativeQuery(jpql.toString(), param);
		log.info("getQuantitaEvasaSuOrdine ", "qta --> " + jpql.toString());
		log.info("getQuantitaEvasaSuOrdine ", "rigaRmsId --> " + rigaRmsId.toString());


		final List<Object> list = query.getResultList();
		BigDecimal ris = BigDecimal.ZERO;
		for(final Object obj : list) {
			if(obj!=null) {
				ris = (BigDecimal)obj;
			}
		}
		log.info("getQuantitaEvasaSuOrdine ", "qta --> " + ris.toString());
		return ris;//findOrdineByIds(listaId);
	}

}
