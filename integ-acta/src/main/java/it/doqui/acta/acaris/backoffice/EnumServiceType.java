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

package it.doqui.acta.acaris.backoffice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumServiceType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumServiceType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Archive"/&amp;gt;
 *     &amp;lt;enumeration value="BackOffice"/&amp;gt;
 *     &amp;lt;enumeration value="Management"/&amp;gt;
 *     &amp;lt;enumeration value="OfficialBook"/&amp;gt;
 *     &amp;lt;enumeration value="Sms"/&amp;gt;
 *     &amp;lt;enumeration value="SubjectRegistry"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumServiceType")
@XmlEnum
public enum EnumServiceType {

    @XmlEnumValue("Archive")
    ARCHIVE("Archive"),
    @XmlEnumValue("BackOffice")
    BACK_OFFICE("BackOffice"),
    @XmlEnumValue("Management")
    MANAGEMENT("Management"),
    @XmlEnumValue("OfficialBook")
    OFFICIAL_BOOK("OfficialBook"),
    @XmlEnumValue("Sms")
    SMS("Sms"),
    @XmlEnumValue("SubjectRegistry")
    SUBJECT_REGISTRY("SubjectRegistry");
    private final String value;

    EnumServiceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumServiceType fromValue(String v) {
        for (EnumServiceType c: EnumServiceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
