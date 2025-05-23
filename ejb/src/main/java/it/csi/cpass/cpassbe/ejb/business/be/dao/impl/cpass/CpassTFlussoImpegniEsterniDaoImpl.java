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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFlussoImpegniEsterniDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTFlussoImpegniEsterni;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity CpassDCpvDaoImpl
 */
@ApplicationScoped
public class CpassTFlussoImpegniEsterniDaoImpl extends BaseEntityDaoImpl<Integer,CpassTFlussoImpegniEsterni> implements CpassTFlussoImpegniEsterniDao {
	@Override
	public List<CpassTFlussoImpegniEsterni> getFlussoImpegniEsterniByEnte(UUID enteId,String esito, Integer limit, Integer offset, Date dataCaricamento,Integer numelab) {
		final Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("FROM CpassTFlussoImpegniEsterni fie WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "fie.idEnte", "enteId", enteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "fie.esito", "esito", esito);
		if(dataCaricamento!= null) {
			jpql = jpql.append(" AND fie.dataCaricamento >= :dataCaricamento"  );
			params.put("dataCaricamento", dataCaricamento);
		}
		if(numelab!= null) {
			jpql = jpql.append(" AND fie.numElaborazioneDiGiornata >= :numelab"  );
			params.put("numelab", numelab);
		}

		jpql = jpql.append(" ORDER BY fie.flussoImpegniEsterniId "  );

		final TypedQuery<CpassTFlussoImpegniEsterni> query = composeTypedQuery(jpql, params);
		if(offset!=null) {
			query.setFirstResult(offset);
		}
		if(limit!=null) {
			query.setMaxResults(limit);
		}
		return query.getResultList();
	}

	@Override
	public List<CpassTFlussoImpegniEsterni> getFlussoImpegniEsterniElaborabili(UUID enteId,String esito, Integer limit, Integer offset, Date dataCaricamento,Integer numelab) {
		final Map<String, Object> params = new HashMap<>();
		final Calendar calendar = Calendar.getInstance();
		final Integer annoCorrente = calendar.get(Calendar.YEAR);

		final List<Integer> anniEsercizio = new ArrayList<>();
		anniEsercizio.add(annoCorrente);
		anniEsercizio.add(annoCorrente-1);

		StringBuilder jpql = new StringBuilder().append("SELECT DISTINCT(fie) "  );
		jpql = jpql.append(" FROM CpassTFlussoImpegniEsterni fie, CpassTImpegno imp "  );
		jpql = jpql.append(" WHERE ");
		jpql = jpql.append("     fie.annoImpegno = CAST(imp.impegnoAnno AS string)" );
		jpql = jpql.append(" AND fie.numImpegno  = CAST(imp.impegnoNumero AS string)" );
		//jpql = jpql.append("     fie.annoImpegno = imp.impegnoAnno " );
		//jpql = jpql.append(" AND fie.numImpegno  = imp.impegnoNumero" );
		jpql = jpql.append(" AND fie.idEnte      = imp.cpassTEnte.enteId" );
		jpql = jpql.append(" AND imp.impegnoAnnoEsercizio IN (:anniEsercizio) " );

		params.put("anniEsercizio", anniEsercizio);

		JpaQueryHelper.andFieldEquals(jpql, params, "imp.cpassTEnte.enteId", "enteId", enteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "fie.idEnte", "enteId", enteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "fie.esito", "esito", esito);
		if(dataCaricamento!= null) {
			jpql = jpql.append(" AND fie.dataCaricamento >= :dataCaricamento"  );
			params.put("dataCaricamento", dataCaricamento);
		}
		if(numelab!= null) {
			jpql = jpql.append(" AND fie.numElaborazioneDiGiornata >= :numelab"  );
			params.put("numelab", numelab);
		}

		jpql = jpql.append(" ORDER BY fie.flussoImpegniEsterniId "  );

		final TypedQuery<CpassTFlussoImpegniEsterni> query = composeTypedQuery(jpql, params);
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
		sb.append("ALTER SEQUENCE cpass_t_flusso_impegni_esterni_flusso_impegni_esterni_id_seq RESTART WITH 1");
		final Map<String, Object> params = new HashMap<>();
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();

	}

	@Override
	public void deleteAll(Date dataStorico) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder sb = new StringBuilder();
		sb.append("delete from cpass.cpass_t_flusso_impegni_esterni where data_caricamento <= :dataStorico");
		params.put("dataStorico", dataStorico);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}
}
