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


/**
 * &lt;p&gt;Classe Java per DocumentoArchivisticoIdMap complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DocumentoArchivisticoIdMap"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{documentservice.acaris.acta.doqui.it}MappaIdentificazioneDocumento"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="documentoArchivisticoId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="collocazioni" type="{documentservice.acaris.acta.doqui.it}CollocazioneDocumento" maxOccurs="unbounded"/&amp;gt;
 *         &amp;lt;element name="documentiFisiciIdMap" type="{documentservice.acaris.acta.doqui.it}DocumentoFisicoIdMap" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoArchivisticoIdMap", propOrder = {
    "documentoArchivisticoId",
    "collocazioni",
    "documentiFisiciIdMap"
})
public class DocumentoArchivisticoIdMap
    extends MappaIdentificazioneDocumento
{

    @XmlElement(required = true)
    protected ObjectIdType documentoArchivisticoId;
    @XmlElement(required = true)
    protected List<CollocazioneDocumento> collocazioni;
    protected List<DocumentoFisicoIdMap> documentiFisiciIdMap;

    /**
     * Recupera il valore della proprietà documentoArchivisticoId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getDocumentoArchivisticoId() {
        return documentoArchivisticoId;
    }

    /**
     * Imposta il valore della proprietà documentoArchivisticoId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setDocumentoArchivisticoId(ObjectIdType value) {
        this.documentoArchivisticoId = value;
    }

    /**
     * Gets the value of the collocazioni property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the collocazioni property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCollocazioni().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link CollocazioneDocumento }
     * 
     * 
     */
    public List<CollocazioneDocumento> getCollocazioni() {
        if (collocazioni == null) {
            collocazioni = new ArrayList<CollocazioneDocumento>();
        }
        return this.collocazioni;
    }

    /**
     * Gets the value of the documentiFisiciIdMap property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the documentiFisiciIdMap property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDocumentiFisiciIdMap().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoFisicoIdMap }
     * 
     * 
     */
    public List<DocumentoFisicoIdMap> getDocumentiFisiciIdMap() {
        if (documentiFisiciIdMap == null) {
            documentiFisiciIdMap = new ArrayList<DocumentoFisicoIdMap>();
        }
        return this.documentiFisiciIdMap;
    }

}
