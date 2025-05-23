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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

public interface CpassTRmsRigaRmsDao extends BaseAuditedEntityDao<UUID, CpassTRmsRigaRms> {
	List<CpassTRmsRigaRms> findByTestataRms(UUID testataRmsId, Integer progressivo);
	void deleteRigaByTestataRms(UUID idTestataRms);

	/**
	 * Paginated search
	 * @param
	 * @return the page<CpassTRmsRigaRms>
	 */
	Page<CpassTRmsRigaRms> getRicercaRigheRms(
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
			List <Integer> statiRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			UUID settoreAcquisto,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			UUID enteId,
			Boolean flgNonCompetenza
			);

	/**
	 * Paginated search
	 * @param
	 * @return the page<getRicercaRigheRmsDaEvadere>
	 */
	Page<CpassTRmsRigaRms> getRicercaRigheRmsDaEvadere(
			Integer numeroRmsDa,
			Integer numeroRmsA,
			Integer annoRmsDa,
			Integer annoRmsA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			Date dataAutorizzazioneDa,
			Date dataAutorizzazioneA,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			Boolean richiestaMagazzino,
			Integer statoRmsId,
			List<Integer> statiRigaRmsId,
			BigDecimal sogliaQuantita,
			Integer sezioneId,
			//Integer statoRigaRmsId,
			Integer oggettoSpesaId,
			Integer cpvId,
			UUID settoreAcquisto,
			int page,
			int size,
			String sortField,
			String sortDirection,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId,
			UUID enteId
			);
	/**
	 *
	 * @param rigaRdaId
	 * @return
	 */
	List<CpassTRmsRigaRms> getRigaRmsByRigaRdaId(UUID rigaRdaId);

	/**
	 *
	 * @param rdaId
	 * @return
	 */
	List<CpassTRmsRigaRms> getRigheRmsByRdaId(UUID rdaId);
	/**
	 *
	 * @param testataRmsId
	 * @return
	 */
	List<CpassTRmsRigaRms> getRigheRmsByTestataId(UUID testataRmsId);
}
