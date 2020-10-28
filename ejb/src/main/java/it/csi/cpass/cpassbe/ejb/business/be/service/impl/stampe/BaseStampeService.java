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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.stampe;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import it.csi.cpass.cpassbe.ejb.business.be.dad.StampeDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.base.BaseStampaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationValue;
import it.csi.cpass.cpassbe.ejb.util.mime.MimeTypeContainer;
import it.csi.cpass.cpassbe.ejb.util.report.ReportServletInvoker;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;

/**
 * Base class for services for /utente path
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseStampeService<Q extends BaseRequest, R extends BaseStampaResponse> extends BaseService<Q, R> {


	/** Data Access Delegate for Intervento */
	protected final StampeDad stampeDad;

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param stampeDad 
	 */
	protected BaseStampeService(ConfigurationHelper configurationHelper, StampeDad stampeDad) {
		super(configurationHelper);
		this.stampeDad = stampeDad;
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
	protected void callBirt(List<NameValuePair> formData, String reportName, String formatFile) {
		final String methodName = "callBirt";
		ReportServletInvoker invoker = new ReportServletInvoker();
		try {
			String fileNameTemplate = reportName;
			int iDot = reportName.indexOf(".");
			if (iDot != -1) {
				fileNameTemplate = reportName.substring(0, iDot);
			}
			fileNameTemplate = fileNameTemplate.replace(' ', '_');
			
			byte[] res = invoker.invoke(configurationHelper.getProperty(ConfigurationValue.REPORT_ENDPOINT), reportName, formatFile, formData);
			response.setMimeTypeContainer(MimeTypeContainer.byExtension(formatFile));
			response.setBytes(res);
			response.setFileNameTemplate(fileNameTemplate);
		} catch (IOException e) {
			log.error(methodName, "Error in BIRT call: " + e.getMessage(), e);
			checkBusinessCondition(false, CoreError.SYSTEM_ERROR.getError("error", e.getMessage()));
		}
	}
}

/*
stampa_id sequence
nome_stampa
file_name_template
parametro
*/





