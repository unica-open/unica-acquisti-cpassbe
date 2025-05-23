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

import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.utente.GetModuloBySettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.utente.GetModuloBySettoreResponse;
import it.csi.cpass.cpassbe.ejb.entity.CsiLogAudit;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Modulo;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an Moduli
 */
public class GetModuloBySettoreService extends BaseUtenteService<GetModuloBySettoreRequest, GetModuloBySettoreResponse> {
	private SystemDad  systemDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetModuloBySettoreService(ConfigurationHelper configurationHelper, UtenteDad utenteDad,SystemDad  systemDad) {
		super(configurationHelper, utenteDad);
		this.systemDad = systemDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getSettoreId(), "settoreId");
	}

	@Override
	protected void execute() {
		final Utente utente = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final List<Modulo> moduli = utenteDad.getModuliByUtenteAndSettore(utente.getId(), request.getSettoreId());
		//audit(utente.getCodiceFiscale());
		systemDad.insertCsiLogAudit(utente.getCodiceFiscale(),"LOGIN" );
		response.setModuli(moduli);
	}
/*
	private void audit(String cf) {
		CsiLogAudit csiLogAudith = new CsiLogAudit();
		csiLogAudith.setCfUtente(cf);
		csiLogAudith.setDataOra(new Date());
		csiLogAudith.setOperazione("LOGIN");
		systemDad.insertCsiLogAudit(csiLogAudith );
	}
	*/
}
