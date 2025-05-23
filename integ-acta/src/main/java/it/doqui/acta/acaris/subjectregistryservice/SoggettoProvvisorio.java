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

package it.doqui.acta.acaris.subjectregistryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per SoggettoProvvisorio complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SoggettoProvvisorio"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{subjectregistryservice.acaris.acta.doqui.it}SoggettoRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="infoComuniCreazioneSoggetto" type="{subjectregistryservice.acaris.acta.doqui.it}InfoComuniCreazioneSoggetto"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoProvvisorio", propOrder = {
    "infoComuniCreazioneSoggetto"
})
public class SoggettoProvvisorio
    extends SoggettoRequest
{

    @XmlElement(required = true)
    protected InfoComuniCreazioneSoggetto infoComuniCreazioneSoggetto;

    /**
     * Recupera il valore della proprietà infoComuniCreazioneSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link InfoComuniCreazioneSoggetto }
     *     
     */
    public InfoComuniCreazioneSoggetto getInfoComuniCreazioneSoggetto() {
        return infoComuniCreazioneSoggetto;
    }

    /**
     * Imposta il valore della proprietà infoComuniCreazioneSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoComuniCreazioneSoggetto }
     *     
     */
    public void setInfoComuniCreazioneSoggetto(InfoComuniCreazioneSoggetto value) {
        this.infoComuniCreazioneSoggetto = value;
    }

}
