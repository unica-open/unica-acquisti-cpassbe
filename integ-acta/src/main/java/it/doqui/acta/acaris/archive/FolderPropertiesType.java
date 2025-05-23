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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.AllowedChildObjectTypeIdsType;
import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per FolderPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="FolderPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}ArchivePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="objectTypeId" type="{archive.acaris.acta.doqui.it}enumArchiveObjectType"/&amp;gt;
 *         &amp;lt;element name="parentId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="allowedChildObjectTypeIds" type="{common.acaris.acta.doqui.it}AllowedChildObjectTypeIdsType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FolderPropertiesType", propOrder = {
    "objectTypeId",
    "parentId",
    "allowedChildObjectTypeIds"
})
@XmlSeeAlso({
    TitolarioPropertiesType.class,
    ClassificazionePropertiesType.class,
    VocePropertiesType.class,
    FascicoloTemporaneoPropertiesType.class,
    DocumentoFisicoPropertiesType.class,
    GruppoAllegatiPropertiesType.class,
    FascicoloStandardType.class,
    AggregazionePropertiesType.class
})
public abstract class FolderPropertiesType
    extends ArchivePropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumArchiveObjectType objectTypeId;
    @XmlElement(required = true)
    protected ObjectIdType parentId;
    @XmlElement(required = true)
    protected AllowedChildObjectTypeIdsType allowedChildObjectTypeIds;

    /**
     * Recupera il valore della proprietà objectTypeId.
     * 
     * @return
     *     possible object is
     *     {@link EnumArchiveObjectType }
     *     
     */
    public EnumArchiveObjectType getObjectTypeId() {
        return objectTypeId;
    }

    /**
     * Imposta il valore della proprietà objectTypeId.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumArchiveObjectType }
     *     
     */
    public void setObjectTypeId(EnumArchiveObjectType value) {
        this.objectTypeId = value;
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
     * Recupera il valore della proprietà allowedChildObjectTypeIds.
     * 
     * @return
     *     possible object is
     *     {@link AllowedChildObjectTypeIdsType }
     *     
     */
    public AllowedChildObjectTypeIdsType getAllowedChildObjectTypeIds() {
        return allowedChildObjectTypeIds;
    }

    /**
     * Imposta il valore della proprietà allowedChildObjectTypeIds.
     * 
     * @param value
     *     allowed object is
     *     {@link AllowedChildObjectTypeIdsType }
     *     
     */
    public void setAllowedChildObjectTypeIds(AllowedChildObjectTypeIdsType value) {
        this.allowedChildObjectTypeIds = value;
    }

}
