/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;
import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaTestata;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.TestataRda;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;

/**
 * The type Testata Ordine
 */
public class TestataOrdine extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// testata_ordine_id

	private Settore settore; // vedi settore_emittente_id;
	private Ufficio ufficio; // vedi ufficio_id - cpass_t_ufficio
	private Integer anno; // ordine_anno
	private Integer numero; // ordine_numero
	private Utente utenteCompilatore;
	private Stato stato; // vedi stato_id
	private Date dataEmissione; // data_emissione
	private Date dataConferma; // data_conferma
	private Date dataAutorizzazione; // data_autorizzazione
	private Date dataScadenza; // data_scadenza
	private Date dataAnnullamento;
	private Date dataInvioFirma; // data_invio_firma
	private StatoNso statoNso;
	private String descrizione; // descrizione_acquisto ???
	private String note; // note
	private TipoOrdine tipoOrdine; // tipo_ordine_id - cpass_d_ord_tipo_ordine
	private Integer lottoAnno; // lotto_anno
	private Integer lottoNumero; // lotto_numero
	private Fornitore fornitore; // fornitore_id - vedi cpass_t_fornitore

	// oggetto, altra tabella?
	private Provvedimento provvedimento; // provvedimento_anno, provvedimento_numero

	private TipoProceduraOrd tipoProceduraOrd; // tipo_procedura_id - cpass_d_ord_tipo_procedura
	private String numeroProcedura;

	private String consegnaRiferimento; // consegna_riferimento
	private Date consegnaDataDa; // consegna_data_da
	private Date consegnaDataA; // consegna_data_a
	private String consegnaIndirizzo; // consegna_indirizzo
	private String consegnaCap; // consegna_cap
	private String consegnaLocalita; // consegna_localita
	private BigDecimal totaleConIva;
	private BigDecimal totaleNoIva;

	//codice settore provvedimento
	private String provvedimentoSettore;
	private String provvedimentoTipo;
	private String provvedimentoDescrizione;

	private SettoreInterventi tipoAcquisto;

	private List<Destinatario> listDestinatario = new ArrayList<>();
	private List<Impegno> listImpegno = new ArrayList<>();
	private Boolean hasOds;
	private ScaricoMepaTestata scaricoMepaTestata;
	private String cig;
	private MotiviEsclusioneCig motiviEsclusioneCig;
	private List<ProtocolloOrdine> protocolloOrdines;
	private List<TestataRda> rdas;
	private List<DocumentiOrdine> documentiOrdines;
	private Boolean derivato;
	private Integer annoRda; // ordine_anno
	private Integer numeroRda; // ordine_numero
	private Boolean protocollato;
	private Integer annoProtocollo; // protocollo_anno
	private String numeroProtocollo; // protocollo_numero
	private String aoo; // ordine_numero

	/** Default constructor */
	public TestataOrdine() {
	}

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public TestataOrdine(UUID id) {
		super(id);
	}


	public List<ProtocolloOrdine> getProtocolloOrdines() {
		return protocolloOrdines;
	}

	public void setProtocolloOrdines(List<ProtocolloOrdine> protocolloOrdines) {
		this.protocolloOrdines = protocolloOrdines;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	/**
	 * @return the ufficio
	 */
	public Ufficio getUfficio() {
		return ufficio;
	}

	/**
	 * @param ufficio the ufficio to set
	 */
	public void setUfficio(Ufficio ufficio) {
		this.ufficio = ufficio;
	}

	/**
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * @param anno the anno to set
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @return the stato
	 */
	public Stato getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

	/**
	 * @return the dataEmissione
	 */
	public Date getDataEmissione() {
		return dataEmissione;
	}

	/**
	 * @param dataEmissione the dataEmissione to set
	 */
	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	/**
	 * @return the dataConferma
	 */
	public Date getDataConferma() {
		return dataConferma;
	}

	/**
	 * @param dataConferma the dataConferma to set
	 */
	public void setDataConferma(Date dataConferma) {
		this.dataConferma = dataConferma;
	}

	/**
	 * @return the dataAutorizzazione
	 */
	public Date getDataAutorizzazione() {
		return dataAutorizzazione;
	}

	/**
	 * @param dataAutorizzazione the dataAutorizzazione to set
	 */
	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	/**
	 * @return the dataScadenza
	 */
	public Date getDataScadenza() {
		return dataScadenza;
	}

	/**
	 * @param dataScadenza the dataScadenza to set
	 */
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**
	 * @return the dataInvioFirma
	 */
	public Date getDataInvioFirma() {
		return dataInvioFirma;
	}

	/**
	 * @param dataInvioFirma the dataInvioFirma to set
	 */
	public void setDataInvioFirma(Date dataInvioFirma) {
		this.dataInvioFirma = dataInvioFirma;
	}

	/**
	 * @return the statoNso
	 */
	public StatoNso getStatoNso() {
		return statoNso;
	}

	/**
	 * @param statoNso the statoNso to set
	 */
	public void setStatoNso(StatoNso statoNso) {
		this.statoNso = statoNso;
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
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the tipoOrdine
	 */
	public TipoOrdine getTipoOrdine() {
		return tipoOrdine;
	}

	/**
	 * @param tipoOrdine the tipoOrdine to set
	 */
	public void setTipoOrdine(TipoOrdine tipoOrdine) {
		this.tipoOrdine = tipoOrdine;
	}

	/**
	 * @return the lottoAnno
	 */
	public Integer getLottoAnno() {
		return lottoAnno;
	}

	/**
	 * @param lottoAnno the lottoAnno to set
	 */
	public void setLottoAnno(Integer lottoAnno) {
		this.lottoAnno = lottoAnno;
	}

	/**
	 * @return the lottoNumero
	 */
	public Integer getLottoNumero() {
		return lottoNumero;
	}

	/**
	 * @param lottoNumero the lottoNumero to set
	 */
	public void setLottoNumero(Integer lottoNumero) {
		this.lottoNumero = lottoNumero;
	}

	/**
	 * @return the fornitore
	 */
	public Fornitore getFornitore() {
		return fornitore;
	}

	/**
	 * @param fornitore the fornitore to set
	 */
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	/**
	 * @return the provvedimento
	 */
	public Provvedimento getProvvedimento() {
		return provvedimento;
	}

	/**
	 * @param provvedimento the provvedimento to set
	 */
	public void setProvvedimento(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}

	public String getProvvedimentoDescrizione() {
		return provvedimentoDescrizione;
	}


	public void setProvvedimentoDescrizione(String provvedimentoDescrizione) {
		this.provvedimentoDescrizione = provvedimentoDescrizione;
	}


	/**
	 * @return the tipoProcedura
	 */
	public TipoProceduraOrd getTipoProceduraOrd() {
		return tipoProceduraOrd;
	}

	/**
	 * @param tipoProceduraOrd the tipoProcedura to set
	 */
	public void setTipoProceduraOrd(TipoProceduraOrd tipoProceduraOrd) {
		this.tipoProceduraOrd = tipoProceduraOrd;
	}

	/**
	 * @return the consegnaRiferimento
	 */
	public String getConsegnaRiferimento() {
		return consegnaRiferimento;
	}

	/**
	 * @param consegnaRiferimento the consegnaRiferimento to set
	 */
	public void setConsegnaRiferimento(String consegnaRiferimento) {
		this.consegnaRiferimento = consegnaRiferimento;
	}

	/**
	 * @return the consegnaDataDa
	 */
	public Date getConsegnaDataDa() {
		return consegnaDataDa;
	}

	/**
	 * @param consegnaDataDa the consegnaDataDa to set
	 */
	public void setConsegnaDataDa(Date consegnaDataDa) {
		this.consegnaDataDa = consegnaDataDa;
	}

	/**
	 * @return the consegnaDataA
	 */
	public Date getConsegnaDataA() {
		return consegnaDataA;
	}

	/**
	 * @param consegnaDataA the consegnaDataA to set
	 */
	public void setConsegnaDataA(Date consegnaDataA) {
		this.consegnaDataA = consegnaDataA;
	}

	/**
	 * @return the consegnaIndirizzo
	 */
	public String getConsegnaIndirizzo() {
		return consegnaIndirizzo;
	}

	/**
	 * @param consegnaIndirizzo the consegnaIndirizzo to set
	 */
	public void setConsegnaIndirizzo(String consegnaIndirizzo) {
		this.consegnaIndirizzo = consegnaIndirizzo;
	}

	/**
	 * @return the consegnaCap
	 */
	public String getConsegnaCap() {
		return consegnaCap;
	}

	/**
	 * @param consegnaCap the consegnaCap to set
	 */
	public void setConsegnaCap(String consegnaCap) {
		this.consegnaCap = consegnaCap;
	}

	/**
	 * @return the consegnaLocalita
	 */
	public String getConsegnaLocalita() {
		return consegnaLocalita;
	}

	/**
	 * @param consegnaLocalita the consegnaLocalita to set
	 */
	public void setConsegnaLocalita(String consegnaLocalita) {
		this.consegnaLocalita = consegnaLocalita;
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
	 * @return the utenteCompilatore
	 */
	public Utente getUtenteCompilatore() {
		return utenteCompilatore;
	}

	/**
	 * @param utenteCompilatore the utenteCompilatore to set
	 */
	public void setUtenteCompilatore(Utente utenteCompilatore) {
		this.utenteCompilatore = utenteCompilatore;
	}

	/**
	 * @return the listImpegno
	 */
	public List<Impegno> getListImpegno() {
		return listImpegno;
	}

	/**
	 * @param listImpegno the listImpegno to set
	 */
	public void setListImpegno(List<Impegno> listImpegno) {
		this.listImpegno = listImpegno;
	}

	public SettoreInterventi getTipoAcquisto() {
		return tipoAcquisto;
	}

	public void setTipoAcquisto(SettoreInterventi tipoAcquisto) {
		this.tipoAcquisto = tipoAcquisto;
	}
	/**
	 * @return the listDestinatario
	 */
	public List<Destinatario> getListDestinatario() {
		return listDestinatario;
	}

	/**
	 * @param listDestinatario the listDestinatario to set
	 */
	public void setListDestinatario(List<Destinatario> listDestinatario) {
		this.listDestinatario = listDestinatario;
	}

	/**
	 * @return the totaleConIva
	 */
	public BigDecimal getTotaleConIva() {
		return totaleConIva;
	}

	/**
	 * @param totaleConIva the totaleConIva to set
	 */
	public void setTotaleConIva(BigDecimal totaleConIva) {
		this.totaleConIva = totaleConIva;
	}

	/**
	 * @return the totaleNoIva
	 */
	public BigDecimal getTotaleNoIva() {
		return totaleNoIva;
	}

	/**
	 * @param totaleNoIva the totaleNoIva to set
	 */
	public void setTotaleNoIva(BigDecimal totaleNoIva) {
		this.totaleNoIva = totaleNoIva;
	}

	/**
	 * @return the dataAnnullamento
	 */
	public Date getDataAnnullamento() {
		return dataAnnullamento;
	}

	/**
	 * @param dataAnnullamento the dataAnnullamento to set
	 */
	public void setDataAnnullamento(Date dataAnnullamento) {
		this.dataAnnullamento = dataAnnullamento;
	}


	public String getProvvedimentoSettore() {
		return provvedimentoSettore;
	}

	public void setProvvedimentoSettore(String provvedimentoSettore) {
		this.provvedimentoSettore = provvedimentoSettore;
	}

	public String getProvvedimentoTipo() {
		return provvedimentoTipo;
	}

	public void setProvvedimentoTipo(String provvedimentoTipo) {
		this.provvedimentoTipo = provvedimentoTipo;
	}

	public Boolean getHasOds() {
		return hasOds;
	}

	public void setHasOds(Boolean hasOds) {
		this.hasOds = hasOds;
	}

	public ScaricoMepaTestata getScaricoMepaTestata() {
		return scaricoMepaTestata;
	}

	public void setScaricoMepaTestata(ScaricoMepaTestata scaricoMepaTestata) {
		this.scaricoMepaTestata = scaricoMepaTestata;
	}

	/**
	 * @return the cig
	 */
	public String getCig() {
		return cig;
	}

	/**
	 * @param cig the cig to set
	 */
	public void setCig(String cig) {
		this.cig = cig;
	}

	/**
	 * @return the motiviEsclusioneCig
	 */
	public MotiviEsclusioneCig getMotiviEsclusioneCig() {
		return motiviEsclusioneCig;
	}

	/**
	 * @param motiviEsclusioneCig the motiviEsclusioneCig to set
	 */
	public void setMotiviEsclusioneCig(MotiviEsclusioneCig motiviEsclusioneCig) {
		this.motiviEsclusioneCig = motiviEsclusioneCig;
	}

	public List<TestataRda> getRdas() {
		return rdas;
	}

	public void setRdas(List<TestataRda> rdas) {
		this.rdas = rdas;
	}

	/**
	 * @return the documentiOrdines
	 */
	public List<DocumentiOrdine> getDocumentiOrdines() {
		return documentiOrdines;
	}

	/**
	 * @param documentiOrdines the documentiOrdines to set
	 */
	public void setDocumentiOrdines(List<DocumentiOrdine> documentiOrdines) {
		this.documentiOrdines = documentiOrdines;
	}

	/**
	 * @return the derivato
	 */
	public Boolean getDerivato() {
		return derivato;
	}

	/**
	 * @param derivato the derivato to set
	 */
	public void setDerivato(Boolean derivato) {
		this.derivato = derivato;
	}

	/**
	 * @return the annoRda
	 */
	public Integer getAnnoRda() {
		return annoRda;
	}

	/**
	 * @param annoRda the annoRda to set
	 */
	public void setAnnoRda(Integer annoRda) {
		this.annoRda = annoRda;
	}

	/**
	 * @return the numeroRda
	 */
	public Integer getNumeroRda() {
		return numeroRda;
	}

	/**
	 * @param numeroRda the numeroRda to set
	 */
	public void setNumeroRda(Integer numeroRda) {
		this.numeroRda = numeroRda;
	}

	/**
	 * @return the protocollato
	 */
	public Boolean getProtocollato() {
		return protocollato;
	}

	/**
	 * @param protocollato the protocollato to set
	 */
	public void setProtocollato(Boolean protocollato) {
		this.protocollato = protocollato;
	}

	/**
	 * @return the annoProtocollo
	 */
	public Integer getAnnoProtocollo() {
		return annoProtocollo;
	}

	/**
	 * @param annoProtocollo the annoProtocollo to set
	 */
	public void setAnnoProtocollo(Integer annoProtocollo) {
		this.annoProtocollo = annoProtocollo;
	}

	/**
	 * @return the numeroProtocollo
	 */
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	/**
	 * @param numeroProtocollo the numeroProtocollo to set
	 */
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	/**
	 * @return the aoo
	 */
	public String getAoo() {
		return aoo;
	}

	/**
	 * @param aoo the aoo to set
	 */
	public void setAoo(String aoo) {
		this.aoo = aoo;
	}






}
