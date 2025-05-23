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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaSettoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaSettoreResponse;
import it.csi.cpass.cpassbe.ejb.util.CpassEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

public class PostRicercaSettoreService extends BaseService<PostRicercaSettoreRequest, PostRicercaSettoreResponse> {

	protected final SettoreDad settoreDad;
	private Settore settore;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 */
	public PostRicercaSettoreService(ConfigurationHelper configurationHelper, SettoreDad settoreDad) {
		super(configurationHelper);
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		settore = request.getSettore();
		checkNotNull(settore, "settore", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final UUID idSettoreRadice = request.getIdSettoreRadice();

		PagedList<Settore> pagedList = settoreDad.postRicercaSettori(   request.getPage(),
				request.getSize(),
				request.getSort(),
				request.getSettore(),
				Boolean.TRUE
				);

		if (idSettoreRadice != null) {
			final List<Settore> filteredList = new ArrayList<>();
			for (final Settore settore: pagedList.getList()) {
				if (settore.getId().equals(idSettoreRadice) || verificaPadre(settore, idSettoreRadice)) {
					filteredList.add(settore);
				}
			}
			//PagedList tmpPagedList = new PagedListImpl(filteredList);
			//pagedList = tmpPagedList;
			pagedList = new PagedListImpl<>(filteredList);
		}

		for (final Settore settore: pagedList.getList()) {
			final List<SettoreIndirizzo> indirizziPrincipale = settore.getSettoreIndirizzos().stream().filter(el -> el.getPrincipale()).collect(Collectors.toList());
			if(!indirizziPrincipale.isEmpty()) {
				final SettoreIndirizzo indirizzoPrincipale = indirizziPrincipale.get(0);
				settore.setIndirizzoSettorePrincipale(indirizzoPrincipale.getIndirizzo());
				settore.setNumCivicoSettorePrincipale(indirizzoPrincipale.getNumCivico());
				settore.setLocalitaSettorePrincipale(indirizzoPrincipale.getLocalita());
				settore.setProvinciaSettorePrincipale(indirizzoPrincipale.getProvincia());
				settore.setCapSettorePrincipale(indirizzoPrincipale.getCap());
				settore.setTelefonoSettorePrincipale(indirizzoPrincipale.getTelefono());
				settore.setSede(CpassEnum.PRINCIPALE.getCostante());
			}
		}

		response.setSettori(pagedList);
	}

	private boolean verificaPadre(Settore settore, UUID idSettoreRadice) {
		Settore tmp = settore;
		while (tmp.getSettorePadre()!=null && tmp.getSettorePadre().getId() != null) {
			if (tmp.getSettorePadre().getId().equals(idSettoreRadice)) {
				return true;
			}else {
				tmp = settoreDad.findById(tmp.getSettorePadre().getId());
			}
		}
		return false;
	}

}
