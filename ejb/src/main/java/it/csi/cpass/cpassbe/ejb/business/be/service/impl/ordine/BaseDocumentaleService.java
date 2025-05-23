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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentaleDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseResponse;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRUfficioSerie;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationValue;
import it.csi.cpass.cpassbe.ejb.util.report.ReportServletInvoker;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.ParametroStampa;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.ord.AooActa;
import it.csi.cpass.cpassbe.lib.dto.ord.DocumentiOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.FileDocumentoCopiaXFornitore;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Base class for services for /testataOrdine path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseDocumentaleService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for TestataOrdine */
	protected final TestataOrdineDad testataOrdineDad;
	protected DocumentaleDad documentaleDad;
	protected SettoreDad settoreDad;
	protected CommonDad commonDad;
	protected StampeDad stampeDad;
	protected SystemDad systemDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad the testataOrdine dad
	 */
	protected BaseDocumentaleService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad,DocumentaleDad documentaleDad,SettoreDad settoreDad,CommonDad commonDad,StampeDad stampeDad,SystemDad systemDad) {
		super(configurationHelper);
		this.testataOrdineDad = testataOrdineDad;
		this.documentaleDad = documentaleDad;
		this.settoreDad = settoreDad;
		this.commonDad = commonDad;
		this.stampeDad = stampeDad;
		this.systemDad = systemDad;
	}

	protected Long getAooBySettore(UUID idsettore,UUID enteId) {
		final Optional<AooActa> aooOpt = settoreDad.getAooByIdSettore(idsettore,enteId);
		if(aooOpt.isPresent()) {
			return (long) aooOpt.get().getAooActaOrigId();
		}
		return null;
	}
	protected Ufficio getUfficioById(Integer uffId) {
		final Ufficio ufficio = commonDad.getUfficioById(uffId);
		return ufficio;
	}
	protected String getSerieTipologicaDaLocale(Integer ufficioId) {
		final List<CpassRUfficioSerie> ris = documentaleDad.getUfficioSerieByUfficioId(ufficioId);
		if(ris == null || ris.size()== 0) {
			return null;
		}
		return ris.get(0).getUuidSerieActa();
	}
	protected byte[] getFileDocByTestataOrdineId(UUID testataOrdineId) {
		final String methodName = "getFileDocByTestataOrdineId";
		// TODO Auto-generated method stub
		final List<DocumentiOrdine> ris = testataOrdineDad.getDocumentiByOrdineTestataId(testataOrdineId,false);
		if(ris != null && ris.size()>0){
			return ris.get(0).getFileDocumentoByte();
		}
		//stream della stampa Copia per Fornitore
		final String formatoFile = "pdf";
		final List<ParametroStampa> parametri = stampeDad.getParametriStampeByNomeStampa("PRT_T_ORD");
		final List<NameValuePair> params = new ArrayList<>();
		parametri.get(0).getNomeStampa();
		final String nomeTemplate = parametri.get(0).getFileNameTemplate();
		final String fileName     = parametri.get(0).getFileName();

		for(int i = 0; i < parametri.size(); i++) {
			final ParametroStampa ps = parametri.get(i);
			params.add(new BasicNameValuePair(ps.getParametro(), testataOrdineId.toString()));
		}

		log.info(methodName, "prima stampa creata");
		final byte[] stampa = callBirt(params, nomeTemplate, fileName, formatoFile).getBytes();
		log.info(methodName, "stampa creata");
		return stampa;
	}

	private FileDocumentoCopiaXFornitore callBirt(List<NameValuePair> formData, String reportName,String fileNameWithoutExtension, String formatFile) {
		final String methodName = "callBirt";
		final FileDocumentoCopiaXFornitore response = new FileDocumentoCopiaXFornitore();
		final ReportServletInvoker invoker = new ReportServletInvoker();
		try {

			UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
			log.info(methodName, "verifico sul db che esista il parametro ");
			//ConstantsCPassParametro.ChiaveEnum.report_endpoint.getCostante();
			//Parametro reportEndpoint = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.report_endpoint.getCostante(),null, enteId);
			byte[] res = null;
			final Parametro reportEndpointDB = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.report_endpoint.getCostante(),null, enteId);

			if(reportEndpointDB == null || StringUtility.isEmpty(reportEndpointDB.getValore())) {
				log.error(methodName, "ATTENZIONE il Parametro report_endpoint non è settato correttamente sul DB");
				checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", "ATTENZIONE il Parametro report_endpoint non è settato correttamente sul DB"));
			}else {
				// Se sono in locale la url la leggo dal file di properties	(utile per i test)
				String url =reportEndpointDB.getValore() + "-old";
				if(!StringUtility.isEmpty(configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT))) {
					url = configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT) + "-old";
				}
				res = invoker.invoke(url, reportName, formatFile, formData);
				//res = invoker.invoke(configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT) + "-old", reportName, formatFile, formData);
			}
			response.setBytes(res);
			response.setFileNameTemplate(fileNameWithoutExtension);
		} catch (final IOException e) {
			log.error(methodName, "Error in BIRT call: " + e.getMessage(), e);
			checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", e.getMessage()));
		}
		return response;
	}
	protected String getAutoreGiuridico(Ente ente,UUID id, Settore settorePadre) {
		final Settore settore = settoreDad.findById(id);
		String ris = "";
		if(settore.getSettorePadre() == null) {
			ris= ente.getDenominazione()+" - "+ settore.getDescrizione();
		}else {
			final Settore padre = settoreDad.findById(settore.getSettorePadre().getId());
			ris= ente.getDenominazione()+" - "+ padre.getDescrizione()+" - "+ settore.getDescrizione();
		}
		return ris;
	}
	protected String getDestinatarioFisico(Fornitore fornitore) {
		if(fornitore.getNaturaGiuridica()!=null && fornitore.getNaturaGiuridica().equalsIgnoreCase("PF")) {
			return fornitore.getCognome() +" "+ fornitore.getNome()+ " ("+fornitore.getCodiceFiscale()+")";
		}else {
			return "";
		}
	}
	protected String getDestinatarioGiuridico(Fornitore fornitore) {
		if(fornitore.getNaturaGiuridica()!=null && fornitore.getNaturaGiuridica().equalsIgnoreCase("PGI")) {
			return  " (CF:"+fornitore.getCodiceFiscale()+" IVA:"+ fornitore.getPartitaIva()+ " )";
		}else {
			return "";
		}
	}


}
