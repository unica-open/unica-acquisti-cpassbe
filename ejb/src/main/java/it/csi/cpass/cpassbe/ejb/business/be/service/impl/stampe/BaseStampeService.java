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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.stampe;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.http.NameValuePair;

import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseStampaResponse;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.StringUtility;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationValue;
import it.csi.cpass.cpassbe.ejb.util.mime.MimeTypeContainer;
import it.csi.cpass.cpassbe.ejb.util.report.ReportServletInvoker;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Base class for services for /utente path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseStampeService<Q extends BaseRequest, R extends BaseStampaResponse> extends BaseService<Q, R> {


	/** Data Access Delegate for Intervento */
	protected final StampeDad stampeDad;
	protected final SystemDad systemDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param stampeDad
	 */
	protected BaseStampeService(ConfigurationHelper configurationHelper, StampeDad stampeDad, SystemDad systemDad) {
		super(configurationHelper);
		this.stampeDad = stampeDad;
		this.systemDad = systemDad;
	}



	/**
	 * Gets the MIME type containers
	 * @param formatFile the file format
	 * @return the MIME Type Container
	 */
	protected MimeTypeContainer getMimeTypeContainer(String formatFile) {
		if(formatFile.equals("xlsx") || formatFile.equals("xls")) {
			return MimeTypeContainer.EXCEL_WORKBOOK;
		}
		if(formatFile.equals("pdf"))  {
			return MimeTypeContainer.PDF;
		}
		if(formatFile.equals("zip"))  {
			return MimeTypeContainer.ZIP;
		}
		if(formatFile.equals("docx") || formatFile.equals("doc")) {
			return MimeTypeContainer.EXCEL_WORKBOOK;
		}
		return null;
	}

	/**
	 * BIRT invocation
	 * @param formData the form data
	 * @param reportName the report name
	 * @param formatFile the file format
	 */
	protected void callBirt(List<NameValuePair> formData, String reportName,String fileNameWithoutExtension, String formatFile) {
		final String methodName = "callBirt";
		final ReportServletInvoker invoker = new ReportServletInvoker();
		try {
			// *****************************************************************************************************************
			// TODO IMPORTANTE!!!!!!!!!!!!!!! i parametri null passati al generatore di stampa vengono interpretati come 'null'
			// per cui è necessario tenerne conto nella stesura della procedura associata alla stampa
			// *****************************************************************************************************************
			final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
			final Parametro reportEndpointDB = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.report_endpoint.getCostante(),null, enteId);
			byte[] res = null;

			if(reportEndpointDB == null || StringUtility.isEmpty(reportEndpointDB.getValore())) {
				log.error("par 1 ", "chiave-->"+ConstantsCPassParametro.ChiaveEnum.report_endpoint.getCostante()+"*");
				log.error("par 2 ", "riferimento-->"+null+"*");
				log.error("par 3 ", "enteId-->"+enteId+"*");
				log.error(methodName, "ATTENZIONE il Parametro report_endpoint non è settato correttamente sul DB");
				checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", "ATTENZIONE il Parametro report_endpoint non è settato correttamente sul DB"));
			}else {
				String url =reportEndpointDB.getValore() + "-old";
				// Se sono in locale la url la leggo dal file di properties	(utile per i test)
				if(!StringUtility.isEmpty(configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT))) {
					url = configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT) + "-old";
				}
				res = invoker.invoke(url, reportName, formatFile, formData);
			}

			response.setMimeTypeContainer(MimeTypeContainer.byExtension(formatFile));
			response.setBytes(res);
			response.setFileNameTemplate(fileNameWithoutExtension);
		} catch (final IOException e) {
			log.error(methodName, "Error in BIRT call: " + e.getMessage(), e);
			checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", e.getMessage()));
		}
	}

	/**
	 * BIRT invocation
	 * @param formData the form data
	 * @param reportName the report name
	 * @param formatFile the file format
	 */
	//protected void callBirt(String objParams, String reportName, String formatFile) {

	protected void callBirt(String objParams, String reportName, String fileNameWithoutExtension, String formatFile) {
		final String methodName = "callBirt";
		final ReportServletInvoker invoker = new ReportServletInvoker();
		try {
			// *****************************************************************************************************************
			// TODO IMPORTANTE!!!!!!!!!!!!!!! i parametri null passati al generatore di stampa vengono interpretati come 'null'
			// per cui è necessario tenerne conto nella stesura della procedura associata alla stampa
			// *****************************************************************************************************************

			//TODO da gestire con parametro a DB
			final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
			final Parametro reportEndpointDB = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.report_endpoint.getCostante(),null, enteId);
			byte[] res = null;
			if(reportEndpointDB == null || StringUtility.isEmpty(reportEndpointDB.getValore())) {
				log.error(methodName, "ATTENZIONE il Parametro report_endpoint non è settato correttamente sul DB");
				checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", "ATTENZIONE il Parametro report_endpoint non è settato correttamente sul DB"));
			}else {
				String url =reportEndpointDB.getValore() ;
				// Se sono in locale la url la leggo dal file di properties	(utile per i test)
				if(!StringUtility.isEmpty(configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT))) {
					url = configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT);
				}
				log.info(methodName, "url " + url);
				res = invoker.invoke(url,  objParams,fileNameWithoutExtension);
			}

	/*
			final Parametro reportEndpoint = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.report_endpoint.getCostante(),null, enteId);
			byte[] res = null;
			if(reportEndpoint != null && reportEndpoint.getValore()!=null && !reportEndpoint.getValore().trim().equals("")) {
				log.info(methodName, "passo dal nuovo ramo leggendo dal db " + reportEndpoint.getValore());
				//res = invoker.invoke(reportEndpoint.getValore() , reportName, formatFile);
				//da commentare
				res = invoker.invoke(configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT) , reportName, formatFile);

			}else {
				log.info(methodName, "passo dal VECCHIO ramo leggendo dal properties " + configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT));
				res = invoker.invoke(configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT),  objParams,fileNameWithoutExtension);
			}
*/

			response.setMimeTypeContainer(MimeTypeContainer.byExtension(formatFile));
			response.setBytes(res);
			response.setFileNameTemplate(fileNameWithoutExtension);

		} catch (final IOException e) {
			log.error(methodName, "Error in BIRT call: " + e.getMessage(), e);
			checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", e.getMessage()));
		}
	}

	protected void callBirtMulti(String objParams, String reportName, String fileNameWithoutExtension, String formatFile) {
		final String methodName = "callBirtMulti";
		final ReportServletInvoker invoker = new ReportServletInvoker();
		try {
			// *****************************************************************************************************************
			// TODO IMPORTANTE!!!!!!!!!!!!!!! i parametri null passati al generatore di stampa vengono interpretati come 'null'
			// per cui è necessario tenerne conto nella stesura della procedura associata alla stampa
			// *****************************************************************************************************************

			//TODO da gestire con parametro a DB
			final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
			final Parametro reportEndpointDB = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.report_multi_endpoint.getCostante(),null, enteId);
			byte[] res = null;

			if(reportEndpointDB == null || StringUtility.isEmpty(reportEndpointDB.getValore())) {
				log.error(methodName, "ATTENZIONE il Parametro report.multi.endpoint non è settato correttamente sul DB");
				checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", "ATTENZIONE il Parametro report.multi.endpoint non è settato correttamente sul DB"));
			}else {
				String url =reportEndpointDB.getValore() ;
				// Se sono in locale la url la leggo dal file di properties	(utile per i test)
				if(!StringUtility.isEmpty(configurationHelper.getProperty(ConfigurationValue.REPORT_MULTI_ENDPOINT))) {
					url = configurationHelper.getProperty(ConfigurationValue.REPORT_MULTI_ENDPOINT);
				}
				log.info(methodName, "url " + url);
				res = invoker.invoke(url,  objParams,fileNameWithoutExtension);
			}

			/*
			if(reportEndpoint != null && reportEndpoint.getValore()!=null && !reportEndpoint.getValore().trim().equals("")) {
				log.info(methodName, "passo dal nuovo ramo leggendo dal db " + reportEndpoint.getValore());
				//res = invoker.invoke(reportEndpoint.getValore() , reportName, formatFile);
				//da commentare
				//res = invoker.invoke(configurationHelper.getProperty(ConfigurationValue.REPORT_MULTI_ENDPOINT) , reportName, formatFile);
				res = invoker.invoke(configurationHelper.getProperty(ConfigurationValue.REPORT_MULTI_ENDPOINT),  objParams,fileNameWithoutExtension);
			}else {
				log.info(methodName, "passo dal VECCHIO ramo leggendo dal properties " + configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT));
				res = invoker.invoke(configurationHelper.getProperty(ConfigurationValue.REPORT_MULTI_ENDPOINT),  objParams,fileNameWithoutExtension);
			}

			*/
			//response.setMimeTypeContainer(MimeTypeContainer.byExtension(formatFile));
			response.setMimeTypeContainer(MimeTypeContainer.byExtension(formatFile));
			response.setBytes(res);
			response.setFileNameTemplate(fileNameWithoutExtension);

		} catch (final IOException e) {
			log.error(methodName, "Error in BIRT call: " + e.getMessage(), e);
			checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", e.getMessage()));
		}
	}
}
