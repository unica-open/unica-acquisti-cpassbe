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
package it.csi.cpass.cpassbe.ejb.business.be.service.response.utente;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.cpass.cpassbe.lib.dto.SettoreRuoliPermessi;

/**
 * Response for reading lista settori by the id of the parent utente.
 */
public class GetSettoriRuoliPermessiByUtenteResponse extends BaseGetResponse<List<SettoreRuoliPermessi>> {

	private List<SettoreRuoliPermessi> settoriRuoliPermessi = new ArrayList<>();





	/**
	 * @return the settoriRuoliPermessi
	 */
	public List<SettoreRuoliPermessi> getSettoriRuoliPermessi() {
		return settoriRuoliPermessi;
	}





	/**
	 * @param settoriRuoliPermessi the settoriRuoliPermessi to set
	 */
	public void setSettoriRuoliPermessi(List<SettoreRuoliPermessi> settoriRuoliPermessi) {
		this.settoriRuoliPermessi = settoriRuoliPermessi;
	}





	@Override
	protected List<SettoreRuoliPermessi> getEntity() {
		return settoriRuoliPermessi;
	}

}
