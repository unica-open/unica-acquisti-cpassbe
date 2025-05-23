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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.Impegno;

public class SalvaImpegni implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 2822901832886152626L;
	private TestataOrdine testataOrdine;
	private RigaOrdine rigaOrdine;
	private List<Impegno> listImpegno;
	private Boolean ignoreWarnings = false;

	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}

	/**
	 * @return the rigaOrdine
	 */
	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}

	/**
	 * @param rigaOrdine the rigaOrdine to set
	 */
	public void setRigaOrdine(RigaOrdine rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}

	/**
	 * @return the listImpegno
	 */
	public List<Impegno> getListImpegno() {
		return listImpegno;
	}

	/**
	 * @param listImpegno the listImpegno to set
	 */
	public void setListImpegno(List<Impegno> listImpegno) {
		this.listImpegno = listImpegno;
	}

	/**
	 * @return the ignoreWarnings
	 */
	public Boolean getIgnoreWarnings() {
		return ignoreWarnings;
	}

	/**
	 * @param ignoreWarnings the ignoreWarnings to set
	 */
	public void setIgnoreWarnings(Boolean ignoreWarnings) {
		this.ignoreWarnings = ignoreWarnings;
	}

}
