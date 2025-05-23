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
 * &lt;p&gt;Classe Java per enumFascicoloTemporaneoStatoType.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="enumFascicoloTemporaneoStatoType"&amp;gt;
 *   &amp;lt;restriction base="{common.acaris.acta.doqui.it}string"&amp;gt;
 *     &amp;lt;enumeration value="Attivo"/&amp;gt;
 *     &amp;lt;enumeration value="Disattivo"/&amp;gt;
 *     &amp;lt;enumeration value="Congelato"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "enumFascicoloTemporaneoStatoType")
@XmlEnum
public enum EnumFascicoloTemporaneoStatoType {

    @XmlEnumValue("Attivo")
    ATTIVO("Attivo"),
    @XmlEnumValue("Disattivo")
    DISATTIVO("Disattivo"),
    @XmlEnumValue("Congelato")
    CONGELATO("Congelato");
    private final String value;

    EnumFascicoloTemporaneoStatoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumFascicoloTemporaneoStatoType fromValue(String v) {
        for (EnumFascicoloTemporaneoStatoType c: EnumFascicoloTemporaneoStatoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
