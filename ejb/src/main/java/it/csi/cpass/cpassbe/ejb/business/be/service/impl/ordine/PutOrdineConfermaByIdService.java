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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineConfermaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineConfermaByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.UtilityArrotondamento;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class PutOrdineConfermaByIdService extends BaseTestataOrdineService<PutOrdineConfermaByIdRequest, PutOrdineConfermaByIdResponse> {

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
			DecodificaDad decodificaDad) {
		super(configurationHelper, testataOrdineDad);
		this.rigaOrdineDad = rigaOrdineDad;
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId());
		// TestataOrdine testataOrdine = request.getTestataOrdine(); // l'oggetto non è completo

		// Arrotondare a 2 decimali (vedi criterio [4] Arrotondamento a 2 decimali)
		testataOrdine.setTotaleConIva(UtilityArrotondamento.arrotondaDueDecimali(testataOrdine.getTotaleConIva()));
		testataOrdine.setTotaleNoIva(UtilityArrotondamento.arrotondaDueDecimali(testataOrdine.getTotaleNoIva()));

		testataOrdine.setStato(isEntityPresent(
				() -> decodificaDad.getStato(ConstantsCPassStato.StatoEnum.CONFERMATO.getCostante(), ConstantsCPassStato.TipoEnum.ORDINE.getCostante()),
				"stato"));

		testataOrdineDad.updateTestataOrdine(testataOrdine, false);

		if (testataOrdine.getListDestinatario() != null) {
			for (Destinatario destinatario : testataOrdine.getListDestinatario()) {

				List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());
				if (rigaOrdines != null) {
					for (RigaOrdine rigaOrdine : rigaOrdines) {
						// Arrotondare a 2 decimali (vedi criterio [4] Arrotondamento a 2 decimali)
						rigaOrdine.setImportoIva(UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoIva()));
						rigaOrdine.setImportoNetto(UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoNetto()));
						rigaOrdine.setImportoTotale(UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoTotale()));
						rigaOrdine.setImportoSconto(UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoSconto()));
						rigaOrdine.setImportoSconto2(UtilityArrotondamento.arrotondaDueDecimali(rigaOrdine.getImportoSconto2()));

						rigaOrdineDad.updateRigaOrdine(rigaOrdine);
					}
				}
			}
		}

		response.setTestataOrdine(testataOrdine);
	}

}
