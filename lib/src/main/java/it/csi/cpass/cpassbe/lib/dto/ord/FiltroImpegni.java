/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.Subimpegno;

public class FiltroImpegni implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6917368461093667542L;
	private Subimpegno subimpegno;
	private TestataOrdine testataOrdine;
	private String statoImpegno;

	/**
	 * @return the subimpegno
	 */
	public Subimpegno getSubimpegno() {
		return subimpegno;
	}

	/**
	 * @param subimpegno the subimpegno to set
	 */
	public void setSubimpegno(Subimpegno subimpegno) {
		this.subimpegno = subimpegno;
	}

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
	 * @return the statoImpegno
	 */
	public String getStatoImpegno() {
		return statoImpegno;
	}

	/**
	 * @param statoImpegno the statoImpegno to set
	 */
	public void setStatoImpegno(String statoImpegno) {
		this.statoImpegno = statoImpegno;
	}

}
