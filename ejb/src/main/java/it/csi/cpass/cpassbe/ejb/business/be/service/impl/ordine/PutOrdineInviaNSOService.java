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

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PutOrdineInviaNSORequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PutOrdineInviaNSOResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoNSO;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.EsitoInvioDocumento;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.external.NSOHelper;
import it.csi.cpass.cpassbe.lib.external.NSOListener;
import it.csi.cpass.cpassbe.lib.external.dto.RigheOrdineConTotali;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;

/**
 * Invio ordine a NSO
 */
public class PutOrdineInviaNSOService extends BaseTestataOrdineService<PutOrdineInviaNSORequest, PutOrdineInviaNSOResponse> implements NSOListener {

	private DecodificaDad decodificaDad;
	private DestinatarioOrdineDad destinatarioOrdineDad;
	private RigaOrdineDad rigaOrdineDad;
	private TestataOrdine testataOrdine;
	private final ExternalHelperLookup externalHelperLookup;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param externalHelperLookup
	 */
	public PutOrdineInviaNSOService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, DestinatarioOrdineDad destinatarioOrdineDad,
			RigaOrdineDad rigaOrdineDad, DecodificaDad decodificaDad, ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper, testataOrdineDad);
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.decodificaDad = decodificaDad;
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void checkServiceParams() {
		testataOrdine = request.getTestataOrdine();
		checkModel(testataOrdine, "programma");
		checkNotNull(testataOrdine.getOptlock(), "opt look");
	}

	@Override
	protected void execute() {
		final String methodName = "execute";

		TestataOrdine testataOrdineAttuale = isEntityPresent(() -> testataOrdineDad.getTestataOrdine(request.getId()), "testataOrdine");
		checkOptlock(testataOrdine.getOptlock(), testataOrdineAttuale.getOptlock());

		for (Destinatario destinatarioOrdine : testataOrdine.getListDestinatario()) {
			List<RigaOrdine> rigaOrdines = rigaOrdineDad.getRigheByDestinatario(destinatarioOrdine.getId());

			RigheOrdineConTotali righeOrdineConTotali = new RigheOrdineConTotali();
			righeOrdineConTotali.setRigaOrdines(rigaOrdines);

			for (RigaOrdine rigaOrdine : rigaOrdines) {
				righeOrdineConTotali.taxAmount = righeOrdineConTotali.taxAmount.add(rigaOrdine.getImportoIva());
				righeOrdineConTotali.lineExtensionAmount = righeOrdineConTotali.lineExtensionAmount
						.add(rigaOrdine.getPrezzoUnitario().multiply(rigaOrdine.getQuantita()));

				if (rigaOrdine.getImportoSconto() != null) {
					righeOrdineConTotali.allowanceTotalAmount = righeOrdineConTotali.allowanceTotalAmount.add(rigaOrdine.getImportoSconto());
				}
				if (rigaOrdine.getImportoSconto2() != null) {
					righeOrdineConTotali.allowanceTotalAmount = righeOrdineConTotali.allowanceTotalAmount.add(rigaOrdine.getImportoSconto2());
				}

				righeOrdineConTotali.taxExclusiveAmount = righeOrdineConTotali.taxExclusiveAmount.add(rigaOrdine.getImportoNetto());
				righeOrdineConTotali.taxInclusiveAmount = righeOrdineConTotali.taxInclusiveAmount.add(rigaOrdine.getImportoTotale());
				righeOrdineConTotali.payableAmount = righeOrdineConTotali.payableAmount.add(rigaOrdine.getImportoTotale());
			}

			ExternalServiceResolveWrapper<NSOHelper> handler = externalHelperLookup.lookup(NSOHelper.class);
			EsitoInvioDocumento esitoInvioDocumento = invokeExternalService(handler,
					() -> handler.getInstance().invioDocumentoNSO(handler.getParams(), testataOrdine, destinatarioOrdine, righeOrdineConTotali, this));
			log.info(methodName, "urnDocumento: " + esitoInvioDocumento.getUrnDocumento());

			if (!StringUtils.isBlank(esitoInvioDocumento.getUrnDocumento())) {
				// TODO save urn to CPASS_R_ORD_TESTATA_INVIO_NSO

				destinatarioOrdine.setDataInvioNso(new Date());
				destinatarioOrdineDad.updateDestinatario(destinatarioOrdine);
			}
		}

		// TODO verificare
		testataOrdineAttuale.setStatoNso(isEntityPresent(
				() -> decodificaDad.getStatoNso(ConstantsCPassStatoNSO.StatoEnum.OK.getCostante(), ConstantsCPassStatoNSO.TipoEnum.ORDINE.getCostante()),
				"statoNSO"));
		testataOrdineAttuale = testataOrdineDad.updateTestataOrdine(testataOrdineAttuale, false);
		
		response.setTestataOrdine(testataOrdineAttuale);
	}

	@Override
	public int getMaxProgressivoInvio() {
		return 1;
	}

}
