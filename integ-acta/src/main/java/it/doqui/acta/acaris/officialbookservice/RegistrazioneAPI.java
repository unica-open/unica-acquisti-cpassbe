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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per RegistrazioneAPI complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RegistrazioneAPI"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="tipoRegistrazione" type="{officialbookservice.acaris.acta.doqui.it}enumTipoAPI"/&amp;gt;
 *         &amp;lt;element name="infoCreazione" type="{officialbookservice.acaris.acta.doqui.it}InfoCreazioneRegistrazione"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrazioneAPI", propOrder = {
    "tipoRegistrazione",
    "infoCreazione"
})
@XmlSeeAlso({
    RegistrazioneInterna.class,
    RegistrazionePartenza.class,
    RegistrazioneArrivo.class
})
public class RegistrazioneAPI {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipoAPI tipoRegistrazione;
    @XmlElement(required = true)
    protected InfoCreazioneRegistrazione infoCreazione;

    /**
     * Recupera il valore della proprietà tipoRegistrazione.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoAPI }
     *     
     */
    public EnumTipoAPI getTipoRegistrazione() {
        return tipoRegistrazione;
    }

    /**
     * Imposta il valore della proprietà tipoRegistrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoAPI }
     *     
     */
    public void setTipoRegistrazione(EnumTipoAPI value) {
        this.tipoRegistrazione = value;
    }

    /**
     * Recupera il valore della proprietà infoCreazione.
     * 
     * @return
     *     possible object is
     *     {@link InfoCreazioneRegistrazione }
     *     
     */
    public InfoCreazioneRegistrazione getInfoCreazione() {
        return infoCreazione;
    }

    /**
     * Imposta il valore della proprietà infoCreazione.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoCreazioneRegistrazione }
     *     
     */
    public void setInfoCreazione(InfoCreazioneRegistrazione value) {
        this.infoCreazione = value;
    }

}
