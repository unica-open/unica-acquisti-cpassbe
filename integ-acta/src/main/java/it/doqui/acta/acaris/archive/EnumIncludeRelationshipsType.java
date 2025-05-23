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
 * &lt;p&gt;Classe Java per enumIncludeRelationshipsType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumIncludeRelationshipsType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="none"/&amp;gt;
 *     &amp;lt;enumeration value="source"/&amp;gt;
 *     &amp;lt;enumeration value="target"/&amp;gt;
 *     &amp;lt;enumeration value="both"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumIncludeRelationshipsType")
@XmlEnum
public enum EnumIncludeRelationshipsType {

    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("source")
    SOURCE("source"),
    @XmlEnumValue("target")
    TARGET("target"),
    @XmlEnumValue("both")
    BOTH("both");
    private final String value;

    EnumIncludeRelationshipsType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumIncludeRelationshipsType fromValue(String v) {
        for (EnumIncludeRelationshipsType c: EnumIncludeRelationshipsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
