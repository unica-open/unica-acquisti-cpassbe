/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.dto;

import java.util.UUID;

import javax.ws.rs.FormParam;

import it.csi.cpass.cpassbe.lib.dto.FileCsvRegoleHolder;

/**
 * File holder for intervento
 */
public class WebSmistamentoRmsFileHolder {

	//@FormParam("utenteReferenteCf")
	//private String utenteReferenteCf;
	@FormParam("sostituisciRegola")
	private Boolean sostituisciRegola;
	@FormParam("separatore")
	private String separatore;
	@FormParam("attachment")
	private byte[] attachment;
	@FormParam("idEnte")
	private String idEnte;


	/**
	 * @return the attachment2
	 */
	public byte[] getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment2 the attachment2 to set
	 */
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
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

	/**
	 * @return the idEnte
	 */
	public String getIdEnte() {
		return idEnte;
	}

	/**
	 * @param idEnte the idEnte to set
	 */
	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}


	public FileCsvRegoleHolder toFileCsvRegoleHolder() {
		FileCsvRegoleHolder fh = new FileCsvRegoleHolder();
		fh.setAttachment(attachment);
		fh.setIdEnte(UUID.fromString(idEnte));
		fh.setSeparatore(separatore);
		fh.setSostituisciRegola(sostituisciRegola);
		return fh;
	}

}
