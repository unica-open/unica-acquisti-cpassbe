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
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneMessaggioDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.bo.PutDisattivaFunzioniGestioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.bo.PutDisattivaFunzioniGestioneResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro.ValoreEnum;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Permesso;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PutDisattivaFunzioniGestioneService extends BaseBoService<PutDisattivaFunzioniGestioneRequest, PutDisattivaFunzioniGestioneResponse > {
	private final DecodificaDad decodificaDad;

	public PutDisattivaFunzioniGestioneService(
			ConfigurationHelper configurationHelper,
			ElaborazioneDad elaborazioneDad,
			ElaborazioneMessaggioDad elaborazioneMessaggioDad,
			ElaborazioneTipoDad elaborazioneTipoDad,
			DecodificaDad decodificaDad
			) {
		super(configurationHelper,elaborazioneDad,elaborazioneMessaggioDad, elaborazioneTipoDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	public void execute() {
		Elaborazione elaborazione = null;
		boolean errore = false;

		Ente ente = null;

		try {
			ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
			elaborazione = inizializzaElaborazione(ente.getId(), ConstantsCPassElaborazione.TipoEnum.CHIUSURA_FINE_ANNO.getCodice(), ente.getCodice(), null);
			final List<String> moduli = Arrays.asList("ORDINE", "EVASIONE");
			final List<Permesso> permessi = decodificaDad.getPermessiByModuli(moduli);

			for (final Permesso permesso : permessi) {
				decodificaDad.updatePermesso(permesso, Boolean.FALSE);
			}

		}catch (final Exception e) {
			errore = Boolean.TRUE;
			inserisciMessaggioElaborazione(elaborazione, ConstantsCPassElaborazione.TipoEnum.CHIUSURA_FINE_ANNO.getCodice(), ValoreEnum.ERRORE_OPERAZIONI_CHIUSURA.getCostante(), e.getMessage());
		} finally {
			if (errore) {
				concludiElaborazione(elaborazione, CpassEnum.KO.getCostante(), CpassEnum.KO.getCostante());
			} else {
				concludiElaborazione(elaborazione, CpassEnum.OK.getCostante(), CpassEnum.OK.getCostante());
			}
			response.setElaborazione(elaborazione);
		}
	}

}
