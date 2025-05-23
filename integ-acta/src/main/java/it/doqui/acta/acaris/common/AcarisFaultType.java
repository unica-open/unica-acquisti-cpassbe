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
 * &lt;p&gt;Classe Java per acarisFaultType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="acarisFaultType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="exceptionType" type="{common.acaris.acta.doqui.it}enumServiceException"/&amp;gt;
 *         &amp;lt;element name="errorCode" type="{common.acaris.acta.doqui.it}enumErrorCodeType"/&amp;gt;
 *         &amp;lt;element name="technicalInfo" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="objectId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="className" type="{common.acaris.acta.doqui.it}enumObjectType"/&amp;gt;
 *         &amp;lt;element name="propertyName" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acarisFaultType", propOrder = {
    "exceptionType",
    "errorCode",
    "technicalInfo",
    "objectId",
    "className",
    "propertyName"
})
public class AcarisFaultType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumServiceException exceptionType;
    @XmlElement(required = true)
    protected String errorCode;
    @XmlElement(required = true)
    protected String technicalInfo;
    @XmlElement(required = true)
    protected ObjectIdType objectId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumObjectType className;
    @XmlElement(required = true)
    protected String propertyName;

    /**
     * Recupera il valore della proprietà exceptionType.
     * 
     * @return
     *     possible object is
     *     {@link EnumServiceException }
     *     
     */
    public EnumServiceException getExceptionType() {
        return exceptionType;
    }

    /**
     * Imposta il valore della proprietà exceptionType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumServiceException }
     *     
     */
    public void setExceptionType(EnumServiceException value) {
        this.exceptionType = value;
    }

    /**
     * Recupera il valore della proprietà errorCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Imposta il valore della proprietà errorCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Recupera il valore della proprietà technicalInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicalInfo() {
        return technicalInfo;
    }

    /**
     * Imposta il valore della proprietà technicalInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicalInfo(String value) {
        this.technicalInfo = value;
    }

    /**
     * Recupera il valore della proprietà objectId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getObjectId() {
        return objectId;
    }

    /**
     * Imposta il valore della proprietà objectId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setObjectId(ObjectIdType value) {
        this.objectId = value;
    }

    /**
     * Recupera il valore della proprietà className.
     * 
     * @return
     *     possible object is
     *     {@link EnumObjectType }
     *     
     */
    public EnumObjectType getClassName() {
        return className;
    }

    /**
     * Imposta il valore della proprietà className.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumObjectType }
     *     
     */
    public void setClassName(EnumObjectType value) {
        this.className = value;
    }

    /**
     * Recupera il valore della proprietà propertyName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Imposta il valore della proprietà propertyName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyName(String value) {
        this.propertyName = value;
    }

}
