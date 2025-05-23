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
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

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
 * The persistent class for the cpass_t_ord_documento_trasporto_xml database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_documento_trasporto_xml")
@NamedQuery(name="CpassTOrdDocumentoTrasportoXml.findAll", query="SELECT c FROM CpassTOrdDocumentoTrasportoXml c")
public class CpassTOrdDocumentoTrasportoXml implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="cpass_t_ord_documento_trasporto_xml_documentoTrasportoxmlId_GENERATOR", sequenceName="cpass_t_ord_documento_trasporto__documento_trasporto_xml_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_t_ord_documento_trasporto_xml_documentoTrasportoxmlId_GENERATOR")
	@Column(name="documento_trasporto_xml_id")
	private Integer documentoTrasportoXmlId;

	@Column(name="data_consegna")
	private String dataConsegna;

	@Column(name="data_spostamento")
	private Date dataSpostamento;

	@Column(name="despatch_advice_id")
	private String despatchAdviceId;

	@Column(name="file_xml")
	private String fileXml;

	private String note;

	@Column(name="path_file")
	private String pathFile;

	private String tipodoc;

	//bi-directional many-to-one association to CpassTOrdDocumentoTrasporto
	@ManyToOne
	@JoinColumn(name="documento_trasporto_id")
	private CpassTOrdDocumentoTrasporto cpassTOrdDocumentoTrasporto;

	public CpassTOrdDocumentoTrasportoXml() {
	}

	public Integer getDocumentoTrasportoXmlId() {
		return this.documentoTrasportoXmlId;
	}

	public void setDocumentoTrasportoXmlId(Integer documentoTrasportoXmlId) {
		this.documentoTrasportoXmlId = documentoTrasportoXmlId;
	}

	public String getDataConsegna() {
		return this.dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public Date getDataSpostamento() {
		return this.dataSpostamento;
	}

	public void setDataSpostamento(Date dataSpostamento) {
		this.dataSpostamento = dataSpostamento;
	}

	public String getDespatchAdviceId() {
		return this.despatchAdviceId;
	}

	public void setDespatchAdviceId(String despatchAdviceId) {
		this.despatchAdviceId = despatchAdviceId;
	}

	public String getFileXml() {
		return this.fileXml;
	}

	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
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

	public CpassTOrdDocumentoTrasporto getCpassTOrdDocumentoTrasporto() {
		return this.cpassTOrdDocumentoTrasporto;
	}

	public void setCpassTOrdDocumentoTrasporto(CpassTOrdDocumentoTrasporto cpassTOrdDocumentoTrasporto) {
		this.cpassTOrdDocumentoTrasporto = cpassTOrdDocumentoTrasporto;
	}

	@Override
	public Integer getId() {
		return documentoTrasportoXmlId;
	}

	@Override
	public void setId(Integer id) {
		documentoTrasportoXmlId = id;
	}

}
