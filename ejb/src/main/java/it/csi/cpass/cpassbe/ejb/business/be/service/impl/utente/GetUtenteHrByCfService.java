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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.utente;

import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetUtenteHrByCfRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetUtenteHrByCfResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;
import it.csi.cpass.cpassbe.lib.external.UtenteHrHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an utente by its id
 */
public class GetUtenteHrByCfService extends BaseUtenteService<GetUtenteHrByCfRequest, GetUtenteHrByCfResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetUtenteHrByCfService(ConfigurationHelper configurationHelper,ExternalHelperLookup externalHelperLookup, UtenteDad utenteDad) {
		super(configurationHelper, utenteDad);
		this.externalHelperLookup 	= externalHelperLookup;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getCf(), "c.f");
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final ExternalServiceResolveWrapper<UtenteHrHelper> handler = externalHelperLookup.lookup(UtenteHrHelper.class,ente.getId() );
		final Utente utente = invokeExternalService(handler, () -> handler.getInstance().getUtenteHrByCf(handler.getParams(), request.getCf(),ente.getCodice()));
		//checkBusinessCondition(utente!=null && utente.getCognome()!= null , MsgCpassBo.BACUTEE0012.getError());
		warnBusinessCondition(utente!=null && utente.getPresenteSuHr() , MsgCpassBo.BACUTEA0020.getError());
		response.setUtente(utente);

	}

}
