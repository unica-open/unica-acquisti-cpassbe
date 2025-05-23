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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PrincipalIdType;


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
 *         &amp;lt;element name="repositoryId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="principalId" type="{common.acaris.acta.doqui.it}PrincipalIdType"/&amp;gt;
 *         &amp;lt;element name="identitaDigitale" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="codiceFiscale" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="partitaIva" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="fonteEsterna" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="flagPersonaGiuridica" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
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
    "repositoryId",
    "principalId",
    "identitaDigitale",
    "codiceFiscale",
    "partitaIva",
    "fonteEsterna",
    "flagPersonaGiuridica"
})
@XmlRootElement(name = "ricercaSoggetto")
public class RicercaSoggetto {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected PrincipalIdType principalId;
    @XmlElement(required = true)
    protected String identitaDigitale;
    protected String codiceFiscale;
    protected String partitaIva;
    @XmlElement(required = true)
    protected String fonteEsterna;
    protected boolean flagPersonaGiuridica;

    /**
     * Recupera il valore della proprietà repositoryId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getRepositoryId() {
        return repositoryId;
    }

    /**
     * Imposta il valore della proprietà repositoryId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setRepositoryId(ObjectIdType value) {
        this.repositoryId = value;
    }

    /**
     * Recupera il valore della proprietà principalId.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalIdType }
     *     
     */
    public PrincipalIdType getPrincipalId() {
        return principalId;
    }

    /**
     * Imposta il valore della proprietà principalId.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalIdType }
     *     
     */
    public void setPrincipalId(PrincipalIdType value) {
        this.principalId = value;
    }

    /**
     * Recupera il valore della proprietà identitaDigitale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentitaDigitale() {
        return identitaDigitale;
    }

    /**
     * Imposta il valore della proprietà identitaDigitale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentitaDigitale(String value) {
        this.identitaDigitale = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprietà codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprietà partitaIva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * Imposta il valore della proprietà partitaIva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitaIva(String value) {
        this.partitaIva = value;
    }

    /**
     * Recupera il valore della proprietà fonteEsterna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFonteEsterna() {
        return fonteEsterna;
    }

    /**
     * Imposta il valore della proprietà fonteEsterna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFonteEsterna(String value) {
        this.fonteEsterna = value;
    }

    /**
     * Recupera il valore della proprietà flagPersonaGiuridica.
     * 
     */
    public boolean isFlagPersonaGiuridica() {
        return flagPersonaGiuridica;
    }

    /**
     * Imposta il valore della proprietà flagPersonaGiuridica.
     * 
     */
    public void setFlagPersonaGiuridica(boolean value) {
        this.flagPersonaGiuridica = value;
    }

}
