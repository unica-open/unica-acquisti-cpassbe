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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassTPbaIntervento
 */
public interface CpassTPbaInterventoDao extends BaseAuditedEntityDao<UUID, CpassTPbaIntervento> {
	/**
	 * Finds a CpassTPbaIntervento by its CUI
	 * @param interventoCui the intervento cui
	 * @param idProgramma the id programma
	 * @return the entity
	 */
	Optional<CpassTPbaIntervento> findByCUI(String interventoCui, UUID idProgramma, UUID enteId);

	/**
	 * Finds a CpassTPbaIntervento by its CUI
	 * @param cui the intervento cui
	 * @param programmaId the programma id
	 * @return the entity
	 */
	Optional<CpassTPbaIntervento> findInterventoRicompreso(String cui, UUID programmaId);

	/**
	 * Paginated search
	 * @param cui the cui
	 * @param annoAvvio the anno avvio
	 * @param cup the cup
	 * @param lottoFunzionale the lotto funzionale
	 * @param durataMesi the durata mesi
	 * @param nuovoAffidamento the nuovo affidamento
	 * @param descrizioneAcquisto the descrizione acquisto
	 * @param rupCognome the rup cognome
	 * @param programmaId the programma id
	 * @param ausaId the ausa id
	 * @param interventoRicompresoid the intervento ricompreso id
	 * @param interventoCopiaId 
	 * @param settoreInterventiId the settore itnerventi id
	 * @param cpvId the cpv id
	 * @param nutId the nut id
	 * @param prioritaId the priorita id
	 * @param modalitaAffidamentoId the modalita affidamento id
	 * @param statoId the stato id
	 * @param acquistoVariatoId the acquisto variato id
	 * @param ricompresoTipoId the ricompreso tipo id
	 * @param page the page
	 * @param size the size
	 * @param sortField the sort field
	 * @param sortDirection the sort direction
	 * @return the page
	 */
	Page<CpassTPbaIntervento> findPaginated(String cui,
			Integer annoAvvio,
			String cup,
			Boolean lottoFunzionale,
			Integer durataMesi,
			Boolean nuovoAffidamento,
			String descrizioneAcquisto,
			String rupCognome,
			UUID programmaId,
			Integer ausaId,
			//UUID interventoRicompresoid,
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
			);
	
	/**
	 * Gets the intervento by programma stato
	 * @param idProgramma the id programma
	 * @param statoCode the stato code
	 * @param operatoreEqualsStato the operatore equals stato
	 * @return the interventos
	 */
	List<CpassTPbaIntervento> getInterventoByProgrammaStato(UUID idProgramma, String statoCode, boolean operatoreEqualsStato);

	/**
	 * Gets the intervento esistente by programma
	 * @param id the id
	 * @param idProgramma the id programma
	 * @return the intervento
	 */
	Optional<CpassTPbaIntervento> getInterventoEsistenteProgramma(UUID id, UUID idProgramma);

	/**
	 * Finds paginated for copia
	 * @param programmaIdOld the programma id old
	 * @param programmaIdNew the programma id new
	 * @param page the page
	 * @param size the size
	 * @param queryName the query name
	 * @param sortDirection the sort direction
	 * @return a page of interventos
	 */
	Page<CpassTPbaIntervento> findPaginatedXCopia(UUID programmaIdOld, UUID programmaIdNew, int page, int size, String queryName, String sortDirection);

	/**
	 * 
	 * @param programmaAnnoPrecedente
	 * @param programmaVersioneMaxPrecedente
	 * @return
	 */
	List<CpassTPbaIntervento> getInterventiNonRiproponibiliByUltimoProgrammaPrecedente(Integer programmaAnnoPrecedente,Integer programmaVersioneMaxPrecedente);



}
