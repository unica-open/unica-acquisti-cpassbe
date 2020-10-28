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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;

/**
 * Data Access Object interface for the entity CpassTOrdImpegnoOrdine
 */
public interface CpassTOrdImpegnoOrdineDao extends BaseAuditedEntityDao<UUID, CpassTOrdImpegnoOrdine> {

	public List<CpassTOrdImpegnoOrdine> getImpegniCollegati(UUID testataOrdineId);
	
	public BigDecimal calcolaOrdinato(UUID impegnoId, UUID testataOrdineId);
	
	public List<CpassTOrdImpegnoOrdine> getImpegni(UUID rigaOrdineId);
	public List<CpassTOrdImpegnoOrdine> getImpegniNonPresentiEvasione(UUID rigaOrdineId, UUID rigaEvasioneId);
	
	public void deleteByRiga(UUID rigaOrdineId);
	
}
