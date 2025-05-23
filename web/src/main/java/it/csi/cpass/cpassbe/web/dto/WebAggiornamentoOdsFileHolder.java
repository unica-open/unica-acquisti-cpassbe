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

import javax.ws.rs.FormParam;

import it.csi.cpass.cpassbe.lib.dto.FileCsvAggiornaOdsHolder;
//import it.csi.cpass.cpassbe.lib.dto.FileCsvRegoleHolder;

/**
 * File holder for intervento
 */
public class WebAggiornamentoOdsFileHolder {
	@FormParam("separatore")
	private String separatore;
	@FormParam("attachment")
	private byte[] attachment;
	//@FormParam("idEnte")
	//private String idEnte;


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




	public FileCsvAggiornaOdsHolder toFileCsvAggiornaOdsHolder() {
		FileCsvAggiornaOdsHolder fh = new FileCsvAggiornaOdsHolder();
		fh.setAttachment(attachment);
		//fh.setIdEnte(UUID.fromString(idEnte));
		fh.setSeparatore(separatore);
		return fh;
	}

}
