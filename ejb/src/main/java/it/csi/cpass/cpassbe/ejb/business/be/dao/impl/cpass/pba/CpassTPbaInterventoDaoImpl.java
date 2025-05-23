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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.pba;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.InterventoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassRuoloEnum;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.pba.VIntervento;
/**
 * Data Access Object implementor for the entity CpassTPbaIntervento
 */
@ApplicationScoped
public class CpassTPbaInterventoDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTPbaIntervento> implements CpassTPbaInterventoDao {

	@Override
	public Optional<CpassTPbaIntervento> findByCUI(String interventoCui, UUID idProgramma, UUID enteId){
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTPbaIntervento int ")
				.append(" WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoCui", "interventoCui", interventoCui);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaId", "idProgramma", idProgramma);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.cpassTEnte.enteId", "enteId", enteId);
		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<CpassTPbaIntervento> findByCUI(String interventoCui,  UUID enteId){
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTPbaIntervento int  WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoCui", "interventoCui", interventoCui);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.cpassTEnte.enteId", "enteId", enteId);
		jpql.append(" ORDER BY dataCreazione DESC ");

		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTPbaIntervento> findInterventiOldByCUI(String interventoCui, Integer programmaAnno, Integer programmaVersione, UUID enteId){
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTPbaIntervento int  WHERE int.dataCancellazione IS NULL ");
		jpql.append(" AND (int.cpassTPbaProgramma.programmaAnno < :programmaAnno OR (int.cpassTPbaProgramma.programmaAnno = :programmaAnno AND int.cpassTPbaProgramma.programmaVersione < :programmaVersione ))");
		params.put("programmaAnno", programmaAnno);
		params.put("programmaVersione", programmaVersione);
		//JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaVersione", "programmaVersione", programmaVersione);
		//JpaQueryHelper.andFieldEquals(jpql, params, "int.CpassTPbaProgramma.programmaAnno", "programmaAnno", programmaAnno);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoCui", "interventoCui", interventoCui);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.cpassTEnte.enteId", "enteId", enteId);
		jpql.append(" ORDER BY dataCreazione DESC ");
		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<CpassTPbaIntervento> findInterventoRicompreso(String cui, UUID programmaId){
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTPbaIntervento int  WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.interventoCui", "interventoCui", cui);
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassDStato.statoTipo"            , "tipo"   ,     CpassEnum.INTERVENTO.getCostante());
		JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassDStato.statoCodice"          , "codice" ,     StatoInterventiEnum.CANCELLATO.getCostante());
		JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassTPbaProgramma.programmaId" , "programmaId", programmaId);
		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Page<CpassTPbaIntervento> findPaginated(String cui,
			Integer annoAvvio,
			String cup,
			Boolean lottoFunzionale,
			Integer durataMesi,
			Boolean nuovoAffidamento,
			String descrizioneAcquisto,
			String rupCognome,
			UUID programmaId,
			Integer ausaId,
			//UUID interventoRicompresoId,
			String ricompresoCui,
			UUID interventoCopiaId,
			Integer settoreInterventiId,
			Integer cpvId,
			Integer nutId,
			Integer prioritaId,
			Integer modalitaAffidamentoId,
			Integer statoId,
			Integer acquistoVariatoId,
			Integer ricompresoTipoId,
			List<UUID> settoriId,
			String acqNonRip,
			String vefinitivaStr,
			String vistoRagioneriaStr,
			UUID enteId,
			int page,
			int size,
			String sortField,
			String sortDirection,
			String ordinamento
			) {

		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTPbaIntervento int ")
				//.append(" LEFT OUTER JOIN CpassTSettore set ON int.cpassTSettore.settoreId = 	set.settoreId ")
				.append(" WHERE int.dataCancellazione IS NULL ");

		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoAnnoAvvio", "annoAvvio", annoAvvio);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoCup", "cup", cup);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoLottoFunzionale", "lottoFunzionale", lottoFunzionale);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoDurataMesi", "durataMesi", durataMesi);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoNuovoAffidamento", "nuovoAffidamento", nuovoAffidamento);
		JpaQueryHelper.andFieldLike  (jpql, params, "int.interventoDescrizioneAcquisto", "descrizioneAcquisto", descrizioneAcquisto);
		JpaQueryHelper.andFieldLike  (jpql, params, "int.cpassTUtenteRup.utenteCognome", "rupCognome", rupCognome);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaId", "programmaId", programmaId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaAusa.id", "ausaId", ausaId);

		//JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaInterventoRicompreso.interventoId", "interventoRicompresoId", interventoRicompresoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoRicompresoCui", "interventoRicompresoCui", ricompresoCui);

		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaInterventoCopia.interventoId", "interventoCopiaid", interventoCopiaId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaSettoreInterventi.settoreInterventiId", "settoreInterventiId", settoreInterventiId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDCpv.cpvId", "cpvId", cpvId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaNut.nutsId", "nutId", nutId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaPriorita.prioritaId", "prioritaId", prioritaId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaModAffidamento.modAffidamentoId", "modalitaAffidamentoId", modalitaAffidamentoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDStato.statoId", "statoId", statoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaAcquistoVariato.prioritaId", "acquistoVariatoId", acquistoVariatoId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaRicompresoTipo.ricompresoTipoId", "ricompresoTipoId", ricompresoTipoId);

		//JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTSettore.settoreId", "settoreId", settoreId);
		if(settoriId!=null && settoriId.size()>0) {
			jpql.append(" AND int.cpassTSettore.settoreId IN (:settoriId) ");
			params.put("settoriId", settoriId);

		}

		// esclude gli interventi che non possono essere copiati perchè frutto di precedenti copie
		if(acqNonRip!= null && acqNonRip.trim().toUpperCase().equals("ACQ_NON_RIP")) {
			JpaQueryHelper.andFieldNotNull(jpql, "int.motivazioneNonRiproposto");
		}
		if(acqNonRip!= null && acqNonRip.trim().toUpperCase().equals("ACQ_ATTIVI")) {
			JpaQueryHelper.andFieldNull(jpql, "int.motivazioneNonRiproposto");
		}

		if(vefinitivaStr!= null && vefinitivaStr.equalsIgnoreCase("true")){
			JpaQueryHelper.andFieldEquals(jpql, params, "int.versioneDefinitiva", "vefinitivaStr", Boolean.TRUE);
		}
		if(vefinitivaStr!= null && vefinitivaStr.equalsIgnoreCase("false")){
			JpaQueryHelper.andFieldEquals(jpql, params, "int.versioneDefinitiva", "vefinitivaStr", Boolean.FALSE);
		}

		if(vistoRagioneriaStr!= null && vistoRagioneriaStr.equalsIgnoreCase("true")){
			JpaQueryHelper.andFieldEquals(jpql, params, "int.vistoRagioneria", "vistoRagioneriaStr", Boolean.TRUE);
		}
		if(vistoRagioneriaStr!= null && vistoRagioneriaStr.equalsIgnoreCase("false")){
			JpaQueryHelper.andFieldEquals(jpql, params, "int.vistoRagioneria", "vistoRagioneriaStr", Boolean.FALSE);
		}





		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.cpassTEnte.enteId", "enteId", enteId);
		if(ordinamento != null && !ordinamento.equals("")) {
			jpql.append(" ORDER BY ").append(ordinamento);
		}else {
			// potenziale baco
			if (sortField != null && sortDirection != null) {
				jpql.append(" ORDER BY ").append(InterventoSort.ANNO_AVVIO.getQueryName()).append(", ").append(InterventoSort.CUI.getQueryName());
			}

		}
		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public List<CpassTPbaIntervento> getInterventoByProgrammaStato(UUID programmaId, String statoCode,boolean operatoreEqualsStato) {
		final Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTPbaIntervento int ")
				.append(" WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassTPbaProgramma.programmaId", "programmaId",programmaId);
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassDStato.statoTipo", "tipo",CpassEnum.INTERVENTO.getCostante());
		if(statoCode!=null && !statoCode.trim().equals("")) {
			if(operatoreEqualsStato) {
				JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDStato.statoCodice","codice", statoCode);
			}else {
				JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassDStato.statoCodice", "codice", statoCode);
			}
		}
		jpql = jpql.append(" order by int.interventoAnnoAvvio, int.interventoCui  ");

		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		final List<CpassTPbaIntervento> ris = query.getResultList();
		return ris;
	}

	@Override
	public List<UUID> getInterventoIdByProgrammaStato(UUID programmaId, String statoCode,boolean operatoreEqualsStato) {
		final Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
				.append("SELECT int.interventoId FROM CpassTPbaIntervento int ")
				.append(" WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassTPbaProgramma.programmaId", "programmaId",programmaId);
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassDStato.statoTipo", "tipo",CpassEnum.INTERVENTO.getCostante());
		if(statoCode!=null && !statoCode.trim().equals("")) {
			if(operatoreEqualsStato) {
				JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDStato.statoCodice","codice", statoCode);
			}else {
				JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassDStato.statoCodice", "codice", statoCode);
			}
		}
		jpql = jpql.append(" order by int.interventoAnnoAvvio, int.interventoCui  ");

		final TypedQuery<UUID> query = composeTypedQuery(jpql, params,UUID.class);
		final List<UUID> ris = query.getResultList();
		return ris;
	}


	@Override
	public Optional<CpassTPbaIntervento> getInterventoEsistenteProgramma(UUID interventoId, UUID idProgramma) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append(" FROM CpassTPbaIntervento int ")
				.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaId", "idProgramma", idProgramma);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaInterventoCopia.interventoId", "interventoId", interventoId);

		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Page<CpassTPbaIntervento> findPaginatedXCopia(UUID programmaOldId, UUID programmaNewId,UUID utenteRupId, int page, int size,String sortField, String sortDirection, UUID utenteId, UUID settoreId) {
		final Date now = new Date();
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder jpql = new StringBuilder().append("FROM CpassTPbaIntervento int WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaId", "programmaOldId", programmaOldId);
		// escludere i cancellati
		JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassDStato.statoCodice", "codice" ,StatoInterventiEnum.CANCELLATO.getCostante());
		// escludere i non riproposti
		JpaQueryHelper.andFieldNull(jpql, "int.motivazioneNonRiproposto");
		//escludere gli avviati
		jpql.append(" and (int.avviato is null or int.avviato != true)");
		//escludere un i cui già presenti sul nuovo programma
		jpql.append(" AND NOT EXISTS ( ");
		jpql.append(" FROM CpassTPbaIntervento intNew  WHERE intNew.dataCancellazione IS NULL AND intNew.interventoCui = int.interventoCui ");
		JpaQueryHelper.andFieldEquals(jpql, params, "intNew.cpassTPbaProgramma.programmaId", "programmaNewId", programmaNewId);
		jpql.append(") ");

		jpql.append(" AND NOT EXISTS ( ");
		jpql.append("FROM CpassTPbaIntervento intNew  WHERE intNew.dataCancellazione IS NULL AND intNew.cpassTPbaInterventoCopia.interventoId = int.interventoId ");
		JpaQueryHelper.andFieldEquals(jpql, params, "intNew.cpassTPbaProgramma.programmaId", "programmaNewId", programmaNewId);
		jpql.append(") ");

		// visibilita'
		jpql.append(" AND ( EXISTS (");
		jpql.append("    FROM CpassRRuoloUtenteSettore rrus");
		jpql.append("    WHERE rrus.cpassDRuolo.ruoloCodice in ( '" + CpassRuoloEnum.ADMIN.getCodice() + "' , '"+CpassRuoloEnum.REFP.getCodice()+"','"+CpassRuoloEnum.DELEGATO_REFP.getCodice()+"')");
		JpaQueryHelper.andCheckDateValidita(jpql, params, "rrus", "now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente.utenteId", "utenteId", utenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTSettore.settoreId", "settoreId", settoreId);
		jpql.append("    ) ");
		jpql.append(" OR EXISTS (");
		jpql.append("    FROM CpassRRuoloUtenteSettore rrus");
		jpql.append("    WHERE rrus.cpassDRuolo.ruoloCodice in ( '" + CpassRuoloEnum.RUP.getCodice() + "' , '"+CpassRuoloEnum.DELEGATO_RUP.getCodice()+"')");
		JpaQueryHelper.andCheckDateValidita(jpql, params, "rrus", "now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente.utenteId", "utenteId", utenteId);
		jpql.append("    AND rrus.cpassRUtenteSettore.cpassTSettore.settoreId = int.cpassTSettore.settoreId ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTUtenteRup.utenteId", "utenteId", utenteId);
		jpql.append("    ) ");
		jpql.append(" OR EXISTS (");
		jpql.append("    FROM CpassTUtenteRupDeleghe rrus");
		jpql.append("    WHERE rrus.cpassTUtenteRupDelegato.utenteId = '" + utenteId +"'");
		JpaQueryHelper.andCheckDateValidita(jpql, params, "rrus", "now", now);
		jpql.append("    AND rrus.cpassTUtenteRup.utenteId = int.cpassTUtenteRup.utenteId ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTSettore.settoreId", "settoreId", settoreId);
		jpql.append("    ) ");
		jpql.append(" OR EXISTS (");
		jpql.append("    FROM CpassRRuoloUtenteSettore rrus");
		jpql.append("    WHERE rrus.cpassDRuolo.ruoloCodice = '" + CpassRuoloEnum.COMPILATORE.getCodice() + "'");
		JpaQueryHelper.andCheckDateValidita(jpql, params, "rrus", "now", now);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTUtente.utenteId", "utenteId", utenteId);
		JpaQueryHelper.andFieldEquals(jpql, params, "rrus.cpassRUtenteSettore.cpassTSettore.settoreId", "settoreId", settoreId);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTSettore.settoreId", "settoreId", settoreId);
		jpql.append(" ))");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTUtenteRup.utenteId", "utenteRupId", utenteRupId);
		params.put("now", now);
		//if(sortField != null && !sortField.trim().equals("")) {
		//jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
		//}else {
		jpql.append(" ORDER BY int.interventoCui ");
		//}
		//log.info("findPaginatedXCopia", jpql.toString());
		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public List<CpassTPbaIntervento> getInterventiNonRiproponibiliByUltimoProgrammaPrecedente(Integer programmaAnnoPrecedente,Integer programmaVersioneMaxPrecedente) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append("FROM CpassTPbaIntervento int ").append(" WHERE 1 = 1 ");//int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaAnno", "programmaAnno", programmaAnnoPrecedente);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaVersione", "programmaVersione", programmaVersioneMaxPrecedente);
		//JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaAcquistoVariato.acquistoVariatoCodice", "acquistoVariatoCodice", "1");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaAcquistoVariato.acquistiNonRiproposti", "acquistiNonRiproposti", true);
		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Integer getMaxProgressivoCUIByChiave(String chiaveCui) {
		Integer ris;
		final StringBuilder sql = new StringBuilder();
		final Map<String, Object> params = new HashMap<>();
		// fare particolare attenzione al cast a stringa dei campi di tipo UUID quando si utilizza
		// una query nativa doiversamente ottieni errore No Dialect mapping for JDBC type: 1111
		sql.append(" select coalesce (max((substring(intervento_cui,17,21))) , '0') ");
		sql.append(" from ");
		sql.append(" cpass_t_pba_intervento ");
		sql.append(" where 1 = 1 ");
		//sql.append(" intervento_cui LIKE :chiaveCui ");
		JpaQueryHelper.andFieldLike  (sql, params, "intervento_cui", "chiaveCui", chiaveCui);
		//params.put("chiaveCui", chiaveCui);

		final Query query = composeNativeQuery(sql.toString(), params);

		final List<Object> resultList = query.getResultList();
		resultList.get(0);

		ris = Integer.parseInt((String) resultList.get(0));
		//}
		return ris;
	}

	@Override
	public List<CpassTPbaIntervento> getInterventiByCapofilaId(UUID idCapofila,UUID programmaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTPbaIntervento int ")
				.append(" WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaInterventoCapofila.interventoId", "idCapofila", idCapofila);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaId", "programmaId", programmaId);

		JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassDStato.statoCodice", "codice" ,StatoInterventiEnum.CANCELLATO.getCostante());

		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		final List<CpassTPbaIntervento> ris = query.getResultList();
		return ris;
	}

	@Override
	public List<CpassTPbaIntervento> getInterventiCapofila(UUID programmaId, UUID myIdCapofila, UUID idSettore) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTPbaIntervento int ")
				.append(" WHERE int.dataCancellazione IS NULL and int.capofila = true");
		JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassDStato.statoCodice", "codice" ,StatoInterventiEnum.CANCELLATO.getCostante());
		JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassTPbaInterventoCapofila.interventoId", "idCapofila", myIdCapofila);
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassTPbaProgramma.programmaId", "idProgramma", programmaId);

		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTSettore.settoreId", "idSettore", idSettore);
		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTPbaIntervento> getInterventiByListaId(List<UUID> listaId) {
		final Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
				.append("FROM CpassTPbaIntervento int ")
				.append(" WHERE int.dataCancellazione IS NULL ");
		jpql = jpql.append(" AND int.interventoId IN (:listaId) " );
		params.put("listaId", listaId);
		jpql = jpql.append(" ORDER BY int.capofila DESC  " );

		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<CpassTPbaIntervento> getInterventiBloccantiPerConfermaProgramma(UUID idProgramma) {
		final Map<String, Object> params = new HashMap<>();
		final List<String> listaStatoCodice = new ArrayList<>();
		listaStatoCodice.add(StatoInterventiEnum.VISTO.getCostante());
		listaStatoCodice.add(StatoInterventiEnum.BOZZA.getCostante());
		StringBuilder jpql = new StringBuilder().append(" FROM CpassTPbaIntervento int  WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassTPbaProgramma.programmaId", "programmaId",idProgramma);
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassDStato.statoTipo", "tipo",CpassEnum.INTERVENTO.getCostante());
		// condizioni per il blocco del conferma programma
		// blocco se lo stato è visto o bozza a prescindere dal visto ragioneria
		// blocco pure se lo stato risulta validato e il visto ragioneria false
		jpql = jpql.append(" AND (int.cpassDStato.statoCodice IN (:listaStatoCodice) OR (int.vistoRagioneria = false AND int.cpassDStato.statoCodice = :validato ))" );
		//jpql = jpql.append(" AND (int.interventoCui= 'F00514490010201900290' ) " );
		params.put("listaStatoCodice", listaStatoCodice);
		params.put("validato", StatoInterventiEnum.VALIDATO.getCostante());

		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		final List<CpassTPbaIntervento> ris = query.getResultList();
		return ris;

	}

	@Override
	public List<UUID> getInterventiBloccantiIdPerConfermaProgramma(UUID idProgramma) {

		final Map<String, Object> param = new HashMap<>();
		final StringBuilder sql = new StringBuilder();
		sql.append("    SELECT ");
		sql.append("  CAST( interv.intervento_id AS VARCHAR) ");
		sql.append("	FROM ");
		sql.append("		Cpass_T_Pba_Intervento interv ");
		sql.append("		,cpass_d_stato stato ");
		sql.append("	WHERE ");
		sql.append("		interv.stato_id  = stato.stato_id  ");
		sql.append("		and interv.programma_id = :idProgramma ");
		sql.append("		and interv.data_Cancellazione IS null ");
		sql.append("		and (stato.stato_codice in ('VISTO','BOZZA') OR (interv.visto_Ragioneria = false AND stato.stato_Codice = 'VALIDATO' )) ");

		param.put("idProgramma", idProgramma);

		final Query query = composeNativeQuery(sql.toString(), param);

		List<UUID> listaRis = ((List<String>)query.getResultList()).stream().map( x -> UUID.fromString(x)).collect(Collectors.toList());
		return listaRis;
	}

	@Override
	public void deleteByIdIntervento(UUID interventoId) {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM CpassTPbaIntervento imp");
		sb.append(" WHERE imp.interventoId = :interventoId");
		final Map<String, Object> params = new HashMap<>();
		params.put("interventoId", interventoId);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}


	@Override
	public List<CpassTPbaIntervento> getInterventiSuprogrammiFuturi(String interventoCui, Integer programmaAnno,UUID enteId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append(" FROM CpassTPbaIntervento int WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoCui", "interventoCui", interventoCui);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.cpassTEnte.enteId", "enteId", enteId);

		jpql.append("    AND int.cpassTPbaProgramma.programmaAnno   > :programmaAnno ");
		jpql.append("    AND int.motivazioneNonRiproposto is null  ");
		jpql.append("    AND int.cpassDStato.statoCodice != 'CANCELLATO' ");
		params.put("programmaAnno", programmaAnno);
		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		final List<CpassTPbaIntervento> ris = query.getResultList();
		return ris;
	}



	@Override
	public List<CpassTPbaIntervento> getRicercaByProgrammaId(UUID programmaId) {
		final Map<String, Object> params = new HashMap<>();
		final StringBuilder jpql = new StringBuilder().append(" FROM CpassTPbaIntervento int WHERE int.dataCancellazione IS NULL ");
		jpql.append("    AND int.cpassTPbaProgramma.programmaId = :programmaId ");
		params.put("programmaId", programmaId);
		final TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		final List<CpassTPbaIntervento> ris = query.getResultList();
		return ris;
	}


	@Override
	public List<VIntervento> getRicercaNativaByProgrammaId(UUID programmaId) {
		final Map<String, Object> param = new HashMap<>();

		final StringBuilder sql = new StringBuilder().append("SELECT  ");
		sql.append(" CAST(interv.intervento_id AS VARCHAR) 				");			//UUID NOT NULL,
		sql.append(",interv.intervento_cui  				");			//VARCHAR(50) NOT NULL,
		sql.append(",interv.intervento_anno_avvio  		"); 		//INTEGER NOT NULL,
		sql.append(",interv.intervento_cup   			");			//VARCHAR(15),
		sql.append(",interv.intervento_lotto_funzionale  ");			//BOOLEAN DEFAULT false NOT NULL,
		sql.append(",interv.intervento_durata_mesi 	    ");			//INTEGER NOT NULL,
		sql.append(",interv.intervento_nuovo_affid       ");			//BOOLEAN DEFAULT false NOT NULL,
		sql.append(",interv.intervento_descrizione_acquisto   ");	//VARCHAR(500) NOT NULL,
		sql.append(",CAST(interv.programma_id  AS VARCHAR) 	      ");				//UUID NOT NULL,

		sql.append(",interv.flag_cui_non_generato 	      ");		//BOOLEAN DEFAULT false,
		sql.append(",interv.motivazione_non_riproposto 	  ");		//VARCHAR(500),
		sql.append(",interv.intervento_copia_tipo 		  ");		//VARCHAR(50),
		sql.append(",interv.intervento_importi_copia_tipo  ");	    //VARCHAR(50),
		sql.append(",interv.esente_cup 					  ");		//BOOLEAN DEFAULT false,
		sql.append(",interv.data_visto 					  ");		//TIMESTAMP WITHOUT TIME ZONE,
		sql.append(",interv.intervento_ricompreso_cui 	  ");		//VARCHAR(21),
		sql.append(",interv.visto_ragioneria 			  ");		//BOOLEAN DEFAULT false NOT NULL
		sql.append(",interv.data_visto_ragioneria 		  ");		//TIMESTAMP WITHOUT TIME ZONE,
		sql.append(",interv.capofila 					  ");		//BOOLEAN DEFAULT false NOT NULL,

		sql.append(",interv.versione_definitiva 			  ");	    //BOOLEAN DEFAULT true NOT NULL,
		sql.append(",interv.avviato 						  ");	    //BOOLEAN DEFAULT false,
		sql.append(",interv.data_avviato 				  ");		//TIMESTAMP WITHOUT TIME ZONE,
		sql.append(",interv.esente_iva 					  ");		//VARCHAR(5),
		sql.append(",CAST(interv_altri_dati.intervento_altri_dati_id  AS VARCHAR)   ");	//UUID NOT NULL,
		sql.append(",interv_altri_dati.codice_interno 		  ");	//VARCHAR(50),
		sql.append(",interv_altri_dati.spese_sostenute 		  ");	//NUMERIC(13,5),
		sql.append(",interv_altri_dati.iva_primo_anno 		  ");	//NUMERIC,
		sql.append(",interv_altri_dati.iva_secondo_anno 		  ");	//NUMERIC,
		sql.append(",interv_altri_dati.iva_terzo_anno			  ");//NUMERIC,

		sql.append(",interv_altri_dati.iva_anni_successivi 	  ");	//NUMERIC,
		sql.append(",interv_altri_dati.note 					  ");	//VARCHAR(4000),
		sql.append(",interv_altri_dati.normativa_riferimento 	  ");//VARCHAR(200),
		sql.append(",interv_altri_dati.oggettoverdi 				  ");//VARCHAR(500),
		sql.append(",interv_altri_dati.importo_netto_iva_verdi 	  ");//NUMERIC,
		sql.append(",interv_altri_dati.importo_iva_verdi 		  ");//NUMERIC,
		sql.append(",interv_altri_dati.importo_tot_verdi 		  ");//NUMERIC,
		sql.append(",interv_altri_dati.fondi_pnrr 				  ");//BOOLEAN DEFAULT false,
		sql.append(",stato.stato_id   ");
		sql.append(",stato.stato_codice   ");

		sql.append(",stato.stato_descrizione        ");
		sql.append(",asq_variato.acquisto_variato_id  ");
		sql.append(",asq_variato.acquisto_variato_codice  ");
		sql.append(",asq_variato.acquisto_variato_descrizione  ");
		sql.append(",ausa.ausa_id  ");
		sql.append(",ausa.ausa_codice  ");
		sql.append(",ausa.ausa_descrizione  ");
		sql.append(",modAff.mod_affidamento_id  ");
		sql.append(",modAff.mod_affidamento_codice  ");
		sql.append(",modAff.mod_affidamento_descrizione  ");

		sql.append(",nuts.nuts_id  ");
		sql.append(",nuts.nuts_codice  ");
		sql.append(",nuts.nuts_descrizione  ");
		sql.append(",priorita.priorita_id  ");
		sql.append(",priorita.priorita_codice  ");
		sql.append(",priorita.priorita_descrizione  ");
		sql.append(",ricompresoTipo.ricompreso_tipo_id  ");
		sql.append(",ricompresoTipo.ricompreso_tipo_codice  ");
		sql.append(",ricompresoTipo.ricompreso_tipo_descrizione  ");
		sql.append(",ricompresoTipo.ricompreso_tipo_conteggio_importi  ");

		sql.append(",cpv.cpv_id  ");
		sql.append(",cpv.cpv_codice  ");
		sql.append(",cpv.cpv_descrizione  ");
		sql.append(",CAST(utenteRup.utente_id   AS VARCHAR) ");
		sql.append(",utenteRup.utente_codice_fiscale  ");
		sql.append(",utenteRup.utente_cognome  ");
		sql.append(",utenteRup.utente_nome  ");
		sql.append(",settoreInterventi.settore_interventi_id  ");
		sql.append(",settoreInterventi.settore_interventi_codice  ");
		sql.append(",settoreInterventi.settore_interventi_descrizione  ");
		sql.append(",CAST(interv.intervento_capofila_id   AS VARCHAR)					  ");

		sql.append("     from ");
		sql.append("      cpass_t_pba_intervento  interv ");
		sql.append("      inner join cpass_d_stato stato on interv.stato_id=stato.stato_id  ");
		sql.append("      left outer join cpass_t_pba_intervento_altri_dati interv_altri_dati on interv.intervento_id = interv_altri_dati.intervento_id ");
		sql.append("      left outer join cpass_d_pba_acquisto_variato asq_variato on asq_variato.acquisto_variato_id=interv.acquisto_variato_id  ");
		sql.append("      left outer join cpass_d_pba_ausa ausa on ausa.ausa_id=interv.ausa_id  ");
		sql.append("      inner join cpass_d_pba_mod_affidamento modAff on modAff.mod_affidamento_id=interv.mod_affidamento_id  ");
		sql.append("      inner join cpass_d_pba_nuts nuts on nuts.nuts_id=interv.nuts_id  ");
		sql.append("      inner join cpass_d_pba_priorita priorita on priorita.priorita_id=interv.priorita_id  ");
		sql.append("      left outer join cpass_d_pba_ricompreso_tipo ricompresoTipo on ricompresoTipo.ricompreso_tipo_id=interv.ricompreso_tipo_id  ");
		sql.append(" 	  inner join cpass_d_cpv cpv on interv.cpv_id=cpv.cpv_id ");
		sql.append("      left outer join cpass_t_utente utenteRup on interv.utente_rup_id=utenteRup.utente_id  ");
		sql.append("      left outer join cpass_d_pba_settore_interventi settoreInterventi on interv.settore_interventi_id=settoreInterventi.settore_interventi_id ");
		sql.append(" where  ");
		sql.append(" interv.programma_id =:programmaId ");
		//sql.append(" AND interv.intervento_cui ='F00514490010201900290' ");

		sql.append(" ORDER BY interv.intervento_anno_avvio,interv.intervento_cui   " );
		param.put("programmaId", programmaId);

		log.info("getRicercaNativaByProgrammaId ", sql.toString());
		log.info("programmaId ", programmaId);
		final Query query = composeNativeQuery(sql.toString(), param);

		final List<Object[]> list = query.getResultList();
		final List<VIntervento> listaVIntervento = new ArrayList<>();
		for(final Object[] obj : list) {
			final VIntervento vInt = new VIntervento();
			int i = 0;
			vInt.setInterventoId(castUUIDFromString((String)obj[i]));
			i++;
			vInt.setCui((String)obj[i]);
			i++;
			vInt.setAnnoAvvio((Integer)obj[i]);
			i++;
			vInt.setCup((String)obj[i]);
			i++;
			vInt.setLottoFunzionale((Boolean)obj[i]);
			i++;
			vInt.setDurataMesi((Integer)obj[i]);
			i++;
			vInt.setNuovoAffidamento((Boolean)obj[i]);
			i++;
			vInt.setDescrizioneAcquisto((String)obj[i]);
			i++;
			vInt.setProgrammaId(castUUIDFromString((String)obj[i]));

			i++;
			vInt.setFlagCuiNonGenerato((Boolean)obj[i]);
			i++;
			vInt.setMotivazioneNonRiproposto((String)obj[i]);
			i++;
			vInt.setInterventoCopiaTipo((String)obj[i]);
			i++;
			vInt.setInterventoImportiCopiaTipo((String)obj[i]);
			i++;
			vInt.setEsenteCup((Boolean)obj[i]);
			i++;
			vInt.setDataVisto((Date)obj[i]);
			i++;
			vInt.setRicompresoCui((String)obj[i]);
			i++;
			vInt.setVistoRagioneria((Boolean)obj[i]);
			i++;
			vInt.setDataVistoRagioneria((Date)obj[i]);
			i++;
			vInt.setCapofila((Boolean)obj[i]);

			i++;
			vInt.setVersioneDefinitiva((Boolean)obj[i]);
			i++;
			vInt.setAvviato((Boolean)obj[i]);
			i++;
			vInt.setDataAvviato((Date)obj[i]);
			i++;
			vInt.setEsenteIva((String)obj[i]);
			i++;
			vInt.setAltriDatiId(castUUIDFromString((String)obj[i]));
			i++;
			vInt.setAltriDaticodiceInterno((String)obj[i]);
			i++;
			vInt.setAltriDatispeseSostenute((BigDecimal)obj[i]);
			i++;
			vInt.setAltriDatiivaPrimoAnno((BigDecimal)obj[i]);
			i++;
			vInt.setAltriDatiivaSecondoAnno((BigDecimal)obj[i]);
			i++;
			vInt.setAltriDatiivaTerzoAnno((BigDecimal)obj[i]);

			i++;
			vInt.setAltriDatiivaAnniSuccessivi((BigDecimal)obj[i]);
			i++;
			vInt.setAltriDatinote((String)obj[i]);
			i++;
			vInt.setAltriDatinormativaRiferimento((String)obj[i]);
			i++;
			vInt.setAltriDatioggettoverdi((String)obj[i]);
			i++;
			vInt.setAltriDatiimportoNettoIvaVerdi((BigDecimal)obj[i]);
			i++;
			vInt.setAltriDatiimportoIvaVerdi((BigDecimal)obj[i]);
			i++;
			vInt.setAltriDatiimportoTotVerdi((BigDecimal)obj[i]);
			i++;
			vInt.setAltriDatifondiPnrr((Boolean)obj[i]);
			i++;
			vInt.setStatoId((Integer)obj[i]);
			i++;
			vInt.setStatoCodice((String)obj[i]);

			i++;
			vInt.setStatoDescrizione((String)obj[i]);
			i++;
			vInt.setAcquistoVariatoId((Integer)obj[i]);
			i++;
			vInt.setAcquistoVariatoCodice((String)obj[i]);
			i++;
			vInt.setAcquistoVariatoDescrizione((String)obj[i]);
			i++;
			vInt.setAusaId((Integer)obj[i]);
			i++;
			vInt.setAusaCodice((String)obj[i]);
			i++;
			vInt.setAusaDescrizione((String)obj[i]);
			i++;
			vInt.setModalitaAffidamentoId((Integer)obj[i]);
			i++;
			vInt.setModalitaAffidamentoCodice((String)obj[i]);
			i++;
			vInt.setModalitaAffidamentoDescrizione((String)obj[i]);

			i++;
			vInt.setNutsId ((Integer)obj[i]);
			i++;
			vInt.setNutsCodice((String)obj[i]);
			i++;
			vInt.setNutsDescrizione((String)obj[i]);
			i++;
			vInt.setPrioritaId((Integer)obj[i]);
			i++;
			vInt.setPrioritaCodice((String)obj[i]);
			i++;
			vInt.setPrioritaDescrizione((String)obj[i]);
			i++;
			vInt.setRicompresoTipoId((Integer)obj[i]);
			i++;
			vInt.setRicompresoTipoCodice((String)obj[i]);
			i++;
			vInt.setRicompresoTipoDescrizione((String)obj[i]);
			i++;
			vInt.setRicompresoTipoConteggioImporti((Boolean)obj[i]);

			i++;
			vInt.setCpvId((Integer)obj[i]);
			i++;
			vInt.setCpvCodice((String)obj[i]);
			i++;
			vInt.setCpvDescrizione((String)obj[i]);
			i++;
			vInt.setUtenteRupId(castUUIDFromString((String)obj[i]));
			i++;
			vInt.setUtenteRupCF((String)obj[i]);
			i++;
			vInt.setUtenteRupCognome((String)obj[i]);
			i++;
			vInt.setUtenteRupNome((String)obj[i]);
			i++;
			vInt.setSettoreInterventiId((Integer)obj[i]);
			i++;
			vInt.setSettoreInterventiCodice((String)obj[i]);
			i++;
			vInt.setSettoreInterventiDescrizione((String)obj[i]);
			i++;
			vInt.setInterventoCapofilaId(castUUIDFromString((String)obj[i]));

			listaVIntervento.add(vInt);
		}
		return listaVIntervento;//findOrdineByIds(listaId);
	}

	private UUID castUUIDFromString(String value) {
		if(StringUtility.isNotEmpty(value)) {
			return UUID.fromString(value);
		}
		return null;
	}
}


