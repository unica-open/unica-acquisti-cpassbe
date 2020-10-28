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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;

/**
 * The persistent class for the cpass_t_ord_testata_ordine database table.
 * 
 */
@Entity
@Table(name = "cpass_t_ord_testata_ordine")
@NamedQuery(name = "CpassTOrdTestataOrdine.findAll", query = "SELECT c FROM CpassTOrdTestataOrdine c")
public class CpassTOrdTestataOrdine extends BaseAuditedEntity<UUID> implements Serializable {

	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_testata_ordine");

	@Id
	@Column(name = "testata_ordine_id", unique = true, nullable = false)
	private UUID testataOrdineId;

	@Column(name = "consegna_cap")
	private String consegnaCap;

	@Column(name = "consegna_data_a")
	private Date consegnaDataA;

	@Column(name = "consegna_data_da")
	private Date consegnaDataDa;

	@Column(name = "consegna_indirizzo")
	private String consegnaIndirizzo;

	@Column(name = "consegna_localita")
	private String consegnaLocalita;

	@Column(name = "consegna_riferimento")
	private String consegnaRiferimento;

	@Column(name = "data_autorizzazione")
	private Date dataAutorizzazione;

	@Column(name = "data_conferma")
	private Date dataConferma;

	@Column(name = "data_emissione")
	private Date dataEmissione;

	@Column(name = "data_scadenza")
	private Date dataScadenza;
	
	@Column(name = "data_annullamento")
	private Date dataAnnullamento;

	@Column(name = "descrizione_acquisto")
	private String descrizioneAcquisto;

	@Column(name = "lotto_anno")
	private Integer lottoAnno;

	@Column(name = "lotto_numero")
	private Integer lottoNumero;

	private String note;

	@Column(name = "ordine_anno")
	private Integer ordineAnno;

	@Column(name = "ordine_numero")
	private Integer ordineNumero;

	@Column(name = "provvedimento_anno")
	private Integer provvedimentoAnno;

	@Column(name = "provvedimento_numero")
	private String provvedimentoNumero;

	@Column(name = "totale_con_iva")
	private BigDecimal totaleConIva;

	@Column(name = "totale_no_iva")
	private BigDecimal totaleNoIva;

	@Column(name = "numero_procedura")
	private String numeroProcedura;

	// bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name = "utente_compilatore_id")
	private CpassTUtente cpassTUtente;

	// bi-directional many-to-one association to CpassTOrdDestinatarioOrdine
	@OneToMany(mappedBy = "cpassTOrdTestataOrdine")
	private List<CpassTOrdDestinatarioOrdine> cpassTOrdDestinatarios;

	// bi-directional many-to-one association to CpassDOrdTipoOrdine
	@ManyToOne
	@JoinColumn(name = "tipo_ordine_id")
	private CpassDOrdTipoOrdine cpassDOrdTipoOrdine;

	// bi-directional many-to-one association to CpassDOrdTipoProcedura
	@ManyToOne
	@JoinColumn(name = "tipo_procedura_id")
	private CpassDOrdTipoProcedura cpassDOrdTipoProcedura;

	// bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name = "stato_id")
	private CpassDStato cpassDStato;

	// bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name = "settore_emittente_id")
	private CpassTSettore cpassTSettore;

	// bi-directional many-to-one association to CpassTUfficio
	@ManyToOne
	@JoinColumn(name = "ufficio_id")
	private CpassTUfficio cpassTUfficio;

	// bi-directional many-to-one association to CpassTFornitore
	@ManyToOne
	@JoinColumn(name = "fornitore_id")
	private CpassTFornitore cpassTFornitore;
	
	// bi-directional many-to-one association to CpassDOrdStatoNso
	@ManyToOne
	@JoinColumn(name = "stato_nso_id")
	private CpassDOrdStatoNso cpassDOrdStatoNso;

	public CpassTOrdTestataOrdine() {
	}

	public UUID getTestataOrdineId() {
		return this.testataOrdineId;
	}

	public void setTestataOrdineId(UUID testataOrdineId) {
		this.testataOrdineId = testataOrdineId;
	}

	public String getConsegnaCap() {
		return this.consegnaCap;
	}

	public void setConsegnaCap(String consegnaCap) {
		this.consegnaCap = consegnaCap;
	}

	public Date getConsegnaDataA() {
		return this.consegnaDataA;
	}

	public void setConsegnaDataA(Date consegnaDataA) {
		this.consegnaDataA = consegnaDataA;
	}

	public Date getConsegnaDataDa() {
		return this.consegnaDataDa;
	}

	public void setConsegnaDataDa(Date consegnaDataDa) {
		this.consegnaDataDa = consegnaDataDa;
	}

	public String getConsegnaIndirizzo() {
		return this.consegnaIndirizzo;
	}

	public void setConsegnaIndirizzo(String consegnaIndirizzo) {
		this.consegnaIndirizzo = consegnaIndirizzo;
	}

	public String getConsegnaLocalita() {
		return this.consegnaLocalita;
	}

	public void setConsegnaLocalita(String consegnaLocalita) {
		this.consegnaLocalita = consegnaLocalita;
	}

	public String getConsegnaRiferimento() {
		return this.consegnaRiferimento;
	}

	public void setConsegnaRiferimento(String consegnaRiferimento) {
		this.consegnaRiferimento = consegnaRiferimento;
	}

	public Date getDataAutorizzazione() {
		return this.dataAutorizzazione;
	}

	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	public Date getDataConferma() {
		return this.dataConferma;
	}

	public void setDataConferma(Date dataConferma) {
		this.dataConferma = dataConferma;
	}

	public Date getDataEmissione() {
		return this.dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public Date getDataScadenza() {
		return this.dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
	public Date getDataAnnullamento() {
		return dataAnnullamento;
	}

	public void setDataAnnullamento(Date dataAnnullamento) {
		this.dataAnnullamento = dataAnnullamento;
	}

	public String getDescrizioneAcquisto() {
		return this.descrizioneAcquisto;
	}

	public void setDescrizioneAcquisto(String descrizioneAcquisto) {
		this.descrizioneAcquisto = descrizioneAcquisto;
	}

	/**
	 * @return the cpassTFornitore
	 */
	public CpassTFornitore getCpassTFornitore() {
		return cpassTFornitore;
	}

	/**
	 * @param cpassTFornitore the cpassTFornitore to set
	 */
	public void setCpassTFornitore(CpassTFornitore cpassTFornitore) {
		this.cpassTFornitore = cpassTFornitore;
	}

	public Integer getLottoAnno() {
		return this.lottoAnno;
	}

	public void setLottoAnno(Integer lottoAnno) {
		this.lottoAnno = lottoAnno;
	}

	public Integer getLottoNumero() {
		return this.lottoNumero;
	}

	public void setLottoNumero(Integer lottoNumero) {
		this.lottoNumero = lottoNumero;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getOrdineAnno() {
		return this.ordineAnno;
	}

	public void setOrdineAnno(Integer ordineAnno) {
		this.ordineAnno = ordineAnno;
	}

	public Integer getOrdineNumero() {
		return this.ordineNumero;
	}

	public void setOrdineNumero(Integer ordineNumero) {
		this.ordineNumero = ordineNumero;
	}

	public Integer getProvvedimentoAnno() {
		return this.provvedimentoAnno;
	}

	public void setProvvedimentoAnno(Integer provvedimentoAnno) {
		this.provvedimentoAnno = provvedimentoAnno;
	}

	public String getProvvedimentoNumero() {
		return this.provvedimentoNumero;
	}

	public void setProvvedimentoNumero(String provvedimentoNumero) {
		this.provvedimentoNumero = provvedimentoNumero;
	}

	public BigDecimal getTotaleConIva() {
		return this.totaleConIva;
	}

	public void setTotaleConIva(BigDecimal totaleConIva) {
		this.totaleConIva = totaleConIva;
	}

	public BigDecimal getTotaleNoIva() {
		return this.totaleNoIva;
	}

	public void setTotaleNoIva(BigDecimal totaleNoIva) {
		this.totaleNoIva = totaleNoIva;
	}

	public CpassTUtente getCpassTUtente() {
		return this.cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}

	public List<CpassTOrdDestinatarioOrdine> getCpassTOrdDestinatarios() {
		return this.cpassTOrdDestinatarios;
	}

	public void setCpassTOrdDestinatarios(List<CpassTOrdDestinatarioOrdine> cpassTOrdDestinatarios) {
		this.cpassTOrdDestinatarios = cpassTOrdDestinatarios;
	}

	public CpassTOrdDestinatarioOrdine addCpassTOrdDestinatario(CpassTOrdDestinatarioOrdine cpassTOrdDestinatario) {
		getCpassTOrdDestinatarios().add(cpassTOrdDestinatario);
		cpassTOrdDestinatario.setCpassTOrdTestataOrdine(this);

		return cpassTOrdDestinatario;
	}

	public CpassTOrdDestinatarioOrdine removeCpassTOrdDestinatario(CpassTOrdDestinatarioOrdine cpassTOrdDestinatario) {
		getCpassTOrdDestinatarios().remove(cpassTOrdDestinatario);
		cpassTOrdDestinatario.setCpassTOrdTestataOrdine(null);

		return cpassTOrdDestinatario;
	}

	public CpassDOrdTipoOrdine getCpassDOrdTipoOrdine() {
		return this.cpassDOrdTipoOrdine;
	}

	public void setCpassDOrdTipoOrdine(CpassDOrdTipoOrdine cpassDOrdTipoOrdine) {
		this.cpassDOrdTipoOrdine = cpassDOrdTipoOrdine;
	}

	public CpassDOrdTipoProcedura getCpassDOrdTipoProcedura() {
		return this.cpassDOrdTipoProcedura;
	}

	public void setCpassDOrdTipoProcedura(CpassDOrdTipoProcedura cpassDOrdTipoProcedura) {
		this.cpassDOrdTipoProcedura = cpassDOrdTipoProcedura;
	}

	public CpassDStato getCpassDStato() {
		return this.cpassDStato;
	}

	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	public CpassTUfficio getCpassTUfficio() {
		return this.cpassTUfficio;
	}

	public void setCpassTUfficio(CpassTUfficio cpassTUfficio) {
		this.cpassTUfficio = cpassTUfficio;
	}

	@Override
	public UUID getId() {
		return testataOrdineId;
	}

	@Override
	public void setId(UUID id) {
		testataOrdineId = id;
	}

	/**
	 * @return the numeroProcedura
	 */
	public String getNumeroProcedura() {
		return numeroProcedura;
	}

	/**
	 * @param numeroProcedura the numeroProcedura to set
	 */
	public void setNumeroProcedura(String numeroProcedura) {
		this.numeroProcedura = numeroProcedura;
	}
	
	/**
	 * @return the cpassDOrdStatoNso
	 */
	public CpassDOrdStatoNso getCpassDOrdStatoNso() {
		return cpassDOrdStatoNso;
	}

	/**
	 * @param cpassDOrdStatoNso the cpassDOrdStatoNso to set
	 */
	public void setCpassDOrdStatoNso(CpassDOrdStatoNso cpassDOrdStatoNso) {
		this.cpassDOrdStatoNso = cpassDOrdStatoNso;
	}

	@Override
	public void initId() {
		this.testataOrdineId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, ordineAnno + "|" + ordineNumero + "|" + cpassTSettore.getId());
	}

}
