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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.GetSettoreTreeByEnteRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.GetSettoreTreeByEnteResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.VSettore;
import it.csi.cpass.cpassbe.lib.dto.custom.AlberoSettoriWrapper;

/**
 * Gets the Settores by ente
 */
public class GetSettoreTreeByEnteService extends BaseCommonService<GetSettoreTreeByEnteRequest, GetSettoreTreeByEnteResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the helper for the configuration
	 * @param commonDad the DAD for the common
	 */
	public GetSettoreTreeByEnteService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		final String codSettoreRadice = request.getCodSettoreRadice();
		final String validi = request.getValidita() == null ? "true" : request.getValidita();

		final AlberoSettoriWrapper alberoSettoriWrapper = commonDad.getSettoreTreeByEnte(request.getEnteId(),validi);


		List<VSettore> alberoSettori = alberoSettoriWrapper.getAlberoSettori();

		if (codSettoreRadice != null && !codSettoreRadice.isEmpty()) {
			alberoSettori = filtraAlberoPerRadice(alberoSettoriWrapper, codSettoreRadice);
		}

		response.setSettores(alberoSettori);
	}

	private List<VSettore> filtraAlberoPerRadice(AlberoSettoriWrapper alberoSettoriWrapper, String codSettoreRadice) {
		List<VSettore> elencoSettori = alberoSettoriWrapper.getAlberoSettori();
		final int maxLevel = alberoSettoriWrapper.getMaxLevel();
		final List<VSettore> res = new ArrayList<>();

		// scorro l'alberatura a partire dall'alto
		int level = 0;

		while (res.isEmpty() && level < maxLevel) {
			final List<VSettore> listaSettoriFigli = new ArrayList<>();
			for (final VSettore settore : elencoSettori) {
				if (settore.getLivello() > level) {
					level = settore.getLivello();
				}
				if (settore.getCodice().equals(codSettoreRadice)) {
					res.add(settore);
					break;
				} else {
					listaSettoriFigli.addAll(settore.getListSettore());
				}
			}
			elencoSettori = listaSettoriFigli;
		}

		return res;
	}


}
