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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.GetElaborazioneDocumentoScartatoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.GetElaborazioneDocumentoScartatoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;

public class GetElaborazioneDocumentoTrasportoScartatoService extends BaseService<GetElaborazioneDocumentoScartatoRequest, GetElaborazioneDocumentoScartatoResponse> {

	private final ElaborazioneDad elaborazioneDad;
	private final DocumentoTrasportoDad documentoTrasportoDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 */
	public GetElaborazioneDocumentoTrasportoScartatoService(ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			DocumentoTrasportoDad documentoTrasportoDad) {
		super(configurationHelper);
		this.elaborazioneDad = elaborazioneDad;
		this.documentoTrasportoDad = documentoTrasportoDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request, "request", true);
		checkNotNull(request.getIdDocumentoTrasporto(), "idDocumentoTrasporto", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final Integer idDocumentoTrasporto = request.getIdDocumentoTrasporto();

		final DocumentoTrasporto documentoTrasporto = documentoTrasportoDad.findById(idDocumentoTrasporto);
		final String despatchAdviceId = documentoTrasporto.getDespatchAdviceId();

		final List<Elaborazione> elaborazioneList = elaborazioneDad.getElaborazioneDocumentoTrasportoScartato(despatchAdviceId);
		response.setElaborazioneList(elaborazioneList);
	}
}
