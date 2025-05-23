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
 * <p>Classe Java per DataOraTransitoDocumento_Type complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DataOraTransitoDocumento_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DataInvioSuPeppol" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}Data_Type" minOccurs="0"/&gt;
 *         &lt;element name="OrarioInvioSuPeppol" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}Orario_Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataOraTransitoDocumento_Type", propOrder = {
    "dataInvioSuPeppol",
    "orarioInvioSuPeppol"
})
public class DataOraTransitoDocumentoType {

    @XmlElement(name = "DataInvioSuPeppol")
    protected String dataInvioSuPeppol;
    @XmlElement(name = "OrarioInvioSuPeppol")
    protected String orarioInvioSuPeppol;

    /**
     * Recupera il valore della proprietà dataInvioSuPeppol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataInvioSuPeppol() {
        return dataInvioSuPeppol;
    }

    /**
     * Imposta il valore della proprietà dataInvioSuPeppol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInvioSuPeppol(String value) {
        this.dataInvioSuPeppol = value;
    }

    /**
     * Recupera il valore della proprietà orarioInvioSuPeppol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrarioInvioSuPeppol() {
        return orarioInvioSuPeppol;
    }

    /**
     * Imposta il valore della proprietà orarioInvioSuPeppol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrarioInvioSuPeppol(String value) {
        this.orarioInvioSuPeppol = value;
    }

}
