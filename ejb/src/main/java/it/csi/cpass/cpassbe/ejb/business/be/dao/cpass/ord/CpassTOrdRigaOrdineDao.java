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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;

public interface CpassTOrdRigaOrdineDao extends BaseAuditedEntityDao<UUID, CpassTOrdRigaOrdine> {
	
	List<CpassTOrdRigaOrdine> findByIdDestinatario(UUID idDestinatario);
	
	void deleteByDestinatario(UUID idDestinatario);
	
	List<CpassTOrdRigaOrdine> findByTestataOrdine(UUID testataOrdineId, UUID rigaOrdineId);
	
	public long countDaEvadere(Integer annoOrdineDa, 
			Integer numeroOrdineDa, 
			Integer annoOrdineA, 
			Integer numeroOrdineA, 
			Date dataEmissioneDa, 
			Date dataEmissioneA,
			Integer tipoOrdineId,
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
			List<Integer> oggettoSpesaId,
			Integer cpvId);

	List<CpassTOrdRigaOrdine> findDaEvadere(Integer annoOrdineDa, 
			Integer numeroOrdineDa, 
			Integer annoOrdineA, 
			Integer numeroOrdineA, 
			Date dataEmissioneDa, 
			Date dataEmissioneA,
			Integer tipoOrdineId,
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
			List<Integer> oggettoSpesaId,
			Integer cpvId,
			boolean checkVisibilitaDocumentale,
			String cfUtente,
			UUID utenteId,
			UUID settoreId);

}
