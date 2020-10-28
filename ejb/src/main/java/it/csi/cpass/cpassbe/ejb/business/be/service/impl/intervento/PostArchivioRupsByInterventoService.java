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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.intervento;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostArchivioRupsByInterventoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostArchivioRupsByInterventoResponse;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRPbaStoricoInterventoRup;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Retrieves an PostArchivioRupsByIntervento by its id
 */
public class PostArchivioRupsByInterventoService extends BaseService<PostArchivioRupsByInterventoRequest, PostArchivioRupsByInterventoResponse> {

    private InterventoDad interventoDad;
    private UtenteDad utenteDad;
	public PostArchivioRupsByInterventoService(ConfigurationHelper configurationHelper, InterventoDad interventoDad, UtenteDad utenteDad) {
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
		List<Intervento> listaInterventi = request.getListaInterventi();
		Utente utenteRupNuovo = utenteDad.getUtente(request.getIdRup()).orElseThrow(() -> new NotFoundException("utente rup"));		
		List<Intervento> listaInterventiNew  = new ArrayList<Intervento>();
		
		for(Intervento intOld : listaInterventi) {
			Intervento intervento = interventoDad.getIntervento(intOld.getId()).orElseThrow(() -> new NotFoundException("intervento"));		

			Utente utenteRupPrecedente = utenteDad.getUtente(intervento.getUtenteRup().getId()).orElseThrow(() -> new NotFoundException("utente rup old"));		
			intervento.setUtenteRup(utenteRupNuovo);
			Intervento intnew = interventoDad.updateIntervento(intervento);
			
			listaInterventiNew.add(intnew);
			interventoDad.flushAndClear();

			//salvo il nuovo rup
			CpassRPbaStoricoInterventoRup ar = interventoDad.archivioRup(intnew,utenteRupPrecedente);
			interventoDad.flushAndClear();

		}
		
		response.setInterventi(listaInterventiNew);
	
	}

}
