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
import java.util.List;

/**
 * Container for ReportModel instances
 */
public class ReportModelContainer implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -922191426052747740L;
	private List<ReportModel> data;
	private String fileName;
	private String format;

	/**
	 * @return the data
	 */
	public List<ReportModel> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<ReportModel> data) {
		this.data = data;
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

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

}
