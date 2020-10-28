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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.lib.dto.custom.StatoInterventoInfo;

/**
 * Data Access Object implementor for the entity CpassTImpegnoDao
 */
@ApplicationScoped
public class CpassVOrdineDaoImpl extends BaseEntityDaoImpl<Long, CpassVOrdine> implements CpassVOrdineDao {

	
	@Override
	public List<CpassVOrdine> getListImpegniByOrdineId(UUID testataOrdineId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("FROM CpassVOrdine vo WHERE vo.testataOrdineId =:testataOrdineId ");
		/*
		jpql.append(" ORDER BY ");
		jpql.append("  vo.destinatarioId");
		jpql.append(" ,vo.rigaOrdineId");
		
		jpql.append(" ,vo.impegnoAnnoEsercizio");
		jpql.append(" ,vo.impegnoAnno");
		jpql.append(" ,vo.impegnoNumero");
		
		jpql.append(" ,vo.subimpegnoAnno");
		jpql.append(" ,vo.subimpegnoNumero");
		*/
		params.put("testataOrdineId", testataOrdineId);
		
		
		TypedQuery<CpassVOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassVOrdine> getListImpegniOrdinatiImpByOrdineId(UUID testataOrdineId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder().append("FROM CpassVOrdine vo WHERE vo.testataOrdineId =:testataOrdineId and vo.impegnoId != null");
		// non toccare l'ordinamento serve per aggregare gli importi
		jpql.append(" ORDER BY vo.impegnoAnno, vo.impegnoNumero, vo.subimpegnoAnno, vo.subimpegnoNumero");
		
		params.put("testataOrdineId", testataOrdineId);
		
		
		TypedQuery<CpassVOrdine> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassVOrdine> getListImpegniRiepilogoByOrdineId(UUID idTestataOrdine) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder().append("SELECT ");	
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
		
		Query query = composeNativeQuery(sql.toString(), param);
		
		List<Object[]> list = query.getResultList();
		List<CpassVOrdine> listaRis = new ArrayList<CpassVOrdine>();
		for(Object[] obj : list) {
			CpassVOrdine el = new CpassVOrdine();

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
}
