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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.MetadatiFunzione;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * The Class Intervento.
 */
public class Intervento extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cui. */
	private String cui;
	/** The anno avvio. */
	private Integer annoAvvio;
	/** The cup. */
	private String cup;
	/** The lotto funzionale. */
	private Boolean lottoFunzionale;
	/** The durata mesi. */
	private Integer durataMesi;
	/** The nuovo affidamento. */
	private Boolean nuovoAffidamento;
	/** The descrizione acquisto. */
	private String descrizioneAcquisto;
	/** The ausa. */
	private Ausa ausa;
	/** The utente rup. */
	private Utente utenteRup;
	/** The intervento ricompreso. */
	private String ricompresoCui;
	/** The intervento ricompreso. */
	private Intervento interventoCopia;
	/** The settore interventi. */
	private SettoreInterventi settoreInterventi;
	/** The cpv. */
	private Cpv cpv;
	/** The programma. */
	private Programma programma;
	/** The nuts. */
	private Nuts nuts;
	/** The tipo priorita. */
	private Priorita priorita;
	/** The modalita affidamento. */
	private ModalitaAffidamento modalitaAffidamento;
	/** The stato. */
	private Stato stato;
	/** The acquistoVariato. */
	private AcquistoVariato acquistoVariato;
	/** The ricompreso. */
	private RicompresoTipo ricompresoTipo;
	/**	interventoCopiaTipo */
	private String interventoCopiaTipo;
	/**	interventoImportiCopiaTipo */
	private String interventoImportiCopiaTipo;

	//presente solo su model
	private String motivazioneRifiutoRagioneria;

	private String motivazioneRiportaInBozza;

	private String motivazioneRifiuto;

	private Boolean esenteCup;

	private Boolean flagCuiNonGenerato;

	private String motivazioneNonRiproposto;

	private Settore settore;

	/** The lotto funzionale. */
	private Boolean avviato;

	/** The elementi Dipendenti. */
	private Boolean elementiDipendenti;

	/** The listInterventoImporti. */
	private List<InterventoImporti> listInterventoImporti = new ArrayList<>();

	/** The listCpv. */
	private List<Cpv> listCpv = new ArrayList<>();

	/** The listInterventoAltriDati. */
	private List<InterventoAltriDati> listInterventoAltriDati = new ArrayList<>();

	private Integer risorsaIdCapitalePrivato = null;

	/** The listInterventoAltriDati. */
	private List<MetadatiFunzione> listMetadatiFunzione = new ArrayList<>();

	private Date dataVisto;

	private Utente utenteVisto;

	private Date dataValidazione;

	private Utente utenteValidazione;

	private Date dataRifiuto;

	private Utente utenteRifiuto;

	private String selNonRip;

	private Boolean capofila;

	private Date dataVistoRagioneria;

	private Boolean versioneDefinitiva;

	private Boolean vistoRagioneria;

	private List<StatiIntervento> statiInterventos;

	private List<StoricoInterventoRup> storicoInterventoRups;

	private Intervento interventoCapofila;

	private TipoProceduraPba tipoProceduraPba;

	private Utente utenteVistoRagioneria;

	private String versioneDefinitivaStr;

	private String vistoRagioneriaStr;

	private String statoXStorico;

	private Date dataAvviato;

	private Utente utenteAvviato;

	private List<InterventoCig> interventoCigs;

	private String esenteIva;
	private Boolean presenteInAltroProgramma;

	private Boolean presenteInAltroProgrammaFuturo;

	private String descrizioneUltimoStato;

	public String getEsenteIva() {
		return esenteIva;
	}

	public void setEsenteIva(String esenteIva) {
		this.esenteIva = esenteIva;
	}

	/** Default constructor */
	public Intervento() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Intervento(UUID id) {
		super(id);
	}

	/**
	 * Gets the cui.
	 * @return the cui
	 */
	public String getCui() {
		return cui;
	}

	/**
	 * Sets the cui.
	 * @param cui the new cui
	 */
	public void setCui(String cui) {
		this.cui = cui;
	}

	/**
	 * Gets the anno avvio.
	 * @return the anno avvio
	 */
	public Integer getAnnoAvvio() {
		return annoAvvio;
	}

	/**
	 * Sets the anno avvio.
	 * @param annoAvvio the new anno avvio
	 */
	public void setAnnoAvvio(Integer annoAvvio) {
		this.annoAvvio = annoAvvio;
	}

	/**
	 * Gets the cup.
	 * @return the cup
	 */
	public String getCup() {
		return cup;
	}

	/**
	 * Sets the cup.
	 * @param cup the new cup
	 */
	public void setCup(String cup) {
		this.cup = cup;
	}

	/**
	 * Checks if is lotto funzionale.
	 * @return the boolean
	 */
	public Boolean getLottoFunzionale() {
		return lottoFunzionale;
	}

	/**
	 * Sets the lotto funzionale.
	 * @param lottoFunzionale the new lotto funzionale
	 */
	public void setLottoFunzionale(Boolean lottoFunzionale) {
		this.lottoFunzionale = lottoFunzionale;
	}

	/**
	 * Gets the durata mesi.
	 * @return the durata mesi
	 */
	public Integer getDurataMesi() {
		return durataMesi;
	}

	/**
	 * Sets the durata mesi.
	 * @param durataMesi the new durata mesi
	 */
	public void setDurataMesi(Integer durataMesi) {
		this.durataMesi = durataMesi;
	}

	/**
	 * Checks if is nuovo affidamento.
	 * @return the boolean
	 */
	public Boolean getNuovoAffidamento() {
		return nuovoAffidamento;
	}

	/**
	 * Sets the nuovo affidamento.
	 * @param nuovoAffidamento the new nuovo affidamento
	 */
	public void setNuovoAffidamento(Boolean nuovoAffidamento) {
		this.nuovoAffidamento = nuovoAffidamento;
	}

	/**
	 * @return the descrizioneAcquisto
	 */
	public String getDescrizioneAcquisto() {
		return descrizioneAcquisto;
	}

	/**
	 * @param descrizioneAcquisto the descrizioneAcquisto to set
	 */
	public void setDescrizioneAcquisto(String descrizioneAcquisto) {
		this.descrizioneAcquisto = descrizioneAcquisto;
	}

	/**
	 * @return the utenteRup
	 */
	public Utente getUtenteRup() {
		return utenteRup;
	}

	/**
	 * @param utenteRup the responsabileUnicoProcedimento to set
	 */
	public void setUtenteRup(Utente utenteRup) {
		this.utenteRup = utenteRup;
	}

	/*
	public Intervento getInterventoRicompreso() {
		return interventoRicompreso;
	}

	public void setInterventoRicompreso(Intervento interventoRicompreso) {
		this.interventoRicompreso = interventoRicompreso;
	}
	*/



	/**
	 * Gets the settore interventi.
	 * @return the settore interventi
	 */
	public SettoreInterventi getSettoreInterventi() {
		return settoreInterventi;
	}

	/**
	 * @return the ricompresoCui
	 */
	public String getRicompresoCui() {
		return ricompresoCui;
	}

	/**
	 * @param ricompresoCui the ricompresoCui to set
	 */
	public void setRicompresoCui(String ricompresoCui) {
		this.ricompresoCui = ricompresoCui;
	}

	/**
	 * Sets the settore interventi.
	 * @param settoreInterventi the new settore interventi
	 */
	public void setSettoreInterventi(SettoreInterventi settoreInterventi) {
		this.settoreInterventi = settoreInterventi;
	}

	/**
	 * Gets the cpv.
	 * @return the cpv
	 */
	public Cpv getCpv() {
		return cpv;
	}

	/**
	 * Sets the cpv.
	 * @param cpv the new cpv
	 */
	public void setCpv(Cpv cpv) {
		this.cpv = cpv;
	}

	/**
	 * Gets the programma.
	 * @return the programma
	 */
	public Programma getProgramma() {
		return programma;
	}

	/**
	 * Sets the programma.
	 * @param programma the new programma
	 */
	public void setProgramma(Programma programma) {
		this.programma = programma;
	}

	/**
	 * Gets the nuts.
	 * @return the nuts
	 */
	public Nuts getNuts() {
		return nuts;
	}

	/**
	 * Sets the nuts.
	 * @param nuts the new nuts
	 */
	public void setNuts(Nuts nuts) {
		this.nuts = nuts;
	}

	/**
	 * Gets the priorita.
	 * @return the priorita
	 */
	public Priorita getPriorita() {
		return priorita;
	}

	/**
	 * Sets the priorita.
	 * @param priorita the new priorita
	 */
	public void setPriorita(Priorita priorita) {
		this.priorita = priorita;
	}

	/**
	 * Gets the modalita affidamento.
	 * @return the modalita affidamento
	 */
	public ModalitaAffidamento getModalitaAffidamento() {
		return modalitaAffidamento;
	}

	/**
	 * Sets the modalita affidamento.
	 * @param modalitaAffidamento the new modalita affidamento
	 */
	public void setModalitaAffidamento(ModalitaAffidamento modalitaAffidamento) {
		this.modalitaAffidamento = modalitaAffidamento;
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
	 * @return the listInterventoImporti
	 */
	public List<InterventoImporti> getListInterventoImporti() {
		return listInterventoImporti;
	}

	/**
	 * @param listInterventoImporti the listInterventoImporti to set
	 */
	public void setListInterventoImporti(List<InterventoImporti> listInterventoImporti) {
		this.listInterventoImporti = listInterventoImporti != null ? listInterventoImporti : new ArrayList<>();
	}

	/**
	 * @return the listCpv
	 */
	public List<Cpv> getListCpv() {
		return listCpv;
	}

	/**
	 * @param listCpv the listCpv to set
	 */
	public void setListCpv(List<Cpv> listCpv) {
		this.listCpv = listCpv != null ? listCpv : new ArrayList<>();
	}

	/**
	 * @return the listInterventoAltriDati
	 */
	public List<InterventoAltriDati> getListInterventoAltriDati() {
		return listInterventoAltriDati;
	}

	/**
	 * @param listInterventoImporti the listInterventoImporti to set
	 */
	public void setListInterventoAltriDati(List<InterventoAltriDati> listInterventoAltriDati) {
		this.listInterventoAltriDati = listInterventoAltriDati != null ? listInterventoAltriDati : new ArrayList<>();
	}

	/**
	 * @return the ausa
	 */
	public Ausa getAusa() {
		return ausa;
	}

	/**
	 * @param ausa the ausa to set
	 */
	public void setAusa(Ausa ausa) {
		this.ausa = ausa;
	}

	/**
	 * @return the acquistoVariato
	 */
	public AcquistoVariato getAcquistoVariato() {
		return acquistoVariato;
	}

	/**
	 * @param acquistoVariato the acquistoVariato to set
	 */
	public void setAcquistoVariato(AcquistoVariato acquistoVariato) {
		this.acquistoVariato = acquistoVariato;
	}


	/**
	 * @return the ricompresoTipo
	 */
	public RicompresoTipo getRicompresoTipo() {
		return ricompresoTipo;
	}

	/**
	 * @param ricompresoTipo the ricompresoTipo to set
	 */
	public void setRicompresoTipo(RicompresoTipo ricompresoTipo) {
		this.ricompresoTipo = ricompresoTipo;
	}


	/**
	 * @return the interventoCopia
	 */
	public Intervento getInterventoCopia() {
		return interventoCopia;
	}

	/**
	 * @param interventoCopia the interventoCopia to set
	 */
	public void setInterventoCopia(Intervento interventoCopia) {
		this.interventoCopia = interventoCopia;
	}

	/**
	 * @return the interventoCopiaTipo
	 */
	public String getInterventoCopiaTipo() {
		return interventoCopiaTipo;
	}

	/**
	 * @param interventoCopiaTipo the interventoCopiaTipo to set
	 */
	public void setInterventoCopiaTipo(String interventoCopiaTipo) {
		this.interventoCopiaTipo = interventoCopiaTipo;
	}

	/**
	 * @return the flagCuiNonGenerato
	 */
	public Boolean getFlagCuiNonGenerato() {
		return flagCuiNonGenerato;
	}

	/**
	 * @param flagCuiNonGenerato the flagCuiNonGenerato to set
	 */
	public void setFlagCuiNonGenerato(Boolean flagCuiNonGenerato) {
		this.flagCuiNonGenerato = flagCuiNonGenerato;
	}

	/**
	 * @return the motivazioneNonRiproposto
	 */
	public String getMotivazioneNonRiproposto() {
		return motivazioneNonRiproposto;
	}

	/**
	 * @param motivazioneNonRiproposto the motivazioneNonRiproposto to set
	 */
	public void setMotivazioneNonRiproposto(String motivazioneNonRiproposto) {
		this.motivazioneNonRiproposto = motivazioneNonRiproposto;
	}



	/**
	 * @return the motivazioneRiportaInBozza
	 */
	public String getMotivazioneRiportaInBozza() {
		return motivazioneRiportaInBozza;
	}

	/**
	 * @param motivazioneRiportaInBozza the motivazioneRiportaInBozza to set
	 */
	public void setMotivazioneRiportaInBozza(String motivazioneRiportaInBozza) {
		this.motivazioneRiportaInBozza = motivazioneRiportaInBozza;
	}

	/**
	 * @return the motivazioneRifiuto
	 */
	public String getMotivazioneRifiuto() {
		return motivazioneRifiuto;
	}

	/**
	 * @param motivazioneRifiuto the motivazioneRifiuto to set
	 */
	public void setMotivazioneRifiuto(String motivazioneRifiuto) {
		this.motivazioneRifiuto = motivazioneRifiuto;
	}

	/**
	 * @return the interventoImportiCopiaTipo
	 */
	public String getInterventoImportiCopiaTipo() {
		return interventoImportiCopiaTipo;
	}

	/**
	 * @param interventoImportiCopiaTipo the interventoImportiCopiaTipo to set
	 */
	public void setInterventoImportiCopiaTipo(String interventoImportiCopiaTipo) {
		this.interventoImportiCopiaTipo = interventoImportiCopiaTipo;
	}

	/**
	 * @return the risorsaIdCapitalePrivato
	 */
	public Integer getRisorsaIdCapitalePrivato() {
		return risorsaIdCapitalePrivato;
	}

	/**
	 * @param risorsaIdCapitalePrivato the risorsaIdCapitalePrivato to set
	 */
	public void setRisorsaIdCapitalePrivato(Integer risorsaIdCapitalePrivato) {
		this.risorsaIdCapitalePrivato = risorsaIdCapitalePrivato;
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
	 * @return the esenteCup
	 */
	public Boolean getEsenteCup() {
		return esenteCup;
	}

	/**
	 * @param esenteCup the esenteCup to set
	 */
	public void setEsenteCup(Boolean esenteCup) {
		this.esenteCup = esenteCup;
	}


	/**
	 * @return the listMetadatiFunzione
	 */
	public List<MetadatiFunzione> getListMetadatiFunzione() {
		return listMetadatiFunzione;
	}

	/**
	 * @param listMetadatiFunzione the listMetadatiFunzione to set
	 */
	public void setListMetadatiFunzione(List<MetadatiFunzione> listMetadatiFunzione) {
		this.listMetadatiFunzione = listMetadatiFunzione;
	}


	/**
	 * @return the dataVisto
	 */
	public Date getDataVisto() {
		return dataVisto;
	}

	/**
	 * @param dataVisto the dataVisto to set
	 */
	public void setDataVisto(Date dataVisto) {
		this.dataVisto = dataVisto;
	}

	/**
	 * @return the utenteVisto
	 */
	public Utente getUtenteVisto() {
		return utenteVisto;
	}

	/**
	 * @param utenteVisto the utenteVisto to set
	 */
	public void setUtenteVisto(Utente utenteVisto) {
		this.utenteVisto = utenteVisto;
	}

	/**
	 * @return the dataValidazione
	 */
	public Date getDataValidazione() {
		return dataValidazione;
	}

	/**
	 * @param dataValidazione the dataValidazione to set
	 */
	public void setDataValidazione(Date dataValidazione) {
		this.dataValidazione = dataValidazione;
	}

	/**
	 * @return the utenteValidazione
	 */
	public Utente getUtenteValidazione() {
		return utenteValidazione;
	}

	/**
	 * @param utenteValidazione the utenteValidazione to set
	 */
	public void setUtenteValidazione(Utente utenteValidazione) {
		this.utenteValidazione = utenteValidazione;
	}

	/**
	 * @return the dataRifiuto
	 */
	public Date getDataRifiuto() {
		return dataRifiuto;
	}

	/**
	 * @param dataRifiuto the dataRifiuto to set
	 */
	public void setDataRifiuto(Date dataRifiuto) {
		this.dataRifiuto = dataRifiuto;
	}

	/**
	 * @return the utenteRifiuto
	 */
	public Utente getUtenteRifiuto() {
		return utenteRifiuto;
	}

	/**
	 * @param utenteRifiuto the utenteRifiuto to set
	 */
	public void setUtenteRifiuto(Utente utenteRifiuto) {
		this.utenteRifiuto = utenteRifiuto;
	}

	/**
	 * @return the capofila
	 */
	public Boolean getCapofila() {
		return capofila;
	}

	/**
	 * @param capofila the capofila to set
	 */
	public void setCapofila(Boolean capofila) {
		this.capofila = capofila;
	}

	/**
	 * @return the dataVistoRagioneria
	 */
	public Date getDataVistoRagioneria() {
		return dataVistoRagioneria;
	}

	/**
	 * @param dataVistoRagioneria the dataVistoRagioneria to set
	 */
	public void setDataVistoRagioneria(Date dataVistoRagioneria) {
		this.dataVistoRagioneria = dataVistoRagioneria;
	}

	/**
	 * @return the versioneDefinitiva
	 */
	public Boolean getVersioneDefinitiva() {
		return versioneDefinitiva;
	}

	/**
	 * @param versioneDefinitiva the versioneDefinitiva to set
	 */
	public void setVersioneDefinitiva(Boolean versioneDefinitiva) {
		this.versioneDefinitiva = versioneDefinitiva;
	}

	/**
	 * @return the vistoRagioneria
	 */
	public Boolean getVistoRagioneria() {
		return vistoRagioneria;
	}

	/**
	 * @param vistoRagioneria the vistoRagioneria to set
	 */
	public void setVistoRagioneria(Boolean vistoRagioneria) {
		this.vistoRagioneria = vistoRagioneria;
	}

	/**
	 * @return the statiInterventos
	 */
	public List<StatiIntervento> getStatiInterventos() {
		return statiInterventos;
	}

	/**
	 * @param statiInterventos the statiInterventos to set
	 */
	public void setStatiInterventos(List<StatiIntervento> statiInterventos) {
		this.statiInterventos = statiInterventos;
	}

	/**
	 * @return the storicoInterventoRups
	 */
	public List<StoricoInterventoRup> getStoricoInterventoRups() {
		return storicoInterventoRups;
	}

	/**
	 * @param storicoInterventoRups the storicoInterventoRups to set
	 */
	public void setStoricoInterventoRups(List<StoricoInterventoRup> storicoInterventoRups) {
		this.storicoInterventoRups = storicoInterventoRups;
	}



	/**
	 * @return the utenteVistoRagioneria
	 */
	public Utente getUtenteVistoRagioneria() {
		return utenteVistoRagioneria;
	}

	/**
	 * @param utenteVistoRagioneria the utenteVistoRagioneria to set
	 */
	public void setUtenteVistoRagioneria(Utente utenteVistoRagioneria) {
		this.utenteVistoRagioneria = utenteVistoRagioneria;
	}

	/**
	 * @return the selNonRip
	 */
	public String getSelNonRip() {
		return selNonRip;
	}

	/**
	 * @param selNonRip the selNonRip to set
	 */
	public void setSelNonRip(String selNonRip) {
		this.selNonRip = selNonRip;
	}

	/**
	 * @return the interventoCapofila
	 */
	public Intervento getInterventoCapofila() {
		return interventoCapofila;
	}

	/**
	 * @param interventoCapofila the interventoCapofila to set
	 */
	public void setInterventoCapofila(Intervento interventoCapofila) {
		this.interventoCapofila = interventoCapofila;
	}


	/**
	 * @return the vistoRagioneriaStr
	 */
	public String getVistoRagioneriaStr() {
		return vistoRagioneriaStr;
	}

	/**
	 * @param vistoRagioneriaStr the vistoRagioneriaStr to set
	 */
	public void setVistoRagioneriaStr(String vistoRagioneriaStr) {
		this.vistoRagioneriaStr = vistoRagioneriaStr;
	}



	/**
	 * @return the statoXStorico
	 */
	public String getStatoXStorico() {
		return statoXStorico;
	}

	/**
	 * @param statoXStorico the statoXStorico to set
	 */
	public void setStatoXStorico(String statoXStorico) {
		this.statoXStorico = statoXStorico;
	}

	/**
	 * @return the avviato
	 */
	public Boolean getAvviato() {
		return avviato;
	}

	/**
	 * @param avviato the avviato to set
	 */
	public void setAvviato(Boolean avviato) {
		this.avviato = avviato;
	}

	/**
	 * @return the versioneDefinitivaStr
	 */
	public String getVersioneDefinitivaStr() {
		return versioneDefinitivaStr;
	}

	/**
	 * @param versioneDefinitivaStr the versioneDefinitivaStr to set
	 */
	public void setVersioneDefinitivaStr(String versioneDefinitivaStr) {
		this.versioneDefinitivaStr = versioneDefinitivaStr;
	}


	/**
	 * @return the motivazioneRifiutoRagioneria
	 */
	public String getMotivazioneRifiutoRagioneria() {
		return motivazioneRifiutoRagioneria;
	}

	/**
	 * @param motivazioneRifiutoRagioneria the motivazioneRifiutoRagioneria to set
	 */
	public void setMotivazioneRifiutoRagioneria(String motivazioneRifiutoRagioneria) {
		this.motivazioneRifiutoRagioneria = motivazioneRifiutoRagioneria;
	}

	/**
	 * @return the elementiDipendenti
	 */
	public Boolean getElementiDipendenti() {
		return elementiDipendenti;
	}

	/**
	 * @param elementiDipendenti the elementiDipendenti to set
	 */
	public void setElementiDipendenti(Boolean elementiDipendenti) {
		this.elementiDipendenti = elementiDipendenti;
	}

	/**
	 * @return the dataAvviato
	 */
	public Date getDataAvviato() {
		return dataAvviato;
	}

	/**
	 * @param dataAvviato the dataAvviato to set
	 */
	public void setDataAvviato(Date dataAvviato) {
		this.dataAvviato = dataAvviato;
	}

	/**
	 * @return the utenteAvviato
	 */
	public Utente getUtenteAvviato() {
		return utenteAvviato;
	}

	/**
	 * @param utenteAvviato the utenteAvviato to set
	 */
	public void setUtenteAvviato(Utente utenteAvviato) {
		this.utenteAvviato = utenteAvviato;
	}


	/**
	 * @return the tipoProceduraPba
	 */
	public TipoProceduraPba getTipoProceduraPba() {
		return tipoProceduraPba;
	}

	/**
	 * @param tipoProceduraPba the tipoProceduraPba to set
	 */
	public void setTipoProceduraPba(TipoProceduraPba tipoProceduraPba) {
		this.tipoProceduraPba = tipoProceduraPba;
	}

	/**
	 * @return the interventoCigs
	 */
	public List<InterventoCig> getInterventoCigs() {
		return interventoCigs;
	}

	/**
	 * @param interventoCigs the interventoCigs to set
	 */
	public void setInterventoCigs(List<InterventoCig> interventoCigs) {
		this.interventoCigs = interventoCigs;
	}

	/**
	 * @return the trasmesso
	 */
	public Boolean getPresenteInAltroProgramma() {
		return presenteInAltroProgramma;
	}

	/**
	 * @param presenteInAltroProgramma the trasmesso to set
	 */
	public void setPresenteInAltroProgramma(Boolean presenteInAltroProgramma) {
		this.presenteInAltroProgramma = presenteInAltroProgramma;
	}


	/**
	 * @return the presenteInAltroProgrammaFuturo
	 */
	public Boolean getPresenteInAltroProgrammaFuturo() {
		return presenteInAltroProgrammaFuturo;
	}

	/**
	 * @param presenteInAltroProgrammaFuturo the presenteInAltroProgrammaFuturo to set
	 */
	public void setPresenteInAltroProgrammaFuturo(Boolean presenteInAltroProgrammaFuturo) {
		this.presenteInAltroProgrammaFuturo = presenteInAltroProgrammaFuturo;
	}

	/**
	 * @return the descrizioneUltimoStato
	 */
	public String getDescrizioneUltimoStato() {
		return descrizioneUltimoStato;
	}

	/**
	 * @param descrizioneUltimoStato the descrizioneUltimoStato to set
	 */
	public void setDescrizioneUltimoStato(String descrizioneUltimoStato) {
		this.descrizioneUltimoStato = descrizioneUltimoStato;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Intervento [cui=").append(cui)
			.append(", annoAvvio=").append(annoAvvio)
			.append(", cup=").append(cup)
			.append(", lottoFunzionale=").append(lottoFunzionale)
			.append(", durataMesi=").append(durataMesi)
			.append(", nuovoAffidamento=").append(nuovoAffidamento)
			.append(", descrizioneAcquisto=").append(descrizioneAcquisto)
			.append(", ausa=").append(ausa)
			.append(", utenteRup=").append(utenteRup)
			.append(", ricompresoCui=").append(ricompresoCui)
			.append(", settoreInterventi=").append(settoreInterventi)
			.append(", cpv=").append(cpv)
			.append(", programma=").append(programma)
			.append(", nuts=").append(nuts)
			.append(", priorita=").append(priorita)
			.append(", modalitaAffidamento=").append(modalitaAffidamento)
			.append(", stato=").append(stato)
			.append(", acquistoVariato=").append(acquistoVariato)
			.append(", ricompresoTipo=").append(ricompresoTipo)
			.append(", listInterventoImporti=").append(listInterventoImporti)
			.append(", interventoCopiaTipo=").append(interventoCopiaTipo)
			.append(", interventoImportiCopiaTipo=").append(interventoImportiCopiaTipo)
			.append(", flagCuiNonGenerato=").append(flagCuiNonGenerato)
			.append(", motivazioneNonRiproposto=").append(motivazioneNonRiproposto)
			.append(", risorsaIdCapitalePrivato=").append(risorsaIdCapitalePrivato)
			.append(innerToString())
			.append("]")
			.toString();
	}

}
