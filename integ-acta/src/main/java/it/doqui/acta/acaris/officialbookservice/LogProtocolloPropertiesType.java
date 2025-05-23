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

package it.doqui.acta.acaris.officialbookservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per LogProtocolloPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="LogProtocolloPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}OfficialBookPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="dataModifica" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="vecchioValore" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="idCampo" type="{common.acaris.acta.doqui.it}integer"/&amp;gt;
 *         &amp;lt;element name="idRegistroProtocollo" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idRegistrazione" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idUtenteCreazione" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogProtocolloPropertiesType", propOrder = {
    "dataModifica",
    "vecchioValore",
    "idCampo",
    "idRegistroProtocollo",
    "idRegistrazione",
    "idUtenteCreazione"
})
public class LogProtocolloPropertiesType
    extends OfficialBookPropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataModifica;
    @XmlElement(required = true)
    protected String vecchioValore;
    protected int idCampo;
    @XmlElement(required = true)
    protected ObjectIdType idRegistroProtocollo;
    @XmlElement(required = true)
    protected ObjectIdType idRegistrazione;
    @XmlElement(required = true)
    protected ObjectIdType idUtenteCreazione;

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
     * Recupera il valore della proprietà idRegistroProtocollo.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdRegistroProtocollo() {
        return idRegistroProtocollo;
    }

    /**
     * Imposta il valore della proprietà idRegistroProtocollo.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdRegistroProtocollo(ObjectIdType value) {
        this.idRegistroProtocollo = value;
    }

    /**
     * Recupera il valore della proprietà idRegistrazione.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdRegistrazione() {
        return idRegistrazione;
    }

    /**
     * Imposta il valore della proprietà idRegistrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdRegistrazione(ObjectIdType value) {
        this.idRegistrazione = value;
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

}
