/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
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
	
	private Boolean esenteCup; 
	
	private Boolean flagCuiNonGenerato;
	
	private String motivazioneNonRiproposto;
	
	private Settore settore;

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
