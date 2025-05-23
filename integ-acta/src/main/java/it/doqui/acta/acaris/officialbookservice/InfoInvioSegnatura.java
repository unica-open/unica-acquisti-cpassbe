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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per InfoInvioSegnatura complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="InfoInvioSegnatura"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="invioMultiplo" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="forzaturaAssenzaSigillo" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="invioNonPrioritario" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="copiaLavoro" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoInvioSegnatura", propOrder = {
    "invioMultiplo",
    "forzaturaAssenzaSigillo",
    "invioNonPrioritario",
    "copiaLavoro"
})
public class InfoInvioSegnatura {

    protected boolean invioMultiplo;
    protected boolean forzaturaAssenzaSigillo;
    protected boolean invioNonPrioritario;
    protected boolean copiaLavoro;

    /**
     * Recupera il valore della proprietà invioMultiplo.
     * 
     */
    public boolean isInvioMultiplo() {
        return invioMultiplo;
    }

    /**
     * Imposta il valore della proprietà invioMultiplo.
     * 
     */
    public void setInvioMultiplo(boolean value) {
        this.invioMultiplo = value;
    }

    /**
     * Recupera il valore della proprietà forzaturaAssenzaSigillo.
     * 
     */
    public boolean isForzaturaAssenzaSigillo() {
        return forzaturaAssenzaSigillo;
    }

    /**
     * Imposta il valore della proprietà forzaturaAssenzaSigillo.
     * 
     */
    public void setForzaturaAssenzaSigillo(boolean value) {
        this.forzaturaAssenzaSigillo = value;
    }

    /**
     * Recupera il valore della proprietà invioNonPrioritario.
     * 
     */
    public boolean isInvioNonPrioritario() {
        return invioNonPrioritario;
    }

    /**
     * Imposta il valore della proprietà invioNonPrioritario.
     * 
     */
    public void setInvioNonPrioritario(boolean value) {
        this.invioNonPrioritario = value;
    }

    /**
     * Recupera il valore della proprietà copiaLavoro.
     * 
     */
    public boolean isCopiaLavoro() {
        return copiaLavoro;
    }

    /**
     * Imposta il valore della proprietà copiaLavoro.
     * 
     */
    public void setCopiaLavoro(boolean value) {
        this.copiaLavoro = value;
    }

}
