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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PrincipalIdType;


/**
 * &lt;p&gt;Classe Java per anonymous complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="repositoryId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="principalId" type="{common.acaris.acta.doqui.it}PrincipalIdType"/&amp;gt;
 *         &amp;lt;element name="identificatoreRegistrazione" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="identificatoreSegnatura" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoMessaggio" type="{officialbookservice.acaris.acta.doqui.it}enumTipoMessaggio" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="maxItems" type="{common.acaris.acta.doqui.it}integer" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="skipCount" type="{common.acaris.acta.doqui.it}integer" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "repositoryId",
    "principalId",
    "identificatoreRegistrazione",
    "identificatoreSegnatura",
    "tipoMessaggio",
    "maxItems",
    "skipCount"
})
@XmlRootElement(name = "ricercaDatiInterscambio")
public class RicercaDatiInterscambio {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected PrincipalIdType principalId;
    protected ObjectIdType identificatoreRegistrazione;
    protected ObjectIdType identificatoreSegnatura;
    @XmlSchemaType(name = "string")
    protected EnumTipoMessaggio tipoMessaggio;
    protected Integer maxItems;
    protected Integer skipCount;

    /**
     * Recupera il valore della proprietà repositoryId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getRepositoryId() {
        return repositoryId;
    }

    /**
     * Imposta il valore della proprietà repositoryId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setRepositoryId(ObjectIdType value) {
        this.repositoryId = value;
    }

    /**
     * Recupera il valore della proprietà principalId.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalIdType }
     *     
     */
    public PrincipalIdType getPrincipalId() {
        return principalId;
    }

    /**
     * Imposta il valore della proprietà principalId.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalIdType }
     *     
     */
    public void setPrincipalId(PrincipalIdType value) {
        this.principalId = value;
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

    /**
     * Recupera il valore della proprietà identificatoreSegnatura.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdentificatoreSegnatura() {
        return identificatoreSegnatura;
    }

    /**
     * Imposta il valore della proprietà identificatoreSegnatura.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdentificatoreSegnatura(ObjectIdType value) {
        this.identificatoreSegnatura = value;
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
     * Recupera il valore della proprietà maxItems.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxItems() {
        return maxItems;
    }

    /**
     * Imposta il valore della proprietà maxItems.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxItems(Integer value) {
        this.maxItems = value;
    }

    /**
     * Recupera il valore della proprietà skipCount.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSkipCount() {
        return skipCount;
    }

    /**
     * Imposta il valore della proprietà skipCount.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSkipCount(Integer value) {
        this.skipCount = value;
    }

}
