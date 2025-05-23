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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;

/**
 * Base class for services for /intervento path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseSettoreService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for settore */
	protected final SettoreDad settoreDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento dad
	 */
	protected BaseSettoreService(ConfigurationHelper configurationHelper, SettoreDad settoreDad) {
		super(configurationHelper);
		this.settoreDad = settoreDad;
	}

	protected Boolean controlloGerarchiasettori(Settore settore2) {
		final int posizioneFiglio = settore2.getTipoSettore().getPosizione();
		int posizionePadre = 0;
		if(settore2.getSettorePadre().getId()!=null) {
			posizionePadre = settoreDad.findById(settore2.getSettorePadre().getId()).getTipoSettore().getPosizione();
		}
		if(posizioneFiglio <= posizionePadre) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}



}
