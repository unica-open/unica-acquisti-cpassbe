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
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTParametro;

/**
 * Data Access Object interface for the entity CpassTParametro
 */
public interface CpassTParametroDao extends BaseEntityDao<Integer, CpassTParametro> {

	/**
	 * Finds a parametro
	 * 
	 * @param chiave the chiave
	 * @return the parametro
	 */
	CpassTParametro getParametro(String chiave, String riferimento, UUID enteId);

	/**
	 * Finds parametri
	 * 
	 * @param riferimento the riferimento
	 * @param ambiente the ambiente
	 * @return the parametri
	 */
	List<CpassTParametro> getParametriByRiferimentoAndAmbiente(String riferimento, String ambiente, UUID enteId);
	
	/**
	 * Finds parametri
	 * 
	 * @param chiave
	 * @param riferimento the riferimento
	 * @param ambiente the ambiente
	 * @return the parametri
	 */
	List<CpassTParametro> getParametriByChiaveRiferimentoAndAmbiente(
			String chiave,
			String riferimento,
			String ambiente,
			UUID enteId);

}
