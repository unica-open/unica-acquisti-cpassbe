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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms.CpassTRmsTestataRmsDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsTestataRms;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoRmsEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Data Access Object implementor for the entity CpassTOrdTestataOrdine
 */
@ApplicationScoped
public class CpassTRmsTestataRmsDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTRmsTestataRms> implements CpassTRmsTestataRmsDao {

	@Override
	public List<CpassTRmsTestataRms> findRmsByIds(List<UUID> listaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CpassTRmsTestataRms> findByAnnoENumero(Integer anno, Integer numero, UUID enteId) {

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTRmsTestataRms tRms ")
				.append(" WHERE 1 = 1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "tRms.rmsAnno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tRms.rmsNumero", "numero", numero);

		JpaQueryHelper.andFieldEquals(jpql, params, "tRms.cpassTEnte.enteId", "enteId", enteId);

		final TypedQuery<CpassTRmsTestataRms> query = composeTypedQuery(jpql, params);

		return query.getResultList().stream().findFirst();

	}


	@Override
	public Page<CpassTRmsTestataRms> findPaginated( Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			Boolean richiestaMagazzino,
			Integer statoRmsId,
			Integer statoRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			Settore settoreEmittente,
			Settore settore,
			SettoreIndirizzo settoreIndirizzo,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			UUID enteId,
			TestataRms testataRms
			) {
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTRmsTestataRms tRms ")
				.append(" WHERE tRms.cpassTEnte.enteId = :enteId ");
		params.put("enteId", enteId);

		composeQueryRicercaRms(numeroRmsDa,numeroRmsA,annoRmsDa,annoRmsA,
				dataInserimentoDa,dataInserimentoA,strutturaEmittenteId,strutturaDestinatarioId,richiestaMagazzino,
				statoRmsId,statoRigaRmsId,oggettoSpesaId,cpvId, settoreEmittente, settore, settoreIndirizzo,
				true,cfUtente,utenteId,settoreId, params, jpql, testataRms);

		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
			.append(", tRms.testataRmsId ASC ");
		}

		if (sortField == null) {
			jpql.append(" ORDER BY tRms.rmsAnno DESC, tRms.rmsNumero DESC ");
		}
		final Page<CpassTRmsTestataRms> ris = getPagedResult(jpql, params, page, size);
		return ris;
		// TODO Auto-generated method stub
	}

	@Override
	public List<CpassTRmsTestataRms> findTestataRmsByRigaRmsId(UUID rigaRmsId) {
		// TODO Auto-generated method stub
		return null;
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
			Integer statoRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			Settore settoreEmittente,
			Settore settore,
			SettoreIndirizzo settoreIndirizzo,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			Map<String, Object> params, StringBuilder jpql,
			TestataRms testataRms) {


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


		JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassTSettoreEmittente.settoreId", "settoreEmittente", settoreEmittente.getId());

		JpaQueryHelper.andFieldEquals(jpql, params, "trms.richiestaMagazzino", "richiestaMagazzino", richiestaMagazzino);
		JpaQueryHelper.andFieldEquals(jpql, params, "trms.cpassDStato.statoId", "statoRmsId", statoRmsId);

		if (statoRigaRmsId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTRmsRigaRms ctrr WHERE ctrr.cpassTRmsTestataRms = trms and ctrr.cpassDStato.statoId = :statoRigaRmsId ) ");
			params.put("statoRigaRmsId", statoRigaRmsId);
		}
		if (oggettoSpesaId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTRmsRigaRms ctrr WHERE ctrr.cpassTRmsTestataRms = trms and ctrr.cpassDOggettiSpesa.oggettiSpesaId = :oggettoSpesaId ) ");
			params.put("oggettoSpesaId", oggettoSpesaId);
		}
		if (cpvId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTRmsRigaRms ctrr WHERE ctrr.cpassTRmsTestataRms = trms and ctrr.cpassDOggettiSpesa.cpassDCpv.cpvId = :cpvId ) ");
			params.put("cpvId", cpvId);
		}

		if(settoreIndirizzo!=null) {
			if(settoreIndirizzo.getIndirizzo() != null) {
				jpql.append(" AND trms.destinatarioIndirizzo = :destinatarioIndirizzo ");
				params.put("destinatarioIndirizzo", settoreIndirizzo.getIndirizzo());
			}
			if(settoreIndirizzo.getCap() != null) {
				jpql.append(" AND trms.destinatarioCap = :destinatarioCap ");
				params.put("destinatarioCap", settoreIndirizzo.getCap());
			}
			if(settoreIndirizzo.getProvincia() != null) {
				jpql.append(" AND trms.destinatarioProvincia = :destinatarioProvincia ");
				params.put("destinatarioProvincia", settoreIndirizzo.getProvincia());
			}
			if(settoreIndirizzo.getLocalita() != null) {
				jpql.append(" AND trms.destinatarioLocalita = :destinatarioLocalita ");
				params.put("destinatarioLocalita", settoreIndirizzo.getLocalita());
			}
			if(settoreIndirizzo.getNumCivico() != null) {
				jpql.append(" AND trms.destinatarioNumCivico = :destinatarioNumCivico ");
				params.put("destinatarioNumCivico", settoreIndirizzo.getNumCivico());
			}
		}
		if (checkVisibilitaDocumentale && settoreId != null) {
			final Date now = new Date();
			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ")
			//issue-228
			//.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))")
			.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))")
			.append(" AND rrus.cpassDRuolo.ruoloCodice in ( :ruoloAdmin, :ruoloAdminEnte, :ruoloOsservatore) )");
			jpql.append(" OR ( (trms.cpassDStato.statoCodice = :statoCodice AND trms.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			jpql.append(" OR (trms.cpassDStato.statoCodice != :statoCodice ");

			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus, CpassRRuoloUtenteSettore crrus , CpassRRuoloModulo crrm ")
			.append( " WHERE crus.cpassTSettore.settoreId = trms.cpassTSettoreEmittente.settoreId AND crus.cpassTUtente.utenteId = :utenteId ")
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
			params.put("statoCodice", StatoRmsEnum.BOZZA.getCostante());
			params.put("cfUtenteLoggato", cfUtente);
			params.put("moduloCodice", ConstantsDecodifiche.ModuloEnum.RMS.getCodice());

		}
	}

	@Override
	public long countRicercaRms(Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			Boolean richiestaMagazzino,
			Integer statoRmsId,
			Integer statoRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			Settore settoreEmittente,
			Settore settore,
			SettoreIndirizzo settoreIndirizzo,
			UUID    enteId,
			TestataRms testataRms
			){

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTRmsTestataRms tRms ")
				.append(" WHERE tRms.cpassTEnte.enteId = :enteId ");
		params.put("enteId", enteId);
		composeQueryRicercaRms(numeroRmsDa, numeroRmsA, annoRmsDa, annoRmsA, dataInserimentoDa, dataInserimentoA,
				strutturaEmittenteId, strutturaDestinatarioId, richiestaMagazzino, statoRmsId, statoRigaRmsId, oggettoSpesaId, cpvId, settoreEmittente, settore, settoreIndirizzo,
				false,null, null, null, params, jpql,testataRms);

		final Query qn = composeQuery(getCountQuery(jpql.toString()), params);
		final long count = ((Number) qn.getSingleResult()).longValue();
		return count;

	}
	/*
	@Override
	public List<CpassTRmsTestataRms> findRmsByRdaId(UUID rdaId) {

		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTRmsTestataRms tRms ")
     			.append(" WHERE 1 = 1 ");

		jpql.append(" AND EXISTS ( FROM CpassTRmsRigaRms ctrr WHERE ctrr.cpassTRmsTestataRms = trms and ctrr.cpassTOrdRigaRda.cpassTOrdTestataRda.testataRdaId = :testataRdaId ) ");

		params.put("testataRdaId", rdaId);

		TypedQuery<CpassTRmsTestataRms> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	 */
	/*
	@Override
	public List<CpassTRmsTestataRms> findRmsByRdaId(UUID rdaId) {

		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTRmsTestataRms tRms ")
     			.append(" WHERE 1 = 1 ");

		jpql.append(" AND EXISTS ( FROM CpassTRmsRigaRms ctrr WHERE ctrr.cpassTRmsTestataRms = trms and ctrr.cpassTOrdRigaRda.cpassTOrdTestataRda.testataRdaId = :testataRdaId ) ");

		params.put("testataRdaId", rdaId);

		TypedQuery<CpassTRmsTestataRms> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	 */

	@Override
	public void deleteStatiRigheByTestataRms(UUID testataRmsId) {
		// TODO Auto-generated method stub

	}
}
