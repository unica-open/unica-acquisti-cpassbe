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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.decodifiche;

import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassDElaborazioneTipo;

/**
 * Data Access Object interface for the entity CpassDElaborazioneTipo
 */
public interface CpassDElaborazioneTipoDao extends BaseEntityDao<Integer, CpassDElaborazioneTipo> {

	public Optional<CpassDElaborazioneTipo> findByElaborazioneTipoCodice(String elaborazioneTipoCodice);

}
