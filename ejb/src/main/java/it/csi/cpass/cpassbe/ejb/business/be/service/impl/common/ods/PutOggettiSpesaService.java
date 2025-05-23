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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.BaseCommonService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.PutOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.PutOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutOggettiSpesaService extends BaseCommonService<PutOggettiSpesaRequest, PutOggettiSpesaResponse> {

	private Ods ods;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 */
	public PutOggettiSpesaService(ConfigurationHelper configurationHelper,  CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void checkServiceParams() {
		ods = request.getOds();
		checkModel(ods, "ods");
		checkModel(ods.getCpv(), "cpv");
		checkModel(ods.getAliquoteIva(), "aliquote iva");
		checkModel(ods.getUnitaMisura(), "unita' di misura");
		checkNotNull(ods.getCodice(),"codice");
		checkNotNull(ods.getDescrizione(),"descrizione");
		checkNotNull(ods.getPrezzoUnitario(),"prezzo unitario");
	}

	@Override
	protected void execute() {
		final Utente utenteConnesso   = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final Ods odsNew = commonDad.putOggettoSpesa(this.ods,utenteConnesso.getCodiceFiscale(),ente);
		response.setOds(odsNew);
	}


}
