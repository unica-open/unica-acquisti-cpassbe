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
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdDestinatarioOrdine;

public interface CpassTOrdDestinatarioOrdineDao extends BaseAuditedEntityDao<UUID, CpassTOrdDestinatarioOrdine> {

	public List<CpassTOrdDestinatarioOrdine> findByOrdine(UUID idOrdine);

	public List<CpassTOrdDestinatarioOrdine> getDestinatarioByTestataAndProgressivo(UUID idTestata,Integer progressivoDestinatario);

	public void deleteFromTestataordine(UUID testataOrdineId);

	public void updateFromTestataordine(UUID testataOrdineId);

}
