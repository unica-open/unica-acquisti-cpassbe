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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.UtenteDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.GetTestataOrdineByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.GetTestataOrdineByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityVisibilitaDocumentale;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.ProtocolloOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetTestataOrdineByIdService extends BaseOrdineService<GetTestataOrdineByIdRequest, GetTestataOrdineByIdResponse> {

	private final ImpegnoDad impegnoDad;
	private final SystemDad systemDad;
	private final UtenteDad utenteDad;
	private final DocumentaleDad documentaleDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetTestataOrdineByIdService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad, ImpegnoDad impegnoDad, SystemDad systemDad,
			UtenteDad utenteDad,SettoreDad settoreDad,DocumentaleDad documentaleDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.impegnoDad = impegnoDad;
		this.systemDad = systemDad;
		this.utenteDad = utenteDad;
		this.documentaleDad = documentaleDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		// TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdine(request.getId()).orElseThrow(() -> new
		// NotFoundException("testataOrdine"));
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();
		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getId(),ente.getId());




		final boolean bVisibilitaDocumentale = UtilityVisibilitaDocumentale.isVisibile(utenteDad, testataOrdine);
		if (!bVisibilitaDocumentale) {
			checkBusinessCondition(false, MsgCpassOrd.ORDORDE0044.getError());
		}
		List<Impegno> listImpegno = testataOrdine.getListImpegno();

		// carico impegni collegati per controlli successivi (modifica)
		final CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine = UtilityImpegni.verificaPresenzaImpegniCollegatiOrdine(impegnoDad, request.getId());

		// controllo "anno congruente impegni collegati"
		// Se il caso d’uso chiamante è [7] ORD5 Modificare testata ordine e l’ordine ha già degli impegni collegati
		if (cpassTOrdImpegnoOrdine != null) {
			listImpegno = UtilityImpegni.controlloAnnoCongruente(impegnoDad, request.getId(), cpassTOrdImpegnoOrdine, listImpegno);
		}

		// controllo "data_ordini_futuri"
		// Se il caso d’uso chiamante è [6] ORD1 Inserire testata ordine, oppure il caso d’uso chiamante è [7] ORD5 Modificare testata ordine e
		// l’ordine non ha ancora alcun impegno collegato
		if (cpassTOrdImpegnoOrdine == null) {
			final List<Parametro> parameters = systemDad.getParametriList(null, ConstantsCPassParametro.RiferimentoEnum.IMPEGNO.getCostante(), null, ente.getId());
			final Map<String, String> params = new HashMap<>();
			for (final Parametro parametro : parameters) {
				params.put(parametro.getChiave(), parametro.getValore());
			}
			listImpegno = UtilityImpegni.controlloDataAnniFuturi(params, listImpegno, testataOrdine.getDataEmissione());
		}

		testataOrdine.setListImpegno(listImpegno);
		final List<TestataRda> listTestataRda = testataOrdineDad.getTestataRdaByTestataOrdineId(request.getId());
		testataOrdine.setRdas(listTestataRda);

		final List<ProtocolloOrdine> listProtocolloOrdine = documentaleDad.getProtocolloOrdineByTestataOrdineId(request.getId(),ente.getId());
		testataOrdine.setProtocolloOrdines(listProtocolloOrdine);

		//Derivato Check-box Se esiste un’occorrenza dell’id dell’ordine su CPASS_R_ORD_RDA_ORDINE, spuntare il check-box, altrimenti no
		//Anno Rda Int CPASS_T_ORD_TESTATA_RDA.anno_rda, dopo aver cercato l’id. ordine in CPASS_R_ORD_RDA_ORDINE e ricavato l’id RDA
		//Numero Rda Int CPASS_T_ORD_TESTATA_RDA.numero_rda, dopo aver cercato l’id. ordine in CPASS_R_ORD_RDA_ORDINE e ricavato l’id RDA
		if(listTestataRda.size()>0) {
			testataOrdine.setDerivato(Boolean.TRUE);
			testataOrdine.setAnnoRda(listTestataRda.get(0).getAnno());
			testataOrdine.setNumeroRda(listTestataRda.get(0).getNumero());
		}else {
			testataOrdine.setDerivato(Boolean.FALSE);
		}

		if(testataOrdine.getProtocolloOrdines()!= null &&	testataOrdine.getProtocolloOrdines().size()>0) {
			final ProtocolloOrdine protocollo = testataOrdine.getProtocolloOrdines().get(0);
			testataOrdine.setProtocollato(Boolean.TRUE);
			testataOrdine.setAnnoProtocollo(protocollo.getAnnoProtocollo());
			testataOrdine.setNumeroProtocollo(protocollo.getNumeroProtocollo());
			testataOrdine.setAoo(protocollo.getAoo());
			//ProtocollatoCheck-box Se esiste un’occorrenza dell’id dell’ordine su CPASS_T_ORD_PROTOCOLLO, spuntare il check-box, altrimenti no
			//Anno protocollo Int CPASS_T_ORD_PROTOCOLLO.protocollo_ordine_anno , matchando con CPASS_T_ORD_TESTATA.testata_ordine_id
			//Numero protocollo Int CPASS_T_ORD_PROTOCOLLO.protocollo_ordine_numero , matchando con CPASS_T_ORD_TESTATA:ORDINE.testata_ordine_id
			//AOO Char CPASS_T_ORD_PROTOCOLLO.protocollo_ordine_aoo, matchando con CPASS_T_ORD_TESTATA_ORDINE.testata_ordine_id
			//CIG Char CPASS_T_ORD_TESTATA_ORDINE.cig
			//Motivo esclusione CIG Combo-box Popolato con la descrizione di CPASS_D_MOTIVO_ESCLUSIONE_CIG corrispondente a CPASS_T_ORD_TESTATA_ORDINE.motivo_esclusione_cig_id

		}

		response.setTestataOrdine(testataOrdine);
	}



}
