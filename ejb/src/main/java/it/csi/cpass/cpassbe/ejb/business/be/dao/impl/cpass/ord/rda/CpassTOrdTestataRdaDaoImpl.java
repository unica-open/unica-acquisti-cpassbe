/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.rda;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.rda.CpassTOrdTestataRdaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRdaEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

@ApplicationScoped
public class CpassTOrdTestataRdaDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdTestataRda> implements CpassTOrdTestataRdaDao {

	@Override
	public List<CpassTOrdTestataRda> findTestataRdaByOrderId(UUID testataOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT croro.cpassTOrdTestataRda FROM CpassROrdRdaOrdine croro ");
		jpql.append(" WHERE croro.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");
		params.put("testataOrdineId", testataOrdineId);
		final TypedQuery<CpassTOrdTestataRda> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassTOrdTestataRda> findByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		CpassTOrdTestataRda queryResult = null;

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataRda trda ")
				//			.append(" WHERE trda.dataCancellazione IS NULL ");
				.append(" WHERE 1 = 1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "trda.anno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "trda.numero", "numero", numero);

		JpaQueryHelper.andFieldEquals(jpql, params, "trda.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);

		final TypedQuery<CpassTOrdTestataRda> query = composeTypedQuery(jpql, params);

		final List<CpassTOrdTestataRda> results = query.getResultList();

		if(!results.isEmpty()) {
			queryResult = results.get(0);
		}

		return Optional.ofNullable(queryResult);
	}

	@Override
	public long countRicercaRda(Integer rdaNumeroDa, Integer rdaNumeroA, Integer rdaAnnoDa, Integer rdaAnnoA,
			Date dataInserimentoDa, Date dataInserimentoA, UUID settoreEmittenteId, UUID settoreDestinatarioId,
			Integer statoRdaId, Integer statoRigaRdaId, Integer oggettoSpesaId, Integer cpvId) {


		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("SELECT DISTINCT trda FROM CpassTOrdTestataRda trda LEFT OUTER JOIN CpassTOrdRigaRda rrda ON trda.testataRdaId = rrda.cpassTOrdTestataRda.testataRdaId ")
				// da capire come sostituire e se è necessario farlo
				.append(" LEFT OUTER JOIN CpassRRmsRigaRda rrms ON rrms.cpassTOrdRigaRda.rigaRdaId = rrda.rigaRdaId ")
				.append(" LEFT OUTER JOIN CpassTRmsTestataRms trms ON rrms.cpassTRmsRigaRms.cpassTRmsTestataRms.testataRmsId = trms.testataRmsId ")


				//.append(" LEFT OUTER JOIN CpassTRmsRigaRms rrms ON rrms.cpassTOrdRigaRda.rigaRdaId = rrda.rigaRdaId ")
				//.append(" LEFT OUTER JOIN CpassTRmsTestataRms trms ON rrms.cpassTRmsTestataRms.testataRmsId = trms.testataRmsId ")

				.append(" WHERE 1=1 ");


		composeQueryRicercaRda(rdaNumeroDa, rdaNumeroA, rdaAnnoDa, rdaAnnoA, dataInserimentoDa, dataInserimentoA,
				settoreEmittenteId, settoreDestinatarioId, statoRdaId, statoRigaRdaId, oggettoSpesaId, cpvId,
				false,null, null, null, params, jpql);

		final Query qn = composeQuery(getCountQuery(jpql.toString()), params);
		final long count = ((Number) qn.getSingleResult()).longValue();
		return count;

	}

	private void composeQueryRicercaRda(Integer rdaNumeroDa, Integer rdaNumeroA, Integer rdaAnnoDa, Integer rdaAnnoA,
			Date dataInserimentoDa, Date dataInserimentoA, UUID settoreEmittenteId, UUID settoreDestinatarioId,
			Integer statoRdaId, Integer statoRigaRdaId, Integer oggettoSpesaId, Integer cpvId,
			boolean checkVisibilitaDocumentale, String cfUtente, UUID utenteId, UUID settoreId, Map<String, Object> params, StringBuilder jpql) {


		/*
		if(rdaAnnoDa != null) {
			jpql.append(" AND trda.anno >= :rdaAnnoDa ");
			params.put("rdaAnnoDa", rdaAnnoDa);
		}
		if (rdaAnnoA != null) {
			jpql.append(" AND trda.anno <= :rdaAnnoA ");
			params.put("rdaAnnoA", rdaAnnoA);
		}
		if (rdaNumeroDa != null) {
			jpql.append(" AND trda.numero >= :rdaNumeroDa ");
			params.put("rdaNumeroDa", rdaNumeroDa);
		}
		if (rdaNumeroA != null) {
			jpql.append(" AND trda.numero <= :rdaNumeroA ");
			params.put("rdaNumeroA", rdaNumeroA);
		}
	*/
		if(rdaAnnoDa!=null && rdaAnnoA!=null && rdaAnnoDa<rdaAnnoA) {

			jpql.append(" AND (");
			for(int i=rdaAnnoDa; i<=rdaAnnoA; i++) {
				if(i==rdaAnnoDa) {
					if(rdaNumeroDa!= null) {
						jpql.append("( trda.anno = :annoRda"+i+" AND trda.numero >= :rdaNumeroDa )");
						params.put("rdaNumeroDa", rdaNumeroDa);
					}else {
						jpql.append(" trda.anno = :annoRda"+i);
					}
					params.put("annoRda"+i, i);
					jpql.append(" OR ");
				}

				if(i<rdaAnnoA && i>rdaAnnoDa) {
					jpql.append(" trda.anno = :annoRda"+i);
					jpql.append(" OR ");
					params.put("annoRda"+i, i);
				}

				if(i==rdaAnnoA) {
					if(rdaNumeroA!= null) {
						jpql.append("( trda.anno = :annoRda"+i+" and trda.numero <= :rdaNumeroA )");
						params.put("rdaNumeroA", rdaNumeroA);
					}else {
						jpql.append(" trda.anno = :annoRda"+i);
					}
					params.put("annoRda"+i, i);
				}


			}
			jpql.append(")");

		}else {
			if(rdaAnnoDa != null && rdaNumeroDa == null) {
				jpql.append(" AND trda.anno >= :rdaAnnoDa ");
				params.put("rdaAnnoDa", rdaAnnoDa);
			}
			if (rdaAnnoDa == null && rdaNumeroDa != null) {
				jpql.append(" AND trda.numero >= :rdaNumeroDa ");
				params.put("rdaNumeroDa", rdaNumeroDa);
			}
			if (rdaAnnoDa != null && rdaNumeroDa != null) {
				jpql.append(" AND trda.anno >= :rdaAnnoDa ");
				jpql.append(" AND trda.numero >= :rdaNumeroDa ");
				params.put("rdaAnnoDa", rdaAnnoDa);
				params.put("rdaNumeroDa", rdaNumeroDa);
			}

			if (rdaAnnoA != null && rdaNumeroA == null) {
				jpql.append(" AND trda.anno <= :rdaAnnoA ");
				params.put("rdaAnnoA", rdaAnnoA);
			}

			if (rdaAnnoA == null && rdaNumeroA != null) {
				jpql.append(" AND trda.numero <= :rdaNumeroA ");
				params.put("rdaNumeroA", rdaNumeroA);
			}
			if (rdaAnnoA != null && rdaNumeroA != null) {
				jpql.append(" AND trda.anno <= :rdaAnnoA ");
				jpql.append(" AND trda.numero <= :rdaNumeroA ");
				params.put("rdaAnnoA", rdaAnnoA);
				params.put("rdaNumeroA", rdaNumeroA);
			}
		}


		if (dataInserimentoDa != null) {
			jpql.append(" AND date_trunc('day',trda.dataCreazione) >= :dataCreazioneDa ");
			params.put("dataCreazioneDa", dataInserimentoDa);
		}
		if (dataInserimentoA != null) {
			jpql.append(" AND date_trunc('day',trda.dataCreazione) <= :dataCreazioneA ");
			params.put("dataCreazioneA", dataInserimentoA);
		}


		JpaQueryHelper.andFieldEquals(jpql, params, "trda.cpassDStato.statoId", "statoRdaId", statoRdaId);
		JpaQueryHelper.andFieldEquals(jpql, params, "trda.cpassTSettore.settoreId", "settoreEmittenteId", settoreEmittenteId);
		//JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassTSettoreDestinatario.settoreId", "settoreDestinatarioId", settoreDestinatarioId);
		if (statoRigaRdaId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdRigaRda rrda WHERE rrda.cpassTOrdTestataRda = trda and rrda.cpassDStato.statoId = :statoRigaRdaId ) ");
			params.put("statoRigaRdaId", statoRigaRdaId);
		}
		if (oggettoSpesaId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdRigaRda rrda WHERE rrda.cpassTOrdTestataRda = trda and rrda.cpassDOggettiSpesa.oggettiSpesaId = :oggettoSpesaId ) ");
			params.put("oggettoSpesaId", oggettoSpesaId);
		}
		if (cpvId != null) {
			jpql.append(" AND EXISTS (  FROM CpassTOrdRigaRda rrda WHERE rrda.cpassTOrdTestataRda = trda and rrda.cpassDOggettiSpesa.cpassDCpv.cpvId = :cpvId ) ");
			params.put("cpvId", cpvId);
		}
		if(settoreDestinatarioId != null) {
			jpql.append(" AND EXISTS (  FROM CpassTRmsTestataRms trms1 WHERE rrms.cpassTRmsTestataRms = trms1 and trms1.cpassTSettoreDestinatario.settoreId = :settoreDestinatarioId ) ");
			params.put("settoreDestinatarioId", settoreDestinatarioId);
		}


		if (checkVisibilitaDocumentale && settoreId != null) {

			final Date now = new Date();

			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ")
			//issue-228
			//.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))")
			.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))")
			.append(" AND rrus.cpassDRuolo.ruoloCodice in (:ruoloAdmin, :ruoloAdminEnte, :ruoloOsservatore) ) ");
			jpql.append(" OR ( (trms.cpassDStato.statoCodice = :statoCodice AND trms.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			jpql.append(" OR (trms.cpassDStato.statoCodice != :statoCodice ");

			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus, CpassRRuoloUtenteSettore crrus , CpassRRuoloModulo crrm ")
			.append( " WHERE crus.cpassTSettore.settoreId = trda.cpassTSettore.settoreId AND crus.cpassTUtente.utenteId = :utenteId ")
			.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and trms.dataCreazione <= crus.dataValiditaFine )) ")
			.append(" AND crrus.cpassRUtenteSettore.id = crus.id  ")
			.append(" AND crrus.cpassDRuolo.id = crrm.cpassDRuolo.id  ")
			.append(" AND crrm.cpassDModulo.moduloCodice =  :moduloCodice ");

			jpql.append(" )))) ");

			params.put("settoreId", settoreId);
			params.put("utenteId", utenteId);
			params.put("now", now);
			params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());
			params.put("ruoloAdminEnte", CpassRuoloEnum.ADMIN_ENTE.getCodice());
			params.put("ruoloOsservatore", CpassRuoloEnum.OSSERVATORE_RMS.getCodice());
			params.put("statoCodice", StatoRdaEnum.BOZZA.getCostante());
			params.put("cfUtenteLoggato", cfUtente);
			params.put("moduloCodice", ConstantsDecodifiche.ModuloEnum.ORD.getCodice());
		}

	}

	@Override
	public Page<CpassTOrdTestataRda> findPaginated(Integer numeroRdaDa, Integer numeroRdaA, Integer annoRdaDa,
			Integer annoRdaA, Date dataInserimentoDa, Date dataInserimentoA, UUID settoreEmittenteId,
			UUID settoreDestinatarioId, Integer statoRdaId, Integer statoRigaRdaId, Integer oggettoSpesaId,
			Integer cpvId, int page, int size, String sortField, String sortDirection,
			boolean checkVisibilitaDocumentale, String cfUtente, UUID utenteId, UUID settoreId, UUID enteId) {

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append(" SELECT DISTINCT trda FROM CpassTOrdTestataRda trda LEFT OUTER JOIN CpassTOrdRigaRda rrda ON trda.testataRdaId = rrda.cpassTOrdTestataRda.testataRdaId ")
				// da capire come sostituire e se è necessario farlo
				//.append(" LEFT OUTER JOIN CpassTRmsRigaRms rrms ON rrms.cpassTOrdRigaRda.rigaRdaId = rrda.rigaRdaId ")
				//.append(" LEFT OUTER JOIN CpassTRmsTestataRms trms ON rrms.cpassTRmsTestataRms.testataRmsId = trms.testataRmsId ")
				.append(" LEFT OUTER JOIN CpassRRmsRigaRda rrms ON rrms.cpassTOrdRigaRda.rigaRdaId = rrda.rigaRdaId ")
				.append(" LEFT OUTER JOIN CpassTRmsTestataRms trms ON rrms.cpassTRmsRigaRms.cpassTRmsTestataRms.testataRmsId = trms.testataRmsId ")


				.append(" WHERE 1=1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "trda.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);

		composeQueryRicercaRda(numeroRdaDa, numeroRdaA, annoRdaDa, annoRdaA, dataInserimentoDa, dataInserimentoA,
				settoreEmittenteId, settoreDestinatarioId, statoRdaId, statoRigaRdaId, oggettoSpesaId, cpvId,
				checkVisibilitaDocumentale,cfUtente, utenteId, settoreId, params, jpql);

		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
			.append(", trda.testataRdaId ASC ");
		}

		if (sortField == null) {
			jpql.append(" ORDER BY trda.anno DESC, trda.numero DESC ");
		}

		return getPagedResult(jpql, params, page, size);
	}



}
