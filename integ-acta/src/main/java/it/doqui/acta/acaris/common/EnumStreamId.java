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
 * &lt;p&gt;Classe Java per enumStreamId.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumStreamId"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="primary"/&amp;gt;
 *     &amp;lt;enumeration value="signature"/&amp;gt;
 *     &amp;lt;enumeration value="timestamp"/&amp;gt;
 *     &amp;lt;enumeration value="renditionEngine"/&amp;gt;
 *     &amp;lt;enumeration value="renditionDocument"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumStreamId")
@XmlEnum
public enum EnumStreamId {

    @XmlEnumValue("primary")
    PRIMARY("primary"),
    @XmlEnumValue("signature")
    SIGNATURE("signature"),
    @XmlEnumValue("timestamp")
    TIMESTAMP("timestamp"),
    @XmlEnumValue("renditionEngine")
    RENDITION_ENGINE("renditionEngine"),
    @XmlEnumValue("renditionDocument")
    RENDITION_DOCUMENT("renditionDocument");
    private final String value;

    EnumStreamId(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumStreamId fromValue(String v) {
        for (EnumStreamId c: EnumStreamId.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
