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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.bo;

//import com.sun.jersey.core.header.FormDataContentDisposition;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.FileCsvAggiornaOdsHolder;
/**
 * Request for posting the Intervento
 */
public class PostUploadCsvAggiornamentoOdsRequest implements BaseRequest {

	private String separatore;
	//private UUID idEnte;
	private byte[] attachmentCsv;
	private Boolean sostituisciRegola;

	/**
	 * @param file the file
	 */
	public PostUploadCsvAggiornamentoOdsRequest(FileCsvAggiornaOdsHolder file) {
		this.separatore = file.getSeparatore();
		//this.idEnte = file.getIdEnte();
		this.attachmentCsv=file.getAttachment();
		this.separatore=file.getSeparatore();
	}


	/**
	 * @return the attachmentCsv
	 */
	public byte[] getAttachmentCsv() {
		return attachmentCsv;
	}

	/**
	 * @param attachmentCsv the attachmentCsv to set
	 */
	public void setAttachmentCsv(byte[] attachmentCsv) {
		this.attachmentCsv = attachmentCsv;
	}

	/**
	 * @return the separatore
	 */
	public String getSeparatore() {
		return separatore;
	}



	/**
	 * @param separatore the separatore to set
	 */
	public void setSeparatore(String separatore) {
		this.separatore = separatore;
	}

	/**
	 * @return the sostituisciRegola
	 */
	public Boolean getSostituisciRegola() {
		return sostituisciRegola;
	}

	/**
	 * @param sostituisciRegola the sostituisciRegola to set
	 */
	public void setSostituisciRegola(Boolean sostituisciRegola) {
		this.sostituisciRegola = sostituisciRegola;
	}


}
