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
import java.math.BigDecimal;

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
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;



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
	@SequenceGenerator(name="cpass_t_ord_documento_trasporto_documentoTrasportoRigaId_GENERATOR", sequenceName="cpass_t_ord_documento_trasporto_documento_trasporto_riga_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_t_ord_documento_trasporto_documentoTrasportoRigaId_GENERATOR")
	@Column(name="documento_trasporto_riga_id")
	private Integer documentoTrasportoRigaId;

	@Column(name="codice_listino_fornitore")
	private String codiceListinoFornitore;

	@Column(name="motivo_qta_inevasa")
	private String motivoQtaInevasa;

	@Column(name="note_fornitore")
	private String noteFornitore;

	@Column(name="ordine_data")
	private String ordineData;

	@Column(name="ordine_nso_id")
	private String ordineNsoId;

	@Column(name="ordine_tipo")
	private String ordineTipo;

	@Column(name="progressivo_riga_id")
	private String progressivoRigaId;

	@Column(name="progressivo_riga_ordine_evasa")
	private String progressivoRigaOrdineEvasa;

	@Column(name="qta_evasa")
	private BigDecimal qtaEvasa;

	@Column(name="qta_inevasa")
	private BigDecimal qtaInevasa;

	@Column(name="unita_misura")
	private String unitaMisura;

	//bi-directional many-to-one association to CpassTOrdDocumentoTrasporto
	@ManyToOne
	@JoinColumn(name="documento_trasporto_id")
	private CpassTOrdDocumentoTrasporto cpassTOrdDocumentoTrasporto;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@ManyToOne
	@JoinColumn(name="riga_ordine_id")
	private CpassTOrdRigaOrdine cpassTOrdRigaOrdine;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@ManyToOne
	@JoinColumn(name="riga_evasione_id")
	private CpassTOrdRigaEvasione cpassTOrdRigaEvasione;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@ManyToOne
	@JoinColumn(name="testata_ordine_id")
	private CpassTOrdTestataOrdine cpassTOrdTestataOrdine;

	public CpassTOrdDocumentoTrasportoRiga() {
	}

	public Integer getDocumentoTrasportoRigaId() {
		return this.documentoTrasportoRigaId;
	}

	public void setDocumentoTrasportoRigaId(Integer documentoTrasportoRigaId) {
		this.documentoTrasportoRigaId = documentoTrasportoRigaId;
	}

	public String getCodiceListinoFornitore() {
		return this.codiceListinoFornitore;
	}

	public void setCodiceListinoFornitore(String codiceListinoFornitore) {
		this.codiceListinoFornitore = codiceListinoFornitore;
	}

	public String getMotivoQtaInevasa() {
		return this.motivoQtaInevasa;
	}

	public void setMotivoQtaInevasa(String motivoQtaInevasa) {
		this.motivoQtaInevasa = motivoQtaInevasa;
	}

	public String getNoteFornitore() {
		return this.noteFornitore;
	}

	public void setNoteFornitore(String noteFornitore) {
		this.noteFornitore = noteFornitore;
	}

	public String getOrdineData() {
		return this.ordineData;
	}

	public void setOrdineData(String ordineData) {
		this.ordineData = ordineData;
	}

	public String getOrdineNsoId() {
		return this.ordineNsoId;
	}

	public void setOrdineNsoId(String ordineNsoId) {
		this.ordineNsoId = ordineNsoId;
	}

	public String getOrdineTipo() {
		return this.ordineTipo;
	}

	public void setOrdineTipo(String ordineTipo) {
		this.ordineTipo = ordineTipo;
	}

	public String getProgressivoRigaId() {
		return this.progressivoRigaId;
	}

	public void setProgressivoRigaId(String progressivoRigaId) {
		this.progressivoRigaId = progressivoRigaId;
	}

	public String getProgressivoRigaOrdineEvasa() {
		return this.progressivoRigaOrdineEvasa;
	}

	public void setProgressivoRigaOrdineEvasa(String progressivoRigaOrdineEvasa) {
		this.progressivoRigaOrdineEvasa = progressivoRigaOrdineEvasa;
	}

	public BigDecimal getQtaEvasa() {
		return this.qtaEvasa;
	}

	public void setQtaEvasa(BigDecimal qtaEvasa) {
		this.qtaEvasa = qtaEvasa;
	}

	public BigDecimal getQtaInevasa() {
		return this.qtaInevasa;
	}

	public void setQtaInevasa(BigDecimal qtaInevasa) {
		this.qtaInevasa = qtaInevasa;
	}

	public String getUnitaMisura() {
		return this.unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	public CpassTOrdDocumentoTrasporto getCpassTOrdDocumentoTrasporto() {
		return this.cpassTOrdDocumentoTrasporto;
	}

	public void setCpassTOrdDocumentoTrasporto(CpassTOrdDocumentoTrasporto cpassTOrdDocumentoTrasporto) {
		this.cpassTOrdDocumentoTrasporto = cpassTOrdDocumentoTrasporto;
	}

	public CpassTOrdRigaOrdine getCpassTOrdRigaOrdine() {
		return this.cpassTOrdRigaOrdine;
	}

	public void setCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		this.cpassTOrdRigaOrdine = cpassTOrdRigaOrdine;
	}

	public CpassTOrdTestataOrdine getCpassTOrdTestataOrdine() {
		return this.cpassTOrdTestataOrdine;
	}

	public void setCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		this.cpassTOrdTestataOrdine = cpassTOrdTestataOrdine;
	}

	/**
	 * @return the cpassTOrdRigaEvasione
	 */
	public CpassTOrdRigaEvasione getCpassTOrdRigaEvasione() {
		return cpassTOrdRigaEvasione;
	}

	/**
	 * @param cpassTOrdRigaEvasione the cpassTOrdRigaEvasione to set
	 */
	public void setCpassTOrdRigaEvasione(CpassTOrdRigaEvasione cpassTOrdRigaEvasione) {
		this.cpassTOrdRigaEvasione = cpassTOrdRigaEvasione;

	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return documentoTrasportoRigaId;
	}

	@Override
	public void setId(Integer id) {
		documentoTrasportoRigaId = id;

	}



}
