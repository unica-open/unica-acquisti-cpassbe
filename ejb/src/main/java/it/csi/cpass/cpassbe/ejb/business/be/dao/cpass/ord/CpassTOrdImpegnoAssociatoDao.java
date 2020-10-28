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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord;

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoAssociato;

/**
 * Data Access Object interface for the entity CpassTPbaIntervento
 */
public interface CpassTOrdImpegnoAssociatoDao extends BaseAuditedEntityDao<UUID, CpassTOrdImpegnoAssociato> {

	/**
	 * 
	 * @param testataOrdineId
	 * @return
	 */
	List<CpassTOrdImpegnoAssociato> getImpegniAssociati(UUID testataOrdineId);

	void deleteByTestataOrdine(UUID testataOrdineId);

}
