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
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &amp;lt;element name="tipologiaCreazione" type="{subjectregistryservice.acaris.acta.doqui.it}enumTipologiaCreazioneSoggetto"/&amp;gt;
 *         &amp;lt;element name="infoRichiestaCreazione" type="{subjectregistryservice.acaris.acta.doqui.it}SoggettoRequest"/&amp;gt;
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
    "tipologiaCreazione",
    "infoRichiestaCreazione"
})
@XmlRootElement(name = "creaSoggetto")
public class CreaSoggetto {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected PrincipalIdType principalId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipologiaCreazioneSoggetto tipologiaCreazione;
    @XmlElement(required = true)
    protected SoggettoRequest infoRichiestaCreazione;

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
     * Recupera il valore della proprietà tipologiaCreazione.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipologiaCreazioneSoggetto }
     *     
     */
    public EnumTipologiaCreazioneSoggetto getTipologiaCreazione() {
        return tipologiaCreazione;
    }

    /**
     * Imposta il valore della proprietà tipologiaCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipologiaCreazioneSoggetto }
     *     
     */
    public void setTipologiaCreazione(EnumTipologiaCreazioneSoggetto value) {
        this.tipologiaCreazione = value;
    }

    /**
     * Recupera il valore della proprietà infoRichiestaCreazione.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoRequest }
     *     
     */
    public SoggettoRequest getInfoRichiestaCreazione() {
        return infoRichiestaCreazione;
    }

    /**
     * Imposta il valore della proprietà infoRichiestaCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoRequest }
     *     
     */
    public void setInfoRichiestaCreazione(SoggettoRequest value) {
        this.infoRichiestaCreazione = value;
    }

}
