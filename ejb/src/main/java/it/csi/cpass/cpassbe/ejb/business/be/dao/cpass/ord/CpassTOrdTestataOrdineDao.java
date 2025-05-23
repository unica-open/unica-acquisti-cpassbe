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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Data Access Object interface for the entity CpassTOrdTestataOrdine
 */
public interface CpassTOrdTestataOrdineDao extends BaseAuditedEntityDao<UUID, CpassTOrdTestataOrdine> {

	/**
	 *
	 * @param anno
	 * @param numero
	 * @param enteId
	 * @return
	 */
	Optional<CpassTOrdTestataOrdine> findByAnnoENumero(Integer anno, Integer numero, UUID enteId);

	/**
	 *
	 * @param listaId
	 * @return List<CpassTOrdTestataOrdine>
	 */
	List<CpassTOrdTestataOrdine> findOrdineByIds(List<UUID> listaId);

	/**
	 * Paginated search
	 * @param
	 * @return the page
	 */
	Page<CpassTOrdTestataOrdine> findPaginated(Integer annoOrdineDa,
			Integer numeroOrdineDa,
			Integer annoOrdineA,
			Integer numeroOrdineA,
			Date dataEmissioneDa,
			Date dataEmissioneA,
			Integer tipoOrdineId,
			Integer statoOrdineId,
			Integer statoNsoId,
			Integer lottoAnno,
			Integer lottoNumero,
			Integer tipoProceduraId,
			String numeroProcedura,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId,
			Integer cpvId,
			Settore settoreEmittente,
			Integer settoreIndirizzoId,
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

	long countRicercaOrdini(Integer annoOrdineDa,
			Integer numeroOrdineDa,
			Integer annoOrdineA,
			Integer numeroOrdineA,
			Date dataEmissioneDa,
			Date dataEmissioneA,
			Integer tipoOrdineId,
			Integer statoOrdineId,
			Integer statoNsoId,
			Integer lottoAnno,
			Integer lottoNumero,
			Integer tipoProceduraId,
			String numeroProcedura,
			UUID strutturaEmittenteId,
			UUID strutturaDestinatarioId,
			UUID fornitoreId,
			Integer provvedimentoAnno,
			String provvedimentoNumero,
			UUID impegnoId,
			UUID subimpegnoId,
			Integer oggettoSpesaId,
			Integer cpvId,
			Settore settoreEmittente,
			Integer settoreIndirizzoId);

	/**
	 *
	 * @param evasioneId
	 * @return List<CpassTOrdTestataOrdine
	 */
	List<CpassTOrdTestataOrdine> findTestateOrdineByEvasioneId(UUID evasioneId);

	/**
	 * Ottiene le sole righe ordine collegate ad un evasione e ad un ordine
	 * @param ordineId l'id ordine
	 * @param evasioneId l'id evasione
	 * @return List<CpassTOrdRigaOrdine
	 */
	public List<CpassTOrdRigaOrdine> findRigheByOrdineAndEvasioneId(UUID ordineId, UUID evasioneId);

	/**
	 *
	 * @param destinatarioEvasioneId
	 * @return List<CpassTOrdTestataOrdine
	 */
	List<CpassTOrdTestataOrdine> findTestateOrdineByDestinatarioEvasioneId(UUID destinatarioEvasioneId);

	/**
	 *
	 * @param rigaEvasioneId
	 * @return List<CpassTOrdTestataOrdine
	 */
	List<CpassTOrdTestataOrdine> findTestateOrdineByRigaEvasioneId(UUID rigaEvasioneId);

	/**
	 *
	 * @param anno
	 * @param numero
	 * @param stato
	 * @param enteId
	 * @return
	 */
	Optional<CpassTOrdTestataOrdine> findByAnnoENumeroEStato(Integer anno, Integer numero,String stato, UUID enteId);
	/**
	 * oggetti spesa associati all'ordine
	 * @param testataOrdineId
	 * @return count degli ods legati all'ordine
	 */
	public Integer countOggettiSpesaLegatiAOrdine(UUID testataOrdineId);

	/**
	 * ritorna le testate in base all'anno e/o al numero ordine
	 * @param anno
	 * @param numero
	 * @return
	 */
	List<CpassTOrdTestataOrdine> findTestataByAnnoOrNumero(Integer anno, Integer numero);

	List<CpassTOrdTestataOrdine> findTestataOrdineByRdaId(UUID testataRdaId);
	List<CpassTOrdTestataOrdine> findByStato(String stato, UUID enteId);

	void updateTestataOrdine(UUID testataOrdineId);

}
