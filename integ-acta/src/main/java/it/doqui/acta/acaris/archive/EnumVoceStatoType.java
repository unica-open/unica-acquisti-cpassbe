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
 * &lt;p&gt;Classe Java per enumVoceStatoType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumVoceStatoType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Bozza"/&amp;gt;
 *     &amp;lt;enumeration value="Preattiva"/&amp;gt;
 *     &amp;lt;enumeration value="Attiva"/&amp;gt;
 *     &amp;lt;enumeration value="Disattiva"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumVoceStatoType")
@XmlEnum
public enum EnumVoceStatoType {

    @XmlEnumValue("Bozza")
    BOZZA("Bozza"),
    @XmlEnumValue("Preattiva")
    PREATTIVA("Preattiva"),
    @XmlEnumValue("Attiva")
    ATTIVA("Attiva"),
    @XmlEnumValue("Disattiva")
    DISATTIVA("Disattiva");
    private final String value;

    EnumVoceStatoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumVoceStatoType fromValue(String v) {
        for (EnumVoceStatoType c: EnumVoceStatoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
