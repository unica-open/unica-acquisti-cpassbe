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

package it.rer.intercenter.notier.services._1_0.notificamdn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per IdentificativoDocumento_Type complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="IdentificativoDocumento_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Urn" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}Urn_Type"/&gt;
 *         &lt;element name="NumeroDocumento" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}NumeroDocumento_Type"/&gt;
 *         &lt;element name="RecipientID" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}RecipientID_Type"/&gt;
 *         &lt;element name="DocumentID" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}DocumentID_Type"/&gt;
 *         &lt;element name="RiferimentoTemporale" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}DataOraTransitoDocumento_Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificativoDocumento_Type", propOrder = {
    "urn",
    "numeroDocumento",
    "recipientID",
    "documentID",
    "riferimentoTemporale"
})
public class IdentificativoDocumentoType {

    @XmlElement(name = "Urn", required = true)
    protected String urn;
    @XmlElement(name = "NumeroDocumento", required = true)
    protected String numeroDocumento;
    @XmlElement(name = "RecipientID", required = true)
    protected String recipientID;
    @XmlElement(name = "DocumentID", required = true)
    protected String documentID;
    @XmlElement(name = "RiferimentoTemporale")
    protected DataOraTransitoDocumentoType riferimentoTemporale;

    /**
     * Recupera il valore della proprietà urn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrn() {
        return urn;
    }

    /**
     * Imposta il valore della proprietà urn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrn(String value) {
        this.urn = value;
    }

    /**
     * Recupera il valore della proprietà numeroDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Imposta il valore della proprietà numeroDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

    /**
     * Recupera il valore della proprietà recipientID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientID() {
        return recipientID;
    }

    /**
     * Imposta il valore della proprietà recipientID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientID(String value) {
        this.recipientID = value;
    }

    /**
     * Recupera il valore della proprietà documentID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentID() {
        return documentID;
    }

    /**
     * Imposta il valore della proprietà documentID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentID(String value) {
        this.documentID = value;
    }

    /**
     * Recupera il valore della proprietà riferimentoTemporale.
     * 
     * @return
     *     possible object is
     *     {@link DataOraTransitoDocumentoType }
     *     
     */
    public DataOraTransitoDocumentoType getRiferimentoTemporale() {
        return riferimentoTemporale;
    }

    /**
     * Imposta il valore della proprietà riferimentoTemporale.
     * 
     * @param value
     *     allowed object is
     *     {@link DataOraTransitoDocumentoType }
     *     
     */
    public void setRiferimentoTemporale(DataOraTransitoDocumentoType value) {
        this.riferimentoTemporale = value;
    }

}
