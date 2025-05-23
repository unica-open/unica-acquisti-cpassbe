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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Versione" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}TokenNVMax10Type"/&gt;
 *         &lt;element name="Documento" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}DocumentoType"/&gt;
 *         &lt;element name="Configurazione" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}ConfigurazioneType"/&gt;
 *         &lt;element name="Integrazione" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}IntegrazioneType" minOccurs="0"/&gt;
 *         &lt;element name="Collegamento" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}CollegamentoType" minOccurs="0"/&gt;
 *         &lt;element name="MetadatiBusdox" type="{http://notier.intercenter.rer.it/services/1.0/inviodocumento/request}MetadatiBusdoxType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "versione",
    "documento",
    "configurazione",
    "integrazione",
    "collegamento",
    "metadatiBusdox"
})
@XmlRootElement(name = "InvioDocumento")
public class InvioDocumento {

    @XmlElement(name = "Versione", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String versione;
    @XmlElement(name = "Documento", required = true)
    protected DocumentoType documento;
    @XmlElement(name = "Configurazione", required = true)
    protected ConfigurazioneType configurazione;
    @XmlElement(name = "Integrazione")
    protected IntegrazioneType integrazione;
    @XmlElement(name = "Collegamento")
    protected CollegamentoType collegamento;
    @XmlElement(name = "MetadatiBusdox")
    protected MetadatiBusdoxType metadatiBusdox;

    /**
     * Recupera il valore della proprietà versione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersione() {
        return versione;
    }

    /**
     * Imposta il valore della proprietà versione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersione(String value) {
        this.versione = value;
    }

    /**
     * Recupera il valore della proprietà documento.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoType }
     *     
     */
    public DocumentoType getDocumento() {
        return documento;
    }

    /**
     * Imposta il valore della proprietà documento.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoType }
     *     
     */
    public void setDocumento(DocumentoType value) {
        this.documento = value;
    }

    /**
     * Recupera il valore della proprietà configurazione.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurazioneType }
     *     
     */
    public ConfigurazioneType getConfigurazione() {
        return configurazione;
    }

    /**
     * Imposta il valore della proprietà configurazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurazioneType }
     *     
     */
    public void setConfigurazione(ConfigurazioneType value) {
        this.configurazione = value;
    }

    /**
     * Recupera il valore della proprietà integrazione.
     * 
     * @return
     *     possible object is
     *     {@link IntegrazioneType }
     *     
     */
    public IntegrazioneType getIntegrazione() {
        return integrazione;
    }

    /**
     * Imposta il valore della proprietà integrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegrazioneType }
     *     
     */
    public void setIntegrazione(IntegrazioneType value) {
        this.integrazione = value;
    }

    /**
     * Recupera il valore della proprietà collegamento.
     * 
     * @return
     *     possible object is
     *     {@link CollegamentoType }
     *     
     */
    public CollegamentoType getCollegamento() {
        return collegamento;
    }

    /**
     * Imposta il valore della proprietà collegamento.
     * 
     * @param value
     *     allowed object is
     *     {@link CollegamentoType }
     *     
     */
    public void setCollegamento(CollegamentoType value) {
        this.collegamento = value;
    }

    /**
     * Recupera il valore della proprietà metadatiBusdox.
     * 
     * @return
     *     possible object is
     *     {@link MetadatiBusdoxType }
     *     
     */
    public MetadatiBusdoxType getMetadatiBusdox() {
        return metadatiBusdox;
    }

    /**
     * Imposta il valore della proprietà metadatiBusdox.
     * 
     * @param value
     *     allowed object is
     *     {@link MetadatiBusdoxType }
     *     
     */
    public void setMetadatiBusdox(MetadatiBusdoxType value) {
        this.metadatiBusdox = value;
    }

}
