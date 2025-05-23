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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsTestataRms;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.rms.TestataRms;

/**
 * Data Access Object interface for the entity CpassTRmsTestataRms
 */
public interface CpassTRmsTestataRmsDao extends BaseAuditedEntityDao<UUID, CpassTRmsTestataRms> {

	/**
	 *
	 * @param listaId
	 * @return List<CpassTRmsTestataRms>
	 */
	List<CpassTRmsTestataRms> findRmsByIds(List<UUID> listaId);

	/**
	 *
	 * @param anno
	 * @param numero
	 * @param enteId
	 * @return
	 */
	Optional<CpassTRmsTestataRms> findByAnnoENumero(Integer anno, Integer numero, UUID enteId);


	/**
	 * Paginated search
	 * @param
	 * @return the page<CpassTRmsTestataRms>
	 */
	Page<CpassTRmsTestataRms> findPaginated(
			Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			Boolean richiestaMagazzino,
			Integer statoRmsId,
			Integer statoRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			Settore settoreEmittente,
			Settore settore,
			SettoreIndirizzo settoreIndirizzo,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			UUID enteId,
			TestataRms testataRms
			);

	/**
	 *
	 * @param rigaRmsId
	 * @return List<CpassTRmsTestataRms>
	 */
	List<CpassTRmsTestataRms> findTestataRmsByRigaRmsId(UUID rigaRmsId);

	long countRicercaRms(Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			Boolean richiestaMagazzino,
			Integer statoRmsId,
			Integer statoRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			Settore settoreEmittente,
			Settore settore,
			SettoreIndirizzo settoreIndirizzo,
			UUID    enteId,
			TestataRms testataRms
			);
	/**
	 *
	 * @param rdaId
	 * @return List<CpassTRmsTestataRms>
	 */
	//public List<CpassTRmsTestataRms> findRmsByRdaId(UUID rdaId);

	void deleteStatiRigheByTestataRms(UUID testataRmsId);
}
