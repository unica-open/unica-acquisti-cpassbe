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

package it.doqui.acta.acaris.management;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per anonymous complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="movimentazione" type="{management.acaris.acta.doqui.it}MovimentazioneType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "movimentazione"
})
@XmlRootElement(name = "getMovimentazioneResponse")
public class GetMovimentazioneResponse {

    protected MovimentazioneType movimentazione;

    /**
     * Recupera il valore della proprietà movimentazione.
     * 
     * @return
     *     possible object is
     *     {@link MovimentazioneType }
     *     
     */
    public MovimentazioneType getMovimentazione() {
        return movimentazione;
    }

    /**
     * Imposta il valore della proprietà movimentazione.
     * 
     * @param value
     *     allowed object is
     *     {@link MovimentazioneType }
     *     
     */
    public void setMovimentazione(MovimentazioneType value) {
        this.movimentazione = value;
    }

}
