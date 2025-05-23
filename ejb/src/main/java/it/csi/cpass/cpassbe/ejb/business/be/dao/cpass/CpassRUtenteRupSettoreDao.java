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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassRUtenteRupSettore;

public interface CpassRUtenteRupSettoreDao extends BaseEntityDao<Integer, CpassRUtenteRupSettore> {
	public void deleteLogicallyByUtenteId(UUID utenteId, UUID settoreId);

	public List<CpassRUtenteRupSettore> getRupBySettoreUtenteAttivi(UUID settoreId, UUID utenteId,Date dataValiditaFine);
	public List<CpassRUtenteRupSettore> getRupBySettoreUtenteChiusiInData(UUID settoreId, UUID utenteId,Date dataValiditaFine);
}
