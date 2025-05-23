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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.InterventoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento.PostControlloCapofilaXCopiaInterventiRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.intervento.PostControlloCapofilaXCopiaInterventiResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.pba.Intervento;

/**
 * Saves an Intervento
 */
public class PostControlloCapofilaXCopiaInterventiService extends BaseInterventoService<PostControlloCapofilaXCopiaInterventiRequest, PostControlloCapofilaXCopiaInterventiResponse> {

	private List<Intervento> interventi;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param interventoDad the intervento DAD
	 */
	public PostControlloCapofilaXCopiaInterventiService(ConfigurationHelper configurationHelper, InterventoDad interventoDad) {
		super(configurationHelper, interventoDad);
	}

	@Override
	protected void checkServiceParams() {
		interventi = request.getInterventi();
	}

	@Override
	protected void execute() {
		final List<UUID> listaId = new ArrayList<>();
		for(final Intervento inerrv : interventi) {
			listaId.add(inerrv.getId());
		}
		final List<Intervento> lista = interventoDad.getInterventiByListaId(listaId);
		final Map<String, Intervento> mapCapofila = new HashMap<>();
		Boolean coerenza = Boolean.TRUE;
		for(final Intervento el : lista) {
			if(el.getCapofila()) {
				mapCapofila.put(el.getId().toString(), el);
			}else {
				if(el.getInterventoCapofila()!= null) {
					final String idCapofilaIndicato = el.getInterventoCapofila().getId().toString();
					final Intervento ris = mapCapofila.get(idCapofilaIndicato);
					if(ris == null) {
						coerenza = Boolean.FALSE;
						break;
					}
				}
			}
		}
		response.setCoerenzacapofila(coerenza);
	}

}
