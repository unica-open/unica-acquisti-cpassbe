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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSubimpegno;

/**
 * Data Access Object interface for the entity CpassTPbaIntervento
 */
public interface CpassTSubimpegnoDao extends BaseAuditedEntityDao<UUID, CpassTSubimpegno> {

	/**
	 * Finds a Subimpegno
	 */
	List<CpassTSubimpegno> getSubimpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId, Integer subimpegnoAnno, Integer subimpegnoNumero);

}
