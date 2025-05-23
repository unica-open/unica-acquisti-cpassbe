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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;



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

	@Column(name="cap_destinatario")
	private String capDestinatario;

	@Column(name="contatto_destinatario")
	private String contattoDestinatario;

	@Column(name="data_consegna")
	private String dataConsegna;

	@Column(name="despatch_advice_id")
	private String despatchAdviceId;

	@Column(name="endpoint_id")
	private String endpointId;

	@Column(name="id_notier")
	private String idNotier;

	@Column(name="indirizzo_destinatario")
	private String indirizzoDestinatario;

	@Column(name="localita_destinatario")
	private String localitaDestinatario;

	private String note;

	@Column(name="ordine_unico_data")
	private String ordineUnicoData;

	@Column(name="ordine_unico_id")
	private String ordineUnicoId;

	@Column(name="ordine_unico_tipo")
	private String ordineUnicoTipo;

	@Column(name="partita_iva_fornitore")
	private String partitaIvaFornitore;

	@Column(name="provincia_destinatario")
	private String provinciaDestinatario;

	@Column(name="ragione_sociale_fornitore")
	private String ragioneSocialeFornitore;

	@Column(name="settore_emittente_ordine")
	private String settoreEmittenteOrdine;

	@Column(name="telefono_destinatario")
	private String telefonoDestinatario;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@ManyToOne
	@JoinColumn(name="testata_ordine_id")
	private CpassTOrdTestataOrdine cpassTOrdTestataOrdine;


	//bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name="stato_id")
	private CpassDStato cpassDStato;

	//bi-directional many-to-one association to CpassTFornitore
	@ManyToOne
	@JoinColumn(name="fornitore_id")
	private CpassTFornitore cpassTFornitore;

	//bi-directional many-to-one association to CpassTOrdTestataEvasione
	@ManyToOne
	@JoinColumn(name="testata_evasione_id")
	private CpassTOrdTestataEvasione cpassTOrdTestataEvasione;

	//bi-directional many-to-one association to CpassTOrdDocumentoTrasportoRiga
	@OneToMany(mappedBy="cpassTOrdDocumentoTrasporto")
	private List<CpassTOrdDocumentoTrasportoRiga> cpassTOrdDocumentoTrasportoRigas;

	//bi-directional many-to-one association to CpassTOrdDocumentoTrasportoXml
	@OneToMany(mappedBy="cpassTOrdDocumentoTrasporto")
	private List<CpassTOrdDocumentoTrasportoXml> cpassTOrdDocumentoTrasportoXmls;

	public CpassTOrdDocumentoTrasporto() {
	}

	public Integer getDocumentoTrasportoId() {
		return this.documentoTrasportoId;
	}

	public void setDocumentoTrasportoId(Integer documentoTrasportoId) {
		this.documentoTrasportoId = documentoTrasportoId;
	}

	public String getCapDestinatario() {
		return this.capDestinatario;
	}

	public void setCapDestinatario(String capDestinatario) {
		this.capDestinatario = capDestinatario;
	}

	public String getContattoDestinatario() {
		return this.contattoDestinatario;
	}

	public void setContattoDestinatario(String contattoDestinatario) {
		this.contattoDestinatario = contattoDestinatario;
	}

	public String getDataConsegna() {
		return this.dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getDespatchAdviceId() {
		return this.despatchAdviceId;
	}

	public void setDespatchAdviceId(String despatchAdviceId) {
		this.despatchAdviceId = despatchAdviceId;
	}

	public String getEndpointId() {
		return this.endpointId;
	}

	public void setEndpointId(String endpointId) {
		this.endpointId = endpointId;
	}

	public String getIdNotier() {
		return this.idNotier;
	}

	public void setIdNotier(String idNotier) {
		this.idNotier = idNotier;
	}

	public String getIndirizzoDestinatario() {
		return this.indirizzoDestinatario;
	}

	public void setIndirizzoDestinatario(String indirizzoDestinatario) {
		this.indirizzoDestinatario = indirizzoDestinatario;
	}

	public String getLocalitaDestinatario() {
		return this.localitaDestinatario;
	}

	public void setLocalitaDestinatario(String localitaDestinatario) {
		this.localitaDestinatario = localitaDestinatario;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrdineUnicoData() {
		return this.ordineUnicoData;
	}

	public void setOrdineUnicoData(String ordineUnicoData) {
		this.ordineUnicoData = ordineUnicoData;
	}

	public String getOrdineUnicoId() {
		return this.ordineUnicoId;
	}

	public void setOrdineUnicoId(String ordineUnicoId) {
		this.ordineUnicoId = ordineUnicoId;
	}

	public String getOrdineUnicoTipo() {
		return this.ordineUnicoTipo;
	}

	public void setOrdineUnicoTipo(String ordineUnicoTipo) {
		this.ordineUnicoTipo = ordineUnicoTipo;
	}

	public String getPartitaIvaFornitore() {
		return this.partitaIvaFornitore;
	}

	public void setPartitaIvaFornitore(String partitaIvaFornitore) {
		this.partitaIvaFornitore = partitaIvaFornitore;
	}

	public String getProvinciaDestinatario() {
		return this.provinciaDestinatario;
	}

	public void setProvinciaDestinatario(String provinciaDestinatario) {
		this.provinciaDestinatario = provinciaDestinatario;
	}

	public String getRagioneSocialeFornitore() {
		return this.ragioneSocialeFornitore;
	}

	public void setRagioneSocialeFornitore(String ragioneSocialeFornitore) {
		this.ragioneSocialeFornitore = ragioneSocialeFornitore;
	}

	public String getSettoreEmittenteOrdine() {
		return this.settoreEmittenteOrdine;
	}

	public void setSettoreEmittenteOrdine(String settoreEmittenteOrdine) {
		this.settoreEmittenteOrdine = settoreEmittenteOrdine;
	}

	public String getTelefonoDestinatario() {
		return this.telefonoDestinatario;
	}

	public void setTelefonoDestinatario(String telefonoDestinatario) {
		this.telefonoDestinatario = telefonoDestinatario;
	}

	public CpassDStato getCpassDStato() {
		return this.cpassDStato;
	}

	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public CpassTFornitore getCpassTFornitore() {
		return this.cpassTFornitore;
	}

	public void setCpassTFornitore(CpassTFornitore cpassTFornitore) {
		this.cpassTFornitore = cpassTFornitore;
	}

	public CpassTOrdTestataEvasione getCpassTOrdTestataEvasione() {
		return this.cpassTOrdTestataEvasione;
	}

	public void setCpassTOrdTestataEvasione(CpassTOrdTestataEvasione cpassTOrdTestataEvasione) {
		this.cpassTOrdTestataEvasione = cpassTOrdTestataEvasione;
	}

	public List<CpassTOrdDocumentoTrasportoRiga> getCpassTOrdDocumentoTrasportoRigas() {
		return this.cpassTOrdDocumentoTrasportoRigas;
	}

	public void setCpassTOrdDocumentoTrasportoRigas(List<CpassTOrdDocumentoTrasportoRiga> cpassTOrdDocumentoTrasportoRigas) {
		this.cpassTOrdDocumentoTrasportoRigas = cpassTOrdDocumentoTrasportoRigas;
	}

	public CpassTOrdDocumentoTrasportoRiga addCpassTOrdDocumentoTrasportoRiga(CpassTOrdDocumentoTrasportoRiga cpassTOrdDocumentoTrasportoRiga) {
		getCpassTOrdDocumentoTrasportoRigas().add(cpassTOrdDocumentoTrasportoRiga);
		cpassTOrdDocumentoTrasportoRiga.setCpassTOrdDocumentoTrasporto(this);

		return cpassTOrdDocumentoTrasportoRiga;
	}

	public CpassTOrdDocumentoTrasportoRiga removeCpassTOrdDocumentoTrasportoRiga(CpassTOrdDocumentoTrasportoRiga cpassTOrdDocumentoTrasportoRiga) {
		getCpassTOrdDocumentoTrasportoRigas().remove(cpassTOrdDocumentoTrasportoRiga);
		cpassTOrdDocumentoTrasportoRiga.setCpassTOrdDocumentoTrasporto(null);

		return cpassTOrdDocumentoTrasportoRiga;
	}

	public List<CpassTOrdDocumentoTrasportoXml> getCpassTOrdDocumentoTrasportoXmls() {
		return this.cpassTOrdDocumentoTrasportoXmls;
	}

	public void setCpassTOrdDocumentoTrasportoXmls(List<CpassTOrdDocumentoTrasportoXml> cpassTOrdDocumentoTrasportoXmls) {
		this.cpassTOrdDocumentoTrasportoXmls = cpassTOrdDocumentoTrasportoXmls;
	}

	public CpassTOrdDocumentoTrasportoXml addCpassTOrdDocumentoTrasportoXml(CpassTOrdDocumentoTrasportoXml cpassTOrdDocumentoTrasportoXml) {
		getCpassTOrdDocumentoTrasportoXmls().add(cpassTOrdDocumentoTrasportoXml);
		cpassTOrdDocumentoTrasportoXml.setCpassTOrdDocumentoTrasporto(this);

		return cpassTOrdDocumentoTrasportoXml;
	}

	public CpassTOrdDocumentoTrasportoXml removeCpassTOrdDocumentoTrasportoXml(CpassTOrdDocumentoTrasportoXml cpassTOrdDocumentoTrasportoXml) {
		getCpassTOrdDocumentoTrasportoXmls().remove(cpassTOrdDocumentoTrasportoXml);
		cpassTOrdDocumentoTrasportoXml.setCpassTOrdDocumentoTrasporto(null);

		return cpassTOrdDocumentoTrasportoXml;
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
		return documentoTrasportoId;
	}

	@Override
	public void setId(Integer id) {
		documentoTrasportoId=id;
	}
}
