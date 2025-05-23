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

package it.doqui.acta.acaris.subjectregistryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ChangeTokenType;
import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per IdentificazioneSoggetto complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="IdentificazioneSoggetto"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="soggettoId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="tipoSoggetto" type="{subjectregistryservice.acaris.acta.doqui.it}enumTipologiaCreazioneSoggetto"/&amp;gt;
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
@XmlType(name = "IdentificazioneSoggetto", propOrder = {
    "soggettoId",
    "tipoSoggetto",
    "dataUltimoAggiornamento"
})
public class IdentificazioneSoggetto {

    @XmlElement(required = true)
    protected ObjectIdType soggettoId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipologiaCreazioneSoggetto tipoSoggetto;
    @XmlElement(required = true)
    protected ChangeTokenType dataUltimoAggiornamento;

    /**
     * Recupera il valore della proprietà soggettoId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getSoggettoId() {
        return soggettoId;
    }

    /**
     * Imposta il valore della proprietà soggettoId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setSoggettoId(ObjectIdType value) {
        this.soggettoId = value;
    }

    /**
     * Recupera il valore della proprietà tipoSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipologiaCreazioneSoggetto }
     *     
     */
    public EnumTipologiaCreazioneSoggetto getTipoSoggetto() {
        return tipoSoggetto;
    }

    /**
     * Imposta il valore della proprietà tipoSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipologiaCreazioneSoggetto }
     *     
     */
    public void setTipoSoggetto(EnumTipologiaCreazioneSoggetto value) {
        this.tipoSoggetto = value;
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
