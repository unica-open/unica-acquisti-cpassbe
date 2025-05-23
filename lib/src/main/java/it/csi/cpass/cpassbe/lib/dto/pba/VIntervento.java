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
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.MotiviEsclusioneCig;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * The Class Intervento.
 */
public class VIntervento  implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private UUID interventoId;
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
	private Integer ausaId;
	private String ausaCodice;
	private String ausaDescrizione;

	/** The utente rup. */
	private UUID utenteRupId;
	private String utenteRupCF;
	private String utenteRupCognome;
	private String utenteRupNome;


	/** The intervento ricompreso. */
	private String ricompresoCui;
	/** The intervento ricompreso. */
	private VIntervento interventoCopia;
	/** The settore interventi. */
	//private SettoreInterventi settoreInterventi;
	private Integer settoreInterventiId;
	/** The descrizione. */
	private String settoreInterventiDescrizione;
	/** The descrizione. */
	private String settoreInterventiCodice;
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

	private Integer risorsaIdCapitalePrivato = null;

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

	private String versioneDefinitivaStr;

	private String vistoRagioneriaStr;

	private Date dataAvviato;

	private Utente utenteAvviato;

	private String esenteIva;

	private Boolean presenteInAltroProgramma;

	private Boolean presenteInAltroProgrammaFuturo;

	private String descrizioneUltimoStato;

	private Integer cpvId;
	private String cpvCodice;
	private String cpvDescrizione;
	/** The programma. */
	private UUID programmaId;
	private Integer nutsId;
	private String nutsCodice;
	private String nutsDescrizione;

	private Integer prioritaId;
	private String prioritaCodice;
	private String prioritaDescrizione;

	private Integer modalitaAffidamentoId;
	private String modalitaAffidamentoCodice;
	private String modalitaAffidamentoDescrizione;

	private Integer statoAffidamentoId;
	private String statoAffidamentoCodice;
	private String statoAffidamentoDescrizione;

	private Integer acquistoVariatoId;
	private String acquistoVariatoCodice;
	private String acquistoVariatoDescrizione;

	private Integer ricompresoTipoId;
	private String ricompresoTipoCodice;
	private String ricompresoTipoDescrizione;
	private Boolean ricompresoTipoConteggioImporti;

	/** The listInterventoAltriDati. */
	//private List<InterventoAltriDati> listInterventoAltriDati = new ArrayList<>();altriDati
	private UUID altriDatiId;
	private String altriDatinote;
	private String altriDaticodiceInterno;
	private BigDecimal altriDatispeseSostenute;
	private BigDecimal altriDatiivaPrimoAnno;
	private BigDecimal altriDatiivaSecondoAnno;
	private BigDecimal altriDatiivaTerzoAnno;
	private BigDecimal altriDatiivaAnniSuccessivi;
	private BigDecimal altriDatiimportoIvaMatRic;
	private BigDecimal altriDatiimportoIvaVerdi;
	private BigDecimal altriDatiimportoNettoIvaMatRic;
	private BigDecimal altriDatiimportoNettoIvaVerdi;
	private BigDecimal altriDatiimportoTotMatRic;
	private BigDecimal altriDatiimportoTotVerdi;
	private String altriDatinormativaRiferimento;
	private String altriDatioggettoMatRic;
	private String altriDatioggettoverdi;
	private Intervento altriDatiintervento;
	private Cpv altriDaticpvMatRic;
	private Cpv altriDaticpvVerdi;
	private TipoAcquisto altriDatitipoAcquistoMatRic;
	private TipoAcquisto altriDatitipoAcquistoVerdi;
	private Boolean altriDatifondiPnrr;
	private MotiviEsclusioneCig altriDatimotiviEsclusioneCig;
	private Integer statoId;
	private String statoCodice;
	private String statoDescrizione;
	private UUID interventoCapofilaId;



	/** Default constructor */
	public VIntervento() {}


	public String getEsenteIva() {
		return esenteIva;
	}

	public void setEsenteIva(String esenteIva) {
		this.esenteIva = esenteIva;
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



	/*
	public Intervento getInterventoRicompreso() {
		return interventoRicompreso;
	}

	public void setInterventoRicompreso(Intervento interventoRicompreso) {
		this.interventoRicompreso = interventoRicompreso;
	}
	*/



	/**
	 * @return the utenteRupId
	 */
	public UUID getUtenteRupId() {
		return utenteRupId;
	}


	/**
	 * @return the ricompresoTipoConteggioImporti
	 */
	public Boolean getRicompresoTipoConteggioImporti() {
		return ricompresoTipoConteggioImporti;
	}


	/**
	 * @param ricompresoTipoConteggioImporti the ricompresoTipoConteggioImporti to set
	 */
	public void setRicompresoTipoConteggioImporti(Boolean ricompresoTipoConteggioImporti) {
		this.ricompresoTipoConteggioImporti = ricompresoTipoConteggioImporti;
	}


	/**
	 * @param utenteRupId the utenteRupId to set
	 */
	public void setUtenteRupId(UUID utenteRupId) {
		this.utenteRupId = utenteRupId;
	}


	/**
	 * @return the utenteRupCF
	 */
	public String getUtenteRupCF() {
		return utenteRupCF;
	}


	/**
	 * @param utenteRupCF the utenteRupCF to set
	 */
	public void setUtenteRupCF(String utenteRupCF) {
		this.utenteRupCF = utenteRupCF;
	}


	/**
	 * @return the settoreInterventiId
	 */
	public Integer getSettoreInterventiId() {
		return settoreInterventiId;
	}


	/**
	 * @param settoreInterventiId the settoreInterventiId to set
	 */
	public void setSettoreInterventiId(Integer settoreInterventiId) {
		this.settoreInterventiId = settoreInterventiId;
	}


	/**
	 * @return the settoreInterventiDescrizione
	 */
	public String getSettoreInterventiDescrizione() {
		return settoreInterventiDescrizione;
	}


	/**
	 * @param settoreInterventiDescrizione the settoreInterventiDescrizione to set
	 */
	public void setSettoreInterventiDescrizione(String settoreInterventiDescrizione) {
		this.settoreInterventiDescrizione = settoreInterventiDescrizione;
	}


	/**
	 * @return the settoreInterventiCodice
	 */
	public String getSettoreInterventiCodice() {
		return settoreInterventiCodice;
	}


	/**
	 * @param settoreInterventiCodice the settoreInterventiCodice to set
	 */
	public void setSettoreInterventiCodice(String settoreInterventiCodice) {
		this.settoreInterventiCodice = settoreInterventiCodice;
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
	 * @return the ausaId
	 */
	public Integer getAusaId() {
		return ausaId;
	}


	/**
	 * @param ausaId the ausaId to set
	 */
	public void setAusaId(Integer ausaId) {
		this.ausaId = ausaId;
	}


	/**
	 * @return the ausaCodice
	 */
	public String getAusaCodice() {
		return ausaCodice;
	}


	/**
	 * @param ausaCodice the ausaCodice to set
	 */
	public void setAusaCodice(String ausaCodice) {
		this.ausaCodice = ausaCodice;
	}


	/**
	 * @return the ausaDescrizione
	 */
	public String getAusaDescrizione() {
		return ausaDescrizione;
	}


	/**
	 * @param ausaDescrizione the ausaDescrizione to set
	 */
	public void setAusaDescrizione(String ausaDescrizione) {
		this.ausaDescrizione = ausaDescrizione;
	}


	/**
	 * @return the interventoCopia
	 */
	public VIntervento getInterventoCopia() {
		return interventoCopia;
	}

	/**
	 * @param interventoCopia the interventoCopia to set
	 */
	public void setInterventoCopia(VIntervento interventoCopia) {
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

	/**
	 * @return the interventoId
	 */
	public UUID getInterventoId() {
		return interventoId;
	}


	/**
	 * @param interventoId the interventoId to set
	 */
	public void setInterventoId(UUID interventoId) {
		this.interventoId = interventoId;
	}


	/**
	 * @return the cpvId
	 */
	public Integer getCpvId() {
		return cpvId;
	}


	/**
	 * @param cpvId the cpvId to set
	 */
	public void setCpvId(Integer cpvId) {
		this.cpvId = cpvId;
	}


	/**
	 * @return the cpvcodice
	 */
	public String getCpvCodice() {
		return cpvCodice;
	}


	/**
	 * @param cpvcodice the cpvcodice to set
	 */
	public void setCpvCodice(String cpvCodice) {
		this.cpvCodice = cpvCodice;
	}


	/**
	 * @return the cpvDescrizione
	 */
	public String getCpvDescrizione() {
		return cpvDescrizione;
	}


	/**
	 * @param cpvDescrizione the cpvDescrizione to set
	 */
	public void setCpvDescrizione(String cpvDescrizione) {
		this.cpvDescrizione = cpvDescrizione;
	}


	/**
	 * @return the programmaId
	 */
	public UUID getProgrammaId() {
		return programmaId;
	}


	/**
	 * @param programmaId the programmaId to set
	 */
	public void setProgrammaId(UUID programmaId) {
		this.programmaId = programmaId;
	}


	/**
	 * @return the nutsId
	 */
	public Integer getNutsId() {
		return nutsId;
	}


	/**
	 * @param nutsId the nutsId to set
	 */
	public void setNutsId(Integer nutsId) {
		this.nutsId = nutsId;
	}


	/**
	 * @return the nutsCodice
	 */
	public String getNutsCodice() {
		return nutsCodice;
	}


	/**
	 * @param nutsCodice the nutsCodice to set
	 */
	public void setNutsCodice(String nutsCodice) {
		this.nutsCodice = nutsCodice;
	}


	/**
	 * @return the nutsDescrizione
	 */
	public String getNutsDescrizione() {
		return nutsDescrizione;
	}


	/**
	 * @param nutsDescrizione the nutsDescrizione to set
	 */
	public void setNutsDescrizione(String nutsDescrizione) {
		this.nutsDescrizione = nutsDescrizione;
	}


	/**
	 * @return the prioritaId
	 */
	public Integer getPrioritaId() {
		return prioritaId;
	}


	/**
	 * @param prioritaId the prioritaId to set
	 */
	public void setPrioritaId(Integer prioritaId) {
		this.prioritaId = prioritaId;
	}


	/**
	 * @return the prioritaCodice
	 */
	public String getPrioritaCodice() {
		return prioritaCodice;
	}


	/**
	 * @param prioritaCodice the prioritaCodice to set
	 */
	public void setPrioritaCodice(String prioritaCodice) {
		this.prioritaCodice = prioritaCodice;
	}


	/**
	 * @return the prioritaDescrizione
	 */
	public String getPrioritaDescrizione() {
		return prioritaDescrizione;
	}


	/**
	 * @param prioritaDescrizione the prioritaDescrizione to set
	 */
	public void setPrioritaDescrizione(String prioritaDescrizione) {
		this.prioritaDescrizione = prioritaDescrizione;
	}


	/**
	 * @return the modalitaAffidamentoId
	 */
	public Integer getModalitaAffidamentoId() {
		return modalitaAffidamentoId;
	}


	/**
	 * @param modalitaAffidamentoId the modalitaAffidamentoId to set
	 */
	public void setModalitaAffidamentoId(Integer modalitaAffidamentoId) {
		this.modalitaAffidamentoId = modalitaAffidamentoId;
	}


	/**
	 * @return the modalitaAffidamentoCodice
	 */
	public String getModalitaAffidamentoCodice() {
		return modalitaAffidamentoCodice;
	}


	/**
	 * @param modalitaAffidamentoCodice the modalitaAffidamentoCodice to set
	 */
	public void setModalitaAffidamentoCodice(String modalitaAffidamentoCodice) {
		this.modalitaAffidamentoCodice = modalitaAffidamentoCodice;
	}


	/**
	 * @return the modalitaAffidamentoDescrizione
	 */
	public String getModalitaAffidamentoDescrizione() {
		return modalitaAffidamentoDescrizione;
	}


	/**
	 * @param modalitaAffidamentoDescrizione the modalitaAffidamentoDescrizione to set
	 */
	public void setModalitaAffidamentoDescrizione(String modalitaAffidamentoDescrizione) {
		this.modalitaAffidamentoDescrizione = modalitaAffidamentoDescrizione;
	}


	/**
	 * @return the statoAffidamentoId
	 */
	public Integer getStatoAffidamentoId() {
		return statoAffidamentoId;
	}


	/**
	 * @param statoAffidamentoId the statoAffidamentoId to set
	 */
	public void setStatoAffidamentoId(Integer statoAffidamentoId) {
		this.statoAffidamentoId = statoAffidamentoId;
	}


	/**
	 * @return the statoAffidamentoCodice
	 */
	public String getStatoAffidamentoCodice() {
		return statoAffidamentoCodice;
	}


	/**
	 * @param statoAffidamentoCodice the statoAffidamentoCodice to set
	 */
	public void setStatoAffidamentoCodice(String statoAffidamentoCodice) {
		this.statoAffidamentoCodice = statoAffidamentoCodice;
	}


	/**
	 * @return the statoAffidamentoDescrizione
	 */
	public String getStatoAffidamentoDescrizione() {
		return statoAffidamentoDescrizione;
	}


	/**
	 * @param statoAffidamentoDescrizione the statoAffidamentoDescrizione to set
	 */
	public void setStatoAffidamentoDescrizione(String statoAffidamentoDescrizione) {
		this.statoAffidamentoDescrizione = statoAffidamentoDescrizione;
	}


	/**
	 * @return the acquistoVariatoId
	 */
	public Integer getAcquistoVariatoId() {
		return acquistoVariatoId;
	}


	/**
	 * @param acquistoVariatoId the acquistoVariatoId to set
	 */
	public void setAcquistoVariatoId(Integer acquistoVariatoId) {
		this.acquistoVariatoId = acquistoVariatoId;
	}


	/**
	 * @return the acquistoVariatoCodice
	 */
	public String getAcquistoVariatoCodice() {
		return acquistoVariatoCodice;
	}


	/**
	 * @param acquistoVariatoCodice the acquistoVariatoCodice to set
	 */
	public void setAcquistoVariatoCodice(String acquistoVariatoCodice) {
		this.acquistoVariatoCodice = acquistoVariatoCodice;
	}


	/**
	 * @return the acquistoVariatoDescrizione
	 */
	public String getAcquistoVariatoDescrizione() {
		return acquistoVariatoDescrizione;
	}


	/**
	 * @param acquistoVariatoDescrizione the acquistoVariatoDescrizione to set
	 */
	public void setAcquistoVariatoDescrizione(String acquistoVariatoDescrizione) {
		this.acquistoVariatoDescrizione = acquistoVariatoDescrizione;
	}


	/**
	 * @return the ricompresoTipoId
	 */
	public Integer getRicompresoTipoId() {
		return ricompresoTipoId;
	}


	/**
	 * @param ricompresoTipoId the ricompresoTipoId to set
	 */
	public void setRicompresoTipoId(Integer ricompresoTipoId) {
		this.ricompresoTipoId = ricompresoTipoId;
	}


	/**
	 * @return the ricompresoTipoCodice
	 */
	public String getRicompresoTipoCodice() {
		return ricompresoTipoCodice;
	}


	/**
	 * @param ricompresoTipoCodice the ricompresoTipoCodice to set
	 */
	public void setRicompresoTipoCodice(String ricompresoTipoCodice) {
		this.ricompresoTipoCodice = ricompresoTipoCodice;
	}


	/**
	 * @return the ricompresoTipoDescrizione
	 */
	public String getRicompresoTipoDescrizione() {
		return ricompresoTipoDescrizione;
	}


	/**
	 * @param ricompresoTipoDescrizione the ricompresoTipoDescrizione to set
	 */
	public void setRicompresoTipoDescrizione(String ricompresoTipoDescrizione) {
		this.ricompresoTipoDescrizione = ricompresoTipoDescrizione;
	}


	/**
	 * @return the altriDatiId
	 */
	public UUID getAltriDatiId() {
		return altriDatiId;
	}


	/**
	 * @param altriDatiId the altriDatiId to set
	 */
	public void setAltriDatiId(UUID altriDatiId) {
		this.altriDatiId = altriDatiId;
	}


	/**
	 * @return the altriDatinote
	 */
	public String getAltriDatinote() {
		return altriDatinote;
	}


	/**
	 * @param altriDatinote the altriDatinote to set
	 */
	public void setAltriDatinote(String altriDatinote) {
		this.altriDatinote = altriDatinote;
	}


	/**
	 * @return the altriDaticodiceInterno
	 */
	public String getAltriDaticodiceInterno() {
		return altriDaticodiceInterno;
	}


	/**
	 * @param altriDaticodiceInterno the altriDaticodiceInterno to set
	 */
	public void setAltriDaticodiceInterno(String altriDaticodiceInterno) {
		this.altriDaticodiceInterno = altriDaticodiceInterno;
	}


	/**
	 * @return the altriDatispeseSostenute
	 */
	public BigDecimal getAltriDatispeseSostenute() {
		return altriDatispeseSostenute;
	}


	/**
	 * @param altriDatispeseSostenute the altriDatispeseSostenute to set
	 */
	public void setAltriDatispeseSostenute(BigDecimal altriDatispeseSostenute) {
		this.altriDatispeseSostenute = altriDatispeseSostenute;
	}


	/**
	 * @return the altriDatiivaPrimoAnno
	 */
	public BigDecimal getAltriDatiivaPrimoAnno() {
		return altriDatiivaPrimoAnno;
	}


	/**
	 * @param altriDatiivaPrimoAnno the altriDatiivaPrimoAnno to set
	 */
	public void setAltriDatiivaPrimoAnno(BigDecimal altriDatiivaPrimoAnno) {
		this.altriDatiivaPrimoAnno = altriDatiivaPrimoAnno;
	}


	/**
	 * @return the altriDatiivaSecondoAnno
	 */
	public BigDecimal getAltriDatiivaSecondoAnno() {
		return altriDatiivaSecondoAnno;
	}


	/**
	 * @param altriDatiivaSecondoAnno the altriDatiivaSecondoAnno to set
	 */
	public void setAltriDatiivaSecondoAnno(BigDecimal altriDatiivaSecondoAnno) {
		this.altriDatiivaSecondoAnno = altriDatiivaSecondoAnno;
	}


	/**
	 * @return the altriDatiivaTerzoAnno
	 */
	public BigDecimal getAltriDatiivaTerzoAnno() {
		return altriDatiivaTerzoAnno;
	}


	/**
	 * @param altriDatiivaTerzoAnno the altriDatiivaTerzoAnno to set
	 */
	public void setAltriDatiivaTerzoAnno(BigDecimal altriDatiivaTerzoAnno) {
		this.altriDatiivaTerzoAnno = altriDatiivaTerzoAnno;
	}


	/**
	 * @return the altriDatiivaAnniSuccessivi
	 */
	public BigDecimal getAltriDatiivaAnniSuccessivi() {
		return altriDatiivaAnniSuccessivi;
	}


	/**
	 * @param altriDatiivaAnniSuccessivi the altriDatiivaAnniSuccessivi to set
	 */
	public void setAltriDatiivaAnniSuccessivi(BigDecimal altriDatiivaAnniSuccessivi) {
		this.altriDatiivaAnniSuccessivi = altriDatiivaAnniSuccessivi;
	}


	/**
	 * @return the altriDatiimportoIvaMatRic
	 */
	public BigDecimal getAltriDatiimportoIvaMatRic() {
		return altriDatiimportoIvaMatRic;
	}


	/**
	 * @param altriDatiimportoIvaMatRic the altriDatiimportoIvaMatRic to set
	 */
	public void setAltriDatiimportoIvaMatRic(BigDecimal altriDatiimportoIvaMatRic) {
		this.altriDatiimportoIvaMatRic = altriDatiimportoIvaMatRic;
	}


	/**
	 * @return the altriDatiimportoIvaVerdi
	 */
	public BigDecimal getAltriDatiimportoIvaVerdi() {
		return altriDatiimportoIvaVerdi;
	}


	/**
	 * @param altriDatiimportoIvaVerdi the altriDatiimportoIvaVerdi to set
	 */
	public void setAltriDatiimportoIvaVerdi(BigDecimal altriDatiimportoIvaVerdi) {
		this.altriDatiimportoIvaVerdi = altriDatiimportoIvaVerdi;
	}


	/**
	 * @return the altriDatiimportoNettoIvaMatRic
	 */
	public BigDecimal getAltriDatiimportoNettoIvaMatRic() {
		return altriDatiimportoNettoIvaMatRic;
	}


	/**
	 * @param altriDatiimportoNettoIvaMatRic the altriDatiimportoNettoIvaMatRic to set
	 */
	public void setAltriDatiimportoNettoIvaMatRic(BigDecimal altriDatiimportoNettoIvaMatRic) {
		this.altriDatiimportoNettoIvaMatRic = altriDatiimportoNettoIvaMatRic;
	}


	/**
	 * @return the altriDatiimportoNettoIvaVerdi
	 */
	public BigDecimal getAltriDatiimportoNettoIvaVerdi() {
		return altriDatiimportoNettoIvaVerdi;
	}


	/**
	 * @param altriDatiimportoNettoIvaVerdi the altriDatiimportoNettoIvaVerdi to set
	 */
	public void setAltriDatiimportoNettoIvaVerdi(BigDecimal altriDatiimportoNettoIvaVerdi) {
		this.altriDatiimportoNettoIvaVerdi = altriDatiimportoNettoIvaVerdi;
	}


	/**
	 * @return the altriDatiimportoTotMatRic
	 */
	public BigDecimal getAltriDatiimportoTotMatRic() {
		return altriDatiimportoTotMatRic;
	}


	/**
	 * @param altriDatiimportoTotMatRic the altriDatiimportoTotMatRic to set
	 */
	public void setAltriDatiimportoTotMatRic(BigDecimal altriDatiimportoTotMatRic) {
		this.altriDatiimportoTotMatRic = altriDatiimportoTotMatRic;
	}


	/**
	 * @return the altriDatiimportoTotVerdi
	 */
	public BigDecimal getAltriDatiimportoTotVerdi() {
		return altriDatiimportoTotVerdi;
	}


	/**
	 * @param altriDatiimportoTotVerdi the altriDatiimportoTotVerdi to set
	 */
	public void setAltriDatiimportoTotVerdi(BigDecimal altriDatiimportoTotVerdi) {
		this.altriDatiimportoTotVerdi = altriDatiimportoTotVerdi;
	}


	/**
	 * @return the altriDatinormativaRiferimento
	 */
	public String getAltriDatinormativaRiferimento() {
		return altriDatinormativaRiferimento;
	}


	/**
	 * @param altriDatinormativaRiferimento the altriDatinormativaRiferimento to set
	 */
	public void setAltriDatinormativaRiferimento(String altriDatinormativaRiferimento) {
		this.altriDatinormativaRiferimento = altriDatinormativaRiferimento;
	}


	/**
	 * @return the altriDatioggettoMatRic
	 */
	public String getAltriDatioggettoMatRic() {
		return altriDatioggettoMatRic;
	}


	/**
	 * @param altriDatioggettoMatRic the altriDatioggettoMatRic to set
	 */
	public void setAltriDatioggettoMatRic(String altriDatioggettoMatRic) {
		this.altriDatioggettoMatRic = altriDatioggettoMatRic;
	}


	/**
	 * @return the altriDatioggettoverdi
	 */
	public String getAltriDatioggettoverdi() {
		return altriDatioggettoverdi;
	}


	/**
	 * @param altriDatioggettoverdi the altriDatioggettoverdi to set
	 */
	public void setAltriDatioggettoverdi(String altriDatioggettoverdi) {
		this.altriDatioggettoverdi = altriDatioggettoverdi;
	}


	/**
	 * @return the altriDatiintervento
	 */
	public Intervento getAltriDatiintervento() {
		return altriDatiintervento;
	}


	/**
	 * @param altriDatiintervento the altriDatiintervento to set
	 */
	public void setAltriDatiintervento(Intervento altriDatiintervento) {
		this.altriDatiintervento = altriDatiintervento;
	}


	/**
	 * @return the altriDaticpvMatRic
	 */
	public Cpv getAltriDaticpvMatRic() {
		return altriDaticpvMatRic;
	}


	/**
	 * @param altriDaticpvMatRic the altriDaticpvMatRic to set
	 */
	public void setAltriDaticpvMatRic(Cpv altriDaticpvMatRic) {
		this.altriDaticpvMatRic = altriDaticpvMatRic;
	}


	/**
	 * @return the altriDaticpvVerdi
	 */
	public Cpv getAltriDaticpvVerdi() {
		return altriDaticpvVerdi;
	}


	/**
	 * @param altriDaticpvVerdi the altriDaticpvVerdi to set
	 */
	public void setAltriDaticpvVerdi(Cpv altriDaticpvVerdi) {
		this.altriDaticpvVerdi = altriDaticpvVerdi;
	}


	/**
	 * @return the altriDatitipoAcquistoMatRic
	 */
	public TipoAcquisto getAltriDatitipoAcquistoMatRic() {
		return altriDatitipoAcquistoMatRic;
	}


	/**
	 * @param altriDatitipoAcquistoMatRic the altriDatitipoAcquistoMatRic to set
	 */
	public void setAltriDatitipoAcquistoMatRic(TipoAcquisto altriDatitipoAcquistoMatRic) {
		this.altriDatitipoAcquistoMatRic = altriDatitipoAcquistoMatRic;
	}


	/**
	 * @return the altriDatitipoAcquistoVerdi
	 */
	public TipoAcquisto getAltriDatitipoAcquistoVerdi() {
		return altriDatitipoAcquistoVerdi;
	}


	/**
	 * @param altriDatitipoAcquistoVerdi the altriDatitipoAcquistoVerdi to set
	 */
	public void setAltriDatitipoAcquistoVerdi(TipoAcquisto altriDatitipoAcquistoVerdi) {
		this.altriDatitipoAcquistoVerdi = altriDatitipoAcquistoVerdi;
	}


	/**
	 * @return the altriDatifondiPnrr
	 */
	public Boolean getAltriDatifondiPnrr() {
		return altriDatifondiPnrr;
	}


	/**
	 * @param altriDatifondiPnrr the altriDatifondiPnrr to set
	 */
	public void setAltriDatifondiPnrr(Boolean altriDatifondiPnrr) {
		this.altriDatifondiPnrr = altriDatifondiPnrr;
	}


	/**
	 * @return the altriDatimotiviEsclusioneCig
	 */
	public MotiviEsclusioneCig getAltriDatimotiviEsclusioneCig() {
		return altriDatimotiviEsclusioneCig;
	}


	/**
	 * @param altriDatimotiviEsclusioneCig the altriDatimotiviEsclusioneCig to set
	 */
	public void setAltriDatimotiviEsclusioneCig(MotiviEsclusioneCig altriDatimotiviEsclusioneCig) {
		this.altriDatimotiviEsclusioneCig = altriDatimotiviEsclusioneCig;
	}


	/**
	 * @return the statoId
	 */
	public Integer getStatoId() {
		return statoId;
	}


	/**
	 * @param statoId the statoId to set
	 */
	public void setStatoId(Integer statoId) {
		this.statoId = statoId;
	}


	/**
	 * @return the statoCodice
	 */
	public String getStatoCodice() {
		return statoCodice;
	}


	/**
	 * @param statoCodice the statoCodice to set
	 */
	public void setStatoCodice(String statoCodice) {
		this.statoCodice = statoCodice;
	}


	/**
	 * @return the statoDescrizione
	 */
	public String getStatoDescrizione() {
		return statoDescrizione;
	}


	/**
	 * @param statoDescrizione the statoDescrizione to set
	 */
	public void setStatoDescrizione(String statoDescrizione) {
		this.statoDescrizione = statoDescrizione;
	}


	/**
	 * @return the interventoCapofilaId
	 */
	public UUID getInterventoCapofilaId() {
		return interventoCapofilaId;
	}


	/**
	 * @param interventoCapofilaId the interventoCapofilaId to set
	 */
	public void setInterventoCapofilaId(UUID interventoCapofilaId) {
		this.interventoCapofilaId = interventoCapofilaId;
	}


	/**
	 * @return the utenteRupCognome
	 */
	public String getUtenteRupCognome() {
		return utenteRupCognome;
	}


	/**
	 * @param utenteRupCognome the utenteRupCognome to set
	 */
	public void setUtenteRupCognome(String utenteRupCognome) {
		this.utenteRupCognome = utenteRupCognome;
	}


	/**
	 * @return the utenteRupNome
	 */
	public String getUtenteRupNome() {
		return utenteRupNome;
	}


	/**
	 * @param utenteRupNome the utenteRupNome to set
	 */
	public void setUtenteRupNome(String utenteRupNome) {
		this.utenteRupNome = utenteRupNome;
	}


	@Override
	public String toString() {
		return new StringBuilder()
			.append("Intervento [cui=").append(cui)
			.append("]")
			.toString();
	}

}
