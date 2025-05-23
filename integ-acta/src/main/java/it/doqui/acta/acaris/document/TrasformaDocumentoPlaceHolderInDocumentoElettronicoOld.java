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

package it.doqui.acta.acaris.document;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.AcarisContentStreamType;
import it.doqui.acta.acaris.common.ObjectIdType;
import it.doqui.acta.acaris.common.PrincipalIdType;
import it.doqui.acta.acaris.common.PropertiesType;


/**
 * &lt;p&gt;Classe Java per anonymous complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="repositoryId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="principalId" type="{common.acaris.acta.doqui.it}PrincipalIdType"/&amp;gt;
 *         &amp;lt;element name="placeHolderId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="placeHolderId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="tipoDocFisicoId" type="{common.acaris.acta.doqui.it}integer"/&amp;gt;
 *         &amp;lt;element name="composizioneId" type="{common.acaris.acta.doqui.it}integer"/&amp;gt;
 *         &amp;lt;element name="multiplo" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="rimandareOperazioneSbustamento" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="documentoFisico" type="{common.acaris.acta.doqui.it}PropertiesType" maxOccurs="unbounded"/&amp;gt;
 *         &amp;lt;element name="contenutoFisico" type="{common.acaris.acta.doqui.it}PropertiesType" maxOccurs="unbounded"/&amp;gt;
 *         &amp;lt;element name="contentStream" type="{common.acaris.acta.doqui.it}acarisContentStreamType" maxOccurs="unbounded"/&amp;gt;
 *         &amp;lt;element name="annotazione" type="{common.acaris.acta.doqui.it}PropertiesType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "trasformaDocumentoPlaceHolderInDocumentoElettronicoOld")
public class TrasformaDocumentoPlaceHolderInDocumentoElettronicoOld {

    @XmlElementRefs({
        @XmlElementRef(name = "repositoryId", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "principalId", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "placeHolderId", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tipoDocFisicoId", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "composizioneId", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "multiplo", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "rimandareOperazioneSbustamento", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "documentoFisico", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "contenutoFisico", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "contentStream", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "annotazione", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Recupera il resto del modello di contenuto. 
     * 
     * &lt;p&gt;
     * Questa proprietà "catch-all" viene recuperata per il seguente motivo: 
     * Il nome di campo "PlaceHolderId" è usato da due diverse parti di uno schema. Vedere: 
     * riga 66 di http://tst-applogic.reteunitaria.piemonte.it/actasrv/documentWS?xsd=ACARIS-DocumentMessaging.xsd
     * riga 65 di http://tst-applogic.reteunitaria.piemonte.it/actasrv/documentWS?xsd=ACARIS-DocumentMessaging.xsd
     * &lt;p&gt;
     * Per eliminare questa proprietà, applicare una personalizzazione della proprietà a una 
     * delle seguenti due dichiarazioni per modificarne il nome: 
     * Gets the value of the content property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the content property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getContent().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ObjectIdType }{@code >}
     * {@link JAXBElement }{@code <}{@link PrincipalIdType }{@code >}
     * {@link JAXBElement }{@code <}{@link ObjectIdType }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * {@link JAXBElement }{@code <}{@link PropertiesType }{@code >}
     * {@link JAXBElement }{@code <}{@link PropertiesType }{@code >}
     * {@link JAXBElement }{@code <}{@link AcarisContentStreamType }{@code >}
     * {@link JAXBElement }{@code <}{@link PropertiesType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

}
