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
package it.csi.cpass.cpassbe.ejb.entity.ord;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the cpass_t_ord_protocollo_ordine database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_protocollo_ordine")
@NamedQuery(name="CpassTOrdProtocolloOrdine.findAll", query="SELECT c FROM CpassTOrdProtocolloOrdine c")
public class CpassTOrdProtocolloOrdine extends BaseAuditedEntity<Integer> {
	@Id
	@SequenceGenerator(name="CPASS_T_ORD_PROTOCOLLO_ORDINE_PROTOCOLLOORDINEID_GENERATOR" , sequenceName="cpass_t_ord_protocollo_ordine_protocollo_ordine_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_ORD_PROTOCOLLO_ORDINE_PROTOCOLLOORDINEID_GENERATOR")
	@Column(name="protocollo_ordine_id")
	private Integer protocolloOrdineId;

	@Column(name="anno_protocollo_orig")
	private Integer annoProtocolloOrig;

	@Column(name="aoo_orig")
	private String aooOrig;

	@Column(name="data_protocollo")
	private Date dataProtocollo;

	@Column(name="numero_protocollo_orig")
	private String numeroProtocolloOrig;

	@Column(name="anno_protocollo")
	private Integer annoProtocollo;

	private String aoo;

	@Column(name="data_protocollo_orig")
	private Date dataProtocolloOrig;

	@Column(name="descrizione_protocollo_orig")
	private String descrizioneProtocolloOrig;

	@Column(name="descrizione_protocollo")
	private String descrizioneProtocollo;

	@Column(name="numero_protocollo")
	private String numeroProtocollo;

	@Column(name="uuid_documento_ordine")
	private String uuidDocumentoOrdine;

	@Column(name="uuid_documento_orig")
	private String uuidDocumentoOrig;

	@Column(name="uuid_reg_protocollo_ordine")
	private String uuidRegProtocolloOrdine;

	@Column(name="uuid_reg_protocollo_orig")
	private String uuidRegProtocolloOrig;


	@Column(name="id_classificazione_value")
	private String idClassificazioneValue;

	@Column(name="struttura_aggregativa_object_id")
	private String strutturaAggregativaObjectId;

	@Column(name="indice_classificazione_esteso")
	private String indiceClassificazioneEsteso;

	@Column(name="voce_titolario")
	private String voceTitolario;

	@Column(name="numero_fascicolo_dossier")
	private String numeroFascicoloDossier;

	@Column(name="aoo_dossier")
	private String aooDossier;



	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@ManyToOne
	@JoinColumn(name="testata_ordine_id")
	private CpassTOrdTestataOrdine cpassTOrdTestataOrdine;

	//bi-directional many-to-one association to CpassDAooActa
	@ManyToOne
	@JoinColumn(name="aoo_acta_id")
	private CpassDAooActa cpassDAooActa;



	public CpassTOrdProtocolloOrdine() {
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
	 * @return the protocolloOrdineId
	 */
	public Integer getProtocolloOrdineId() {
		return protocolloOrdineId;
	}

	/**
	 * @param protocolloOrdineId the protocolloOrdineId to set
	 */
	public void setProtocolloOrdineId(Integer protocolloOrdineId) {
		this.protocolloOrdineId = protocolloOrdineId;
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
	 * @return the uuidRegProtocolloOrig
	 */
	public String getUuidRegProtocolloOrig() {
		return uuidRegProtocolloOrig;
	}




	/**
	 * @param uuidRegProtocolloOrig the uuidRegProtocolloOrig to set
	 */
	public void setUuidRegProtocolloOrig(String uuidRegProtocolloOrig) {
		this.uuidRegProtocolloOrig = uuidRegProtocolloOrig;
	}




	/**
	 * @return the cpassTOrdTestataOrdine
	 */
	public CpassTOrdTestataOrdine getCpassTOrdTestataOrdine() {
		return cpassTOrdTestataOrdine;
	}




	/**
	 * @param cpassTOrdTestataOrdine the cpassTOrdTestataOrdine to set
	 */
	public void setCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		this.cpassTOrdTestataOrdine = cpassTOrdTestataOrdine;
	}




	/**
	 * @return the cpassDAooActa
	 */
	public CpassDAooActa getCpassDAooActa() {
		return cpassDAooActa;
	}




	/**
	 * @param cpassDAooActa the cpassDAooActa to set
	 */
	public void setCpassDAooActa(CpassDAooActa cpassDAooActa) {
		this.cpassDAooActa = cpassDAooActa;
	}




	/**
	 * @return the idClassificazioneValue
	 */
	public String getIdClassificazioneValue() {
		return idClassificazioneValue;
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
	 * @param idClassificazioneValue the idClassificazioneValue to set
	 */
	public void setIdClassificazioneValue(String idClassificazioneValue) {
		this.idClassificazioneValue = idClassificazioneValue;
	}

	@Override
	public Integer getId() {
		return protocolloOrdineId;
	}

	@Override
	public void setId(Integer id) {
		protocolloOrdineId = id;
	}

}
