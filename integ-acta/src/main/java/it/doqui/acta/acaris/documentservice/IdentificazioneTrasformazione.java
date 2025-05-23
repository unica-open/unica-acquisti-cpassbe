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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.archive.RelationshipPropertiesType;
import it.doqui.acta.acaris.common.ChangeTokenType;


/**
 * &lt;p&gt;Classe Java per IdentificazioneTrasformazione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="IdentificazioneTrasformazione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="relazione" type="{archive.acaris.acta.doqui.it}RelationshipPropertiesType"/&amp;gt;
 *         &amp;lt;element name="dataUltimoAggiornamento" type="{common.acaris.acta.doqui.it}ChangeTokenType"/&amp;gt;
 *         &amp;lt;element name="failedSteps" type="{documentservice.acaris.acta.doqui.it}FailedStepsInfo" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificazioneTrasformazione", propOrder = {
    "relazione",
    "dataUltimoAggiornamento",
    "failedSteps"
})
public class IdentificazioneTrasformazione {

    @XmlElement(required = true)
    protected RelationshipPropertiesType relazione;
    @XmlElement(required = true)
    protected ChangeTokenType dataUltimoAggiornamento;
    protected List<FailedStepsInfo> failedSteps;

    /**
     * Recupera il valore della proprietà relazione.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipPropertiesType }
     *     
     */
    public RelationshipPropertiesType getRelazione() {
        return relazione;
    }

    /**
     * Imposta il valore della proprietà relazione.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipPropertiesType }
     *     
     */
    public void setRelazione(RelationshipPropertiesType value) {
        this.relazione = value;
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

    /**
     * Gets the value of the failedSteps property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the failedSteps property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getFailedSteps().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link FailedStepsInfo }
     * 
     * 
     */
    public List<FailedStepsInfo> getFailedSteps() {
        if (failedSteps == null) {
            failedSteps = new ArrayList<FailedStepsInfo>();
        }
        return this.failedSteps;
    }

}
