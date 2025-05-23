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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per MappaIdentificazioneDocumento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="MappaIdentificazioneDocumento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="rappresentazioneLimitata" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MappaIdentificazioneDocumento", propOrder = {
    "rappresentazioneLimitata"
})
@XmlSeeAlso({
    DocumentoArchivisticoIdMap.class,
    DocumentoFisicoIdMap.class,
    ContenutoFisicoIdMap.class
})
public abstract class MappaIdentificazioneDocumento {

    protected boolean rappresentazioneLimitata;

    /**
     * Recupera il valore della proprietà rappresentazioneLimitata.
     * 
     */
    public boolean isRappresentazioneLimitata() {
        return rappresentazioneLimitata;
    }

    /**
     * Imposta il valore della proprietà rappresentazioneLimitata.
     * 
     */
    public void setRappresentazioneLimitata(boolean value) {
        this.rappresentazioneLimitata = value;
    }

}
