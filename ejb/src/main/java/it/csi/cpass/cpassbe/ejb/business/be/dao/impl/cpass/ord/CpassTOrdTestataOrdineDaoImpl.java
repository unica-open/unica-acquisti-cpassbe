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
import java.math.BigInteger;
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
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Data Access Object implementor for the entity CpassTOrdTestataOrdine
 */
@ApplicationScoped
public class CpassTOrdTestataOrdineDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdTestataOrdine> implements CpassTOrdTestataOrdineDao {

	@Override
	public Optional<CpassTOrdTestataOrdine> findByAnnoENumero(Integer anno, Integer numero, UUID enteId) {
		CpassTOrdTestataOrdine queryResult = null;
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTOrdTestataOrdine tord  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.ordineAnno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.ordineNumero", "numero", numero);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTOrdTestataOrdine> query = composeTypedQuery(jpql, params);
		final List<CpassTOrdTestataOrdine> results = query.getResultList();
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
			Settore settoreEmittente,
			Integer settoreIndirizzoId,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			UUID enteId){

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassTOrdTestataOrdine tord  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);

		composeQueryRicercaOrdini(annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa,
				dataEmissioneA, tipoOrdineId, statoOrdineId, statoNsoId, lottoAnno, lottoNumero, tipoProceduraId,
				numeroProcedura, strutturaEmittenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, impegnoId, subimpegnoId, oggettoSpesaId, cpvId, settoreEmittente, settoreIndirizzoId, checkVisibilitaDocumentale,
				cfUtente, utenteId, settoreId,params, jpql);

		if (sortField != null && sortDirection != null) {
			jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
			.append(", tord.testataOrdineId ASC ");
		}

		if (sortField == null) {
			jpql.append(" ORDER BY tord.ordineAnno DESC, tord.ordineNumero DESC ");
		}
		log.info(cfUtente, "jpql " + jpql);
		final Page<CpassTOrdTestataOrdine> ris = getPagedResult(jpql, params, page, size);
		return ris;
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
			Integer cpvId,
			Settore settoreEmittente,
			Integer settoreIndirizzoId){

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataOrdine tord  WHERE 1=1 ");

		composeQueryRicercaOrdini(annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa,
				dataEmissioneA, tipoOrdineId, statoOrdineId, statoNsoId, lottoAnno, lottoNumero, tipoProceduraId,
				numeroProcedura, strutturaEmittenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, impegnoId, subimpegnoId, oggettoSpesaId, cpvId, settoreEmittente, settoreIndirizzoId, false,
				null, null, null,params, jpql);

		log.info("sql jpql ", jpql.toString());




		final Query qn = composeQuery(getCountQuery(jpql.toString()), params);

		final long count = ((Number) qn.getSingleResult()).longValue();
		log.info("sql count ", count);

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
			Integer oggettoSpesaId, Integer cpvId, Settore settoreEmittente, Integer settoreIndirizzoId, boolean checkVisibilitaDocumentale, String cfUtente, UUID utenteId,
			UUID settoreId, Map<String, Object> params, StringBuilder jpql) {

		// TODO da capire cosa si intende la riceca numero con anni differenti

/*
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
		*/

		if(annoOrdineDa!=null && annoOrdineA!=null && annoOrdineDa<annoOrdineA) {

			jpql.append(" AND (");
			for(int i=annoOrdineDa; i<=annoOrdineA; i++) {
				if(i==annoOrdineDa) {
					if(numeroOrdineDa!= null) {
						jpql.append("( tord.ordineAnno = :annoOrdine"+i+" AND tord.ordineNumero >= :numeroOrdineDa )");
						params.put("numeroOrdineDa", numeroOrdineDa);
					}else {
						jpql.append(" tord.ordineAnno = :annoOrdine"+i);
					}
					params.put("annoOrdine"+i, i);
					jpql.append(" OR ");
				}

				if(i<annoOrdineA && i>annoOrdineDa) {
					jpql.append(" tord.ordineAnno = :annoOrdine"+i);
					jpql.append(" OR ");
					params.put("annoOrdine"+i, i);
				}

				if(i==annoOrdineA) {
					if(numeroOrdineA!= null) {
						jpql.append("( tord.ordineAnno = :annoOrdine"+i+" and tord.ordineNumero <= :numeroOrdineA )");
						params.put("numeroOrdineA", numeroOrdineA);
					}else {
						jpql.append(" tord.ordineAnno = :annoOrdine"+i);
					}
					params.put("annoOrdine"+i, i);
				}


			}
			jpql.append(")");

		}else {
			if(annoOrdineDa != null && numeroOrdineDa == null) {
				jpql.append(" AND tord.ordineAnno >= :annoOrdineDa ");
				params.put("annoOrdineDa", annoOrdineDa);
			}
			if (annoOrdineDa == null && numeroOrdineDa != null) {
				jpql.append(" AND tord.ordineNumero >= :numeroOrdineDa ");
				params.put("numeroOrdineDa", numeroOrdineDa);
			}
			if (annoOrdineDa != null && numeroOrdineDa != null) {
				jpql.append(" AND tord.ordineAnno >= :annoOrdineDa ");
				jpql.append(" AND tord.ordineNumero >= :numeroOrdineDa ");
				params.put("annoOrdineDa", annoOrdineDa);
				params.put("numeroOrdineDa", numeroOrdineDa);
			}

			if (annoOrdineA != null && numeroOrdineA == null) {
				jpql.append(" AND tord.ordineAnno <= :annoOrdineA ");
				params.put("annoOrdineA", annoOrdineA);
			}

			if (annoOrdineA == null && numeroOrdineA != null) {
				jpql.append(" AND tord.ordineNumero <= :numeroOrdineA ");
				params.put("numeroOrdineA", numeroOrdineA);
			}
			if (annoOrdineA != null && numeroOrdineA != null) {
				jpql.append(" AND tord.ordineAnno <= :annoOrdineA ");
				jpql.append(" AND tord.ordineNumero <= :numeroOrdineA ");
				params.put("annoOrdineA", annoOrdineA);
				params.put("numeroOrdineA", numeroOrdineA);
			}
		}



		if (dataEmissioneDa != null) {
			jpql.append(" AND date_trunc('day',tord.dataEmissione) >= :dataEmissioneDa ");
			params.put("dataEmissioneDa", dataEmissioneDa);
		}
		if (dataEmissioneA != null) {
			jpql.append(" AND date_trunc('day',tord.dataEmissione) <= :dataEmissioneA ");
			params.put("dataEmissioneA", dataEmissioneA);
		}

		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDOrdTipoOrdine.tipoOrdineId", "tipoOrdineId", tipoOrdineId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDStato.statoId", "statoOrdineId", statoOrdineId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDOrdStatoNso.statoNsoId", "statoNsoId", statoNsoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.lottoAnno", "lottoAnno", lottoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.lottoNumero", "lottoNumero", lottoNumero);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDOrdTipoProcedura.tipoProceduraId", "tipoProceduraId", tipoProceduraId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.numeroProcedura", "numeroProcedura", numeroProcedura);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.settoreId", "settoreEmittente", settoreEmittente.getId());
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
			jpql.append(" AND EXISTS (  ");
			jpql.append("  FROM CpassTOrdDestinatarioOrdine ctod, CpassTOrdRigaOrdine ctoro, CpassTOrdImpegnoOrdine ctoi, CpassTOrdSubimpegnoOrdine ctos ");
			jpql.append("  WHERE ctod.cpassTOrdTestataOrdine = tord and ctoro.cpassTOrdDestinatario = ctod and ctoi.cpassTOrdRigaOrdine = ctoro ");
			jpql.append("  and ctos.cpassTOrdImpegnoOrdine = ctoi and ctos.cpassTSubimpegno.subimpegnoId = :subimpegnoId ");
			jpql.append("            )");

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

		if(settoreIndirizzoId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioOrdine ctod WHERE ctod.cpassTOrdTestataOrdine = tord AND ctod.cpassTSettoreIndirizzo.settoreIndirizzoId = :settoreIndirizzoId)");
			params.put("settoreIndirizzoId", settoreIndirizzoId);

		}
		if (checkVisibilitaDocumentale && settoreId != null) {

			final Date now = new Date();

			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ")
			//issue-228
			//.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))")
			.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))")
			.append(" AND rrus.cpassDRuolo.ruoloCodice in ( :ruoloAdmin, :ruoloAdminEnte) )");
			jpql.append(" OR ( (tord.cpassDStato.statoCodice = :statoCodice AND tord.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			jpql.append(" OR (tord.cpassDStato.statoCodice != :statoCodice ");
			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus, CpassRRuoloUtenteSettore crrus , CpassRRuoloModulo crrm ")
			.append( " WHERE crus.cpassTSettore.settoreId = tord.cpassTSettore.settoreId AND crus.cpassTUtente.utenteId = :utenteId ")
			.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and tord.dataEmissione <= crus.dataValiditaFine )) ")
			.append(" AND crrus.cpassRUtenteSettore.id = crus.id  ")
			.append(" AND crrus.cpassDRuolo.id = crrm.cpassDRuolo.id  ")
			.append(" AND crrm.cpassDModulo.moduloCodice =  :moduloCodice ");

			jpql.append(" )))) ");


			params.put("settoreId", settoreId);
			params.put("utenteId", utenteId);
			params.put("now", now);
			params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());
			params.put("ruoloAdminEnte", CpassRuoloEnum.ADMIN_ENTE.getCodice());
			//params.put("ruoloOsservatore", CpassRuoloEnum.OSSERVATORE_RMS.getCodice());
			params.put("statoCodice", ConstantsCPassStato.StatoEnum.BOZZA.getCostante());
			params.put("cfUtenteLoggato", cfUtente);
			params.put("moduloCodice", ConstantsDecodifiche.ModuloEnum.ORD.getCodice());

		}
	}

	@Override
	public List<CpassTOrdTestataOrdine> findTestateOrdineByEvasioneId(UUID evasioneId) {
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> param = new HashMap<>();
		// fare particolare attenzione al cast a stringa dei campi di tipo UUID quando si utilizza
		// una query nativa diversamente ottieni errore No Dialect mapping for JDBC type: 1111
		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("  CAST( testataOrdine.testata_ordine_id AS VARCHAR) ");
		sql.append(" ,testataOrdine.ordine_anno ");
		sql.append(" ,testataOrdine.ordine_numero ");
		sql.append(" FROM ");
		sql.append(" cpass_t_ord_testata_evasione testataEvasione");
		sql.append(" ,cpass_t_ord_destinatario_evasione destEvasione");
		sql.append(" ,cpass_t_ord_riga_evasione rigaevasione ");
		sql.append(" ,cpass_t_ord_riga_ordine rigaordine");
		sql.append(" ,cpass_t_ord_destinatario_ordine destordine ");
		sql.append(" ,cpass_t_ord_testata_ordine testataOrdine");
		sql.append(" ,cpass_d_stato stato");
		sql.append(" ,cpass_d_stato stato_riga_evasione");
		sql.append(" WHERE ");
		sql.append("       testataEvasione.testata_evasione_id    = destEvasione.testata_evasione_id ");
		sql.append("   AND destEvasione.destinatario_evasione_id  = rigaevasione.destinatario_evasione_id ");
		sql.append("   AND rigaevasione.riga_ordine_id            = rigaordine.riga_ordine_id ");
		sql.append("   AND rigaordine.destinatario_id             = destordine.destinatario_id ");
		sql.append("   AND destordine.testata_ordine_id           = testataOrdine.testata_ordine_id ");
		sql.append("   AND testataOrdine.stato_id                 = stato.stato_id ");
		sql.append("   AND stato.stato_codice                     <> :ordAnnullato ");
		sql.append("   AND testataEvasione.testata_evasione_id    = :evasioneId");

		sql.append("   AND rigaevasione.stato_id =  stato_riga_evasione.stato_id ");
		sql.append("   AND stato_riga_evasione.stato_codice <> :selAnnullata ");

		// DATE DI CANCELLAZIONE
		sql.append("   AND destEvasione.data_cancellazione  is null  ");
		sql.append("   AND rigaevasione.data_cancellazione  is null  ");
		sql.append("   AND rigaordine.data_cancellazione    is null  ");
		sql.append("   AND destordine.data_cancellazione    is null  ");
		sql.append("   AND testataOrdine.data_cancellazione is null  ");

		sql.append(" ORDER BY testataOrdine.ordine_anno, testataOrdine.ordine_numero ");

		param.put("evasioneId", evasioneId);
		param.put("ordAnnullato", StatoOrdineEnum.ANNULLATO.getCostante());
		param.put("selAnnullata", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante());
		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> resultList = query.getResultList();

		final List<UUID> listaId = new ArrayList<>();
		for(final Object[] obj : resultList) {
			listaId.add(UUID.fromString((String)obj[0]));
		}
		if(listaId.isEmpty()) {
			return new ArrayList<>();
		}

		return findOrdineByIds(listaId);
	}

	@Override
	public List<CpassTOrdRigaOrdine> findRigheByOrdineAndEvasioneId(UUID ordineId, UUID evasioneId) {
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> param = new HashMap<>();
		final List<CpassTOrdRigaOrdine> result = new ArrayList<>();

		// fare particolare attenzione al cast a stringa dei campi di tipo UUID quando si utilizza
		// una query nativa diversamente ottieni errore No Dialect mapping for JDBC type: 1111
		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append(" CAST(rigaOrd.riga_ordine_id AS VARCHAR), ");
		sql.append(" rigaEv.importo_totale ");
		sql.append(" FROM ");
		sql.append(" cpass_t_ord_riga_ordine rigaOrd, ");
		sql.append(" cpass_t_ord_destinatario_ordine destOrd, ");
		sql.append(" cpass_t_ord_testata_ordine testOrd, ");
		sql.append(" cpass_t_ord_testata_evasione testEv, ");
		sql.append(" cpass_t_ord_destinatario_evasione destEv, ");
		sql.append(" cpass_t_ord_riga_evasione rigaEv, ");
		sql.append(" cpass_d_stato cds, ");
		sql.append(" cpass_d_stato cdseo ");
		sql.append(" WHERE ");
		sql.append(" rigaOrd.riga_ordine_id = rigaEv.riga_ordine_id ");
		sql.append(" AND rigaOrd.destinatario_id = destOrd.destinatario_id ");
		sql.append(" AND destOrd.testata_ordine_id = testOrd.testata_ordine_id ");
		sql.append(" AND rigaEv.destinatario_evasione_id = destEv.destinatario_evasione_id ");
		sql.append(" AND destEv.testata_evasione_id = testEv.testata_evasione_id ");
		sql.append(" AND testEv.testata_evasione_id = :idEvasione ");
		sql.append(" AND testOrd.testata_ordine_id = :idOrdine ");
		sql.append(" AND rigaEv.stato_id = cdseo.stato_id ");
		sql.append(" AND cdseo.stato_codice <> :selAnnullata ");
		sql.append(" AND testOrd.stato_id = cds.stato_id ");
		sql.append(" AND cds.stato_codice <> :ordAnnullato ");

		// DATE DI CANCELLAZIONE per coerenza con la ricerca ordini
		sql.append(" AND destEv.data_cancellazione is null  ");
		sql.append(" AND rigaEv.data_cancellazione is null  ");
		sql.append(" AND rigaOrd.data_cancellazione is null ");
		sql.append(" AND destOrd.data_cancellazione is null ");
		sql.append(" AND testOrd.data_cancellazione is null ");

		param.put("idEvasione", evasioneId);
		param.put("idOrdine", ordineId);
		param.put("ordAnnullato", StatoOrdineEnum.ANNULLATO.getCostante());
		param.put("selAnnullata", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante());

		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> resultList = query.getResultList();

		for(final Object[] obj : resultList) {
			final CpassTOrdRigaOrdine riga = new CpassTOrdRigaOrdine();
			riga.setId(UUID.fromString((String) obj[0]));
			riga.setImportoTotale((BigDecimal) obj[1]);

			result.add(riga);
		}

		return result;
	}

	@Override
	public List<CpassTOrdTestataOrdine> findTestateOrdineByDestinatarioEvasioneId(UUID destinatarioEvasioneId) {
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> param = new HashMap<>();
		// fare particolare attenzione al cast a stringa dei campi di tipo UUID quando si utilizza
		// una query nativa doiversamente ottieni errore No Dialect mapping for JDBC type: 1111
		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("  CAST( testataOrdine.testata_ordine_id AS VARCHAR) ");
		sql.append(" ,testataOrdine.ordine_anno ");
		sql.append(" ,testataOrdine.ordine_numero ");
		sql.append(" FROM ");
		sql.append(" cpass_t_ord_destinatario_evasione destEvasione");
		sql.append(" ,cpass_t_ord_riga_evasione rigaevasione");
		sql.append(" ,cpass_t_ord_riga_ordine rigaordine");
		sql.append(" ,cpass_t_ord_destinatario_ordine destordine");
		sql.append(" ,cpass_t_ord_testata_ordine testataOrdine ");
		sql.append(" ,cpass_d_stato stato");
		sql.append(" ,cpass_d_stato stato_riga_evasione");
		sql.append(" WHERE ");
		sql.append("       destEvasione.destinatario_evasione_id = rigaevasione.destinatario_evasione_id ");
		sql.append("   AND rigaevasione.riga_ordine_id           = rigaordine.riga_ordine_id ");
		sql.append("   AND rigaordine.destinatario_id            = destordine.destinatario_id ");
		sql.append("   AND destordine.testata_ordine_id  		 = testataOrdine.testata_ordine_id ");
		sql.append("   AND testataOrdine.stato_id 				 = stato.stato_id ");
		sql.append("   AND stato.stato_codice 					 <> :ordAnnullato ");
		sql.append("   AND rigaevasione.stato_id 				 = stato_riga_evasione.stato_id ");
		sql.append("   AND stato_riga_evasione.stato_codice 	 <> :selAnnullata ");
		sql.append("   AND destEvasione.destinatario_evasione_id = :destinatarioEvasioneId");
		// DATE DI CANCELLAZIONE
		sql.append("   AND rigaevasione.data_cancellazione is null ");
		sql.append("   AND rigaordine.data_cancellazione is null ");
		sql.append("   AND destordine.data_cancellazione is null ");
		sql.append("   AND testataOrdine.data_cancellazione is null ");
		sql.append(" ORDER BY testataOrdine.ordine_anno, testataOrdine.ordine_numero ");
		param.put("destinatarioEvasioneId", destinatarioEvasioneId);
		param.put("ordAnnullato", StatoOrdineEnum.ANNULLATO.getCostante());
		param.put("selAnnullata", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante());

		final Query query = composeNativeQuery(sql.toString(), param);
		final List<Object[]> resultList = query.getResultList();
		final List<UUID> listaId = new ArrayList<>();
		for(final Object[] obj : resultList) {
			listaId.add(UUID.fromString((String)obj[0]));
		}
		if(listaId.isEmpty()) {
			return new ArrayList<>();
		}
		return findOrdineByIds(listaId);
	}


	@Override
	public List<CpassTOrdTestataOrdine> findTestateOrdineByRigaEvasioneId(UUID rigaEvasioneId) {
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> param = new HashMap<>();
		// fare particolare attenzione al cast a stringa dei campi di tipo UUID quando si utilizza
		// una query nativa diversamente ottieni errore No Dialect mapping for JDBC type: 1111
		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("  CAST( testataOrdine.testata_ordine_id AS VARCHAR) ");
		sql.append(" ,testataOrdine.ordine_anno ");
		sql.append(" ,testataOrdine.ordine_numero ");
		sql.append(" FROM ");
		sql.append("  cpass_t_ord_riga_evasione rigaevasione");
		sql.append(" ,cpass_t_ord_riga_ordine rigaordine");
		sql.append(" ,cpass_t_ord_destinatario_ordine destordine");
		sql.append(" ,cpass_t_ord_testata_ordine testataOrdine ");
		sql.append(" ,cpass_d_stato stato");
		sql.append(" ,cpass_d_stato stato_riga_evasione");
		sql.append(" WHERE ");
		sql.append("       rigaevasione.riga_ordine_id          = rigaordine.riga_ordine_id ");
		sql.append("   AND rigaordine.destinatario_id           = destordine.destinatario_id ");
		sql.append("   AND destordine.testata_ordine_id  		= testataOrdine.testata_ordine_id ");
		sql.append("   AND testataOrdine.stato_id 				=  stato.stato_id ");
		sql.append("   AND stato.stato_codice 					<> :ordAnnullato ");
		sql.append("   AND rigaevasione.stato_id 				=  stato_riga_evasione.stato_id ");
		sql.append("   AND stato_riga_evasione.stato_codice 	<> :selAnnullata ");

		sql.append("   AND rigaevasione.riga_evasione_id   = :rigaEvasioneId");
		// DATE DI CANCELLAZIONE
		sql.append("   AND rigaordine.data_cancellazione is null ");
		sql.append("   AND destordine.data_cancellazione is null ");
		sql.append("   AND testataOrdine.data_cancellazione is null");

		sql.append(" ORDER BY testataOrdine.ordine_anno, testataOrdine.ordine_numero ");

		param.put("rigaEvasioneId", rigaEvasioneId);
		param.put("ordAnnullato", StatoOrdineEnum.ANNULLATO.getCostante());
		param.put("selAnnullata", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_EVASIONE_ANNULLATA.getCostante());

		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> resultList = query.getResultList();

		final List<UUID> listaId = new ArrayList<>();
		for(final Object[] obj : resultList) {
			listaId.add(UUID.fromString((String)obj[0]));
		}
		if(listaId.isEmpty()) {
			return new ArrayList<>();
		}

		return findOrdineByIds(listaId);
	}

	@Override
	public List<CpassTOrdTestataOrdine> findOrdineByIds(List<UUID> listaId) {
		log.info("findOrdineByIds", "start");
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM CpassTOrdTestataOrdine tord ");
		jpql.append(" WHERE tord.dataCancellazione IS NULL ");
		jpql.append(" AND tord.testataOrdineId in (:listaId) ");
		params.put("listaId", listaId);
		final TypedQuery<CpassTOrdTestataOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassTOrdTestataOrdine> findByAnnoENumeroEStato(Integer anno, Integer numero, String stato, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append(" FROM CpassTOrdTestataOrdine tord  WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.ordineAnno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.ordineNumero", "numero", numero);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDStato.statoCodice", "stato", stato);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTOrdTestataOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Integer countOggettiSpesaLegatiAOrdine(UUID testataOrdineId) {
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> params = new HashMap<>();
		sql.append(" SELECT COUNT(*) FROM");
		sql.append(" cpass_t_ord_riga_ordine ctoro, ");
		sql.append(" cpass_t_ord_destinatario_ordine ctodo, ");
		sql.append(" cpass_d_oggetti_spesa cdos ");
		sql.append(" WHERE ");
		sql.append(" ctodo.testata_ordine_id = :testataOrdineId ");
		sql.append(" AND ctoro.destinatario_id = ctodo.destinatario_id ");
		sql.append(" AND cdos.oggetti_spesa_id = ctoro.oggetti_spesa_id ");
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sql.toString(), params);
		final List<Object> resultList = query.getResultList();
		BigInteger result = BigInteger.ZERO;
		if(resultList != null && resultList.size() > 0) {
			result = (BigInteger) resultList.get(0);
		}
		return result.intValue();
	}

	@Override
	public List<CpassTOrdTestataOrdine> findTestataByAnnoOrNumero(Integer anno, Integer numero) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTOrdTestataOrdine tord WHERE 1 = 1 ");
		if (anno != null) {
			JpaQueryHelper.andFieldEquals(jpql, params, "tord.ordineAnno", "anno", anno);
		}
		if (numero != null) {
			JpaQueryHelper.andFieldEquals(jpql, params, "tord.ordineNumero", "numero", numero);
		}
		final TypedQuery<CpassTOrdTestataOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdTestataOrdine> findTestataOrdineByRdaId(UUID testataRdaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT croro.cpassTOrdTestataOrdine FROM CpassROrdRdaOrdine croro ");
		jpql.append(" WHERE croro.cpassTOrdTestataRda.testataRdaId = :testataRdaId ");
		params.put("testataRdaId", testataRdaId);
		final TypedQuery<CpassTOrdTestataOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTOrdTestataOrdine> findByStato(String stato, UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTOrdTestataOrdine tord WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassDStato.statoCodice", "stato", stato);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTOrdTestataOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void updateTestataOrdine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("update cpass_t_ord_testata_ordine set stato_id = 11 where testata_ordine_id in (select distinct testata_ordine_id from cpass_v_ordine where testata_ordine_id = :testataOrdineId)");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}

}

