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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProvvedimento;

/**
 * Data Access Object interface for the entity CpassTProvvedimento
 */
public interface CpassTProvvedimentoDao extends BaseEntityDao<Integer, CpassTProvvedimento> {
	List<CpassTProvvedimento> getProvvedimentoByAnnoNumeroTipo(Integer anno, String numero, Integer tipoId, List<UUID> settoriId ,UUID enteId);
}
