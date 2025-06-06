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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the cpass_d_ord_tipo_ordine database table.
 *
 */
@Entity
@Table(name="cpass_d_ord_tipo_ordine")
@NamedQuery(name="CpassDOrdTipoOrdine.findAll", query="SELECT c FROM CpassDOrdTipoOrdine c")
public class CpassDOrdTipoOrdine extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_D_ORD_TIPO_ORDINE_TIPOORDINEID_GENERATOR", sequenceName="CPASS_D_ORD_TIPO_ORDINE_TIPO_ORDINE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_ORD_TIPO_ORDINE_TIPOORDINEID_GENERATOR")
	@Column(name="tipo_ordine_id")
	private Integer tipoOrdineId;

	@Column(name="tipologia_documento_codice")
	private String tipologiaDocumentoCodice;

	@Column(name="tipologia_documento_descrizione")
	private String tipologiaDocumentoDescrizione;


	@Column(name="flag_trasm_nso")
	private Boolean flagTrasmNso;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@OneToMany(mappedBy="cpassDOrdTipoOrdine")
	private List<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines;

	public CpassDOrdTipoOrdine() {
	}

	public Integer getTipoOrdineId() {
		return this.tipoOrdineId;
	}

	public void setTipoOrdineId(Integer tipoOrdineId) {
		this.tipoOrdineId = tipoOrdineId;
	}



	public String getTipologiaDocumentoCodice() {
		return this.tipologiaDocumentoCodice;
	}

	public void setTipologiaDocumentoCodice(String tipologiaDocumentoCodice) {
		this.tipologiaDocumentoCodice = tipologiaDocumentoCodice;
	}

	public String getTipologiaDocumentoDescrizione() {
		return this.tipologiaDocumentoDescrizione;
	}

	public void setTipologiaDocumentoDescrizione(String tipologiaDocumentoDescrizione) {
		this.tipologiaDocumentoDescrizione = tipologiaDocumentoDescrizione;
	}


	public List<CpassTOrdTestataOrdine> getCpassTOrdTestataOrdines() {
		return this.cpassTOrdTestataOrdines;
	}

	public void setCpassTOrdTestataOrdines(List<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines) {
		this.cpassTOrdTestataOrdines = cpassTOrdTestataOrdines;
	}

	public CpassTOrdTestataOrdine addCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		getCpassTOrdTestataOrdines().add(cpassTOrdTestataOrdine);
		cpassTOrdTestataOrdine.setCpassDOrdTipoOrdine(this);

		return cpassTOrdTestataOrdine;
	}

	public CpassTOrdTestataOrdine removeCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		getCpassTOrdTestataOrdines().remove(cpassTOrdTestataOrdine);
		cpassTOrdTestataOrdine.setCpassDOrdTipoOrdine(null);

		return cpassTOrdTestataOrdine;
	}


	/**
	 * @return the flagTrasmNso
	 */
	public Boolean getFlagTrasmNso() {
		return flagTrasmNso;
	}

	/**
	 * @param flagTrasmNso the flagTrasmNso to set
	 */
	public void setFlagTrasmNso(Boolean flagTrasmNso) {
		this.flagTrasmNso = flagTrasmNso;
	}

	@Override
	public Integer getId() {
		return tipoOrdineId;
	}

	@Override
	public void setId(Integer id) {
		tipoOrdineId = id;
	}

}
