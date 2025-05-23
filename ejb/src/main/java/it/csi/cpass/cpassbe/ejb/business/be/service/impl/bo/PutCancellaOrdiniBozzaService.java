/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.bo;

import java.util.Arrays;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutCancellaOrdiniBozzaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutCancellaOrdiniBozzaResponse;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassVOrdine;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ValoreEnum;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;


public class PutCancellaOrdiniBozzaService extends BaseBoService<PutCancellaOrdiniBozzaRequest, PutCancellaOrdiniBozzaResponse> {

	private final TestataOrdineDad testataOrdineDad;
	private final RdaDad rdaDad;
	private final DecodificaDad decodificaDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;


	public PutCancellaOrdiniBozzaService(
			ConfigurationHelper configurationHelper,
			TestataOrdineDad testataOrdineDad,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			DecodificaDad decodificaDad,
			ImpegnoDad impegnoDad,
			RigaOrdineDad rigaOrdineDad,
			DestinatarioOrdineDad destinatarioOrdineDad,
			RdaDad rdaDad
			) {
		super(configurationHelper,
				elaborazioneDad,
				elaborazioneMessaggioDad,
				elaborazioneTipoDad
				);
		this.testataOrdineDad = testataOrdineDad;
		this.decodificaDad = decodificaDad;
		this.impegnoDad = impegnoDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.rdaDad = rdaDad;
	}

	@Override
	public void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final Elaborazione elaborazione = inizializzaElaborazione(ente.getId() ,ConstantsCPassElaborazione.TipoEnum.CHIUSURA_FINE_ANNO.getCodice(), ente.getCodice(),null);
		cancellaOrdiniInBozza(elaborazione);
		response.setElaborazione(elaborazione);

	}

	private void cancellaOrdiniInBozza(Elaborazione elaborazione) {
		boolean errore = false;
		final Integer statoId = decodificaDad.getStato("BOZZA", "ORDINE").getId();
		final List<Integer> statiIds = Arrays.asList(statoId);
		final List<CpassVOrdine> listaVOrdini = testataOrdineDad.getListVOrdineByStati(statiIds);

		for (final CpassVOrdine vOrdine : listaVOrdini) {
			try {
				final List<TestataRda> listaTestateRDA = testataOrdineDad.getTestataRdaByTestataOrdineId(vOrdine.getTestataOrdineId());
				if (!listaTestateRDA.isEmpty()) {
					for (final TestataRda testataRda : listaTestateRDA) {
						// Aggiorna lo stato di tutte le righe RDA
						for (final RigaRda rigaRds : testataRda.getRigaRda()) {
							rigaRds.setStato(decodificaDad.getStato(ConstantsCPassStato.StatoRigaRdaEnum.DAE.getCostante(), ConstantsCPassStato.TipoStatoEnum.RIGA_RDA.getCostante()));
							rdaDad.updateRigaRda(rigaRds);
						}
					}
				}

				//cancellaRigaRda()
				testataOrdineDad.deleteRelRdaOrdine(vOrdine.getTestataOrdineId(), null);

				//cancellaSubById
				impegnoDad.deleteSubImpFromTestataordine(vOrdine.getTestataOrdineId());

				//cancellaImpById
				impegnoDad.deleteImpFromTestataordine(vOrdine.getTestataOrdineId());

				//cancellaRigaById
				rigaOrdineDad.deleteRigaOrdFromTestataordine(vOrdine.getTestataOrdineId());

				//cancellaAssociatiById
				testataOrdineDad.deleteAssociatiById(vOrdine.getTestataOrdineId());

				//cancellaDestById
				destinatarioOrdineDad.deleteDestFromTestataordine(vOrdine.getTestataOrdineId());

				//cancellaProtocolli
				testataOrdineDad.deleteProtocolliOrdineByTestataOrdine(vOrdine.getTestataOrdineId());

				//cancellaTestataById
				testataOrdineDad.deleteTestataOrdine(vOrdine.getTestataOrdineId());


			} catch (final Exception e) {
				errore = Boolean.TRUE;
				inserisciMessaggioElaborazione(elaborazione , ConstantsCPassElaborazione.TipoEnum.CHIUSURA_FINE_ANNO.getCodice(), ValoreEnum.ERRORE_OPERAZIONI_CHIUSURA.getCostante(), e.getMessage());
			}
		}
		if(errore) {
			concludiElaborazione(elaborazione, CpassEnum.KO.getCostante(),CpassEnum.KO.getCostante());
		} else {
			concludiElaborazione(elaborazione, CpassEnum.OK.getCostante(),CpassEnum.OK.getCostante());
		}
	}
}

