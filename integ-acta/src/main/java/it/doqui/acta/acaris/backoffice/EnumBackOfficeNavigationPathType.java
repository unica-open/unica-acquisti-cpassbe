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
 * &lt;p&gt;Classe Java per enumBackOfficeNavigationPathType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumBackOfficeNavigationPathType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="aoo"/&amp;gt;
 *     &amp;lt;enumeration value="strutture"/&amp;gt;
 *     &amp;lt;enumeration value="utenti"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumBackOfficeNavigationPathType")
@XmlEnum
public enum EnumBackOfficeNavigationPathType {

    @XmlEnumValue("aoo")
    AOO("aoo"),
    @XmlEnumValue("strutture")
    STRUTTURE("strutture"),
    @XmlEnumValue("utenti")
    UTENTI("utenti");
    private final String value;

    EnumBackOfficeNavigationPathType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumBackOfficeNavigationPathType fromValue(String v) {
        for (EnumBackOfficeNavigationPathType c: EnumBackOfficeNavigationPathType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
