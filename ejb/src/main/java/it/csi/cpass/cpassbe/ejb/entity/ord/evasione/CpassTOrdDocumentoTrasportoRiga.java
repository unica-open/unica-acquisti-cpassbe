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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_documento_trasporto_riga database table.
 * 
 */
@Entity
@Table(name="cpass_t_ord_documento_trasporto_riga")
@NamedQuery(name="CpassTOrdDocumentoTrasportoRiga.findAll", query="SELECT c FROM CpassTOrdDocumentoTrasportoRiga c")
public class CpassTOrdDocumentoTrasportoRiga implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="cpass_t_ord_documento_trasporto_documentoTrasportoRigaId_GENERATOR", sequenceName="CPASS_T_ORD_DOCUMENTO_TRASPORTO_RIGA_DOCUMENTO_TRASPORTO_RIGA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_t_ord_documento_trasporto_documentoTrasportoRigaId_GENERATOR")
	@Column(name="documento_trasporto_riga_id")
	private Integer documentoTrasportoRigaId;
	
//	@Column(name="documento_trasporto_id")
//	private Integer documentoTrasportoId;

	//bi-directional one-to-one association to CpassTDocumentoTrasporto
	@OneToOne
	@JoinColumn(name="documento_trasporto_id")
	private CpassTOrdDocumentoTrasporto cpassTDocumentoTrasporto;

	//bi-directional many-to-one association to CpassTOrdRigaEvasione
	// @OneToMany(mappedBy="cpassTOrdDocumentoTrasportoRiga")
	// private List<CpassTOrdRigaEvasione> cpassTOrdRigaEvasiones;

	public CpassTOrdDocumentoTrasportoRiga() {
	}

//	public Integer getDocumentoTrasportoId() {
//		return this.documentoTrasportoId;
//	}
//
//	public void setDocumentoTrasportoId(Integer documentoTrasportoId) {
//		this.documentoTrasportoId = documentoTrasportoId;
//	}

	public Integer getDocumentoTrasportoRigaId() {
		return this.documentoTrasportoRigaId;
	}

	public void setDocumentoTrasportoRigaId(Integer documentoTrasportoRigaId) {
		this.documentoTrasportoRigaId = documentoTrasportoRigaId;
	}

	public CpassTOrdDocumentoTrasporto getCpassTDocumentoTrasporto() {
		return this.cpassTDocumentoTrasporto;
	}

	public void setCpassTDocumentoTrasporto(CpassTOrdDocumentoTrasporto cpassTDocumentoTrasporto) {
		this.cpassTDocumentoTrasporto = cpassTDocumentoTrasporto;
	}

	@Override
	public Integer getId() {
		return documentoTrasportoRigaId;
	}

	@Override
	public void setId(Integer id) {
		documentoTrasportoRigaId=id;
	}

//	public List<CpassTOrdRigaEvasione> getCpassTOrdRigaEvasiones() {
//		return this.cpassTOrdRigaEvasiones;
//	}
//
//	public void setCpassTOrdRigaEvasiones(List<CpassTOrdRigaEvasione> cpassTOrdRigaEvasiones) {
//		this.cpassTOrdRigaEvasiones = cpassTOrdRigaEvasiones;
//	}

//	public CpassTOrdRigaEvasione addCpassTOrdRigaEvasione(CpassTOrdRigaEvasione cpassTOrdRigaEvasione) {
//		getCpassTOrdRigaEvasiones().add(cpassTOrdRigaEvasione);
//		cpassTOrdRigaEvasione.setCpassTDocumentoTrasportoRiga(this);
//
//		return cpassTOrdRigaEvasione;
//	}
//
//	public CpassTOrdRigaEvasione removeCpassTOrdRigaEvasione(CpassTOrdRigaEvasione cpassTOrdRigaEvasione) {
//		getCpassTOrdRigaEvasiones().remove(cpassTOrdRigaEvasione);
//		cpassTOrdRigaEvasione.setCpassTDocumentoTrasportoRiga(null);
//
//		return cpassTOrdRigaEvasione;
//	}

}
