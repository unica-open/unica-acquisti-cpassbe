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

package it.doqui.acta.acaris.archive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.IdSmistamentoType;
import it.doqui.acta.acaris.common.PropertiesType;


/**
 * &lt;p&gt;Classe Java per MoveDocumentPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MoveDocumentPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{common.acaris.acta.doqui.it}PropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="offlineMoveRequest" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="idSmistamentoType" type="{common.acaris.acta.doqui.it}IdSmistamentoType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MoveDocumentPropertiesType", propOrder = {
    "offlineMoveRequest",
    "idSmistamentoType"
})
public class MoveDocumentPropertiesType
    extends PropertiesType
{

    protected boolean offlineMoveRequest;
    protected IdSmistamentoType idSmistamentoType;

    /**
     * Recupera il valore della proprietà offlineMoveRequest.
     * 
     */
    public boolean isOfflineMoveRequest() {
        return offlineMoveRequest;
    }

    /**
     * Imposta il valore della proprietà offlineMoveRequest.
     * 
     */
    public void setOfflineMoveRequest(boolean value) {
        this.offlineMoveRequest = value;
    }

    /**
     * Recupera il valore della proprietà idSmistamentoType.
     * 
     * @return
     *     possible object is
     *     {@link IdSmistamentoType }
     *     
     */
    public IdSmistamentoType getIdSmistamentoType() {
        return idSmistamentoType;
    }

    /**
     * Imposta il valore della proprietà idSmistamentoType.
     * 
     * @param value
     *     allowed object is
     *     {@link IdSmistamentoType }
     *     
     */
    public void setIdSmistamentoType(IdSmistamentoType value) {
        this.idSmistamentoType = value;
    }

}
