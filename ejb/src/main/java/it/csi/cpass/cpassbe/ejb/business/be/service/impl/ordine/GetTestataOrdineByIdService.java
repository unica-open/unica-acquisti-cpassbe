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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.business.be.service.UtilityVisibilitàDocumentale;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestataOrdineByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestataOrdineByIdResponse;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetTestataOrdineByIdService extends BaseTestataOrdineService<GetTestataOrdineByIdRequest, GetTestataOrdineByIdResponse> {

	private ImpegnoDad impegnoDad;
	private SystemDad systemDad;
	private UtenteDad utenteDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetTestataOrdineByIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, ImpegnoDad impegnoDad, SystemDad systemDad,
			UtenteDad utenteDad) {
		super(configurationHelper, testataOrdineDad);
		this.impegnoDad = impegnoDad;
		this.systemDad = systemDad;
		this.utenteDad = utenteDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		// TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdine(request.getId()).orElseThrow(() -> new
		// NotFoundException("testataOrdine"));
		TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId());
		
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		Ente ente = settoreCorrente.getEnte();

		boolean bVisibilitàDocumentale = UtilityVisibilitàDocumentale.isVisibile(utenteDad, testataOrdine);
		if (!bVisibilitàDocumentale) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0044.getError());
		}

		List<Impegno> listImpegno = testataOrdine.getListImpegno();

		// carico impegni collegati per controlli successivi (modifica)
		CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine = UtilityImpegni.verificaPresenzaImpegniCollegatiOrdine(impegnoDad, request.getId());

		// controllo "anno congruente impegni collegati"
		// Se il caso d’uso chiamante è [7] ORD5 Modificare testata ordine e l’ordine ha già degli impegni collegati
		if (cpassTOrdImpegnoOrdine != null) {
			listImpegno = UtilityImpegni.controlloAnnoCongruente(impegnoDad, request.getId(), cpassTOrdImpegnoOrdine, listImpegno);
		}

		// controllo "data_ordini_futuri"
		// Se il caso d’uso chiamante è [6] ORD1 Inserire testata ordine, oppure il caso d’uso chiamante è [7] ORD5 Modificare testata ordine e
		// l’ordine non ha ancora alcun impegno collegato
		if (cpassTOrdImpegnoOrdine == null) {
			List<Parametro> parameters = systemDad.getParametriList(null, ConstantsCPassParametro.RiferimentoEnum.IMPEGNO.getCostante(), null, ente.getId());
			Map<String, String> params = new HashMap<String, String>();
			for (Parametro parametro : parameters) {
				params.put(parametro.getChiave(), parametro.getValore());
			}
			listImpegno = UtilityImpegni.controlloDataAnniFuturi(params, listImpegno, testataOrdine.getDataEmissione());
		}

		testataOrdine.setListImpegno(listImpegno);
		response.setTestataOrdine(testataOrdine);
	}

	

}
