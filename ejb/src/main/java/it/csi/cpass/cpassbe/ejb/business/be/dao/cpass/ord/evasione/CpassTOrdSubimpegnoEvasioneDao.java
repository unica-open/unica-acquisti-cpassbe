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
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdSubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

/**
 * Data Access Object interface for the entity CpassTOrdSubimpegnoEvasione
 */
public interface CpassTOrdSubimpegnoEvasioneDao extends BaseAuditedEntityDao<UUID, CpassTOrdSubimpegnoEvasione> {

	public List<CpassTOrdSubimpegnoEvasione> getSubimpegni(UUID impegnoEvasioneId);

	@Override
	public void delete(UUID idSubimpegnoEvasione);

	public BigDecimal calcolaTotaleEvaso(UUID subimpegnoOrdineId);

	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero, Integer subimpegnoAnno, Integer subimpegnoNumero, UUID testataEvasioneId);

	public List<CpassTOrdSubimpegnoEvasione> findByIdSubimpegnoOrdine(UUID subimpegnoOrdineId);

	List<CpassTOrdSubimpegnoEvasione> findByIdsRigaEvasioneESubimpegno(List<RigaEvasione> rigaEvasiones, Integer impegnoAnno, Integer impegnoNumero,Integer subimpegnoAnno, Integer subimpegnoNumero);

	public BigDecimal calcolaSubimpegnoEvaso(Integer annoEsercizio, Integer anno, Integer numero, Integer anno2,
			Integer numero2, Integer filtroAnnoEsercizio, UUID enteId,Integer statoId);


}
