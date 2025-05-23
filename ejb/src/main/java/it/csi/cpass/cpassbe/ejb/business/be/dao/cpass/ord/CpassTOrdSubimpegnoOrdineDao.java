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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;

/**
 * Data Access Object interface for the entity CpassTOrdSubimpegnoOrdine
 */
public interface CpassTOrdSubimpegnoOrdineDao extends BaseAuditedEntityDao<UUID, CpassTOrdSubimpegnoOrdine> {

	// TODO logica non coerente con rispettivo calcolo ordinato su impegno
	// verificare se corretto
	public BigDecimal calcolaSubimpegnoOrdinato(UUID subimpegnoId,Integer impegnoAnnoEsercizio);

	public List<CpassTOrdSubimpegnoOrdine> getSubimpegni(UUID impegnoOrdineId);

	@Override
	public void delete(UUID idSubimpegnoOrdine);

	public BigDecimal calcolaOrdinatoSubImpegno(Integer annoEsercizio, Integer annoImpegno, Integer numeroImpegno,Integer annoSub,Integer numeroSub, Integer annoEsercizioSuOrdine,UUID enteId);

	public void deleteFromRiga(UUID idRiga);

	public void deleteFromTestataordine(UUID testataOrdineId);

	public void updateFromTestataordine(UUID testataOrdineId);

}
