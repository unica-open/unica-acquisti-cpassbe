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

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.InterventoSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.TipoEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassStatiEnum;
import it.csi.cpass.cpassbe.ejb.util.jpa.JpaQueryHelper;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity CpassTPbaIntervento
 */
@ApplicationScoped
public class CpassTPbaInterventoDaoImpl extends BaseAuditedEntityDaoImpl<UUID, CpassTPbaIntervento> implements CpassTPbaInterventoDao {

	@Override
	public Optional<CpassTPbaIntervento> findByCUI(String interventoCui, UUID idProgramma, UUID enteId){
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTPbaIntervento int ")
			.append(" WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.interventoCui", "interventoCui", interventoCui);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaId", "idProgramma", idProgramma);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.cpassTEnte.enteId", "enteId", enteId);
		TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Optional<CpassTPbaIntervento> findInterventoRicompreso(String cui, UUID programmaId){
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTPbaIntervento int  WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.interventoCui", "interventoCui", cui);
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassDStato.statoTipo"            , "tipo"   ,     CpassEnum.INTERVENTO.getCostante());
		JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassDStato.statoCodice"          , "codice" ,     CpassStatiEnum.INT_CANCELLATO.getCostante());
		JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassTPbaProgramma.programmaId" , "programmaId", programmaId);
		
		
		TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
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
													UUID settoreId,
													UUID enteId,
													int page,
													int size,
													String sortField,
													String sortDirection,
													String ordinamento
													) {
		
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
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
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTSettore.settoreId", "settoreId", settoreId);
		// esclude gli interventi che non possono essere copiati perchè frutto di precedenti copie
		
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.cpassTEnte.enteId", "enteId", enteId);
		if(ordinamento != null && !ordinamento.equals("")) {
			jpql.append(" ORDER BY ").append(ordinamento);
		}else {
			if (sortField != null && sortDirection != null) {
				jpql.append(" ORDER BY ").append(InterventoSort.ANNO_AVVIO.getQueryName()).append(", ").append(InterventoSort.CUI.getQueryName());
			}
		}
		return getPagedResult(jpql, params, page, size);
	}

	@Override
	public List<CpassTPbaIntervento> getInterventoByProgrammaStato(UUID programmaId, String statoCode,boolean operatoreEqualsStato) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTPbaIntervento int ")
			.append(" WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassTPbaProgramma.programmaId", "programmaId",programmaId);
		JpaQueryHelper.andFieldEquals   (jpql, params, "int.cpassDStato.statoTipo", "tipo",CpassEnum.INTERVENTO.getCostante());
		if(operatoreEqualsStato) {
			JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDStato.statoCodice","codice", statoCode);
		}else {
			JpaQueryHelper.andFieldNotEquals(jpql, params, "int.cpassDStato.statoCodice", "codice", statoCode);
		}
		TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		
		return query.getResultList();
	}

	@Override
	public Optional<CpassTPbaIntervento> getInterventoEsistenteProgramma(UUID interventoId, UUID idProgramma) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append(" FROM CpassTPbaIntervento int ")
			.append(" WHERE 1 = 1 ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaId", "idProgramma", idProgramma);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaInterventoCopia.interventoId", "interventoId", interventoId);

		TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);
		return query.getResultList().stream().findFirst();
	}

	@Override
	public Page<CpassTPbaIntervento> findPaginatedXCopia(UUID programmaOldId, UUID programmaNewId, int page, int size,String sortField, String sortDirection) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTPbaIntervento int ")
			.append(" WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaId", "programmaOldId", programmaOldId);
		JpaQueryHelper.andFieldNull(jpql, "int.motivazioneNonRiproposto");

		jpql.append(" AND NOT EXISTS ( ");
			jpql.append("FROM CpassTPbaIntervento intNew  WHERE intNew.dataCancellazione IS NULL  AND intNew.cpassTPbaInterventoCopia.interventoId = int.interventoId ");
			JpaQueryHelper.andFieldEquals(jpql, params, "intNew.cpassTPbaProgramma.programmaId", "programmaNewId", programmaNewId);
			
		jpql.append(") ");

		jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
		log.info("findPaginatedXCopia", jpql.toString());
		return getPagedResult(jpql, params, page, size);	
	}

	@Override
	public List<CpassTPbaIntervento> getInterventiNonRiproponibiliByUltimoProgrammaPrecedente(Integer programmaAnnoPrecedente,Integer programmaVersioneMaxPrecedente) {
		
		Map<String, Object> params = new HashMap<>();
		StringBuilder jpql = new StringBuilder()
			.append("FROM CpassTPbaIntervento int ")
			.append(" WHERE int.dataCancellazione IS NULL ");
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaAnno", "programmaAnno", programmaAnnoPrecedente);
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassTPbaProgramma.programmaVersione", "programmaVersione", programmaVersioneMaxPrecedente);		
		JpaQueryHelper.andFieldEquals(jpql, params, "int.cpassDPbaAcquistoVariato.acquistoVariatoCodice", "acquistoVariatoCodice", "1");

		TypedQuery<CpassTPbaIntervento> query = composeTypedQuery(jpql, params);		
		return query.getResultList();
	}
}
