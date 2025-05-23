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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.settore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.settore.PostSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.settore.PostSettoreResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Cdc;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Settore
 *
 */
public class PostSettoreService extends BaseSettoreService<PostSettoreRequest, PostSettoreResponse> {

	private Settore settore;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param settoreDad the settore DAD
	 */
	public PostSettoreService(ConfigurationHelper configurationHelper, SettoreDad settoreDad) {
		super(configurationHelper, settoreDad);
	}

	@Override
	protected void checkServiceParams() {
		settore = request.getSettore();
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		settore.setEnte(ente);
		final List<SettoreIndirizzo> listaIndirizzi = settore.getSettoreIndirizzos();
		final List<Ufficio> listaUffici = settore.getUffici();
		final List<Cdc> listaCdc = settore.getCdcs();

		final Optional<Settore> settRicercato = settoreDad.findByCodice(settore.getCodice(), ente.getId(),false);
		checkBusinessCondition(!settRicercato.isPresent(),MsgCpassBo.BACSTRE0005.getError("codice",settore.getCodice()),false);

		final Boolean gerarchiaCorretta = controlloGerarchiasettori(settore);
		checkBusinessCondition(gerarchiaCorretta,MsgCpassBo.BACSTRE0007.getError(),false);

		gotoException();

		settore.setUffici(new ArrayList<>());
		settore.setCdcs(new ArrayList<>());
		settore.setSettoreIndirizzos(new ArrayList<>());
		settore = settoreDad.saveSettoreAndFlush(settore);

		for (final SettoreIndirizzo ind: listaIndirizzi) {
			ind.setSettore(settore);
			settoreDad.saveAndFlushSettoreIndirizzo(ind);
		}

		for (final Ufficio uff: listaUffici) {
			settoreDad.insertUfficioSettore(uff,settore);
		}

		for (final Cdc cdc: listaCdc) {
			settoreDad.insertCdcSettore(cdc,settore);
		}


		log.info("post settore", "dopo il salvataggio");
		response.setSettore(settore);
	}
}
