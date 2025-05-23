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
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per ProtocollazioneDocumentoEsistente complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ProtocollazioneDocumentoEsistente"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}RegistrazioneRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="classificazioneId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="registrazioneAPI" type="{officialbookservice.acaris.acta.doqui.it}RegistrazioneAPI"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProtocollazioneDocumentoEsistente", propOrder = {
    "classificazioneId",
    "registrazioneAPI"
})
public class ProtocollazioneDocumentoEsistente
    extends RegistrazioneRequest
{

    @XmlElement(required = true)
    protected ObjectIdType classificazioneId;
    @XmlElement(required = true)
    protected RegistrazioneAPI registrazioneAPI;

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
     * Recupera il valore della proprietà registrazioneAPI.
     * 
     * @return
     *     possible object is
     *     {@link RegistrazioneAPI }
     *     
     */
    public RegistrazioneAPI getRegistrazioneAPI() {
        return registrazioneAPI;
    }

    /**
     * Imposta il valore della proprietà registrazioneAPI.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrazioneAPI }
     *     
     */
    public void setRegistrazioneAPI(RegistrazioneAPI value) {
        this.registrazioneAPI = value;
    }

}
