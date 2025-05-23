/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.rda;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

public interface CpassTOrdTestataRdaDao extends BaseAuditedEntityDao<UUID, CpassTOrdTestataRda> {


	List<CpassTOrdTestataRda> findTestataRdaByOrderId(UUID orderId);

	/**
	 *
	 * @param anno
	 * @param numero
	 * @param enteId
	 * @return
	 */
	Optional<CpassTOrdTestataRda> findByAnnoENumero(Integer anno, Integer numero, UUID enteId);

	long countRicercaRda(Integer rdaNumeroDa,
			Integer rdaNumeroA,
			Integer rdaAnnoDa,
			Integer rdaAnnoA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			UUID settoreEmittenteId,
			UUID settoreDestinatarioId,
			Integer statoRdaId,
			Integer statoRigaRdaId,
			Integer oggettoSpesaId,
			Integer cpvId);

	/**
	 * Paginated search
	 * @param
	 * @return the page<CpassTOrdTestataRda>
	 */
	Page<CpassTOrdTestataRda> findPaginated(
			Integer numeroRdaDa,
			Integer numeroRdaA,
			Integer annoRdaDa,
			Integer annoRdaA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			UUID settoreEmittenteId,
			UUID settoreDestinatarioId,
			Integer statoRdaId,
			Integer statoRigaRdaId,
			Integer oggettoSpesaId,
			Integer cpvId,
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
}
