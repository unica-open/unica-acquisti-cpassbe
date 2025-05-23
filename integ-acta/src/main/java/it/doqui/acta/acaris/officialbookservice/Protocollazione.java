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
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per Protocollazione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Protocollazione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}RegistrazioneRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="creazioneAsincronaDocumento" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="folderId" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="folderType" type="{officialbookservice.acaris.acta.doqui.it}enumTipoContenitore" minOccurs="0"/&amp;gt;
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
@XmlType(name = "Protocollazione", propOrder = {
    "creazioneAsincronaDocumento",
    "folderId",
    "folderType",
    "registrazioneAPI"
})
public class Protocollazione
    extends RegistrazioneRequest
{

    protected boolean creazioneAsincronaDocumento;
    protected ObjectIdType folderId;
    @XmlSchemaType(name = "string")
    protected EnumTipoContenitore folderType;
    @XmlElement(required = true)
    protected RegistrazioneAPI registrazioneAPI;

    /**
     * Recupera il valore della proprietà creazioneAsincronaDocumento.
     * 
     */
    public boolean isCreazioneAsincronaDocumento() {
        return creazioneAsincronaDocumento;
    }

    /**
     * Imposta il valore della proprietà creazioneAsincronaDocumento.
     * 
     */
    public void setCreazioneAsincronaDocumento(boolean value) {
        this.creazioneAsincronaDocumento = value;
    }

    /**
     * Recupera il valore della proprietà folderId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getFolderId() {
        return folderId;
    }

    /**
     * Imposta il valore della proprietà folderId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setFolderId(ObjectIdType value) {
        this.folderId = value;
    }

    /**
     * Recupera il valore della proprietà folderType.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoContenitore }
     *     
     */
    public EnumTipoContenitore getFolderType() {
        return folderType;
    }

    /**
     * Imposta il valore della proprietà folderType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoContenitore }
     *     
     */
    public void setFolderType(EnumTipoContenitore value) {
        this.folderType = value;
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
