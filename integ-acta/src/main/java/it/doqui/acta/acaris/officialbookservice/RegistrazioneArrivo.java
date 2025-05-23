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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per RegistrazioneArrivo complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="RegistrazioneArrivo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}RegistrazioneAPI"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="infoDateArrivo" type="{officialbookservice.acaris.acta.doqui.it}InfoDateArrivo" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="infoProtocolloMittente" type="{officialbookservice.acaris.acta.doqui.it}InfoProtocolloMittente" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="mittenteEsterno" type="{officialbookservice.acaris.acta.doqui.it}MittenteEsterno" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrazioneArrivo", propOrder = {
    "infoDateArrivo",
    "infoProtocolloMittente",
    "mittenteEsterno"
})
public class RegistrazioneArrivo
    extends RegistrazioneAPI
{

    protected InfoDateArrivo infoDateArrivo;
    protected InfoProtocolloMittente infoProtocolloMittente;
    protected List<MittenteEsterno> mittenteEsterno;

    /**
     * Recupera il valore della proprietà infoDateArrivo.
     * 
     * @return
     *     possible object is
     *     {@link InfoDateArrivo }
     *     
     */
    public InfoDateArrivo getInfoDateArrivo() {
        return infoDateArrivo;
    }

    /**
     * Imposta il valore della proprietà infoDateArrivo.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoDateArrivo }
     *     
     */
    public void setInfoDateArrivo(InfoDateArrivo value) {
        this.infoDateArrivo = value;
    }

    /**
     * Recupera il valore della proprietà infoProtocolloMittente.
     * 
     * @return
     *     possible object is
     *     {@link InfoProtocolloMittente }
     *     
     */
    public InfoProtocolloMittente getInfoProtocolloMittente() {
        return infoProtocolloMittente;
    }

    /**
     * Imposta il valore della proprietà infoProtocolloMittente.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoProtocolloMittente }
     *     
     */
    public void setInfoProtocolloMittente(InfoProtocolloMittente value) {
        this.infoProtocolloMittente = value;
    }

    /**
     * Gets the value of the mittenteEsterno property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the mittenteEsterno property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getMittenteEsterno().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link MittenteEsterno }
     * 
     * 
     */
    public List<MittenteEsterno> getMittenteEsterno() {
        if (mittenteEsterno == null) {
            mittenteEsterno = new ArrayList<MittenteEsterno>();
        }
        return this.mittenteEsterno;
    }

}
