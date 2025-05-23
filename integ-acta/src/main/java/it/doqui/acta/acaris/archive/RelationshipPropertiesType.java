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

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per RelationshipPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RelationshipPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}ArchivePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="relationType" type="{archive.acaris.acta.doqui.it}enumRelationshipObjectType"/&amp;gt;
 *         &amp;lt;element name="sourceId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="sourceType" type="{archive.acaris.acta.doqui.it}enumArchiveObjectType"/&amp;gt;
 *         &amp;lt;element name="targetId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="targetType" type="{archive.acaris.acta.doqui.it}enumArchiveObjectType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationshipPropertiesType", propOrder = {
    "relationType",
    "sourceId",
    "sourceType",
    "targetId",
    "targetType"
})
@XmlSeeAlso({
    DocumentAssociationPropertiesType.class,
    HistoryModificheTecnichePropertiesType.class,
    DocumentCompositionPropertiesType.class,
    HistoryVecchieVersioniPropertiesType.class
})
public class RelationshipPropertiesType
    extends ArchivePropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumRelationshipObjectType relationType;
    @XmlElement(required = true)
    protected ObjectIdType sourceId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumArchiveObjectType sourceType;
    @XmlElement(required = true)
    protected ObjectIdType targetId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumArchiveObjectType targetType;

    /**
     * Recupera il valore della proprietà relationType.
     * 
     * @return
     *     possible object is
     *     {@link EnumRelationshipObjectType }
     *     
     */
    public EnumRelationshipObjectType getRelationType() {
        return relationType;
    }

    /**
     * Imposta il valore della proprietà relationType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumRelationshipObjectType }
     *     
     */
    public void setRelationType(EnumRelationshipObjectType value) {
        this.relationType = value;
    }

    /**
     * Recupera il valore della proprietà sourceId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getSourceId() {
        return sourceId;
    }

    /**
     * Imposta il valore della proprietà sourceId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setSourceId(ObjectIdType value) {
        this.sourceId = value;
    }

    /**
     * Recupera il valore della proprietà sourceType.
     * 
     * @return
     *     possible object is
     *     {@link EnumArchiveObjectType }
     *     
     */
    public EnumArchiveObjectType getSourceType() {
        return sourceType;
    }

    /**
     * Imposta il valore della proprietà sourceType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumArchiveObjectType }
     *     
     */
    public void setSourceType(EnumArchiveObjectType value) {
        this.sourceType = value;
    }

    /**
     * Recupera il valore della proprietà targetId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getTargetId() {
        return targetId;
    }

    /**
     * Imposta il valore della proprietà targetId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setTargetId(ObjectIdType value) {
        this.targetId = value;
    }

    /**
     * Recupera il valore della proprietà targetType.
     * 
     * @return
     *     possible object is
     *     {@link EnumArchiveObjectType }
     *     
     */
    public EnumArchiveObjectType getTargetType() {
        return targetType;
    }

    /**
     * Imposta il valore della proprietà targetType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumArchiveObjectType }
     *     
     */
    public void setTargetType(EnumArchiveObjectType value) {
        this.targetType = value;
    }

}
