/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.web.dto;

import it.csi.cpass.cpassbe.lib.dto.Permesso;
import it.csi.cpass.cpassbe.lib.dto.Ruolo;

public class RicercaRuoloPermesso {
	private Ruolo ruolo;
	private Permesso permesso;
	/**
	 * @return the ruolo
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}
	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	/**
	 * @return the permesso
	 */
	public Permesso getPermesso() {
		return permesso;
	}
	/**
	 * @param permesso the permesso to set
	 */
	public void setPermesso(Permesso permesso) {
		this.permesso = permesso;
	}



}
