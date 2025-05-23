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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per PropertyFilterType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PropertyFilterType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="filterType" type="{common.acaris.acta.doqui.it}enumPropertyFilter"/&amp;gt;
 *         &amp;lt;element name="propertyList" type="{common.acaris.acta.doqui.it}QueryNameType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyFilterType", propOrder = {
    "filterType",
    "propertyList"
})
public class PropertyFilterType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPropertyFilter filterType;
    protected List<QueryNameType> propertyList;

    /**
     * Recupera il valore della proprietà filterType.
     * 
     * @return
     *     possible object is
     *     {@link EnumPropertyFilter }
     *     
     */
    public EnumPropertyFilter getFilterType() {
        return filterType;
    }

    /**
     * Imposta il valore della proprietà filterType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPropertyFilter }
     *     
     */
    public void setFilterType(EnumPropertyFilter value) {
        this.filterType = value;
    }

    /**
     * Gets the value of the propertyList property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the propertyList property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getPropertyList().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link QueryNameType }
     * 
     * 
     */
    public List<QueryNameType> getPropertyList() {
        if (propertyList == null) {
            propertyList = new ArrayList<QueryNameType>();
        }
        return this.propertyList;
    }

}
