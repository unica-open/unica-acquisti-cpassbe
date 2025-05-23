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
import it.csi.cpass.cpassbe.ejb.entity.CpassRDirigenteSettore;

public interface CpassRDirigenteSettoreDao extends BaseEntityDao<Integer, CpassRDirigenteSettore> {

	public List<CpassRDirigenteSettore> findByRDirigenteSettore(UUID utenteId, UUID settoreId,Date dataFine);

	public List<CpassRDirigenteSettore> findByRAltriDirigentiSuStessoSettore(UUID utenteId, UUID settoreId,Date dataValiditaFine);

	public void deleteLogicallyByUtenteId(UUID utenteId,UUID settoreId,Date dataValiditaFine);

}
