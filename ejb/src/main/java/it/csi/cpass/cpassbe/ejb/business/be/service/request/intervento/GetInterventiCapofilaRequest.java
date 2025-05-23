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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Request for reading the Interventos
 */
public class GetInterventiCapofilaRequest implements BaseRequest {
	private final Intervento intervento ;
	private final UUID programmaId ;

	/**
	 * @param id
	 */
	public GetInterventiCapofilaRequest(UUID programmaId, Intervento intervento) {
		super();
		this.intervento = intervento;
		this.programmaId =  programmaId;
	}



	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}



	/**
	 * @return the programmaId
	 */
	public UUID getProgrammaId() {
		return programmaId;
	}


}
