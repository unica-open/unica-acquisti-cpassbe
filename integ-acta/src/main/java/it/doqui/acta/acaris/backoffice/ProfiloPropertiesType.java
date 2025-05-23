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

import it.doqui.acta.acaris.common.IdProfiloType;


/**
 * &lt;p&gt;Classe Java per ProfiloPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ProfiloPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="idProfilo" type="{common.acaris.acta.doqui.it}IdProfiloType"/&amp;gt;
 *         &amp;lt;element name="codice" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfiloPropertiesType", propOrder = {
    "idProfilo",
    "codice"
})
public class ProfiloPropertiesType {

    @XmlElement(required = true)
    protected IdProfiloType idProfilo;
    @XmlElement(required = true)
    protected String codice;

    /**
     * Recupera il valore della proprietà idProfilo.
     * 
     * @return
     *     possible object is
     *     {@link IdProfiloType }
     *     
     */
    public IdProfiloType getIdProfilo() {
        return idProfilo;
    }

    /**
     * Imposta il valore della proprietà idProfilo.
     * 
     * @param value
     *     allowed object is
     *     {@link IdProfiloType }
     *     
     */
    public void setIdProfilo(IdProfiloType value) {
        this.idProfilo = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

}
