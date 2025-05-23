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


/**
 * &lt;p&gt;Classe Java per ProtocollazioneDaSmistamento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ProtocollazioneDaSmistamento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}RegistrazioneRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="smistamentoId" type="{common.acaris.acta.doqui.it}IDDBType"/&amp;gt;
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
@XmlType(name = "ProtocollazioneDaSmistamento", propOrder = {
    "smistamentoId",
    "registrazioneAPI"
})
public class ProtocollazioneDaSmistamento
    extends RegistrazioneRequest
{

    protected long smistamentoId;
    @XmlElement(required = true)
    protected RegistrazioneAPI registrazioneAPI;

    /**
     * Recupera il valore della proprietà smistamentoId.
     * 
     */
    public long getSmistamentoId() {
        return smistamentoId;
    }

    /**
     * Imposta il valore della proprietà smistamentoId.
     * 
     */
    public void setSmistamentoId(long value) {
        this.smistamentoId = value;
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
