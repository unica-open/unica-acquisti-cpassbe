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
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEvasioneEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
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

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassTOrdTestataEvasione tord  WHERE tord.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.evasioneAnno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.evasioneNumero", "numero", numero);
		JpaQueryHelper.andFieldEquals(jpql, params, "tord.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTOrdTestataEvasione> query = composeTypedQuery(jpql, params);

		final List<CpassTOrdTestataEvasione> results = query.getResultList();

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
			String provvedimentoTipo,
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
			UUID settoreId,
			UUID enteId){

		final Map<String, Object> params = new HashMap<>();
		final Date now = new Date();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataEvasione tev ")
				.append(" WHERE (tev.dataCancellazione IS NULL OR tev.dataCancellazione >=:now) ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tev.cpassTSettore.cpassTEnte.enteId", "enteId", enteId);
		params.put("now", now);

		composeQueryRicercaEvasioni(
				annoEvasioneDa, numeroEvasioneDa, annoEvasioneA, numeroEvasioneA, dataInserimentoDa, dataInserimentoA,
				annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa, dataEmissioneA,
				tipoEvasioneId, statoEvasioneId, strutturaCompetenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, provvedimentoTipo, impegnoId, subimpegnoId, oggettoSpesaId, checkVisibilitaDocumentale,
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
			String provvedimentoTipo,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId){

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataEvasione tev ")
				.append(" WHERE tev.dataCancellazione IS NULL ");

		composeQueryRicercaEvasioni(
				annoEvasioneDa, numeroEvasioneDa, annoEvasioneA, numeroEvasioneA, dataInserimentoDa, dataInserimentoA,
				annoOrdineDa, numeroOrdineDa, annoOrdineA, numeroOrdineA, dataEmissioneDa, dataEmissioneA,
				tipoEvasioneId, statoEvasioneId, strutturaCompetenteId, strutturaDestinatarioId, fornitoreId, provvedimentoAnno,
				provvedimentoNumero, provvedimentoTipo, impegnoId, subimpegnoId, oggettoSpesaId, false,
				null, null, null, params, jpql);

		final Query qn = composeQuery(getCountQuery(jpql.toString()), params);
		final long count = ((Number) qn.getSingleResult()).longValue();
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
			String provvedimentoTipo,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId,
			boolean checkVisibilitaDocumentale, String cfUtente, UUID utenteId,
			UUID settoreId, Map<String, Object> params, StringBuilder jpql) {

		if(annoEvasioneDa!=null && annoEvasioneA!=null && annoEvasioneDa<annoEvasioneA) {

			jpql.append(" AND (");
			for(int i=annoEvasioneDa; i<=annoEvasioneA; i++) {
				if(i==annoEvasioneDa) {
					if(numeroEvasioneDa!= null) {
						jpql.append("( tev.evasioneAnno = :annoEvasione"+i+" AND tev.evasioneNumero >= :numeroEvasioneDa )");
						params.put("numeroEvasioneDa", numeroEvasioneDa);
					}else {
						jpql.append(" tev.evasioneAnno = :annoEvasione"+i);
					}
					params.put("annoEvasione"+i, i);
					jpql.append(" OR ");
				}

				if(i<annoEvasioneA && i>annoEvasioneDa) {
					jpql.append(" tev.evasioneAnno = :annoEvasione"+i);
					jpql.append(" OR ");
					params.put("annoEvasione"+i, i);
				}

				if(i==annoEvasioneA) {
					if(numeroEvasioneA!= null) {
						jpql.append("( tev.evasioneAnno = :annoEvasione"+i+" and tev.evasioneNumero <= :numeroEvasioneA )");
						params.put("numeroEvasioneA", numeroEvasioneA);
					}else {
						jpql.append(" tev.evasioneAnno = :annoEvasione"+i);
					}
					params.put("annoEvasione"+i, i);
				}


			}
			jpql.append(")");

		}else {
			if(annoEvasioneDa != null && numeroEvasioneDa == null) {
				jpql.append(" AND tev.evasioneAnno >= :annoEvasioneDa ");
				params.put("annoEvasioneDa", annoEvasioneDa);
			}
			if (annoEvasioneDa == null && numeroEvasioneDa != null) {
				jpql.append(" AND tev.evasioneNumero >= :numeroEvasioneDa ");
				params.put("numeroEvasioneDa", numeroEvasioneDa);
			}
			if (annoEvasioneDa != null && numeroEvasioneDa != null) {
				jpql.append(" AND tev.evasioneAnno >= :annoEvasioneDa ");
				jpql.append(" AND tev.evasioneNumero >= :numeroEvasioneDa ");
				params.put("annoEvasioneDa", annoEvasioneDa);
				params.put("numeroEvasioneDa", numeroEvasioneDa);
			}

			if (annoEvasioneA != null && numeroEvasioneA == null) {
				jpql.append(" AND tev.evasioneAnno <= :annoEvasioneA ");
				params.put("annoEvasioneA", annoEvasioneA);
			}

			if (annoEvasioneA == null && numeroEvasioneA != null) {
				jpql.append(" AND tev.evasioneNumero <= :numeroEvasioneA ");
				params.put("numeroEvasioneA", numeroEvasioneA);
			}
			if (annoEvasioneA != null && numeroEvasioneA != null) {
				jpql.append(" AND tev.evasioneAnno <= :annoEvasioneA ");
				jpql.append(" AND tev.evasioneNumero <= :numeroEvasioneA ");
				params.put("annoEvasioneA", annoEvasioneA);
				params.put("numeroEvasioneA", numeroEvasioneA);
			}
		}

		if (dataInserimentoDa != null) {
			jpql.append(" AND date_trunc('day', tev.dataInserimento) >= :dataInserimentoDa ");
			params.put("dataInserimentoDa", dataInserimentoDa);
		}
		if (dataInserimentoA != null) {
			jpql.append(" AND date_trunc('day', tev.dataInserimento) <= :dataInserimentoA ");
			params.put("dataInserimentoA", dataInserimentoA);
		}


		if (annoOrdineDa != null || annoOrdineA != null || numeroOrdineDa != null || numeroOrdineA != null || dataEmissioneDa != null || dataEmissioneA != null || provvedimentoAnno != null || provvedimentoNumero != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioEvasione ctoe WHERE ctoe.cpassTOrdTestataEvasione = tev ");

			//////////////////////////////////////////////////////////////////////////////////////////////////



			if(annoOrdineDa!=null && annoOrdineA!=null && annoOrdineDa<annoOrdineA) {

				jpql.append(" AND (");
				for(int i=annoOrdineDa; i<=annoOrdineA; i++) {

					if(i==annoOrdineDa) {
						if(numeroOrdineDa!= null) {
							//jpql.append("( tev.evasioneAnno = :annoEvasione"+i+" AND tev.evasioneNumero >= :numeroEvasioneDa )");
							jpql.append("( ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineAnno >= :annoOrdine"+i +" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineNumero >= :numeroOrdineDa )" );
							params.put("numeroOrdineDa", numeroOrdineDa);
						}else {
							jpql.append(" ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineAnno >= :annoOrdine"+i);
						}
						params.put("annoOrdine"+i, i);
						jpql.append(" OR ");
					}

					if(i<annoOrdineA && i>annoOrdineDa) {
						jpql.append(" ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineAnno >= :annoOrdine"+i);
						jpql.append(" OR ");
						params.put("annoOrdine"+i, i);
					}

					if(i==annoOrdineA) {
						if(numeroOrdineA!= null) {
							jpql.append("( ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineAnno >= :annoOrdine"+i +" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineNumero <= :numeroOrdineA )" );
							params.put("numeroOrdineA", numeroOrdineA);
						}else {
							jpql.append(" ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.ordineAnno <= :annoOrdine"+i);
						}
						params.put("annoOrdine"+i, i);
					}
				}
				jpql.append(")");

			}else {

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
			}



			///////////////////////////////////////////////////////////////////////////////
			if (dataEmissioneDa != null) {
				jpql.append(" AND date_trunc('day', ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.dataEmissione) >= :dataEmissioneDa ");
				params.put("dataEmissioneDa", dataEmissioneDa);
			}
			if (dataEmissioneA != null) {
				jpql.append(" AND date_trunc('day', ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.dataEmissione) <= :dataEmissioneA ");
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
			if (provvedimentoTipo != null) {
				jpql.append(" AND ctoe.cpassTOrdDestinatarioOrdine.cpassTOrdTestataOrdine.provvedimentoTipo = :provvedimentoTipo ");
				params.put("provvedimentoTipo", provvedimentoTipo);
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
			.append("  and ctes.cpassTOrdImpegnoEvasione = ctei and ctes.cpassTSubimpegno.subimpegnoId = :subimpegnoId ) ");
			params.put("subimpegnoId", subimpegnoId);
		}
		if (oggettoSpesaId != null) {
			jpql.append(" AND EXISTS ( FROM CpassTOrdDestinatarioEvasione ctode, CpassTOrdRigaEvasione ctore WHERE ctode.cpassTOrdTestataEvasione = tev and ctore.cpassTOrdDestinatarioEvasione = ctode and ctore.cpassDOggettiSpesa.oggettiSpesaId = :oggettoSpesaId ) ");
			params.put("oggettoSpesaId", oggettoSpesaId);
		}

		if (checkVisibilitaDocumentale && settoreId != null) {

			final Date now = new Date();

			jpql.append(" AND ( EXISTS ( FROM CpassRRuoloUtenteSettore rrus WHERE (rrus.cpassRUtenteSettore.cpassTSettore.settoreId = :settoreId AND rrus.cpassRUtenteSettore.cpassTUtente.utenteId = :utenteId) ")
			//issue-228
			//.append(" AND (rrus.dataValiditaInizio IS NULL OR (rrus.dataValiditaInizio IS NOT NULL and rrus.dataValiditaInizio <= :now ))")
			.append(" AND (rrus.dataValiditaFine   IS NULL OR (rrus.dataValiditaFine   IS NOT NULL and rrus.dataValiditaFine   >= :now ))")
			.append(" AND rrus.cpassDRuolo.ruoloCodice in (:ruoloAdmin, :ruoloAdminEnte)) ");
			jpql.append(" OR ( (tev.cpassDStato.statoCodice = :statoCodice AND tev.cpassTUtente.utenteCodiceFiscale = :cfUtenteLoggato) ");
			jpql.append(" OR (tev.cpassDStato.statoCodice != :statoCodice ");

			jpql.append(" AND EXISTS ( FROM CpassRUtenteSettore crus, CpassRRuoloUtenteSettore crrus , CpassRRuoloModulo crrm ")
			.append( " WHERE crus.cpassTSettore.settoreId = tev.cpassTSettore.settoreId AND crus.cpassTUtente.utenteId = :utenteId ")
			.append(" AND (crus.dataValiditaFine IS NULL OR (crus.dataValiditaFine IS NOT NULL and tev.dataInserimento <= crus.dataValiditaFine )) ")
			.append(" AND crrus.cpassRUtenteSettore.id = crus.id  ")
			.append(" AND crrus.cpassDRuolo.id = crrm.cpassDRuolo.id  ")
			.append(" AND crrm.cpassDModulo.moduloCodice =  :moduloCodice ");

			jpql.append(" )))) ");
			params.put("settoreId", settoreId);
			params.put("utenteId", utenteId);
			params.put("now", now);

			params.put("ruoloAdmin", CpassRuoloEnum.ADMIN.getCodice());
			params.put("ruoloAdminEnte", CpassRuoloEnum.ADMIN_ENTE.getCodice());
			params.put("statoCodice", StatoEvasioneEnum.BOZZA.getCostante());

			params.put("cfUtenteLoggato", cfUtente);
			params.put("moduloCodice", ConstantsDecodifiche.ModuloEnum.ORD.getCodice());

		}
		/*
		log.error("query ricerca evasione", "*********************************");
		log.error("query ricerca evasione", "*********************************");
		log.error("query ricerca evasione", jpql.toString());
		log.error("query ricerca evasione", "*********************************");
		log.error("query ricerca evasione", "*********************************");
		 */
	}

	@Override
	public List<CpassTOrdTestataEvasione> findTestateEvasioniByFattura(Integer anno, String numero, String tipo, String codice) {

		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTOrdTestataEvasione tord ")
				.append(" WHERE tord.dataCancellazione IS NULL ")
				.append(" AND  tord.fatturaAnno = :anno ")
				.append(" AND  tord.fatturaNumero = :numero ")
				.append(" AND  tord.fatturaTipo = :tipo ")
				.append(" AND  tord.fatturaCodiceFornitore = :codice ")
				.append(" AND  tord.cpassDStato.statoCodice <> :statoCodice ");

		params.put("anno", anno);
		params.put("numero", numero);
		params.put("tipo", tipo);
		params.put("codice", codice);
		params.put("statoCodice", StatoEvasioneEnum.ANNULLATA.getCostante());

		final TypedQuery<CpassTOrdTestataEvasione> query = composeTypedQuery(jpql, params);
		final List<CpassTOrdTestataEvasione> results = query.getResultList();
		return results;
	}

	@Override
	public Optional<CpassTOrdTestataEvasione> getTestataEvasioneByDestinatario(UUID idDest) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT dest.cpassTOrdTestataEvasione FROM CpassTOrdDestinatarioEvasione dest ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "dest.destinatarioEvasioneId", "idDest", idDest);
		final TypedQuery<CpassTOrdTestataEvasione> query = composeTypedQuery(jpql, params);
		final Optional<CpassTOrdTestataEvasione> results = query.getResultStream().findFirst();
		return results;
	}
}
