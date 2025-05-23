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
import java.math.BigDecimal;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;


public class DocumentoTrasportoRiga  extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codiceListinoFornitore;
	private String motivoQtaInevasa;
	private String noteFornitore;
	private String ordineData;
	private String ordineNsoId;
	private String ordineTipo;
	private String progressivoRigaId;
	private String progressivoRigaOrdineEvasa;
	private BigDecimal qtaEvasa;
	private BigDecimal qtaInevasa;
	private String unitaMisura;
	private DocumentoTrasporto documentoTrasporto;
	private RigaOrdine rigaOrdine;
	private RigaEvasione rigaEvasione;
	private TestataOrdine testataOrdine;

	/**
	 * dato calcolato, non serve colonna sul db
	 */
	private BigDecimal qtaEvadibile;


	public DocumentoTrasportoRiga() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCodiceListinoFornitore() {
		return codiceListinoFornitore;
	}

	public void setCodiceListinoFornitore(String codiceListinoFornitore) {
		this.codiceListinoFornitore = codiceListinoFornitore;
	}

	public String getMotivoQtaInevasa() {
		return motivoQtaInevasa;
	}

	public void setMotivoQtaInevasa(String motivoQtaInevasa) {
		this.motivoQtaInevasa = motivoQtaInevasa;
	}

	public String getNoteFornitore() {
		return noteFornitore;
	}

	public void setNoteFornitore(String noteFornitore) {
		this.noteFornitore = noteFornitore;
	}

	public String getOrdineData() {
		return ordineData;
	}

	public void setOrdineData(String ordineData) {
		this.ordineData = ordineData;
	}

	public String getOrdineNsoId() {
		return ordineNsoId;
	}

	public void setOrdineNsoId(String ordineNsoId) {
		this.ordineNsoId = ordineNsoId;
	}

	public String getOrdineTipo() {
		return ordineTipo;
	}

	public void setOrdineTipo(String ordineTipo) {
		this.ordineTipo = ordineTipo;
	}

	public String getProgressivoRigaId() {
		return progressivoRigaId;
	}

	public void setProgressivoRigaId(String progressivoRigaId) {
		this.progressivoRigaId = progressivoRigaId;
	}

	public String getProgressivoRigaOrdineEvasa() {
		return progressivoRigaOrdineEvasa;
	}

	public void setProgressivoRigaOrdineEvasa(String progressivoRigaOrdineEvasa) {
		this.progressivoRigaOrdineEvasa = progressivoRigaOrdineEvasa;
	}

	public BigDecimal getQtaEvasa() {
		return qtaEvasa;
	}

	public void setQtaEvasa(BigDecimal qtaEvasa) {
		this.qtaEvasa = qtaEvasa;
	}

	public BigDecimal getQtaInevasa() {
		return qtaInevasa;
	}

	public void setQtaInevasa(BigDecimal qtaInevasa) {
		this.qtaInevasa = qtaInevasa;
	}

	public String getUnitaMisura() {
		return unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	public DocumentoTrasporto getDocumentoTrasporto() {
		return documentoTrasporto;
	}

	public void setDocumentoTrasporto(DocumentoTrasporto documentoTrasporto) {
		this.documentoTrasporto = documentoTrasporto;
	}

	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}

	public void setRigaOrdine(RigaOrdine rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}

	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}

	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}


	/**
	 * @return the qtaEvadibile
	 */
	public BigDecimal getQtaEvadibile() {
		return qtaEvadibile;
	}

	/**
	 * @param qtaEvadibile the qtaEvadibile to set
	 */
	public void setQtaEvadibile(BigDecimal qtaEvadibile) {
		this.qtaEvadibile = qtaEvadibile;
	}

	/**
	 * @return the rigaEvasione
	 */
	public RigaEvasione getRigaEvasione() {
		return rigaEvasione;
	}

	/**
	 * @param rigaEvasione the rigaEvasione to set
	 */
	public void setRigaEvasione(RigaEvasione rigaEvasione) {
		this.rigaEvasione = rigaEvasione;
	}

}
