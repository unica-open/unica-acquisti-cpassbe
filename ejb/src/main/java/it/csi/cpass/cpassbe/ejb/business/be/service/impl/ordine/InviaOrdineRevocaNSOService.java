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
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.InviaOrdineNSORequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.InviaOrdineNSOResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassOrdStatoNso;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.EsitoInviaOrdineNSOService;
import it.csi.cpass.cpassbe.lib.dto.ord.nso.TipoOrdineNSO;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Invio ordine a NSO
 */
//public class InviaOrdineNSOService extends BaseTestataOrdineService<InviaOrdineNSORequest, InviaOrdineNSOResponse> implements NSOListener {
public class InviaOrdineRevocaNSOService extends BaseInviaOrdineNSOService<InviaOrdineNSORequest, InviaOrdineNSOResponse> {

	private TestataOrdine testataOrdine;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param externalHelperLookup
	 */
	public InviaOrdineRevocaNSOService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioOrdineDad,
			RigaOrdineDad rigaOrdineDad, DecodificaDad decodificaDad,ImpegnoDad impegnoDad,SettoreDad settoreDad, ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper, testataOrdineDad,destinatarioOrdineDad,rigaOrdineDad,decodificaDad,impegnoDad,settoreDad,externalHelperLookup);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "testata ordine id");
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();

		testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId(),ente.getId());

		final EsitoInviaOrdineNSOService esitoInviaOrdineNSOService = new EsitoInviaOrdineNSOService();

		//I destinatari da considerare sono quelli inviati a nso

		final List<Destinatario> destinatariDaInviare = testataOrdine.getListDestinatario().stream().filter((x) ->
		x.getStatoNso()!=null && (x.getStatoNso().getCodice().equals(ConstantsCPassOrdStatoNso.StatoEnum.OK.getCostante()) || x.getStatoNso().getCodice().equals(ConstantsCPassOrdStatoNso.StatoEnum.INVIATO.getCostante())))
				.collect(Collectors.toList());
		if (!destinatariDaInviare.isEmpty()) {
			inviaOrdineNSO(testataOrdine, destinatariDaInviare, TipoOrdineNSO.REVOCA);

			checkBusinessCondition(!response.getEsitoInvio().equals(ConstantsCPassOrdStatoNso.StatoEnum.KO.getCostante()), MsgCpassOrd.ORDORDE0139.getError(), false);

		}
		esitoInviaOrdineNSOService.setTestataOrdine(testataOrdine);
		response.setEsitoInviaOrdineNSOService(esitoInviaOrdineNSOService);
	}

}
