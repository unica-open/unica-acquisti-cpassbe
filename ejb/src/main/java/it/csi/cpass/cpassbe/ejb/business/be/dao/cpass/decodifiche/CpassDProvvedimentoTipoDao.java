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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDProvvedimentoTipo;

/**
 * Data Access Object interface for the entity CpassTProvvedimentoTipo
 */
public interface CpassDProvvedimentoTipoDao extends BaseEntityDao<Integer, CpassDProvvedimentoTipo> {
	Optional<CpassDProvvedimentoTipo> findByCodice(String codice ,UUID enteId);
	List<CpassDProvvedimentoTipo> findAllByEnte(UUID enteId);
}
