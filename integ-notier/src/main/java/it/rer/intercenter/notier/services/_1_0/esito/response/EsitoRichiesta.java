/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.rer.intercenter.notier.services._1_0.esito.response;

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
 *         &lt;element name="Versione" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TokenNVMax10Type"/&gt;
 *         &lt;element name="Esito" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}EsitoType"/&gt;
 *         &lt;element name="Documento" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}DocumentoType" minOccurs="0"/&gt;
 *         &lt;element name="ListaDocumenti" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}ListaDocumentiType" minOccurs="0"/&gt;
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
    "esito",
    "documento",
    "listaDocumenti"
})
@XmlRootElement(name = "EsitoRichiesta")
public class EsitoRichiesta {

    @XmlElement(name = "Versione", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String versione;
    @XmlElement(name = "Esito", required = true)
    protected EsitoType esito;
    @XmlElement(name = "Documento")
    protected DocumentoType documento;
    @XmlElement(name = "ListaDocumenti")
    protected ListaDocumentiType listaDocumenti;

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
     * Recupera il valore della proprietà esito.
     * 
     * @return
     *     possible object is
     *     {@link EsitoType }
     *     
     */
    public EsitoType getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della proprietà esito.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoType }
     *     
     */
    public void setEsito(EsitoType value) {
        this.esito = value;
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
     * Recupera il valore della proprietà listaDocumenti.
     * 
     * @return
     *     possible object is
     *     {@link ListaDocumentiType }
     *     
     */
    public ListaDocumentiType getListaDocumenti() {
        return listaDocumenti;
    }

    /**
     * Imposta il valore della proprietà listaDocumenti.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDocumentiType }
     *     
     */
    public void setListaDocumenti(ListaDocumentiType value) {
        this.listaDocumenti = value;
    }

}
