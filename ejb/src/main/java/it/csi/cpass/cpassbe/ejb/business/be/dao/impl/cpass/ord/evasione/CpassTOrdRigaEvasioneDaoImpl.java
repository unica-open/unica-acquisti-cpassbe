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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdRigaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTOrdRigaEvasioneDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdRigaEvasione> implements CpassTOrdRigaEvasioneDao {


	@Override
	public List<CpassTOrdRigaEvasione> findByIdDestinatarioEvasione(UUID idDestinatarioEvasione) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdRigaEvasione riga ")
				.append(" WHERE riga.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatarioEvasione.destinatarioEvasioneId", "idDest", idDestinatarioEvasione);
		final TypedQuery<CpassTOrdRigaEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdRigaEvasione> findByIdRigaOrdine(UUID idRigaOrdine) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTOrdRigaEvasione riga ")
				//				.append(" WHERE riga.dataCancellazione IS NULL ");
				.append(" WHERE riga.cpassDStato.statoId NOT IN (SELECT statoId FROM CpassDStato WHERE statoTipo = 'RIGA_EVASIONE' AND statoCodice = 'ANN') ");
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdRigaOrdine.rigaOrdineId", "idRigaOrdine", idRigaOrdine);
		final TypedQuery<CpassTOrdRigaEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteByDestinatarioEvasione(UUID idDestinatario) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdImpegnoEvasione ");
		sb.append(" WHERE cpassTOrdDestinatario.destinatarioId = :idDestinatario");

		final Map<String, Object> params = new HashMap<>();
		params.put("idDestinatario", idDestinatario);

		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public List<CpassTOrdRigaEvasione> findByTestataEvasione(UUID testataEvasioneId, UUID rigaEvasioneId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdRigaEvasione riga ");
		jpql.append(" WHERE riga.dataCancellazione IS NULL ");
		jpql.append(" AND riga.cpassTOrdDestinatarioEvasione.cpassTOrdTestataEvasione.testataEvasioneId = :testataEvasioneId ");

		params.put("testataEvasioneId", testataEvasioneId);

		if (rigaEvasioneId != null) {
			// esclude la riga specificata
			jpql.append(" AND riga.rigaEvasioneId <> :rigaEvasioneId ");
			params.put("rigaEvasioneId", rigaEvasioneId);
		}

		final TypedQuery<CpassTOrdRigaEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public BigDecimal calcolaTotale(UUID rigaOrdineId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(re.importoTotale) ");
		jpql.append(" FROM CpassTOrdRigaEvasione re ");
		jpql.append(" WHERE re.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ");
		jpql.append(" AND re.cpassDStato.statoCodice <> :statoCodice ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("rigaOrdineId", rigaOrdineId);
		q.setParameter("statoCodice", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante()); // DISABBINATA -> Annullata

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal calcolaTotaleEsclusaEvasione(UUID rigaOrdineId, UUID rigaEvasioneId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(re.importoTotale) ");
		jpql.append(" FROM CpassTOrdRigaEvasione re ");
		jpql.append(" WHERE re.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ");
		jpql.append(" AND re.cpassDStato.statoCodice <> :statoCodice ");
		jpql.append(" AND re.rigaEvasioneId <> :rigaEvasioneId ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("rigaOrdineId", rigaOrdineId);
		q.setParameter("statoCodice", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante()); // DISABBINATA -> Annullata
		q.setParameter("rigaEvasioneId", rigaEvasioneId);


		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}


	//controllare questa query
	@Override
	public BigDecimal calcolaQuantitaEvasa(UUID rigaOrdineId) {
		final StringBuilder jpql = new StringBuilder()
				.append(" SELECT SUM(re.quantitaEvasa) ")
				.append(" FROM CpassTOrdRigaEvasione re ")
				.append(" WHERE re.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ")
				.append(" AND re.cpassDStato.statoCodice <> :statoCodice ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("rigaOrdineId", rigaOrdineId);
		q.setParameter("statoCodice", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante());

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return BigDecimal.ZERO;
	}

	@Override
	public List<CpassTOrdRigaEvasione> findByIdOrdineEDocumento(List<UUID> idOrdines, UUID idOrdine, Integer fatturaAnno, String fatturaNumero, String fatturaTipo, String fatturaCodice) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" SELECT rigaEva FROM CpassTOrdRigaEvasione rigaEva, CpassTOrdDestinatarioEvasione destEva, CpassTOrdTestataEvasione testataEva")
				.append(" , CpassTOrdRigaOrdine rigaOrd, CpassTOrdDestinatarioOrdine destOrd")
				.append(" WHERE rigaEva.dataCancellazione IS NULL ")
				.append(" and rigaEva.cpassTOrdRigaOrdine.rigaOrdineId = rigaOrd.rigaOrdineId")
				.append(" and rigaOrd.cpassTOrdDestinatario.destinatarioId = destOrd.destinatarioId")
				.append(" and destOrd.cpassTOrdTestataOrdine.testataOrdineId = :idOrdine ")
				.append(" and rigaEva.cpassTOrdDestinatarioEvasione.destinatarioEvasioneId = destEva.destinatarioEvasioneId")
				.append(" and destEva.cpassTOrdTestataEvasione.testataEvasioneId = testataEva.testataEvasioneId")
				.append(" AND NOT EXISTS ( ")
				.append("   from CpassTOrdDestinatarioEvasione de, CpassTOrdDestinatarioOrdine do, CpassTOrdTestataOrdine to")
				.append("   where de.cpassTOrdTestataEvasione.testataEvasioneId = testataEva.testataEvasioneId")
				.append("   and de.cpassTOrdDestinatarioOrdine.destinatarioId = do.destinatarioId")
				.append("   and do.cpassTOrdTestataOrdine.testataOrdineId not in (:idOrdines)")
				.append(" ) ");
		//				List<String> collect = idOrdines.stream().map(UUID::toString).collect(Collectors.toList());
		params.put("idOrdines", idOrdines);

		//		jpql.append(" AND rigaEv.cpassTOrdRigaOrdine.cpassTOrdDestinatario.cpassTOrdTestataOrdine.testataOrdineId = :idOrdine ");

		params.put("idOrdine", idOrdine);
		JpaQueryHelper.andFieldNotEquals(jpql, params, "rigaEva.cpassDStato.statoCodice", "statoRigaEva", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_TOTALMENTE_FATTURATA.getCostante());
		JpaQueryHelper.andFieldEquals(jpql, params, "testataEva.cpassDStato.statoCodice", "statoEva", StatoEvasioneEnum.IN_CONTABILITA.getCostante());
		JpaQueryHelper.andFieldEquals(jpql, params, "testataEva.fatturaAnno", "fatturaAnno", fatturaAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "testataEva.fatturaNumero", "fatturaNumero", fatturaNumero);
		JpaQueryHelper.andFieldEquals(jpql, params, "testataEva.fatturaTipo", "fatturaTipo", fatturaTipo);
		JpaQueryHelper.andFieldEquals(jpql, params, "testataEva.fatturaCodice", "fatturaCodice", fatturaCodice);


		final TypedQuery<CpassTOrdRigaEvasione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public BigDecimal getTotQtaEvasa(UUID rigaOrdineId) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(re.quantitaEvasa) ");
		jpql.append(" FROM CpassTOrdRigaEvasione re, CpassDStato statoElOrd");
		jpql.append(" WHERE re.cpassTOrdRigaOrdine.rigaOrdineId = :rigaOrdineId ");
		jpql.append(" AND re.cpassDStato.statoCodice <> :statoCodice ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("rigaOrdineId", rigaOrdineId);
		q.setParameter("statoCodice", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante()); // DISABBINATA -> Annullata

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

}
