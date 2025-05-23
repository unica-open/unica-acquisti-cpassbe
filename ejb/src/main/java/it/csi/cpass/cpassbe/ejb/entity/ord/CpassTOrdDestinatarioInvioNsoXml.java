/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_ord_destinatario_invio_nso_xml database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_destinatario_invio_nso_xml")
@NamedQuery(name="CpassTOrdDestinatarioInvioNsoXml.findAll", query="SELECT c FROM CpassTOrdDestinatarioInvioNsoXml c")
public class CpassTOrdDestinatarioInvioNsoXml implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_ORD_DESTINATARIO_INVIO_NSO_XMLID_GENERATOR", sequenceName="cpass_t_ord_destinatario_invi_destinatario_invio_nso_xml_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_ORD_DESTINATARIO_INVIO_NSO_XMLID_GENERATOR")
	@Column(name="destinatario_invio_nso_xml_id", unique=true, nullable=false)
	private Integer destinatarioInvioNsoXmlId;

	@Column(name="data_consegna")
	private String dataConsegna;

	@Column(name="data_ricezione")
	private String dataRicezione;

	@Column(name="data_spostamento")
	private Date dataSpostamento;

	@Column(name="file_xml")
	private String fileXml;

	@Column(name="metadati_xml")
	private String metadatiXml;

	@Column(name="identificativo_trasmissione")
	private String identificativoTrasmissione;

	private String note;

	@Column(name="path_file")
	private String pathFile;

	private String tipodoc;

	//bi-directional many-to-one association to CpassTOrdDestinatarioInvioNso
	@ManyToOne
	@JoinColumn(name="destinatario_invio_nso_id")
	private CpassTOrdDestinatarioInvioNso cpassTOrdDestinatarioInvioNso;

	public CpassTOrdDestinatarioInvioNsoXml() {
	}

	public Integer getDestinatarioInvioNsoXmlId() {
		return this.destinatarioInvioNsoXmlId;
	}

	public void setDestinatarioInvioNsoXmlId(Integer destinatarioInvioNsoXmlId) {
		this.destinatarioInvioNsoXmlId = destinatarioInvioNsoXmlId;
	}

	public String getDataConsegna() {
		return this.dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getDataRicezione() {
		return dataRicezione;
	}

	public void setDataRicezione(String dataRicezione) {
		this.dataRicezione = dataRicezione;
	}

	public Date getDataSpostamento() {
		return this.dataSpostamento;
	}

	public void setDataSpostamento(Date dataSpostamento) {
		this.dataSpostamento = dataSpostamento;
	}

	public String getFileXml() {
		return this.fileXml;
	}

	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}

	public String getMetadatiXml() {
		return metadatiXml;
	}

	public void setMetadatiXml(String metadatiXml) {
		this.metadatiXml = metadatiXml;
	}

	public String getIdentificativoTrasmissione() {
		return this.identificativoTrasmissione;
	}

	public void setIdentificativoTrasmissione(String identificativoTrasmissione) {
		this.identificativoTrasmissione = identificativoTrasmissione;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPathFile() {
		return this.pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getTipodoc() {
		return this.tipodoc;
	}

	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public CpassTOrdDestinatarioInvioNso getCpassTOrdDestinatarioInvioNso() {
		return this.cpassTOrdDestinatarioInvioNso;
	}

	public void setCpassTOrdDestinatarioInvioNso(CpassTOrdDestinatarioInvioNso cpassTOrdDestinatarioInvioNso) {
		this.cpassTOrdDestinatarioInvioNso = cpassTOrdDestinatarioInvioNso;
	}

	@Override
	public Integer getId() {
		return destinatarioInvioNsoXmlId;
	}

	@Override
	public void setId(Integer id) {
		destinatarioInvioNsoXmlId = id;
	}
}
