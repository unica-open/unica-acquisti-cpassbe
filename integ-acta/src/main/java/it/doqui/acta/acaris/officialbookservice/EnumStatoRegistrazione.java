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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumStatoRegistrazione.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumStatoRegistrazione"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Registrata"/&amp;gt;
 *     &amp;lt;enumeration value="Modificata"/&amp;gt;
 *     &amp;lt;enumeration value="Annullata"/&amp;gt;
 *     &amp;lt;enumeration value="DaInoltrareAltraAOO"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumStatoRegistrazione")
@XmlEnum
public enum EnumStatoRegistrazione {

    @XmlEnumValue("Registrata")
    REGISTRATA("Registrata"),
    @XmlEnumValue("Modificata")
    MODIFICATA("Modificata"),
    @XmlEnumValue("Annullata")
    ANNULLATA("Annullata"),
    @XmlEnumValue("DaInoltrareAltraAOO")
    DA_INOLTRARE_ALTRA_AOO("DaInoltrareAltraAOO");
    private final String value;

    EnumStatoRegistrazione(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumStatoRegistrazione fromValue(String v) {
        for (EnumStatoRegistrazione c: EnumStatoRegistrazione.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
