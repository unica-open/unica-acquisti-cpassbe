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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaFornitoreInternoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaFornitoreInternoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;

public class PostRicercaFornitoreInternoService extends BaseCommonService<PostRicercaFornitoreInternoRequest, PostRicercaFornitoreInternoResponse> {

	private Fornitore fornitore;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 */
	public PostRicercaFornitoreInternoService(ConfigurationHelper configurationHelper,  CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void checkServiceParams() {
		fornitore = request.getFornitore();
	}

	@Override
	protected void execute() {
		
		//controllo che almeno un criterio di ricerca sia stato valorizzato
		checkBusinessCondition(StringUtils.isNotEmpty(fornitore.getCodice()) || StringUtils.isNotEmpty(fornitore.getCodiceFiscale()) || StringUtils.isNotEmpty(fornitore.getPartitaIva()) || StringUtils.isNotEmpty(fornitore.getRagioneSociale())
							   , MsgCpassOrd.ORDORDE0003.getError());
		
		List<Fornitore> fornitore = commonDad.postRicercaFornitoreInterno(
				request.getFornitore()				
				);
		response.setFornitori(fornitore);
	}

	
}
