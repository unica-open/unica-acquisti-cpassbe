/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.util.UUID;

/**
 * File holder
 */
public  class FileHolder {

	private byte[] attachment;
	private byte[] attachment2;
	private String annoProgramma;
	private String versioneProgramma;
	private String utenteReferenteCf;
	private String separatore;

	private String fileXml;
	private UUID idEnte;
	/**
	 * @return the attachment
	 */
	public byte[] getAttachment() {
		return attachment;
	}
	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	/**
	 * @return the attachment2
	 */
	public byte[] getAttachment2() {
		return attachment2;
	}
	/**
	 * @param attachment2 the attachment2 to set
	 */
	public void setAttachment2(byte[] attachment2) {
		this.attachment2 = attachment2;
	}
	/**
	 * @return the annoProgramma
	 */
	public String getAnnoProgramma() {
		return annoProgramma;
	}
	/**
	 * @param annoProgramma the annoProgramma to set
	 */
	public void setAnnoProgramma(String annoProgramma) {
		this.annoProgramma = annoProgramma;
	}
	/**
	 * @return the versioneProgramma
	 */
	public String getVersioneProgramma() {
		return versioneProgramma;
	}
	/**
	 * @param versioneProgramma the versioneProgramma to set
	 */
	public void setVersioneProgramma(String versioneProgramma) {
		this.versioneProgramma = versioneProgramma;
	}
	/**
	 * @return the idEnte
	 */
	public UUID getIdEnte() {
		return idEnte;
	}
	/**
	 * @param idEnte the idEnte to set
	 */
	public void setIdEnte(UUID idEnte) {
		this.idEnte = idEnte;
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
	public String getFileXml() {
		return fileXml;
	}
	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}

}
