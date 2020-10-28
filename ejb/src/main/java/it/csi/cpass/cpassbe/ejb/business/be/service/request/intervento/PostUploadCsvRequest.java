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
package it.csi.cpass.cpassbe.ejb.business.be.service.request.intervento;

import java.util.UUID;

//import com.sun.jersey.core.header.FormDataContentDisposition;

import it.csi.cpass.cpassbe.ejb.business.be.service.request.base.BaseRequest;
import it.csi.cpass.cpassbe.lib.dto.FileHolder;
/**
 * Request for posting the Intervento
 */
public class PostUploadCsvRequest implements BaseRequest {

	private Integer annoProgramma;
	private Integer versioneProgramma;
	//utente_referente_id da aggiungere
    private String utenteReferenteCf;

	private UUID idEnte;
	private byte[] attachmentInterventi;
	private byte[] attachmentImportiInterventi;
	//private FormDataContentDisposition fileDisposition;
	//private MultipartFormDataInput uploadedInputStream;

	/**
	 * @param file the file
	 */
	public PostUploadCsvRequest(FileHolder file) {
		this.annoProgramma = Integer.valueOf(file.getAnnoProgramma());
		this.versioneProgramma = Integer.valueOf(file.getVersioneProgramma());
		this.utenteReferenteCf = file.getUtenteReferenteCf();
		this.idEnte = file.getIdEnte();
		this.attachmentInterventi=file.getAttachment();
		this.attachmentImportiInterventi=file.getAttachment2();
	}



	/**
	 * @return the annoProgramma
	 */
	public Integer getAnnoProgramma() {
		return annoProgramma;
	}

	/**
	 * @param annoProgramma the annoProgramma to set
	 */
	public void setAnnoProgramma(Integer annoProgramma) {
		this.annoProgramma = annoProgramma;
	}

	/**
	 * @return the versioneProgramma
	 */
	public Integer getVersioneProgramma() {
		return versioneProgramma;
	}

	/**
	 * @param versioneProgramma the versioneProgramma to set
	 */
	public void setVersioneProgramma(Integer versioneProgramma) {
		this.versioneProgramma = versioneProgramma;
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
	 * @return the attachmentInterventi
	 */
	public byte[] getAttachmentInterventi() {
		return attachmentInterventi;
	}



	/**
	 * @param attachmentInterventi the attachmentInterventi to set
	 */
	public void setAttachmentInterventi(byte[] attachmentInterventi) {
		this.attachmentInterventi = attachmentInterventi;
	}



	/**
	 * @return the utenteReferenteCf
	 */
	public String getUtenteReferenteCf() {
		return utenteReferenteCf;
	}



	/**
	 * @param utenteReferenteCf the utenteReferenteCf to set
	 */
	public void setUtenteReferenteCf(String utenteReferenteCf) {
		this.utenteReferenteCf = utenteReferenteCf;
	}



	/**
	 * @return the attachmentImportiInterventi
	 */
	public byte[] getAttachmentImportiInterventi() {
		return attachmentImportiInterventi;
	}



	/**
	 * @param attachmentImportiInterventi the attachmentImportiInterventi to set
	 */
	public void setAttachmentImportiInterventi(byte[] attachmentImportiInterventi) {
		this.attachmentImportiInterventi = attachmentImportiInterventi;
	}
	
	
}
