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
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PropertiesType;


/**
 * &lt;p&gt;Classe Java per DocumentoFisicoIRC complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DocumentoFisicoIRC"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{documentservice.acaris.acta.doqui.it}InfoRichiestaCreazione"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="documentoArchivistico" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="documentRootFolderId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="propertiesDocumentoFisico" type="{common.acaris.acta.doqui.it}PropertiesType"/&amp;gt;
 *         &amp;lt;element name="contenutiFisici" type="{documentservice.acaris.acta.doqui.it}ContenutoFisicoIRC" maxOccurs="unbounded"/&amp;gt;
 *         &amp;lt;element name="azioniVerificaFirma" type="{documentservice.acaris.acta.doqui.it}StepErrorAction" maxOccurs="7" minOccurs="7"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoFisicoIRC", propOrder = {
    "documentoArchivistico",
    "documentRootFolderId",
    "propertiesDocumentoFisico",
    "contenutiFisici",
    "azioniVerificaFirma"
})
public class DocumentoFisicoIRC
    extends InfoRichiestaCreazione
{

    @XmlElement(required = true)
    protected ObjectIdType documentoArchivistico;
    @XmlElement(required = true)
    protected ObjectIdType documentRootFolderId;
    @XmlElement(required = true)
    protected PropertiesType propertiesDocumentoFisico;
    @XmlElement(required = true)
    protected List<ContenutoFisicoIRC> contenutiFisici;
    @XmlElement(required = true)
    protected List<StepErrorAction> azioniVerificaFirma;

    /**
     * Recupera il valore della proprietà documentoArchivistico.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getDocumentoArchivistico() {
        return documentoArchivistico;
    }

    /**
     * Imposta il valore della proprietà documentoArchivistico.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setDocumentoArchivistico(ObjectIdType value) {
        this.documentoArchivistico = value;
    }

    /**
     * Recupera il valore della proprietà documentRootFolderId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getDocumentRootFolderId() {
        return documentRootFolderId;
    }

    /**
     * Imposta il valore della proprietà documentRootFolderId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setDocumentRootFolderId(ObjectIdType value) {
        this.documentRootFolderId = value;
    }

    /**
     * Recupera il valore della proprietà propertiesDocumentoFisico.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getPropertiesDocumentoFisico() {
        return propertiesDocumentoFisico;
    }

    /**
     * Imposta il valore della proprietà propertiesDocumentoFisico.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setPropertiesDocumentoFisico(PropertiesType value) {
        this.propertiesDocumentoFisico = value;
    }

    /**
     * Gets the value of the contenutiFisici property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the contenutiFisici property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getContenutiFisici().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ContenutoFisicoIRC }
     * 
     * 
     */
    public List<ContenutoFisicoIRC> getContenutiFisici() {
        if (contenutiFisici == null) {
            contenutiFisici = new ArrayList<ContenutoFisicoIRC>();
        }
        return this.contenutiFisici;
    }

    /**
     * Gets the value of the azioniVerificaFirma property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the azioniVerificaFirma property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getAzioniVerificaFirma().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link StepErrorAction }
     * 
     * 
     */
    public List<StepErrorAction> getAzioniVerificaFirma() {
        if (azioniVerificaFirma == null) {
            azioniVerificaFirma = new ArrayList<StepErrorAction>();
        }
        return this.azioniVerificaFirma;
    }

}
