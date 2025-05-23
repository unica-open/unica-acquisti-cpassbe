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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.mag;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.mag.CpassTMagMagazzino;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassTMagMagazzino
 */
public interface CpassTMagMagazzinoDao extends BaseEntityDao<Integer, CpassTMagMagazzino> {
	List<CpassTMagMagazzino> getMagazziniByEnteId(UUID enteId);
	Page<CpassTMagMagazzino> findPaginated(String codice, String descrizione, UUID id, int page, int size,String sortField, String sortDirection);
}
