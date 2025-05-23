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

import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineConfermaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineConfermaByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoOrdineEnum;
import it.csi.cpass.cpassbe.ejb.util.NumberUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutOrdineConfermaByIdService extends BaseOrdineService<PutOrdineConfermaByIdRequest, PutOrdineConfermaByIdResponse> {

	private final RigaOrdineDad rigaOrdineDad;
	private final DecodificaDad decodificaDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param rigaOrdineDad
	 */
	public PutOrdineConfermaByIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, RigaOrdineDad rigaOrdineDad,
			DecodificaDad decodificaDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.rigaOrdineDad = rigaOrdineDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId(),ente.getId());
		// TestataOrdine testataOrdine = request.getTestataOrdine(); // l'oggetto non è completo

		// Arrotondare a 2 decimali (vedi criterio [4] Arrotondamento a 2 decimali)
		testataOrdine.setTotaleConIva(NumberUtility.arrotondaDueDec(testataOrdine.getTotaleConIva()));
		testataOrdine.setTotaleNoIva(NumberUtility.arrotondaDueDec(testataOrdine.getTotaleNoIva()));

		testataOrdine.setDataConferma(new Date(System.currentTimeMillis()));
		testataOrdine.setStato(isEntityPresent(() -> decodificaDad.getStatoOpt(StatoOrdineEnum.CONFERMATO.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE.getCostante()),"stato"));

		testataOrdineDad.updateTestataOrdine(testataOrdine, false);

		if (testataOrdine.getListDestinatario() != null) {
			for (final Destinatario destinatario : testataOrdine.getListDestinatario()) {

				final List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				if (rigaOrdines != null) {
					for (final RigaOrdine rigaOrdine : rigaOrdines) {
						// Arrotondare a 2 decimali (vedi criterio [4] Arrotondamento a 2 decimali)
						rigaOrdine.setImportoIva(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoIva()));
						rigaOrdine.setImportoNetto(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoNetto()));
						rigaOrdine.setImportoTotale(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoTotale()));
						rigaOrdine.setImportoSconto(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoSconto()));
						rigaOrdine.setImportoSconto2(NumberUtility.arrotondaDueDec(rigaOrdine.getImportoSconto2()));

						rigaOrdineDad.updateRigaOrdine(rigaOrdine);
					}
				}
			}
		}

		response.setTestataOrdine(testataOrdine);
	}

}
