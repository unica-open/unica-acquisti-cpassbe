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

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVRiepilogoFatturaEvasione;

/**
 * Data Access Object interface for the entity CpassTPbaIntervento
 */
public interface CpassVRiepilogoFatturaEvasioneDao extends BaseEntityDao<Long, CpassVRiepilogoFatturaEvasione> {

	List<CpassVRiepilogoFatturaEvasione> getRiepilogoFatturaByIdEvasione(UUID testataEvasioneId);

}
