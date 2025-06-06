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
package it.csi.cpass.cpassbe.ejb.business.be.dao.cpass;

import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTComunicazione;

/**
 * Data Access Object interface for the entity CpassTComunicazione
 */
public interface CpassTComunicazioneDao extends BaseEntityDao<Integer, CpassTComunicazione> {

	/**
	 * Finds the active communications at a specified date
	 * @param checkDate the check date
	 * @return the active communications
	 */
	List<CpassTComunicazione> findActive(Date checkDate);

}
