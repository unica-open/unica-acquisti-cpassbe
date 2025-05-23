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

package it.doqui.acta.acaris.documentservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per StepErrorAction complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="StepErrorAction"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="step" type="{common.acaris.acta.doqui.it}integer"/&amp;gt;
 *         &amp;lt;element name="action" type="{documentservice.acaris.acta.doqui.it}enumStepErrorAction"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StepErrorAction", propOrder = {
    "step",
    "action"
})
public class StepErrorAction {

    protected int step;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumStepErrorAction action;

    /**
     * Recupera il valore della proprietà step.
     * 
     */
    public int getStep() {
        return step;
    }

    /**
     * Imposta il valore della proprietà step.
     * 
     */
    public void setStep(int value) {
        this.step = value;
    }

    /**
     * Recupera il valore della proprietà action.
     * 
     * @return
     *     possible object is
     *     {@link EnumStepErrorAction }
     *     
     */
    public EnumStepErrorAction getAction() {
        return action;
    }

    /**
     * Imposta il valore della proprietà action.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumStepErrorAction }
     *     
     */
    public void setAction(EnumStepErrorAction value) {
        this.action = value;
    }

}
