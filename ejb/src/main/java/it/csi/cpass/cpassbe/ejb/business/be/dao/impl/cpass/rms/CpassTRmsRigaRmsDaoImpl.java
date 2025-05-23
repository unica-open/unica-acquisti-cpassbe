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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.rms;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassTRmsRigaRmsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRmsEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

@ApplicationScoped
public class CpassTRmsRigaRmsDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTRmsRigaRms> implements CpassTRmsRigaRmsDao {

	@Override
	public List<CpassTRmsRigaRms> findByTestataRms(UUID testataRmsId,Integer progressivoRiga) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTRmsRigaRms riga  WHERE riga.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTRmsTestataRms.testataRmsId", "testataRmsId", testataRmsId);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.progressivoRiga", "progressivoRiga", progressivoRiga);
		final TypedQuery<CpassTRmsRigaRms> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteRigaByTestataRms(UUID testataRmsId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTRmsRigaRms riga WHERE riga.cpassTRmsTestataRms.testataRmsId = :testataRmsId");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataRmsId", testataRmsId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public Page<CpassTRmsRigaRms> getRicercaRigheRms(Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			Boolean richiestaMagazzino,
			Integer statoRmsId,
			List<Integer> statiRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			UUID settoreAcquistoId,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			UUID enteId,
			Boolean flgNonCompetenza
			) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append(" SELECT riga FROM CpassTRmsTestataRms trms, CpassTRmsRigaRms riga  WHERE trms = riga.cpassTRmsTestataRms and riga.cpassTEnte.enteId= :enteId ");

		params.put("enteId", enteId);

		composeQueryRicercaRms(numeroRmsDa,numeroRmsA,annoRmsDa,annoRmsA,
				dataInserimentoDa,dataInserimentoA,strutturaEmittenteId,strutturaDestinatarioId,richiestaMagazzino,
				statoRmsId,statiRigaRmsId, oggettoSpesaId,cpvId,settoreAcquistoId,
				checkVisibilitaDocumentale,cfUtente,utenteId,settoreId, params, jpql, flgNonCompetenza);

		if (sortField != null ) {
			jpql.append(" ORDER BY ").append(sortField);
			if(sortDirection != null) {
				jpql.append(" ").append(sortDirection);
			}
		}

		if (sortField == null) {
			jpql.append(" ORDER BY riga.cpassDOggettiSpesa.cpassDCpv.cpvCodice ");
		}


		final Page<CpassTRmsRigaRms> ris = getPagedResult(jpql, params, page, size);
		return ris;
		// TODO Auto-generated method stub
	}

	/**
	 *
	 * @param numeroRmsDa
	 * @param numeroRmsA
	 * @param annoRmsA
	 * @param annoRmsDa
	 * @param dataInserimentoDa
	 * @param dataInserimentoA
	 * @param strutturaEmittenteId
	 * @param strutturaDestinatarioId
	 * @param richiestaMagazzino
	 * @param statoRmsId
	 * @param statoRigaRmsId
	 * @param oggettoSpesaId
	 * @param cpvId
	 * @param page
	 * @param size
	 * @param sortField
	 * @param sortDirection
	 * @param cfUtente
	 * @param utenteId
	 * @param settoreId
	 * @param params
	 * @param jpql
	 */
	private void composeQueryRicercaRms(Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			Boolean richiestaMagazzino,
			Integer statoRmsId,
			List<Integer> statiRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			UUID settoreAcquistoId,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			//String sortField,
			//String sortDirection,
			Map<String, Object> params, StringBuilder jpql, Boolean flgNonCompetenza) {

		JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassTSettoreEmittente.settoreId", "strutturaEmittenteId", strutturaEmittenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassTSettoreDestinatario.settoreId", "strutturaDestinatarioId", strutturaDestinatarioId);
		JpaQueryHelper.andFieldEquals(jpql, params, "trms.richiestaMagazzino", "richiestaMagazzino", richiestaMagazzino);
		JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassDStato.statoId", "statoRmsId", statoRmsId);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTSettoreAcquisto.settoreId", "settoreAcquistoId", settoreAcquistoId);

		if(annoRmsDa != null) {
			jpql.append(" AND trms.rmsAnno >= :annoRmsDa ");
			params.put("annoRmsDa", annoRmsDa);
		}
		if (annoRmsA != null) {
			jpql.append(" AND trms.rmsAnno <= :annoRmsA ");
			params.put("annoRmsA", annoRmsA);
		}
		if (numeroRmsDa != null) {
			jpql.append(" AND trms.rmsNumero >= :numeroRmsDa ");
			params.put("numeroRmsDa", numeroRmsDa);
		}
		if (numeroRmsA != null) {
			jpql.append(" AND trms.rmsNumero <= :numeroRmsA ");
			params.put("numeroRmsA", numeroRmsA);
		}
		if (dataInserimentoDa != null) {
			jpql.append(" AND date_trunc('day',trms.dataCreazione) >= :dataCreazioneDa ");
			params.put("dataCreazioneDa", dataInserimentoDa);
		}
		if (dataInserimentoA != null) {
			jpql.append(" AND date_trunc('day',trms.dataCreazione) <= :dataCreazioneA ");
			params.put("dataCreazioneA", dataInserimentoA);
		}

		/*		if (statiRigaRmsId != null && statiRigaRmsId.size()>0) {
			jpql.append(" AND  riga.cpassDStato.statoId IN ( :statiRigaRmsId ) ");
			params.put("statiRigaRmsId", statiRigaRmsId);
		}*/
		if (statiRigaRmsId != null && statiRigaRmsId.size() > 0) {
			final boolean includeRifiutato = statiRigaRmsId.contains(37);
			final boolean includeDaSmistare = statiRigaRmsId.contains(32);
			boolean firstConditionAdded = false;

			jpql.append(" AND ( ");

			if (includeRifiutato) {
				jpql.append(" (riga.cpassDStato.statoId = 37 AND riga.flgNonCompetenza = true) ");
				firstConditionAdded = true;
			}

			if (includeDaSmistare) {
				if (firstConditionAdded) {
					jpql.append(" OR ");
				}
				jpql.append(" riga.cpassDStato.statoId = 32 ");
			}

			jpql.append(" ) ");
		}


		/*
		if (statoRigaRmsId != null) {
			jpql.append(" AND ( riga.cpassDStato.statoId = :statoRigaRmsId  ");
			jpql.append(" OR riga.cpassDStato.statoId = :statoRigaRmsRifId )  ");
			params.put("statoRigaRmsId", statoRigaRmsId);
			params.put("statoRigaRmsRifId", statoRigaRmsRifId);
		}
		 */
		if (oggettoSpesaId != null) {
			jpql.append(" AND riga.cpassDOggettiSpesa.oggettiSpesaId = :oggettoSpesaId  ");
			params.put("oggettoSpesaId", oggettoSpesaId);
		}
		if (cpvId != null) {
			jpql.append(" AND riga.cpassDOggettiSpesa.cpassDCpv.cpvId = :cpvId ");
			params.put("cpvId", cpvId);
		}


		if (checkVisibilitaDocumentale && settoreId != null) {
			final Date now = new Date();
			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ");
			jpql.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))");
			jpql.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))");
			//TODO da capire se è necessario il ruolo osservatore ANTO
			jpql.append(" AND rrus.cpassDRuolo.ruoloCodice in (:ruoloAdmin, :ruoloSmistatore)) ");
			//Nessuna altra regola di visibilita necessaria
			//			jpql.append(" OR ( (trms.cpassDStato.statoCodice = :statoCodice AND trms.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			//			jpql.append(" OR (trms.cpassDStato.statoCodice != :statoCodice ");
			//			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus WHERE crus.cpassTSettore.settoreId = trms.cpassTSettoreEmittente.settoreId AND crus.cpassTUtente.utenteId = :utenteId ");
			//			jpql.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and trms.dataCreazione <= crus.dataValiditaFine ))");
			//			jpql.append("))) ");
			jpql.append(") ");
			params.put("settoreId", settoreId);
			params.put("utenteId", utenteId);
			params.put("now", now);
			params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());
			params.put("ruoloSmistatore", CpassRuoloEnum.SMISTATORE_RMS.getCodice());
			params.put("statoCodice", StatoRmsEnum.BOZZA.getCostante());
			params.put("cfUtenteLoggato", cfUtente);

		}
		//todo gestire ordinamento
		/*if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
			//.append(", trda.testataRdaId ASC ")
			;
		}*/
	}
	/*
	@Override
	public List<CpassTRmsRigaRms> getRigaRmsByRigaRdaId(UUID rigaRdaId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTRmsRigaRms riga ")
			.append(" WHERE riga.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdRigaRda.rigaRdaId", "rigaRdaId", rigaRdaId);
		TypedQuery<CpassTRmsRigaRms> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	 */

	@Override
	public List<CpassTRmsRigaRms> getRigaRmsByRigaRdaId(UUID rigaRdaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("select r.cpassTRmsRigaRms FROM CpassRRmsRigaRda r  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "r.cpassTOrdRigaRda.rigaRdaId", "rigaRdaId", rigaRdaId);
		final TypedQuery<CpassTRmsRigaRms> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	/*
	@Override
	public List<CpassTRmsRigaRms> getRigheRmsByRdaId(UUID testataRdaId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append(" FROM CpassTRmsRigaRms rigaRms WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "rigaRms.cpassTOrdRigaRda.cpassTOrdTestataRda.testataRdaId", "testataRdaId", testataRdaId);
		TypedQuery<CpassTRmsRigaRms> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	 */
	@Override
	public List<CpassTRmsRigaRms> getRigheRmsByRdaId(UUID testataRdaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append(" select r.cpassTRmsRigaRms FROM CpassRRmsRigaRda r WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "r.cpassTOrdRigaRda.cpassTOrdTestataRda.testataRdaId", "testataRdaId", testataRdaId);
		final TypedQuery<CpassTRmsRigaRms> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Page<CpassTRmsRigaRms> getRicercaRigheRmsDaEvadere(Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			Date dataAutorizzazioneDa,
			Date dataAutorizzazioneA,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			Boolean richiestaMagazzino,
			Integer statoRmsId,
			List<Integer> statiRigaRmsId,
			BigDecimal sogliaQuantita,
			Integer sezioneId,
			//Integer statoRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			UUID settoreAcquistoId,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			UUID enteId
			) {
		final Map<String, Object> params = new HashMap<>();

		params.put("enteId", enteId);

		final Date now = new Date();
		params.put("now", now);
		params.put("utenteId", utenteId);
		params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());
		params.put("settoreId", settoreId);

		final StringBuilder jpql = new StringBuilder().append(" SELECT riga FROM CpassTRmsTestataRms trms, CpassTRmsRigaRms riga  WHERE trms = riga.cpassTRmsTestataRms and riga.cpassTEnte.enteId= :enteId ");

		JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassTSettoreEmittente.settoreId", "strutturaEmittenteId", strutturaEmittenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassTSettoreDestinatario.settoreId", "strutturaDestinatarioId", strutturaDestinatarioId);
		JpaQueryHelper.andFieldEquals(jpql, params, "trms.richiestaMagazzino", "richiestaMagazzino", richiestaMagazzino);
		JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassDStato.statoId", "statoRmsId", statoRmsId);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTSettoreAcquisto.settoreId", "settoreAcquistoId", settoreAcquistoId);
		jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ");
		jpql.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))");
		jpql.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))");
		jpql.append(" AND rrus.cpassDRuolo.ruoloCodice = :ruoloAdmin) ");
		jpql.append(" OR (riga.cpassTOrdSezione is null ");
		jpql.append(" OR (");
		jpql.append(" EXISTS ( FROM CpassROrdUtenteSezione rrus WHERE rrus.cpassTOrdSezione = riga.cpassTOrdSezione AND rrus.cpassTUtente.utenteId = :utenteId AND (rrus.dataCancellazione IS NULL OR (rrus.dataCancellazione IS NOT NULL and rrus.dataCancellazione > :now ) ))");
		jpql.append(" )");
		jpql.append(" ))");


/*
		if(annoRmsDa != null) {
			jpql.append(" AND trms.rmsAnno >= :annoRmsDa ");
			params.put("annoRmsDa", annoRmsDa);
		}
		if (annoRmsA != null) {
			jpql.append(" AND trms.rmsAnno <= :annoRmsA ");
			params.put("annoRmsA", annoRmsA);
		}
		if (numeroRmsDa != null) {
			jpql.append(" AND trms.rmsNumero >= :numeroRmsDa ");
			params.put("numeroRmsDa", numeroRmsDa);
		}
		if (numeroRmsA != null) {
			jpql.append(" AND trms.rmsNumero <= :numeroRmsA ");
			params.put("numeroRmsA", numeroRmsA);
		}
	*/
		if(annoRmsDa!=null && annoRmsA!=null && annoRmsDa<annoRmsA) {

			jpql.append(" AND (");
			for(int i=annoRmsDa; i<=annoRmsA; i++) {
				if(i==annoRmsDa) {
					if(numeroRmsDa!= null) {
						jpql.append("( trms.rmsAnno = :annoRms"+i+" AND trms.rmsNumero >= :numeroRmsDa )");
						params.put("numeroRmsDa", numeroRmsDa);
					}else {
						jpql.append(" trms.rmsAnno = :annoRms"+i);
					}
					params.put("annoRms"+i, i);
					jpql.append(" OR ");
				}

				if(i<annoRmsA && i>annoRmsDa) {
					jpql.append(" trms.rmsAnno = :annoRms"+i);
					jpql.append(" OR ");
					params.put("annoRms"+i, i);
				}

				if(i==annoRmsA) {
					if(numeroRmsA!= null) {
						jpql.append("( trms.rmsAnno = :annoRms"+i+" and trms.rmsNumero <= :numeroRmsA )");
						params.put("numeroRmsA", numeroRmsA);
					}else {
						jpql.append(" trms.rmsAnno = :annoRms"+i);
					}
					params.put("annoRms"+i, i);
				}


			}
			jpql.append(")");

		}else {
			if(annoRmsDa != null && numeroRmsDa == null) {
				jpql.append(" AND trms.rmsAnno >= :annoRmsDa ");
				params.put("annoRmsDa", annoRmsDa);
			}
			if (annoRmsDa == null && numeroRmsDa != null) {
				jpql.append(" AND trms.rmsNumero >= :numeroRmsDa ");
				params.put("numeroRmsDa", numeroRmsDa);
			}
			if (annoRmsDa != null && numeroRmsDa != null) {
				jpql.append(" AND trms.rmsAnno >= :annoRmsDa ");
				jpql.append(" AND trms.rmsNumero >= :numeroRmsDa ");
				params.put("annoRmsDa", annoRmsDa);
				params.put("numeroRmsDa", numeroRmsDa);
			}

			if (annoRmsA != null && numeroRmsA == null) {
				jpql.append(" AND trms.rmsAnno <= :annoRmsA ");
				params.put("annoRmsA", annoRmsA);
			}

			if (annoRmsA == null && numeroRmsA != null) {
				jpql.append(" AND trms.rmsNumero <= :numeroRmsA ");
				params.put("numeroRmsA", numeroRmsA);
			}
			if (annoRmsA != null && numeroRmsA != null) {
				jpql.append(" AND trms.rmsAnno <= :annoRmsA ");
				jpql.append(" AND trms.rmsNumero <= :numeroRmsA ");
				params.put("annoRmsA", annoRmsA);
				params.put("numeroRmsA", numeroRmsA);
			}
		}


		if (dataInserimentoDa != null) {
			jpql.append(" AND date_trunc('day',trms.dataCreazione) >= :dataCreazioneDa ");
			params.put("dataCreazioneDa", dataInserimentoDa);
		}
		if (dataInserimentoA != null) {
			jpql.append(" AND date_trunc('day',trms.dataCreazione) <= :dataCreazioneA ");
			params.put("dataCreazioneA", dataInserimentoA);
		}
		if (dataAutorizzazioneDa != null) {
			jpql.append(" AND date_trunc('day',trms.dataAutorizzazione) >= :dataAutorizzazioneDa ");
			params.put("dataAutorizzazioneDa", dataAutorizzazioneDa);
		}
		if (dataAutorizzazioneA != null) {
			jpql.append(" AND date_trunc('day',trms.dataAutorizzazione) <= :dataAutorizzazioneA ");
			params.put("dataAutorizzazioneA", dataAutorizzazioneA);
		}





		if (statiRigaRmsId != null && statiRigaRmsId.size()>0) {
			jpql.append(" AND  riga.cpassDStato.statoId IN ( :statiRigaRmsId ) ");
			params.put("statiRigaRmsId", statiRigaRmsId);
		}

		jpql.append(" AND COALESCE(riga.quantitaSuRda,0) < riga.quantita ");

		/*
		if (statoRigaRmsId != null) {
			jpql.append(" AND riga.cpassDStato.statoId = :statoRigaRmsId  ");
			params.put("statoRigaRmsId", statoRigaRmsId);
		}
		 */
		if(sezioneId != null) {
			jpql.append(" AND riga.cpassTOrdSezione.sezioneId = :sezioneId ");
			params.put("sezioneId", sezioneId);
		}


		if (oggettoSpesaId != null) {
			jpql.append(" AND riga.cpassDOggettiSpesa.oggettiSpesaId = :oggettoSpesaId  ");
			params.put("oggettoSpesaId", oggettoSpesaId);
		}
		if (cpvId != null) {
			jpql.append(" AND riga.cpassDOggettiSpesa.cpassDCpv.cpvId = :cpvId ");
			params.put("cpvId", cpvId);
		}

		if (checkVisibilitaDocumentale && settoreId != null) {
			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ");
			//issue-228
			//jpql.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))");
			jpql.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))");
			jpql.append(" AND rrus.cpassDRuolo.ruoloCodice = :ruoloAdmin) ");
			jpql.append(" OR ( (trms.cpassDStato.statoCodice = :statoCodice AND trms.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			jpql.append(" OR (trms.cpassDStato.statoCodice != :statoCodice ");
			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus WHERE crus.cpassTSettore.settoreId =  riga.cpassTSettoreAcquisto.settoreId AND crus.cpassTUtente.utenteId = :utenteId ");
			jpql.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and trms.dataCreazione <= crus.dataValiditaFine ))");
			jpql.append(")))) ");

			//		    params.put("settoreId", settoreId); // add param all'inizio del metodo
			//			params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());  // add param all'inizio del metodo
			params.put("statoCodice", StatoRmsEnum.BOZZA.getCostante());
			params.put("cfUtenteLoggato", cfUtente);

		}
		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
			//.append(", trda.testataRdaId ASC ")
			;
		}
		if (sortField == null) {
			jpql.append(" ORDER BY riga.cpassDOggettiSpesa.oggettiSpesaCodice, trms.rmsAnno , trms.rmsNumero ");
		}
		final Page<CpassTRmsRigaRms> ris = getPagedResult(jpql, params, page, size);
		return ris;
	}

	@Override
	public List<CpassTRmsRigaRms> getRigheRmsByTestataId(UUID testataRmsId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTRmsRigaRms r WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "r.cpassTRmsTestataRms.testataRmsId", "testataRmsId", testataRmsId);
		final TypedQuery<CpassTRmsRigaRms> query = composeTypedQuery(jpql, params);
		return query.getResultList();

	}
}
