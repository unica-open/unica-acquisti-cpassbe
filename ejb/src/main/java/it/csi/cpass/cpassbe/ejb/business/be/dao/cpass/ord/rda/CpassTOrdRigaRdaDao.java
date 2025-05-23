/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.rda;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdRigaRda;

public interface CpassTOrdRigaRdaDao extends BaseAuditedEntityDao<UUID, CpassTOrdRigaRda> {

	List<CpassTOrdRigaRda> getRigaRdaByRmsIdAndTestataRda(UUID rigaRmsId, UUID testataRdaId);

}
