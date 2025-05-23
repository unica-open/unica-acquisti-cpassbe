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

package it.doqui.acta.acaris.subjectregistryservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per enumStatoSoggetto.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumStatoSoggetto"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Definitivo"/&amp;gt;
 *     &amp;lt;enumeration value="Provvisorio"/&amp;gt;
 *     &amp;lt;enumeration value="NonCensito"/&amp;gt;
 *     &amp;lt;enumeration value="Duplicato"/&amp;gt;
 *     &amp;lt;enumeration value="Organigramma"/&amp;gt;
 *     &amp;lt;enumeration value="Cancellato"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumStatoSoggetto")
@XmlEnum
public enum EnumStatoSoggetto {

    @XmlEnumValue("Definitivo")
    DEFINITIVO("Definitivo"),
    @XmlEnumValue("Provvisorio")
    PROVVISORIO("Provvisorio"),
    @XmlEnumValue("NonCensito")
    NON_CENSITO("NonCensito"),
    @XmlEnumValue("Duplicato")
    DUPLICATO("Duplicato"),
    @XmlEnumValue("Organigramma")
    ORGANIGRAMMA("Organigramma"),
    @XmlEnumValue("Cancellato")
    CANCELLATO("Cancellato");
    private final String value;

    EnumStatoSoggetto(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumStatoSoggetto fromValue(String v) {
        for (EnumStatoSoggetto c: EnumStatoSoggetto.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
