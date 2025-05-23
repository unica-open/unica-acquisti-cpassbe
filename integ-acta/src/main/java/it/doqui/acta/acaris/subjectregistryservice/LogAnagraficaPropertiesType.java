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

package it.doqui.acta.acaris.subjectregistryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per LogAnagraficaPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LogAnagraficaPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{subjectregistryservice.acaris.acta.doqui.it}SubjectRegistryPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="dataModifica" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="vecchioValore" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="modificatoDaFonte" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="idCampo" type="{common.acaris.acta.doqui.it}integer"/&amp;gt;
 *         &amp;lt;element name="idUtenteCreazione" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idSoggetto" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogAnagraficaPropertiesType", propOrder = {
    "dataModifica",
    "vecchioValore",
    "modificatoDaFonte",
    "idCampo",
    "idUtenteCreazione",
    "idSoggetto"
})
public class LogAnagraficaPropertiesType
    extends SubjectRegistryPropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataModifica;
    @XmlElement(required = true)
    protected String vecchioValore;
    protected boolean modificatoDaFonte;
    protected int idCampo;
    @XmlElement(required = true)
    protected ObjectIdType idUtenteCreazione;
    @XmlElement(required = true)
    protected ObjectIdType idSoggetto;

    /**
     * Recupera il valore della proprietà dataModifica.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataModifica() {
        return dataModifica;
    }

    /**
     * Imposta il valore della proprietà dataModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataModifica(XMLGregorianCalendar value) {
        this.dataModifica = value;
    }

    /**
     * Recupera il valore della proprietà vecchioValore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVecchioValore() {
        return vecchioValore;
    }

    /**
     * Imposta il valore della proprietà vecchioValore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVecchioValore(String value) {
        this.vecchioValore = value;
    }

    /**
     * Recupera il valore della proprietà modificatoDaFonte.
     * 
     */
    public boolean isModificatoDaFonte() {
        return modificatoDaFonte;
    }

    /**
     * Imposta il valore della proprietà modificatoDaFonte.
     * 
     */
    public void setModificatoDaFonte(boolean value) {
        this.modificatoDaFonte = value;
    }

    /**
     * Recupera il valore della proprietà idCampo.
     * 
     */
    public int getIdCampo() {
        return idCampo;
    }

    /**
     * Imposta il valore della proprietà idCampo.
     * 
     */
    public void setIdCampo(int value) {
        this.idCampo = value;
    }

    /**
     * Recupera il valore della proprietà idUtenteCreazione.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdUtenteCreazione() {
        return idUtenteCreazione;
    }

    /**
     * Imposta il valore della proprietà idUtenteCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdUtenteCreazione(ObjectIdType value) {
        this.idUtenteCreazione = value;
    }

    /**
     * Recupera il valore della proprietà idSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdSoggetto() {
        return idSoggetto;
    }

    /**
     * Imposta il valore della proprietà idSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdSoggetto(ObjectIdType value) {
        this.idSoggetto = value;
    }

}
