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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdRigaOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;

@ApplicationScoped
public class CpassTOrdRigaOrdineDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTOrdRigaOrdine> implements CpassTOrdRigaOrdineDao {

	@Override
	public List<CpassTOrdRigaOrdine> findByIdDestinatario(UUID idDestinatario, Integer progressivo) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdRigaOrdine riga ")
				.append(" WHERE riga.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.destinatarioId", "idDest", idDestinatario);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.progressivo", "progressivo", progressivo);
		final TypedQuery<CpassTOrdRigaOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	@Override
	public List<CpassTOrdRigaOrdine> findByIdDestinatarios(List<UUID> idDestinatarios) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdRigaOrdine riga ")
				.append(" WHERE riga.dataCancellazione IS NULL ")
				.append(" and riga.cpassTOrdDestinatario.destinatarioId in (:idDest)");
		params.put("idDest", idDestinatarios);
		final TypedQuery<CpassTOrdRigaOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public void deleteByDestinatario(UUID idDestinatario) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTOrdImpegnoOrdine ");
		sb.append(" WHERE cpassTOrdDestinatario.destinatarioId = :idDestinatario");

		final Map<String, Object> params = new HashMap<>();
		params.put("idDestinatario", idDestinatario);

		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public List<CpassTOrdRigaOrdine> findByTestataOrdine(UUID testataOrdineId, UUID rigaOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder();
		jpql.append("FROM CpassTOrdRigaOrdine riga ");
		jpql.append(" WHERE riga.dataCancellazione IS NULL ");
		jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.testataOrdineId = :testataOrdineId ");

		params.put("testataOrdineId", testataOrdineId);

		if (rigaOrdineId != null) {
			// esclude la riga specificata
			jpql.append(" AND riga.rigaOrdineId <> :rigaOrdineId ");
			params.put("rigaOrdineId", rigaOrdineId);
		}

		final TypedQuery<CpassTOrdRigaOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public long countDaEvadere(Integer annoOrdineDa,
			Integer numeroOrdineDa,
			Integer annoOrdineA,
			Integer numeroOrdineA,
			Date dataEmissioneDa,
			Date dataEmissioneA,
			Integer tipoOrdineId,
			Integer lottoAnno,
			Integer lottoNumero,
			Integer tipoProceduraId,
			String numeroProcedura,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			String provvedimentoTipo,
			UUID impegnoId,
			UUID subimpegnoId,
			List<Integer> oggettoSpesaId,
			Integer cpvId){

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdRigaOrdine riga ")
				.append(" WHERE riga.dataCancellazione IS NULL ");

		composeQueryRigheDaEvadere(params, jpql);

		composeQueryRicercaOrdiniDaEvadere(annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa,
				dataEmissioneA, tipoOrdineId, lottoAnno, lottoNumero, tipoProceduraId,
				numeroProcedura, strutturaEmittenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, provvedimentoTipo,impegnoId, subimpegnoId, oggettoSpesaId, cpvId, false,
				null, null, null, params, jpql);

		final Query qn = composeQuery(getCountQuery(jpql.toString()), params);
		final long count = ((Number) qn.getSingleResult()).longValue();
		return count;
	}

	@Override
	public List<CpassTOrdRigaOrdine> findDaEvadere(Integer annoOrdineDa,
			Integer numeroOrdineDa,
			Integer annoOrdineA,
			Integer numeroOrdineA,
			Date dataEmissioneDa,
			Date dataEmissioneA,
			Integer tipoOrdineId,
			Integer lottoAnno,
			Integer lottoNumero,
			Integer tipoProceduraId,
			String numeroProcedura,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			String provvedimentoTipo,
			UUID impegnoId,
			UUID subimpegnoId,
			List<Integer> oggettoSpesaId,
			Integer cpvId,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId){
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdRigaOrdine riga ")
				.append(" WHERE riga.dataCancellazione IS NULL ");

		composeQueryRigheDaEvadere(params, jpql);

		composeQueryRicercaOrdiniDaEvadere(annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa,
				dataEmissioneA, tipoOrdineId, lottoAnno, lottoNumero, tipoProceduraId,
				numeroProcedura, strutturaEmittenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, provvedimentoTipo,impegnoId, subimpegnoId, oggettoSpesaId, cpvId, checkVisibilitaDocumentale,
				cfUtente, utenteId, settoreId, params, jpql);

		final TypedQuery<CpassTOrdRigaOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();

	}
	private void composeQueryRicercaOrdiniDaEvadere(Integer annoOrdineDa, Integer numeroOrdineDa, Integer annoOrdineA,
			Integer numeroOrdineA, Date dataEmissioneDa, Date dataEmissioneA, Integer tipoOrdineId,Integer lottoAnno, Integer lottoNumero,
			Integer tipoProceduraId, String numeroProcedura, UUID strutturaEmittenteId, UUID strutturaDestinatarioId,
			UUID fornitoreId, Integer provvedimentoAnno, String provvedimentoNumero, String provvedimentoTipo ,UUID impegnoId, UUID subimpegnoId,
			List<Integer> oggettoSpesaId, Integer cpvId, boolean checkVisibilitaDocumentale, String cfUtente, UUID utenteId,
			UUID settoreId, Map<String, Object> params, StringBuilder jpql) {

		/*
		if(annoOrdineDa != null) {
			jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno >= :annoOrdineDa ");
			params.put("annoOrdineDa", annoOrdineDa);
		}
		if (annoOrdineA != null) {
			jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno <= :annoOrdineA ");
			params.put("annoOrdineA", annoOrdineA);
		}
		if (numeroOrdineDa != null) {
			jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineNumero >= :numeroOrdineDa ");
			params.put("numeroOrdineDa", numeroOrdineDa);
		}
		if (numeroOrdineA != null) {
			jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineNumero <= :numeroOrdineA ");
			params.put("numeroOrdineA", numeroOrdineA);
		}
		**/
		if(annoOrdineDa!=null && annoOrdineA!=null && annoOrdineDa<annoOrdineA) {

			jpql.append(" AND (");
			for(int i=annoOrdineDa; i<=annoOrdineA; i++) {
				if(i==annoOrdineDa) {
					if(numeroOrdineDa!= null) {
						jpql.append("( riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno = :annoOrdine"+i+" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineNumero >= :numeroOrdineDa )");
						params.put("numeroOrdineDa", numeroOrdineDa);
					}else {
						jpql.append(" riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno = :annoOrdine"+i);
					}
					params.put("annoOrdine"+i, i);
					jpql.append(" OR ");
				}

				if(i<annoOrdineA && i>annoOrdineDa) {
					jpql.append(" riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno = :annoOrdine"+i);
					jpql.append(" OR ");
					params.put("annoOrdine"+i, i);
				}

				if(i==annoOrdineA) {
					if(numeroOrdineA!= null) {
						jpql.append("( riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno = :annoOrdine"+i+" and riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineNumero <= :numeroOrdineA )");
						params.put("numeroOrdineA", numeroOrdineA);
					}else {
						jpql.append(" riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno = :annoOrdine"+i);
					}
					params.put("annoOrdine"+i, i);
				}


			}
			jpql.append(")");

		}else {
			if(annoOrdineDa != null && numeroOrdineDa == null) {
				jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno >= :annoOrdineDa ");
				params.put("annoOrdineDa", annoOrdineDa);
			}
			if (annoOrdineDa == null && numeroOrdineDa != null) {
				jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineNumero >= :numeroOrdineDa ");
				params.put("numeroOrdineDa", numeroOrdineDa);
			}
			if (annoOrdineDa != null && numeroOrdineDa != null) {
				jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno >= :annoOrdineDa ");
				jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineNumero >= :numeroOrdineDa ");
				params.put("annoOrdineDa", annoOrdineDa);
				params.put("numeroOrdineDa", numeroOrdineDa);
			}

			if (annoOrdineA != null && numeroOrdineA == null) {
				jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno <= :annoOrdineA ");
				params.put("annoOrdineA", annoOrdineA);
			}

			if (annoOrdineA == null && numeroOrdineA != null) {
				jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineNumero <= :numeroOrdineA ");
				params.put("numeroOrdineA", numeroOrdineA);
			}
			if (annoOrdineA != null && numeroOrdineA != null) {
				jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineAnno <= :annoOrdineA ");
				jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.ordineNumero <= :numeroOrdineA ");
				params.put("annoOrdineA", annoOrdineA);
				params.put("numeroOrdineA", numeroOrdineA);
			}
		}

		if (dataEmissioneDa != null) {
			jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.dataEmissione >= :dataEmissioneDa ");
			params.put("dataEmissioneDa", dataEmissioneDa);
		}
		if (dataEmissioneA != null) {
			jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.dataEmissione <= :dataEmissioneA ");
			params.put("dataEmissioneA", dataEmissioneA);
		}

		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassDOrdTipoOrdine.tipoOrdineId", "tipoOrdineId", tipoOrdineId);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.lottoAnno", "lottoAnno", lottoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.lottonumero", "lottoNumero", lottoNumero);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassDOrdTipoProcedura.tipoProceduraId", "tipoProceduraId", tipoProceduraId);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.numeroProcedura", "numeroProcedura", numeroProcedura);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassTSettore.settoreId", "strutturaEmittenteId", strutturaEmittenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassTFornitore.fornitoreId", "fornitoreId", fornitoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.provvedimentoAnno", "provvedimentoAnno", provvedimentoAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.provvedimentoNumero", "provvedimentoNumero", provvedimentoNumero);
		JpaQueryHelper.andFieldEquals(jpql, params, "riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.provvedimentoTipo", "provvedimentoTipo", provvedimentoTipo);

		if (strutturaDestinatarioId != null) {
			jpql.append(" AND riga.cpassTOrdDestinatario.cpassTSettore.settoreId = :destinatarioId ");
			params.put("destinatarioId", strutturaDestinatarioId);
		}
		if (impegnoId != null ) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdImpegnoOrdine ctoi ")
			.append(" WHERE ctoi.cpassTOrdRigaOrdine = riga and ctoi.cpassTImpegno.impegnoId = :impegnoId) ");
			params.put("impegnoId", impegnoId);
		}
		if (subimpegnoId != null ) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdImpegnoOrdine ctoi, CpassTOrdSubimpegnoOrdine ctos ")
			.append("  WHERE ctoi.cpassTOrdRigaOrdine = riga ")
			.append("  and ctos.cpassTOrdImpegnoOrdine = ctoi and ctos.cpassTSubimpegno.subimpegnoId = :subimpegnoId) ");
			params.put("subimpegnoId", subimpegnoId);
		}
		if (oggettoSpesaId != null && oggettoSpesaId.size() > 0) {
			jpql.append(" AND riga.cpassDOggettiSpesa.oggettiSpesaId in (:oggettoSpesaId) ");
			params.put("oggettoSpesaId", oggettoSpesaId);
		}
		else if (cpvId != null) {
			jpql.append(" AND riga.cpassDOggettiSpesa.cpassDCpv.cpvId = :cpvId ");
			params.put("cpvId", cpvId);
		}

		if (checkVisibilitaDocumentale && settoreId != null) {

			final Date now = new Date();

			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ")
			//issue-288
			//.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))")
			.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))")
			.append(" AND rrus.cpassDRuolo.ruoloCodice = :ruoloAdmin) ");
			jpql.append(" OR ( (riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassDStato.statoCodice = :statoCodice AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			jpql.append(" OR (riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassDStato.statoCodice != :statoCodice ");
			//			15.09.2020 Il controllo è da farsi sul settore non sull'ente del settore
			//			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus WHERE crus.cpassTSettore.settoreId = riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassTSettore.settoreId AND crus.cpassTUtente.utenteId = :utenteId ");
			//			jpql.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.dataEmissione <= crus.dataValiditaFine )))))) ");

			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus, CpassRRuoloUtenteSettore crrus , CpassRRuoloModulo crrm ")
			.append( " WHERE crus.cpassTSettore.settoreId = riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassTSettore.settoreId AND crus.cpassTUtente.utenteId = :utenteId ")
			.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.dataEmissione <= crus.dataValiditaFine )) ")
			.append(" AND crrus.cpassRUtenteSettore.id = crus.id  ")
			.append(" AND crrus.cpassDRuolo.id = crrm.cpassDRuolo.id  ")
			.append(" AND crrm.cpassDModulo.moduloCodice =  :moduloCodice ");

			//commentato per task-353
			//JpaQueryHelper.andCheckDateValidita(jpql, params, "crrus", now);// check date CpassRRuoloUtenteSettore

			jpql.append(" )))) ");

			params.put("settoreId", settoreId);
			params.put("utenteId", utenteId);
			params.put("now", now);

			params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());
			params.put("statoCodice", StatoOrdineEnum.BOZZA.getCostante());

			params.put("cfUtenteLoggato", cfUtente);
			params.put("moduloCodice", ConstantsDecodifiche.ModuloEnum.ORD.getCodice());
		}
	}

	private void composeQueryRigheDaEvadere(
			Map<String, Object> params, StringBuilder jpql) {

		final Integer annoCorrente = Calendar.getInstance().get(Calendar.YEAR);

		jpql.append(" AND riga.cpassDStato.statoCodice in (:daEvadere, : evasaParzialmente) ");
		jpql.append(" AND riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassDStato.statoCodice = :statoCodiceAut ");
		jpql.append(" AND ( (riga.cpassTOrdDestinatario.cpassDOrdStatoNso.statoNsoId IS NOT NULL AND EXISTS (FROM CpassDOrdStatoNso cdodn WHERE riga.cpassTOrdDestinatario.cpassDOrdStatoNso = cdodn AND cdodn.statoNsoCodice = :statoNsoCodice) )")
		.append(" OR riga.cpassTOrdDestinatario.cpassTOrdTestataOrdine.cpassDOrdTipoOrdine.flagTrasmNso = false ")
		.append(" ) ");
		jpql.append(" AND NOT EXISTS ( FROM CpassTOrdImpegnoOrdine ctoi ")
		.append(" WHERE ctoi.cpassTOrdRigaOrdine = riga and ctoi.impegnoAnnoEsercizio > :annoCorrente ) ");

		params.put("daEvadere", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_DA_EVADERE.getCostante());
		params.put("evasaParzialmente", ConstantsCPassStato.StatoOrdineEvasioneEnum.RIGA_ORDINE_EVASA_PARZIALMENTE.getCostante());
		params.put("statoCodiceAut", StatoOrdineEnum.AUTORIZZATO.getCostante());
		params.put("statoNsoCodice", "OK");// valutare se usare enum
		params.put("annoCorrente", annoCorrente);

	}

	@Override
	public void deleteFromTestataordine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("delete from cpass_t_ord_riga_ordine where cpass_t_ord_riga_ordine.riga_ordine_id in (select distinct riga_ordine_id from cpass_v_ordine where cpass_v_ordine.testata_ordine_id = :testataOrdineId  )");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public void updateFromTestataordine(UUID testataOrdineId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("update cpass_t_ord_riga_ordine set stato_id = 40 where riga_ordine_id in (select distinct riga_ordine_id from cpass_v_ordine where testata_ordine_id = :testataOrdineId)");
		final Map<String, Object> params = new HashMap<>();
		params.put("testataOrdineId", testataOrdineId);
		final Query query = composeNativeQuery(sb, params);
		query.executeUpdate();
	}

}
