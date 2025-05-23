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

package it.doqui.acta.acaris.documentservice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.EnumStreamId;
import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per ContenutoFisicoIdMap complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ContenutoFisicoIdMap"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{documentservice.acaris.acta.doqui.it}MappaIdentificazioneDocumento"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="contenutoFisicoId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="documentoFisicoId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="contentStreamId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="tipoContenuto" type="{common.acaris.acta.doqui.it}enumStreamId"/&amp;gt;
 *         &amp;lt;element name="verifyReportList" type="{common.acaris.acta.doqui.it}ObjectIdType" maxOccurs="unbounded"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContenutoFisicoIdMap", propOrder = {
    "contenutoFisicoId",
    "documentoFisicoId",
    "contentStreamId",
    "tipoContenuto",
    "verifyReportList"
})
public class ContenutoFisicoIdMap
    extends MappaIdentificazioneDocumento
{

    @XmlElement(required = true)
    protected ObjectIdType contenutoFisicoId;
    @XmlElement(required = true)
    protected ObjectIdType documentoFisicoId;
    @XmlElement(required = true)
    protected ObjectIdType contentStreamId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumStreamId tipoContenuto;
    @XmlElement(required = true)
    protected List<ObjectIdType> verifyReportList;

    /**
     * Recupera il valore della proprietà contenutoFisicoId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getContenutoFisicoId() {
        return contenutoFisicoId;
    }

    /**
     * Imposta il valore della proprietà contenutoFisicoId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setContenutoFisicoId(ObjectIdType value) {
        this.contenutoFisicoId = value;
    }

    /**
     * Recupera il valore della proprietà documentoFisicoId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getDocumentoFisicoId() {
        return documentoFisicoId;
    }

    /**
     * Imposta il valore della proprietà documentoFisicoId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setDocumentoFisicoId(ObjectIdType value) {
        this.documentoFisicoId = value;
    }

    /**
     * Recupera il valore della proprietà contentStreamId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getContentStreamId() {
        return contentStreamId;
    }

    /**
     * Imposta il valore della proprietà contentStreamId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setContentStreamId(ObjectIdType value) {
        this.contentStreamId = value;
    }

    /**
     * Recupera il valore della proprietà tipoContenuto.
     * 
     * @return
     *     possible object is
     *     {@link EnumStreamId }
     *     
     */
    public EnumStreamId getTipoContenuto() {
        return tipoContenuto;
    }

    /**
     * Imposta il valore della proprietà tipoContenuto.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumStreamId }
     *     
     */
    public void setTipoContenuto(EnumStreamId value) {
        this.tipoContenuto = value;
    }

    /**
     * Gets the value of the verifyReportList property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the verifyReportList property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getVerifyReportList().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectIdType }
     * 
     * 
     */
    public List<ObjectIdType> getVerifyReportList() {
        if (verifyReportList == null) {
            verifyReportList = new ArrayList<ObjectIdType>();
        }
        return this.verifyReportList;
    }

}
