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

package it.doqui.acta.acaris.officialbookservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.DecodificaType;
import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per CorrispondentePropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CorrispondentePropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}OfficialBookPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="denominazione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="nome" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="cognome" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="tipoMD" type="{officialbookservice.acaris.acta.doqui.it}enumTipoCorrispondente"/&amp;gt;
 *         &amp;lt;element name="interno" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="ordinale" type="{common.acaris.acta.doqui.it}integer"/&amp;gt;
 *         &amp;lt;element name="carica" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="persona" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="motivoCancellazione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="dataInizioValidita" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="dataFineValidita" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="testoFoglioTrasmissione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="ruolo" type="{common.acaris.acta.doqui.it}DecodificaType"/&amp;gt;
 *         &amp;lt;element name="idSoggetto" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idRegistrazione" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrispondentePropertiesType", propOrder = {
    "denominazione",
    "nome",
    "cognome",
    "tipoMD",
    "interno",
    "ordinale",
    "carica",
    "persona",
    "motivoCancellazione",
    "dataInizioValidita",
    "dataFineValidita",
    "testoFoglioTrasmissione",
    "ruolo",
    "idSoggetto",
    "idRegistrazione"
})
public class CorrispondentePropertiesType
    extends OfficialBookPropertiesType
{

    @XmlElement(required = true)
    protected String denominazione;
    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String cognome;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipoCorrispondente tipoMD;
    protected boolean interno;
    protected int ordinale;
    @XmlElement(required = true)
    protected String carica;
    @XmlElement(required = true)
    protected String persona;
    @XmlElement(required = true)
    protected String motivoCancellazione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    @XmlElement(required = true)
    protected String testoFoglioTrasmissione;
    @XmlElement(required = true)
    protected DecodificaType ruolo;
    @XmlElement(required = true)
    protected ObjectIdType idSoggetto;
    @XmlElement(required = true)
    protected ObjectIdType idRegistrazione;

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
     * Recupera il valore della proprietà tipoMD.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoCorrispondente }
     *     
     */
    public EnumTipoCorrispondente getTipoMD() {
        return tipoMD;
    }

    /**
     * Imposta il valore della proprietà tipoMD.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoCorrispondente }
     *     
     */
    public void setTipoMD(EnumTipoCorrispondente value) {
        this.tipoMD = value;
    }

    /**
     * Recupera il valore della proprietà interno.
     * 
     */
    public boolean isInterno() {
        return interno;
    }

    /**
     * Imposta il valore della proprietà interno.
     * 
     */
    public void setInterno(boolean value) {
        this.interno = value;
    }

    /**
     * Recupera il valore della proprietà ordinale.
     * 
     */
    public int getOrdinale() {
        return ordinale;
    }

    /**
     * Imposta il valore della proprietà ordinale.
     * 
     */
    public void setOrdinale(int value) {
        this.ordinale = value;
    }

    /**
     * Recupera il valore della proprietà carica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarica() {
        return carica;
    }

    /**
     * Imposta il valore della proprietà carica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarica(String value) {
        this.carica = value;
    }

    /**
     * Recupera il valore della proprietà persona.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersona() {
        return persona;
    }

    /**
     * Imposta il valore della proprietà persona.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersona(String value) {
        this.persona = value;
    }

    /**
     * Recupera il valore della proprietà motivoCancellazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoCancellazione() {
        return motivoCancellazione;
    }

    /**
     * Imposta il valore della proprietà motivoCancellazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoCancellazione(String value) {
        this.motivoCancellazione = value;
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
     * Recupera il valore della proprietà testoFoglioTrasmissione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestoFoglioTrasmissione() {
        return testoFoglioTrasmissione;
    }

    /**
     * Imposta il valore della proprietà testoFoglioTrasmissione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestoFoglioTrasmissione(String value) {
        this.testoFoglioTrasmissione = value;
    }

    /**
     * Recupera il valore della proprietà ruolo.
     * 
     * @return
     *     possible object is
     *     {@link DecodificaType }
     *     
     */
    public DecodificaType getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il valore della proprietà ruolo.
     * 
     * @param value
     *     allowed object is
     *     {@link DecodificaType }
     *     
     */
    public void setRuolo(DecodificaType value) {
        this.ruolo = value;
    }

    /**
     * Recupera il valore della proprietà idSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdSoggetto() {
        return idSoggetto;
    }

    /**
     * Imposta il valore della proprietà idSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdSoggetto(ObjectIdType value) {
        this.idSoggetto = value;
    }

    /**
     * Recupera il valore della proprietà idRegistrazione.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdRegistrazione() {
        return idRegistrazione;
    }

    /**
     * Imposta il valore della proprietà idRegistrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdRegistrazione(ObjectIdType value) {
        this.idRegistrazione = value;
    }

}
