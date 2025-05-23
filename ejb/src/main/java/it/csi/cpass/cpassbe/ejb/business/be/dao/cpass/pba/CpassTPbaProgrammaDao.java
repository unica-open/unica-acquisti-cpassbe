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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassTPbaInterventoImporti
 */
public interface CpassTPbaProgrammaDao extends BaseAuditedEntityDao<UUID, CpassTPbaProgramma> {

	/**
	 * Finds the page relative to the given params
	 * @param programmaAnno the anno
	 * @param numeroProvvedimento the numero provvedimento
	 * @param descrizioneProvvedimento the descrizione provvedimento
	 * @param dataProvvedimento the data provvedimento
	 * @param dataPubblicazione the data pubblicazione
	 * @param url the url
	 * @param page the page
	 * @param size the size
	 * @return the paged results
	 */
	Page<CpassTPbaProgramma> findPaginated(
			Integer programmaAnno
			,String numeroProvvedimento
			,String descrizioneProvvedimento
			,Date dataProvvedimento
			,Date dataPubblicazione
			,String url
			,int page
			,int size
			);

	/**
	 * @param anno
	 * @param enteId
	 * @param statoCodice
	 * @return Optional<Integer>
	 */
	public Integer getMaxVersioneProgrammaByAnnoEnteStato(Integer anno, UUID enteId, String statoCodice);

	/**
	 * @param anno
	 * @param enteId
	 * @param statoCodice
	 * @return Optional<Integer>
	 */
	public Integer getMaxVersioneProgrammaByAnnoEnteStato(Integer anno, UUID enteId, String statoCodice, Boolean solovalidi);

	/**
	 *
	 * @param settoreId
	 * @param anno
	 * @param programmaVersione
	 * @param statoCode
	 * @param solovalidi
	 * @return List<CpassTPbaProgramma>
	 */
	List<CpassTPbaProgramma> getProgrammiBySettoreAnnoVersioneStato(UUID settoreId, Integer anno, Integer programmaVersione,List<String> listaStatoCodice,  Boolean solovalidi);

	/**
	 *
	 * @param anno
	 * @param programmaVersione
	 * @param enteId
	 * @param statoCodice
	 * @param solovalidi
	 * @return List<CpassTPbaProgramma>
	 */
	List<CpassTPbaProgramma> getProgrammiByAnnoVersioneEnteStato(Integer anno,Integer annoFineProgramma, Integer programmaVersione, UUID enteId, String statoCodice, Boolean solovalidi, String sortField,
			String sortDirection);

	/**
	 *
	 * @return the programmi trasmissione MIT
	 */
	List<CpassTPbaProgramma> getProgrammiTrasmissioneMIT(UUID enteId);

	List<CpassTPbaProgramma> getProgrammiConfermatiDaTrasmettere(UUID enteId);
}
