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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaAcquistiCapPrivatiDaTrasmettere;

/**
 * Data Access Object interface for the entity extends CpassTPbaAcquistiDaTrasmettereDaoImpl
 */
public interface CpassTPbaAcquistiCapPrivatiDaTrasmettereDao extends BaseEntityDao<Integer, CpassTPbaAcquistiCapPrivatiDaTrasmettere> {

	void deleteByProgrammaId(UUID programmaId);

	List<CpassTPbaAcquistiCapPrivatiDaTrasmettere> getAcquistiDaTrasmettereCapPrivatoByIntgerventoId(UUID interventoId);

}
