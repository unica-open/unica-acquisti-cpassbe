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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.util.List;

public class ControllaEvasione implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -5604861742980976412L;
	private List<String> listIgnoreWarning;

	/**
	 * @return the listIgnoreWarning
	 */
	public List<String> getListIgnoreWarning() {
		return listIgnoreWarning;
	}

	/**
	 * @param listIgnoreWarning the listIgnoreWarning to set
	 */
	public void setListIgnoreWarning(List<String> listIgnoreWarning) {
		this.listIgnoreWarning = listIgnoreWarning;
	}

}
