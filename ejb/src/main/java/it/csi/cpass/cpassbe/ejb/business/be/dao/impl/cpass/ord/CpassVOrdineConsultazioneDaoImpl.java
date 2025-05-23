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

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassVOrdineConsultazioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniOrdine;

/**
 * Data Access Object implementor for the entity CpassTImpegnoDao
 */
@ApplicationScoped
public class CpassVOrdineConsultazioneDaoImpl extends BaseEntityDaoImpl<Long, CpassVOrdine> implements CpassVOrdineConsultazioneDao {
	@Override
	public List<ConsultazioniOrdine> getListOrdineByImpegni(Integer annoEsercizio, Integer annoImpegno, Integer numeroImpegno,Integer annoSubImpegno, Integer numeroSubImpegno) {
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
		sql.append(" 	cpass_v_ordine ");
		sql.append(" 	,cpass_d_stato ");
		sql.append(" 	,CPASS_T_FORNITORE ");
		sql.append(" where ");
		sql.append(" 	cpass_v_ordine.stato_id       			    = cpass_d_stato.stato_id ");
		sql.append(" 	AND cpass_v_ordine.fornitore_id   			= CPASS_T_FORNITORE.fornitore_id ");
		sql.append(" 	AND	cpass_v_ordine.impegno_anno_esercizio 	= :annoEsercizio ");
		sql.append(" 	AND cpass_v_ordine.impegno_anno   			= :annoImpegno ");
		sql.append(" 	AND cpass_v_ordine.impegno_numero 			= :numeroImpegno ");
		if(annoSubImpegno!=null) {
			sql.append(" 	AND cpass_v_ordine.subimpegno_anno      = :annoSubImpegno ");
			sql.append(" 	AND cpass_v_ordine.subimpegno_numero    = :numeroSubImpegno ");
			param.put( "annoSubImpegno", annoSubImpegno );
			param.put( "numeroSubImpegno",numeroSubImpegno );
		}
		sql.append(" ORDER BY cpass_v_ordine.ordine_anno,cpass_v_ordine.ordine_numero  ");

		param.put( "annoEsercizio",annoEsercizio );
		param.put( "annoImpegno", annoImpegno );
		param.put( "numeroImpegno",numeroImpegno );

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
}
