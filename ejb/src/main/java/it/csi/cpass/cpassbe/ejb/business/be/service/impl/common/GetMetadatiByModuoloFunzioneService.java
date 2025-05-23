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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetMetadatiByModuoloFunzioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetMetadatiByModuoloFunzioneResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Gets the Ufficios by settore
 */
public class GetMetadatiByModuoloFunzioneService extends BaseCommonService<GetMetadatiByModuoloFunzioneRequest, GetMetadatiByModuoloFunzioneResponse> {

	SystemDad systemDad;
	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetMetadatiByModuoloFunzioneService(ConfigurationHelper configurationHelper, CommonDad commonDad, SystemDad systemDad) {
		super(configurationHelper, commonDad);
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getFunzione(), "funzione");
		checkNotNull(request.getModulo(), "modulo");
	}

	@Override
	protected void execute() {
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		final List<MetadatiFunzione>lista = commonDad.getMetadatiByModuoloFunzione(request.getModulo(),request.getFunzione());
		final Parametro parametroVR = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.VISTO_RAGIONERIA.getCostante(),ConstantsCPassParametro.RiferimentoEnum.PBA.getCostante(),enteId);
		final Parametro parametroVD = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.GESTIONE_ACQUISTO_VERS_DEFINITIVA.getCostante(),ConstantsCPassParametro.RiferimentoEnum.PBA.getCostante(),enteId);
		final Parametro parametroSIO = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.SOGLIA_IVA_OBBLIGATORIA.getCostante(),ConstantsCPassParametro.ValoreEnum.SOGLIA_IVA_OBBLIGATORIA.getCostante(),enteId);
		//TODO da rivedere assolutamente pezza per la contingenza
		List<MetadatiFunzione>listaNew = new ArrayList<>();
		if (parametroVR == null || parametroVR.getValore().equalsIgnoreCase("false")) {
			for(final MetadatiFunzione mf :lista) {
				if(!mf.getChiaveColonna().equalsIgnoreCase("PBA.INTERVENTION.FIELD.VISTO_RAGIONERIA")){
					listaNew.add(mf);
				}
			}
		}else {
			listaNew=lista;
		}

		List<MetadatiFunzione>listaNew2 = new ArrayList<>();
		if (parametroVD == null || parametroVD.getValore().equalsIgnoreCase("false")) {
			for(final MetadatiFunzione mf :listaNew) {
				if(!mf.getChiaveColonna().equalsIgnoreCase("PBA.INTERVENTION.FIELD.VER_DEF")){
					listaNew2.add(mf);
				} else {
					log.info("", "non aggancio alla lista");
				}
			}
		}else {
			listaNew2=listaNew;
		}

		response.setMetadatiFunzione(listaNew2);
		response.setParametroSIO(parametroSIO);
	}

}
