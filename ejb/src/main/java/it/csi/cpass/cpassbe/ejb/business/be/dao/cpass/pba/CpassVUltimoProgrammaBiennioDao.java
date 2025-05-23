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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseDao;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVUltimoProgrammaBiennio;

public interface CpassVUltimoProgrammaBiennioDao extends BaseDao<CpassVUltimoProgrammaBiennio> {
	List<CpassVUltimoProgrammaBiennio> getProgrammaUltimoDelBiennioById(UUID interventoId);
}



//