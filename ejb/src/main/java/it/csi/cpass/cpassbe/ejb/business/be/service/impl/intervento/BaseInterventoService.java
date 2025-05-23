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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassRisorsa;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.dto.pba.Risorsa;

/**
 * Base class for services for /intervento path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseInterventoService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for Intervento */
	protected final InterventoDad interventoDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento dad
	 */
	protected BaseInterventoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;
	}

	/**
	 * setRisorsaCapitalePrivato
	 * @param intervento
	 */
	public static void setRisorsaCapitalePrivato(Intervento intervento) {
		// cerca la risorsa capitale privato valorizzata
		for (final InterventoImporti interventoImporti : intervento.getListInterventoImporti()) {
			if (interventoImporti.getRisorsa().getTipo().equals(ConstantsCPassRisorsa.TipoEnum.CAPITALE_PRIVATO.getTipo())) {
				final boolean importiTuttiAZero = interventoImporti.getImportoAnnoPrimo().signum() == 0
						&& interventoImporti.getImportoAnnoSecondo().signum() == 0
						&& interventoImporti.getImportoAnnoTerzo().signum() == 0
						&& interventoImporti.getImportoAnniSuccessivi().signum() == 0;
				if (!importiTuttiAZero) {
					intervento.setRisorsaIdCapitalePrivato(interventoImporti.getRisorsa().getId());
				}
			}
		}
	}
	/**
	 * 
	 * @param ii
	 * @param intervento
	 * @param decodificaDad
	 * @return
	 */
	protected InterventoImporti getInterventoImportiPrivati(InterventoImporti ii, Intervento intervento, DecodificaDad decodificaDad) {
		InterventoImporti interventoImportiPrivati = null;
		if (ii.getRisorsa().getTagTrasmissione() != null && ii.getRisorsa().getTagTrasmissione().equals(ConstantsCPassRisorsa.RisorsaEnum.RISORSE_PRIVATI.getTagTrasmissione())) {
			if (intervento.getRisorsaIdCapitalePrivato() != null) {
				final Optional<Risorsa> optionalRisorsa = decodificaDad.getRisorsa(intervento.getRisorsaIdCapitalePrivato());
				interventoImportiPrivati = new InterventoImporti();
				interventoImportiPrivati.setDataCancellazione(ii.getDataCancellazione());
				interventoImportiPrivati.setDataCreazione(ii.getDataCreazione());
				interventoImportiPrivati.setDataModifica(ii.getDataModifica());
				interventoImportiPrivati.setImportoAnniSuccessivi(ii.getImportoAnniSuccessivi());
				interventoImportiPrivati.setImportoAnnoPrimo(ii.getImportoAnnoPrimo());
				interventoImportiPrivati.setImportoAnnoSecondo(ii.getImportoAnnoSecondo());
				interventoImportiPrivati.setImportoAnnoTerzo(ii.getImportoAnnoTerzo());

				if (ii.getIntervento() != null) {
					interventoImportiPrivati.setIntervento(ii.getIntervento());
				}
				if(optionalRisorsa.isPresent()) {
					interventoImportiPrivati.setRisorsa(optionalRisorsa.get());
				}
				interventoImportiPrivati.setUtenteCancellazione(ii.getUtenteCancellazione());
				interventoImportiPrivati.setUtenteCreazione(ii.getUtenteCreazione());
				interventoImportiPrivati.setUtenteModifica(ii.getUtenteModifica());

				interventoImportiPrivati.setMotivazione(ii.getMotivazione());
				interventoImportiPrivati.setRichiestaMotivazione(ii.getRichiestaMotivazione());
			}
		}
		return interventoImportiPrivati;
	}
	/**
	 * 
	 * @param cui
	 * @return
	 */
	protected boolean controlloFormaleCui(String cui) {
		boolean ris = Boolean.TRUE;
		if(cui==null || cui.length()!=21 ) {
			ris = Boolean.FALSE;
		} else {
			final String caratterePrimo = cui.substring(0,1);
			if(!(caratterePrimo.substring(0,1).equals("S") || caratterePrimo.substring(0,1).equals("F") || caratterePrimo.substring(0,1).equals("L"))) {
				ris = Boolean.FALSE;
			}
		}
		return ris;
	}
}
