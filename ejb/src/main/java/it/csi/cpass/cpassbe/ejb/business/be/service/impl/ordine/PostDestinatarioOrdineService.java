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
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostDestinatarioOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostDestinatarioOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostDestinatarioOrdineService extends BaseService<PostDestinatarioOrdineRequest, PostDestinatarioOrdineResponse> {

	private final DestinatarioOrdineDad destinatarioDad;
	private final SettoreDad settoreDad;
	private Destinatario destinatario;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostDestinatarioOrdineService(ConfigurationHelper configurationHelper, DestinatarioOrdineDad destinatarioDad, SettoreDad settoreDad) {
		super(configurationHelper);
		this.destinatarioDad = destinatarioDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		destinatario = request.getOrdineDestinatario();
		checkNotNull(destinatario, "destinatario", Boolean.TRUE);
		checkNotNull(destinatario.getSettore(), "settore", Boolean.TRUE);
		checkNotNull(destinatario.getSettore().getCodice(), "codiceSettore", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		// controllo che il settore passato esista e sia valido
		final Optional<Settore> settore = settoreDad.findByCodice(destinatario.getSettore().getCodice(),ente.getId());
		checkBusinessCondition(settore.isPresent(), MsgCpassOrd.ORDORDE0008.getError());

		//controllo che se uno dei campi indirizzo è popoloato, lo siano tutti
		if( StringUtility.isNotEmpty(destinatario.getIndirizzo()) || StringUtility.isNotEmpty(destinatario.getNumCivico()) || StringUtility.isNotEmpty(destinatario.getLocalita()) || StringUtility.isNotEmpty(destinatario.getCap()) || StringUtility.isNotEmpty(destinatario.getProvincia())) {
			checkBusinessCondition(StringUtility.isNotEmpty(destinatario.getIndirizzo())
					&& StringUtility.isNotEmpty(destinatario.getNumCivico())
					&& StringUtility.isNotEmpty(destinatario.getLocalita())
					&& StringUtility.isNotEmpty(destinatario.getCap())
					&& StringUtility.isNotEmpty(destinatario.getProvincia()), MsgCpassOrd.ORDORDE0009.getError());
		}

		//controllo che se vi sono altri destinatari legati allo stesso ordine, non vi sia già lo stesso
		final List<Destinatario> destinatariOrdine = destinatarioDad.findByOrdine(destinatario.getTestataOrdine().getId());
		boolean foundEquals = Boolean.FALSE;
		for(final Destinatario dest : destinatariOrdine) {
			if(dest.getSettore() != null && dest.getSettore().getCodice() != null && dest.getSettore().getCodice().equalsIgnoreCase(destinatario.getSettore().getCodice())){
				if(super.isIndirizzoEquals(destinatario,dest)) { //destinatario.getIndirizzo().equals(dest.getIndirizzo())) {
					foundEquals = Boolean.TRUE;
				}
			}
		}
		checkBusinessCondition(!foundEquals, MsgCpassOrd.ORDORDE0030.getError());
		destinatario = destinatarioDad.saveDestinatario(destinatario);
		response.setDestinatario(destinatario);
	}
}
