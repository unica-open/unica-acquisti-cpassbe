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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PrincipalIdType;
import it.doqui.acta.acaris.common.PropertiesType;


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
 *         &amp;lt;element name="objectId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="principalId" type="{common.acaris.acta.doqui.it}PrincipalIdType"/&amp;gt;
 *         &amp;lt;element name="folderId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="associativeObjectTypeId" type="{archive.acaris.acta.doqui.it}enumFolderObjectType"/&amp;gt;
 *         &amp;lt;element name="associativeObjectProperties" type="{common.acaris.acta.doqui.it}PropertiesType"/&amp;gt;
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
    "objectId",
    "principalId",
    "folderId",
    "associativeObjectTypeId",
    "associativeObjectProperties"
})
@XmlRootElement(name = "addAssociativeObjectToFolder")
public class AddAssociativeObjectToFolder {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected ObjectIdType objectId;
    @XmlElement(required = true)
    protected PrincipalIdType principalId;
    @XmlElement(required = true)
    protected ObjectIdType folderId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumFolderObjectType associativeObjectTypeId;
    @XmlElement(required = true)
    protected PropertiesType associativeObjectProperties;

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
     * Recupera il valore della proprietà associativeObjectTypeId.
     * 
     * @return
     *     possible object is
     *     {@link EnumFolderObjectType }
     *     
     */
    public EnumFolderObjectType getAssociativeObjectTypeId() {
        return associativeObjectTypeId;
    }

    /**
     * Imposta il valore della proprietà associativeObjectTypeId.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumFolderObjectType }
     *     
     */
    public void setAssociativeObjectTypeId(EnumFolderObjectType value) {
        this.associativeObjectTypeId = value;
    }

    /**
     * Recupera il valore della proprietà associativeObjectProperties.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getAssociativeObjectProperties() {
        return associativeObjectProperties;
    }

    /**
     * Imposta il valore della proprietà associativeObjectProperties.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setAssociativeObjectProperties(PropertiesType value) {
        this.associativeObjectProperties = value;
    }

}
