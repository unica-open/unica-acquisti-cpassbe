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
 * &lt;p&gt;Classe Java per enumQueryOperator.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumQueryOperator"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="equals"/&amp;gt;
 *     &amp;lt;enumeration value="greaterThan"/&amp;gt;
 *     &amp;lt;enumeration value="lessThan"/&amp;gt;
 *     &amp;lt;enumeration value="greaterThanOrEqualTo"/&amp;gt;
 *     &amp;lt;enumeration value="lessThanOrEqualTo"/&amp;gt;
 *     &amp;lt;enumeration value="like"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumQueryOperator")
@XmlEnum
public enum EnumQueryOperator {

    @XmlEnumValue("equals")
    EQUALS("equals"),
    @XmlEnumValue("greaterThan")
    GREATER_THAN("greaterThan"),
    @XmlEnumValue("lessThan")
    LESS_THAN("lessThan"),
    @XmlEnumValue("greaterThanOrEqualTo")
    GREATER_THAN_OR_EQUAL_TO("greaterThanOrEqualTo"),
    @XmlEnumValue("lessThanOrEqualTo")
    LESS_THAN_OR_EQUAL_TO("lessThanOrEqualTo"),
    @XmlEnumValue("like")
    LIKE("like");
    private final String value;

    EnumQueryOperator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumQueryOperator fromValue(String v) {
        for (EnumQueryOperator c: EnumQueryOperator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
