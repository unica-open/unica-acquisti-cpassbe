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
 * <p>Classe Java per Esito_Type complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Esito_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Documento" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}IdentificativoDocumento_Type"/&gt;
 *         &lt;element name="EsitoMDN" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}IdentificativoTrasmissione_Type"/&gt;
 *         &lt;element name="EsitoTrasmissione" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}EsitoTrasmissione_Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Esito_Type", propOrder = {
    "documento",
    "esitoMDN",
    "esitoTrasmissione"
})
public class EsitoType {

    @XmlElement(name = "Documento", required = true)
    protected IdentificativoDocumentoType documento;
    @XmlElement(name = "EsitoMDN", required = true)
    protected IdentificativoTrasmissioneType esitoMDN;
    @XmlElement(name = "EsitoTrasmissione", required = true)
    protected EsitoTrasmissioneType esitoTrasmissione;

    /**
     * Recupera il valore della proprietà documento.
     * 
     * @return
     *     possible object is
     *     {@link IdentificativoDocumentoType }
     *     
     */
    public IdentificativoDocumentoType getDocumento() {
        return documento;
    }

    /**
     * Imposta il valore della proprietà documento.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificativoDocumentoType }
     *     
     */
    public void setDocumento(IdentificativoDocumentoType value) {
        this.documento = value;
    }

    /**
     * Recupera il valore della proprietà esitoMDN.
     * 
     * @return
     *     possible object is
     *     {@link IdentificativoTrasmissioneType }
     *     
     */
    public IdentificativoTrasmissioneType getEsitoMDN() {
        return esitoMDN;
    }

    /**
     * Imposta il valore della proprietà esitoMDN.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificativoTrasmissioneType }
     *     
     */
    public void setEsitoMDN(IdentificativoTrasmissioneType value) {
        this.esitoMDN = value;
    }

    /**
     * Recupera il valore della proprietà esitoTrasmissione.
     * 
     * @return
     *     possible object is
     *     {@link EsitoTrasmissioneType }
     *     
     */
    public EsitoTrasmissioneType getEsitoTrasmissione() {
        return esitoTrasmissione;
    }

    /**
     * Imposta il valore della proprietà esitoTrasmissione.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoTrasmissioneType }
     *     
     */
    public void setEsitoTrasmissione(EsitoTrasmissioneType value) {
        this.esitoTrasmissione = value;
    }

}
