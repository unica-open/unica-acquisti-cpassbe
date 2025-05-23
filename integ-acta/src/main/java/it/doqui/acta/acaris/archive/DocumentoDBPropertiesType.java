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
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per DocumentoDBPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DocumentoDBPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}DocumentoPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="dataUltimoAggiornam" type="{archive.acaris.acta.doqui.it}DataUltimoAggiornamType"/&amp;gt;
 *         &amp;lt;element name="attivo" type="{archive.acaris.acta.doqui.it}AttivoType"/&amp;gt;
 *         &amp;lt;element name="ubicazione" type="{archive.acaris.acta.doqui.it}UbicazioneType"/&amp;gt;
 *         &amp;lt;element name="versioneSW" type="{archive.acaris.acta.doqui.it}VersioneSWType"/&amp;gt;
 *         &amp;lt;element name="tipoDocFisico" type="{archive.acaris.acta.doqui.it}enumTipoDocumentoType"/&amp;gt;
 *         &amp;lt;element name="composizione" type="{archive.acaris.acta.doqui.it}enumDocPrimarioType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoDBPropertiesType", propOrder = {
    "dataUltimoAggiornam",
    "attivo",
    "ubicazione",
    "versioneSW",
    "tipoDocFisico",
    "composizione"
})
public class DocumentoDBPropertiesType
    extends DocumentoPropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataUltimoAggiornam;
    protected boolean attivo;
    @XmlElement(required = true)
    protected String ubicazione;
    @XmlElement(required = true)
    protected String versioneSW;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipoDocumentoType tipoDocFisico;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumDocPrimarioType composizione;

    /**
     * Recupera il valore della proprietà dataUltimoAggiornam.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataUltimoAggiornam() {
        return dataUltimoAggiornam;
    }

    /**
     * Imposta il valore della proprietà dataUltimoAggiornam.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataUltimoAggiornam(XMLGregorianCalendar value) {
        this.dataUltimoAggiornam = value;
    }

    /**
     * Recupera il valore della proprietà attivo.
     * 
     */
    public boolean isAttivo() {
        return attivo;
    }

    /**
     * Imposta il valore della proprietà attivo.
     * 
     */
    public void setAttivo(boolean value) {
        this.attivo = value;
    }

    /**
     * Recupera il valore della proprietà ubicazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUbicazione() {
        return ubicazione;
    }

    /**
     * Imposta il valore della proprietà ubicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUbicazione(String value) {
        this.ubicazione = value;
    }

    /**
     * Recupera il valore della proprietà versioneSW.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersioneSW() {
        return versioneSW;
    }

    /**
     * Imposta il valore della proprietà versioneSW.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersioneSW(String value) {
        this.versioneSW = value;
    }

    /**
     * Recupera il valore della proprietà tipoDocFisico.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoDocumentoType }
     *     
     */
    public EnumTipoDocumentoType getTipoDocFisico() {
        return tipoDocFisico;
    }

    /**
     * Imposta il valore della proprietà tipoDocFisico.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoDocumentoType }
     *     
     */
    public void setTipoDocFisico(EnumTipoDocumentoType value) {
        this.tipoDocFisico = value;
    }

    /**
     * Recupera il valore della proprietà composizione.
     * 
     * @return
     *     possible object is
     *     {@link EnumDocPrimarioType }
     *     
     */
    public EnumDocPrimarioType getComposizione() {
        return composizione;
    }

    /**
     * Imposta il valore della proprietà composizione.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumDocPrimarioType }
     *     
     */
    public void setComposizione(EnumDocPrimarioType value) {
        this.composizione = value;
    }

}
