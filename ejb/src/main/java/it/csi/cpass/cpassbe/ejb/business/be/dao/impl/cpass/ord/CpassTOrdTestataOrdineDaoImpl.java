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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord;

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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdTestataOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassTOrdTestataOrdine
 */
@ApplicationScoped
public class CpassTOrdTestataOrdineDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdTestataOrdine> implements CpassTOrdTestataOrdineDao {

	@Override
	public Optional<CpassTOrdTestataOrdine> findByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		
		CpassTOrdTestataOrdine queryResult = null;
		
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTOrdTestataOrdine tord ")
//			.append(" WHERE tord.dataCancellazione IS NULL ");
			.append(" WHERE 1 = 1 ");
		
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.ordineAnno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.ordineNumero", "numero", numero);
		
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);
		
		TypedQuery<CpassTOrdTestataOrdine> query = composeTypedQuery(jpql, params);
		
		List<CpassTOrdTestataOrdine> results = query.getResultList();
		
		if(!results.isEmpty()) {
			queryResult = results.get(0);
		}
		
		return Optional.ofNullable(queryResult);
	}
	
	@Override
	public Page<CpassTOrdTestataOrdine> findPaginated(Integer annoOrdineDa, 
			Integer numeroOrdineDa, 
			Integer annoOrdineA, 
			Integer numeroOrdineA, 
			Date dataEmissioneDa, 
			Date dataEmissioneA,
			Integer tipoOrdineId,
			Integer statoOrdineId,
			Integer statoNsoId,
			Integer lottoAnno,
			Integer lottoNumero,
			Integer tipoProceduraId,
			String numeroProcedura,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId,
			Integer cpvId,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId){
		
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataOrdine tord ")
//				.append(" WHERE tord.dataCancellazione IS NULL ");
				.append(" WHERE 1 = 1 ");
		
		composeQueryRicercaOrdini(annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa,
				dataEmissioneA, tipoOrdineId, statoOrdineId, statoNsoId, lottoAnno, lottoNumero, tipoProceduraId,
				numeroProcedura, strutturaEmittenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, impegnoId, subimpegnoId, oggettoSpesaId, cpvId, checkVisibilitaDocumentale,
				cfUtente, utenteId, settoreId, params, jpql);
		
		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
				.append(", tord.testataOrdineId ASC ");
		}
		
		if (sortField == null) {
			jpql.append(" ORDER BY tord.ordineAnno DESC, tord.ordineNumero DESC ");
		}
		
		return getPagedResult(jpql, params, page, size);
	}


	@Override
	public long countRicercaOrdini(Integer annoOrdineDa, 
			Integer numeroOrdineDa, 
			Integer annoOrdineA, 
			Integer numeroOrdineA, 
			Date dataEmissioneDa, 
			Date dataEmissioneA,
			Integer tipoOrdineId,
			Integer statoOrdineId,
			Integer statoNsoId,
			Integer lottoAnno,
			Integer lottoNumero,
			Integer tipoProceduraId,
			String numeroProcedura,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId,
			Integer cpvId){
		
		Map<String, Object> params = new HashMap<>();
		
		StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataOrdine tord ")
//				.append(" WHERE tord.dataCancellazione IS NULL ");
				.append(" WHERE 1=1 ");
		
		composeQueryRicercaOrdini(annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa,
				dataEmissioneA, tipoOrdineId, statoOrdineId, statoNsoId, lottoAnno, lottoNumero, tipoProceduraId,
				numeroProcedura, strutturaEmittenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, impegnoId, subimpegnoId, oggettoSpesaId, cpvId, false,
				null, null, null, params, jpql);
		
		Query qn = composeQuery(getCountQuery(jpql), params);
		long count = ((Number) qn.getSingleResult()).longValue();
		return count; 		

	}
	/**
	 * @param annoOrdineDa
	 * @param numeroOrdineDa
	 * @param annoOrdineA
	 * @param numeroOrdineA
	 * @param dataEmissioneDa
	 * @param dataEmissioneA
	 * @param tipoOrdineId
	 * @param statoOrdineId
	 * @param statoInvioNso
	 * @param lottoAnno
	 * @param lottoNumero
	 * @param tipoProceduraId
	 * @param numeroProcedura
	 * @param strutturaEmittenteId
	 * @param strutturaDestinatarioId
	 * @param fornitoreId
	 * @param provvedimentoAnno
	 * @param provvedimentoNumero
	 * @param impegnoId
	 * @param subimpegnoId
	 * @param oggettoSpesaId
	 * @param cpvId
	 * @param checkVisibilitaDocumentale
	 * @param cfUtente
	 * @param utenteId
	 * @param settoreId
	 * @param params
	 * @param jpql
	 */
	private void composeQueryRicercaOrdini(Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA,
			Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, Integer tipoOrdineId,
			Integer statoOrdineId, Integer statoNsoId, Integer lottoAnno, Integer lottoNumero,
			Integer tipoProceduraId, String numeroProcedura, UUID strutturaEmittenteId, UUID strutturaDestinatarioId,
			UUID fornitoreId, Integer provvedimentoAnno, String provvedimentoNumero, UUID impegnoId, UUID subimpegnoId,
			Integer oggettoSpesaId, Integer cpvId, boolean checkVisibilitaDocumentale, String cfUtente, UUID utenteId,
			UUID settoreId, Map<String, Object> params, StringBuilder jpql) {
		if(annoOrdineDa != null) {
			jpql.append(" AND tord.ordineAnno >= :annoOrdineDa ");
			params.put("annoOrdineDa", annoOrdineDa);
		}
		if (annoOrdineA != null) {
			jpql.append(" AND tord.ordineAnno <= :annoOrdineA ");
			params.put("annoOrdineA", annoOrdineA);
		}
		if (numeroOrdineDa != null) {
			jpql.append(" AND tord.ordineNumero >= :numeroOrdineDa ");
			params.put("numeroOrdineDa", numeroOrdineDa);
		}
		if (numeroOrdineA != null) {
			jpql.append(" AND tord.ordineNumero <= :numeroOrdineA ");
			params.put("numeroOrdineA", numeroOrdineA);
		}
		if (dataEmissioneDa != null) {
			jpql.append(" AND tord.dataEmissione >= :dataEmissioneDa ");
			params.put("dataEmissioneDa", dataEmissioneDa);
		}
		if (dataEmissioneA != null) {
			jpql.append(" AND tord.dataEmissione <= :dataEmissioneA ");
			params.put("dataEmissioneA", dataEmissioneA);
		}
		
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDOrdTipoOrdine.tipoOrdineId", "tipoOrdineId", tipoOrdineId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDStato.statoId", "statoOrdineId", statoOrdineId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDOrdStatoNso", "statoNsoId", statoNsoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.lottoAnno", "lottoAnno", lottoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.lottonumero", "lottoNumero", lottoNumero);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDOrdTipoProcedura.tipoProceduraId", "tipoProceduraId", tipoProceduraId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.numeroProcedura", "numeroProcedura", numeroProcedura);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.settoreId", "strutturaEmittenteId", strutturaEmittenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTFornitore.fornitoreId", "fornitoreId", fornitoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.provvedimentoAnno", "provvedimentoAnno", provvedimentoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.provvedimentoNumero", "provvedimentoNumero", provvedimentoNumero);
		
		if (strutturaDestinatarioId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioOrdine ctod WHERE ctod.cpassTOrdTestataOrdine = tord AND ctod.cpassTSettore.settoreId = :destinatarioId)");
			params.put("destinatarioId", strutturaDestinatarioId);
		}
		if (impegnoId != null ) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioOrdine ctod, CpassTOrdRigaOrdine ctoro, CpassTOrdImpegnoOrdine ctoi ")
			.append(" WHERE ctod.cpassTOrdTestataOrdine = tord and ctoro.cpassTOrdDestinatario = ctod ")
			.append(" AND ctoi.cpassTOrdRigaOrdine = ctoro and ctoi.cpassTImpegno.impegnoId = :impegnoId)");
			params.put("impegnoId", impegnoId);
		}
		if (subimpegnoId != null ) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioOrdine ctod, CpassTOrdRigaOrdine ctoro, CpassTOrdImpegnoOrdine ctoi, CpassTOrdSubimpegnoOrdine ctos ")
			.append("  WHERE ctod.cpassTOrdTestataOrdine = tord and ctoro.cpassTOrdDestinatario = ctod and ctoi.cpassTOrdRigaOrdine = ctoro ")
			.append("  and ctos.cpassTOrdImpegnoOrdine = ctoi and ctos.cpassTSubimpegno.subimpegnoId = :subimpegnoId ");
			params.put("subimpegnoId", subimpegnoId);
		}
		if (oggettoSpesaId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioOrdine ctod, CpassTOrdRigaOrdine ctoro WHERE ctod.cpassTOrdTestataOrdine = tord and ctoro.cpassTOrdDestinatario = ctod and ctoro.cpassDOggettiSpesa.oggettiSpesaId = :oggettoSpesaId ) ");
			params.put("oggettoSpesaId", oggettoSpesaId);
		}
		if (cpvId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioOrdine ctod, CpassTOrdRigaOrdine ctoro WHERE ctod.cpassTOrdTestataOrdine = tord and ctoro.cpassTOrdDestinatario = ctod and ctoro.cpassDOggettiSpesa.cpassDCpv.cpvId = :cpvId ) ");
			params.put("cpvId", cpvId);
		}
		if (checkVisibilitaDocumentale && settoreId != null) {

			Date now = new Date();
			
			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ")
			.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))")
			.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))")
			.append(" AND rrus.cpassDRuolo.ruoloCodice = :ruoloAdmin) ");
			jpql.append(" OR ( (tord.cpassDStato.statoCodice = :statoCodice AND tord.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			jpql.append(" OR (tord.cpassDStato.statoCodice != :statoCodice ");
//			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus WHERE crus.cpassTSettore.cpassTEnte.enteId = tord.cpassTSettore.cpassTEnte.enteId AND crus.cpassTUtente.utenteId = :utenteId ");
			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus WHERE crus.cpassTSettore.settoreId = tord.cpassTSettore.settoreId AND crus.cpassTUtente.utenteId = :utenteId ");
			jpql.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and tord.dataEmissione <= crus.dataValiditaFine )))))) ");
			
			params.put("settoreId", settoreId);
			params.put("utenteId", utenteId);
			params.put("now", now);
			
			params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());
			params.put("statoCodice", ConstantsCPassStato.StatoEnum.BOZZA.getCostante());
			
			params.put("cfUtenteLoggato", cfUtente);
			
		}
	}

	@Override
	public List<CpassTOrdTestataOrdine> findTestateOrdineByEvasioneId(UUID evasioneId) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		// fare particolare attenzione al cast a stringa dei campi di tipo UUID quando si utilizza
		// una query nativa doiversamente ottieni errore No Dialect mapping for JDBC type: 1111
		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("  CAST( cpass_t_ord_testata_ordine.testata_ordine_id AS VARCHAR) ");
	    sql.append(" ,cpass_t_ord_testata_ordine.ordine_anno ");
		sql.append(" ,cpass_t_ord_testata_ordine.ordine_numero ");
	    sql.append(" FROM ");
	    sql.append(" cpass_t_ord_testata_evasione ");
	    sql.append(" ,cpass_t_ord_destinatario_evasione ");
	    sql.append(" ,cpass_t_ord_riga_evasione ");
	    sql.append(" ,cpass_t_ord_riga_ordine ");
	    sql.append(" ,cpass_t_ord_destinatario_ordine ");
	    sql.append(" ,cpass_t_ord_testata_ordine ");
	    sql.append(" ,cpass_d_stato ");
	    sql.append(" ,cpass_d_stato_el_ordine ");
	    sql.append(" WHERE ");
	    sql.append("       cpass_t_ord_testata_evasione.testata_evasione_id            = cpass_t_ord_destinatario_evasione.testata_evasione_id ");
	    sql.append("   AND cpass_t_ord_destinatario_evasione.destinatario_evasione_id  = cpass_t_ord_riga_evasione.destinatario_evasione_id ");
	    sql.append("   AND cpass_t_ord_riga_evasione.riga_ordine_id                    = cpass_t_ord_riga_ordine.riga_ordine_id ");
	    sql.append("   AND cpass_t_ord_riga_ordine.destinatario_id                     = cpass_t_ord_destinatario_ordine.destinatario_id ");
	    sql.append("   AND cpass_t_ord_destinatario_ordine.testata_ordine_id           = cpass_t_ord_testata_ordine.testata_ordine_id ");		
		sql.append("   AND cpass_t_ord_testata_ordine.stato_id                         =  cpass_d_stato.stato_id ");
		sql.append("   AND cpass_d_stato.stato_codice <> 'CANCELLATO' "); 
	    sql.append("   AND cpass_t_ord_testata_evasione.testata_evasione_id   = :evasioneId");
	    
		sql.append("   AND cpass_t_ord_riga_evasione.stato_el_ordine_id =  cpass_d_stato_el_ordine.stato_el_ordine_id ");
		sql.append("   AND cpass_d_stato_el_ordine.stato_codice <> 'CANCELLATO' "); 

	    // DATE DI CANCELLAZIONE
	    sql.append("   AND cpass_t_ord_destinatario_evasione.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_riga_evasione.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_riga_ordine.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_destinatario_ordine.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_testata_ordine.data_cancellazione is null");	    
	    
		sql.append(" ORDER BY cpass_t_ord_testata_ordine.ordine_anno, cpass_t_ord_testata_ordine.ordine_numero ");

		param.put("evasioneId", evasioneId);		
		Query query = composeNativeQuery(sql.toString(), param);
		
		List<Object[]> resultList = query.getResultList();
	    
		List<UUID> listaId = new ArrayList<UUID>();
		for(Object[] obj : resultList) {
			listaId.add(UUID.fromString((String)obj[0]));
		}		
	    if(listaId.isEmpty()) {
			return new ArrayList<CpassTOrdTestataOrdine>();
		}

	    return findOrdineByIds(listaId);
	}

	@Override
	public List<CpassTOrdTestataOrdine> findTestateOrdineByDestinatarioEvasioneId(UUID destinatarioEvasioneId) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		// fare particolare attenzione al cast a stringa dei campi di tipo UUID quando si utilizza
		// una query nativa doiversamente ottieni errore No Dialect mapping for JDBC type: 1111
		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("  CAST( cpass_t_ord_testata_ordine.testata_ordine_id AS VARCHAR) ");
	    sql.append(" ,cpass_t_ord_testata_ordine.ordine_anno ");
		sql.append(" ,cpass_t_ord_testata_ordine.ordine_numero ");
	    sql.append(" FROM ");
	    sql.append(" cpass_t_ord_destinatario_evasione ");
	    sql.append(" ,cpass_t_ord_riga_evasione ");
	    sql.append(" ,cpass_t_ord_riga_ordine ");
	    sql.append(" ,cpass_t_ord_destinatario_ordine ");
	    sql.append(" ,cpass_t_ord_testata_ordine ");
	    sql.append(" ,cpass_d_stato ");
	    sql.append(" ,cpass_d_stato_el_ordine ");
	    sql.append(" WHERE ");
	    sql.append("       cpass_t_ord_destinatario_evasione.destinatario_evasione_id  = cpass_t_ord_riga_evasione.destinatario_evasione_id ");
	    sql.append("   AND cpass_t_ord_riga_evasione.riga_ordine_id           = cpass_t_ord_riga_ordine.riga_ordine_id ");
	    sql.append("   AND cpass_t_ord_riga_ordine.destinatario_id            = cpass_t_ord_destinatario_ordine.destinatario_id ");
	    sql.append("   AND cpass_t_ord_destinatario_ordine.testata_ordine_id  = cpass_t_ord_testata_ordine.testata_ordine_id ");		
		sql.append("   AND cpass_t_ord_testata_ordine.stato_id =  cpass_d_stato.stato_id ");
		
		sql.append("   AND cpass_t_ord_riga_evasione.stato_el_ordine_id =  cpass_d_stato_el_ordine.stato_el_ordine_id ");
		sql.append("   AND cpass_d_stato_el_ordine.stato_codice <> 'ANNULLATA' "); 

		
		sql.append("   AND cpass_d_stato.stato_codice <> 'ANNULLATO' "); 
	    sql.append("   AND cpass_t_ord_destinatario_evasione.destinatario_evasione_id   = :destinatarioEvasioneId");
	    // DATE DI CANCELLAZIONE
	    sql.append("   AND cpass_t_ord_riga_evasione.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_riga_ordine.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_destinatario_ordine.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_testata_ordine.data_cancellazione is null");	    
	    
		sql.append(" ORDER BY cpass_t_ord_testata_ordine.ordine_anno, cpass_t_ord_testata_ordine.ordine_numero ");

		param.put("destinatarioEvasioneId", destinatarioEvasioneId);		
		Query query = composeNativeQuery(sql.toString(), param);
		
		List<Object[]> resultList = query.getResultList();
	    
		List<UUID> listaId = new ArrayList<UUID>();
		for(Object[] obj : resultList) {
			listaId.add(UUID.fromString((String)obj[0]));
		}		
	    if(listaId.isEmpty()) {
			return new ArrayList<CpassTOrdTestataOrdine>();
		}

	    return findOrdineByIds(listaId);
	}
	
	
	@Override
	public List<CpassTOrdTestataOrdine> findTestateOrdineByRigaEvasioneId(UUID rigaEvasioneId) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		// fare particolare attenzione al cast a stringa dei campi di tipo UUID quando si utilizza
		// una query nativa doiversamente ottieni errore No Dialect mapping for JDBC type: 1111
		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("  CAST( cpass_t_ord_testata_ordine.testata_ordine_id AS VARCHAR) ");
	    sql.append(" ,cpass_t_ord_testata_ordine.ordine_anno ");
		sql.append(" ,cpass_t_ord_testata_ordine.ordine_numero ");
	    sql.append(" FROM ");
	    sql.append("  cpass_t_ord_riga_evasione ");
	    sql.append(" ,cpass_t_ord_riga_ordine ");
	    sql.append(" ,cpass_t_ord_destinatario_ordine ");
	    sql.append(" ,cpass_t_ord_testata_ordine ");
	    sql.append(" ,cpass_d_stato ");
	    sql.append(" ,cpass_d_stato_el_ordine ");
	    sql.append(" WHERE ");
	    sql.append("       cpass_t_ord_riga_evasione.riga_ordine_id           = cpass_t_ord_riga_ordine.riga_ordine_id ");
	    sql.append("   AND cpass_t_ord_riga_ordine.destinatario_id            = cpass_t_ord_destinatario_ordine.destinatario_id ");
	    sql.append("   AND cpass_t_ord_destinatario_ordine.testata_ordine_id  = cpass_t_ord_testata_ordine.testata_ordine_id ");		
		sql.append("   AND cpass_t_ord_testata_ordine.stato_id =  cpass_d_stato.stato_id ");
		sql.append("   AND cpass_d_stato.stato_codice <> 'ANNULLATO' "); 
		sql.append("   AND cpass_t_ord_riga_evasione.stato_el_ordine_id =  cpass_d_stato_el_ordine.stato_el_ordine_id ");
		sql.append("   AND cpass_d_stato_el_ordine.stato_codice <> 'ANNULLATA' "); 
	    sql.append("   AND cpass_t_ord_riga_evasione.riga_evasione_id   = :rigaEvasioneId");
	    // DATE DI CANCELLAZIONE
	    sql.append("   AND cpass_t_ord_riga_ordine.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_destinatario_ordine.data_cancellazione is null ");
	    sql.append("   AND cpass_t_ord_testata_ordine.data_cancellazione is null");	    
	    
		sql.append(" ORDER BY cpass_t_ord_testata_ordine.ordine_anno, cpass_t_ord_testata_ordine.ordine_numero ");

		param.put("rigaEvasioneId", rigaEvasioneId);		
		Query query = composeNativeQuery(sql.toString(), param);
		
		List<Object[]> resultList = query.getResultList();
	    
		List<UUID> listaId = new ArrayList<UUID>();
		for(Object[] obj : resultList) {
			listaId.add(UUID.fromString((String)obj[0]));
		}		
	    if(listaId.isEmpty()) {
			return new ArrayList<CpassTOrdTestataOrdine>();
		}

	    return findOrdineByIds(listaId);
	}
	
	@Override
	public List<CpassTOrdTestataOrdine> findOrdineByIds(List<UUID> listaId) {
		log.info("findOrdineByIds", "start");
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("FROM CpassTOrdTestataOrdine tord ");
		jpql.append(" WHERE tord.dataCancellazione IS NULL ");

		jpql.append(" AND tord.testataOrdineId in (:listaId) ");
		params.put("listaId", listaId);		
		
		TypedQuery<CpassTOrdTestataOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();				
	}

}

