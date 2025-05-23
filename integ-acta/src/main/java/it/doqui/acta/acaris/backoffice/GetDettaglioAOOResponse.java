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
 *         &amp;lt;element name="dettaglioAOO" type="{backoffice.acaris.acta.doqui.it}DettaglioAOOType" minOccurs="0"/&amp;gt;
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
    "dettaglioAOO"
})
@XmlRootElement(name = "getDettaglioAOOResponse")
public class GetDettaglioAOOResponse {

    protected DettaglioAOOType dettaglioAOO;

    /**
     * Recupera il valore della proprietà dettaglioAOO.
     * 
     * @return
     *     possible object is
     *     {@link DettaglioAOOType }
     *     
     */
    public DettaglioAOOType getDettaglioAOO() {
        return dettaglioAOO;
    }

    /**
     * Imposta il valore della proprietà dettaglioAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link DettaglioAOOType }
     *     
     */
    public void setDettaglioAOO(DettaglioAOOType value) {
        this.dettaglioAOO = value;
    }

}
