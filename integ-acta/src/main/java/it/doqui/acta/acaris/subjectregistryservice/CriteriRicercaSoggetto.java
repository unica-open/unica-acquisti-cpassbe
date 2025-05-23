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


/**
 * &lt;p&gt;Classe Java per CriteriRicercaSoggetto complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CriteriRicercaSoggetto"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="tipoAnagrafica" type="{subjectregistryservice.acaris.acta.doqui.it}enumPFPG"/&amp;gt;
 *         &amp;lt;element name="codiceFonteEsterna" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="personaGiuridicaCR" type="{subjectregistryservice.acaris.acta.doqui.it}PersonaGiuridicaCR"/&amp;gt;
 *         &amp;lt;element name="personaFisicaCR" type="{subjectregistryservice.acaris.acta.doqui.it}PersonaFisicaCR"/&amp;gt;
 *         &amp;lt;element name="codiceFiscalePartitaIva" type="{subjectregistryservice.acaris.acta.doqui.it}CodiceFiscalePartitaIvaCR"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CriteriRicercaSoggetto", propOrder = {
    "tipoAnagrafica",
    "codiceFonteEsterna",
    "personaGiuridicaCR",
    "personaFisicaCR",
    "codiceFiscalePartitaIva"
})
public class CriteriRicercaSoggetto {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPFPG tipoAnagrafica;
    @XmlElement(required = true)
    protected String codiceFonteEsterna;
    @XmlElement(required = true)
    protected PersonaGiuridicaCR personaGiuridicaCR;
    @XmlElement(required = true)
    protected PersonaFisicaCR personaFisicaCR;
    @XmlElement(required = true)
    protected CodiceFiscalePartitaIvaCR codiceFiscalePartitaIva;

    /**
     * Recupera il valore della proprietà tipoAnagrafica.
     * 
     * @return
     *     possible object is
     *     {@link EnumPFPG }
     *     
     */
    public EnumPFPG getTipoAnagrafica() {
        return tipoAnagrafica;
    }

    /**
     * Imposta il valore della proprietà tipoAnagrafica.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPFPG }
     *     
     */
    public void setTipoAnagrafica(EnumPFPG value) {
        this.tipoAnagrafica = value;
    }

    /**
     * Recupera il valore della proprietà codiceFonteEsterna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFonteEsterna() {
        return codiceFonteEsterna;
    }

    /**
     * Imposta il valore della proprietà codiceFonteEsterna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFonteEsterna(String value) {
        this.codiceFonteEsterna = value;
    }

    /**
     * Recupera il valore della proprietà personaGiuridicaCR.
     * 
     * @return
     *     possible object is
     *     {@link PersonaGiuridicaCR }
     *     
     */
    public PersonaGiuridicaCR getPersonaGiuridicaCR() {
        return personaGiuridicaCR;
    }

    /**
     * Imposta il valore della proprietà personaGiuridicaCR.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaGiuridicaCR }
     *     
     */
    public void setPersonaGiuridicaCR(PersonaGiuridicaCR value) {
        this.personaGiuridicaCR = value;
    }

    /**
     * Recupera il valore della proprietà personaFisicaCR.
     * 
     * @return
     *     possible object is
     *     {@link PersonaFisicaCR }
     *     
     */
    public PersonaFisicaCR getPersonaFisicaCR() {
        return personaFisicaCR;
    }

    /**
     * Imposta il valore della proprietà personaFisicaCR.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaFisicaCR }
     *     
     */
    public void setPersonaFisicaCR(PersonaFisicaCR value) {
        this.personaFisicaCR = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscalePartitaIva.
     * 
     * @return
     *     possible object is
     *     {@link CodiceFiscalePartitaIvaCR }
     *     
     */
    public CodiceFiscalePartitaIvaCR getCodiceFiscalePartitaIva() {
        return codiceFiscalePartitaIva;
    }

    /**
     * Imposta il valore della proprietà codiceFiscalePartitaIva.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiceFiscalePartitaIvaCR }
     *     
     */
    public void setCodiceFiscalePartitaIva(CodiceFiscalePartitaIvaCR value) {
        this.codiceFiscalePartitaIva = value;
    }

}
