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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per RegistrazioneRequest complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RegistrazioneRequest"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="registroId" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="aooProtocollanteId" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="senzaCreazioneSoggettiEsterni" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrazioneRequest", propOrder = {
    "registroId",
    "aooProtocollanteId",
    "senzaCreazioneSoggettiEsterni"
})
@XmlSeeAlso({
    RegistrazioneRapida.class,
    Protocollazione.class,
    ProtocollazioneDocumentoEsistente.class,
    ProtocollazioneDaSmistamento.class
})
public abstract class RegistrazioneRequest {

    protected ObjectIdType registroId;
    protected ObjectIdType aooProtocollanteId;
    protected boolean senzaCreazioneSoggettiEsterni;

    /**
     * Recupera il valore della proprietà registroId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getRegistroId() {
        return registroId;
    }

    /**
     * Imposta il valore della proprietà registroId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setRegistroId(ObjectIdType value) {
        this.registroId = value;
    }

    /**
     * Recupera il valore della proprietà aooProtocollanteId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getAooProtocollanteId() {
        return aooProtocollanteId;
    }

    /**
     * Imposta il valore della proprietà aooProtocollanteId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setAooProtocollanteId(ObjectIdType value) {
        this.aooProtocollanteId = value;
    }

    /**
     * Recupera il valore della proprietà senzaCreazioneSoggettiEsterni.
     * 
     */
    public boolean isSenzaCreazioneSoggettiEsterni() {
        return senzaCreazioneSoggettiEsterni;
    }

    /**
     * Imposta il valore della proprietà senzaCreazioneSoggettiEsterni.
     * 
     */
    public void setSenzaCreazioneSoggettiEsterni(boolean value) {
        this.senzaCreazioneSoggettiEsterni = value;
    }

}
