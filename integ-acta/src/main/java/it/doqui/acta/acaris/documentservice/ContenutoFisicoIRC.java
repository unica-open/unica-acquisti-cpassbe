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

import it.doqui.acta.acaris.common.AcarisContentStreamType;
import it.doqui.acta.acaris.common.ChangeTokenType;
import it.doqui.acta.acaris.common.EnumStreamId;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PropertiesType;


/**
 * &lt;p&gt;Classe Java per ContenutoFisicoIRC complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ContenutoFisicoIRC"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{documentservice.acaris.acta.doqui.it}InfoRichiestaCreazione"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="documentoFisico" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="dataUltimoAggiornamentoDocumento" type="{common.acaris.acta.doqui.it}ChangeTokenType"/&amp;gt;
 *         &amp;lt;element name="propertiesContenutoFisico" type="{common.acaris.acta.doqui.it}PropertiesType"/&amp;gt;
 *         &amp;lt;element name="tipo" type="{common.acaris.acta.doqui.it}enumStreamId"/&amp;gt;
 *         &amp;lt;element name="stream" type="{common.acaris.acta.doqui.it}acarisContentStreamType"/&amp;gt;
 *         &amp;lt;element name="azioniVerificaFirma" type="{documentservice.acaris.acta.doqui.it}StepErrorAction" maxOccurs="7" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContenutoFisicoIRC", propOrder = {
    "documentoFisico",
    "dataUltimoAggiornamentoDocumento",
    "propertiesContenutoFisico",
    "tipo",
    "stream",
    "azioniVerificaFirma"
})
public class ContenutoFisicoIRC
    extends InfoRichiestaCreazione
{

    @XmlElement(required = true)
    protected ObjectIdType documentoFisico;
    @XmlElement(required = true)
    protected ChangeTokenType dataUltimoAggiornamentoDocumento;
    @XmlElement(required = true)
    protected PropertiesType propertiesContenutoFisico;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumStreamId tipo;
    @XmlElement(required = true)
    protected AcarisContentStreamType stream;
    protected List<StepErrorAction> azioniVerificaFirma;

    /**
     * Recupera il valore della proprietà documentoFisico.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getDocumentoFisico() {
        return documentoFisico;
    }

    /**
     * Imposta il valore della proprietà documentoFisico.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setDocumentoFisico(ObjectIdType value) {
        this.documentoFisico = value;
    }

    /**
     * Recupera il valore della proprietà dataUltimoAggiornamentoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link ChangeTokenType }
     *     
     */
    public ChangeTokenType getDataUltimoAggiornamentoDocumento() {
        return dataUltimoAggiornamentoDocumento;
    }

    /**
     * Imposta il valore della proprietà dataUltimoAggiornamentoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeTokenType }
     *     
     */
    public void setDataUltimoAggiornamentoDocumento(ChangeTokenType value) {
        this.dataUltimoAggiornamentoDocumento = value;
    }

    /**
     * Recupera il valore della proprietà propertiesContenutoFisico.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getPropertiesContenutoFisico() {
        return propertiesContenutoFisico;
    }

    /**
     * Imposta il valore della proprietà propertiesContenutoFisico.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setPropertiesContenutoFisico(PropertiesType value) {
        this.propertiesContenutoFisico = value;
    }

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link EnumStreamId }
     *     
     */
    public EnumStreamId getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumStreamId }
     *     
     */
    public void setTipo(EnumStreamId value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà stream.
     * 
     * @return
     *     possible object is
     *     {@link AcarisContentStreamType }
     *     
     */
    public AcarisContentStreamType getStream() {
        return stream;
    }

    /**
     * Imposta il valore della proprietà stream.
     * 
     * @param value
     *     allowed object is
     *     {@link AcarisContentStreamType }
     *     
     */
    public void setStream(AcarisContentStreamType value) {
        this.stream = value;
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
