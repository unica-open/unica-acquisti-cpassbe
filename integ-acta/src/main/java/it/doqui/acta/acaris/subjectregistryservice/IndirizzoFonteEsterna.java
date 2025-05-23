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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per IndirizzoFonteEsterna complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="IndirizzoFonteEsterna"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="indirizzo" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoIndirizzo" type="{subjectregistryservice.acaris.acta.doqui.it}enumTipoIndirizzoFonteEsterna" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="frazione" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="comune" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="provincia" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="siglaProvincia" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="regione" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="cap" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="stato" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndirizzoFonteEsterna", propOrder = {
    "indirizzo",
    "tipoIndirizzo",
    "frazione",
    "comune",
    "provincia",
    "siglaProvincia",
    "regione",
    "cap",
    "stato"
})
public class IndirizzoFonteEsterna {

    protected String indirizzo;
    @XmlSchemaType(name = "string")
    protected EnumTipoIndirizzoFonteEsterna tipoIndirizzo;
    protected String frazione;
    protected String comune;
    protected String provincia;
    protected String siglaProvincia;
    protected String regione;
    protected String cap;
    protected String stato;

    /**
     * Recupera il valore della proprietà indirizzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta il valore della proprietà indirizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzo(String value) {
        this.indirizzo = value;
    }

    /**
     * Recupera il valore della proprietà tipoIndirizzo.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoIndirizzoFonteEsterna }
     *     
     */
    public EnumTipoIndirizzoFonteEsterna getTipoIndirizzo() {
        return tipoIndirizzo;
    }

    /**
     * Imposta il valore della proprietà tipoIndirizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoIndirizzoFonteEsterna }
     *     
     */
    public void setTipoIndirizzo(EnumTipoIndirizzoFonteEsterna value) {
        this.tipoIndirizzo = value;
    }

    /**
     * Recupera il valore della proprietà frazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrazione() {
        return frazione;
    }

    /**
     * Imposta il valore della proprietà frazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrazione(String value) {
        this.frazione = value;
    }

    /**
     * Recupera il valore della proprietà comune.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComune() {
        return comune;
    }

    /**
     * Imposta il valore della proprietà comune.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComune(String value) {
        this.comune = value;
    }

    /**
     * Recupera il valore della proprietà provincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Imposta il valore della proprietà provincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Recupera il valore della proprietà siglaProvincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    /**
     * Imposta il valore della proprietà siglaProvincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaProvincia(String value) {
        this.siglaProvincia = value;
    }

    /**
     * Recupera il valore della proprietà regione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegione() {
        return regione;
    }

    /**
     * Imposta il valore della proprietà regione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegione(String value) {
        this.regione = value;
    }

    /**
     * Recupera il valore della proprietà cap.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCap() {
        return cap;
    }

    /**
     * Imposta il valore della proprietà cap.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCap(String value) {
        this.cap = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStato(String value) {
        this.stato = value;
    }

}
