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
public  class FileCsvRegoleHolder {

	private byte[] attachment;
	private String separatore;
	private String fileXml;
	private UUID idEnte;
	private Boolean sostituisciRegola;
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
