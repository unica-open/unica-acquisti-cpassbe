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
 * &lt;p&gt;Classe Java per DocumentoRegistroPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DocumentoRegistroPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}DocumentoPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="codiceRegistro" type="{archive.acaris.acta.doqui.it}CodiceRegistroType"/&amp;gt;
 *         &amp;lt;element name="annoRegistro" type="{archive.acaris.acta.doqui.it}AnnoRegistroType"/&amp;gt;
 *         &amp;lt;element name="numeroPrimaRegistrazione" type="{archive.acaris.acta.doqui.it}NumeroPrimaRegistrazioneType"/&amp;gt;
 *         &amp;lt;element name="dataPrimaRegistrazione" type="{archive.acaris.acta.doqui.it}DataPrimaRegistrazioneType"/&amp;gt;
 *         &amp;lt;element name="numeroUltimaRegistrazione" type="{archive.acaris.acta.doqui.it}NumeroUltimaRegistrazioneType"/&amp;gt;
 *         &amp;lt;element name="dataUltimaRegistrazione" type="{archive.acaris.acta.doqui.it}DataUltimaRegistrazioneType"/&amp;gt;
 *         &amp;lt;element name="dataApertura" type="{archive.acaris.acta.doqui.it}DataAperturaType"/&amp;gt;
 *         &amp;lt;element name="dataChiusura" type="{archive.acaris.acta.doqui.it}DataChiusuraType"/&amp;gt;
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
@XmlType(name = "DocumentoRegistroPropertiesType", propOrder = {
    "codiceRegistro",
    "annoRegistro",
    "numeroPrimaRegistrazione",
    "dataPrimaRegistrazione",
    "numeroUltimaRegistrazione",
    "dataUltimaRegistrazione",
    "dataApertura",
    "dataChiusura",
    "ubicazione",
    "versioneSW",
    "tipoDocFisico",
    "composizione"
})
public class DocumentoRegistroPropertiesType
    extends DocumentoPropertiesType
{

    @XmlElement(required = true)
    protected String codiceRegistro;
    protected int annoRegistro;
    @XmlElement(required = true)
    protected String numeroPrimaRegistrazione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataPrimaRegistrazione;
    @XmlElement(required = true)
    protected String numeroUltimaRegistrazione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataUltimaRegistrazione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataApertura;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataChiusura;
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
     * Recupera il valore della proprietà codiceRegistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRegistro() {
        return codiceRegistro;
    }

    /**
     * Imposta il valore della proprietà codiceRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRegistro(String value) {
        this.codiceRegistro = value;
    }

    /**
     * Recupera il valore della proprietà annoRegistro.
     * 
     */
    public int getAnnoRegistro() {
        return annoRegistro;
    }

    /**
     * Imposta il valore della proprietà annoRegistro.
     * 
     */
    public void setAnnoRegistro(int value) {
        this.annoRegistro = value;
    }

    /**
     * Recupera il valore della proprietà numeroPrimaRegistrazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroPrimaRegistrazione() {
        return numeroPrimaRegistrazione;
    }

    /**
     * Imposta il valore della proprietà numeroPrimaRegistrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroPrimaRegistrazione(String value) {
        this.numeroPrimaRegistrazione = value;
    }

    /**
     * Recupera il valore della proprietà dataPrimaRegistrazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataPrimaRegistrazione() {
        return dataPrimaRegistrazione;
    }

    /**
     * Imposta il valore della proprietà dataPrimaRegistrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataPrimaRegistrazione(XMLGregorianCalendar value) {
        this.dataPrimaRegistrazione = value;
    }

    /**
     * Recupera il valore della proprietà numeroUltimaRegistrazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroUltimaRegistrazione() {
        return numeroUltimaRegistrazione;
    }

    /**
     * Imposta il valore della proprietà numeroUltimaRegistrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroUltimaRegistrazione(String value) {
        this.numeroUltimaRegistrazione = value;
    }

    /**
     * Recupera il valore della proprietà dataUltimaRegistrazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataUltimaRegistrazione() {
        return dataUltimaRegistrazione;
    }

    /**
     * Imposta il valore della proprietà dataUltimaRegistrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataUltimaRegistrazione(XMLGregorianCalendar value) {
        this.dataUltimaRegistrazione = value;
    }

    /**
     * Recupera il valore della proprietà dataApertura.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataApertura() {
        return dataApertura;
    }

    /**
     * Imposta il valore della proprietà dataApertura.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataApertura(XMLGregorianCalendar value) {
        this.dataApertura = value;
    }

    /**
     * Recupera il valore della proprietà dataChiusura.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataChiusura() {
        return dataChiusura;
    }

    /**
     * Imposta il valore della proprietà dataChiusura.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataChiusura(XMLGregorianCalendar value) {
        this.dataChiusura = value;
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
