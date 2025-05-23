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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per PropertyFilterConfigurationInfoType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PropertyFilterConfigurationInfoType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="operation" type="{common.acaris.acta.doqui.it}enumPropertyFilterOperation"/&amp;gt;
 *         &amp;lt;element name="isAllAllowed" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="isNoneAllowed" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="isListAllowed" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyFilterConfigurationInfoType", propOrder = {
    "operation",
    "isAllAllowed",
    "isNoneAllowed",
    "isListAllowed"
})
public class PropertyFilterConfigurationInfoType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPropertyFilterOperation operation;
    protected boolean isAllAllowed;
    protected boolean isNoneAllowed;
    protected boolean isListAllowed;

    /**
     * Recupera il valore della proprietà operation.
     * 
     * @return
     *     possible object is
     *     {@link EnumPropertyFilterOperation }
     *     
     */
    public EnumPropertyFilterOperation getOperation() {
        return operation;
    }

    /**
     * Imposta il valore della proprietà operation.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPropertyFilterOperation }
     *     
     */
    public void setOperation(EnumPropertyFilterOperation value) {
        this.operation = value;
    }

    /**
     * Recupera il valore della proprietà isAllAllowed.
     * 
     */
    public boolean isIsAllAllowed() {
        return isAllAllowed;
    }

    /**
     * Imposta il valore della proprietà isAllAllowed.
     * 
     */
    public void setIsAllAllowed(boolean value) {
        this.isAllAllowed = value;
    }

    /**
     * Recupera il valore della proprietà isNoneAllowed.
     * 
     */
    public boolean isIsNoneAllowed() {
        return isNoneAllowed;
    }

    /**
     * Imposta il valore della proprietà isNoneAllowed.
     * 
     */
    public void setIsNoneAllowed(boolean value) {
        this.isNoneAllowed = value;
    }

    /**
     * Recupera il valore della proprietà isListAllowed.
     * 
     */
    public boolean isIsListAllowed() {
        return isListAllowed;
    }

    /**
     * Imposta il valore della proprietà isListAllowed.
     * 
     */
    public void setIsListAllowed(boolean value) {
        this.isListAllowed = value;
    }

}
