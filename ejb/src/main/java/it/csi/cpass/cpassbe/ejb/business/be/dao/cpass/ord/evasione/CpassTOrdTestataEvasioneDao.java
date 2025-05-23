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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdTestataEvasione;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassTOrdTestataEvasione
 */
public interface CpassTOrdTestataEvasioneDao extends BaseAuditedEntityDao<UUID, CpassTOrdTestataEvasione> {


	Optional<CpassTOrdTestataEvasione> findByAnnoENumero(Integer anno, Integer numero, UUID enteId);
	long countRicercaEvasioni(
			Integer annoEvasioneDa,
			Integer numeroEvasioneDa,
			Integer annoEvasioneA,
			Integer numeroEvasioneA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			Integer annoOrdineDa,
			Integer numeroOrdineDa,
			Integer annoOrdineA,
			Integer numeroOrdineA,
			Date dataEmissioneDa,
			Date dataEmissioneA,
			Integer tipoEvasioneId,
			Integer statoEvasioneId,
			UUID strutturaCompetenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			String provvedimentoTipo,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId);

	/**
	 * Paginated search
	 * @param
	 * @return the page
	 */
	Page<CpassTOrdTestataEvasione> findPaginated(
			Integer annoEvasioneDa,
			Integer numeroEvasioneDa,
			Integer annoEvasioneA,
			Integer numeroEvasioneA,
			Date dataInserimentoDa,
			Date dataInserimentoA,
			Integer annoOrdineDa,
			Integer numeroOrdineDa,
			Integer annoOrdineA,
			Integer numeroOrdineA,
			Date dataEmissioneDa,
			Date dataEmissioneA,
			Integer tipoEvasioneId,
			Integer statoEvasioneId,
			UUID strutturaCompetenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			String provvedimentoTipo,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId,
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

	List<CpassTOrdTestataEvasione> findTestateEvasioniByFattura(Integer anno, String numero, String tipo, String codice);

	Optional<CpassTOrdTestataEvasione> getTestataEvasioneByDestinatario(UUID idDest);

}
