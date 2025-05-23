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

package it.doqui.acta.acaris.subjectregistryservice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.DecodificaType;
import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per SoggettoPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SoggettoPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{subjectregistryservice.acaris.acta.doqui.it}SubjectRegistryPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="uuid" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="dataInizioValidita" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="dataFineValidita" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="stato" type="{subjectregistryservice.acaris.acta.doqui.it}enumStatoSoggetto"/&amp;gt;
 *         &amp;lt;element name="chiaveEsterna" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="internoEnte" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="pfPgUl" type="{subjectregistryservice.acaris.acta.doqui.it}enumPFPGUL"/&amp;gt;
 *         &amp;lt;element name="dataAccorpamento" type="{common.acaris.acta.doqui.it}date" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="idEnteAppartenenza" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idAOOAppartenenza" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idUtenteCreazione" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idAooAssociata" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idStrutturaAssociata" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idNodoAssociato" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="categoriaAnagrafica" type="{common.acaris.acta.doqui.it}DecodificaType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceFonte" type="{common.acaris.acta.doqui.it}DecodificaType"/&amp;gt;
 *         &amp;lt;element name="tipoSoggettoOrigine" type="{common.acaris.acta.doqui.it}DecodificaType"/&amp;gt;
 *         &amp;lt;element name="tipoSoggettoAppartenenza" type="{common.acaris.acta.doqui.it}DecodificaType"/&amp;gt;
 *         &amp;lt;element name="idSoggettoPadre" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="nome" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="cognome" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="denominazione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="codice" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="codiceFiscale" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="email" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="emailPEC" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="partitaIVA" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="fax" type="{common.acaris.acta.doqui.it}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="telefono" type="{common.acaris.acta.doqui.it}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ente" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="aoo" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="matricola" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="dataNascita" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="luogoNascita" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="note" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="denominazioneNormalizzata" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="idIndirizzo" type="{common.acaris.acta.doqui.it}ObjectIdType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoPropertiesType", propOrder = {
    "uuid",
    "dataInizioValidita",
    "dataFineValidita",
    "stato",
    "chiaveEsterna",
    "internoEnte",
    "pfPgUl",
    "dataAccorpamento",
    "idEnteAppartenenza",
    "idAOOAppartenenza",
    "idUtenteCreazione",
    "idAooAssociata",
    "idStrutturaAssociata",
    "idNodoAssociato",
    "categoriaAnagrafica",
    "codiceFonte",
    "tipoSoggettoOrigine",
    "tipoSoggettoAppartenenza",
    "idSoggettoPadre",
    "nome",
    "cognome",
    "denominazione",
    "codice",
    "codiceFiscale",
    "email",
    "emailPEC",
    "partitaIVA",
    "fax",
    "telefono",
    "ente",
    "aoo",
    "matricola",
    "dataNascita",
    "luogoNascita",
    "note",
    "denominazioneNormalizzata",
    "idIndirizzo"
})
public class SoggettoPropertiesType
    extends SubjectRegistryPropertiesType
{

    @XmlElement(required = true)
    protected String uuid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumStatoSoggetto stato;
    @XmlElement(required = true)
    protected String chiaveEsterna;
    protected boolean internoEnte;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPFPGUL pfPgUl;
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> dataAccorpamento;
    @XmlElement(required = true)
    protected ObjectIdType idEnteAppartenenza;
    @XmlElement(required = true)
    protected ObjectIdType idAOOAppartenenza;
    @XmlElement(required = true)
    protected ObjectIdType idUtenteCreazione;
    @XmlElement(required = true)
    protected ObjectIdType idAooAssociata;
    @XmlElement(required = true)
    protected ObjectIdType idStrutturaAssociata;
    @XmlElement(required = true)
    protected ObjectIdType idNodoAssociato;
    protected List<DecodificaType> categoriaAnagrafica;
    @XmlElement(required = true)
    protected DecodificaType codiceFonte;
    @XmlElement(required = true)
    protected DecodificaType tipoSoggettoOrigine;
    @XmlElement(required = true)
    protected DecodificaType tipoSoggettoAppartenenza;
    @XmlElement(required = true)
    protected ObjectIdType idSoggettoPadre;
    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String cognome;
    @XmlElement(required = true)
    protected String denominazione;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String codiceFiscale;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String emailPEC;
    @XmlElement(required = true)
    protected String partitaIVA;
    protected List<String> fax;
    protected List<String> telefono;
    protected boolean ente;
    protected boolean aoo;
    @XmlElement(required = true)
    protected String matricola;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataNascita;
    @XmlElement(required = true)
    protected String luogoNascita;
    @XmlElement(required = true)
    protected String note;
    @XmlElement(required = true)
    protected String denominazioneNormalizzata;
    protected List<ObjectIdType> idIndirizzo;

    /**
     * Recupera il valore della proprietà uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Imposta il valore della proprietà uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della proprietà dataInizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della proprietà dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della proprietà dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link EnumStatoSoggetto }
     *     
     */
    public EnumStatoSoggetto getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumStatoSoggetto }
     *     
     */
    public void setStato(EnumStatoSoggetto value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprietà chiaveEsterna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiaveEsterna() {
        return chiaveEsterna;
    }

    /**
     * Imposta il valore della proprietà chiaveEsterna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiaveEsterna(String value) {
        this.chiaveEsterna = value;
    }

    /**
     * Recupera il valore della proprietà internoEnte.
     * 
     */
    public boolean isInternoEnte() {
        return internoEnte;
    }

    /**
     * Imposta il valore della proprietà internoEnte.
     * 
     */
    public void setInternoEnte(boolean value) {
        this.internoEnte = value;
    }

    /**
     * Recupera il valore della proprietà pfPgUl.
     * 
     * @return
     *     possible object is
     *     {@link EnumPFPGUL }
     *     
     */
    public EnumPFPGUL getPfPgUl() {
        return pfPgUl;
    }

    /**
     * Imposta il valore della proprietà pfPgUl.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPFPGUL }
     *     
     */
    public void setPfPgUl(EnumPFPGUL value) {
        this.pfPgUl = value;
    }

    /**
     * Gets the value of the dataAccorpamento property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the dataAccorpamento property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDataAccorpamento().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getDataAccorpamento() {
        if (dataAccorpamento == null) {
            dataAccorpamento = new ArrayList<XMLGregorianCalendar>();
        }
        return this.dataAccorpamento;
    }

    /**
     * Recupera il valore della proprietà idEnteAppartenenza.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdEnteAppartenenza() {
        return idEnteAppartenenza;
    }

    /**
     * Imposta il valore della proprietà idEnteAppartenenza.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdEnteAppartenenza(ObjectIdType value) {
        this.idEnteAppartenenza = value;
    }

    /**
     * Recupera il valore della proprietà idAOOAppartenenza.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdAOOAppartenenza() {
        return idAOOAppartenenza;
    }

    /**
     * Imposta il valore della proprietà idAOOAppartenenza.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdAOOAppartenenza(ObjectIdType value) {
        this.idAOOAppartenenza = value;
    }

    /**
     * Recupera il valore della proprietà idUtenteCreazione.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdUtenteCreazione() {
        return idUtenteCreazione;
    }

    /**
     * Imposta il valore della proprietà idUtenteCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdUtenteCreazione(ObjectIdType value) {
        this.idUtenteCreazione = value;
    }

    /**
     * Recupera il valore della proprietà idAooAssociata.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdAooAssociata() {
        return idAooAssociata;
    }

    /**
     * Imposta il valore della proprietà idAooAssociata.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdAooAssociata(ObjectIdType value) {
        this.idAooAssociata = value;
    }

    /**
     * Recupera il valore della proprietà idStrutturaAssociata.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdStrutturaAssociata() {
        return idStrutturaAssociata;
    }

    /**
     * Imposta il valore della proprietà idStrutturaAssociata.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdStrutturaAssociata(ObjectIdType value) {
        this.idStrutturaAssociata = value;
    }

    /**
     * Recupera il valore della proprietà idNodoAssociato.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdNodoAssociato() {
        return idNodoAssociato;
    }

    /**
     * Imposta il valore della proprietà idNodoAssociato.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdNodoAssociato(ObjectIdType value) {
        this.idNodoAssociato = value;
    }

    /**
     * Gets the value of the categoriaAnagrafica property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the categoriaAnagrafica property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCategoriaAnagrafica().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link DecodificaType }
     * 
     * 
     */
    public List<DecodificaType> getCategoriaAnagrafica() {
        if (categoriaAnagrafica == null) {
            categoriaAnagrafica = new ArrayList<DecodificaType>();
        }
        return this.categoriaAnagrafica;
    }

    /**
     * Recupera il valore della proprietà codiceFonte.
     * 
     * @return
     *     possible object is
     *     {@link DecodificaType }
     *     
     */
    public DecodificaType getCodiceFonte() {
        return codiceFonte;
    }

    /**
     * Imposta il valore della proprietà codiceFonte.
     * 
     * @param value
     *     allowed object is
     *     {@link DecodificaType }
     *     
     */
    public void setCodiceFonte(DecodificaType value) {
        this.codiceFonte = value;
    }

    /**
     * Recupera il valore della proprietà tipoSoggettoOrigine.
     * 
     * @return
     *     possible object is
     *     {@link DecodificaType }
     *     
     */
    public DecodificaType getTipoSoggettoOrigine() {
        return tipoSoggettoOrigine;
    }

    /**
     * Imposta il valore della proprietà tipoSoggettoOrigine.
     * 
     * @param value
     *     allowed object is
     *     {@link DecodificaType }
     *     
     */
    public void setTipoSoggettoOrigine(DecodificaType value) {
        this.tipoSoggettoOrigine = value;
    }

    /**
     * Recupera il valore della proprietà tipoSoggettoAppartenenza.
     * 
     * @return
     *     possible object is
     *     {@link DecodificaType }
     *     
     */
    public DecodificaType getTipoSoggettoAppartenenza() {
        return tipoSoggettoAppartenenza;
    }

    /**
     * Imposta il valore della proprietà tipoSoggettoAppartenenza.
     * 
     * @param value
     *     allowed object is
     *     {@link DecodificaType }
     *     
     */
    public void setTipoSoggettoAppartenenza(DecodificaType value) {
        this.tipoSoggettoAppartenenza = value;
    }

    /**
     * Recupera il valore della proprietà idSoggettoPadre.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdSoggettoPadre() {
        return idSoggettoPadre;
    }

    /**
     * Imposta il valore della proprietà idSoggettoPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdSoggettoPadre(ObjectIdType value) {
        this.idSoggettoPadre = value;
    }

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà cognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il valore della proprietà cognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Recupera il valore della proprietà denominazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Imposta il valore della proprietà denominazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazione(String value) {
        this.denominazione = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprietà codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprietà email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della proprietà email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Recupera il valore della proprietà emailPEC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailPEC() {
        return emailPEC;
    }

    /**
     * Imposta il valore della proprietà emailPEC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailPEC(String value) {
        this.emailPEC = value;
    }

    /**
     * Recupera il valore della proprietà partitaIVA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitaIVA() {
        return partitaIVA;
    }

    /**
     * Imposta il valore della proprietà partitaIVA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitaIVA(String value) {
        this.partitaIVA = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the fax property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getFax().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFax() {
        if (fax == null) {
            fax = new ArrayList<String>();
        }
        return this.fax;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the telefono property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getTelefono().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTelefono() {
        if (telefono == null) {
            telefono = new ArrayList<String>();
        }
        return this.telefono;
    }

    /**
     * Recupera il valore della proprietà ente.
     * 
     */
    public boolean isEnte() {
        return ente;
    }

    /**
     * Imposta il valore della proprietà ente.
     * 
     */
    public void setEnte(boolean value) {
        this.ente = value;
    }

    /**
     * Recupera il valore della proprietà aoo.
     * 
     */
    public boolean isAoo() {
        return aoo;
    }

    /**
     * Imposta il valore della proprietà aoo.
     * 
     */
    public void setAoo(boolean value) {
        this.aoo = value;
    }

    /**
     * Recupera il valore della proprietà matricola.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * Imposta il valore della proprietà matricola.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricola(String value) {
        this.matricola = value;
    }

    /**
     * Recupera il valore della proprietà dataNascita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta il valore della proprietà dataNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascita(XMLGregorianCalendar value) {
        this.dataNascita = value;
    }

    /**
     * Recupera il valore della proprietà luogoNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLuogoNascita() {
        return luogoNascita;
    }

    /**
     * Imposta il valore della proprietà luogoNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLuogoNascita(String value) {
        this.luogoNascita = value;
    }

    /**
     * Recupera il valore della proprietà note.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta il valore della proprietà note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Recupera il valore della proprietà denominazioneNormalizzata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneNormalizzata() {
        return denominazioneNormalizzata;
    }

    /**
     * Imposta il valore della proprietà denominazioneNormalizzata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneNormalizzata(String value) {
        this.denominazioneNormalizzata = value;
    }

    /**
     * Gets the value of the idIndirizzo property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the idIndirizzo property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIdIndirizzo().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectIdType }
     * 
     * 
     */
    public List<ObjectIdType> getIdIndirizzo() {
        if (idIndirizzo == null) {
            idIndirizzo = new ArrayList<ObjectIdType>();
        }
        return this.idIndirizzo;
    }

}
