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


/**
 * &lt;p&gt;Classe Java per InfoAnnullamentoRegistrazione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="InfoAnnullamentoRegistrazione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="annotazione" type="{officialbookservice.acaris.acta.doqui.it}InfoCreazioneAnnotazioneOB"/&amp;gt;
 *         &amp;lt;element name="dataAnnullamento" type="{common.acaris.acta.doqui.it}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="utenteAnnullamento" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoAnnullamentoRegistrazione", propOrder = {
    "annotazione",
    "dataAnnullamento",
    "utenteAnnullamento"
})
public class InfoAnnullamentoRegistrazione {

    @XmlElement(required = true)
    protected InfoCreazioneAnnotazioneOB annotazione;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataAnnullamento;
    protected String utenteAnnullamento;

    /**
     * Recupera il valore della proprietà annotazione.
     * 
     * @return
     *     possible object is
     *     {@link InfoCreazioneAnnotazioneOB }
     *     
     */
    public InfoCreazioneAnnotazioneOB getAnnotazione() {
        return annotazione;
    }

    /**
     * Imposta il valore della proprietà annotazione.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoCreazioneAnnotazioneOB }
     *     
     */
    public void setAnnotazione(InfoCreazioneAnnotazioneOB value) {
        this.annotazione = value;
    }

    /**
     * Recupera il valore della proprietà dataAnnullamento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAnnullamento() {
        return dataAnnullamento;
    }

    /**
     * Imposta il valore della proprietà dataAnnullamento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAnnullamento(XMLGregorianCalendar value) {
        this.dataAnnullamento = value;
    }

    /**
     * Recupera il valore della proprietà utenteAnnullamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteAnnullamento() {
        return utenteAnnullamento;
    }

    /**
     * Imposta il valore della proprietà utenteAnnullamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteAnnullamento(String value) {
        this.utenteAnnullamento = value;
    }

}
