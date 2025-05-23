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
import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PutInterventoPrendiInCaricoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PutInterventoPrendiInCaricoResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Retrieves an PostArchivioRupsByIntervento by its id
 */
public class PutInterventoPrendiInCaricoService extends BaseService<PutInterventoPrendiInCaricoRequest, PutInterventoPrendiInCaricoResponse> {

	private final InterventoDad interventoDad;
	private final UtenteDad utenteDad;
	public PutInterventoPrendiInCaricoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad) {
		super(configurationHelper);
		this.interventoDad = interventoDad;
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdRup(), "id rup");
		checkNotNull(request.getListaInterventi(), "lista interventi");
	}

	@Override
	protected void execute() {
		final List<Intervento> listaInterventi = request.getListaInterventi();
		final Utente utenteRupNuovo = utenteDad.getUtente(request.getIdRup()).orElseThrow(() -> new NotFoundException("utente rup"));
		final List<Intervento> listaInterventiNew  = new ArrayList<>();
		for(final Intervento intOld : listaInterventi) {
			final Intervento intervento = interventoDad.getInterventoOpt(intOld.getId()).orElseThrow(() -> new NotFoundException("intervento"));
			if(intervento.getUtenteRup() !=null && intervento.getUtenteRup().getId()!= null) {
				final Optional<Utente> utenteRupPrecedenteOpt = utenteDad.getUtenteById(intervento.getUtenteRup().getId());
				checkBusinessCondition(utenteRupPrecedenteOpt.isPresent(),CoreError.GENERIC_ERROR.getError("error","Il RUP precedente non è censito a sistema "  +intervento.getUtenteRup().getCodiceFiscale()  ));
				intervento.setUtenteRup(utenteRupNuovo);
				final Intervento intnew = interventoDad.updateIntervento(intervento);
				listaInterventiNew.add(intnew);
				interventoDad.flushAndClear();
				if(utenteRupPrecedenteOpt.isPresent()) {
					interventoDad.archivioRup(intnew,utenteRupPrecedenteOpt.get());
				}
				interventoDad.flushAndClear();
			}
		}
		response.setInterventi(listaInterventiNew);

	}
}
