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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;

/**
 * Data Access Object interface for the entity CpassTFornitore
 */
public interface CpassTFornitoreDao extends BaseAuditedEntityDao<UUID, CpassTFornitore> {

	/**
	 * Finds a Fornitore
	 */
	CpassTFornitore getFornitoreByCodice(String codice, UUID enteId);

	List<CpassTFornitore> getFornitore (String codice, String codiceFiscale, String partitaIva, String ragioneSociale, UUID enteId);


	//	Page<CpassTFornitore> findPaginated(String codice, String codiceFiscale, String partitaIva, String ragioneSociale, int page, int size, String sortField, String sortDirection);
}
