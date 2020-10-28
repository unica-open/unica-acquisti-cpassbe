/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.exception;

import javax.ejb.ApplicationException;

/**
 * Exception during parameter check
 */
@ApplicationException(rollback = true)
public class ParamValidationException extends ServiceException {

	/** For serialization */
	private static final long serialVersionUID = -6151808156585443325L;

	/**
	 * @see Exception#Exception(String)
	 */
	public ParamValidationException(String message) {
		super(message);
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public ParamValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
