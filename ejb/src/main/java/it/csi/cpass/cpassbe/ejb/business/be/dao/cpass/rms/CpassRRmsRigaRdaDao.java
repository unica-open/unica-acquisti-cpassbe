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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.rms;


import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassRRmsRigaRda;

public interface CpassRRmsRigaRdaDao extends BaseEntityDao<Integer, CpassRRmsRigaRda> {
	public void deleteByRdaId(UUID rigaRdaId);
	public List<CpassRRmsRigaRda> getRRmsRdaRmsByRigheRdaRmsId(UUID rigaRdaId,UUID rigaRmsId);

}
