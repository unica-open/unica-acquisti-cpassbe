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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutAvviaInterventoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutAvviaInterventoByIdResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoInterventiEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoAltriDati;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoCig;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Saves an Intervento
 */
public class PutAvviaInterventoByIdService extends BaseInterventoService<PutAvviaInterventoByIdRequest, PutAvviaInterventoByIdResponse> {

	UtenteDad utenteDad;

	public PutAvviaInterventoByIdService(ConfigurationHelper configurationHelper, InterventoDad interventoDad,UtenteDad utenteDad) {
		super(configurationHelper, interventoDad);
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull( request.getInterventoId(),"id intervento");
	}

	@Override
	protected void execute() {
		final String methodName = "execute";
		log.info(methodName, "Start");
		final UUID interventoId = request.getInterventoId();
		final List<String> cigs = request.getCigs();
		final Integer motivoEsclusioneCigId = request.getMotivoEsclusioneCigId();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Intervento interventoAttuale = isEntityPresent(() -> interventoDad.getInterventoOpt(request.getInterventoId()), "intervento");
		final List<InterventoCig> cigsInterventoAttuale = interventoDad.getCigByInterventoId(interventoAttuale.getId());
		final List<String> cigsInterventoAttualeString = new ArrayList<>();

		// gestione cig
		if(!cigsInterventoAttuale.isEmpty()) {
			for (final InterventoCig cig : cigsInterventoAttuale) {
				cigsInterventoAttualeString.add(cig.getCig());
			}
			if (!cigsInterventoAttualeString.equals(cigs)) {
				final List<String> cigsDaAggiungere = new ArrayList<>();
				for (final String cig : cigs) {
					if (!cigsInterventoAttualeString.contains(cig)) {
						cigsDaAggiungere.add(cig);
					}
				}
				final List<String> diffCigs = new ArrayList<>(cigsInterventoAttualeString);
				diffCigs.removeAll(cigs);
				for (final String cig : diffCigs) {
					interventoDad.deleteByInterventoIdAndCig(interventoAttuale.getId(), cig);
				}
				if(!cigsDaAggiungere.isEmpty()) {
					interventoDad.postCigByInterventoId(interventoAttuale.getId(), cigsDaAggiungere);
				}
			}
		} else {
			interventoDad.postCigByInterventoId(interventoId, cigs);
		}

		//interventoAttuale
		interventoAttuale.setAvviato(Boolean.TRUE);
		interventoAttuale.setUtenteAvviato(utenteConnesso);
		interventoAttuale.setDataAvviato(new Date());
		interventoDad.updateInterventoMinimal(interventoAttuale);
		interventoDad.salvaStoricoStatoIntervento(utenteConnesso,interventoAttuale , StatoInterventiEnum.AVVIATO.getCostante());
		//TODO gestire il motivo esclusione
		if(motivoEsclusioneCigId !=null && motivoEsclusioneCigId>0) {
			final List<InterventoAltriDati> listaAd = interventoAttuale.getListInterventoAltriDati();
			if (listaAd == null || listaAd.size() == 0) {
				final InterventoAltriDati intAltridati = new InterventoAltriDati();
				intAltridati.setIntervento(interventoAttuale);
				final MotiviEsclusioneCig motiviEsclusioneCig = new MotiviEsclusioneCig();
				motiviEsclusioneCig.setId(motivoEsclusioneCigId);
				intAltridati.setMotiviEsclusioneCig(motiviEsclusioneCig);
				interventoDad.saveAltriDati(intAltridati);
			} else {
				for (final InterventoAltriDati ad : listaAd) {
					final MotiviEsclusioneCig motiviEsclusioneCig = new MotiviEsclusioneCig();
					motiviEsclusioneCig.setId(motivoEsclusioneCigId);
					ad.setMotiviEsclusioneCig(motiviEsclusioneCig);
					interventoDad.saveAltriDati(ad);
				}
			}
		} else {
			final List<InterventoAltriDati> listaAd = interventoAttuale.getListInterventoAltriDati();
			for (final InterventoAltriDati ad : listaAd) {
				if(ad.getMotiviEsclusioneCig() != null) {
					ad.setMotiviEsclusioneCig(null);
				}
				interventoDad.saveAltriDati(ad);
			}
		}

		log.info(methodName, "Stop");
	}

}
