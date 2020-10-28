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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl.cpass.ord.evasione;

import javax.enterprise.context.ApplicationScoped;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdDocumentoTrasportoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdDocumentoTrasporto;

//public interface CpassTOrdDocumentoTrasportoDaoImpl extends BaseEntityDao<Integer, CpassTOrdDocumentoTrasporto> {

@ApplicationScoped
public class CpassTOrdDocumentoTrasportoDaoImpl extends BaseEntityDaoImpl<Integer, CpassTOrdDocumentoTrasporto> implements CpassTOrdDocumentoTrasportoDao {

}
