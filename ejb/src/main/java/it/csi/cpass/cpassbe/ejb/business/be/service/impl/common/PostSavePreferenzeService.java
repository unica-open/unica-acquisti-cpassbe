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

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostSavePreferenzeRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostSavePreferenzeResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

public class PostSavePreferenzeService extends BaseCommonService<PostSavePreferenzeRequest, PostSavePreferenzeResponse> {
	
	/**
	 * Constructor
	 */
	public PostSavePreferenzeService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
		
		/** Controllo preferenze != null 
			checkBusinessCondition(request.getPreferenze() != null, --errore--);
		*/
	}

	@Override
	protected void execute() {
		// conversione mappa preferenze in stringa formattata come json
		String preferenzeString = JsonUtility.serialize(request.getPreferenze());
		
		//TODO: salvare le preferenze come stringa a db
		
		response.setPreferenze(request.getPreferenze());
	}

}
