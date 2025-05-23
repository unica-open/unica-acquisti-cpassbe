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

package it.doqui.acta.acaris.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumPropertyFilter.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumPropertyFilter"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="none"/&amp;gt;
 *     &amp;lt;enumeration value="all"/&amp;gt;
 *     &amp;lt;enumeration value="list"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumPropertyFilter")
@XmlEnum
public enum EnumPropertyFilter {

    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("list")
    LIST("list");
    private final String value;

    EnumPropertyFilter(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumPropertyFilter fromValue(String v) {
        for (EnumPropertyFilter c: EnumPropertyFilter.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
