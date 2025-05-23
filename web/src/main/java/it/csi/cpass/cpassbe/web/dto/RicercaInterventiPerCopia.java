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

/**
 * Intervento da copia
 */
public class RicercaInterventiPerCopia {

	private UUID programmaIdOld;
	private UUID programmaIdNew;
	private UUID utenteRupId;
	/**
	 * @return the programmaIdOld
	 */
	public UUID getProgrammaIdOld() {
		return programmaIdOld;
	}
	/**
	 * @param programmaIdOld the programmaIdOld to set
	 */
	public void setProgrammaIdOld(UUID programmaIdOld) {
		this.programmaIdOld = programmaIdOld;
	}
	/**
	 * @return the programmaIdNew
	 */
	public UUID getProgrammaIdNew() {
		return programmaIdNew;
	}
	/**
	 * @param programmaIdNew the programmaIdNew to set
	 */
	public void setProgrammaIdNew(UUID programmaIdNew) {
		this.programmaIdNew = programmaIdNew;
	}
	/**
	 * @return the utenteRupId
	 */
	public UUID getUtenteRupId() {
		return utenteRupId;
	}
	/**
	 * @param utenteRupId the utenteRupId to set
	 */
	public void setUtenteRupId(UUID utenteRupId) {
		this.utenteRupId = utenteRupId;
	}



}
