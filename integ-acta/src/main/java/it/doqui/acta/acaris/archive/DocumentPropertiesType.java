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

import it.doqui.acta.acaris.common.EnumMimeTypeType;


/**
 * &lt;p&gt;Classe Java per DocumentPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DocumentPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}ArchivePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="objectTypeId" type="{archive.acaris.acta.doqui.it}enumArchiveObjectType"/&amp;gt;
 *         &amp;lt;element name="contentStreamLength" type="{common.acaris.acta.doqui.it}contentStreamLengthType"/&amp;gt;
 *         &amp;lt;element name="contentStreamMimeType" type="{common.acaris.acta.doqui.it}enumMimeTypeType"/&amp;gt;
 *         &amp;lt;element name="contentStreamFilename" type="{common.acaris.acta.doqui.it}contentStreamFilenameType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentPropertiesType", propOrder = {
    "objectTypeId",
    "contentStreamLength",
    "contentStreamMimeType",
    "contentStreamFilename"
})
@XmlSeeAlso({
    ClipsMetallicaPropertiesType.class,
    ContenutoFisicoPropertiesType.class,
    DocumentoPropertiesType.class
})
public abstract class DocumentPropertiesType
    extends ArchivePropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumArchiveObjectType objectTypeId;
    protected int contentStreamLength;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumMimeTypeType contentStreamMimeType;
    @XmlElement(required = true)
    protected String contentStreamFilename;

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
     * Recupera il valore della proprietà contentStreamLength.
     * 
     */
    public int getContentStreamLength() {
        return contentStreamLength;
    }

    /**
     * Imposta il valore della proprietà contentStreamLength.
     * 
     */
    public void setContentStreamLength(int value) {
        this.contentStreamLength = value;
    }

    /**
     * Recupera il valore della proprietà contentStreamMimeType.
     * 
     * @return
     *     possible object is
     *     {@link EnumMimeTypeType }
     *     
     */
    public EnumMimeTypeType getContentStreamMimeType() {
        return contentStreamMimeType;
    }

    /**
     * Imposta il valore della proprietà contentStreamMimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumMimeTypeType }
     *     
     */
    public void setContentStreamMimeType(EnumMimeTypeType value) {
        this.contentStreamMimeType = value;
    }

    /**
     * Recupera il valore della proprietà contentStreamFilename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentStreamFilename() {
        return contentStreamFilename;
    }

    /**
     * Imposta il valore della proprietà contentStreamFilename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentStreamFilename(String value) {
        this.contentStreamFilename = value;
    }

}
