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

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per DatoInterscambio complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DatoInterscambio"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="objectIdMessaggio" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="objectIdMessaggioInviato" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoMessaggio" type="{officialbookservice.acaris.acta.doqui.it}enumTipoMessaggio"/&amp;gt;
 *         &amp;lt;element name="statoPecInviata" type="{officialbookservice.acaris.acta.doqui.it}enumStatoPec" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="oggettoMessaggio" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="mittenteMessaggio" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="destinatariMessaggio" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="destinatariAccettato" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="destinatariAvvenutaConsegna" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="destinatariErroreConsegna" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="perContoDi" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataInvioMessaggio" type="{common.acaris.acta.doqui.it}timeStamp" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataRicezioneMessaggio" type="{common.acaris.acta.doqui.it}timeStamp" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="identificatoreRegistrazione" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatoInterscambio", propOrder = {
    "objectIdMessaggio",
    "objectIdMessaggioInviato",
    "tipoMessaggio",
    "statoPecInviata",
    "oggettoMessaggio",
    "mittenteMessaggio",
    "destinatariMessaggio",
    "destinatariAccettato",
    "destinatariAvvenutaConsegna",
    "destinatariErroreConsegna",
    "perContoDi",
    "dataInvioMessaggio",
    "dataRicezioneMessaggio",
    "identificatoreRegistrazione"
})
public class DatoInterscambio {

    @XmlElement(required = true)
    protected ObjectIdType objectIdMessaggio;
    protected ObjectIdType objectIdMessaggioInviato;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipoMessaggio tipoMessaggio;
    @XmlSchemaType(name = "string")
    protected EnumStatoPec statoPecInviata;
    @XmlElement(required = true)
    protected String oggettoMessaggio;
    @XmlElement(required = true)
    protected String mittenteMessaggio;
    @XmlElement(required = true)
    protected String destinatariMessaggio;
    protected String destinatariAccettato;
    protected String destinatariAvvenutaConsegna;
    protected String destinatariErroreConsegna;
    protected String perContoDi;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInvioMessaggio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRicezioneMessaggio;
    @XmlElement(required = true)
    protected ObjectIdType identificatoreRegistrazione;

    /**
     * Recupera il valore della proprietà objectIdMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getObjectIdMessaggio() {
        return objectIdMessaggio;
    }

    /**
     * Imposta il valore della proprietà objectIdMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setObjectIdMessaggio(ObjectIdType value) {
        this.objectIdMessaggio = value;
    }

    /**
     * Recupera il valore della proprietà objectIdMessaggioInviato.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getObjectIdMessaggioInviato() {
        return objectIdMessaggioInviato;
    }

    /**
     * Imposta il valore della proprietà objectIdMessaggioInviato.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setObjectIdMessaggioInviato(ObjectIdType value) {
        this.objectIdMessaggioInviato = value;
    }

    /**
     * Recupera il valore della proprietà tipoMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoMessaggio }
     *     
     */
    public EnumTipoMessaggio getTipoMessaggio() {
        return tipoMessaggio;
    }

    /**
     * Imposta il valore della proprietà tipoMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoMessaggio }
     *     
     */
    public void setTipoMessaggio(EnumTipoMessaggio value) {
        this.tipoMessaggio = value;
    }

    /**
     * Recupera il valore della proprietà statoPecInviata.
     * 
     * @return
     *     possible object is
     *     {@link EnumStatoPec }
     *     
     */
    public EnumStatoPec getStatoPecInviata() {
        return statoPecInviata;
    }

    /**
     * Imposta il valore della proprietà statoPecInviata.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumStatoPec }
     *     
     */
    public void setStatoPecInviata(EnumStatoPec value) {
        this.statoPecInviata = value;
    }

    /**
     * Recupera il valore della proprietà oggettoMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOggettoMessaggio() {
        return oggettoMessaggio;
    }

    /**
     * Imposta il valore della proprietà oggettoMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOggettoMessaggio(String value) {
        this.oggettoMessaggio = value;
    }

    /**
     * Recupera il valore della proprietà mittenteMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMittenteMessaggio() {
        return mittenteMessaggio;
    }

    /**
     * Imposta il valore della proprietà mittenteMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMittenteMessaggio(String value) {
        this.mittenteMessaggio = value;
    }

    /**
     * Recupera il valore della proprietà destinatariMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatariMessaggio() {
        return destinatariMessaggio;
    }

    /**
     * Imposta il valore della proprietà destinatariMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatariMessaggio(String value) {
        this.destinatariMessaggio = value;
    }

    /**
     * Recupera il valore della proprietà destinatariAccettato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatariAccettato() {
        return destinatariAccettato;
    }

    /**
     * Imposta il valore della proprietà destinatariAccettato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatariAccettato(String value) {
        this.destinatariAccettato = value;
    }

    /**
     * Recupera il valore della proprietà destinatariAvvenutaConsegna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatariAvvenutaConsegna() {
        return destinatariAvvenutaConsegna;
    }

    /**
     * Imposta il valore della proprietà destinatariAvvenutaConsegna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatariAvvenutaConsegna(String value) {
        this.destinatariAvvenutaConsegna = value;
    }

    /**
     * Recupera il valore della proprietà destinatariErroreConsegna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatariErroreConsegna() {
        return destinatariErroreConsegna;
    }

    /**
     * Imposta il valore della proprietà destinatariErroreConsegna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatariErroreConsegna(String value) {
        this.destinatariErroreConsegna = value;
    }

    /**
     * Recupera il valore della proprietà perContoDi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerContoDi() {
        return perContoDi;
    }

    /**
     * Imposta il valore della proprietà perContoDi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerContoDi(String value) {
        this.perContoDi = value;
    }

    /**
     * Recupera il valore della proprietà dataInvioMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInvioMessaggio() {
        return dataInvioMessaggio;
    }

    /**
     * Imposta il valore della proprietà dataInvioMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInvioMessaggio(XMLGregorianCalendar value) {
        this.dataInvioMessaggio = value;
    }

    /**
     * Recupera il valore della proprietà dataRicezioneMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRicezioneMessaggio() {
        return dataRicezioneMessaggio;
    }

    /**
     * Imposta il valore della proprietà dataRicezioneMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRicezioneMessaggio(XMLGregorianCalendar value) {
        this.dataRicezioneMessaggio = value;
    }

    /**
     * Recupera il valore della proprietà identificatoreRegistrazione.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdentificatoreRegistrazione() {
        return identificatoreRegistrazione;
    }

    /**
     * Imposta il valore della proprietà identificatoreRegistrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdentificatoreRegistrazione(ObjectIdType value) {
        this.identificatoreRegistrazione = value;
    }

}
