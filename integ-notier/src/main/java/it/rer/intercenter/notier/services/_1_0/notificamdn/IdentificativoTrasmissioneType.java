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

package it.rer.intercenter.notier.services._1_0.notificamdn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per IdentificativoTrasmissione_Type complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="IdentificativoTrasmissione_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransmissionID" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}TransmissionID_Type"/&gt;
 *         &lt;element name="MessageID" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}MessageID_Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificativoTrasmissione_Type", propOrder = {
    "transmissionID",
    "messageID"
})
public class IdentificativoTrasmissioneType {

    @XmlElement(name = "TransmissionID", required = true)
    protected String transmissionID;
    @XmlElement(name = "MessageID", required = true)
    protected String messageID;

    /**
     * Recupera il valore della proprietà transmissionID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransmissionID() {
        return transmissionID;
    }

    /**
     * Imposta il valore della proprietà transmissionID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransmissionID(String value) {
        this.transmissionID = value;
    }

    /**
     * Recupera il valore della proprietà messageID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * Imposta il valore della proprietà messageID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageID(String value) {
        this.messageID = value;
    }

}
