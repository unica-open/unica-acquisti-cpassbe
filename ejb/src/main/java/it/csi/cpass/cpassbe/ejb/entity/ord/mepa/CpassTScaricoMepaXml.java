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
package it.csi.cpass.cpassbe.ejb.entity.ord.mepa;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * The persistent class for the cpass_t_scarico_mepa_xml database table.
 *
 */
@Entity
@Table(name="cpass_t_scarico_mepa_xml")
@NamedQuery(name="CpassTScaricoMepaXml.findAll", query="SELECT c FROM CpassTScaricoMepaXml c")
public class CpassTScaricoMepaXml implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_SCARICO_MEPA_XML_SCARICOMEPAXMLID_GENERATOR", sequenceName="cpass_t_scarico_mepa_xml_scarico_mepa_xml_id_seq", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_SCARICO_MEPA_XML_SCARICOMEPAXMLID_GENERATOR")
	@Column(name="scarico_mepa_xml_id")
	private Integer scaricoMepaXmlId;

	@Column(name="data_spostamento")
	private Timestamp dataSpostamento;

	@Column(name="file_xml")
	private String fileXml;

	@Column(name="path_file")
	private String pathFile;

	//bi-directional many-to-one association to CpassTScaricoMepaTestata
	@ManyToOne
	@JoinColumn(name="scarico_mepa_testata_id")
	private CpassTScaricoMepaTestata cpassTScaricoMepaTestata;

	public CpassTScaricoMepaXml() {
	}

	public Integer getScaricoMepaXmlId() {
		return this.scaricoMepaXmlId;
	}

	public void setScaricoMepaXmlId(Integer scaricoMepaXmlId) {
		this.scaricoMepaXmlId = scaricoMepaXmlId;
	}

	public Timestamp getDataSpostamento() {
		return this.dataSpostamento;
	}

	public void setDataSpostamento(Timestamp dataSpostamento) {
		this.dataSpostamento = dataSpostamento;
	}

	public String getFileXml() {
		return this.fileXml;
	}

	public void setFileXml(String fileXml) {
		this.fileXml = fileXml;
	}

	public String getPathFile() {
		return this.pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public CpassTScaricoMepaTestata getCpassTScaricoMepaTestata() {
		return this.cpassTScaricoMepaTestata;
	}

	public void setCpassTScaricoMepaTestata(CpassTScaricoMepaTestata cpassTScaricoMepaTestata) {
		this.cpassTScaricoMepaTestata = cpassTScaricoMepaTestata;
	}

	@Override
	public Integer getId() {
		return scaricoMepaXmlId;
	}

	@Override
	public void setId(Integer id) {
		scaricoMepaXmlId = id;
	}
}
