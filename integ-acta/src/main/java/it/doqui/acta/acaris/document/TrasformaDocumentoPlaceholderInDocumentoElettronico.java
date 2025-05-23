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

package it.doqui.acta.acaris.document;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PrincipalIdType;
import it.doqui.acta.acaris.documentservice.DocumentoFisicoIRC;
import it.doqui.acta.acaris.documentservice.InfoRichiestaTrasformazione;


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
 *         &amp;lt;element name="classificazioneId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="registrazioneId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="infoRichiesta" type="{documentservice.acaris.acta.doqui.it}InfoRichiestaTrasformazione"/&amp;gt;
 *         &amp;lt;element name="documentoFisico" type="{documentservice.acaris.acta.doqui.it}DocumentoFisicoIRC" maxOccurs="unbounded"/&amp;gt;
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
    "classificazioneId",
    "registrazioneId",
    "infoRichiesta",
    "documentoFisico"
})
@XmlRootElement(name = "trasformaDocumentoPlaceholderInDocumentoElettronico")
public class TrasformaDocumentoPlaceholderInDocumentoElettronico {

    @XmlElement(required = true)
    protected ObjectIdType repositoryId;
    @XmlElement(required = true)
    protected PrincipalIdType principalId;
    @XmlElement(required = true)
    protected ObjectIdType classificazioneId;
    @XmlElement(required = true)
    protected ObjectIdType registrazioneId;
    @XmlElement(required = true)
    protected InfoRichiestaTrasformazione infoRichiesta;
    @XmlElement(required = true)
    protected List<DocumentoFisicoIRC> documentoFisico;

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
     * Recupera il valore della proprietà classificazioneId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getClassificazioneId() {
        return classificazioneId;
    }

    /**
     * Imposta il valore della proprietà classificazioneId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setClassificazioneId(ObjectIdType value) {
        this.classificazioneId = value;
    }

    /**
     * Recupera il valore della proprietà registrazioneId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getRegistrazioneId() {
        return registrazioneId;
    }

    /**
     * Imposta il valore della proprietà registrazioneId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setRegistrazioneId(ObjectIdType value) {
        this.registrazioneId = value;
    }

    /**
     * Recupera il valore della proprietà infoRichiesta.
     * 
     * @return
     *     possible object is
     *     {@link InfoRichiestaTrasformazione }
     *     
     */
    public InfoRichiestaTrasformazione getInfoRichiesta() {
        return infoRichiesta;
    }

    /**
     * Imposta il valore della proprietà infoRichiesta.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoRichiestaTrasformazione }
     *     
     */
    public void setInfoRichiesta(InfoRichiestaTrasformazione value) {
        this.infoRichiesta = value;
    }

    /**
     * Gets the value of the documentoFisico property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the documentoFisico property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDocumentoFisico().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoFisicoIRC }
     * 
     * 
     */
    public List<DocumentoFisicoIRC> getDocumentoFisico() {
        if (documentoFisico == null) {
            documentoFisico = new ArrayList<DocumentoFisicoIRC>();
        }
        return this.documentoFisico;
    }

}
