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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTProvvedimentoDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProvvedimento;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;

/**
 * Data Access Delegate for interventos
 */
@ApplicationScoped
public class ProvvedimentoDad extends BaseDad {

	@Inject private CpassTProvvedimentoDao cpassTProvvedimentoDao;

	/**
	 * Gets the parametri stampa by nome stampa
	 * @param nomeStampa the nome stampa
	 * @return the parametri
	 */
	public List<Provvedimento> getProvvedimentoByAnnoNumeroTipoSettore(Integer anno, String numero, Integer tipoId, List<UUID> settoriId,UUID enteId) {
		final List<CpassTProvvedimento> entities = cpassTProvvedimentoDao.getProvvedimentoByAnnoNumeroTipo(anno, numero, tipoId, settoriId,enteId);
		return CpassMappers.PROVVEDIMENTO.toModels(entities);
	}
	/**
	 * 
	 * @param anno
	 * @param numero
	 * @param tipoId
	 * @param enteId
	 * @return
	 */
	public List<Provvedimento> getProvvedimentoByAnnoNumeroTipo(Integer anno, String numero, Integer tipoId,UUID enteId) {
		return getProvvedimentoByAnnoNumeroTipoSettore(anno, numero, tipoId, null, enteId);
	}

}
