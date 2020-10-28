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
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTComunicazioneDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTParametroDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTComunicazione;
import it.csi.cpass.cpassbe.ejb.entity.CpassTParametro;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Comunicazione;
import it.csi.cpass.cpassbe.lib.dto.Parametro;

/**
 * Data Access Delegate for system
 */
@ApplicationScoped
public class SystemDad extends BaseDad {

	@Inject
	private CpassTComunicazioneDao cpassTComunicazioneDao;
	@Inject
	private CpassTParametroDao cpassTParametroDao;

	/**
	 * Returns the Comunicaziones
	 * 
	 * @return the comunicaziones
	 */
	public List<Comunicazione> getComunicaziones() {
		Date now = new Date();
		List<CpassTComunicazione> entities = cpassTComunicazioneDao.findActive(now);
		return CpassMappers.COMUNICAZIONE.toModels(entities);
	}

	/**
	 * Parametro
	 * 
	 * @param chiave
	 * @param enteId
	 * @return
	 */
	public Parametro getParametro(String chiave, String riferimento, UUID enteId) {
		CpassTParametro entity = cpassTParametroDao.getParametro(chiave, riferimento, enteId);
		return entity != null ? CpassMappers.PARAMETRO.toModel(entity) : null;
	}

	/**
	 * Get parametri
	 * 
	 * @param chiave
	 * @param riferimento
	 * @param ambiente
	 * @param enteId
	 * @return
	 */
	public List<Parametro> getParametriList(String chiave, String riferimento, String ambiente, UUID enteId) {
		List<CpassTParametro> list = cpassTParametroDao.getParametriByChiaveRiferimentoAndAmbiente(chiave, riferimento, ambiente, enteId);
		return CpassMappers.PARAMETRO.toModels(list);
	}

	/**
	 * Gets
	 * 
	 * @param riferimento
	 * @param ambiente
	 * @return i parametri
	 */
	public Map<String, String> getParametri(String riferimento, String ambiente, UUID enteId) {
		List<CpassTParametro> list = cpassTParametroDao.getParametriByRiferimentoAndAmbiente(riferimento, ambiente, enteId);

		Map<String, String> parametri = new HashMap<>();
		for (CpassTParametro cpassTParametro : list) {
			parametri.put(cpassTParametro.getChiave(), cpassTParametro.getValore());
		}

		return parametri;
	}

}
