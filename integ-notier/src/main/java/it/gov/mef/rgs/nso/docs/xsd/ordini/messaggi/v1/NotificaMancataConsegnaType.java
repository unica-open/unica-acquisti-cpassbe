/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.gov.mef.rgs.nso.docs.xsd.ordini.messaggi.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per NotificaMancataConsegna_Type complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="NotificaMancataConsegna_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Identificativo" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}Identificativo_Type"/&gt;
 *         &lt;element name="NomeFile" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}NomeFile_Type"/&gt;
 *         &lt;element name="DataOraRicezione" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="RiferimentoArchivio" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}RiferimentoArchivio_Type" minOccurs="0"/&gt;
 *         &lt;element name="Descrizione" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}Descrizione_Type" minOccurs="0"/&gt;
 *         &lt;element name="MessageId" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}MessageId_Type"/&gt;
 *         &lt;element name="PecMessageId" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}PecMessageId_Type" minOccurs="0"/&gt;
 *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="versione" use="required" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}Versione_Type" fixed="1.0" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificaMancataConsegna_Type", propOrder = {
    "identificativo",
    "nomeFile",
    "dataOraRicezione",
    "riferimentoArchivio",
    "descrizione",
    "messageId",
    "pecMessageId",
    "note"
})
public class NotificaMancataConsegnaType {

    @XmlElement(name = "Identificativo", required = true)
    protected String identificativo;
    @XmlElement(name = "NomeFile", required = true)
    protected String nomeFile;
    @XmlElement(name = "DataOraRicezione", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraRicezione;
    @XmlElement(name = "RiferimentoArchivio")
    protected RiferimentoArchivioType riferimentoArchivio;
    @XmlElement(name = "Descrizione")
    protected String descrizione;
    @XmlElement(name = "MessageId", required = true)
    protected String messageId;
    @XmlElement(name = "PecMessageId")
    protected String pecMessageId;
    @XmlElement(name = "Note")
    protected String note;
    @XmlAttribute(name = "versione", required = true)
    protected String versione;

    /**
     * Recupera il valore della proprietà identificativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativo() {
        return identificativo;
    }

    /**
     * Imposta il valore della proprietà identificativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativo(String value) {
        this.identificativo = value;
    }

    /**
     * Recupera il valore della proprietà nomeFile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeFile() {
        return nomeFile;
    }

    /**
     * Imposta il valore della proprietà nomeFile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeFile(String value) {
        this.nomeFile = value;
    }

    /**
     * Recupera il valore della proprietà dataOraRicezione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraRicezione() {
        return dataOraRicezione;
    }

    /**
     * Imposta il valore della proprietà dataOraRicezione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraRicezione(XMLGregorianCalendar value) {
        this.dataOraRicezione = value;
    }

    /**
     * Recupera il valore della proprietà riferimentoArchivio.
     * 
     * @return
     *     possible object is
     *     {@link RiferimentoArchivioType }
     *     
     */
    public RiferimentoArchivioType getRiferimentoArchivio() {
        return riferimentoArchivio;
    }

    /**
     * Imposta il valore della proprietà riferimentoArchivio.
     * 
     * @param value
     *     allowed object is
     *     {@link RiferimentoArchivioType }
     *     
     */
    public void setRiferimentoArchivio(RiferimentoArchivioType value) {
        this.riferimentoArchivio = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà messageId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Imposta il valore della proprietà messageId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Recupera il valore della proprietà pecMessageId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPecMessageId() {
        return pecMessageId;
    }

    /**
     * Imposta il valore della proprietà pecMessageId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPecMessageId(String value) {
        this.pecMessageId = value;
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
     * Recupera il valore della proprietà versione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersione() {
        if (versione == null) {
            return "1.0";
        } else {
            return versione;
        }
    }

    /**
     * Imposta il valore della proprietà versione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersione(String value) {
        this.versione = value;
    }

}
