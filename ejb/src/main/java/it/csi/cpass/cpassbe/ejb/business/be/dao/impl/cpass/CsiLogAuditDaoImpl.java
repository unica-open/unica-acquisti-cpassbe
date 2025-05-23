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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass;

import javax.enterprise.context.ApplicationScoped;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CsiLogAuditDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.CsiLogAudit;



@ApplicationScoped
public class CsiLogAuditDaoImpl extends BaseEntityDaoImpl<Integer, CsiLogAudit> implements CsiLogAuditDao {


}
