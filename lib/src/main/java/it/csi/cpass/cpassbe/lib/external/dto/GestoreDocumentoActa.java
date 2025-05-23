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
package it.csi.cpass.cpassbe.lib.external.dto;

import java.io.Serializable;

public class GestoreDocumentoActa implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7561675641078894344L;
	private String error;
	private String uuidDocumentoOrdine;
	private String idClassificazioneValue;
	private String uuidRegProtocolloOrdine;
	private String annoProtocollo;
	private String numeroProtocollo;
	private String descrizioneProtocollo;
	private String dataProtocollo;
	private String uuidSerieTipologica;
	private String objectIdVolume;
	private String soggettoId;

	public String getUuidDocumentoOrdine() {
		return uuidDocumentoOrdine;
	}
	public void setUuidDocumentoOrdine(String uuidDocumentoOrdine) {
		this.uuidDocumentoOrdine = uuidDocumentoOrdine;
	}
	/**
	 * @return the uuidRegProtocolloOrdine
	 */
	public String getUuidRegProtocolloOrdine() {
		return uuidRegProtocolloOrdine;
	}
	/**
	 * @param uuidRegProtocolloOrdine the uuidRegProtocolloOrdine to set
	 */
	public void setUuidRegProtocolloOrdine(String uuidRegProtocolloOrdine) {
		this.uuidRegProtocolloOrdine = uuidRegProtocolloOrdine;
	}
	/**
	 * @return the annoProtocollo
	 */
	public String getAnnoProtocollo() {
		return annoProtocollo;
	}
	/**
	 * @param annoProtocollo the annoProtocollo to set
	 */
	public void setAnnoProtocollo(String annoProtocollo) {
		this.annoProtocollo = annoProtocollo;
	}
	/**
	 * @return the numeroProtocollo
	 */
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}
	/**
	 * @param numeroProtocollo the numeroProtocollo to set
	 */
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}
	/**
	 * @return the descrizioneProtocollo
	 */
	public String getDescrizioneProtocollo() {
		return descrizioneProtocollo;
	}
	/**
	 * @param descrizioneProtocollo the descrizioneProtocollo to set
	 */
	public void setDescrizioneProtocollo(String descrizioneProtocollo) {
		this.descrizioneProtocollo = descrizioneProtocollo;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the idClassificazioneValue
	 */
	public String getIdClassificazioneValue() {
		return idClassificazioneValue;
	}
	/**
	 * @param idClassificazioneValue the idClassificazioneValue to set
	 */
	public void setIdClassificazioneValue(String idClassificazioneValue) {
		this.idClassificazioneValue = idClassificazioneValue;
	}


	/**
	 * @return the soggettoId
	 */
	public String getSoggettoId() {
		return soggettoId;
	}
	/**
	 * @param soggettoId the soggettoId to set
	 */
	public void setSoggettoId(String soggettoId) {
		this.soggettoId = soggettoId;
	}
	/**
	 * @return the dataProtocollo
	 */
	public String getDataProtocollo() {
		return dataProtocollo;
	}
	/**
	 * @param dataProtocollo the dataProtocollo to set
	 */
	public void setDataProtocollo(String dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	/**
	 * @return the uuidSerieTipologica
	 */
	public String getUuidSerieTipologica() {
		return uuidSerieTipologica;
	}
	/**
	 * @param uuidSerieTipologica the uuidSerieTipologica to set
	 */
	public void setUuidSerieTipologica(String uuidSerieTipologica) {
		this.uuidSerieTipologica = uuidSerieTipologica;
	}
	/**
	 * @return the objectIdVolume
	 */
	public String getObjectIdVolume() {
		return objectIdVolume;
	}
	/**
	 * @param objectIdVolume the objectIdVolume to set
	 */
	public void setObjectIdVolume(String objectIdVolume) {
		this.objectIdVolume = objectIdVolume;
	}


}
