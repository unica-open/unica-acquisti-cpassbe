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
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutAnnullaOrdiniConfermatiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutAnnullaOrdiniConfermatiResponse;
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

public class PutAnnullaOrdiniConfermatiService extends BaseBoService<PutAnnullaOrdiniConfermatiRequest, PutAnnullaOrdiniConfermatiResponse> {
	private final TestataOrdineDad testataOrdineDad;
	private final DecodificaDad decodificaDad;
	private final RdaDad rdaDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final ImpegnoOrdineDad impegnoOrdineDad;

	public PutAnnullaOrdiniConfermatiService
	(ConfigurationHelper configurationHelper,
			TestataOrdineDad testataOrdineDad,
			ElaborazioneDad  elaborazioneDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			DecodificaDad decodificaDad,
			RdaDad rdaDad,
			RigaOrdineDad rigaOrdineDad,
			DestinatarioOrdineDad destinatarioOrdineDad,
			ImpegnoOrdineDad impegnoOrdineDad
			) {
		super(configurationHelper,elaborazioneDad,elaborazioneMessaggioDad, elaborazioneTipoDad);
		this.testataOrdineDad = testataOrdineDad;
		this.decodificaDad = decodificaDad;
		this.rdaDad = rdaDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.impegnoOrdineDad = impegnoOrdineDad;
	}

	@Override
	public void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		final Elaborazione elaborazione = inizializzaElaborazione(ente.getId(),ConstantsCPassElaborazione.TipoEnum.CHIUSURA_FINE_ANNO.getCodice(),ente.getCodice(),null);
		annullaOrdiniConfermati(elaborazione);
		response.setElaborazione(elaborazione);
	}

	public void annullaOrdiniConfermati(Elaborazione elaborazione) {
		boolean errore = false;
		final Integer statoIdConfermato = decodificaDad.getStato("CONFERMATO", "ORDINE").getId();
		final Integer statoIdInFirma = decodificaDad.getStato("IN_FIRMA", "ORDINE").getId();
		final List<Integer> statiIds = Arrays.asList(statoIdConfermato, statoIdInFirma);
		final List<CpassVOrdine> listaVOrdini = testataOrdineDad.getListVOrdineByStati(statiIds);

		// Itera sugli ordini confermati
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
				impegnoOrdineDad.updateSubImpFromTestataordine(vOrdine.getTestataOrdineId());
				impegnoOrdineDad.updateImpFromTestataordine(vOrdine.getTestataOrdineId());
				rigaOrdineDad.updateRigaOrdFromTestataordine(vOrdine.getTestataOrdineId());
				destinatarioOrdineDad.updateDestFromTestataordine(vOrdine.getTestataOrdineId());
				testataOrdineDad.updateTestataordineById(vOrdine.getTestataOrdineId());

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
