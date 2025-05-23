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

package it.rer.intercenter.notier.services._1_0.inviodocumento.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per MetadatiBusdoxType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MetadatiBusdoxType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RecipientIdentifier" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}TokenNVMax254Type"/&gt;
 *         &lt;element name="SenderIdentifier" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}TokenNVMax254Type"/&gt;
 *         &lt;element name="DocumentIdentifier" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}TokenNVMax1024Type"/&gt;
 *         &lt;element name="ProcessIdentifier" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}TokenNVMax1024Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MetadatiBusdoxType", propOrder = {
    "recipientIdentifier",
    "senderIdentifier",
    "documentIdentifier",
    "processIdentifier"
})
public class MetadatiBusdoxType {

    @XmlElement(name = "RecipientIdentifier", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String recipientIdentifier;
    @XmlElement(name = "SenderIdentifier", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String senderIdentifier;
    @XmlElement(name = "DocumentIdentifier", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String documentIdentifier;
    @XmlElement(name = "ProcessIdentifier", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String processIdentifier;

    /**
     * Recupera il valore della proprietà recipientIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientIdentifier() {
        return recipientIdentifier;
    }

    /**
     * Imposta il valore della proprietà recipientIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientIdentifier(String value) {
        this.recipientIdentifier = value;
    }

    /**
     * Recupera il valore della proprietà senderIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderIdentifier() {
        return senderIdentifier;
    }

    /**
     * Imposta il valore della proprietà senderIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderIdentifier(String value) {
        this.senderIdentifier = value;
    }

    /**
     * Recupera il valore della proprietà documentIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentIdentifier() {
        return documentIdentifier;
    }

    /**
     * Imposta il valore della proprietà documentIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentIdentifier(String value) {
        this.documentIdentifier = value;
    }

    /**
     * Recupera il valore della proprietà processIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessIdentifier() {
        return processIdentifier;
    }

    /**
     * Imposta il valore della proprietà processIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessIdentifier(String value) {
        this.processIdentifier = value;
    }

}
