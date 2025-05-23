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

import java.util.ArrayList;
import java.util.List;

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
 *         &amp;lt;element name="origine" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="identificatoreRegistraizone" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="mittente" type="{officialbookservice.acaris.acta.doqui.it}InfoMittenteIS"/&amp;gt;
 *         &amp;lt;element name="destinatari" type="{officialbookservice.acaris.acta.doqui.it}InfoDestinatarioIS" maxOccurs="unbounded"/&amp;gt;
 *         &amp;lt;element name="identitaDigitale" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
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
    "origine",
    "identificatoreRegistraizone",
    "mittente",
    "destinatari",
    "identitaDigitale"
})
@XmlRootElement(name = "inviaSegnatura")
public class InviaSegnatura {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected PrincipalIdType principalId;
    @XmlElement(required = true)
    protected String origine;
    @XmlElement(required = true)
    protected ObjectIdType identificatoreRegistraizone;
    @XmlElement(required = true)
    protected InfoMittenteIS mittente;
    @XmlElement(required = true)
    protected List<InfoDestinatarioIS> destinatari;
    @XmlElement(required = true)
    protected String identitaDigitale;

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
     * Recupera il valore della proprietà origine.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigine() {
        return origine;
    }

    /**
     * Imposta il valore della proprietà origine.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigine(String value) {
        this.origine = value;
    }

    /**
     * Recupera il valore della proprietà identificatoreRegistraizone.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdentificatoreRegistraizone() {
        return identificatoreRegistraizone;
    }

    /**
     * Imposta il valore della proprietà identificatoreRegistraizone.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdentificatoreRegistraizone(ObjectIdType value) {
        this.identificatoreRegistraizone = value;
    }

    /**
     * Recupera il valore della proprietà mittente.
     * 
     * @return
     *     possible object is
     *     {@link InfoMittenteIS }
     *     
     */
    public InfoMittenteIS getMittente() {
        return mittente;
    }

    /**
     * Imposta il valore della proprietà mittente.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoMittenteIS }
     *     
     */
    public void setMittente(InfoMittenteIS value) {
        this.mittente = value;
    }

    /**
     * Gets the value of the destinatari property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the destinatari property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDestinatari().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link InfoDestinatarioIS }
     * 
     * 
     */
    public List<InfoDestinatarioIS> getDestinatari() {
        if (destinatari == null) {
            destinatari = new ArrayList<InfoDestinatarioIS>();
        }
        return this.destinatari;
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

}
