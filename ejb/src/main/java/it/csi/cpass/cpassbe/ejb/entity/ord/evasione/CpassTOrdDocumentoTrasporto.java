/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_documento_trasporto database table.
 * 
 */
@Entity
@Table(name="cpass_t_ord_documento_trasporto")
@NamedQuery(name="CpassTOrdDocumentoTrasporto.findAll", query="SELECT c FROM CpassTOrdDocumentoTrasporto c")
public class CpassTOrdDocumentoTrasporto implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id	
	@SequenceGenerator(name="cpass_t_ord_documento_trasporto_documentoTrasportoId_GENERATOR", sequenceName="CPASS_T_ORD_DOCUMENTO_TRASPORTO_DOCUMENTO_TRASPORTO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_t_ord_documento_trasporto_documentoTrasportoId_GENERATOR")
	@Column(name="documento_trasporto_id")
	private Integer documentoTrasportoId;

	public CpassTOrdDocumentoTrasporto() {
	}

	public Integer getDocumentoTrasportoId() {
		return this.documentoTrasportoId;
	}

	public void setDocumentoTrasportoId(Integer documentoTrasportoId) {
		this.documentoTrasportoId = documentoTrasportoId;
	}

	@Override
	public Integer getId() {
		return documentoTrasportoId;
	}

	@Override
	public void setId(Integer id) {
		documentoTrasportoId=id;
	}
}
