/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - ACTA
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.doqui.acta.acaris.archive;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.IdVitalRecordCodeType;
import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per DocumentoPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DocumentoPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}DocumentPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="idCorrente" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="registrato" type="{archive.acaris.acta.doqui.it}RegistratoType"/&amp;gt;
 *         &amp;lt;element name="modificabile" type="{archive.acaris.acta.doqui.it}ModificabileType"/&amp;gt;
 *         &amp;lt;element name="definitivo" type="{archive.acaris.acta.doqui.it}DefinitivoType"/&amp;gt;
 *         &amp;lt;element name="origineInterna" type="{archive.acaris.acta.doqui.it}OrigineInternaType"/&amp;gt;
 *         &amp;lt;element name="analogico" type="{archive.acaris.acta.doqui.it}AnalogicoType"/&amp;gt;
 *         &amp;lt;element name="rappresentazioneDigitale" type="{archive.acaris.acta.doqui.it}RappresentazioneDigitaleType"/&amp;gt;
 *         &amp;lt;element name="daConservare" type="{archive.acaris.acta.doqui.it}DaConservareType"/&amp;gt;
 *         &amp;lt;element name="prontoPerConservazione" type="{archive.acaris.acta.doqui.it}ProntoPerConservazioneType"/&amp;gt;
 *         &amp;lt;element name="daConservareDopoIl" type="{archive.acaris.acta.doqui.it}DaConservareDopoIlType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="daConservarePrimaDel" type="{archive.acaris.acta.doqui.it}DaConservarePrimaDelType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="conservato" type="{archive.acaris.acta.doqui.it}ConservatoType"/&amp;gt;
 *         &amp;lt;element name="datiPersonali" type="{archive.acaris.acta.doqui.it}DatiPersonaliType"/&amp;gt;
 *         &amp;lt;element name="datiSensibili" type="{archive.acaris.acta.doqui.it}DatiSensibiliType"/&amp;gt;
 *         &amp;lt;element name="datiRiservati" type="{archive.acaris.acta.doqui.it}DatiRiservatiType"/&amp;gt;
 *         &amp;lt;element name="dataCreazione" type="{archive.acaris.acta.doqui.it}DataCreazioneType"/&amp;gt;
 *         &amp;lt;element name="paroleChiave" type="{archive.acaris.acta.doqui.it}ParoleChiaveType"/&amp;gt;
 *         &amp;lt;element name="modSMSQuarantena" type="{archive.acaris.acta.doqui.it}ModSMSQuarantenaType"/&amp;gt;
 *         &amp;lt;element name="congelato" type="{archive.acaris.acta.doqui.it}CongelatoType"/&amp;gt;
 *         &amp;lt;element name="referenziabile" type="{archive.acaris.acta.doqui.it}ReferenziabileType"/&amp;gt;
 *         &amp;lt;element name="identificativoConservazione" type="{archive.acaris.acta.doqui.it}IdentificativoConservazioneType"/&amp;gt;
 *         &amp;lt;element name="indiceEstesoOrigineEstratto" type="{archive.acaris.acta.doqui.it}IndiceClassificazioneEstesaXMLType"/&amp;gt;
 *         &amp;lt;element name="indiciEstesiEstrattiGenerati" type="{archive.acaris.acta.doqui.it}IndiceClassificazioneEstesaXMLType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="autoreGiuridico" type="{archive.acaris.acta.doqui.it}AutoreGiuridicoType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="autoreFisico" type="{archive.acaris.acta.doqui.it}AutoreFisicoType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="scrittore" type="{archive.acaris.acta.doqui.it}ScrittoreType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="originatore" type="{archive.acaris.acta.doqui.it}OriginatoreType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="destinatarioGiuridico" type="{archive.acaris.acta.doqui.it}DestinatarioGiuridicoType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="destinatarioFisico" type="{archive.acaris.acta.doqui.it}DestinatarioFisicoType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="soggettoProduttore" type="{archive.acaris.acta.doqui.it}SoggettoProduttoreType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="oggetto" type="{archive.acaris.acta.doqui.it}OggettoType"/&amp;gt;
 *         &amp;lt;element name="dataDocTopica" type="{archive.acaris.acta.doqui.it}DataDocTopicaType"/&amp;gt;
 *         &amp;lt;element name="dataDocCronica" type="{archive.acaris.acta.doqui.it}DataDocCronicaType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataTrasmissione" type="{archive.acaris.acta.doqui.it}DataTrasmissioneType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataRicevimento" type="{archive.acaris.acta.doqui.it}DataRicevimentoType"/&amp;gt;
 *         &amp;lt;element name="numRepertorio" type="{archive.acaris.acta.doqui.it}NumRepertorioType"/&amp;gt;
 *         &amp;lt;element name="forzareSeNumRepertorioEsistente" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="docConAllegati" type="{archive.acaris.acta.doqui.it}DocConAllegatiType"/&amp;gt;
 *         &amp;lt;element name="docAllegato" type="{archive.acaris.acta.doqui.it}DocAllegatoType"/&amp;gt;
 *         &amp;lt;element name="firmaElettronicaDigitale" type="{archive.acaris.acta.doqui.it}FirmaElettronicaDigitaleType"/&amp;gt;
 *         &amp;lt;element name="docAutenticato" type="{archive.acaris.acta.doqui.it}DocAutenticatoType"/&amp;gt;
 *         &amp;lt;element name="docAutenticatoFirmaAutenticata" type="{archive.acaris.acta.doqui.it}DocAutenticatoFirmaAutenticataType"/&amp;gt;
 *         &amp;lt;element name="docAutenticatoCopiaAutentica" type="{archive.acaris.acta.doqui.it}DocAutenticatoCopiaAutenticaType"/&amp;gt;
 *         &amp;lt;element name="idStatoDiEfficacia" type="{archive.acaris.acta.doqui.it}IdStatoDiEfficaciaType"/&amp;gt;
 *         &amp;lt;element name="idFormaDocumentaria" type="{archive.acaris.acta.doqui.it}IdFormaDocumentariaType"/&amp;gt;
 *         &amp;lt;element name="firmaElettronica" type="{archive.acaris.acta.doqui.it}FirmaElettronicaType"/&amp;gt;
 *         &amp;lt;element name="idProtocollo" type="{common.acaris.acta.doqui.it}ObjectIdType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="indiceClassificazione" type="{archive.acaris.acta.doqui.it}IndiceClassificazioneXMLType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="indiceClassificazioneEstesa" type="{archive.acaris.acta.doqui.it}IndiceClassificazioneEstesaXMLType" maxOccurs="unbounded"/&amp;gt;
 *         &amp;lt;element name="applicativoAlimentante" type="{archive.acaris.acta.doqui.it}ApplicativoAlimentanteType"/&amp;gt;
 *         &amp;lt;element name="dataCancellazione" type="{archive.acaris.acta.doqui.it}DataCancellazioneType"/&amp;gt;
 *         &amp;lt;element name="dataEsportazione" type="{archive.acaris.acta.doqui.it}DataEsportazioneType"/&amp;gt;
 *         &amp;lt;element name="idVitalRecordCode" type="{common.acaris.acta.doqui.it}IdVitalRecordCodeType"/&amp;gt;
 *         &amp;lt;element name="idAnnotazioniList" type="{common.acaris.acta.doqui.it}ObjectIdType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="renditionXMLList" type="{archive.acaris.acta.doqui.it}RenditionXMLType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataDocChiusuraRifTempUTC" type="{archive.acaris.acta.doqui.it}DataDocChiusuraRifTempUTCType"/&amp;gt;
 *         &amp;lt;element name="dataDocChiusura" type="{archive.acaris.acta.doqui.it}DataDocChiusuraType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoPropertiesType", propOrder = {
    "idCorrente",
    "registrato",
    "modificabile",
    "definitivo",
    "origineInterna",
    "analogico",
    "rappresentazioneDigitale",
    "daConservare",
    "prontoPerConservazione",
    "daConservareDopoIl",
    "daConservarePrimaDel",
    "conservato",
    "datiPersonali",
    "datiSensibili",
    "datiRiservati",
    "dataCreazione",
    "paroleChiave",
    "modSMSQuarantena",
    "congelato",
    "referenziabile",
    "identificativoConservazione",
    "indiceEstesoOrigineEstratto",
    "indiciEstesiEstrattiGenerati",
    "autoreGiuridico",
    "autoreFisico",
    "scrittore",
    "originatore",
    "destinatarioGiuridico",
    "destinatarioFisico",
    "soggettoProduttore",
    "oggetto",
    "dataDocTopica",
    "dataDocCronica",
    "dataTrasmissione",
    "dataRicevimento",
    "numRepertorio",
    "forzareSeNumRepertorioEsistente",
    "docConAllegati",
    "docAllegato",
    "firmaElettronicaDigitale",
    "docAutenticato",
    "docAutenticatoFirmaAutenticata",
    "docAutenticatoCopiaAutentica",
    "idStatoDiEfficacia",
    "idFormaDocumentaria",
    "firmaElettronica",
    "idProtocollo",
    "indiceClassificazione",
    "indiceClassificazioneEstesa",
    "applicativoAlimentante",
    "dataCancellazione",
    "dataEsportazione",
    "idVitalRecordCode",
    "idAnnotazioniList",
    "renditionXMLList",
    "dataDocChiusuraRifTempUTC",
    "dataDocChiusura"
})
@XmlSeeAlso({
    DocumentoSemplicePropertiesType.class,
    DocumentoRegistroPropertiesType.class,
    DocumentoDBPropertiesType.class
})
public abstract class DocumentoPropertiesType
    extends DocumentPropertiesType
{

    @XmlElement(required = true)
    protected ObjectIdType idCorrente;
    protected boolean registrato;
    protected boolean modificabile;
    protected boolean definitivo;
    protected boolean origineInterna;
    protected boolean analogico;
    protected boolean rappresentazioneDigitale;
    protected boolean daConservare;
    protected boolean prontoPerConservazione;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar daConservareDopoIl;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar daConservarePrimaDel;
    protected boolean conservato;
    protected boolean datiPersonali;
    protected boolean datiSensibili;
    protected boolean datiRiservati;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataCreazione;
    @XmlElement(required = true)
    protected String paroleChiave;
    protected boolean modSMSQuarantena;
    protected boolean congelato;
    protected boolean referenziabile;
    @XmlElement(required = true)
    protected String identificativoConservazione;
    @XmlElement(required = true)
    protected String indiceEstesoOrigineEstratto;
    protected List<String> indiciEstesiEstrattiGenerati;
    protected List<String> autoreGiuridico;
    protected List<String> autoreFisico;
    protected List<String> scrittore;
    protected List<String> originatore;
    protected List<String> destinatarioGiuridico;
    protected List<String> destinatarioFisico;
    protected List<String> soggettoProduttore;
    @XmlElement(required = true)
    protected String oggetto;
    @XmlElement(required = true)
    protected String dataDocTopica;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDocCronica;
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> dataTrasmissione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataRicevimento;
    @XmlElement(required = true)
    protected String numRepertorio;
    protected boolean forzareSeNumRepertorioEsistente;
    protected boolean docConAllegati;
    protected boolean docAllegato;
    protected boolean firmaElettronicaDigitale;
    protected boolean docAutenticato;
    protected boolean docAutenticatoFirmaAutenticata;
    protected boolean docAutenticatoCopiaAutentica;
    @XmlElement(required = true)
    protected IdStatoDiEfficaciaType idStatoDiEfficacia;
    @XmlElement(required = true)
    protected IdFormaDocumentariaType idFormaDocumentaria;
    protected boolean firmaElettronica;
    protected List<ObjectIdType> idProtocollo;
    protected List<String> indiceClassificazione;
    @XmlElement(required = true)
    protected List<String> indiceClassificazioneEstesa;
    @XmlElement(required = true)
    protected String applicativoAlimentante;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataCancellazione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataEsportazione;
    @XmlElement(required = true)
    protected IdVitalRecordCodeType idVitalRecordCode;
    protected List<ObjectIdType> idAnnotazioniList;
    protected List<String> renditionXMLList;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDocChiusuraRifTempUTC;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDocChiusura;

    /**
     * Recupera il valore della proprietà idCorrente.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdCorrente() {
        return idCorrente;
    }

    /**
     * Imposta il valore della proprietà idCorrente.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdCorrente(ObjectIdType value) {
        this.idCorrente = value;
    }

    /**
     * Recupera il valore della proprietà registrato.
     * 
     */
    public boolean isRegistrato() {
        return registrato;
    }

    /**
     * Imposta il valore della proprietà registrato.
     * 
     */
    public void setRegistrato(boolean value) {
        this.registrato = value;
    }

    /**
     * Recupera il valore della proprietà modificabile.
     * 
     */
    public boolean isModificabile() {
        return modificabile;
    }

    /**
     * Imposta il valore della proprietà modificabile.
     * 
     */
    public void setModificabile(boolean value) {
        this.modificabile = value;
    }

    /**
     * Recupera il valore della proprietà definitivo.
     * 
     */
    public boolean isDefinitivo() {
        return definitivo;
    }

    /**
     * Imposta il valore della proprietà definitivo.
     * 
     */
    public void setDefinitivo(boolean value) {
        this.definitivo = value;
    }

    /**
     * Recupera il valore della proprietà origineInterna.
     * 
     */
    public boolean isOrigineInterna() {
        return origineInterna;
    }

    /**
     * Imposta il valore della proprietà origineInterna.
     * 
     */
    public void setOrigineInterna(boolean value) {
        this.origineInterna = value;
    }

    /**
     * Recupera il valore della proprietà analogico.
     * 
     */
    public boolean isAnalogico() {
        return analogico;
    }

    /**
     * Imposta il valore della proprietà analogico.
     * 
     */
    public void setAnalogico(boolean value) {
        this.analogico = value;
    }

    /**
     * Recupera il valore della proprietà rappresentazioneDigitale.
     * 
     */
    public boolean isRappresentazioneDigitale() {
        return rappresentazioneDigitale;
    }

    /**
     * Imposta il valore della proprietà rappresentazioneDigitale.
     * 
     */
    public void setRappresentazioneDigitale(boolean value) {
        this.rappresentazioneDigitale = value;
    }

    /**
     * Recupera il valore della proprietà daConservare.
     * 
     */
    public boolean isDaConservare() {
        return daConservare;
    }

    /**
     * Imposta il valore della proprietà daConservare.
     * 
     */
    public void setDaConservare(boolean value) {
        this.daConservare = value;
    }

    /**
     * Recupera il valore della proprietà prontoPerConservazione.
     * 
     */
    public boolean isProntoPerConservazione() {
        return prontoPerConservazione;
    }

    /**
     * Imposta il valore della proprietà prontoPerConservazione.
     * 
     */
    public void setProntoPerConservazione(boolean value) {
        this.prontoPerConservazione = value;
    }

    /**
     * Recupera il valore della proprietà daConservareDopoIl.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDaConservareDopoIl() {
        return daConservareDopoIl;
    }

    /**
     * Imposta il valore della proprietà daConservareDopoIl.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDaConservareDopoIl(XMLGregorianCalendar value) {
        this.daConservareDopoIl = value;
    }

    /**
     * Recupera il valore della proprietà daConservarePrimaDel.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDaConservarePrimaDel() {
        return daConservarePrimaDel;
    }

    /**
     * Imposta il valore della proprietà daConservarePrimaDel.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDaConservarePrimaDel(XMLGregorianCalendar value) {
        this.daConservarePrimaDel = value;
    }

    /**
     * Recupera il valore della proprietà conservato.
     * 
     */
    public boolean isConservato() {
        return conservato;
    }

    /**
     * Imposta il valore della proprietà conservato.
     * 
     */
    public void setConservato(boolean value) {
        this.conservato = value;
    }

    /**
     * Recupera il valore della proprietà datiPersonali.
     * 
     */
    public boolean isDatiPersonali() {
        return datiPersonali;
    }

    /**
     * Imposta il valore della proprietà datiPersonali.
     * 
     */
    public void setDatiPersonali(boolean value) {
        this.datiPersonali = value;
    }

    /**
     * Recupera il valore della proprietà datiSensibili.
     * 
     */
    public boolean isDatiSensibili() {
        return datiSensibili;
    }

    /**
     * Imposta il valore della proprietà datiSensibili.
     * 
     */
    public void setDatiSensibili(boolean value) {
        this.datiSensibili = value;
    }

    /**
     * Recupera il valore della proprietà datiRiservati.
     * 
     */
    public boolean isDatiRiservati() {
        return datiRiservati;
    }

    /**
     * Imposta il valore della proprietà datiRiservati.
     * 
     */
    public void setDatiRiservati(boolean value) {
        this.datiRiservati = value;
    }

    /**
     * Recupera il valore della proprietà dataCreazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCreazione() {
        return dataCreazione;
    }

    /**
     * Imposta il valore della proprietà dataCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCreazione(XMLGregorianCalendar value) {
        this.dataCreazione = value;
    }

    /**
     * Recupera il valore della proprietà paroleChiave.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParoleChiave() {
        return paroleChiave;
    }

    /**
     * Imposta il valore della proprietà paroleChiave.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParoleChiave(String value) {
        this.paroleChiave = value;
    }

    /**
     * Recupera il valore della proprietà modSMSQuarantena.
     * 
     */
    public boolean isModSMSQuarantena() {
        return modSMSQuarantena;
    }

    /**
     * Imposta il valore della proprietà modSMSQuarantena.
     * 
     */
    public void setModSMSQuarantena(boolean value) {
        this.modSMSQuarantena = value;
    }

    /**
     * Recupera il valore della proprietà congelato.
     * 
     */
    public boolean isCongelato() {
        return congelato;
    }

    /**
     * Imposta il valore della proprietà congelato.
     * 
     */
    public void setCongelato(boolean value) {
        this.congelato = value;
    }

    /**
     * Recupera il valore della proprietà referenziabile.
     * 
     */
    public boolean isReferenziabile() {
        return referenziabile;
    }

    /**
     * Imposta il valore della proprietà referenziabile.
     * 
     */
    public void setReferenziabile(boolean value) {
        this.referenziabile = value;
    }

    /**
     * Recupera il valore della proprietà identificativoConservazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoConservazione() {
        return identificativoConservazione;
    }

    /**
     * Imposta il valore della proprietà identificativoConservazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoConservazione(String value) {
        this.identificativoConservazione = value;
    }

    /**
     * Recupera il valore della proprietà indiceEstesoOrigineEstratto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiceEstesoOrigineEstratto() {
        return indiceEstesoOrigineEstratto;
    }

    /**
     * Imposta il valore della proprietà indiceEstesoOrigineEstratto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiceEstesoOrigineEstratto(String value) {
        this.indiceEstesoOrigineEstratto = value;
    }

    /**
     * Gets the value of the indiciEstesiEstrattiGenerati property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the indiciEstesiEstrattiGenerati property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIndiciEstesiEstrattiGenerati().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIndiciEstesiEstrattiGenerati() {
        if (indiciEstesiEstrattiGenerati == null) {
            indiciEstesiEstrattiGenerati = new ArrayList<String>();
        }
        return this.indiciEstesiEstrattiGenerati;
    }

    /**
     * Gets the value of the autoreGiuridico property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the autoreGiuridico property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getAutoreGiuridico().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAutoreGiuridico() {
        if (autoreGiuridico == null) {
            autoreGiuridico = new ArrayList<String>();
        }
        return this.autoreGiuridico;
    }

    /**
     * Gets the value of the autoreFisico property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the autoreFisico property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getAutoreFisico().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAutoreFisico() {
        if (autoreFisico == null) {
            autoreFisico = new ArrayList<String>();
        }
        return this.autoreFisico;
    }

    /**
     * Gets the value of the scrittore property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the scrittore property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getScrittore().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getScrittore() {
        if (scrittore == null) {
            scrittore = new ArrayList<String>();
        }
        return this.scrittore;
    }

    /**
     * Gets the value of the originatore property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the originatore property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getOriginatore().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOriginatore() {
        if (originatore == null) {
            originatore = new ArrayList<String>();
        }
        return this.originatore;
    }

    /**
     * Gets the value of the destinatarioGiuridico property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the destinatarioGiuridico property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDestinatarioGiuridico().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDestinatarioGiuridico() {
        if (destinatarioGiuridico == null) {
            destinatarioGiuridico = new ArrayList<String>();
        }
        return this.destinatarioGiuridico;
    }

    /**
     * Gets the value of the destinatarioFisico property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the destinatarioFisico property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDestinatarioFisico().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDestinatarioFisico() {
        if (destinatarioFisico == null) {
            destinatarioFisico = new ArrayList<String>();
        }
        return this.destinatarioFisico;
    }

    /**
     * Gets the value of the soggettoProduttore property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the soggettoProduttore property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getSoggettoProduttore().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSoggettoProduttore() {
        if (soggettoProduttore == null) {
            soggettoProduttore = new ArrayList<String>();
        }
        return this.soggettoProduttore;
    }

    /**
     * Recupera il valore della proprietà oggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Imposta il valore della proprietà oggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOggetto(String value) {
        this.oggetto = value;
    }

    /**
     * Recupera il valore della proprietà dataDocTopica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataDocTopica() {
        return dataDocTopica;
    }

    /**
     * Imposta il valore della proprietà dataDocTopica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataDocTopica(String value) {
        this.dataDocTopica = value;
    }

    /**
     * Recupera il valore della proprietà dataDocCronica.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDocCronica() {
        return dataDocCronica;
    }

    /**
     * Imposta il valore della proprietà dataDocCronica.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDocCronica(XMLGregorianCalendar value) {
        this.dataDocCronica = value;
    }

    /**
     * Gets the value of the dataTrasmissione property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the dataTrasmissione property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDataTrasmissione().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getDataTrasmissione() {
        if (dataTrasmissione == null) {
            dataTrasmissione = new ArrayList<XMLGregorianCalendar>();
        }
        return this.dataTrasmissione;
    }

    /**
     * Recupera il valore della proprietà dataRicevimento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRicevimento() {
        return dataRicevimento;
    }

    /**
     * Imposta il valore della proprietà dataRicevimento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRicevimento(XMLGregorianCalendar value) {
        this.dataRicevimento = value;
    }

    /**
     * Recupera il valore della proprietà numRepertorio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumRepertorio() {
        return numRepertorio;
    }

    /**
     * Imposta il valore della proprietà numRepertorio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumRepertorio(String value) {
        this.numRepertorio = value;
    }

    /**
     * Recupera il valore della proprietà forzareSeNumRepertorioEsistente.
     * 
     */
    public boolean isForzareSeNumRepertorioEsistente() {
        return forzareSeNumRepertorioEsistente;
    }

    /**
     * Imposta il valore della proprietà forzareSeNumRepertorioEsistente.
     * 
     */
    public void setForzareSeNumRepertorioEsistente(boolean value) {
        this.forzareSeNumRepertorioEsistente = value;
    }

    /**
     * Recupera il valore della proprietà docConAllegati.
     * 
     */
    public boolean isDocConAllegati() {
        return docConAllegati;
    }

    /**
     * Imposta il valore della proprietà docConAllegati.
     * 
     */
    public void setDocConAllegati(boolean value) {
        this.docConAllegati = value;
    }

    /**
     * Recupera il valore della proprietà docAllegato.
     * 
     */
    public boolean isDocAllegato() {
        return docAllegato;
    }

    /**
     * Imposta il valore della proprietà docAllegato.
     * 
     */
    public void setDocAllegato(boolean value) {
        this.docAllegato = value;
    }

    /**
     * Recupera il valore della proprietà firmaElettronicaDigitale.
     * 
     */
    public boolean isFirmaElettronicaDigitale() {
        return firmaElettronicaDigitale;
    }

    /**
     * Imposta il valore della proprietà firmaElettronicaDigitale.
     * 
     */
    public void setFirmaElettronicaDigitale(boolean value) {
        this.firmaElettronicaDigitale = value;
    }

    /**
     * Recupera il valore della proprietà docAutenticato.
     * 
     */
    public boolean isDocAutenticato() {
        return docAutenticato;
    }

    /**
     * Imposta il valore della proprietà docAutenticato.
     * 
     */
    public void setDocAutenticato(boolean value) {
        this.docAutenticato = value;
    }

    /**
     * Recupera il valore della proprietà docAutenticatoFirmaAutenticata.
     * 
     */
    public boolean isDocAutenticatoFirmaAutenticata() {
        return docAutenticatoFirmaAutenticata;
    }

    /**
     * Imposta il valore della proprietà docAutenticatoFirmaAutenticata.
     * 
     */
    public void setDocAutenticatoFirmaAutenticata(boolean value) {
        this.docAutenticatoFirmaAutenticata = value;
    }

    /**
     * Recupera il valore della proprietà docAutenticatoCopiaAutentica.
     * 
     */
    public boolean isDocAutenticatoCopiaAutentica() {
        return docAutenticatoCopiaAutentica;
    }

    /**
     * Imposta il valore della proprietà docAutenticatoCopiaAutentica.
     * 
     */
    public void setDocAutenticatoCopiaAutentica(boolean value) {
        this.docAutenticatoCopiaAutentica = value;
    }

    /**
     * Recupera il valore della proprietà idStatoDiEfficacia.
     * 
     * @return
     *     possible object is
     *     {@link IdStatoDiEfficaciaType }
     *     
     */
    public IdStatoDiEfficaciaType getIdStatoDiEfficacia() {
        return idStatoDiEfficacia;
    }

    /**
     * Imposta il valore della proprietà idStatoDiEfficacia.
     * 
     * @param value
     *     allowed object is
     *     {@link IdStatoDiEfficaciaType }
     *     
     */
    public void setIdStatoDiEfficacia(IdStatoDiEfficaciaType value) {
        this.idStatoDiEfficacia = value;
    }

    /**
     * Recupera il valore della proprietà idFormaDocumentaria.
     * 
     * @return
     *     possible object is
     *     {@link IdFormaDocumentariaType }
     *     
     */
    public IdFormaDocumentariaType getIdFormaDocumentaria() {
        return idFormaDocumentaria;
    }

    /**
     * Imposta il valore della proprietà idFormaDocumentaria.
     * 
     * @param value
     *     allowed object is
     *     {@link IdFormaDocumentariaType }
     *     
     */
    public void setIdFormaDocumentaria(IdFormaDocumentariaType value) {
        this.idFormaDocumentaria = value;
    }

    /**
     * Recupera il valore della proprietà firmaElettronica.
     * 
     */
    public boolean isFirmaElettronica() {
        return firmaElettronica;
    }

    /**
     * Imposta il valore della proprietà firmaElettronica.
     * 
     */
    public void setFirmaElettronica(boolean value) {
        this.firmaElettronica = value;
    }

    /**
     * Gets the value of the idProtocollo property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the idProtocollo property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIdProtocollo().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectIdType }
     * 
     * 
     */
    public List<ObjectIdType> getIdProtocollo() {
        if (idProtocollo == null) {
            idProtocollo = new ArrayList<ObjectIdType>();
        }
        return this.idProtocollo;
    }

    /**
     * Gets the value of the indiceClassificazione property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the indiceClassificazione property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIndiceClassificazione().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIndiceClassificazione() {
        if (indiceClassificazione == null) {
            indiceClassificazione = new ArrayList<String>();
        }
        return this.indiceClassificazione;
    }

    /**
     * Gets the value of the indiceClassificazioneEstesa property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the indiceClassificazioneEstesa property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIndiceClassificazioneEstesa().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIndiceClassificazioneEstesa() {
        if (indiceClassificazioneEstesa == null) {
            indiceClassificazioneEstesa = new ArrayList<String>();
        }
        return this.indiceClassificazioneEstesa;
    }

    /**
     * Recupera il valore della proprietà applicativoAlimentante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicativoAlimentante() {
        return applicativoAlimentante;
    }

    /**
     * Imposta il valore della proprietà applicativoAlimentante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicativoAlimentante(String value) {
        this.applicativoAlimentante = value;
    }

    /**
     * Recupera il valore della proprietà dataCancellazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCancellazione() {
        return dataCancellazione;
    }

    /**
     * Imposta il valore della proprietà dataCancellazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCancellazione(XMLGregorianCalendar value) {
        this.dataCancellazione = value;
    }

    /**
     * Recupera il valore della proprietà dataEsportazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEsportazione() {
        return dataEsportazione;
    }

    /**
     * Imposta il valore della proprietà dataEsportazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEsportazione(XMLGregorianCalendar value) {
        this.dataEsportazione = value;
    }

    /**
     * Recupera il valore della proprietà idVitalRecordCode.
     * 
     * @return
     *     possible object is
     *     {@link IdVitalRecordCodeType }
     *     
     */
    public IdVitalRecordCodeType getIdVitalRecordCode() {
        return idVitalRecordCode;
    }

    /**
     * Imposta il valore della proprietà idVitalRecordCode.
     * 
     * @param value
     *     allowed object is
     *     {@link IdVitalRecordCodeType }
     *     
     */
    public void setIdVitalRecordCode(IdVitalRecordCodeType value) {
        this.idVitalRecordCode = value;
    }

    /**
     * Gets the value of the idAnnotazioniList property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the idAnnotazioniList property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIdAnnotazioniList().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectIdType }
     * 
     * 
     */
    public List<ObjectIdType> getIdAnnotazioniList() {
        if (idAnnotazioniList == null) {
            idAnnotazioniList = new ArrayList<ObjectIdType>();
        }
        return this.idAnnotazioniList;
    }

    /**
     * Gets the value of the renditionXMLList property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the renditionXMLList property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getRenditionXMLList().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRenditionXMLList() {
        if (renditionXMLList == null) {
            renditionXMLList = new ArrayList<String>();
        }
        return this.renditionXMLList;
    }

    /**
     * Recupera il valore della proprietà dataDocChiusuraRifTempUTC.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDocChiusuraRifTempUTC() {
        return dataDocChiusuraRifTempUTC;
    }

    /**
     * Imposta il valore della proprietà dataDocChiusuraRifTempUTC.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDocChiusuraRifTempUTC(XMLGregorianCalendar value) {
        this.dataDocChiusuraRifTempUTC = value;
    }

    /**
     * Recupera il valore della proprietà dataDocChiusura.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDocChiusura() {
        return dataDocChiusura;
    }

    /**
     * Imposta il valore della proprietà dataDocChiusura.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDocChiusura(XMLGregorianCalendar value) {
        this.dataDocChiusura = value;
    }

}
