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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumClassificazioneStatoType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumClassificazioneStatoType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Attiva"/&amp;gt;
 *     &amp;lt;enumeration value="Ri-classificato"/&amp;gt;
 *     &amp;lt;enumeration value="Cancellato"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumClassificazioneStatoType")
@XmlEnum
public enum EnumClassificazioneStatoType {

    @XmlEnumValue("Attiva")
    ATTIVA("Attiva"),
    @XmlEnumValue("Ri-classificato")
    RI_CLASSIFICATO("Ri-classificato"),
    @XmlEnumValue("Cancellato")
    CANCELLATO("Cancellato");
    private final String value;

    EnumClassificazioneStatoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumClassificazioneStatoType fromValue(String v) {
        for (EnumClassificazioneStatoType c: EnumClassificazioneStatoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
