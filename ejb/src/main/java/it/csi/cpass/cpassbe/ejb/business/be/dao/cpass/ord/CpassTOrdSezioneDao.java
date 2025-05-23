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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassTOrdSezioneDao
 */
public interface CpassTOrdSezioneDao extends BaseEntityDao<Integer, CpassTOrdSezione> {

	List<CpassTOrdSezione> getSezioniByEnte(UUID enteId);
	List<CpassTOrdSezione> getSezioniByUtente(UUID settoreId, UUID utenteId);
	Page<CpassTOrdSezione> findPaginated(String codice, String descrizione, UUID enteId, int page, int size,String sortField, String sortDirection);
	List<CpassTOrdSezione> getSezioniBySettoreId(UUID settoreId);

}
