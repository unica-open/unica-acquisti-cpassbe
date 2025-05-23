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

import it.doqui.acta.acaris.common.IdVitalRecordCodeType;


/**
 * &lt;p&gt;Classe Java per FormaDocumentariaType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="FormaDocumentariaType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="idFormaDocumentaria" type="{archive.acaris.acta.doqui.it}IdFormaDocumentariaType"/&amp;gt;
 *         &amp;lt;element name="descrizione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="firmato" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="dataFineValidita" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="daConservareSostitutiva" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="originale" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="unico" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="idVitalRecordCode" type="{common.acaris.acta.doqui.it}IdVitalRecordCodeType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormaDocumentariaType", propOrder = {
    "idFormaDocumentaria",
    "descrizione",
    "firmato",
    "dataFineValidita",
    "daConservareSostitutiva",
    "originale",
    "unico",
    "idVitalRecordCode"
})
public class FormaDocumentariaType {

    @XmlElement(required = true)
    protected IdFormaDocumentariaType idFormaDocumentaria;
    @XmlElement(required = true)
    protected String descrizione;
    protected boolean firmato;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    protected boolean daConservareSostitutiva;
    protected boolean originale;
    protected boolean unico;
    @XmlElement(required = true)
    protected IdVitalRecordCodeType idVitalRecordCode;

    /**
     * Recupera il valore della proprietà idFormaDocumentaria.
     * 
     * @return
     *     possible object is
     *     {@link IdFormaDocumentariaType }
     *     
     */
    public IdFormaDocumentariaType getIdFormaDocumentaria() {
        return idFormaDocumentaria;
    }

    /**
     * Imposta il valore della proprietà idFormaDocumentaria.
     * 
     * @param value
     *     allowed object is
     *     {@link IdFormaDocumentariaType }
     *     
     */
    public void setIdFormaDocumentaria(IdFormaDocumentariaType value) {
        this.idFormaDocumentaria = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà firmato.
     * 
     */
    public boolean isFirmato() {
        return firmato;
    }

    /**
     * Imposta il valore della proprietà firmato.
     * 
     */
    public void setFirmato(boolean value) {
        this.firmato = value;
    }

    /**
     * Recupera il valore della proprietà dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della proprietà dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della proprietà daConservareSostitutiva.
     * 
     */
    public boolean isDaConservareSostitutiva() {
        return daConservareSostitutiva;
    }

    /**
     * Imposta il valore della proprietà daConservareSostitutiva.
     * 
     */
    public void setDaConservareSostitutiva(boolean value) {
        this.daConservareSostitutiva = value;
    }

    /**
     * Recupera il valore della proprietà originale.
     * 
     */
    public boolean isOriginale() {
        return originale;
    }

    /**
     * Imposta il valore della proprietà originale.
     * 
     */
    public void setOriginale(boolean value) {
        this.originale = value;
    }

    /**
     * Recupera il valore della proprietà unico.
     * 
     */
    public boolean isUnico() {
        return unico;
    }

    /**
     * Imposta il valore della proprietà unico.
     * 
     */
    public void setUnico(boolean value) {
        this.unico = value;
    }

    /**
     * Recupera il valore della proprietà idVitalRecordCode.
     * 
     * @return
     *     possible object is
     *     {@link IdVitalRecordCodeType }
     *     
     */
    public IdVitalRecordCodeType getIdVitalRecordCode() {
        return idVitalRecordCode;
    }

    /**
     * Imposta il valore della proprietà idVitalRecordCode.
     * 
     * @param value
     *     allowed object is
     *     {@link IdVitalRecordCodeType }
     *     
     */
    public void setIdVitalRecordCode(IdVitalRecordCodeType value) {
        this.idVitalRecordCode = value;
    }

}
