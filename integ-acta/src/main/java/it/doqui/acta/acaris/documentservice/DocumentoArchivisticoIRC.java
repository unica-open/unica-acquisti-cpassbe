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

package it.doqui.acta.acaris.documentservice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.archive.GruppoAllegatiPropertiesType;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PropertiesType;


/**
 * &lt;p&gt;Classe Java per DocumentoArchivisticoIRC complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DocumentoArchivisticoIRC"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{documentservice.acaris.acta.doqui.it}InfoRichiestaCreazione"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="parentFolderId" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoDocumento" type="{documentservice.acaris.acta.doqui.it}enumTipoDocumentoArchivistico"/&amp;gt;
 *         &amp;lt;element name="allegato" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="gruppoAllegati" type="{archive.acaris.acta.doqui.it}GruppoAllegatiPropertiesType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="classificazionePrincipale" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="propertiesDocumento" type="{common.acaris.acta.doqui.it}PropertiesType"/&amp;gt;
 *         &amp;lt;element name="propertiesClassificazione" type="{common.acaris.acta.doqui.it}PropertiesType"/&amp;gt;
 *         &amp;lt;element name="documentiFisici" type="{documentservice.acaris.acta.doqui.it}DocumentoFisicoIRC" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoArchivisticoIRC", propOrder = {
    "parentFolderId",
    "tipoDocumento",
    "allegato",
    "gruppoAllegati",
    "classificazionePrincipale",
    "propertiesDocumento",
    "propertiesClassificazione",
    "documentiFisici"
})
public class DocumentoArchivisticoIRC
    extends InfoRichiestaCreazione
{

    protected ObjectIdType parentFolderId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipoDocumentoArchivistico tipoDocumento;
    protected boolean allegato;
    protected GruppoAllegatiPropertiesType gruppoAllegati;
    @XmlElement(required = true)
    protected ObjectIdType classificazionePrincipale;
    @XmlElement(required = true)
    protected PropertiesType propertiesDocumento;
    @XmlElement(required = true)
    protected PropertiesType propertiesClassificazione;
    protected List<DocumentoFisicoIRC> documentiFisici;

    /**
     * Recupera il valore della proprietà parentFolderId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getParentFolderId() {
        return parentFolderId;
    }

    /**
     * Imposta il valore della proprietà parentFolderId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setParentFolderId(ObjectIdType value) {
        this.parentFolderId = value;
    }

    /**
     * Recupera il valore della proprietà tipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoDocumentoArchivistico }
     *     
     */
    public EnumTipoDocumentoArchivistico getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Imposta il valore della proprietà tipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoDocumentoArchivistico }
     *     
     */
    public void setTipoDocumento(EnumTipoDocumentoArchivistico value) {
        this.tipoDocumento = value;
    }

    /**
     * Recupera il valore della proprietà allegato.
     * 
     */
    public boolean isAllegato() {
        return allegato;
    }

    /**
     * Imposta il valore della proprietà allegato.
     * 
     */
    public void setAllegato(boolean value) {
        this.allegato = value;
    }

    /**
     * Recupera il valore della proprietà gruppoAllegati.
     * 
     * @return
     *     possible object is
     *     {@link GruppoAllegatiPropertiesType }
     *     
     */
    public GruppoAllegatiPropertiesType getGruppoAllegati() {
        return gruppoAllegati;
    }

    /**
     * Imposta il valore della proprietà gruppoAllegati.
     * 
     * @param value
     *     allowed object is
     *     {@link GruppoAllegatiPropertiesType }
     *     
     */
    public void setGruppoAllegati(GruppoAllegatiPropertiesType value) {
        this.gruppoAllegati = value;
    }

    /**
     * Recupera il valore della proprietà classificazionePrincipale.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getClassificazionePrincipale() {
        return classificazionePrincipale;
    }

    /**
     * Imposta il valore della proprietà classificazionePrincipale.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setClassificazionePrincipale(ObjectIdType value) {
        this.classificazionePrincipale = value;
    }

    /**
     * Recupera il valore della proprietà propertiesDocumento.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getPropertiesDocumento() {
        return propertiesDocumento;
    }

    /**
     * Imposta il valore della proprietà propertiesDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setPropertiesDocumento(PropertiesType value) {
        this.propertiesDocumento = value;
    }

    /**
     * Recupera il valore della proprietà propertiesClassificazione.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getPropertiesClassificazione() {
        return propertiesClassificazione;
    }

    /**
     * Imposta il valore della proprietà propertiesClassificazione.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setPropertiesClassificazione(PropertiesType value) {
        this.propertiesClassificazione = value;
    }

    /**
     * Gets the value of the documentiFisici property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the documentiFisici property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDocumentiFisici().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoFisicoIRC }
     * 
     * 
     */
    public List<DocumentoFisicoIRC> getDocumentiFisici() {
        if (documentiFisici == null) {
            documentiFisici = new ArrayList<DocumentoFisicoIRC>();
        }
        return this.documentiFisici;
    }

}
