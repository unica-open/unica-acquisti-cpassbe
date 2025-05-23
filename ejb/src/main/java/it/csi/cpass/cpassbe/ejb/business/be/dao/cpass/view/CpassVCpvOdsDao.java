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

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpvOds;

/**
 * Data Access Object interface for the entity CpassDCpv
 */
public interface CpassVCpvOdsDao extends BaseEntityDao<Long, CpassVCpvOds> {
	/**
	 * Gets the CPV tree
	 * @return the CPV tree
	 */
	List<CpassVCpvOds> getTreeCpvs();
}
