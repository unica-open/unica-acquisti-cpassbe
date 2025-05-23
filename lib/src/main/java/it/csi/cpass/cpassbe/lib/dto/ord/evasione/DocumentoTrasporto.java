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
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;


public class DocumentoTrasporto extends BaseDto<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String capDestinatario;
	private String contattoDestinatario;
	private String dataConsegna;
	private String despatchAdviceId;
	private String endpointId;
	private String idNotier;
	private String indirizzoDestinatario;
	private String localitaDestinatario;
	private String note;
	private String ordineUnicoData;
	private String ordineUnicoId;
	private String ordineUnicoTipo;
	private String partitaIvaFornitore;
	private String provinciaDestinatario;
	private String ragioneSocialeFornitore;
	private String settoreEmittenteOrdine;
	private String telefonoDestinatario;
	private Fornitore fornitore;
	private Stato stato;
	private TestataEvasione testataEvasione;
	private TestataOrdine testataOrdine;
	private List<DocumentoTrasportoRiga> documentoTrasportoRigaList;
	private List<DocumentoTrasportoXML> documentoTrasportoXMLList;
	private String urnDocumento;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCapDestinatario() {
		return capDestinatario;
	}

	public void setCapDestinatario(String capDestinatario) {
		this.capDestinatario = capDestinatario;
	}

	public String getContattoDestinatario() {
		return contattoDestinatario;
	}

	public void setContattoDestinatario(String contattoDestinatario) {
		this.contattoDestinatario = contattoDestinatario;
	}

	public String getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getDespatchAdviceId() {
		return despatchAdviceId;
	}

	public void setDespatchAdviceId(String despatchAdviceId) {
		this.despatchAdviceId = despatchAdviceId;
	}

	public String getEndpointId() {
		return endpointId;
	}

	public void setEndpointId(String endpointId) {
		this.endpointId = endpointId;
	}

	public String getIdNotier() {
		return idNotier;
	}

	public void setIdNotier(String idNotier) {
		this.idNotier = idNotier;
	}

	public String getIndirizzoDestinatario() {
		return indirizzoDestinatario;
	}

	public void setIndirizzoDestinatario(String indirizzoDestinatario) {
		this.indirizzoDestinatario = indirizzoDestinatario;
	}

	public String getLocalitaDestinatario() {
		return localitaDestinatario;
	}

	public void setLocalitaDestinatario(String localitaDestinatario) {
		this.localitaDestinatario = localitaDestinatario;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrdineUnicoData() {
		return ordineUnicoData;
	}

	public void setOrdineUnicoData(String ordineUnicoData) {
		this.ordineUnicoData = ordineUnicoData;
	}

	public String getOrdineUnicoId() {
		return ordineUnicoId;
	}

	public void setOrdineUnicoId(String ordineUnicoId) {
		this.ordineUnicoId = ordineUnicoId;
	}

	public String getOrdineUnicoTipo() {
		return ordineUnicoTipo;
	}

	public void setOrdineUnicoTipo(String ordineUnicoTipo) {
		this.ordineUnicoTipo = ordineUnicoTipo;
	}

	public String getPartitaIvaFornitore() {
		return partitaIvaFornitore;
	}

	public void setPartitaIvaFornitore(String partitaIvaFornitore) {
		this.partitaIvaFornitore = partitaIvaFornitore;
	}

	public String getProvinciaDestinatario() {
		return provinciaDestinatario;
	}

	public void setProvinciaDestinatario(String provinciaDestinatario) {
		this.provinciaDestinatario = provinciaDestinatario;
	}

	public String getRagioneSocialeFornitore() {
		return ragioneSocialeFornitore;
	}

	public void setRagioneSocialeFornitore(String ragioneSocialeFornitore) {
		this.ragioneSocialeFornitore = ragioneSocialeFornitore;
	}

	public String getSettoreEmittenteOrdine() {
		return settoreEmittenteOrdine;
	}

	public void setSettoreEmittenteOrdine(String settoreEmittenteOrdine) {
		this.settoreEmittenteOrdine = settoreEmittenteOrdine;
	}

	public String getTelefonoDestinatario() {
		return telefonoDestinatario;
	}

	public void setTelefonoDestinatario(String telefonoDestinatario) {
		this.telefonoDestinatario = telefonoDestinatario;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

	public void setTestataEvasione(TestataEvasione testataEvasione) {
		this.testataEvasione = testataEvasione;
	}

	public List<DocumentoTrasportoRiga> getDocumentoTrasportoRigaList() {
		return documentoTrasportoRigaList;
	}

	public void setDocumentoTrasportoRigaList(List<DocumentoTrasportoRiga> documentoTrasportoRigaList) {
		this.documentoTrasportoRigaList = documentoTrasportoRigaList;
	}

	public List<DocumentoTrasportoXML> getDocumentoTrasportoXMLList() {
		return documentoTrasportoXMLList;
	}

	public void setDocumentoTrasportoXMLList(List<DocumentoTrasportoXML> documentoTrasportoXMLList) {
		this.documentoTrasportoXMLList = documentoTrasportoXMLList;
	}

	/**
	 * @return the urnDocumento
	 */
	public String getUrnDocumento() {
		return urnDocumento;
	}

	/**
	 * @param urnDocumento the urnDocumento to set
	 */
	public void setUrnDocumento(String urnDocumento) {
		this.urnDocumento = urnDocumento;
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


}
