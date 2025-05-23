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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

/**
 * Data Access Object interface for the entity CpassTOrdImpegnoEvasione
 */
public interface CpassTOrdImpegnoEvasioneDao extends BaseAuditedEntityDao<UUID, CpassTOrdImpegnoEvasione> {

	public List<CpassTOrdImpegnoEvasione> getImpegniEvasione(UUID idRigaEvasione);

	public void deleteByRigaEvasione(UUID rigaEvasioneId);

	// public BigDecimal calcolaOrdinato(UUID impegnoId, UUID testataOrdineId);

	List<CpassTOrdImpegnoEvasione> findByIdRigaEvasione(UUID idRigaEvasione);

	List<CpassTOrdImpegnoEvasione> findByIdImpegnoOrdine(UUID impegnoOrdineId);

	BigDecimal calcolaTotaleEvaso(UUID impegnoOrdineId);

	BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero,UUID testataEvasioneId);

	List<CpassTOrdImpegnoEvasione> findByIdsRigaEvasioneEImpegno(List<RigaEvasione> rigaEvasiones, Integer impegnoAnno, Integer impegnoNumero);

	BigDecimal calcolaImpegnoEvaso(Integer annoEsercizio, Integer anno, Integer numero,Integer filtroAnnoEsercizio, UUID enteId,Integer statoInContId);

}
