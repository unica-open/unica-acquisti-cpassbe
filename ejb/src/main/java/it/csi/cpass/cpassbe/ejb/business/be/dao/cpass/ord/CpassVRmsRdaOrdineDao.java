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
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVRmsRdaOrdine;

/**
 * Data Access Object interface for the entity CpassVOrdineDao
 */
public interface CpassVRmsRdaOrdineDao extends BaseEntityDao<Integer, CpassVRmsRdaOrdine> {

	BigDecimal getQuantitaNonEvasaSuOrdine(UUID rigaRmsId);

	BigDecimal getQuantitaEvasaSuAltriOrdini(UUID rigaRmsId,UUID testataOrdineId);



}
