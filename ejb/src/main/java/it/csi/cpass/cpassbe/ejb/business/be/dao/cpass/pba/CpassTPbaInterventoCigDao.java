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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoCig;

/**
 * Data Access Object interface for the entity CpassTPbaInterventoCig
 */
public interface CpassTPbaInterventoCigDao extends BaseEntityDao<Integer, CpassTPbaInterventoCig> {
	List<CpassTPbaInterventoCig> getCigByInterventoId(UUID interventoId);

	void deleteByIdIntervento(UUID id);

	void deleteByInterventoIdAndCig(UUID interventoId, String cig);
}
