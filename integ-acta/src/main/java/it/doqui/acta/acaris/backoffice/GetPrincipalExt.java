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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.CodiceFiscaleType;
import it.doqui.acta.acaris.common.IdAOOType;
import it.doqui.acta.acaris.common.IdNodoType;
import it.doqui.acta.acaris.common.IdStrutturaType;
import it.doqui.acta.acaris.common.ObjectIdType;


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
 *         &amp;lt;element name="idUtente" type="{common.acaris.acta.doqui.it}CodiceFiscaleType"/&amp;gt;
 *         &amp;lt;element name="idAOO" type="{common.acaris.acta.doqui.it}IdAOOType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="idStruttura" type="{common.acaris.acta.doqui.it}IdStrutturaType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="idNodo" type="{common.acaris.acta.doqui.it}IdNodoType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="clientApplicationInfo" type="{backoffice.acaris.acta.doqui.it}ClientApplicationInfo"/&amp;gt;
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
    "idUtente",
    "idAOO",
    "idStruttura",
    "idNodo",
    "clientApplicationInfo"
})
@XmlRootElement(name = "getPrincipalExt")
public class GetPrincipalExt {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected CodiceFiscaleType idUtente;
    protected IdAOOType idAOO;
    protected IdStrutturaType idStruttura;
    protected IdNodoType idNodo;
    @XmlElement(required = true)
    protected ClientApplicationInfo clientApplicationInfo;

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
     * Recupera il valore della proprietà idUtente.
     * 
     * @return
     *     possible object is
     *     {@link CodiceFiscaleType }
     *     
     */
    public CodiceFiscaleType getIdUtente() {
        return idUtente;
    }

    /**
     * Imposta il valore della proprietà idUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiceFiscaleType }
     *     
     */
    public void setIdUtente(CodiceFiscaleType value) {
        this.idUtente = value;
    }

    /**
     * Recupera il valore della proprietà idAOO.
     * 
     * @return
     *     possible object is
     *     {@link IdAOOType }
     *     
     */
    public IdAOOType getIdAOO() {
        return idAOO;
    }

    /**
     * Imposta il valore della proprietà idAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link IdAOOType }
     *     
     */
    public void setIdAOO(IdAOOType value) {
        this.idAOO = value;
    }

    /**
     * Recupera il valore della proprietà idStruttura.
     * 
     * @return
     *     possible object is
     *     {@link IdStrutturaType }
     *     
     */
    public IdStrutturaType getIdStruttura() {
        return idStruttura;
    }

    /**
     * Imposta il valore della proprietà idStruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link IdStrutturaType }
     *     
     */
    public void setIdStruttura(IdStrutturaType value) {
        this.idStruttura = value;
    }

    /**
     * Recupera il valore della proprietà idNodo.
     * 
     * @return
     *     possible object is
     *     {@link IdNodoType }
     *     
     */
    public IdNodoType getIdNodo() {
        return idNodo;
    }

    /**
     * Imposta il valore della proprietà idNodo.
     * 
     * @param value
     *     allowed object is
     *     {@link IdNodoType }
     *     
     */
    public void setIdNodo(IdNodoType value) {
        this.idNodo = value;
    }

    /**
     * Recupera il valore della proprietà clientApplicationInfo.
     * 
     * @return
     *     possible object is
     *     {@link ClientApplicationInfo }
     *     
     */
    public ClientApplicationInfo getClientApplicationInfo() {
        return clientApplicationInfo;
    }

    /**
     * Imposta il valore della proprietà clientApplicationInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientApplicationInfo }
     *     
     */
    public void setClientApplicationInfo(ClientApplicationInfo value) {
        this.clientApplicationInfo = value;
    }

}
