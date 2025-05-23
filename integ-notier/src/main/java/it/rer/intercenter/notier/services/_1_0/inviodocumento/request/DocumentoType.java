/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.rer.intercenter.notier.services._1_0.inviodocumento.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DocumentoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DocumentoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Chiave" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}ChiaveDocumentoType"/&gt;
 *         &lt;element name="Rappresentazione" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}RappresentazioneType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoType", propOrder = {
    "chiave",
    "rappresentazione"
})
public class DocumentoType {

    @XmlElement(name = "Chiave", required = true)
    protected ChiaveDocumentoType chiave;
    @XmlElement(name = "Rappresentazione", required = true)
    protected RappresentazioneType rappresentazione;

    /**
     * Recupera il valore della proprietà chiave.
     * 
     * @return
     *     possible object is
     *     {@link ChiaveDocumentoType }
     *     
     */
    public ChiaveDocumentoType getChiave() {
        return chiave;
    }

    /**
     * Imposta il valore della proprietà chiave.
     * 
     * @param value
     *     allowed object is
     *     {@link ChiaveDocumentoType }
     *     
     */
    public void setChiave(ChiaveDocumentoType value) {
        this.chiave = value;
    }

    /**
     * Recupera il valore della proprietà rappresentazione.
     * 
     * @return
     *     possible object is
     *     {@link RappresentazioneType }
     *     
     */
    public RappresentazioneType getRappresentazione() {
        return rappresentazione;
    }

    /**
     * Imposta il valore della proprietà rappresentazione.
     * 
     * @param value
     *     allowed object is
     *     {@link RappresentazioneType }
     *     
     */
    public void setRappresentazione(RappresentazioneType value) {
        this.rappresentazione = value;
    }

}
