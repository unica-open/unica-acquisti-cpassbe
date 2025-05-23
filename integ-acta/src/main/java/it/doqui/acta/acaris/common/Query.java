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

package it.doqui.acta.acaris.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &amp;lt;element name="target" type="{common.acaris.acta.doqui.it}QueryableObjectType"/&amp;gt;
 *         &amp;lt;element name="filter" type="{common.acaris.acta.doqui.it}PropertyFilterType"/&amp;gt;
 *         &amp;lt;element name="criteria" type="{common.acaris.acta.doqui.it}QueryConditionType" maxOccurs="unbounded"/&amp;gt;
 *         &amp;lt;element name="navigationLimits" type="{common.acaris.acta.doqui.it}NavigationConditionInfoType" minOccurs="0"/&amp;gt;
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
    "target",
    "filter",
    "criteria",
    "navigationLimits",
    "maxItems",
    "skipCount"
})
@XmlRootElement(name = "query")
public class Query {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected PrincipalIdType principalId;
    @XmlElement(required = true)
    protected QueryableObjectType target;
    @XmlElement(required = true)
    protected PropertyFilterType filter;
    @XmlElement(required = true)
    protected List<QueryConditionType> criteria;
    protected NavigationConditionInfoType navigationLimits;
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
     * Recupera il valore della proprietà target.
     * 
     * @return
     *     possible object is
     *     {@link QueryableObjectType }
     *     
     */
    public QueryableObjectType getTarget() {
        return target;
    }

    /**
     * Imposta il valore della proprietà target.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryableObjectType }
     *     
     */
    public void setTarget(QueryableObjectType value) {
        this.target = value;
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
     * Gets the value of the criteria property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the criteria property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCriteria().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link QueryConditionType }
     * 
     * 
     */
    public List<QueryConditionType> getCriteria() {
        if (criteria == null) {
            criteria = new ArrayList<QueryConditionType>();
        }
        return this.criteria;
    }

    /**
     * Recupera il valore della proprietà navigationLimits.
     * 
     * @return
     *     possible object is
     *     {@link NavigationConditionInfoType }
     *     
     */
    public NavigationConditionInfoType getNavigationLimits() {
        return navigationLimits;
    }

    /**
     * Imposta il valore della proprietà navigationLimits.
     * 
     * @param value
     *     allowed object is
     *     {@link NavigationConditionInfoType }
     *     
     */
    public void setNavigationLimits(NavigationConditionInfoType value) {
        this.navigationLimits = value;
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
