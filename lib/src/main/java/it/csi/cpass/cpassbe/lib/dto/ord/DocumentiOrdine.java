/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;



public class DocumentiOrdine extends BaseAuditedDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descrizione;

	private byte[] fileDocumentoByte;

	private String fileDocumento;

	private Integer progressivo;

	private String nomefile;

	private String ext;

	private Boolean caricatoDaLocale;

	private TestataOrdine testataOrdine;

	public DocumentiOrdine() {
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	/**
	 * @return the fileDocumento
	 */
	public String getFileDocumento() {
		return fileDocumento;
	}

	/**
	 * @param fileDocumento the fileDocumento to set
	 */
	public void setFileDocumento(String fileDocumento) {
		this.fileDocumento = fileDocumento;
	}

	/**
	 * @return the progressivo
	 */
	public Integer getProgressivo() {
		return progressivo;
	}

	/**
	 * @param progressivo the progressivo to set
	 */
	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
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
	 * @return the nomeFile
	 */
	public String getNomefile() {
		return nomefile;
	}

	/**
	 * @param nomeFile the nomeFile to set
	 */
	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}



	/**
	 * @return the fileDocumentoByte
	 */
	public byte[] getFileDocumentoByte() {
		return fileDocumentoByte;
	}

	/**
	 * @param fileDocumentoByte the fileDocumentoByte to set
	 */
	public void setFileDocumentoByte(byte[] fileDocumentoByte) {
		this.fileDocumentoByte = fileDocumentoByte;
	}

	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * @param ext the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * @return the caricatoDaLocale
	 */
	public Boolean getCaricatoDaLocale() {
		return caricatoDaLocale;
	}

	/**
	 * @param caricatoDaLocale the caricatoDaLocale to set
	 */
	public void setCaricatoDaLocale(Boolean caricatoDaLocale) {
		this.caricatoDaLocale = caricatoDaLocale;
	}



}
