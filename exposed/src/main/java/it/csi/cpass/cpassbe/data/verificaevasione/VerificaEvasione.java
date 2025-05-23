/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EXPOSED submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.csi.cpass.cpassbe.data.verificaevasione;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per verificaEvasione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="verificaEvasione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="evasioni" type="{http://cpass.csi.it/cpassbe/data/verificaevasione}evasioni" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificaEvasione", propOrder = {
    "evasioni"
})
public class VerificaEvasione {

    protected Evasioni evasioni;

    /**
     * Recupera il valore della proprietà evasioni.
     * 
     * @return
     *     possible object is
     *     {@link Evasioni }
     *     
     */
    public Evasioni getEvasioni() {
        return evasioni;
    }

    /**
     * Imposta il valore della proprietà evasioni.
     * 
     * @param value
     *     allowed object is
     *     {@link Evasioni }
     *     
     */
    public void setEvasioni(Evasioni value) {
        this.evasioni = value;
    }

}
