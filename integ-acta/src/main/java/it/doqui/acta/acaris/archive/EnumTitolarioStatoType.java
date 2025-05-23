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
 * &lt;p&gt;Classe Java per enumTitolarioStatoType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTitolarioStatoType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Bozza"/&amp;gt;
 *     &amp;lt;enumeration value="Preattivo"/&amp;gt;
 *     &amp;lt;enumeration value="Attivo"/&amp;gt;
 *     &amp;lt;enumeration value="Disattivo"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTitolarioStatoType")
@XmlEnum
public enum EnumTitolarioStatoType {

    @XmlEnumValue("Bozza")
    BOZZA("Bozza"),
    @XmlEnumValue("Preattivo")
    PREATTIVO("Preattivo"),
    @XmlEnumValue("Attivo")
    ATTIVO("Attivo"),
    @XmlEnumValue("Disattivo")
    DISATTIVO("Disattivo");
    private final String value;

    EnumTitolarioStatoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTitolarioStatoType fromValue(String v) {
        for (EnumTitolarioStatoType c: EnumTitolarioStatoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
