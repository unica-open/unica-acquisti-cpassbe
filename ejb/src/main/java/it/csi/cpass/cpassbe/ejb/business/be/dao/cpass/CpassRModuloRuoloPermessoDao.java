/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassRModuloRuoloPermesso;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

public interface CpassRModuloRuoloPermessoDao extends BaseEntityDao<Integer, CpassRModuloRuoloPermesso> {
	Page<CpassRModuloRuoloPermesso> getPermessiSelezionabiliNonAttivi(Integer moduloId, int page, int size, String sortField, String sortDirection);

	List<CpassRModuloRuoloPermesso> getRuoloPermessoNonAttiviList(Integer moduloId);
}
