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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.rms;

import java.util.UUID;

//import com.sun.jersey.core.header.FormDataContentDisposition;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.FileCsvRegoleHolder;
/**
 * Request for posting the Intervento
 */
public class PostUploadCsvRegoleSmistamentoRmsRequest implements BaseRequest {

	private String separatore;
	private UUID idEnte;
	private byte[] attachmentCsv;
	private Boolean sostituisciRegola;

	/**
	 * @param file the file
	 */
	public PostUploadCsvRegoleSmistamentoRmsRequest(FileCsvRegoleHolder file) {
		this.separatore = file.getSeparatore();
		this.idEnte = file.getIdEnte();
		this.attachmentCsv=file.getAttachment();
		this.separatore=file.getSeparatore();
		this.sostituisciRegola=file.getSostituisciRegola();
	}

	/**
	 * @return the ente
	 */
	public UUID getIdEnte() {
		return idEnte;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(UUID ente) {
		this.idEnte = ente;
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
	 * @param idEnte the idEnte to set
	 */
	public void setIdEnte(UUID idEnte) {
		this.idEnte = idEnte;
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
