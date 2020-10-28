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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.view.CpassVCpv;

/**
 * Data Access Object interface for the entity CpassDCpv
 */
public interface CpassVCpvDao extends BaseEntityDao<Long, CpassVCpv> {
	/**
	 * Gets the CPV tree
	 * @return the CPV tree
	 */
	List<CpassVCpv> getTreeCpvs();
}
