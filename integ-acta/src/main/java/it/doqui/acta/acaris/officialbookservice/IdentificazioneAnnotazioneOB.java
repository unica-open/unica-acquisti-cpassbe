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

import it.doqui.acta.acaris.common.ChangeTokenType;
import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per IdentificazioneAnnotazioneOB complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="IdentificazioneAnnotazioneOB"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="idAnnotazioneOB" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="target" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="tipologiaTarget" type="{officialbookservice.acaris.acta.doqui.it}enumTipologiaTargetAnnotazioneOB"/&amp;gt;
 *         &amp;lt;element name="dataUltimoAggiornamento" type="{common.acaris.acta.doqui.it}ChangeTokenType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificazioneAnnotazioneOB", propOrder = {
    "idAnnotazioneOB",
    "target",
    "tipologiaTarget",
    "dataUltimoAggiornamento"
})
public class IdentificazioneAnnotazioneOB {

    @XmlElement(required = true)
    protected ObjectIdType idAnnotazioneOB;
    @XmlElement(required = true)
    protected ObjectIdType target;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipologiaTargetAnnotazioneOB tipologiaTarget;
    @XmlElement(required = true)
    protected ChangeTokenType dataUltimoAggiornamento;

    /**
     * Recupera il valore della proprietà idAnnotazioneOB.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdAnnotazioneOB() {
        return idAnnotazioneOB;
    }

    /**
     * Imposta il valore della proprietà idAnnotazioneOB.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdAnnotazioneOB(ObjectIdType value) {
        this.idAnnotazioneOB = value;
    }

    /**
     * Recupera il valore della proprietà target.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getTarget() {
        return target;
    }

    /**
     * Imposta il valore della proprietà target.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setTarget(ObjectIdType value) {
        this.target = value;
    }

    /**
     * Recupera il valore della proprietà tipologiaTarget.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipologiaTargetAnnotazioneOB }
     *     
     */
    public EnumTipologiaTargetAnnotazioneOB getTipologiaTarget() {
        return tipologiaTarget;
    }

    /**
     * Imposta il valore della proprietà tipologiaTarget.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipologiaTargetAnnotazioneOB }
     *     
     */
    public void setTipologiaTarget(EnumTipologiaTargetAnnotazioneOB value) {
        this.tipologiaTarget = value;
    }

    /**
     * Recupera il valore della proprietà dataUltimoAggiornamento.
     * 
     * @return
     *     possible object is
     *     {@link ChangeTokenType }
     *     
     */
    public ChangeTokenType getDataUltimoAggiornamento() {
        return dataUltimoAggiornamento;
    }

    /**
     * Imposta il valore della proprietà dataUltimoAggiornamento.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeTokenType }
     *     
     */
    public void setDataUltimoAggiornamento(ChangeTokenType value) {
        this.dataUltimoAggiornamento = value;
    }

}
