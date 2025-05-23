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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PrincipalIdType;
import it.doqui.acta.acaris.common.PropertyFilterType;


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
 *         &amp;lt;element name="folderId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="principalId" type="{common.acaris.acta.doqui.it}PrincipalIdType"/&amp;gt;
 *         &amp;lt;element name="depth" type="{common.acaris.acta.doqui.it}integer" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="filter" type="{common.acaris.acta.doqui.it}PropertyFilterType"/&amp;gt;
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
    "folderId",
    "principalId",
    "depth",
    "filter",
    "maxItems",
    "skipCount"
})
@XmlRootElement(name = "getDescendants")
public class GetDescendants {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected ObjectIdType folderId;
    @XmlElement(required = true)
    protected PrincipalIdType principalId;
    protected Integer depth;
    @XmlElement(required = true)
    protected PropertyFilterType filter;
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
     * Recupera il valore della proprietà folderId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getFolderId() {
        return folderId;
    }

    /**
     * Imposta il valore della proprietà folderId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setFolderId(ObjectIdType value) {
        this.folderId = value;
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
     * Recupera il valore della proprietà depth.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDepth() {
        return depth;
    }

    /**
     * Imposta il valore della proprietà depth.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDepth(Integer value) {
        this.depth = value;
    }

    /**
     * Recupera il valore della proprietà filter.
     * 
     * @return
     *     possible object is
     *     {@link PropertyFilterType }
     *     
     */
    public PropertyFilterType getFilter() {
        return filter;
    }

    /**
     * Imposta il valore della proprietà filter.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyFilterType }
     *     
     */
    public void setFilter(PropertyFilterType value) {
        this.filter = value;
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
