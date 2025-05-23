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
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTParametroStampaDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTParametroStampa;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ParametroStampa;

/**
 * Data Access Delegate for interventos
 */
@ApplicationScoped
public class StampeDad extends BaseDad {

	@Inject private CpassTParametroStampaDao cpassTparametroStampaDao;

	/**
	 * Gets the parametri stampa by nome stampa
	 * @param nomeStampa the nome stampa
	 * @return the parametri
	 */
	public List<ParametroStampa> getParametriStampeByNomeStampa(String nomeStampa) {
		final List<CpassTParametroStampa> entities = cpassTparametroStampaDao.getParametriStampeByNomeStampa(nomeStampa);
		return CpassMappers.PARAMETRO_STAMPA.toModels(entities);
	}

}
