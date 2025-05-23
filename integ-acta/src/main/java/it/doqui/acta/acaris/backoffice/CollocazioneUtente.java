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

package it.doqui.acta.acaris.backoffice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.DecodificaExtType;


/**
 * &lt;p&gt;Classe Java per CollocazioneUtente complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CollocazioneUtente"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="aoo" type="{common.acaris.acta.doqui.it}DecodificaExtType"/&amp;gt;
 *         &amp;lt;element name="struttura" type="{common.acaris.acta.doqui.it}DecodificaExtType"/&amp;gt;
 *         &amp;lt;element name="nodo" type="{common.acaris.acta.doqui.it}DecodificaExtType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollocazioneUtente", propOrder = {
    "aoo",
    "struttura",
    "nodo"
})
public class CollocazioneUtente {

    @XmlElement(required = true)
    protected DecodificaExtType aoo;
    @XmlElement(required = true)
    protected DecodificaExtType struttura;
    @XmlElement(required = true)
    protected DecodificaExtType nodo;

    /**
     * Recupera il valore della proprietà aoo.
     * 
     * @return
     *     possible object is
     *     {@link DecodificaExtType }
     *     
     */
    public DecodificaExtType getAoo() {
        return aoo;
    }

    /**
     * Imposta il valore della proprietà aoo.
     * 
     * @param value
     *     allowed object is
     *     {@link DecodificaExtType }
     *     
     */
    public void setAoo(DecodificaExtType value) {
        this.aoo = value;
    }

    /**
     * Recupera il valore della proprietà struttura.
     * 
     * @return
     *     possible object is
     *     {@link DecodificaExtType }
     *     
     */
    public DecodificaExtType getStruttura() {
        return struttura;
    }

    /**
     * Imposta il valore della proprietà struttura.
     * 
     * @param value
     *     allowed object is
     *     {@link DecodificaExtType }
     *     
     */
    public void setStruttura(DecodificaExtType value) {
        this.struttura = value;
    }

    /**
     * Recupera il valore della proprietà nodo.
     * 
     * @return
     *     possible object is
     *     {@link DecodificaExtType }
     *     
     */
    public DecodificaExtType getNodo() {
        return nodo;
    }

    /**
     * Imposta il valore della proprietà nodo.
     * 
     * @param value
     *     allowed object is
     *     {@link DecodificaExtType }
     *     
     */
    public void setNodo(DecodificaExtType value) {
        this.nodo = value;
    }

}
