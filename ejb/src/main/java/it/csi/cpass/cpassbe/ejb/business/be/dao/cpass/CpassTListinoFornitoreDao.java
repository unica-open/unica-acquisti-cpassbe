/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Data Access Object interface for the entity CpassTListaFornitore
 */
public interface CpassTListinoFornitoreDao extends BaseEntityDao<Integer, CpassTListinoFornitore> {

	Page<CpassTListinoFornitore> findPaginated(
			String codiceOds,
			String descrizione,
			UUID fornitoreId,
			Integer oggettiSpesaId,
			int page,
			int size, String sortField, String sortDirection);

	
}
