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
package it.csi.cpass.cpassbe.lib.external.res;

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.lib.external.dto.Errore;

/**
 * Wrapper for external service invocation response
 * @param <R> the wrapped response data type
 */
public class ExternalServiceResponseWrapper<R> {

	private boolean success;
	private List<String> errors = new ArrayList<>();
	private List<String> messages = new ArrayList<>();
	private List<Errore> errori = new ArrayList<>();
	private R response;

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors != null ? errors : new ArrayList<>();
	}
	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages != null ? messages : new ArrayList<>();
	}
	/**
	 * @return the response
	 */
	public R getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(R response) {
		this.response = response;
	}
	/**
	 * Adds an error
	 * @param error the error to add
	 * @return whether the error was added
	 */
	public boolean addError(String error) {
		return this.errors.add(error);
	}
	/**
	 * Adds a message
	 * @param message the message to add
	 * @return whether the message was added
	 */
	public boolean addMessage(String message) {
		return this.messages.add(message);
	}
	/**
	 * @return the errori
	 */
	public List<Errore> getErrori() {
		return errori;
	}
	/**
	 * @param errori the errori to set
	 */
	public void setErrori(List<Errore> errori) {
		this.errori = errori;
	}


}

