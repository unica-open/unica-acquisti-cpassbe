/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.mepa;

import java.util.List;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.mepa.GetOrdiniMepaDaCaricareRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.mepa.GetOrdiniMepaDaCaricareResponse;
import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaTestata;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class GetOrdiniMepaDaCaricareService extends
BaseScaricoMepaService<GetOrdiniMepaDaCaricareRequest, GetOrdiniMepaDaCaricareResponse> {

	CommonDad commonDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper   the configuration helper
	 * @param scaricoMepaTestataDad the scarico MEPA dad
	 */
	public GetOrdiniMepaDaCaricareService(ConfigurationHelper configurationHelper,
			ScaricoMepaTestataDad scaricoMepaTestataDad,
			CommonDad commonDad,
			DecodificaDad decodificaDad) {
		super(configurationHelper, scaricoMepaTestataDad, decodificaDad);
		this.commonDad = commonDad;
	}

	/**
	 * Restituisce una lista di testate mepa da caricare relative al settore dell'utente
	 */
	@Override
	protected void execute() {
		final Settore settoreUtente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		if (settoreUtente == null) {
			throw new RuntimeException(GetOrdiniMepaDaCaricareService.class.getName() + " ERROR: settore utente mancante");
		}

		final List<CpassTScaricoMepaTestata> listaNull = scaricoMepaTestataDad.getOrdiniMepaDaCaricareSenzaCodUfficio();

		checkBusinessCondition(!(listaNull!= null && listaNull.size()>0), () -> MsgCpassOrd.ORDORDE0173.getError());
		//ORD	E	0173	Sono presenti in archivio ordini MEPA che non hanno un codice univoco ufficio. Contattare l’assistenza

		PagedList<ScaricoMepaTestata> scaricoMepaTestataList = new PagedListImpl<>();

		final List<String> codiciUfficioList = commonDad.getUfficiBySettore(settoreUtente.getId()).stream().map(ufficio -> { return ufficio.getCodice().toString(); }).collect(Collectors.toList());

		if (codiciUfficioList != null && codiciUfficioList.size() > 0) {
			scaricoMepaTestataList =scaricoMepaTestataDad.getOrdiniMepaDaCaricare(codiciUfficioList, request.getPage(), request.getSize(), request.getSort());
		}

		response.setScaricoMepaTestataPagedList(scaricoMepaTestataList);
	}
}
