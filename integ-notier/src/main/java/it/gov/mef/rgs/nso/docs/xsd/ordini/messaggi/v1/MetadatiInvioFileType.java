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

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per MetadatiInvioFile_Type complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MetadatiInvioFile_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Identificativo" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}Identificativo_Type"/&gt;
 *         &lt;element name="NomeFile" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}NomeFile_Type"/&gt;
 *         &lt;element name="CodiceDestinatario" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}CodiceDestinatario_Type"/&gt;
 *         &lt;element name="Formato" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}Formato_Type" minOccurs="0"/&gt;
 *         &lt;element name="TentativiInvio" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="MessageId" type="{http://nso.rgs.mef.gov.it/docs/xsd/ordini/messaggi/v1.0}MessageId_Type"/&gt;
 *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="HASH" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "MetadatiInvioFile_Type", propOrder = {
    "identificativo",
    "nomeFile",
    "codiceDestinatario",
    "formato",
    "tentativiInvio",
    "messageId",
    "note",
    "hash"
})
public class MetadatiInvioFileType {

    @XmlElement(name = "Identificativo", required = true)
    protected String identificativo;
    @XmlElement(name = "NomeFile", required = true)
    protected String nomeFile;
    @XmlElement(name = "CodiceDestinatario", required = true)
    protected String codiceDestinatario;
    @XmlElement(name = "Formato")
    protected String formato;
    @XmlElement(name = "TentativiInvio", required = true)
    protected BigInteger tentativiInvio;
    @XmlElement(name = "MessageId", required = true)
    protected String messageId;
    @XmlElement(name = "Note")
    protected String note;
    @XmlElement(name = "HASH", required = true)
    protected String hash;
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
     * Recupera il valore della proprietà codiceDestinatario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceDestinatario() {
        return codiceDestinatario;
    }

    /**
     * Imposta il valore della proprietà codiceDestinatario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceDestinatario(String value) {
        this.codiceDestinatario = value;
    }

    /**
     * Recupera il valore della proprietà formato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormato() {
        return formato;
    }

    /**
     * Imposta il valore della proprietà formato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormato(String value) {
        this.formato = value;
    }

    /**
     * Recupera il valore della proprietà tentativiInvio.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTentativiInvio() {
        return tentativiInvio;
    }

    /**
     * Imposta il valore della proprietà tentativiInvio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTentativiInvio(BigInteger value) {
        this.tentativiInvio = value;
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
     * Recupera il valore della proprietà hash.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHASH() {
        return hash;
    }

    /**
     * Imposta il valore della proprietà hash.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHASH(String value) {
        this.hash = value;
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
