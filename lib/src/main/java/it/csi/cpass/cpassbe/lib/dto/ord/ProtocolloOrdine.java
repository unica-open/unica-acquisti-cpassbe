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
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;


public class ProtocolloOrdine extends BaseAuditedDto<Integer> implements Serializable {

	private static final long serialVersionUID = -1408411504367329198L;

	private Integer annoProtocollo;
	private String  numeroProtocollo;
	private String  aoo;
	private String  aooCode;
	private Date    dataProtocollo;
	private String  descrizioneProtocollo;

	private String  uuidDocumentoOrdine;
	private String  uuidDocumentoOrig;
	private String  uuidRegProtocolloOrdine;
	private String  uuidRegProtocolloOrig;
	private TestataOrdine testataOrdine;

	private Integer annoProtocolloOrig;
	private String  numeroProtocolloOrig;
	private Date    dataProtocolloOrig;
	private String  descrizioneProtocolloOrig;
	private AooActa aooActa;
	private String  aooOrig;
	private String  aooOrigCode;
	private Boolean annullato  ;
	private String descrizioneTipoRegistrazione  ;
	private Boolean strutturaAccessibile  ;
	private String codErrore  ;
	private String descErrore  ;
    private String idClassificazioneValue;

    private String strutturaAggregativaObjectId;
	private String indiceClassificazioneEsteso;
	private String voceTitolario;
	private String numeroFascicoloDossier;
	private String aooDossier;


	/**
	 * @return the annoProtocollo
	 */
	public Integer getAnnoProtocollo() {
		return annoProtocollo;
	}
	/**
	 * @param annoProtocollo the annoProtocollo to set
	 */
	public void setAnnoProtocollo(Integer annoProtocollo) {
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
	 * @return the aoo
	 */
	public String getAoo() {
		return aoo;
	}
	/**
	 * @param aoo the aoo to set
	 */
	public void setAoo(String aoo) {
		this.aoo = aoo;
	}
	/**
	 * @return the dataProtocollo
	 */
	public Date getDataProtocollo() {
		return dataProtocollo;
	}
	/**
	 * @param dataProtocollo the dataProtocollo to set
	 */
	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
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
	 * @return the uuidDocumentoOrdine
	 */
	public String getUuidDocumentoOrdine() {
		return uuidDocumentoOrdine;
	}
	/**
	 * @param uuidDocumentoOrdine the uuidDocumentoOrdine to set
	 */
	public void setUuidDocumentoOrdine(String uuidDocumentoOrdine) {
		this.uuidDocumentoOrdine = uuidDocumentoOrdine;
	}
	/**
	 * @return the uuidDocumentoOrig
	 */
	public String getUuidDocumentoOrig() {
		return uuidDocumentoOrig;
	}
	/**
	 * @param uuidDocumentoOrig the uuidDocumentoOrig to set
	 */
	public void setUuidDocumentoOrig(String uuidDocumentoOrig) {
		this.uuidDocumentoOrig = uuidDocumentoOrig;
	}
	/**
	 * @return the uuidRegProtococolloOrdine
	 */
	public String getUuidRegProtocolloOrdine() {
		return uuidRegProtocolloOrdine;
	}
	/**
	 * @param uuidRegProtococolloOrdine the uuidRegProtococolloOrdine to set
	 */
	public void setUuidRegProtocolloOrdine(String uuidRegProtocolloOrdine) {
		this.uuidRegProtocolloOrdine = uuidRegProtocolloOrdine;
	}
	/**
	 * @return the uuidRegProtococolloOrig
	 */
	public String getUuidRegProtocolloOrig() {
		return uuidRegProtocolloOrig;
	}
	/**
	 * @param uuidRegProtococolloOrig the uuidRegProtococolloOrig to set
	 */
	public void setUuidRegProtocolloOrig(String uuidRegProtocolloOrig) {
		this.uuidRegProtocolloOrig = uuidRegProtocolloOrig;
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
	 * @return the annoProtocolloOrig
	 */
	public Integer getAnnoProtocolloOrig() {
		return annoProtocolloOrig;
	}
	/**
	 * @param annoProtocolloOrig the annoProtocolloOrig to set
	 */
	public void setAnnoProtocolloOrig(Integer annoProtocolloOrig) {
		this.annoProtocolloOrig = annoProtocolloOrig;
	}
	/**
	 * @return the numeroProtocolloOrig
	 */
	public String getNumeroProtocolloOrig() {
		return numeroProtocolloOrig;
	}
	/**
	 * @param numeroProtocolloOrig the numeroProtocolloOrig to set
	 */
	public void setNumeroProtocolloOrig(String numeroProtocolloOrig) {
		this.numeroProtocolloOrig = numeroProtocolloOrig;
	}
	/**
	 * @return the dataProtocolloOrig
	 */
	public Date getDataProtocolloOrig() {
		return dataProtocolloOrig;
	}
	/**
	 * @param dataProtocolloOrig the dataProtocolloOrig to set
	 */
	public void setDataProtocolloOrig(Date dataProtocolloOrig) {
		this.dataProtocolloOrig = dataProtocolloOrig;
	}
	/**
	 * @return the descrizioneProtocolloOrig
	 */
	public String getDescrizioneProtocolloOrig() {
		return descrizioneProtocolloOrig;
	}
	/**
	 * @param descrizioneProtocolloOrig the descrizioneProtocolloOrig to set
	 */
	public void setDescrizioneProtocolloOrig(String descrizioneProtocolloOrig) {
		this.descrizioneProtocolloOrig = descrizioneProtocolloOrig;
	}

	/**
	 * @return the aooActa
	 */
	public AooActa getAooActa() {
		return aooActa;
	}
	/**
	 * @param aooActa the aooActa to set
	 */
	public void setAooActa(AooActa aooActa) {
		this.aooActa = aooActa;
	}
	/**
	 * @return the aooOrig
	 */
	public String getAooOrig() {
		return aooOrig;
	}
	/**
	 * @param aooOrig the aooOrig to set
	 */
	public void setAooOrig(String aooOrig) {
		this.aooOrig = aooOrig;
	}
	/**
	 * @return the annullato
	 */
	public Boolean getAnnullato() {
		return annullato;
	}
	/**
	 * @param annullato the annullato to set
	 */
	public void setAnnullato(Boolean annullato) {
		this.annullato = annullato;
	}
	/**
	 * @return the descrizioneTipoRegistrazione
	 */
	public String getDescrizioneTipoRegistrazione() {
		return descrizioneTipoRegistrazione;
	}
	/**
	 * @param descrizioneTipoRegistrazione the descrizioneTipoRegistrazione to set
	 */
	public void setDescrizioneTipoRegistrazione(String descrizioneTipoRegistrazione) {
		this.descrizioneTipoRegistrazione = descrizioneTipoRegistrazione;
	}
	/**
	 * @return the strutturaAccessibile
	 */
	public Boolean getStrutturaAccessibile() {
		return strutturaAccessibile;
	}
	/**
	 * @param accessibile the strutturaAccessibile to set
	 */
	public void setStrutturaAccessibile(Boolean strutturaAccessibile) {
		this.strutturaAccessibile = strutturaAccessibile;
	}
	/**
	 * @return the codErrore
	 */
	public String getCodErrore() {
		return codErrore;
	}
	/**
	 * @param codErrore the codErrore to set
	 */
	public void setCodErrore(String codErrore) {
		this.codErrore = codErrore;
	}
	/**
	 * @return the descErrore
	 */
	public String getDescErrore() {
		return descErrore;
	}
	/**
	 * @param descErrore the descErrore to set
	 */
	public void setDescErrore(String descErrore) {
		this.descErrore = descErrore;
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
	 * @return the strutturaAggregativaObjectId
	 */
	public String getStrutturaAggregativaObjectId() {
		return strutturaAggregativaObjectId;
	}
	/**
	 * @param strutturaAggregativaObjectId the strutturaAggregativaObjectId to set
	 */
	public void setStrutturaAggregativaObjectId(String strutturaAggregativaObjectId) {
		this.strutturaAggregativaObjectId = strutturaAggregativaObjectId;
	}
	/**
	 * @return the indiceClassificazioneEsteso
	 */
	public String getIndiceClassificazioneEsteso() {
		return indiceClassificazioneEsteso;
	}
	/**
	 * @param indiceClassificazioneEsteso the indiceClassificazioneEsteso to set
	 */
	public void setIndiceClassificazioneEsteso(String indiceClassificazioneEsteso) {
		this.indiceClassificazioneEsteso = indiceClassificazioneEsteso;
	}
	/**
	 * @return the voceTitolario
	 */
	public String getVoceTitolario() {
		return voceTitolario;
	}
	/**
	 * @param voceTitolario the voceTitolario to set
	 */
	public void setVoceTitolario(String voceTitolario) {
		this.voceTitolario = voceTitolario;
	}
	/**
	 * @return the numeroFascicoloDossier
	 */
	public String getNumeroFascicoloDossier() {
		return numeroFascicoloDossier;
	}
	/**
	 * @param numeroFascicoloDossier the numeroFascicoloDossier to set
	 */
	public void setNumeroFascicoloDossier(String numeroFascicoloDossier) {
		this.numeroFascicoloDossier = numeroFascicoloDossier;
	}
	/**
	 * @return the aooDossier
	 */
	public String getAooDossier() {
		return aooDossier;
	}
	/**
	 * @param aooDossier the aooDossier to set
	 */
	public void setAooDossier(String aooDossier) {
		this.aooDossier = aooDossier;
	}
	/**
	 * @return the aooCode
	 */
	public String getAooCode() {
		return aooCode;
	}
	/**
	 * @param aooCode the aooCode to set
	 */
	public void setAooCode(String aooCode) {
		this.aooCode = aooCode;
	}
	/**
	 * @return the aooOrigCode
	 */
	public String getAooOrigCode() {
		return aooOrigCode;
	}
	/**
	 * @param aooOrigCode the aooOrigCode to set
	 */
	public void setAooOrigCode(String aooOrigCode) {
		this.aooOrigCode = aooOrigCode;
	}


}
