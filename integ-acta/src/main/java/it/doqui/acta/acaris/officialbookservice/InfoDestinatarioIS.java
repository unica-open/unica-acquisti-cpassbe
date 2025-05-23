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
 * &lt;p&gt;Classe Java per InfoDestinatarioIS complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="InfoDestinatarioIS"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="identificatore" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="emailPec" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="confermaRicezione" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoDestinatarioIS", propOrder = {
    "identificatore",
    "emailPec",
    "confermaRicezione"
})
public class InfoDestinatarioIS {

    @XmlElement(required = true)
    protected ObjectIdType identificatore;
    @XmlElement(required = true)
    protected String emailPec;
    protected boolean confermaRicezione;

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
     * Recupera il valore della proprietà emailPec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailPec() {
        return emailPec;
    }

    /**
     * Imposta il valore della proprietà emailPec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailPec(String value) {
        this.emailPec = value;
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

}
