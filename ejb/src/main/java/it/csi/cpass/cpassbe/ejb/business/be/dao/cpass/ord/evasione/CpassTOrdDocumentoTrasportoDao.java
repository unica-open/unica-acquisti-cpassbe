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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasporto;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

public interface CpassTOrdDocumentoTrasportoDao extends BaseEntityDao<Integer, CpassTOrdDocumentoTrasporto> {

	Page<CpassTOrdDocumentoTrasporto> ricercaDocumentoTrasporto (
			List<String> idNotierList,
			List<UUID> idOrdineList,
			Integer idStato,
			Date dataConsegna,
			UUID idFornitore,
			Integer page,
			Integer size,
			String sortField,
			String sortDirection
			);

	CpassTOrdDocumentoTrasporto findDocumentoTrasportoByIdEvasione(UUID idEvasione);

}
