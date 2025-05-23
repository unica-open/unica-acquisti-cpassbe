/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.batch;

public class CustomBatchError extends Exception {

	private static final long serialVersionUID = -8850776971429304638L;
	  Integer status;
	  CustomBatchError(String message, Integer status) {
	    super(message);
	    this.status = status;
	  }
}
