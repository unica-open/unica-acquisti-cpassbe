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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioInvioNso;

/**
 * Data Access Object interface for the entity CpassTOrdDestinatarioInvioNso
 */
public interface CpassTOrdDestinatarioInvioNsoDao extends BaseEntityDao<Integer, CpassTOrdDestinatarioInvioNso> {

	Optional<CpassTOrdDestinatarioInvioNso> getUltimoInvioByDestinatario(UUID id);

	Optional<CpassTOrdDestinatarioInvioNso> getUltimoInvioByOrdine(UUID id);

	List<CpassTOrdDestinatarioInvioNso> findByIdNotier(String idNotier);

}
