/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.rer.intercenter.notier.services._1_0.esito.response;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ListaDocumentiType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ListaDocumentiType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TotaleDocumentiScaricabili" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="NumeroDocumenti" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="Documenti" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}DocumentiType" minOccurs="0"/&gt;
 *         &lt;element name="DataInizio" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="DataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaDocumentiType", propOrder = {
    "totaleDocumentiScaricabili",
    "numeroDocumenti",
    "documenti",
    "dataInizio",
    "dataFine"
})
public class ListaDocumentiType {

    @XmlElement(name = "TotaleDocumentiScaricabili", required = true)
    protected BigInteger totaleDocumentiScaricabili;
    @XmlElement(name = "NumeroDocumenti", required = true)
    protected BigInteger numeroDocumenti;
    @XmlElement(name = "Documenti")
    protected DocumentiType documenti;
    @XmlElement(name = "DataInizio", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizio;
    @XmlElement(name = "DataFine", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFine;

    /**
     * Recupera il valore della proprietà totaleDocumentiScaricabili.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotaleDocumentiScaricabili() {
        return totaleDocumentiScaricabili;
    }

    /**
     * Imposta il valore della proprietà totaleDocumentiScaricabili.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotaleDocumentiScaricabili(BigInteger value) {
        this.totaleDocumentiScaricabili = value;
    }

    /**
     * Recupera il valore della proprietà numeroDocumenti.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroDocumenti() {
        return numeroDocumenti;
    }

    /**
     * Imposta il valore della proprietà numeroDocumenti.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroDocumenti(BigInteger value) {
        this.numeroDocumenti = value;
    }

    /**
     * Recupera il valore della proprietà documenti.
     * 
     * @return
     *     possible object is
     *     {@link DocumentiType }
     *     
     */
    public DocumentiType getDocumenti() {
        return documenti;
    }

    /**
     * Imposta il valore della proprietà documenti.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentiType }
     *     
     */
    public void setDocumenti(DocumentiType value) {
        this.documenti = value;
    }

    /**
     * Recupera il valore della proprietà dataInizio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Imposta il valore della proprietà dataInizio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(XMLGregorianCalendar value) {
        this.dataInizio = value;
    }

    /**
     * Recupera il valore della proprietà dataFine.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Imposta il valore della proprietà dataFine.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
    }

}
