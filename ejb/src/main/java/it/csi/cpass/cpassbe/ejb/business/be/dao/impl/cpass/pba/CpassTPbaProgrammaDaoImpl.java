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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.pba;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaProgrammaDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.business.be.facade.MITManager;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassTPbaProgrammaDaoImpl
 */
@ApplicationScoped
public class CpassTPbaProgrammaDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTPbaProgramma> implements CpassTPbaProgrammaDao {

	@Override
	public Page<CpassTPbaProgramma> findPaginated(Integer programmaAnno,  Integer numeroProvvedimento,
			String descrizioneProvvedimento, Date dataProvvedimento, Date dataPubblicazione, String url, int page,
			int size) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTPbaProgramma tp ")
			.append("WHERE 1=1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.programmaAnno", "programmaAnno", programmaAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.descrizioneProvvedimento", "descrizioneProvvedimento", descrizioneProvvedimento);
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.url", "url", url);
		
		jpql.append(" ORDER BY tp.programmaAnno, tp.programmaVersione asc");
		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public Integer getMaxVersioneProgrammaByAnnoEnteStato(Integer anno, UUID enteId, String statoCodice) {
		return getMaxVersioneProgrammaByAnnoEnteStato( anno,  enteId,  statoCodice,Boolean.FALSE);
	}

	
	@Override
	public Integer getMaxVersioneProgrammaByAnnoEnteStato(Integer anno, UUID enteId, String statoCodice,Boolean solovalidi) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append(" SELECT MAX(tp.programmaVersione) ")
			.append(" FROM CpassTPbaProgramma tp ")
			.append(" WHERE 1=1 ");
			if(solovalidi.equals(Boolean.TRUE)) {
				jpql.append(" AND tp.cpassDStato.statoCodice <> :statoCancellato ");
				params.put("statoCancellato", CpassStatiEnum.PRO_CANCELLATO.getCostante());
			} 
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.programmaAnno", "anno", anno);
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.cpassTEnte.enteId", "enteId", enteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.cpassDStato.statoCodice", "statoCodice", statoCodice);
		
		TypedQuery<Integer> query = composeTypedQuery(jpql, params, Integer.class);
		List<Integer> maxver = query.getResultList();
		if(maxver==null || maxver.get(0)==null) {
			return 0;
		}
		return query.getResultList().stream().findFirst().get();
	}

	@Override
	public List<CpassTPbaProgramma> getProgrammiBySettoreAnnoVersioneStato(UUID settoreId, Integer anno, Integer programmaVersione,String statoCodice, Boolean solovalidi) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append(" SELECT tp ")
			.append("FROM CpassTPbaProgramma tp, CpassTSettore ts ");
		    
		    jpql.append("WHERE tp.cpassTEnte = ts.cpassTEnte");
			if(solovalidi.equals(Boolean.TRUE)) {
				jpql.append(" AND tp.cpassDStato.statoCodice <> :statoCancellato ");
				params.put("statoCancellato", CpassStatiEnum.PRO_CANCELLATO.getCostante());
			}
		JpaQueryHelper.andFieldEquals(jpql, params, "ts.settoreId", "settoreId", settoreId);		
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.programmaAnno", "anno", anno);		
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.programmaVersione", "programmaVersione", programmaVersione);
		JpaQueryHelper.andFieldEquals(jpql, params, "tp.cpassDStato.statoCodice", "statoCodice", statoCodice);		
		jpql.append(" ORDER BY tp.programmaAnno desc , tp.programmaVersione asc ");
		TypedQuery<CpassTPbaProgramma> query = composeTypedQuery(jpql, params);
		//return query.getResultList().stream().findFirst();
		return query.getResultList();
	}
	
	@Override
	public List<CpassTPbaProgramma> getProgrammiByAnnoVersioneEnteStato(Integer programmaAnno, Integer programmaVersione, UUID enteId, String statoCodice , Boolean solovalidi, String sortField,String sortDirection) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append(" SELECT tp ")
			.append("FROM CpassTPbaProgramma tp ")
			.append("WHERE 1 = 1 ");
		
			if(solovalidi.equals(Boolean.TRUE)) {
				jpql.append(" AND tp.cpassDStato.statoCodice <> :statoCancellato ");
				params.put("statoCancellato", CpassStatiEnum.PRO_CANCELLATO.getCostante());
			}
			
			JpaQueryHelper.andFieldEquals(jpql, params, "tp.programmaAnno", "programmaAnno", programmaAnno);
			JpaQueryHelper.andFieldEquals(jpql, params, "tp.programmaVersione", "programmaVersione", programmaVersione);
			JpaQueryHelper.andFieldEquals(jpql, params, "tp.cpassTEnte.enteId", "enteId", enteId);
			JpaQueryHelper.andFieldEquals(jpql, params, "tp.cpassDStato.statoCodice", "statoCodice", statoCodice);		
			
			if (sortField != null && sortDirection != null) {
				jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
			}
			
			TypedQuery<CpassTPbaProgramma> query = composeTypedQuery(jpql, params);
			return query.getResultList();			
	}


	@Override
	public List<CpassTPbaProgramma> getProgrammiTrasmissioneMIT() {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT tp ");
		jpql.append(" FROM CpassTPbaProgramma tp ");
		jpql.append(" WHERE tp.cpassDStato.statoCodice = '" + ConstantsCPassStato.StatoEnum.CONFERMATO.getCostante() + "' ");
		
		jpql.append(" AND NOT EXISTS ( ");
		jpql.append("   SELECT te FROM CpassTElaborazione te, CpassTElaborazioneParametro tep ");
		jpql.append("   WHERE te.entitaId = CONCAT('', tp.programmaId) ");
		jpql.append("   AND te.elaborazioneStato IN ( ");
		jpql.append("     '" + ConstantsCPassElaborazione.StatoEnum.ELABORATO.getStatoDB() + "', ");
		jpql.append("     '" + ConstantsCPassElaborazione.StatoEnum.IN_ELABORAZIONE.getStatoDB() + "' ");
		jpql.append(" 	) ");
		
		jpql.append("   AND te.elaborazioneId = tep.cpassTElaborazione.elaborazioneId ");
		jpql.append("   AND tep.elaborazioneParametroChiave = '" + MITManager.MODALITA_INVIO + "' ");
		jpql.append("   AND tep.elaborazioneParametroValore = '" + MITManager.MODALITA_INVIO_CONTROLLO_PUBBLICAZIONE + "' ");
		
		jpql.append(" ) ");
		jpql.append(" ORDER BY tp.programmaAnno desc ");
		TypedQuery<CpassTPbaProgramma> query = composeTypedQuery(jpql, params);
		// return query.getResultList().stream().findFirst();
		return query.getResultList();
	}
	
}
