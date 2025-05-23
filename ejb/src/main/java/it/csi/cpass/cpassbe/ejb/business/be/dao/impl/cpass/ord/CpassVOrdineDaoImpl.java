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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;

/**
 * Data Access Object implementor for the entity CpassTImpegnoDao
 */
@ApplicationScoped
public class CpassVOrdineDaoImpl extends BaseEntityDaoImpl<Long, CpassVOrdine> implements CpassVOrdineDao {

	@Override
	public List<CpassVOrdine> getListVOrdineByStati(List<Integer> statiIds) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("SELECT DISTINCT vo FROM CpassVOrdine vo WHERE vo.statoId IN :statiIds ");
		params.put("statiIds", statiIds);
		final TypedQuery<CpassVOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}


	@Override
	public List<CpassVOrdine> getListImpegniByOrdineId(UUID testataOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassVOrdine vo WHERE vo.testataOrdineId =:testataOrdineId ");
		jpql.append(" ORDER BY ");
		jpql.append("  vo.ordineAnno");
		jpql.append(" ,vo.ordineNumero");
		params.put("testataOrdineId", testataOrdineId);
		final TypedQuery<CpassVOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassVOrdine> getListImpegniOrdinatiImpByOrdineId(UUID testataOrdineId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassVOrdine vo WHERE vo.testataOrdineId =:testataOrdineId and vo.impegnoId != null");
		// non toccare l'ordinamento serve per aggregare gli importi
		jpql.append(" ORDER BY vo.impegnoAnno, vo.impegnoNumero, vo.subimpegnoAnno, vo.subimpegnoNumero");
		params.put("testataOrdineId", testataOrdineId);
		final TypedQuery<CpassVOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassVOrdine> getListImpegniRiepilogoByOrdineId(UUID idTestataOrdine) {
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder sql = new StringBuilder().append("SELECT ");
		//jpql.append(" testata_ordine_id, ");
		sql.append(" impegno_anno_esercizio,");
		sql.append(" impegno_anno, ");
		sql.append(" impegno_numero, ");
		sql.append(" numero_capitolo, ");
		sql.append(" numero_articolo, ");
		sql.append(" subimpegno_anno, ");
		sql.append(" subimpegno_numero, ");
		sql.append(" sum(COALESCE (importo_impegno,0)) importo_impegno,  ");
		sql.append(" sum(COALESCE (subimpegno_importo,0)) subimpegno_importo " );
		sql.append(" from cpass_v_ordine ");
		sql.append(" where ");
		sql.append(" cpass_v_ordine.testata_ordine_id =:idTestataOrdine");
		sql.append(" group by  ");
		//jpql.append("    testata_ordine_id, ");
		sql.append("    impegno_anno_esercizio, ");
		sql.append("    impegno_anno, ");
		sql.append("    impegno_numero,");
		sql.append("    numero_capitolo, ");
		sql.append("    numero_articolo, ");
		sql.append("    subimpegno_anno, ");
		sql.append("    subimpegno_numero ");
		sql.append(" order by ");
		sql.append("    impegno_anno, ");
		sql.append("    impegno_numero, ");
		sql.append("    subimpegno_anno, ");
		sql.append("    subimpegno_numero ");

		param.put("idTestataOrdine", idTestataOrdine);
		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> list = query.getResultList();
		final List<CpassVOrdine> listaRis = new ArrayList<>();
		for(final Object[] obj : list) {
			final CpassVOrdine el = new CpassVOrdine();
			el.setTestataOrdineId(idTestataOrdine);
			el.setImpegnoAnnoEsercizio((Integer)obj[0]);
			el.setImpegnoAnno((Integer)obj[1]);
			el.setImpegnoNumero((Integer)obj[2]);
			el.setNumeroCapitolo((Integer)obj[3]);
			el.setNumeroArticolo((Integer)obj[4]);
			el.setSubimpegnoAnno((Integer)obj[5]);
			el.setSubimpegnoNumero((Integer)obj[6]);
			el.setImportoImpegno((BigDecimal)obj[7]);
			el.setSubimpegnoImporto((BigDecimal)obj[8]);
			listaRis.add(el);
		}
		return listaRis;//findOrdineByIds(listaId);
	}

	@Override
	public BigDecimal getImpegnatoByOrdineId(UUID testataOrdineId, Integer impegnoAnno,Integer  impegnoNumero) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.importoImpegno,0)) ");
		jpql.append(" FROM CpassVOrdine ie ");
		jpql.append(" WHERE ie.testataOrdineId = :testataOrdineId ");
		if(impegnoAnno!=null) {
			jpql.append(" AND ie.impegnoAnno = :impegnoAnno ");
			jpql.append(" AND ie.impegnoNumero = :impegnoNumero ");
		}





		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("testataOrdineId", testataOrdineId);
		if(impegnoAnno!=null) {
			q.setParameter("impegnoAnno", impegnoAnno);
			q.setParameter("impegnoNumero", impegnoNumero);
		}
		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getsubImpegnatoByOrdineId(UUID testataOrdineId, Integer subimpegnoAnno,Integer  subimpegnoNumero) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.subimpegnoImporto,0)) ");
		jpql.append(" FROM CpassVOrdine ie ");
		jpql.append(" WHERE ie.testataOrdineId = :testataOrdineId ");
		if(subimpegnoAnno!=null) {
			jpql.append(" AND ie.subimpegnoAnno = :subimpegnoAnno ");
			jpql.append(" AND ie.subimpegnoNumero = :subimpegnoNumero ");
		}

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("testataOrdineId", testataOrdineId);
		if(subimpegnoAnno!=null) {
			q.setParameter("subimpegnoAnno", subimpegnoAnno);
			q.setParameter("subimpegnoNumero", subimpegnoNumero);
		}



		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	/*
	 *
	 */

	@Override
	public BigDecimal getEvasoSubByOrdineId(UUID testataOrdineId,Integer impegnoAnnoEsercizio, Integer impegnoAnno, Integer impegnoNumero,Integer annoSubImpegno, Integer numeroSubImpegno,UUID enteId,Integer statoId) {
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder sql = new StringBuilder().append("SELECT ");
		sql.append(" SUM(cpass_t_ord_subimpegno_evasione.importo_ripartito + cpass_t_ord_subimpegno_evasione.importo_Sospeso) ");
		sql.append(" FROM cpass_t_ord_testata_ordine ");
		sql.append(" JOIN cpass_t_ord_destinatario_ordine ON cpass_t_ord_testata_ordine.testata_ordine_id    = cpass_t_ord_destinatario_ordine.testata_ordine_id ");
		sql.append(" JOIN cpass_t_ord_riga_ordine         ON cpass_t_ord_destinatario_ordine.destinatario_id = cpass_t_ord_riga_ordine.destinatario_id ");
		sql.append(" JOIN cpass_t_ord_riga_evasione       ON cpass_t_ord_riga_ordine.riga_ordine_id     = cpass_t_ord_riga_evasione.riga_ordine_id ");
		sql.append(" JOIN cpass_t_ord_impegno_evasione    ON cpass_t_ord_riga_evasione.riga_evasione_id = cpass_t_ord_impegno_evasione.riga_evasione_id ");
		sql.append(" JOIN cpass_t_ord_subimpegno_evasione ON cpass_t_ord_impegno_evasione.impegno_evasione_id = cpass_t_ord_subimpegno_evasione.impegno_evasione_id ");
		sql.append(" JOIN cpass_d_stato ON cpass_t_ord_testata_ordine.stato_id = cpass_d_stato.stato_id ");
		sql.append(" WHERE ");
		sql.append(" cpass_t_ord_testata_ordine.testata_ordine_id = :testataOrdineId  ");
		sql.append(" AND cpass_t_ord_subimpegno_evasione.impegno_Anno_Esercizio >= :impegnoAnnoEsercizio  ");
		sql.append(" AND cpass_t_ord_subimpegno_evasione.impegno_Anno            = :impegnoAnno  ");
		sql.append(" AND cpass_t_ord_subimpegno_evasione.impegno_Numero          = :impegnoNumero  ");
		sql.append(" AND cpass_t_ord_subimpegno_evasione.subimpegno_Anno         = :annoSubImpegno  ");
		sql.append(" AND cpass_t_ord_subimpegno_evasione.subimpegno_Numero       = :numeroSubImpegno  ");
		if(statoId!=null) {
			sql.append(" AND cpass_t_ord_riga_evasione.stato_id = :statoId ");
			param.put("statoId", statoId);
		}

		param.put("testataOrdineId", testataOrdineId);
		param.put("impegnoAnnoEsercizio", impegnoAnnoEsercizio);
		param.put("impegnoAnno", impegnoAnno);
		param.put("impegnoNumero", impegnoNumero);
		param.put("annoSubImpegno", annoSubImpegno);
		param.put("numeroSubImpegno", numeroSubImpegno);

		final Query query = composeNativeQuery(sql.toString(), param);

		final Object result = query.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);

	}


	@Override
	public BigDecimal getEvasoByOrdineId(UUID testataOrdineId, Integer impegnoAnnoEsercizio, Integer impegnoAnno,Integer impegnoNumero, UUID enteId, Integer statoId) {
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder sql = new StringBuilder().append(" SELECT ");
		sql.append(" SUM(cpass_t_ord_impegno_evasione.importo_ripartito + cpass_t_ord_impegno_evasione.importo_Sospeso) ");
		sql.append(" FROM cpass_t_ord_testata_ordine ");
		sql.append(" JOIN cpass_t_ord_destinatario_ordine ON cpass_t_ord_testata_ordine.testata_ordine_id    = cpass_t_ord_destinatario_ordine.testata_ordine_id ");
		sql.append(" JOIN cpass_t_ord_riga_ordine         ON cpass_t_ord_destinatario_ordine.destinatario_id = cpass_t_ord_riga_ordine.destinatario_id ");
		sql.append(" JOIN cpass_t_ord_riga_evasione       ON cpass_t_ord_riga_ordine.riga_ordine_id     = cpass_t_ord_riga_evasione.riga_ordine_id ");
		sql.append(" JOIN cpass_t_ord_impegno_evasione    ON cpass_t_ord_riga_evasione.riga_evasione_id = cpass_t_ord_impegno_evasione.riga_evasione_id ");
		sql.append(" JOIN cpass_d_stato ON cpass_t_ord_testata_ordine.stato_id = cpass_d_stato.stato_id ");
		sql.append(" WHERE ");
		sql.append(" cpass_t_ord_testata_ordine.testata_ordine_id             = :testataOrdineId  ");
		sql.append(" AND cpass_t_ord_impegno_evasione.impegno_Anno_Esercizio >= :impegnoAnnoEsercizio  ");
		sql.append(" AND cpass_t_ord_impegno_evasione.impegno_Anno            = :impegnoAnno  ");
		sql.append(" AND cpass_t_ord_impegno_evasione.impegno_Numero          = :impegnoNumero  ");

		if(statoId!=null) {
			sql.append(" AND cpass_t_ord_riga_evasione.stato_id = :statoId ");
			param.put("statoId", statoId);
		}


		param.put("testataOrdineId", testataOrdineId);
		param.put("impegnoAnnoEsercizio", impegnoAnnoEsercizio);
		param.put("impegnoAnno", impegnoAnno);
		param.put("impegnoNumero", impegnoNumero);

		final Query query = composeNativeQuery(sql.toString(), param);

		final Object result = query.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}


	@Override
	public BigDecimal getEvasoByOrdineId(UUID testataOrdineId,Integer statoId) {
		final Map<String, Object> param = new HashMap<>();
		final StringBuilder sql = new StringBuilder().append("SELECT ");
		sql.append(" SUM (COALESCE (cpass_v_ordine_evasione.evasione_totale_con_iva,0)) ");
		sql.append(" FROM  ");
		sql.append(" cpass_v_ordine_evasione ");
		sql.append(" where  1 = 1");
		sql.append(" and cpass_v_ordine_evasione.testata_Ordine_Id = :testataOrdineId ");
		if(statoId!=null) {
			sql.append(" AND cpass_v_ordine_evasione.evasione_stato_id = :statoId ");
			param.put("statoId", statoId);
		}
		param.put("testataOrdineId", testataOrdineId);

		final Query query = composeNativeQuery(sql.toString(), param);
		final Object result = query.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}


	@Override
	public List<ConsultazioniOrdine> getOrdiniXConsultazioni(RicercaXConsultazioni filtro) {
		final List<ConsultazioniOrdine> listaRis = new ArrayList<>();
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder sql = new StringBuilder().append("SELECT DISTINCT ");
		//1 Ordine
		sql.append("   cast(cpass_v_ordine.testata_ordine_id as varchar) ");
		sql.append(" 	,cpass_v_ordine.ordine_anno ");
		sql.append(" 	,cpass_v_ordine.ordine_numero ");
		//2 Stato
		sql.append(" 	,cpass_d_stato.stato_descrizione ");
		//3 Fornitore
		sql.append(" 	,CPASS_T_FORNITORE.codice ");
		sql.append(" 	,CPASS_T_FORNITORE.ragione_sociale ");
		//4 Netto
		sql.append(" 	,cpass_v_ordine.totale_no_iva ");
		//5 Lordo
		sql.append(" 	,cpass_v_ordine.totale_con_iva ");
		//6 Impegnato ∑(CPASS_T_ORD_IMPEGNO_ORDINE.importo)
		//7 Evaso ∑(CPASS_T_ORD_RIGA_EVASIONE.importo_totale)
		//8 In contabilità ∑(CPASS_T_ORD_RIGA_EVASIONE.importo_totale)
		//9 Descrizione
		sql.append(" 	,cpass_v_ordine.descrizione_acquisto ");
		sql.append(" from  ");
		sql.append(" 	 cpass_v_ordine ");
		sql.append(" 	,cpass_d_stato ");
		sql.append(" 	,CPASS_T_FORNITORE ");
		sql.append(" where ");
		sql.append(" 	cpass_v_ordine.stato_id       			    = cpass_d_stato.stato_id ");
		sql.append(" 	AND cpass_v_ordine.fornitore_id   			= CPASS_T_FORNITORE.fornitore_id ");

		if(filtro.getNumeroProcedura() != null) {
			sql.append(" AND cpass_v_ordine.numero_Procedura = :numeroProcedura ");
			sql.append(" AND cpass_v_ordine.tipo_Procedura_Id = :tipoProceduraId ");
			param.put("numeroProcedura", filtro.getNumeroProcedura());
			param.put("tipoProceduraId", filtro.getTipoProcedura().getId());
		}
		if(filtro.getProvvedimento() != null && filtro.getProvvedimento().getAnno()!= null) {
			sql.append(" AND cpass_v_ordine.provvedimento_Anno   = :provvedimentoAnno ");
			sql.append(" AND cpass_v_ordine.provvedimento_Numero = :provvedimentoNumero ");
			sql.append(" AND cpass_v_ordine.provvedimento_Tipo   = :provvedimentoTipo ");
			sql.append(" AND cpass_v_ordine.ordine_Anno          = :annoEsercizio ");
			param.put("provvedimentoAnno", filtro.getProvvedimento().getAnno());
			param.put("provvedimentoNumero", filtro.getProvvedimento().getNumero());
			param.put("provvedimentoTipo", filtro.getProvvedimento().getProvvedimentoTipo().getCodice());
			param.put("annoEsercizio", filtro.getAnnoEsercizio());
		}
		sql.append(" ORDER BY  cpass_v_ordine.ordine_anno,cpass_v_ordine.ordine_numero");

		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> list = query.getResultList();
		for(final Object[] obj : list) {
			final ConsultazioniOrdine el = new ConsultazioniOrdine();
			//log.info("", obj[0].getClass());
			el.setId((String)obj[0]);
			el.setAnnoOrdine((Integer)obj[1]);
			el.setNumeroOrdine((Integer)obj[2]);
			el.setStatoDesc((String)obj[3]);
			el.setFornitoreCodice((String)obj[4]);
			el.setFornitoreRagioneSociale((String)obj[5]);
			el.setNetto((BigDecimal)obj[6]==null ? BigDecimal.ZERO : (BigDecimal)obj[6]);
			el.setLordo((BigDecimal)obj[7]==null ? BigDecimal.ZERO : (BigDecimal)obj[7]);
			el.setDescrizioneAcquisto((String)obj[8]);
			listaRis.add(el);
		}
		return listaRis;
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnno(Integer provvedimentoAnno
			, String provvedimentoNumero
			, String provvedimentoTipo
			, String settoreCodice
			, Integer annoEsercizio) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.totaleConIva,0)) ");
		jpql.append(" FROM CpassTOrdTestataOrdine ie ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" and ie.provvedimentoAnno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimentoNumero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimentoTipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimentoSettore = :settoreCodice ");
		jpql.append(" and ie.ordineAnno           = :annoEsercizio ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("provvedimentoAnno", provvedimentoAnno);
		q.setParameter("provvedimentoNumero", provvedimentoNumero);
		q.setParameter("provvedimentoTipo", provvedimentoTipo);
		q.setParameter("settoreCodice", settoreCodice);
		q.setParameter("annoEsercizio", annoEsercizio);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnnoSuImpegni(Integer provvedimentoAnno
			, String provvedimentoNumero
			, String provvedimentoTipo
			, String settoreCodice
			, Integer annoEsercizio) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.importoImpegno,0)) ");
		jpql.append(" FROM CpassVOrdine ie ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" and ie.provvedimentoAnno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimentoNumero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimentoTipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimentoSettore = :settoreCodice ");
		jpql.append(" and ie.ordineAnno           = :annoEsercizio ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("provvedimentoAnno", provvedimentoAnno);
		q.setParameter("provvedimentoNumero", provvedimentoNumero);
		q.setParameter("provvedimentoTipo", provvedimentoTipo);
		q.setParameter("settoreCodice", settoreCodice);
		q.setParameter("annoEsercizio", annoEsercizio);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnnoSuImpegniResidui(Integer provvedimentoAnno
			, String provvedimentoNumero
			, String provvedimentoTipo
			, String settoreCodice
			, Integer annoEsercizio) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.importoImpegno,0)) ");
		jpql.append(" FROM CpassVOrdine ie ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" and ie.provvedimentoAnno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimentoNumero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimentoTipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimentoSettore = :settoreCodice ");
		jpql.append(" and ie.ordineAnno           = :annoEsercizio ");
		jpql.append(" and ie.impegnoAnno          < :annoEsercizio ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("provvedimentoAnno", provvedimentoAnno);
		q.setParameter("provvedimentoNumero", provvedimentoNumero);
		q.setParameter("provvedimentoTipo", provvedimentoTipo);
		q.setParameter("settoreCodice", settoreCodice);
		q.setParameter("annoEsercizio", annoEsercizio);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnnoSuImpegniCompetenza(Integer provvedimentoAnno
			, String provvedimentoNumero
			, String provvedimentoTipo
			, String settoreCodice
			, Integer annoEsercizio) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.importoImpegno,0)) ");
		jpql.append(" FROM CpassVOrdine ie ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" and ie.provvedimentoAnno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimentoNumero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimentoTipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimentoSettore = :settoreCodice ");
		jpql.append(" and ie.ordineAnno           = :annoEsercizio ");
		jpql.append(" and ie.impegnoAnno          = :annoEsercizio ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("provvedimentoAnno", provvedimentoAnno);
		q.setParameter("provvedimentoNumero", provvedimentoNumero);
		q.setParameter("provvedimentoTipo", provvedimentoTipo);
		q.setParameter("settoreCodice", settoreCodice);
		q.setParameter("annoEsercizio", annoEsercizio);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnnoSuImpegniAnnoSuccessivo(Integer provvedimentoAnno
			, String provvedimentoNumero
			, String provvedimentoTipo
			, String settoreCodice
			, Integer annoEsercizio) {

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.importoImpegno,0)) ");
		jpql.append(" FROM CpassVOrdine ie ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" and ie.provvedimentoAnno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimentoNumero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimentoTipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimentoSettore = :settoreCodice ");
		jpql.append(" and ie.ordineAnno           = :annoEsercizio ");
		jpql.append(" and ie.impegnoAnno          = :annoEsercizioSucc ");
		final Integer annoEsercizioSucc = annoEsercizio +1;
		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("provvedimentoAnno", provvedimentoAnno);
		q.setParameter("provvedimentoNumero", provvedimentoNumero);
		q.setParameter("provvedimentoTipo", provvedimentoTipo);
		q.setParameter("settoreCodice", settoreCodice);
		q.setParameter("annoEsercizio", annoEsercizio);
		q.setParameter("annoEsercizioSucc", annoEsercizioSucc);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnniPrecedenti(Integer provvedimentoAnno, String provvedimentoNumero,String provvedimentoTipo, String settoreCodice, Integer annoEsercizio) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.totaleConIva,0)) ");
		jpql.append(" FROM CpassTOrdTestataOrdine ie ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" and ie.provvedimentoAnno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimentoNumero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimentoTipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimentoSettore = :settoreCodice ");
		jpql.append(" and ie.ordineAnno           < :annoEsercizio ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("provvedimentoAnno", provvedimentoAnno);
		q.setParameter("provvedimentoNumero", provvedimentoNumero);
		q.setParameter("provvedimentoTipo", provvedimentoTipo);
		q.setParameter("settoreCodice", settoreCodice);
		q.setParameter("annoEsercizio", annoEsercizio);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegni(Integer provvedimentoAnno,
			String provvedimentoNumero, String provvedimentoTipo, String settoreCodice, Integer annoEsercizio) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.importoImpegno,0)) ");
		jpql.append(" FROM CpassVOrdine ie ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" and ie.provvedimentoAnno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimentoNumero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimentoTipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimentoSettore = :settoreCodice ");
		jpql.append(" and ie.ordineAnno          < :annoEsercizio ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("provvedimentoAnno", provvedimentoAnno);
		q.setParameter("provvedimentoNumero", provvedimentoNumero);
		q.setParameter("provvedimentoTipo", provvedimentoTipo);
		q.setParameter("settoreCodice", settoreCodice);
		q.setParameter("annoEsercizio", annoEsercizio);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegniNonLiq(Integer provvedimentoAnno,String provvedimentoNumero, String provvedimentoTipo, String settoreCodice, Integer annoEsercizio) {
		BigDecimal ris = BigDecimal.ZERO;
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT distinct ie.impegno_anno, ie.impegno_numero, COALESCE (im.liq_Anno_Prec,0) ");
		jpql.append(" FROM cpass_v_ordine ie,cpass_T_impegno im ");
		jpql.append(" WHERE 1 = 1 ");

		// JOIN
		jpql.append(" and ie.impegno_Anno           = im.impegno_Anno ");
		jpql.append(" and ie.impegno_Numero         = im.impegno_Numero ");

		jpql.append(" and ie.provvedimento_Anno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimento_Numero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimento_Tipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimento_Settore = :settoreCodice ");
		jpql.append(" and ie.ordine_Anno           < :annoEsercizio ");
		jpql.append(" and ie.impegno_Anno          < :annoEsercizio ");
		jpql.append(" and im.impegno_Anno_Esercizio = :annoEsercizio ");

		param.put("provvedimentoAnno", provvedimentoAnno);
		param.put("provvedimentoNumero", provvedimentoNumero);
		param.put("provvedimentoTipo", provvedimentoTipo);
		param.put("settoreCodice", settoreCodice);
		param.put("annoEsercizio", annoEsercizio);
		final Query query = composeNativeQuery(jpql.toString(), param);

		final List<Object[]> list = query.getResultList();
		for(final Object[] obj : list) {
			ris = ris.add((BigDecimal)obj[2]);
		}

		return ris;
	}

	@Override
	public BigDecimal getOrdinatoComplessivoAnniPrecedentiSuImpegniAnnoEsercizio(Integer provvedimentoAnno,
			String provvedimentoNumero, String provvedimentoTipo, String settoreCodice, Integer annoEsercizio) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT SUM(COALESCE (ie.importoImpegno,0)) ");
		jpql.append(" FROM CpassVOrdine ie ");
		jpql.append(" WHERE 1 = 1 ");
		jpql.append(" and ie.provvedimentoAnno    = :provvedimentoAnno ");
		jpql.append(" and ie.provvedimentoNumero  = :provvedimentoNumero ");
		jpql.append(" and ie.provvedimentoTipo    = :provvedimentoTipo ");
		jpql.append(" and ie.provvedimentoSettore = :settoreCodice ");
		jpql.append(" and ie.ordineAnno          < :annoEsercizio ");
		jpql.append(" and ie.impegnoAnno          = :annoEsercizio ");

		final Query q = entityManager.createQuery(jpql.toString());
		q.setParameter("provvedimentoAnno", provvedimentoAnno);
		q.setParameter("provvedimentoNumero", provvedimentoNumero);
		q.setParameter("provvedimentoTipo", provvedimentoTipo);
		q.setParameter("settoreCodice", settoreCodice);
		q.setParameter("annoEsercizio", annoEsercizio);

		final Object result = q.getSingleResult();
		if (result != null) {
			return (BigDecimal) result;
		}
		return new BigDecimal(0);
	}





}
