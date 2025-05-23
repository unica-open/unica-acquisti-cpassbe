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



@Entity
@Table(name="cpass_t_ord_documenti_ordine")
@NamedQuery(name="CpassTOrdDocumentiOrdine.findAll", query="SELECT c FROM CpassTOrdDocumentiOrdine c")
public class CpassTOrdDocumentiOrdine extends BaseAuditedEntity<Integer> {
	@Id
	@SequenceGenerator(name="CPASS_T_ORD_DOCUMENTI_ORDINE_DOCUMENTIORDINEID_GENERATOR", sequenceName="cpass_t_ord_documenti_ordine_documenti_ordine_id_seq", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_ORD_DOCUMENTI_ORDINE_DOCUMENTIORDINEID_GENERATOR")
	@Column(name="documenti_ordine_id")
	private Integer documentiOrdineId;

	private String descrizione;

	private String ext;

	@Column(name="file")
	private byte[] file;

	private String nomefile;

	private Integer progressivo;

	@ManyToOne
	@JoinColumn(name="testata_ordine_id")
	private CpassTOrdTestataOrdine cpassTOrdTestataOrdine;

	/**
	 * @return the documentiOrdineId
	 */
	public Integer getDocumentiOrdineId() {
		return documentiOrdineId;
	}

	/**
	 * @param documentiOrdineId the documentiOrdineId to set
	 */
	public void setDocumentiOrdineId(Integer documentiOrdineId) {
		this.documentiOrdineId = documentiOrdineId;
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
	 * @return the fileDocumento
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param fileDocumento the fileDocumento to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}

	/**
	 * @return the nomefile
	 */
	public String getNomefile() {
		return nomefile;
	}

	/**
	 * @param nomefile the nomefile to set
	 */
	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
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

	@Override
	public Integer getId() {
		return documentiOrdineId;
	}

	@Override
	public void setId(Integer id) {
		documentiOrdineId = id;
	}

}
