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

package it.doqui.acta.acaris.documentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per CollocazioneDocumento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CollocazioneDocumento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="classificazioneId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="parentId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="parentFolderType" type="{documentservice.acaris.acta.doqui.it}enumParentFolder"/&amp;gt;
 *         &amp;lt;element name="classificazionePrincipaleId" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="gruppoAllegatiId" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollocazioneDocumento", propOrder = {
    "classificazioneId",
    "parentId",
    "parentFolderType",
    "classificazionePrincipaleId",
    "gruppoAllegatiId"
})
public class CollocazioneDocumento {

    @XmlElement(required = true)
    protected ObjectIdType classificazioneId;
    @XmlElement(required = true)
    protected ObjectIdType parentId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumParentFolder parentFolderType;
    protected ObjectIdType classificazionePrincipaleId;
    protected ObjectIdType gruppoAllegatiId;

    /**
     * Recupera il valore della proprietà classificazioneId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getClassificazioneId() {
        return classificazioneId;
    }

    /**
     * Imposta il valore della proprietà classificazioneId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setClassificazioneId(ObjectIdType value) {
        this.classificazioneId = value;
    }

    /**
     * Recupera il valore della proprietà parentId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getParentId() {
        return parentId;
    }

    /**
     * Imposta il valore della proprietà parentId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setParentId(ObjectIdType value) {
        this.parentId = value;
    }

    /**
     * Recupera il valore della proprietà parentFolderType.
     * 
     * @return
     *     possible object is
     *     {@link EnumParentFolder }
     *     
     */
    public EnumParentFolder getParentFolderType() {
        return parentFolderType;
    }

    /**
     * Imposta il valore della proprietà parentFolderType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumParentFolder }
     *     
     */
    public void setParentFolderType(EnumParentFolder value) {
        this.parentFolderType = value;
    }

    /**
     * Recupera il valore della proprietà classificazionePrincipaleId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getClassificazionePrincipaleId() {
        return classificazionePrincipaleId;
    }

    /**
     * Imposta il valore della proprietà classificazionePrincipaleId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setClassificazionePrincipaleId(ObjectIdType value) {
        this.classificazionePrincipaleId = value;
    }

    /**
     * Recupera il valore della proprietà gruppoAllegatiId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getGruppoAllegatiId() {
        return gruppoAllegatiId;
    }

    /**
     * Imposta il valore della proprietà gruppoAllegatiId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setGruppoAllegatiId(ObjectIdType value) {
        this.gruppoAllegatiId = value;
    }

}
