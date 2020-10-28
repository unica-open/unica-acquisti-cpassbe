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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import javax.enterprise.context.ApplicationScoped;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTProgressivoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProgressivo;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProgressivoPk;

/**
 * Data Access Object implementor for the entity CpassTProgressivoDaoImpl
 */
@ApplicationScoped
public class CpassTProgressivoDaoImpl extends BaseEntityDaoImpl<CpassTProgressivoPk, CpassTProgressivo> implements CpassTProgressivoDao {
	// Nothing to do
}

