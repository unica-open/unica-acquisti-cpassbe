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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTImpegno;

/**
 * Data Access Object interface for the entity CpassTPbaIntervento
 */
public interface CpassTImpegnoDao extends BaseAuditedEntityDao<UUID, CpassTImpegno> {

	/**
	 * Finds a Impegno
	 */
	CpassTImpegno getImpegnoByChiaveLogica(Integer annoEsercizio, Integer anno, Integer numero, UUID enteId);
}
