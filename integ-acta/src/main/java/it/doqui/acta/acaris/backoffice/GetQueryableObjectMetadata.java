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

package it.doqui.acta.acaris.backoffice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.EnumPropertyFilterOperation;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.QueryableObjectType;


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
 *         &amp;lt;element name="queryableObject" type="{common.acaris.acta.doqui.it}QueryableObjectType"/&amp;gt;
 *         &amp;lt;element name="operation" type="{common.acaris.acta.doqui.it}enumPropertyFilterOperation"/&amp;gt;
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
    "queryableObject",
    "operation"
})
@XmlRootElement(name = "getQueryableObjectMetadata")
public class GetQueryableObjectMetadata {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected QueryableObjectType queryableObject;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPropertyFilterOperation operation;

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
     * Recupera il valore della proprietà queryableObject.
     * 
     * @return
     *     possible object is
     *     {@link QueryableObjectType }
     *     
     */
    public QueryableObjectType getQueryableObject() {
        return queryableObject;
    }

    /**
     * Imposta il valore della proprietà queryableObject.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryableObjectType }
     *     
     */
    public void setQueryableObject(QueryableObjectType value) {
        this.queryableObject = value;
    }

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

}
