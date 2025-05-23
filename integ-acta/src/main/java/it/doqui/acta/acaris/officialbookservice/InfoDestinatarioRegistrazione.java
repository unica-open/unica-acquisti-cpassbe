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
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per InfoDestinatarioRegistrazione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="InfoDestinatarioRegistrazione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="identificatore" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="casella" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="confermaRicezione" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="usaCanalePec" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoDestinatarioRegistrazione", propOrder = {
    "identificatore",
    "casella",
    "confermaRicezione",
    "usaCanalePec"
})
public class InfoDestinatarioRegistrazione {

    @XmlElement(required = true)
    protected ObjectIdType identificatore;
    protected String casella;
    protected boolean confermaRicezione;
    protected boolean usaCanalePec;

    /**
     * Recupera il valore della proprietà identificatore.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdentificatore() {
        return identificatore;
    }

    /**
     * Imposta il valore della proprietà identificatore.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdentificatore(ObjectIdType value) {
        this.identificatore = value;
    }

    /**
     * Recupera il valore della proprietà casella.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCasella() {
        return casella;
    }

    /**
     * Imposta il valore della proprietà casella.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCasella(String value) {
        this.casella = value;
    }

    /**
     * Recupera il valore della proprietà confermaRicezione.
     * 
     */
    public boolean isConfermaRicezione() {
        return confermaRicezione;
    }

    /**
     * Imposta il valore della proprietà confermaRicezione.
     * 
     */
    public void setConfermaRicezione(boolean value) {
        this.confermaRicezione = value;
    }

    /**
     * Recupera il valore della proprietà usaCanalePec.
     * 
     */
    public boolean isUsaCanalePec() {
        return usaCanalePec;
    }

    /**
     * Imposta il valore della proprietà usaCanalePec.
     * 
     */
    public void setUsaCanalePec(boolean value) {
        this.usaCanalePec = value;
    }

}
