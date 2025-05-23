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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.view;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVSettore;

/**
 * Data Access Object interface for the entity CpassVSettore
 */
public interface CpassVSettoreDao extends BaseEntityDao<UUID, CpassVSettore> {

	/**
	 * Gets the CPV tree
	 * @return the CPV tree
	 */
	List<CpassVSettore> getSettoreTreeByEnte(UUID enteId,String validi);

	Optional<CpassVSettore> getMySectorFather(UUID settoreId);

	List<CpassVSettore> getSettoriSonsBySettoreAndEnte(UUID settoreId,UUID enteId);

}
