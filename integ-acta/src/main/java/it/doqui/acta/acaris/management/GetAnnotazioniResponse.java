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

import it.doqui.acta.acaris.common.AnnotazioniPropertiesType;


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
 *         &amp;lt;element name="annotazioni" type="{common.acaris.acta.doqui.it}AnnotazioniPropertiesType" minOccurs="0"/&amp;gt;
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
    "annotazioni"
})
@XmlRootElement(name = "getAnnotazioniResponse")
public class GetAnnotazioniResponse {

    protected AnnotazioniPropertiesType annotazioni;

    /**
     * Recupera il valore della proprietà annotazioni.
     * 
     * @return
     *     possible object is
     *     {@link AnnotazioniPropertiesType }
     *     
     */
    public AnnotazioniPropertiesType getAnnotazioni() {
        return annotazioni;
    }

    /**
     * Imposta il valore della proprietà annotazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotazioniPropertiesType }
     *     
     */
    public void setAnnotazioni(AnnotazioniPropertiesType value) {
        this.annotazioni = value;
    }

}
