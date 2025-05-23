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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFlussoSubImpegniEsterniDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoSubimpegniEsterni;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassTFlussoSubImpegniEsterniDaoImpl extends BaseEntityDaoImpl<Integer,CpassTFlussoSubimpegniEsterni> implements CpassTFlussoSubImpegniEsterniDao {
	@Override
	public List<CpassTFlussoSubimpegniEsterni> getFlussoSubImpegniEsterniByEnte(UUID enteId, String esito, Integer limit, Integer offset, Date dataCaricamento,Integer numelab) {
		final Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("FROM CpassTFlussoSubimpegniEsterni fie WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "fie.idEnte", "enteId", enteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "fie.esito", "esito", esito);
		if(dataCaricamento!= null) {
			jpql = jpql.append(" AND fie.dataCaricamento >= :dataCaricamento"  );
			params.put("dataCaricamento", dataCaricamento);
		}
		if(numelab!= null) {
			jpql = jpql.append(" AND fie.numElaborazioneDiGiornata = :numelab"  );
			params.put("numelab", numelab);
		}

		jpql = jpql.append(" ORDER BY fie.flussoSubimpegniEsterniId "  );

		final TypedQuery<CpassTFlussoSubimpegniEsterni> query = composeTypedQuery(jpql, params);
		if(offset!=null) {
			query.setFirstResult(offset);
		}
		if(limit!=null) {
			query.setMaxResults(limit);
		}
		return query.getResultList();
	}

	@Override
	public void resetSequence() {
		final StringBuilder sb = new StringBuilder();
		sb.append("ALTER SEQUENCE cpass.cpass_t_flusso_subimpegni_este_flusso_subimpegni_esterni_id_seq RESTART WITH 1");
		final Map<String, Object> params = new HashMap<>();
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public void deleteAll(Date dataStorico) {
		final StringBuilder sb = new StringBuilder();
		sb.append("delete from cpass.cpass_t_flusso_subimpegni_esterni where data_caricamento <= :dataStorico");
		final Map<String, Object> params = new HashMap<>();
		params.put("dataStorico", dataStorico);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}
}
