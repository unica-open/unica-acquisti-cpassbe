/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;

public class OrdRdaOrdine extends BaseDto<Integer> implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public OrdRdaOrdine() {
	}

	public OrdRdaOrdine(Integer id) {
		super(id);
	}

	private TestataOrdine testataOrdine;
	private TestataRda testatarda;

	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}
	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}
	public TestataRda getTestatarda() {
		return testatarda;
	}
	public void setTestatarda(TestataRda testatarda) {
		this.testatarda = testatarda;
	}



}
