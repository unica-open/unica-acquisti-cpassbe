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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.EnteDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FruitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.exposed.VerificaEvasioneService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.exposed.VerificaEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.exposed.VerificaEvasioneResponse;
import it.csi.cpass.cpassbe.lib.exposed.dto.Evasione;

/**
 * Fa&ccedil;ade for the /exposed path
 */
@Stateless
public class ExposedFacade extends BaseFacade {

	// Injection point
	@Inject private FruitoreDad fruitoreDad;
	@Inject private EnteDad enteDad;
	@Inject private TestataOrdineDad testataOrdineDad;
	@Inject private RigaEvasioneDad rigaEvasioneDad;
	@Inject private ImpegnoEvasioneDad impegnoEvasioneDad;
	@Inject private SubimpegnoEvasioneDad subimpegnoEvasioneDad;
	@Inject private DecodificaDad decodificaDad;
	@Inject private DestinatarioEvasioneDad destinatarioEvasioneDad;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public VerificaEvasioneResponse verificaEvasione(Evasione evasioni) {
		return executeService(new VerificaEvasioneRequest(evasioni), new VerificaEvasioneService(configurationHelper, fruitoreDad, enteDad, testataOrdineDad, rigaEvasioneDad, impegnoEvasioneDad, subimpegnoEvasioneDad, decodificaDad, destinatarioEvasioneDad));
		//		responseJaxrs.getApiErrors().stream().map(x -> x.getGroup()).distinct().collect(Collectors.toList());
	}
}
