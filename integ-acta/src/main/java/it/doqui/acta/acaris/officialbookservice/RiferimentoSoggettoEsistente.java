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

import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.prt.EnumPFPGUL;


/**
 * &lt;p&gt;Classe Java per RiferimentoSoggettoEsistente complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RiferimentoSoggettoEsistente"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}InfoSoggettoAssociato"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="soggettoId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="nodoIdSeTipologiaUtente" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipologia" type="{officialbookservice.acaris.acta.doqui.it}enumTipologiaSoggettoAssociato"/&amp;gt;
 *         &amp;lt;element name="idPFPGUL" type="{prt.common.acaris.acta.doqui.it}enumPFPGUL"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RiferimentoSoggettoEsistente", propOrder = {
    "soggettoId",
    "nodoIdSeTipologiaUtente",
    "tipologia",
    "idPFPGUL"
})
public class RiferimentoSoggettoEsistente
    extends InfoSoggettoAssociato
{

    @XmlElement(required = true)
    protected ObjectIdType soggettoId;
    protected ObjectIdType nodoIdSeTipologiaUtente;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipologiaSoggettoAssociato tipologia;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPFPGUL idPFPGUL;

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
     * Recupera il valore della proprietà nodoIdSeTipologiaUtente.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getNodoIdSeTipologiaUtente() {
        return nodoIdSeTipologiaUtente;
    }

    /**
     * Imposta il valore della proprietà nodoIdSeTipologiaUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setNodoIdSeTipologiaUtente(ObjectIdType value) {
        this.nodoIdSeTipologiaUtente = value;
    }

    /**
     * Recupera il valore della proprietà tipologia.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipologiaSoggettoAssociato }
     *     
     */
    public EnumTipologiaSoggettoAssociato getTipologia() {
        return tipologia;
    }

    /**
     * Imposta il valore della proprietà tipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipologiaSoggettoAssociato }
     *     
     */
    public void setTipologia(EnumTipologiaSoggettoAssociato value) {
        this.tipologia = value;
    }

    /**
     * Recupera il valore della proprietà idPFPGUL.
     * 
     * @return
     *     possible object is
     *     {@link EnumPFPGUL }
     *     
     */
    public EnumPFPGUL getIdPFPGUL() {
        return idPFPGUL;
    }

    /**
     * Imposta il valore della proprietà idPFPGUL.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPFPGUL }
     *     
     */
    public void setIdPFPGUL(EnumPFPGUL value) {
        this.idPFPGUL = value;
    }

}
