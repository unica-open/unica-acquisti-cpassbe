/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.rer.intercenter.notier.services._1_0.esito.response;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per StatoConservazioneType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="StatoConservazioneType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="DA_NON_INVIARE"/&gt;
 *     &lt;enumeration value="IN_ATTESA_DATI_FISCALI"/&gt;
 *     &lt;enumeration value="DA_INVIARE"/&gt;
 *     &lt;enumeration value="INVIATO_OK"/&gt;
 *     &lt;enumeration value="INVIATO_KO"/&gt;
 *     &lt;enumeration value="DA_ANNULLARE"/&gt;
 *     &lt;enumeration value="ANNULLATO"/&gt;
 *     &lt;enumeration value="NON_ANNULLABILE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "StatoConservazioneType")
@XmlEnum
public enum StatoConservazioneType {

    DA_NON_INVIARE,
    IN_ATTESA_DATI_FISCALI,
    DA_INVIARE,
    INVIATO_OK,
    INVIATO_KO,
    DA_ANNULLARE,
    ANNULLATO,
    NON_ANNULLABILE;

    public String value() {
        return name();
    }

    public static StatoConservazioneType fromValue(String v) {
        return valueOf(v);
    }

}
