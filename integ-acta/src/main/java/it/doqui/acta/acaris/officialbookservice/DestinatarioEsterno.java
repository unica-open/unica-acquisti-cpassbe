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
 * &lt;p&gt;Classe Java per DestinatarioEsterno complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DestinatarioEsterno"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="corrispondente" type="{officialbookservice.acaris.acta.doqui.it}InfoCreazioneCorrispondente"/&amp;gt;
 *         &amp;lt;element name="idRuoloCorrispondente" type="{common.acaris.acta.doqui.it}IDDBType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DestinatarioEsterno", propOrder = {
    "corrispondente",
    "idRuoloCorrispondente"
})
public class DestinatarioEsterno {

    @XmlElement(required = true)
    protected InfoCreazioneCorrispondente corrispondente;
    protected long idRuoloCorrispondente;

    /**
     * Recupera il valore della proprietà corrispondente.
     * 
     * @return
     *     possible object is
     *     {@link InfoCreazioneCorrispondente }
     *     
     */
    public InfoCreazioneCorrispondente getCorrispondente() {
        return corrispondente;
    }

    /**
     * Imposta il valore della proprietà corrispondente.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoCreazioneCorrispondente }
     *     
     */
    public void setCorrispondente(InfoCreazioneCorrispondente value) {
        this.corrispondente = value;
    }

    /**
     * Recupera il valore della proprietà idRuoloCorrispondente.
     * 
     */
    public long getIdRuoloCorrispondente() {
        return idRuoloCorrispondente;
    }

    /**
     * Imposta il valore della proprietà idRuoloCorrispondente.
     * 
     */
    public void setIdRuoloCorrispondente(long value) {
        this.idRuoloCorrispondente = value;
    }

}
