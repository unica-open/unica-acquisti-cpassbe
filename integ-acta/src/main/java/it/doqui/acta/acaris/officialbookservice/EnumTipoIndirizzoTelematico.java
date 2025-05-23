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
 * &lt;p&gt;Classe Java per enumTipoIndirizzoTelematico.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumTipoIndirizzoTelematico"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="smtp"/&amp;gt;
 *     &amp;lt;enumeration value="url"/&amp;gt;
 *     &amp;lt;enumeration value="other"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumTipoIndirizzoTelematico")
@XmlEnum
public enum EnumTipoIndirizzoTelematico {

    @XmlEnumValue("smtp")
    SMTP("smtp"),
    @XmlEnumValue("url")
    URL("url"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    EnumTipoIndirizzoTelematico(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoIndirizzoTelematico fromValue(String v) {
        for (EnumTipoIndirizzoTelematico c: EnumTipoIndirizzoTelematico.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
