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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.NotificheDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoVolturaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoVolturaResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche;
import it.csi.cpass.cpassbe.ejb.util.ConstantsDecodifiche.NotificaEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Notifica;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.TestoNotifica;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;
import it.csi.cpass.cpassbe.lib.util.serialization.JsonUtility;

/**
 * Retrieves an PostArchivioRupsByIntervento by its id
 */
public class PutInterventoVolturaService extends BaseService<PutInterventoVolturaRequest, PutInterventoVolturaResponse> {

	private final InterventoDad interventoDad;
	private final UtenteDad utenteDad;
	private final SettoreDad settoreDad;
	private final NotificheDad notificheDad;
	public PutInterventoVolturaService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad, SettoreDad settoreDad,NotificheDad notificheDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;
		this.utenteDad = utenteDad;
		this.settoreDad = settoreDad;
		this.notificheDad= notificheDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdSettore(), "id settore");
		checkNotNull(request.getIdRup(), "id rup");
		checkNotNull(request.getListaInterventi(), "lista interventi");
	}

	@Override
	protected void execute() {
		final List<Intervento> listaInterventi = request.getListaInterventi();
		final Utente utenteRupNuovo = utenteDad.getUtente(request.getIdRup()).orElseThrow(() -> new NotFoundException("utente rup"));
		final Settore settoreNuovo = settoreDad.findOne(request.getIdSettore());
		if (settoreNuovo == null) {
			throw new NotFoundException("settore");
		}

		final List<Intervento> listaInterventiNew  = new ArrayList<>();

		for(final Intervento intOld : listaInterventi) {
			final Intervento intervento = interventoDad.getInterventoOpt(intOld.getId()).orElseThrow(() -> new NotFoundException("intervento"));

			if(intervento.getUtenteRup() !=null && intervento.getUtenteRup().getId()!= null) {
				final Optional<Utente> utenteRupPrecedenteOpt = utenteDad.getUtenteById(intervento.getUtenteRup().getId());
				checkBusinessCondition(utenteRupPrecedenteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Il RUP precedente non è censito a sistema "  +intervento.getUtenteRup().getCodiceFiscale()  ));
				intervento.setUtenteRup(utenteRupNuovo);
				intervento.setSettore(settoreNuovo);
				final Intervento intnew = interventoDad.updateIntervento(intervento);
				listaInterventiNew.add(intnew);
				interventoDad.flushAndClear();
				if(utenteRupPrecedenteOpt.isPresent()) {
					interventoDad.archivioRup(intnew,utenteRupPrecedenteOpt.get());
				}
				interventoDad.flushAndClear();
			}
		}

		final Map<UUID, List<Intervento>> map = listaInterventi.stream().collect(Collectors.groupingBy(a-> a.getUtenteRup().getId()));
		map.forEach((k, v) -> inserisciNotificaUtente(NotificaEnum.N0005.getCodice(),v.get(0).getUtenteRup(), v)); // notifica per vecchio rup

		inserisciNotificaUtente(NotificaEnum.N0006.getCodice(), utenteRupNuovo, listaInterventi); // notifica per nuovo rup
		response.setInterventi(listaInterventiNew);

	}

	private void inserisciNotificaUtente(String codiceNotifica, Utente utente, List<Intervento> interventos) {
		log.info("inserisciNotificaPerUtente","NOTIFICA  -> "+codiceNotifica+" per utente -> "+utente.getCodiceFiscale());

		final TestoNotifica testoNotifica = isEntityPresent(() -> notificheDad.getTestoNotifica(codiceNotifica),"testoNotifica");

		final Notifica notifica = new Notifica();
		notifica.setFonte(ConstantsDecodifiche.NotificaFonteEnum.PBA.getCodice());
		notifica.setEntitaTipo(ConstantsDecodifiche.NotificaTipoEntitaEnum.PBA.getCodice());
		final Date now = new Date();
		notifica.setDataInizio(now);
		notifica.setFlgGenerico(false);

		final Map<String,Object> parametri = new HashMap<>();

		final int limit = 20;
		String numeri_cui = interventos.stream().limit(limit).map(i -> i.getCui()).collect(Collectors.joining(", "));
		if (interventos.size()>limit) {
			numeri_cui = numeri_cui + " ... ";
		}
		parametri.put("numeri_cui",numeri_cui);

		notifica.setParametri(JsonUtility.serialize(parametri));
		notifica.setTestoNotifica(testoNotifica);
		notificheDad.saveNotificaUtente(notifica, utente);
	}
}
