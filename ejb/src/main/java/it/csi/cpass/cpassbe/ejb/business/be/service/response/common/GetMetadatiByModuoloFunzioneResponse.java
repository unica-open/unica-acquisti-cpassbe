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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.common;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Parametro;

/**
 * The Class GetMetadatiFunzioneResponse.
 */
public class GetMetadatiByModuoloFunzioneResponse extends BaseGetResponse<List<MetadatiFunzione>> {

	/** The lingue. */
	private List<MetadatiFunzione> metadatiFunzione = new ArrayList<>();
	private Parametro parametroSIO;

	/**
	 * @return the MetadatiFunziones
	 */
	public List<MetadatiFunzione> getMetadatiFunzione() {
		return metadatiFunzione;
	}

	/**
	 * @param MetadatiFunziones the MetadatiFunziones to set
	 */
	public void setMetadatiFunzione(List<MetadatiFunzione> metadatiFunzione) {
		this.metadatiFunzione = metadatiFunzione != null ? metadatiFunzione : new ArrayList<>();
	}


	public Parametro getParametroSIO() {
		return parametroSIO;
	}

	public void setParametroSIO(Parametro parametroSIO) {
		this.parametroSIO = parametroSIO;
	}

	@Override
	protected List<MetadatiFunzione> getEntity() {
		return metadatiFunzione;
	}



}
