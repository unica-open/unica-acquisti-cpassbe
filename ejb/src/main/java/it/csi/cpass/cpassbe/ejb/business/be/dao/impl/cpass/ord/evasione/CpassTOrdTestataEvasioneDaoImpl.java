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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdTestataEvasioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdTestataEvasione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassTOrdTestataEvasione
 */
@ApplicationScoped
public class CpassTOrdTestataEvasioneDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdTestataEvasione> implements CpassTOrdTestataEvasioneDao {

	@Override
	public Optional<CpassTOrdTestataEvasione> findByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		
		CpassTOrdTestataEvasione queryResult = null;
		
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTOrdTestataEvasione tord ")
			.append(" WHERE tord.dataCancellazione IS NULL ");		
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.evasioneAnno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.evasioneNumero", "numero", numero);		
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);		
		TypedQuery<CpassTOrdTestataEvasione> query = composeTypedQuery(jpql, params);
		
		List<CpassTOrdTestataEvasione> results = query.getResultList();
		
		if(!results.isEmpty()) {
			queryResult = results.get(0);
		}
		
		return Optional.ofNullable(queryResult);
	}
	
	@Override
	public Page<CpassTOrdTestataEvasione> findPaginated(			
			Integer annoEvasioneDa, 
			Integer numeroEvasioneDa, 
			Integer annoEvasioneA, 
			Integer numeroEvasioneA, 
			Date dataInserimentoDa, 
			Date dataInserimentoA,
			Integer annoOrdineDa, 
			Integer numeroOrdineDa, 
			Integer annoOrdineA, 
			Integer numeroOrdineA, 
			Date dataEmissioneDa, 
			Date dataEmissioneA,
			Integer tipoEvasioneId,
			Integer statoEvasioneId,
			UUID strutturaCompetenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId){
		
		Map<String, Object> params = new HashMap<>();
		Date now = new Date();
		StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataEvasione tev ")
				.append(" WHERE (tev.dataCancellazione IS NULL OR tev.dataCancellazione >=:now) ");
		params.put("now", now);
		
		composeQueryRicercaEvasioni(
				annoEvasioneDa, numeroEvasioneDa, annoEvasioneA, numeroEvasioneA, dataInserimentoDa, dataInserimentoA,
				annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa, dataEmissioneA,
				tipoEvasioneId, statoEvasioneId, strutturaCompetenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, impegnoId, subimpegnoId, oggettoSpesaId, checkVisibilitaDocumentale,
				cfUtente, utenteId, settoreId, params, jpql);
		
		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
				.append(", tev.testataEvasioneId ASC ");
		}
		
		if (sortField == null) {
			jpql.append(" ORDER BY tev.evasioneAnno DESC, tev.evasioneNumero DESC ");
		}
		
		return getPagedResult(jpql, params, page, size);
	}
	
	@Override
	public long countRicercaEvasioni(
			Integer annoEvasioneDa, 
			Integer numeroEvasioneDa, 
			Integer annoEvasioneA, 
			Integer numeroEvasioneA, 
			Date dataInserimentoDa, 
			Date dataInserimentoA,
			Integer annoOrdineDa, 
			Integer numeroOrdineDa, 
			Integer annoOrdineA, 
			Integer numeroOrdineA, 
			Date dataEmissioneDa, 
			Date dataEmissioneA,
			Integer tipoEvasioneId,
			Integer statoEvasioneId,
			UUID strutturaCompetenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId){
		
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataEvasione tev ")
				.append(" WHERE tev.dataCancellazione IS NULL ");
	
		composeQueryRicercaEvasioni(
				annoEvasioneDa, numeroEvasioneDa, annoEvasioneA, numeroEvasioneA, dataInserimentoDa, dataInserimentoA,
				annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa, dataEmissioneA,
				tipoEvasioneId, statoEvasioneId, strutturaCompetenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, impegnoId, subimpegnoId, oggettoSpesaId, false,
				null, null, null, params, jpql);
		
		Query qn = composeQuery(getCountQuery(jpql), params);
		long count = ((Number) qn.getSingleResult()).longValue();
		return count; 		

	}

	private void composeQueryRicercaEvasioni(
			Integer annoEvasioneDa, 
			Integer numeroEvasioneDa, 
			Integer annoEvasioneA, 
			Integer numeroEvasioneA, 
			Date dataInserimentoDa, 
			Date dataInserimentoA,
			Integer annoOrdineDa, 
			Integer numeroOrdineDa, 
			Integer annoOrdineA, 
			Integer numeroOrdineA, 
			Date dataEmissioneDa, 
			Date dataEmissioneA,
			Integer tipoEvasioneId,
			Integer statoEvasioneId,
			UUID strutturaCompetenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId,
			boolean checkVisibilitaDocumentale, String cfUtente, UUID utenteId,
			UUID settoreId, Map<String, Object> params, StringBuilder jpql) {
		
		if(annoEvasioneDa != null) {
			jpql.append(" AND tev.evasioneAnno >= :annoEvasioneDa ");
			params.put("annoEvasioneDa", annoEvasioneDa);
		}
		if (annoEvasioneA != null) {
			jpql.append(" AND tev.evasioneAnno <= :annoEvasioneA ");
			params.put("annoEvasioneA", annoEvasioneA);
		}
		if (numeroEvasioneDa != null) {
			jpql.append(" AND tev.evasioneNumero >= :numeroEvasioneDa ");
			params.put("numeroEvasioneDa", numeroEvasioneDa);
		}
		if (numeroEvasioneA != null) {
			jpql.append(" AND tev.evasioneNumero <= :numeroEvasioneA ");
			params.put("numeroEvasioneA", numeroEvasioneA);
		}
		if (dataInserimentoDa != null) {
			jpql.append(" AND tev.dataInserimento >= :dataInserimentoDa ");
			params.put("dataInserimentoDa", dataInserimentoDa);
		}
		if (dataInserimentoA != null) {
			jpql.append(" AND tev.dataInserimento <= :dataInserimentoA ");
			params.put("dataInserimentoA", dataInserimentoA);
		}
		
		
		if (annoOrdineDa != null || annoOrdineA != null || numeroOrdineDa != null || numeroOrdineA != null || dataEmissioneDa != null || dataEmissioneA != null || provvedimentoAnno != null || provvedimentoNumero != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioEvasione ctoe WHERE ctoe.cpassTOrdTestataEvasione = tev ");
			if(annoOrdineDa != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineAnno >= :annoOrdineDa ");
				params.put("annoOrdineDa", annoOrdineDa);
			}
			if (annoOrdineA != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineAnno <= :annoOrdineA ");
				params.put("annoOrdineA", annoOrdineA);
			}
			if (numeroOrdineDa != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineNumero >= :numeroOrdineDa ");
				params.put("numeroOrdineDa", numeroOrdineDa);
			}
			if (numeroOrdineA != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineNumero <= :numeroOrdineA ");
				params.put("numeroOrdineA", numeroOrdineA);
			}
			if (dataEmissioneDa != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.dataEmissione >= :dataEmissioneDa ");
				params.put("dataEmissioneDa", dataEmissioneDa);
			}
			if (dataEmissioneA != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.dataEmissione <= :dataEmissioneA ");
				params.put("dataEmissioneA", dataEmissioneA);
			}
			if (provvedimentoAnno != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.provvedimentoAnno = :provvedimentoAnno ");
				params.put("provvedimentoAnno", provvedimentoAnno);
			}
			if (provvedimentoNumero != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.provvedimentoNumero = :provvedimentoNumero ");
				params.put("provvedimentoNumero", provvedimentoNumero);
			}
			jpql.append(" ) ");
		}
		
		JpaQueryHelper.andFieldEquals(jpql, params, "tev.cpassDOrdTipoEvasione.tipoEvasioneId", "tipoEvasioneId", tipoEvasioneId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tev.cpassDStato.statoId", "statoEvasioneId", statoEvasioneId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tev.cpassTSettore.settoreId", "strutturaCompetenteId", strutturaCompetenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tev.cpassTFornitore.fornitoreId", "fornitoreId", fornitoreId);
		
		if (strutturaDestinatarioId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioEvasione ctoe WHERE ctoe.cpassTOrdTestataEvasione = tev AND ctoe.cpassTSettore.settoreId = :destinatarioId)");
			params.put("destinatarioId", strutturaDestinatarioId);
		}
		if (impegnoId != null ) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioEvasione ctode, CpassTOrdRigaEvasione ctere, CpassTOrdImpegnoEvasione ctei ")
			.append(" WHERE ctode.cpassTOrdTestataEvasione = tev and ctere.cpassTOrdDestinatarioEvasione = ctode ")
			.append(" AND ctei.cpassTOrdRigaEvasione = ctere and ctei.cpassTImpegno.impegnoId = :impegnoId)");
			params.put("impegnoId", impegnoId);
		}
		if (subimpegnoId != null ) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioEvasione ctode, CpassTOrdRigaEvasione ctore, CpassTOrdImpegnoEvasione ctei, CpassTOrdSubimpegnoEvasione ctes ")
			.append("  WHERE ctode.cpassTOrdTestataEvasione = tev and ctore.cpassTOrdDestinatarioEvasione = ctode and ctei.cpassTOrdRigaEvasione = ctore ")
			.append("  and ctes.cpassTOrdImpegnoEvasione = ctei and ctes.cpassTSubimpegno.subimpegnoId = :subimpegnoId ");
			params.put("subimpegnoId", subimpegnoId);
		}
		if (oggettoSpesaId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioEvasione ctode, CpassTOrdRigaEvasione ctore WHERE ctode.cpassTOrdTestataEvasione = tev and ctore.cpassTOrdDestinatarioEvasione = ctode and ctore.cpassDOggettiSpesa.oggettiSpesaId = :oggettoSpesaId ) ");
			params.put("oggettoSpesaId", oggettoSpesaId);
		}
		
		if (checkVisibilitaDocumentale && settoreId != null) {

			Date now = new Date();
			
			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ")
			.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))")
			.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))")
			.append(" AND rrus.cpassDRuolo.ruoloCodice = :ruoloAdmin) ");
			jpql.append(" OR ( (tev.cpassDStato.statoCodice = :statoCodice AND tev.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			jpql.append(" OR (tev.cpassDStato.statoCodice != :statoCodice ");
//			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus WHERE crus.cpassTSettore.cpassTEnte.enteId = tev.cpassTSettore.cpassTEnte.enteId AND crus.cpassTUtente.utenteId = :utenteId ");
//			15.09.2020 Il controllo è da farsi sul settore non sull'ente del settore 
			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus WHERE crus.cpassTSettore.settoreId = tev.cpassTSettore.settoreId AND crus.cpassTUtente.utenteId = :utenteId ");
			jpql.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and tev.dataInserimento <= crus.dataValiditaFine )))))) ");
			
			params.put("settoreId", settoreId);
			params.put("utenteId", utenteId);
			params.put("now", now);
			
			params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());
			params.put("statoCodice", ConstantsCPassStato.StatoEnum.BOZZA.getCostante());
			
			params.put("cfUtenteLoggato", cfUtente);
			
		}
	}

	
}
