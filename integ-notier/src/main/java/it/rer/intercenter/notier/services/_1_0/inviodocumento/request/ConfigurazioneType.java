/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.rer.intercenter.notier.services._1_0.inviodocumento.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ConfigurazioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ConfigurazioneType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InvioSdi" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="InvioPeppol" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="InvioConservazione" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ApprovazioneAutomatica" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="AccettaChiaveDuplicata" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigurazioneType", propOrder = {
    "invioSdi",
    "invioPeppol",
    "invioConservazione",
    "approvazioneAutomatica",
    "accettaChiaveDuplicata"
})
public class ConfigurazioneType {

    @XmlElement(name = "InvioSdi")
    protected boolean invioSdi;
    @XmlElement(name = "InvioPeppol")
    protected boolean invioPeppol;
    @XmlElement(name = "InvioConservazione")
    protected boolean invioConservazione;
    @XmlElement(name = "ApprovazioneAutomatica")
    protected boolean approvazioneAutomatica;
    @XmlElement(name = "AccettaChiaveDuplicata")
    protected boolean accettaChiaveDuplicata;

    /**
     * Recupera il valore della proprietà invioSdi.
     * 
     */
    public boolean isInvioSdi() {
        return invioSdi;
    }

    /**
     * Imposta il valore della proprietà invioSdi.
     * 
     */
    public void setInvioSdi(boolean value) {
        this.invioSdi = value;
    }

    /**
     * Recupera il valore della proprietà invioPeppol.
     * 
     */
    public boolean isInvioPeppol() {
        return invioPeppol;
    }

    /**
     * Imposta il valore della proprietà invioPeppol.
     * 
     */
    public void setInvioPeppol(boolean value) {
        this.invioPeppol = value;
    }

    /**
     * Recupera il valore della proprietà invioConservazione.
     * 
     */
    public boolean isInvioConservazione() {
        return invioConservazione;
    }

    /**
     * Imposta il valore della proprietà invioConservazione.
     * 
     */
    public void setInvioConservazione(boolean value) {
        this.invioConservazione = value;
    }

    /**
     * Recupera il valore della proprietà approvazioneAutomatica.
     * 
     */
    public boolean isApprovazioneAutomatica() {
        return approvazioneAutomatica;
    }

    /**
     * Imposta il valore della proprietà approvazioneAutomatica.
     * 
     */
    public void setApprovazioneAutomatica(boolean value) {
        this.approvazioneAutomatica = value;
    }

    /**
     * Recupera il valore della proprietà accettaChiaveDuplicata.
     * 
     */
    public boolean isAccettaChiaveDuplicata() {
        return accettaChiaveDuplicata;
    }

    /**
     * Imposta il valore della proprietà accettaChiaveDuplicata.
     * 
     */
    public void setAccettaChiaveDuplicata(boolean value) {
        this.accettaChiaveDuplicata = value;
    }

}
