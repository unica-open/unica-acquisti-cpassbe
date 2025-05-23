/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.stampe;

import java.io.Serializable;
import java.util.Map;


/**
 * Report model instance
 */
public class ReportModel implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 6430354562695420078L;
	//@SerializedName("__report")
	private String __report;
	//@SerializedName("__format")
	private String __format;
	//@SerializedName("__asattachment")
	private Boolean __attachment;
    private String fileName;
	private Map<String, Object> params;


	/**
	 * @return the __report
	 */
	public String get__report() {
		return __report;
	}
	/**
	 * @param __report the __report to set
	 */
	public void set__report(String __report) {
		this.__report = __report;
	}
	/**
	 * @return the __format
	 */
	public String get__format() {
		return __format;
	}
	/**
	 * @param __format the __format to set
	 */
	public void set__format(String __format) {
		this.__format = __format;
	}
	/**
	 * @return the __attachment
	 */
	public Boolean get__attachment() {
		return __attachment;
	}
	/**
	 * @param __attachment the __attachment to set
	 */
	public void set__attachment(Boolean __attachment) {
		this.__attachment = __attachment;
	}
	/**
	 * @return the params
	 */
	public Map<String, Object> getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}
